import axios from 'axios';

// Obtiene la URL de la variable de entorno.
const API_URL = import.meta.env.VITE_API_URL + '/api/sistemacalefaccion';

const SistemaCalefaccionService = {

    // Obtiene todos los registros de sistemas de calefacción.    
    async obtenerTodos() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Error al obtener todos los sistemas de calefacción:', error);
            throw error;
        }
    },

    //Crea un nuevo registro de sistema de calefacción.    
    async crear(payload) {
        try {
            const response = await axios.post(API_URL, payload);
            return response.data;
        } catch (error) {
            console.error('Error al crear el sistema de calefacción:', error);
            throw error;
        }
    },

    //Obtiene un sistema de calefacción por su ID.   
    async obtenerPorId(id) {
        try {
            const response = await axios.get(`${API_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error al obtener el sistema con ID ${id}:`, error);
            throw error;
        }
    },

    //Actualiza un registro de sistema de calefacción.   
    async actualizar(id, payload) {
        try {
            const response = await axios.put(`${API_URL}/${id}`, payload);
            return response.data;
        } catch (error) {
            console.error(`Error al actualizar el sistema con ID ${id}:`, error);
            throw error;
        }
    },

    //Elimina un registro de sistema de calefacción por su ID.    
    async eliminar(id) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            console.log(`Sistema con ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error(`Error al eliminar el sistema con ID ${id}:`, error);
            throw error;
        }
    }
};

export default SistemaCalefaccionService;