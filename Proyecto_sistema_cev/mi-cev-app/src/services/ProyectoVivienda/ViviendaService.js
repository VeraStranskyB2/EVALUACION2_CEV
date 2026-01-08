import axios from 'axios';

const BASE_URL = '/api/vivienda';

export default {
  
  obtenerViviendas(params) {
    return axios.get(BASE_URL, { params });
  },

  
  obtenerViviendaPorRol(rolVivienda) {
    return axios.get(`${BASE_URL}/${rolVivienda}`);
  },

  // Obtiene las viviendas asociadas a un proyecto específico.  
  obtenerViviendasPorProyecto(idProyecto) {
    return axios.get(`${BASE_URL}/proyecto/${idProyecto}`);
  },

  //Crea una nueva vivienda.  
  crearVivienda(vivienda) {
    return axios.post(BASE_URL, vivienda);
  },

  //Actualiza una vivienda existente.
  actualizarVivienda(rolVivienda, vivienda) {
    return axios.put(`${BASE_URL}/${rolVivienda}`, vivienda);
  },

  // Elimina una vivienda. 
  eliminarVivienda(rolVivienda) {
    return axios.delete(`${BASE_URL}/${rolVivienda}`);
  }
};
