import axios from 'axios';

// La URL base debe coincidir con el endpoint de tu servidor de API
const BASE_URL = '/api/proyectos';

export default {
  // Obtiene todos los proyectos, con opción de pasar parámetros de consulta
  async obtenerProyectos(params) {
    try {
      const response = await axios.get(BASE_URL, { params });
      return response.data;
    } catch (error) {
      console.error("Error al obtener proyectos:", error);
      throw error;
    }
  },
  
  // Obtiene un proyecto específico por su ID
  async obtenerProyectoPorId(id) {
    try {
      const response = await axios.get(`${BASE_URL}/${id}`);
      return response.data;
    } catch (error) {
      console.error("Error al obtener el proyecto:", error);
      throw error;
    }
  },
  
  // Crea un nuevo proyecto
  async crearProyecto(proyecto) {
    try {
      const response = await axios.post(BASE_URL, proyecto);
      return response.data;
    } catch (error) {
      console.error("Error al crear el proyecto:", error);
      throw error;
    }
  },
  
  // Actualiza un proyecto existente por su ID
  async actualizarProyecto(id, proyecto) {
    try {
      const response = await axios.put(`${BASE_URL}/${id}`, proyecto);
      return response.data;
    } catch (error) {
      console.error("Error al actualizar el proyecto:", error);
      throw error;
    }
  },
  
  // Elimina un proyecto por su ID
  async eliminarProyecto(id) {
    try {
      const response = await axios.delete(`${BASE_URL}/${id}`);
      return response.data;
    } catch (error) {
      console.error("Error al eliminar el proyecto:", error);
      throw error;
    }
  }
};
