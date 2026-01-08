<template>
  <div class="formulario-container">
    <div class="header-container">
      <h2 class="section-title">Hacer Evaluación</h2>
      <p class="section-subtitle">Seleccione el proyecto a evaluar para comenzar la evaluación técnica.</p>
    </div>

    <form @submit.prevent="iniciarEvaluacion" class="form-evaluacion">
      <div class="form-group">
        <label for="evaluadorRut">Rut del Evaluador:</label>
        <input type="text" id="evaluadorRut" :value="evaluadorRut" readonly class="readonly-input" />
      </div>
      <div class="form-group">
        <label>Fecha de Evaluación:</label>
        <div class="fecha-group">
          <input type="text" v-model="fecha.dia" placeholder="Día" maxlength="2" required />
          <span>-</span>
          <input type="text" v-model="fecha.mes" placeholder="Mes" maxlength="2" required />
          <span>-</span>
          <input type="text" v-model="fecha.anio" placeholder="Año" maxlength="4" required />
        </div>
      </div>
      <div class="form-group">
        <label for="proyectoId">Seleccionar Proyecto:</label>
        <select id="proyectoId" v-model="proyectoSeleccionadoId" required>
          <option value="" disabled>Seleccione un proyecto</option>
          <option v-for="proyecto in proyectos" :key="proyecto.id_proyecto" :value="proyecto.id_proyecto">
            {{ proyecto.nombre_proyecto }} ({{ proyecto.id_proyecto }})
          </option>
        </select>
      </div>
      <button type="submit" :disabled="loading">
        <span v-if="loading">Iniciando...</span>
        <span v-else>Iniciar Evaluación</span>
      </button>
    </form>
    
    <div v-if="loading" class="loading-message">
      <p>Cargando proyectos...</p>
    </div>
    <div v-if="error" class="error-message">
      {{ error }}
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useEvaluacionStore } from '@/stores/Proceso/useEvaluacionStore';
import { useAuthStore } from '@/stores/Usuarios/useAuthStore';
import EvaluacionService from '@/services/Proceso/EvaluacionService';

const evaluacionStore = useEvaluacionStore();
const authStore = useAuthStore();
const router = useRouter();

const proyectos = ref([]);
const loading = ref(false);
const error = ref(null);

const evaluadorRut = computed(() => authStore.rut_usuario);
const proyectoSeleccionadoId = ref('');
const fecha = ref({
  dia: '',
  mes: '',
  anio: ''
});

onMounted(async () => {
  loading.value = true;
  error.value = null;
  try {
    const response = await EvaluacionService.obtenerProyectos();
    proyectos.value = response.data;
  } catch (e) {
    error.value = 'No se pudieron cargar los proyectos. Intente de nuevo más tarde.';
  } finally {
    loading.value = false;
  }
});

const iniciarEvaluacion = async () => {
  const fechaCompleta = `${fecha.value.anio}-${fecha.value.mes}-${fecha.value.dia}`;
  const fechaValida = !isNaN(new Date(fechaCompleta));
  if (!proyectoSeleccionadoId.value || !fechaValida) {
    evaluacionStore.setError('Por favor, seleccione un proyecto y asegúrese de que la fecha sea válida.');
    return;
  }
  evaluacionStore.setError(null);
  const evaluacionData = {
    proyecto_id_proyecto: proyectoSeleccionadoId.value,
    evaluador_rut_usuario: evaluadorRut.value,
    fecha_evaluacion: fechaCompleta
  };
  const nuevaEvaluacionId = await evaluacionStore.iniciarEvaluacion(evaluacionData);
  if (nuevaEvaluacionId) {
    alert('Evaluación iniciada con éxito. ID: ' + nuevaEvaluacionId);
    router.push({ name: 'evaluador-sistema-calefaccion', params: { id_evaluacion: nuevaEvaluacionId } });
  }
};
</script>

<style scoped>
.formulario-container {
  padding-top: 0;
}
.header-container {
  text-align: center;
  margin-bottom: 2rem;
}
.section-title {
  color: #006666;
  font-size: 2rem;
  padding-bottom: 1rem;
  border-bottom: 5px solid #006666;
  
}
.section-subtitle {
  margin-top: 1rem;
  color: #006666;
}
.form-evaluacion {
  background-color: #f8f9fa;
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid #006666; 
  box-shadow: 0 12px 12px #006666;
  margin-bottom: 2rem;
}
.form-group {
  margin-bottom: 1.5rem;
  padding: 8px 0px;
}
label {
  display: block;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #006666;
}
.fecha-group {
  display: flex;
  align-items: center;
  gap: 10px;
}
.fecha-group input[type="text"] {
  width: auto;
  flex-grow: 1;
}
.fecha-group span {
  font-size: 1.2rem;
  font-weight: bold;
  color: #006666;
}
input[type="text"], select {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
.readonly-input {
  background-color: #e9ecef;
  cursor: not-allowed;
}
button {
  width: 100%;
  padding: 1rem;
  background-color: #006666;
  color: white;
  font-size: 1.1rem;
  font-weight: bold;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}
button:hover {
  background-color: #1870e4;
}
button:disabled {
  background-color: #a0a0a0;
  cursor: not-allowed;
}
.error-message, .loading-message {
  text-align: center;
  margin-top: 1rem;
  padding: 1rem;
  border-radius: 4px;
}
.error-message {
  color: #d9534f;
  background-color: #f2dede;
  border: 1px solid #ebccd1;
}
.loading-message {
  color: #006666;
  font-style: italic;
  background-color: #e6f7ff;
}
</style>