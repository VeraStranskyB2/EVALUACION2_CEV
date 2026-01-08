
import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL + '/api/Estado';

export const EstadoService = {
  async obtenerTodos() {
    try {
      const { data } = await axios.get(`${API_URL}/estados`)
      return data
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al obtener estados')
    }
  },

  async crear(estado) {
    try {
      const { data } = await axios.post(`${API_URL}/estados`, estado)
      return data
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al crear estado')
    }
  },

  async actualizar(id, estado) {
    try {
      const { data } = await axios.put(`${API_URL}/estados/${id}`, estado)
      return data
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al actualizar estado')
    }
  },

  async eliminar(id) {
    try {
      const { data } = await axios.delete(`${API_URL}/estados/${id}`)
      return data
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al eliminar estado')
    }
  }
}
