<template>
  <div class="form-container">
    <!-- Encabezado formulario -->
    <div class="form-header">
      <h2>Registro del Sistema de Agua Caliente Sanitaria</h2>
      <p>Complete los datos para registrar un nuevo sistema de agua caliente sanitaria.</p>
    </div>

    <!-- Mensaje de estado éxito o error -->
    <div v-if="message" :class="['status-message', { 'error-message': isError, 'success-message': !isError }]">
      {{ message }}
    </div>

    <!-- Formulario principal -->
    <form @submit.prevent="guardarSistemaAguaCalienteSanitaria" class="form-card">

      <!-- Campo para seleccionar la evaluación asociada -->
      <div class="form-group">
        <label for="evaluacion_id_evaluacion">Evaluación:</label>
        <select id="evaluacion_id_evaluacion" v-model="sistemaAguaCalienteSanitaria.evaluacion_id_evaluacion" required>
          <option value="" disabled>Seleccione una evaluación</option>
          <option v-for="evaluacion in evaluaciones" :key="evaluacion.id_evaluacion" :value="evaluacion.id_evaluacion">
            {{ evaluacion.nombre_evaluacion || evaluacion.id_evaluacion }}
          </option>
        </select>
      </div>

      <!-- Campo para seleccionar el tipo de distribución -->
      <div class="form-group">
        <label for="tipo_distribucion">Tipo de Distribución:</label>
        <select id="tipo_distribucion" v-model="sistemaAguaCalienteSanitaria.tipo_distribucion" required>
          <option value="" disabled>Seleccione un tipo</option>
          <option value="Vivienda con Sistema Individual">Vivienda con Sistema Individual</option>
          <option value="Viviendas con Sistema Centralizado">Viviendas con Sistema Centralizado</option>
        </select>
      </div>
      
      <!-- Campos condicionales que se muestran solo si se ha seleccionado un tipo de distribución -->
      <div v-if="sistemaAguaCalienteSanitaria.tipo_distribucion">
        <!-- Sistema de encendido -->
        <div class="form-group">
          <label for="sistema_encendido">Sistema de Encendido:</label>
          <select id="sistema_encendido" v-model="sistemaAguaCalienteSanitaria.sistema_encendido" required>
            <option value="" disabled>Seleccione</option>
            <option value="Manual">Manual</option>
            <option value="Automático">Automático</option>
          </select>
        </div>

        <!-- Corrección de distribución -->
        <div class="form-group" v-if="sistemaAguaCalienteSanitaria.tipo_distribucion === 'Viviendas con Sistema Centralizado'">
          <label for="correccion_distribucion">Corrección de Distribución:</label>
          <select id="correccion_distribucion" v-model="sistemaAguaCalienteSanitaria.correccion_distribucion" required>
            <option value="" disabled>Seleccione una corrección</option>
            <option value="Red de Cañerías sin Aislación">Red de Cañerías sin Aislación</option>
            <option value="Red de Cañerías con Aislación">Red de Cañerías con Aislación</option>
          </select>
        </div>

        <!-- ¿Tiene estanque? -->
        <div class="form-group">
          <label for="tiene_estanque">¿Tiene Estanque?:</label>
          <select id="tiene_estanque" v-model="sistemaAguaCalienteSanitaria.tiene_estanque" required>
            <option value="" disabled>Seleccione</option>
            <option value="SI">Sí</option>
            <option value="NO">No</option>
          </select>
        </div>

        <!-- Marca -->
        <div class="form-group">
          <label for="marca">Marca:</label>
          <input type="text" id="marca" v-model="sistemaAguaCalienteSanitaria.marca" maxlength="20" required/>
        </div>

        <!-- Modelo -->
        <div class="form-group">
          <label for="modelo">Modelo:</label>
          <input type="text" id="modelo" v-model="sistemaAguaCalienteSanitaria.modelo" maxlength="20" required/>
        </div>

        <!-- Potencia nominal ACS -->
        <div class="form-group">
          <label for="potencia_nominal_acs">Potencia Nominal ACS (kW):</label>
          <input type="number" id="potencia_nominal_acs" v-model.number="sistemaAguaCalienteSanitaria.potencia_nominal_acs" step="0.01" min="0" required />
        </div>
      </div>
      
      <div v-else class="info-message">
        <p>Seleccione un tipo de distribución para ver los campos adicionales.</p>
      </div>

      <!-- Botón para enviar el formulario -->
      <button type="submit">
        <i class='bx bx-plus-circle'></i> Registrar Sistema
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, defineEmits, onMounted, watch } from 'vue';
import { useSistemaAguaCalienteSanitariaStore } from '@/stores/SistemasTecnicos/useSistemaAguaCalienteSanitariaStore.js';
import { useEvaluacionStore } from '@/stores/Proceso/useEvaluacionStore.js';


