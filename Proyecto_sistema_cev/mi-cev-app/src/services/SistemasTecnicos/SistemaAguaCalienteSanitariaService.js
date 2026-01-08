import axios from 'axios';

// Obtiene la URL de la variable de entorno y define el endpoint específico para ACS.
const API_URL = import.meta.env.VITE_API_URL + '/api/sistemaacs';

const SistemaACSService = {

    //Obtiene todos los registros de sistemas de agua caliente sanitaria.   
    async obtenerTodos() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Error al obtener todos los sistemas ACS:', error);
            throw error;
        }
    },

    //Crea un nuevo registro de sistema de agua caliente sanitaria.   
    async crear(payload) {
        try {
            const response = await axios.post(API_URL, payload);
            return response.data;
        } catch (error) {
            console.error('Error al crear el sistema ACS:', error);
            throw error;
        }
    },

    // Obtiene un sistema de agua caliente sanitaria por su ID.    
    async obtenerPorId(id) {
        try {
            const response = await axios.get(`${API_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error al obtener el sistema ACS con ID ${id}:`, error);
            throw error;
        }
    },

    // Actualiza un registro de sistema de agua caliente sanitaria.   
    async actualizar(id, payload) {
        try {
            const response = await axios.put(`${API_URL}/${id}`, payload);
            return response.data;
        } catch (error) {
            console.error(`Error al actualizar el sistema ACS con ID ${id}:`, error);
            throw error;
        }
    },

    // Elimina un registro de sistema de agua caliente sanitaria por su ID.    
    async eliminar(id) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            console.log(`Sistema ACS con ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error(`Error al eliminar el sistema ACS con ID ${id}:`, error);
            throw error;
        }
    }
};

export default SistemaACSService;
