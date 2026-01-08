import { defineStore } from 'pinia';
import { ref } from 'vue';
import SolicitudService from '@/services/Proceso/SolicitudService';

export const useSolicitudEvaluadorStore = defineStore('solicitudEvaluador', () => {
  const solicitudes = ref([]);
  const estados = ref([]);
  const loading = ref(false);
  const error = ref(null);

  const fetchEstados = async () => {
    try {
        const data = await SolicitudService.obtenerEstados();
        estados.value = data;
    } catch (e) {
        error.value = e.message || 'Error al cargar los estados.';
    }
  };

  const fetchSolicitudesPorEvaluador = async (rutEvaluador) => {
    loading.value = true;
    error.value = null;
    try {
        const data = await SolicitudService.obtenerPorEvaluador(rutEvaluador);
        solicitudes.value = data;
    } catch (e) {
        error.value = e.message || 'Error al cargar las solicitudes del evaluador.';
        throw e;
    } finally {
        loading.value = false;
    }
  };

  const aceptarSolicitud = async (solicitudId, rutEvaluador) => {
    try {
        // Se asume que el ID del estado 'aceptado' es 2
        await SolicitudService.actualizarEstado(solicitudId, { estado_id: 2, rut_evaluador: rutEvaluador });
        await fetchSolicitudesPorEvaluador(rutEvaluador); // Recarga la lista
    } catch (e) {
        error.value = e.message || 'Error al aceptar la solicitud.';
        throw e;
    }
  };

  const rechazarSolicitud = async (solicitudId, rutEvaluador) => {
    try {
        // Se asume que el ID del estado 'rechazado' es 3
        await SolicitudService.actualizarEstado(solicitudId, { estado_id: 3, rut_evaluador: rutEvaluador });
        await fetchSolicitudesPorEvaluador(rutEvaluador); // Recarga la lista
    } catch (e) {
        error.value = e.message || 'Error al rechazar la solicitud.';
        throw e;
    }
  };

  return {
    solicitudes,
    estados,
    loading,
    error,
    fetchEstados,
    fetchSolicitudesPorEvaluador,
    aceptarSolicitud,
    rechazarSolicitud,
  };
});