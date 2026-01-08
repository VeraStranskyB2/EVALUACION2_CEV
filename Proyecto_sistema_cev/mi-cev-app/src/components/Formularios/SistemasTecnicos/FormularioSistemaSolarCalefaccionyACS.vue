<template>
  <div class="form-container">
    <!-- Encabezado formulario -->
    <div class="form-header">
      <h2>Registro de Sistema Solar de Calefacción y ACS</h2>
      <p>Complete los campos para registrar los datos del sistema solar de Calefacción y Agua Caliente Sanitaria.</p>
    </div>

    <!-- Mensaje de estado (éxito o error) -->
    <div v-if="message" :class="['status-message', { 'error-message': isError, 'success-message': !isError }]">
      {{ message }}
    </div>

    <!-- Formulario principal -->
    <form @submit.prevent="guardarSistemaSolarCalefaccion" class="form-sistemasolarcalefaccion">

      <!-- Campo para seleccionar la evaluación asociada -->
      <div class="form-group">
        <label for="evaluacion_id_evaluacion">Evaluación:</label>
        <select id="evaluacion_id_evaluacion" v-model="sistemaSolarCalefaccion.evaluacion_id_evaluacion" required>
          <option value="" disabled>Seleccione una evaluación</option>
          <option v-for="evaluacion in evaluaciones" :key="evaluacion.id_evaluacion" :value="evaluacion.id_evaluacion">
            {{ evaluacion.nombre_evaluacion || evaluacion.id_evaluacion }}
          </option>
        </select>
      </div>

      <!-- Campo para el tipo de distribución solar de Calefaccion y ACS-->
      <div class="form-group">
        <label for="tipo_distribucion_solar">Tipo de Distribución Solar de Calefacción:</label>
        <select id="tipo_distribucion_solar" v-model="sistemaSolarCalefaccion.tipo_distribucion_solar" required>
          <option value="" disabled>Seleccione un tipo</option>
          <option value="Vivienda con Sistema Individual">Vivienda con Sistema Individual</option>
          <option value="Viviendas con Sistema Colectivo">Viviendas con Sistema Colectivo</option>
        </select>
      </div>
      
      <!-- Campo para el sistema de aporte -->
      <div class="form-group">
        <label for="sistema_aporte">Sistema de Aporte:</label>
        <select id="sistema_aporte" v-model="sistemaSolarCalefaccion.sistema_aporte" required>
          <option value="" disabled>Seleccione un sistema</option>
          <option value="Sistema de Calefacción">Sistema de Calefacción</option>
          <option value="Sistema de ACS">Sistema de ACS</option>
        </select>
      </div>

      <!-- Campo para el tipo de colector -->
      <div class="form-group">
        <label for="tipo_colector">Tipo de Colector:</label>
        <select id="tipo_colector" v-model="sistemaSolarCalefaccion.tipo_colector" required>
          <option value="" disabled>Seleccione un colector</option>
          <option value="Colector Plano">Colector Plano</option>
          <option value="Colector de Tubos al Vacío">Colector de Tubos al Vacío</option>
        </select>
      </div>

      <!-- Campo condicional para Colector Plano -->
      <div v-if="sistemaSolarCalefaccion.tipo_colector === 'Colector Plano'" class="form-group">
        <label for="cubierta_semi_transparente">¿Tiene cubierta semi transparente?</label>
        <select id="cubierta_semi_transparente" v-model="sistemaSolarCalefaccion.cubierta_semi_transparente" required>
          <option value="" disabled>Seleccione</option>
          <option value="Sí">Sí</option>
          <option value="No">No</option>
        </select>
      </div>

      <!-- Campo para la marca -->
      <div class="form-group">
        <label for="marca">Marca:</label>
        <input type="text" id="marca" v-model="sistemaSolarCalefaccion.marca" maxlength="20" required />
      </div>

      <!-- Campo para el modelo -->
      <div class="form-group">
        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" v-model="sistemaSolarCalefaccion.modelo" maxlength="20" required />
      </div>

      <!-- Campo para la superficie total -->
      <div class="form-group">
        <label for="superficie_total_ssc">Superficie Total (m2):</label>
        <input type="number" id="superficie_total_ssc" v-model.number="sistemaSolarCalefaccion.superficie_total_ssc" step="0.01" min="0" required />
      </div>

      <!-- Botón para enviar el formulario -->
      <button type="submit">
        <i class='bx bx-plus-circle'></i> Registrar Sistema Solar
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, defineEmits, onMounted, watch } from 'vue';
import { useEvaluacionStore } from '@/stores/Proceso/useEvaluacionStore.js';
import { useSistemaSolarCalefaccionyACSStore } from '@/stores/SistemasTecnicos/useSistemaSolarCalefaccionyACSStore.js';

// Define los eventos que el componente puede emitir
const emit = defineEmits(['registro-exitoso']);

