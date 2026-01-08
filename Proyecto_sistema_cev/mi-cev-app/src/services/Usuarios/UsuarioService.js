import axios from 'axios'

const API_URL = '/api/usuarios'
import.meta.env.VITE_API_URL || 'http://localhost:8080/api/usuarios'

export const UsuarioService = {
  obtenerTodos() {
    return axios.get(API_URL).then(res => res.data)
  },

  obtenerPorId(rut_usuario) {
    return axios.get(`${API_URL}/${rut_usuario}`).then(res => res.data)
  },

  crear(usuario) {
    return axios.post(API_URL, usuario).then(res => res.data)
  },

  actualizar(rut_usuario, usuario) {
    return axios.put(`${API_URL}/${rut_usuario}`, usuario).then(res => res.data)
  },

  eliminar(rut_usuario) {
    return axios.delete(`${API_URL}/${rut_usuario}`).then(res => res.data)
  }
}

