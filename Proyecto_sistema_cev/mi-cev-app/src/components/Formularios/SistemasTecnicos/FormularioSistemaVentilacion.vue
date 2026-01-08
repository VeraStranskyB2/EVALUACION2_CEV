<template>
  <div class="form-container">
    <!-- Encabezado del formulario -->
    <div class="form-header">
      <h2>Registro de Sistema de Ventilación</h2>
      <p>Complete los datos para registrar un nuevo sistema de ventilación.</p>
    </div>

    <!-- Mensaje de estado (éxito o error) -->
    <div v-if="message" :class="['status-message', { 'error-message': isError, 'success-message': !isError }]">
      {{ message }}
    </div>

    <!-- Formulario principal -->
    <form @submit.prevent="guardarSistemaVentilacion" class="form-sistemaventilacion">

      <!-- Campo para seleccionar la evaluación asociada -->
      <div class="form-group">
        <label for="evaluacion_id_evaluacion">Evaluación:</label>
        <select id="evaluacion_id_evaluacion" v-model="sistemaVentilacion.evaluacion_id_evaluacion" required>
          <option value="" disabled>Seleccione una evaluación</option>
          <option v-for="evaluacion in evaluaciones" :key="evaluacion.id_evaluacion" :value="evaluacion.id_evaluacion">
            {{ evaluacion.nombre_evaluacion || evaluacion.id_evaluacion }}
          </option>
        </select>
      </div>
      
      <!-- Campo para seleccionar el tipo de sistema de ventilación -->
      <div class="form-group">
        <label for="tipo_sistema_ventilacion">Tipo de Sistema de Ventilación:</label>
        <select id="tipo_sistema_ventilacion" v-model="sistemaVentilacion.tipo_sistema_ventilacion" required>
          <option value="" disabled>Seleccione un tipo</option>
          <option value="Ventilación mecánica">Ventilación mecánica</option>
          <option value="Ventilación natural">Ventilación natural</option>
          <option value="Ventilación hibrida">Ventilación hibrida</option>
          <option value="Ventilación forzada con recuperacion">Ventilación forzada con recuperacion</option>
        </select>
      </div>      

      <!-- Campos solo para sistemas de ventilación no naturales -->
      <div class="form-group" v-if="sistemaVentilacion.tipo_sistema_ventilacion !== 'Ventilación natural'">
        <label for="potencia_ventilador_sv">Potencia del Ventilador (W):</label>
        <input type="number" id="potencia_ventilador_sv" v-model.number="sistemaVentilacion.potencia_ventilador_sv" min="200" max="2000" required />
      </div>

      <!-- Campo específico para ventilación forzada con recuperación -->
      <div class="form-group" v-if="sistemaVentilacion.tipo_sistema_ventilacion === 'Ventilación forzada con recuperacion'">
        <label for="potencia_recuperador">Potencia del Recuperador (kW):</label>
        <input type="number" id="potencia_recuperador" v-model.number="sistemaVentilacion.potencia_recuperador" min="200" max="2000" step="0.01" required />
      </div>

      <!-- Potencia nominal y consumo de energía para sistemas no naturales -->
      <div class="form-group" v-if="sistemaVentilacion.tipo_sistema_ventilacion !== 'Ventilación natural'">
        <label for="potencia_nominal_sv">Potencia Nominal (kW):</label>
        <input type="number" id="potencia_nominal_sv" v-model.number="sistemaVentilacion.potencia_nominal_sv" min="600" max="1800" step="0.01" required />
      </div>
      
      <div class="form-group" v-if="sistemaVentilacion.tipo_sistema_ventilacion !== 'Ventilación natural'">
        <label for="consumo_energia">Consumo de Energía (kW):</label>
        <input type="number" id="consumo_energia" v-model.number="sistemaVentilacion.consumo_energia" min="400" max="2400" step="0.01" required />
      </div>

      <!-- Campos agregados para marca y modelo -->
      <div class="form-group" v-if="sistemaVentilacion.tipo_sistema_ventilacion !== 'Ventilación natural'">
        <label for="marca">Marca:</label>
        <input type="text" id="marca" v-model="sistemaVentilacion.marca" maxlength="20" required />
      </div>
      <div class="form-group" v-if="sistemaVentilacion.tipo_sistema_ventilacion !== 'Ventilación natural'">
        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" v-model="sistemaVentilacion.modelo" maxlength="20" required />
      </div>

      <!-- Mensaje para ventilación natural, indicando que no requiere más datos -->
      <div class="form-group" v-if="sistemaVentilacion.tipo_sistema_ventilacion === 'Ventilación natural'">
        <p class="nota">No se requieren más datos para el tipo "Ventilación natural".</p>
      </div>

      <!-- Botón para enviar el formulario -->
      <button type="submit">
        <i class='bx bx-plus-circle'></i> Registrar Sistema
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, defineEmits, watch, onMounted } from 'vue';
import { useSistemaVentilacionStore } from '@/stores/SistemasTecnicos/useSistemaVentilacionStore.js';
import { useEvaluacionStore } from '@/stores/Proceso/useEvaluacionStore.js';


