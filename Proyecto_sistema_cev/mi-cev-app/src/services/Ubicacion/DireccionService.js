import axios from 'axios';

// Obtiene la URL base de la API desde las variables de entorno.
const API_URL = import.meta.env.VITE_API_URL + '/api/direcciones';

const DireccionService = {

    // Obtiene todos los registros de direcciones.  
    async obtenerTodos() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Error al obtener todas las direcciones:', error);
            throw error;
        }
    },

    // Crea un nuevo registro de dirección.
    async crear(payload) {
        try {
            const response = await axios.post(API_URL, payload);
            return response.data;
        } catch (error) {
            console.error('Error al crear la dirección:', error);
            throw error;
        }
    },

    //Obtiene una dirección por su ID.   
    async obtenerPorId(id) {
        try {
            const response = await axios.get(`${API_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error al obtener la dirección con ID ${id}:`, error);
            throw error;
        }
    },

    //Actualiza un registro de dirección.    
    async actualizar(id, payload) {
        try {
            const response = await axios.put(`${API_URL}/${id}`, payload);
            return response.data;
        } catch (error) {
            console.error(`Error al actualizar la dirección con ID ${id}:`, error);
            throw error;
        }
    },

    // Elimina un registro de dirección por su ID.    
    async eliminar(id) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            console.log(`Dirección con ID ${id} eliminada correctamente.`);
        } catch (error) {
            console.error(`Error al eliminar la dirección con ID ${id}:`, error);
            throw error;
        }
    }
};

export default DireccionService;