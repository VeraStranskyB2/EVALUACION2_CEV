import axios from 'axios';

// Obtiene la URL de la variable de entorno.
const API_URL = import.meta.env.VITE_API_URL + '/api/solar-calefaccion';

const SistemaSolarCalefaccionyACSService = {

    //Obtiene todos los registros de sistemas solares.     
    async obtenerTodos() {
        try {
            const response = await axios.get(API_URL);
            return response.data;
        } catch (error) {
            console.error('Error al obtener todos los sistemas solares:', error);
            throw error;
        }
    },

    //Crea un nuevo registro de sistema solar.  
    async crear(payload) {
        try {
            const response = await axios.post(API_URL, payload);
            return response.data;
        } catch (error) {
            console.error('Error al crear el sistema solar:', error);
            throw error;
        }
    },

    //Obtiene un sistema solar por su ID.    
    async obtenerPorId(id) {
        try {
            const response = await axios.get(`${API_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error al obtener el sistema solar de Calefacción y ACS por ID ${id}:`, error);
            throw error;
        }
    },

    //Actualiza un registro de sistema solar.   
    async actualizar(id, payload) {
        try {
            const response = await axios.put(`${API_URL}/${id}`, payload);
            return response.data;
        } catch (error) {
            console.error(`Error al actualizar el sistema solar de Calefacción y ACS por ID ${id}:`, error);
            throw error;
        }
    },

    //Elimina un registro de sistema solar por su ID.    
    async eliminar(id) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            console.log(`Sistema solar de Calefacción y ACS por ID ${id} eliminado correctamente.`);
        } catch (error) {
            console.error(`Error al eliminar el Sistema de Calefacción y ACS por ID ${id}:`, error);
            throw error;
        }
    }
};

export default SistemaSolarCalefaccionyACSService;