// Define los eventos que el componente puede emitir
const emit = defineEmits(['registro-exitoso']);


// Importa las tiendas de Pinia para la gestión del estado
const ventilacionStore = useSistemaVentilacionStore();
const evaluacionStore = useEvaluacionStore();

// Define el estado reactivo para los datos del formulario
const sistemaVentilacion = ref({
  tipo_sistema_ventilacion: '',
  potencia_ventilador_sv: null,
  potencia_recuperador: null,
  potencia_nominal_sv: null,
  consumo_energia: null,
  descripcion_adicional: 'N/A',
  marca: '',
  modelo: '',
  evaluacion_id_evaluacion: ''
});

// Define un array reactivo para almacenar la lista de evaluaciones
const evaluaciones = ref([]);

// Hook del ciclo de vida que se ejecuta cuando el componente se monta
onMounted(async () => {
  // Llama a la acción para obtener las evaluaciones
  await evaluacionStore.fetchEvaluacion(); 
  // Asigna los datos obtenidos al estado local
  evaluaciones.value = evaluacionStore.evaluaciones;
});

// Watcher que reacciona a los cambios en el tipo de sistema de ventilación
watch(() => sistemaVentilacion.value.tipo_sistema_ventilacion, (newVal) => {
  if (newVal === 'Ventilación natural') {
    // Si es natural, limpia o establece valores predeterminados para los campos irrelevantes
    sistemaVentilacion.value.potencia_ventilador_sv = null;
    sistemaVentilacion.value.potencia_recuperador = null;
    sistemaVentilacion.value.potencia_nominal_sv = null;
    sistemaVentilacion.value.consumo_energia = null;
    sistemaVentilacion.value.descripcion_adicional = 'Ventilación natural';
    sistemaVentilacion.value.marca = 'N/A';
    sistemaVentilacion.value.modelo = 'N/A';
  } else {
    // Si no es natural, restablece los valores para que el usuario pueda ingresar datos
    sistemaVentilacion.value.descripcion_adicional = 'N/A';
    sistemaVentilacion.value.marca = '';
    sistemaVentilacion.value.modelo = '';
  }
});

// Función asíncrona para guardar los datos del formulario
const guardarSistemaVentilacion = async () => {
  try {
    // Crea un objeto con los datos a enviar
    const sistema = {
      tipo_sistema_ventilacion: sistemaVentilacion.value.tipo_sistema_ventilacion,
      potencia_ventilador_sv: sistemaVentilacion.value.potencia_ventilador_sv,
      potencia_recuperador: sistemaVentilacion.value.potencia_recuperador,
      potencia_nominal_sv: sistemaVentilacion.value.potencia_nominal_sv,
      consumo_energia: sistemaVentilacion.value.consumo_energia,
      descripcion_adicional: sistemaVentilacion.value.descripcion_adicional,
      marca: sistemaVentilacion.value.marca,
      modelo: sistemaVentilacion.value.modelo,
      evaluacion_id_evaluacion: sistemaVentilacion.value.evaluacion_id_evaluacion
    };

    // Llama a la acción para crear el nuevo sistema en la tienda
    await ventilacionStore.crearSistemaVentilacion(sistema);
    // Muestra una alerta de éxito
    alert('Sistema de ventilación registrado exitosamente.');
    // Emite un evento para notificar al componente padre
    emit('registro-exitoso');
    
    // Limpia el formulario después de un envío exitoso
    sistemaVentilacion.value = {
      tipo_sistema_ventilacion: '',
      potencia_ventilador_sv: null,
      potencia_recuperador: null,
      potencia_nominal_sv: null,
      consumo_energia: null,
      descripcion_adicional: 'N/A',
      marca: '',
      modelo: '',
      evaluacion_id_evaluacion: ''
    };

  } catch (error) {
    // Maneja errores y muestra una alerta al usuario
    alert('Error al registrar el sistema de ventilación. Por favor, intente de nuevo.');
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

/* Estilos del encabezado */
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

/* Estilos del cuerpo del formulario */
.form-sistemaventilacion {
  background-color: #f8f9fa;
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid #006666;
  box-shadow: 0 12px 12px #006666;
  margin-bottom: 2rem;
}

/* Estilos para cada grupo de campos */
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

/* Estilo para el mensaje de nota */
.nota {
  background-color: #e0f2f1;
  border-left: 4px solid #006666;
  padding: 1rem;
  border-radius: 4px;
  color: #006666;
  font-style: italic;
  margin-top: 1rem;
}
</style>
