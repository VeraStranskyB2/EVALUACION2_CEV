import { defineStore } from 'pinia';
import { ref } from 'vue';
import SistemaSolarCalefaccionyACSService from '@/services/SistemasTecnicos/SistemaSolarCalefaccionyACSService';

export const useSistemaSolarCalefaccionyACSStore = defineStore('sistemaSolarCalefaccionyACS', () => {
    // Estado (State)
    const sistemas = ref([]);
    const loading = ref(false);
    const error = ref(null);

    // Acciones
    // description Obtiene todos los sistemas solares del servidor.
    
    async function fetchSistemas() {
        loading.value = true;
        error.value = null;
        try {
            const data = await SistemaSolarCalefaccionyACSService.obtenerTodos();
            sistemas.value = data;
        } catch (err) {
            error.value = 'Error al cargar los sistemas de Calefacción solare y ACS.';
            console.error('Detalle del error:', err);
        } finally {
            loading.value = false;
        }
    }

    // Crea un nuevo sistema solar.
    
    async function crearSistema(nuevoSistema) {
        loading.value = true;
        error.value = null;
        try {
            const sistemaCreado = await SistemaSolarCalefaccionyACSService.crear(nuevoSistema);
            // Agrega el nuevo sistema a la lista reactiva
            sistemas.value.push(sistemaCreado);
            return sistemaCreado; // Retorna el objeto creado
        } catch (err) {
            error.value = 'Error al crear el sistema solar.';
            console.error('Detalle del error:', err);
            throw err; // Lanza el error para que el componente pueda manejarlo
        } finally {
            loading.value = false;
        }
    }

    // Actualiza un sistema solar existente.
    
    async function actualizarSistema(id, datosActualizados) {
        loading.value = true;
        error.value = null;
        try {
            const sistemaActualizado = await SistemaSolarCalefaccionyACSService.actualizar(id, datosActualizados);
            // Actualiza el sistema en la lista reactiva
            const index = sistemas.value.findIndex(s => s.id === id);
            if (index !== -1) {
                sistemas.value[index] = sistemaActualizado;
            }
            return sistemaActualizado;
        } catch (err) {
            error.value = 'Error al actualizar el sistema solar.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Elimina un sistema solar.
    
    async function eliminarSistema(id) {
        loading.value = true;
        error.value = null;
        try {
            await SistemaSolarCalefaccionyACSService.eliminar(id);
            // Filtra el sistema eliminado de la lista
            sistemas.value = sistemas.value.filter(s => s.id !== id);
        } catch (err) {
            error.value = 'Error al eliminar el sistema solar.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }
    
    // Devuelve el estado y las acciones para que el componente pueda acceder a ellos
    return {
        sistemas,
        loading,
        error, 
        fetchSistemas,
        crearSistema,
        actualizarSistema,
        eliminarSistema
    };
});