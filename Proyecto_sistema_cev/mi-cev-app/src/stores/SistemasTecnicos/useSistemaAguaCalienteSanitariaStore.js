import { defineStore } from 'pinia';
import { ref } from 'vue';
import SistemaAguaCalienteSanitariaService from '@/services/SistemasTecnicos/SistemaAguaCalienteSanitariaService';

export const useSistemaAguaCalienteSanitariaStore = defineStore('sistemaAguaCalienteSanitaria', () => {
    // Estado (State)
    const sistemasAguaCalienteSanitaria = ref([]);
    const loading = ref(false);
    const error = ref(null);

    // Acciones
    // Obtiene todos los sistemas de agua caliente sanitaria del servidor.
    
    async function fetchSistemasAguaCalienteSanitaria() {
        loading.value = true;
        error.value = null;
        try {
            const data = await SistemaAguaCalienteSanitariaService.obtenerTodos();
            sistemasAguaCalienteSanitaria.value = data;
        } catch (err) {
            error.value = 'Error al cargar los sistemas de agua caliente sanitaria.';
            console.error('Detalle del error:', err);
        } finally {
            loading.value = false;
        }
    }

    // Crea un nuevo sistema de agua caliente sanitaria.
    
    async function crearSistemaAguaCalienteSanitaria(nuevoSistema) {
        loading.value = true;
        error.value = null;
        try {
            const sistemaCreado = await SistemaAguaCalienteSanitariaService.crear(nuevoSistema);
            // Agrega el nuevo sistema a la lista reactiva
            sistemasAguaCalienteSanitaria.value.push(sistemaCreado); 
            return sistemaCreado; // Retorna el objeto creado
        } catch (err) {
            error.value = 'Error al crear el sistema de agua caliente sanitaria.';
            console.error('Detalle del error:', err);
            throw err; // Lanza el error para que el componente pueda manejarlo
        } finally {
            loading.value = false;
        }
    }
    
    // Actualiza un sistema de agua caliente sanitaria existente.
     
    async function actualizarSistemaAguaCalienteSanitaria(id, datosActualizados) {
        loading.value = true;
        error.value = null;
        try {
            const sistemaActualizado = await SistemaAguaCalienteSanitariaService.actualizar(id, datosActualizados);
            // Actualiza el sistema en la lista reactiva
            const index = sistemasAguaCalienteSanitaria.value.findIndex(s => s.id === id);
            if (index !== -1) {
                sistemasAguaCalienteSanitaria.value[index] = sistemaActualizado;
            }
            return sistemaActualizado;
        } catch (err) {
            error.value = 'Error al actualizar el sistema de agua caliente sanitaria.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Elimina un sistema de agua caliente sanitaria.
    
    async function eliminarSistemaAguaCalienteSanitaria(id) {
        loading.value = true;
        error.value = null;
        try {
            await SistemaAguaCalienteSanitariaService.eliminar(id);
            // Filtra el sistema eliminado de la lista
            sistemasAguaCalienteSanitaria.value = sistemasAguaCalienteSanitaria.value.filter(s => s.id !== id);
        } catch (err) {
            error.value = 'Error al eliminar el sistema de agua caliente sanitaria.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }
    
    // Devuelve el estado y las acciones para que el componente pueda acceder a ellos
    return {
        sistemasAguaCalienteSanitaria,
        loading,
        error, 
        fetchSistemasAguaCalienteSanitaria,
        crearSistemaAguaCalienteSanitaria,
        actualizarSistemaAguaCalienteSanitaria,
        eliminarSistemaAguaCalienteSanitaria
    };
});