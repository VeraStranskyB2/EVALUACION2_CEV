import { defineStore } from 'pinia';
import { ref } from 'vue';
import DireccionService from '@/services/Ubicacion/DireccionService'; 

export const useDireccionStore = defineStore('direccion', () => {
    // Estado (State)
    const direcciones = ref([]);
    const loading = ref(false);
    const error = ref(null);

    // Acciones
    // Obtiene todos los registros de direcciones del servidor.
    
    async function fetchDirecciones() {
        loading.value = true;
        error.value = null;
        try {
            const data = await DireccionService.obtenerTodos();
            direcciones.value = data || [];
        } catch (err) {
            error.value = 'Error al cargar las direcciones.';
            console.error('Detalle del error:', err);
        } finally {
            loading.value = false;
        }
    }

    // description Crea un nuevo registro de dirección.
    
    async function crearDireccion(nuevaDireccion) {
        loading.value = true;
        error.value = null;
        try {
            const direccionCreada = await DireccionService.crear(nuevaDireccion);
            direcciones.value.push(direccionCreada);
            return direccionCreada;
        } catch (err) {
            error.value = 'Error al crear la dirección.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Actualiza una dirección existente.
    
    async function actualizarDireccion(id, datosActualizados) {
        loading.value = true;
        error.value = null;
        try {
            const direccionActualizada = await DireccionService.actualizar(id, datosActualizados);
            const index = direcciones.value.findIndex(d => d.id_direccion === id);
            if (index !== -1) {
                direcciones.value[index] = direccionActualizada;
            }
            return direccionActualizada;
        } catch (err) {
            error.value = 'Error al actualizar la dirección.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Elimina una dirección por su ID.
    
    async function eliminarDireccion(id) {
        loading.value = true;
        error.value = null;
        try {
            await DireccionService.eliminar(id);
            direcciones.value = direcciones.value.filter(d => d.id_direccion !== id);
        } catch (err) {
            error.value = 'Error al eliminar la dirección.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Devuelve el estado y las acciones para que el componente pueda acceder a ellos
    return {
        direcciones,
        loading,
        error,
        fetchDirecciones,
        crearDireccion,
        actualizarDireccion,
        eliminarDireccion
    };
});