import { defineStore } from 'pinia';
import { ref } from 'vue';
import ZonaTermicaService from '@/services/Ubicacion/ZonaTermicaService'; 

export const useZonaTermicaStore = defineStore('zonaTermica', () => {
    // Estado (State)
    const zonas = ref([]);
    const loading = ref(false);
    const error = ref(null);

    // Acciones
    // description Obtiene todas las zonas térmicas del servidor.

    async function fetchZonas() {
        loading.value = true;
        error.value = null;
        try {
            const data = await ZonaTermicaService.obtenerTodos();
            zonas.value = data || [];
        } catch (err) {
            error.value = 'Error al cargar las zonas térmicas.';
            console.error('Detalle del error:', err);
        } finally {
            loading.value = false;
        }
    }

    // Crea un nuevo registro de zona térmica.
    
    async function crearZona(nuevaZona) {
        loading.value = true;
        error.value = null;
        try {
            const zonaCreada = await ZonaTermicaService.crear(nuevaZona);
            zonas.value.push(zonaCreada);
            return zonaCreada;
        } catch (err) {
            error.value = 'Error al crear la zona térmica.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Actualiza una zona térmica existente.
    
    async function actualizarZona(id, datosActualizados) {
        loading.value = true;
        error.value = null;
        try {
            const zonaActualizada = await ZonaTermicaService.actualizar(id, datosActualizados);
            const index = zonas.value.findIndex(z => z.id_zona_termica === id);
            if (index !== -1) {
                zonas.value[index] = zonaActualizada;
            }
            return zonaActualizada;
        } catch (err) {
            error.value = 'Error al actualizar la zona térmica.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Elimina una zona térmica por su ID.
    
    async function eliminarZona(id) {
        loading.value = true;
        error.value = null;
        try {
            await ZonaTermicaService.eliminar(id);
            zonas.value = zonas.value.filter(z => z.id_zona_termica !== id);
        } catch (err) {
            error.value = 'Error al eliminar la zona térmica.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Devuelve el estado y las acciones para que el componente pueda acceder a ellos
    return {
        zonas,
        loading,
        error,
        fetchZonas,
        crearZona,
        actualizarZona,
        eliminarZona
    };
});