import axios from 'axios';

// Define la URL base para el recurso de evaluaciones
const EVALUACION_URL = '/api/evaluaciones'
const PROYECTO_URL = '/api/proyectos'

export default {
    
    async crear(payload) {
        return await axios.post(EVALUACION_URL, payload)
    },
    
    async obtenerPorId(id) {
        return await axios.get(`${EVALUACION_URL}/${id}`);
    },
    
    async obtenerPorEvaluador(rutEvaluador) {
        return await axios.get(`${EVALUACION_URL}?evaluador_rut=${rutEvaluador}`);
    },     
   
    async obtenerProyectos() {
        return await axios.get(PROYECTO_URL);
    }

}


