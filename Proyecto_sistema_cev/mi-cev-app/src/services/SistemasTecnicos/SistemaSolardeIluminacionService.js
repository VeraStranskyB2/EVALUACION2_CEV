import axios from 'axios';

// Obtiene la URL de la variable de entorno y define el endpoint específico.
const API_URL = import.meta.env.VITE_API_URL + '/api/solar-iluminacion';

const SistemaSolardeIluminacionService = {

    // Obtiene todos los registros de sistemas solares de iluminación.    
    async obtenerTodos() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Error al obtener todos los sistemas solares de iluminación:', error);
            throw error;
        }
    },

    //Crea un nuevo registro de sistemas solares de iluminación.   
    async crear(payload) {
        try {
            const response = await axios.post(API_URL, payload);
            return response.data;
        } catch (error) {
            console.error('Error al crear el sistema solar de iluminación:', error);
            throw error;
        }
    },

    //Obtiene un sistema solar de iluminación por su ID.    
    async obtenerPorId(id) {
        try {
            const response = await axios.get(`${API_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error al obtener el sistema solar de iluminación por ID ${id}:`, error);
            throw error;
        }
    },

    // Actualiza un registro de sistema solar de iluminación.    
    async actualizar(id, payload) {
        try {
            const response = await axios.put(`${API_URL}/${id}`, payload);
            return response.data;
        } catch (error) {
            console.error(`Error al actualizar el sistema solar de iluminación por ID ${id}:`, error);
            throw error;
        }
    },

    // Elimina un registro de sistema solar de iluminación por su ID.    
    async eliminar(id) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            console.log(`Sistema solar de iluminación con ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error(`Error al eliminar el sistema solar de iluminación por ID ${id}:`, error);
            throw error;
        }
    }
};

export default SistemaSolardeIluminacionService;