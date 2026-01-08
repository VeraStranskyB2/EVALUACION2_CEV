import { defineStore } from 'pinia';
import { ref } from 'vue';
import SistemaVentilacionService from '@/services/SistemasTecnicos/SistemaVentilacionService';

export const useSistemaVentilacionStore = defineStore('sistemaVentilacion', () => {
    // Estado (State)
    const sistemas = ref([]);
    const loading = ref(false);
    const error = ref(null);

    // Acciones
    // Obtiene todos los sistemas de calefacción del servidor.
    
    async function fetchSistemas() {
        loading.value = true;
        error.value = null;
        try {
            const data = await SistemaVentilacionService.obtenerTodos();
            sistemas.value = data;
        } catch (err) {
            error.value = 'Error al cargar los sistemas de ventilación.';
            console.error('Detalle del error:', err);
        } finally {
            loading.value = false;
        }
    }

    // description Crea un nuevo sistema de ventilación.
    
    async function crearSistema(nuevoSistema) {
        loading.value = true;
        error.value = null;
        try {
            const sistemaCreado = await SistemaVentilacionService.crear(nuevoSistema);
            // Agrega el nuevo sistema a la lista reactiva
            sistemas.value.push(sistemaCreado); 
            return sistemaCreado; // Retorna el objeto creado
        } catch (err) {
            error.value = 'Error al crear el sistema de ventilación.';
            console.error('Detalle del error:', err);
            throw err; // Lanza el error para que el componente pueda manejarlo
        } finally {
            loading.value = false;
        }
    }
    
    // description Actualiza un sistema de ventilación existente.
   
    async function actualizarSistema(id, datosActualizados) {
        loading.value = true;
        error.value = null;
        try {
            const sistemaActualizado = await SistemaVentilacionService.actualizar(id, datosActualizados);
            // Actualiza el sistema en la lista reactiva
            const index = sistemas.value.findIndex(s => s.id === id);
            if (index !== -1) {
                sistemas.value[index] = sistemaActualizado;
            }
            return sistemaActualizado;
        } catch (err) {
            error.value = 'Error al actualizar el sistema de ventilación.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Elimina un sistema de ventilación.
    
    async function eliminarSistema(id) {
        loading.value = true;
        error.value = null;
        try {
            await SistemaVentilacionService.eliminar(id);
            // Filtra el sistema eliminado de la lista
            sistemas.value = sistemas.value.filter(s => s.id !== id);
        } catch (err) {
            error.value = 'Error al eliminar el sistema de ventilación.';
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
