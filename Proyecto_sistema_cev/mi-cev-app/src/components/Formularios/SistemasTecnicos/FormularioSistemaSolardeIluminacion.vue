<template>
  <div class="form-container">
    <!-- Encabezado formulario -->
    <div class="form-header">
      <h2>Registro de Sistema Solar de Iluminación</h2>
      <p>Complete los campos para registrar los datos del sistema de iluminación solar.</p>
    </div>

    <!-- Mensaje de estado éxito o error -->
    <div v-if="message" :class="['status-message', { 'error-message': isError, 'success-message': !isError }]">
      {{ message }}
    </div>

    <!-- Formulario principal -->
    <form @submit.prevent="guardarSistemaSolardeIluminacion" class="form-sistemasolardeiluminacion">

      <!-- Campo para seleccionar la evaluación asociada -->
      <div class="form-group">
        <label for="evaluacion_id_evaluacion">Evaluación:</label>
        <select id="evaluacion_id_evaluacion" v-model="sistemaSolardeIluminacion.evaluacion_id_evaluacion" required>
          <option value="" disabled>Seleccione una evaluación</option>
          <option v-for="evaluacion in evaluaciones" :key="evaluacion.id_evaluacion" :value="evaluacion.id_evaluacion">
            {{ 'Evaluación: ' + evaluacion.id_evaluacion }}
          </option>
        </select>
      </div>

      <!-- Campo para seleccionar el tipo de distribución -->  
      <div class="form-group">
        <label for="tipo_distribucion_iluminacion">Tipo de Distribución de Iluminación:</label>
        <select id="tipo_distribucion_iluminacion" v-model="sistemaSolardeIluminacion.tipo_distribucion_iluminacion" required>
          <option value="" disabled>Seleccione un tipo</option>
          <option value="Vivienda con Sistema Individual">Vivienda con Sistema Individual</option>
          <option value="Viviendas con Sistema Colectivo">Viviendas con Sistema Colectivo</option>
        </select>
      </div>
      
      <!-- Campo para seleccionar el numero de Paneles -->
      <div class="form-group">
        <label for="numero_paneles">Número de Paneles:</label>
        <input type="number" id="numero_paneles" v-model.number="sistemaSolardeIluminacion.numero_paneles" min="0" required />
      </div>

      <!-- Marca -->  
      <div class="form-group">
        <label for="marca">Marca:</label>
        <input type="text" id="marca" v-model="sistemaSolardeIluminacion.marca" maxlength="20" required />
      </div>

      <!-- Modelo -->  
      <div class="form-group">
        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" v-model="sistemaSolardeIluminacion.modelo" maxlength="20" required />
      </div>

      <!-- Superficie Total Neta -->
      <div class="form-group">
        <label for="superficie_total_ssi">Superficie Total Neta (m2):</label>
        <input type="number" id="superficie_total_ssi" v-model.number="sistemaSolardeIluminacion.superficie_total_ssi" step="0.01" min="0" required />
      </div>

      <!-- Botón para enviar el formulario -->  
      <button type="submit">
        <i class='bx bx-plus-circle'></i> Registrar Sistema Solar
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, defineEmits, onMounted } from 'vue';
import { useEvaluacionStore } from '@/stores/Proceso/useEvaluacionStore.js';
import { useSistemaSolardeIluminacionStore } from '@/stores/SistemasTecnicos/useSistemaSolardeIluminacionStore.js';


// Define los eventos que el componente puede emitir
const emit = defineEmits(['registro-exitoso']);

// Importa la tienda de Pinia para la gestión del estado de evaluaciones
const sistemaSolardeIluminacionStore = useSistemaSolardeIluminacionStore();
const evaluacionStore = useEvaluacionStore();

// Define el estado reactivo para los datos del formulario
const sistemaSolardeIluminacion = ref({
  tipo_distribucion_iluminacion: '',
  numero_paneles: null,
  marca: '',
  modelo: '',
  superficie_total_ssi: null,
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

// Función para mostrar mensajes de estado
const showMessage = (text, isErrorMessage = false) => {
  message.value = text;
  isError.value = isErrorMessage;
  setTimeout(() => {
    message.value = '';
  }, 4000);
};

// Función asíncrona para guardar los datos del formulario
const guardarSistemaSolardeIluminacion = async () => {
  try {
    // Crea un objeto con los datos a enviar
    const datosSistemaSolardeIluminacion = {
      tipo_distribucion_iluminacion: sistemaSolardeIluminacion.value.tipo_distribucion_iluminacion,
      numero_paneles: sistemaSolardeIluminacion.value.numero_paneles,
      marca: sistemaSolardeIluminacion.value.marca,
      modelo: sistemaSolardeIluminacion.value.modelo,
      superficie_total_ssi: sistemaSolardeIluminacion.value.superficie_total_ssi,
      evaluacion_id_evaluacion: sistemaSolardeIluminacion.value.evaluacion_id_evaluacion
    };
    
    // Llama a la acción para crear el nuevo sistema en la tienda
    // await sistemaSolardeIluminacionStore.crearSistemaSolarIluminacion(datosSistemaSolarIluminacion); // Descomenta esta línea al crear el store
    showMessage('Sistema Solar de Iluminación registrado exitosamente.');
    emit('registro-exitoso');

    // Limpia el formulario después de un envío exitoso
    sistemaSolardeIluminacion.value = {
      tipo_distribucion_iluminacion: '',
      numero_paneles: null,
      marca: '',
      modelo: '',
      superficie_total_ssi: null,
      evaluacion_id_evaluacion: ''
    };

  } catch (error) {
    // Maneja errores y muestra un mensaje al usuario
    showMessage('Error al registrar el Sistema Solar de Iluminación. Por favor, intente de nuevo.', true);
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

/* Estilos del encabezado del formulario */
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
.form-sistemasolardeiluminacion {
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