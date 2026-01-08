import { defineStore } from 'pinia';
import { ref } from 'vue';
import ComunaService from '@/services/Ubicacion/ComunaService';

export const useComunaStore = defineStore('comuna', () => {
    // Estado (State)
    const comunas = ref([]);
    const loading = ref(false);
    const error = ref(null);

    // Acciones
    // Obtiene todas las comunas del servidor.
    
    async function fetchComunas() {
        loading.value = true;
        error.value = null;
        try {
            const data = await ComunaService.obtenerTodos();
            comunas.value = data || [];
        } catch (err) {
            error.value = 'Error al cargar las comunas.';
            console.error('Detalle del error:', err);
        } finally {
            loading.value = false;
        }
    }

    // description Crea un nuevo registro de comuna.
    
    async function crearComuna(nuevaComuna) {
        loading.value = true;
        error.value = null;
        try {
            const comunaCreada = await ComunaService.crear(nuevaComuna);
            comunas.value.push(comunaCreada);
            return comunaCreada;
        } catch (err) {
            error.value = 'Error al crear la comuna.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Actualiza una comuna existente.
    
    async function actualizarComuna(id, datosActualizados) {
        loading.value = true;
        error.value = null;
        try {
            const comunaActualizada = await ComunaService.actualizar(id, datosActualizados);
            const index = comunas.value.findIndex(c => c.id_comuna === id);
            if (index !== -1) {
                comunas.value[index] = comunaActualizada;
            }
            return comunaActualizada;
        } catch (err) {
            error.value = 'Error al actualizar la comuna.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Elimina una comuna por su ID.
       
    async function eliminarComuna(id) {
        loading.value = true;
        error.value = null;
        try {
            await ComunaService.eliminar(id);
            comunas.value = comunas.value.filter(c => c.id_comuna !== id);
        } catch (err) {
            error.value = 'Error al eliminar la comuna.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Devuelve el estado y las acciones para que el componente pueda acceder a ellos
    return {
        comunas,
        loading,
        error,
        fetchComunas,
        crearComuna,
        actualizarComuna,
        eliminarComuna
    };
});