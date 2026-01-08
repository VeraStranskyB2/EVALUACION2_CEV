import axios from 'axios';

// Obtiene la URL de la variable de entorno y define el endpoint específico para Comuna.
const API_URL = import.meta.env.VITE_API_URL + '/api/comunas';

const ComunaService = {

    // Obtiene todos los registros de comunas.  
    async obtenerTodos() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Error al obtener todas las comunas:', error);
            throw error;
        }
    },

    // Crea un nuevo registro de comuna.
    async crear(payload) {
        try {
            const response = await axios.post(API_URL, payload);
            return response.data;
        } catch (error) {
            console.error('Error al crear la comuna:', error);
            throw error;
        }
    },

    // Obtiene una comuna por su ID.    
    async obtenerPorId(id) {
        try {
            const response = await axios.get(`${API_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error al obtener la comuna con ID ${id}:`, error);
            throw error;
        }
    },

    // Actualiza un registro de comuna.    
    async actualizar(id, payload) {
        try {
            const response = await axios.put(`${API_URL}/${id}`, payload);
            return response.data;
        } catch (error) {
            console.error(`Error al actualizar la comuna con ID ${id}:`, error);
            throw error;
        }
    },

    // Elimina un registro de comuna por su ID.  
    async eliminar(id) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            console.log(`Comuna con ID ${id} eliminada correctamente.`);
        } catch (error) {
            console.error(`Error al eliminar la comuna con ID ${id}:`, error);
            throw error;
        }
    }
};

export default ComunaService;