// Define los eventos que el componente puede emitir
const emit = defineEmits(['registro-exitoso']);

// Importa las tiendas de Pinia para la gestión del estado
const sistemaAguaCalienteSanitariaStore = useSistemaAguaCalienteSanitariaStore();
const evaluacionStore = useEvaluacionStore();

// Define el estado reactivo para los datos del formulario
const sistemaAguaCalienteSanitaria = ref({
  tipo_distribucion: '',
  sistema_encendido: '',
  correccion_distribucion: '',
  tiene_estanque: '',
  marca: '',
  modelo: '',
  potencia_nominal_acs: null,
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

// Watcher que reacciona a los cambios en el tipo de distribución
watch(() => sistemaAguaCalienteSanitaria.value.tipo_distribucion, (newVal, oldVal) => {
  
  // Reseteo de los campos
  if (newVal !== oldVal) {
    sistemaAguaCalienteSanitaria.value.sistema_encendido = '';
    sistemaAguaCalienteSanitaria.value.correccion_distribucion = '';
    sistemaAguaCalienteSanitaria.value.tiene_estanque = '';
    sistemaAguaCalienteSanitaria.value.marca = '';
    sistemaAguaCalienteSanitaria.value.modelo = '';
    sistemaAguaCalienteSanitaria.value.potencia_nominal_acs = null;
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
const guardarSistemaAguaCalienteSanitaria = async () => {
  try {
    // Valida que el formulario solo se envíe si el tipo de distribución ha sido seleccionado
    if (!sistemaAguaCalienteSanitaria.value.tipo_distribucion) {
      showMessage('Por favor, seleccione un tipo de distribución.', true);
      return;
    }

    // Llama a la acción para crear el nuevo sistema en la tienda
    await sistemaAguaCalienteSanitariaStore.crearSistemaAguaCalienteSanitaria({ ...sistemaAguaCalienteSanitaria.value });
    showMessage('Sistema Agua Caliente Sanitarua registrado exitosamente.');
    emit('registro-exitoso');
    
    // Limpia el formulario después de un envío exitoso
    sistemaAguaCalienteSanitaria.value = {
      tipo_distribucion: '',
      sistema_encendido: '',
      correccion_distribucion: '',
      tiene_estanque: '',
      marca: '',
      modelo: '',
      potencia_nominal_acs: null,
      evaluacion_id_evaluacion: ''
    };

  } catch (error) {
    // Maneja errores y muestra una alerta al usuario
    showMessage('Error al registrar el sistema de Agua Caliente Sanitaria. Por favor, intente de nuevo.', true);
    console.error('Error al registrar el sistema Agua Caliente Sanitaria:', error);
  }
};
</script>

<style scoped>
/* Estilos del contenedor */
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

/* Estilos del cuerpo del formulario */
.form-card {
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

/* Estilo para el mensaje informativo */
.info-message {
  padding: 1rem; 
  background-color: #e0f2f1;
  color: #006666;
  border-radius: 4px;
  margin-bottom: 1.5rem;
}
</style>