// Importa las tiendas de Pinia para la gestión del estado
const sistemaSolarCalefaccionyACSStore = useSistemaSolarCalefaccionyACSStore();
const evaluacionStore = useEvaluacionStore();

// Define el estado reactivo para los datos del formulario
const sistemaSolarCalefaccion = ref({
  tipo_distribucion_solar: '',
  sistema_aporte: '',
  tipo_colector: '',
  cubierta_semi_transparente: '',
  marca: '',
  modelo: '',
  superficie_total_ssc: null,
  evaluacion_id_evaluacion: ''
});

// Define un array reactivo para almacenar la lista de evaluaciones
const evaluaciones = ref([]);
const message = ref('');
const isError = ref(false);

// Hook del ciclo de vida que se ejecuta cuando el componente se monta
onMounted(async () => {
  try {
    // Llama a la acción para obtener las evaluaciones
    await evaluacionStore.fetchEvaluacion(); 
    // Asigna los datos obtenidos al estado local
    evaluaciones.value = evaluacionStore.evaluaciones;
  } catch (error) {
    showMessage('Error al cargar la lista de evaluaciones.', true);
    console.error('Error al cargar evaluaciones:', error);
  }
});

// Watcher que reacciona a los cambios en el tipo de colector para resetear el campo condicional
watch(() => sistemaSolarCalefaccion.value.tipo_colector, () => {
  sistemaSolarCalefaccion.value.cubierta_semi_transparente = '';
});

// Función para mostrar mensajes de estado
const showMessage = (text, isErrorMessage = false) => {
  message.value = text;
  isError.value = isErrorMessage;
  setTimeout(() => {
    message.value = '';
  }, 4000);
};

// Función asíncrona para guardar los datos del formulario
const guardarSistemaSolarCalefaccion = async () => {
  try {
    // Crea un objeto con los datos a enviar
    const datosSistemaSolarCalefaccion = {
      tipo_distribucion_solar: sistemaSolar.value.tipo_distribucion_solar,
      sistema_aporte: sistemaSolar.value.sistema_aporte,
      tipo_colector: sistemaSolar.value.tipo_colector,
      cubierta_semi_transparente: sistemaSolar.value.cubierta_semi_transparente,
      marca: sistemaSolar.value.marca,
      modelo: sistemaSolar.value.modelo,
      superficie_total_ssc: sistemaSolar.value.superficie_total_ssc,
      evaluacion_id_evaluacion: sistemaSolar.value.evaluacion_id_evaluacion
    };
    
    // Llama a la acción para crear el nuevo sistema en la tienda
    await sistemaSolarCalefaccionyACSStore.crearSistemaSolarCalefaccion(datosSistemaSolar);
    showMessage('Sistema Solar registrado exitosamente.');
    emit('registro-exitoso');

    // Limpia el formulario después de un envío exitoso
    sistemaSolarCalefaccion.value = {
      tipo_distribucion_solar: '',
      sistema_aporte: '',
      tipo_colector: '',
      cubierta_semi_transparente: '',
      marca: '',
      modelo: '',
      superficie_total_ssc: null,
      evaluacion_id_evaluacion: ''
    };

  } catch (error) {
    // Maneja errores y muestra un mensaje al usuario
    showMessage('Error al registrar el Sistema Solar. Por favor, intente de nuevo.', true);
    console.error('Error al registrar el sistema:', error);
  }
};
</script>

<style scoped>
/* Estilos del contenedor*/
.form-container {
  padding-top: 0;
  max-width: 900px;
  margin: auto;
}

/* Estilos del encabezado*/
.form-header {
  background-color: #006666;
  color: white;
  padding: 1.5rem 2rem;
  border-radius: 8px 8px 0 0;
  text-align: center;
  border-bottom: 2px solid #006666;
  margin-bottom: 2rem;
}

.form-header h2 {
  color: white;
  font-size: 2rem;
  margin: 0;
}

.form-header p {
  font-size: 1rem;
  margin-top: 0.5rem;
}

/* Estilo para los mensajes de estado */
.status-message {
  padding: 1rem;
  border-radius: 4px;
  margin-bottom: 1.5rem;
  text-align: center;
  font-weight: bold;
}

.success-message {
  background-color: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}

.error-message {
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
}

/* Estilos del cuerpo del formulario*/
.form-sistemasolar {
  background-color: #f8f9fa;
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid #006666;
  box-shadow: 0 12px 12px #006666;
  margin-bottom: 2rem;
}

/* Estilos para cada grupo de campos*/
.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #006666;
}

/* Estilos para todos los elementos de input y select */
input,
select {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #555;
  background-color: white;
}

/* Estilos de foco para los elementos de input y select */
input:focus,
select:focus {
  outline: none;
  border-color: #006666;
  box-shadow: 0 0 0 2px rgba(0, 102, 102, 0.2);
}

/* Estilos del botón de envío */
button[type="submit"] {
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
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
}

/* Efecto hover para el botón de envío */
button[type="submit"]:hover {
  background-color: #1870e4;
}
</style>