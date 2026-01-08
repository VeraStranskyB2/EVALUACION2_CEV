import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/evaluadores'

export const EvaluadorService = {
  obtenerTodos() {
    return axios.get(API_URL).then(res => res.data)
  },

  obtenerPorId(rut_usuario) {
    return axios.get(`${API_URL}/${rut_usuario}`).then(res => res.data)
  },

  crear(evaluador) {
    return axios.post(API_URL, evaluador).then(res => res.data)
  },

  actualizar(rut_usuario, evaluador) {
    return axios.put(`${API_URL}/${rut_usuario}`, evaluador).then(res => res.data)
  },

  eliminar(rut_usuario) {
    return axios.delete(`${API_URL}/${rut_usuario}`).then(res => res.data)
  }
}
