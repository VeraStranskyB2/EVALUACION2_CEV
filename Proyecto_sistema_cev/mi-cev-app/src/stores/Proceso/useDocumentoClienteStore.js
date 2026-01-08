import { defineStore } from 'pinia'
import { ref } from 'vue' 
import DocumentoService from '@/services/Proceso/DocumentoService'

export const useDocumentoClienteStore = defineStore('documentoCliente', () => {
  // Estado
  const documentos = ref([])
  const loading = ref(false)
  const error = ref(null)

  // Acciones
  // Carga todos los documentos asociados a una solicitud específica. 
  async function fetchDocumentosPorSolicitud(idSolicitud) {
    loading.value = true
    error.value = null
    try {
      // Llamada unificada a la API
      const { data } = await DocumentoService.obtenerDocumentosPorSolicitud(idSolicitud)
      documentos.value = data
    } catch (e) {
      error.value = 'No se pudieron cargar los documentos.'
      console.error('Error al obtener documentos:', e)
    } finally {
      loading.value = false
    }
  }

  // Sube un nuevo documento al servidor.  
  async function subirDocumento(formData) {
    loading.value = true
    error.value = null
    try {
      await DocumentoService.subirDocumento(formData)
    } catch (e) {
      error.value = 'No se pudo subir el documento.'
      console.error('Error al subir documento:', e) 
      throw e // Propaga el error para que el componente lo maneje
    } finally {
      loading.value = false
    }
  }

  // Se retornan las propiedades y métodos
  return {
    documentos,
    loading,
    error,
    fetchDocumentosPorSolicitud,
    subirDocumento
  }
})