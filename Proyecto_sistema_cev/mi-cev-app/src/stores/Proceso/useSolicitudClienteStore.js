import { defineStore } from 'pinia';
import { ref } from 'vue';
import SolicitudService from '@/services/Proceso/SolicitudService';

export const useSolicitudClienteStore = defineStore('solicitud', () => {
    // Estado
    const solicitudes = ref([]);
    const estados = ref([]);
    const loading = ref(false);
    const error = ref(null);

    // Acciones
    // Obtiene las solicitudes de un cliente específico.    
    async function fetchSolicitudesCliente(rutUsuario) {
        loading.value = true;
        error.value = null;
        try {
            const data = await SolicitudService.obtenerPorCliente(rutUsuario);
            solicitudes.value = data;
        } catch (e) {
            error.value = e.message || 'Error al cargar las solicitudes';
        } finally {
            loading.value = false;
        }
    }

    //Crea una nueva solicitud.    
    async function crearSolicitud(payload) {
        loading.value = true;
        error.value = null;
        try {
            const data = await SolicitudService.crear(payload);
            solicitudes.value.push(data);
            return data;
        } catch (e) {
            error.value = e.message || 'No se pudo crear la solicitud';
            throw e;
        } finally {
            loading.value = false;
        }
    }

    // Actualiza una solicitud existente.    
    async function actualizarSolicitud(idSolicitud, payload) {
        loading.value = true;
        error.value = null;
        try {
            const data = await SolicitudService.actualizar(idSolicitud, payload);
            const index = solicitudes.value.findIndex(sol => sol.id_solicitud === idSolicitud);
            if (index !== -1) {
                solicitudes.value[index] = data;
            }
        } catch (e) {
            error.value = e.message || 'No se pudo actualizar la solicitud';
            throw e;
        } finally {
            loading.value = false;
        }
    }

    /**
     * @description Elimina una solicitud.
     * @param {string} idSolicitud - El ID de la solicitud a eliminar.
     */
    async function eliminarSolicitud(idSolicitud) {
        loading.value = true;
        error.value = null;
        try {
            await SolicitudService.eliminar(idSolicitud);
            solicitudes.value = solicitudes.value.filter(sol => sol.id_solicitud !== idSolicitud);
        } catch (e) {
            error.value = e.message || 'No se pudo eliminar la solicitud';
            throw e;
        } finally {
            loading.value = false;
        }
    }

    /**
     * @description Obtiene todos los posibles estados de las solicitudes.
     */
    async function fetchEstados() {
        error.value = null;
        try {
            const data = await SolicitudService.obtenerEstados();
            estados.value = data;
        } catch (e) {
            error.value = e.message || 'Error al cargar estados';
        }
    }

    // Se retorna el estado y las acciones para que el componente pueda acceder a ellos
    return {
        solicitudes,
        estados,
        loading,
        error,
        fetchSolicitudesCliente,
        crearSolicitud,
        actualizarSolicitud,
        eliminarSolicitud,
        fetchEstados
    };
});