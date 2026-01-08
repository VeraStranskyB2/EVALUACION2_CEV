import { defineStore } from 'pinia';
import { ref } from 'vue';
import EvaluacionService from '@/services/Proceso/EvaluacionService'; 

export const useEvaluacionStore = defineStore('evaluacion', () => {
  // Estado (State)
  const evaluaciones = ref([]);
  const evaluacionActual = ref(null);
  const loading = ref(false);
  const error = ref(null);

  // Acciones (Actions)
  // Inicia una nueva evaluación en el sistema.  
  async function iniciarEvaluacion(payload) {
    loading.value = true;
    error.value = null;
    try {
        const data = await EvaluacionService.crear(payload);
        const nuevaEvaluacionId = data.id_evaluacion;
        evaluacionActual.value = data;
        return nuevaEvaluacionId;
    } catch (e) {
        error.value = 'No se pudo iniciar la evaluación. Por favor, intente de nuevo.';
        return null;
    } finally {
        loading.value = false;
    }
  }

  // Obtiene una evaluación específica por su ID.  
  async function fetchEvaluacion(evaluacionId) {
    loading.value = true;
    error.value = null;
    try {
        const data = await EvaluacionService.obtenerPorId(evaluacionId);
        evaluacionActual.value = data;
    } catch (e) {
        error.value = 'Error al cargar la evaluación.';
    } finally {
        loading.value = false;
    }
  }
  
  // Obtiene todas las evaluaciones de un evaluador.  
  async function fetchEvaluacionesPorEvaluador(rutEvaluador) {
    loading.value = true;
    error.value = null;
    try {
        const data = await EvaluacionService.obtenerPorEvaluador(rutEvaluador);
        evaluaciones.value = data;
    } catch (e) {
        error.value = 'Error al cargar las evaluaciones del evaluador.';
    } finally {
        loading.value = false;
    }
  }
  
  // Establece un mensaje de error en el estado.  
  function setError(message) {
    error.value = message;
  }

  // Devolvemos el estado y las acciones para que el componente pueda acceder a ellos
  return {
    evaluaciones,
    evaluacionActual,
    loading,
    error,
    iniciarEvaluacion,
    fetchEvaluacion,
    fetchEvaluacionesPorEvaluador,
    setError
  };
});