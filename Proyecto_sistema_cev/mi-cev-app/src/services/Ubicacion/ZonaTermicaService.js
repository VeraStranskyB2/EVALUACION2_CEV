import axios from 'axios';

// Obtiene la URL de la variable de entorno y define el endpoint específico para ZonaTermica.
const API_URL = import.meta.env.VITE_API_URL + '/api/zonastermicas';

const ZonaTermicaService = {

    //Obtiene todos los registros de zonas térmicas.    
    async obtenerTodos() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Error al obtener todas las zonas térmicas:', error);
            throw error;
        }
    },

    //Crea un nuevo registro de zona térmica.    
    async crear(payload) {
        try {
            const response = await axios.post(API_URL, payload);
            return response.data;
        } catch (error) {
            console.error('Error al crear la zona térmica:', error);
            throw error;
        }
    },

    //Obtiene una zona térmica por su ID.   
    async obtenerPorId(id) {
        try {
            const response = await axios.get(`${API_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error al obtener la zona térmica con ID ${id}:`, error);
            throw error;
        }
    },

    // Actualiza un registro de zona térmica.     
    async actualizar(id, payload) {
        try {
            const response = await axios.put(`${API_URL}/${id}`, payload);
            return response.data;
        } catch (error) {
            console.error(`Error al actualizar la zona térmica con ID ${id}:`, error);
            throw error;
        }
    },

    //Elimina un registro de zona térmica por su ID.   
    async eliminar(id) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            console.log(`Zona térmica con ID ${id} eliminada correctamente.`);
        } catch (error) {
            console.error(`Error al eliminar la zona térmica con ID ${id}:`, error);
            throw error;
        }
    }
};

export default ZonaTermicaService;