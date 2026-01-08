import axios from 'axios';

// Define la URL base de tu API
const BASE_URL = 'http://tu-api.com/api/solicitudes';
const ESTADOS_URL = 'http://tu-api.com/api/estados';


export default {
  // Métodos usados por el CLIENTE
  async obtenerPorCliente(rutUsuario) {
    const response = await axios.get(`${BASE_URL}/cliente/${rutUsuario}`);
    return response.data;
  },

  async crear(payload) {
    const response = await axios.post(BASE_URL, payload);
    return response.data;
  },

  // Métodos usados por el EVALUADOR
  async obtenerPorEvaluador(rutEvaluador) {
    const response = await axios.get(`${BASE_URL}/evaluador/${rutEvaluador}`);
    return response.data;
  },

  async aceptar(solicitudId) {
    const response = await axios.put(`${BASE_URL}/${solicitudId}/aceptar`);
    return response.data;
  },

  async rechazar(solicitudId) {
    const response = await axios.put(`${BASE_URL}/${solicitudId}/rechazar`);
    return response.data;
  },

  // Métodos compartidos
  async obtenerEstados() {
    // Asumiendo que la URL de estados puede ser diferente, o puedes usar BASE_URL
    const response = await axios.get(ESTADOS_URL);
    return response.data;
  },

  async actualizarEstado(solicitudId, estadoId) {
    const response = await axios.put(`${BASE_URL}/${solicitudId}/estado/${estadoId}`);
    return response.data;
  },

};