import axios from 'axios';

// Obtiene la URL de la variable de entorno y define el endpoint específico para Región.
const API_URL = import.meta.env.VITE_API_URL + '/api/region';

const RegionService = {

    // Obtiene todas las regiones, con filtros opcionales.    
    async obtenerRegiones(params) {
        try {
            const response = await axios.get(API_URL, { params });
            return response.data;
        } catch (error) {
            console.error('Error al obtener las regiones:', error);
            throw error;
        }
    },

    // Obtiene una región por su ID.    
    async obtenerRegionPorId(id) {
        try {
            if (!id) {
                throw new Error('ID de la región es obligatorio');
            }
            const response = await axios.get(`${API_URL}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error al obtener la región con ID ${id}:`, error);
            throw error;
        }
    },

    //Crea una nueva región.  
    async crearRegion(payload) {
        try {
            if (!payload || typeof payload.nombre_region !== 'string' || !payload.nombre_region.trim()) {
                throw new Error('Datos de región inválidos: el nombre de la región es obligatorio');
            }
            const response = await axios.post(API_URL, payload);
            return response.data;
        } catch (error) {
            console.error('Error al crear la región:', error);
            throw error;
        }
    },

    // Actualiza una región existente por su ID.   
    async actualizarRegion(id, payload) {
        try {
            if (!id) {
                throw new Error('ID de la región es obligatorio');
            }
            const response = await axios.put(`${API_URL}/${id}`, payload);
            return response.data;
        } catch (error) {
            console.error(`Error al actualizar la región con ID ${id}:`, error);
            throw error;
        }
    },

    //Elimina una región por su ID.  
    async eliminarRegion(id) {
        try {
            if (!id) {
                throw new Error('ID de la región es obligatorio');
            }
            await axios.delete(`${API_URL}/${id}`);
            console.log(`Región con ID ${id} eliminada correctamente.`);
        } catch (error) {
            console.error(`Error al eliminar la región con ID ${id}:`, error);
            throw error;
        }
    }
};

export default RegionService;