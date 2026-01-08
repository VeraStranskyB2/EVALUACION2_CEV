import axios from 'axios'

const BASE_URL = '/api/documentos'
const ESTADOS_URL = '/api/estados'

export default {
  obtenerPorSolicitud(idSolicitud) {
    return axios.get(BASE_URL, {
      params: { solicitud_id: idSolicitud }
    })
  },

  subirDocumento(formData) {
    return axios.post(BASE_URL, formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
  },

  obtenerEstados() {
    return axios.get(ESTADOS_URL)
  }
}
