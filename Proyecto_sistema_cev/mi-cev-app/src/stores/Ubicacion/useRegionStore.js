import { defineStore } from 'pinia';
import { ref } from 'vue';
import RegionService from '@/services/Ubicacion/RegionService';

export const useRegionStore = defineStore('region', () => {
    // Estado (State)
    const regiones = ref([]);
    const loading = ref(false);
    const error = ref(null);

    // Acciones
    // Obtiene todas las regiones del servidor.
    
    async function fetchRegiones() {
        loading.value = true;
        error.value = null;
        try {
            const data = await RegionService.obtenerRegiones();
            regiones.value = data || [];
        } catch (err) {
            error.value = 'Error al cargar las regiones.';
            console.error('Detalle del error:', err);
        } finally {
            loading.value = false;
        }
    }

    // Crea un nuevo registro de región.
    
    async function crearRegion(nuevaRegion) {
        loading.value = true;
        error.value = null;
        try {
            const regionCreada = await RegionService.crearRegion(nuevaRegion);
            regiones.value.push(regionCreada.data);
            return regionCreada.data;
        } catch (err) {
            error.value = 'Error al crear la región.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // description Actualiza una región existente.
    
    async function actualizarRegion(id, datosActualizados) {
        loading.value = true;
        error.value = null;
        try {
            const regionActualizada = await RegionService.actualizarRegion(id, datosActualizados);
            const index = regiones.value.findIndex(r => r.id_region === id);
            if (index !== -1) {
                regiones.value[index] = regionActualizada.data;
            }
            return regionActualizada.data;
        } catch (err) {
            error.value = 'Error al actualizar la región.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Elimina una región por su ID.
    
    async function eliminarRegion(id) {
        loading.value = true;
        error.value = null;
        try {
            await RegionService.eliminarRegion(id);
            regiones.value = regiones.value.filter(r => r.id_region !== id);
        } catch (err) {
            error.value = 'Error al eliminar la región.';
            console.error('Detalle del error:', err);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    // Devuelve el estado y las acciones para que el componente pueda acceder a ellos
    return {
        regiones,
        loading,
        error,
        fetchRegiones,
        crearRegion,
        actualizarRegion,
        eliminarRegion
    };
});