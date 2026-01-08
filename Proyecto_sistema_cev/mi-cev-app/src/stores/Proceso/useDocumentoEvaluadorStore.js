import { defineStore } from 'pinia';
import DocumentoService from '@/services/Proceso/DocumentoService';
import { ref } from 'vue';

export const useDocumentoEvaluadorStore = defineStore('documentoEvaluador', () => {
  // Estado para los documentos, el estado de carga y los errores
  const documentos = ref([]);
  const loading = ref(false);
  const error = ref(null);

  // Acción para obtener documentos desde el servicio
  async function fetchDocumentosPorSolicitud(solicitudId) {
    loading.value = true;
    error.value = null;
    try {
      // Llamada unificada a la API
      const response = await DocumentoService.obtenerDocumentosPorSolicitud(solicitudId);
      documentos.value = response.data;
    } catch (err) {
      error.value = 'Error al cargar los documentos. Por favor, intente de nuevo.';
      console.error(err);
    } finally {
      loading.value = false;
    }
  }

  return { documentos, loading, error, fetchDocumentosPorSolicitud };
});