<template>
  <div class="form-container">
    <!-- Encabezado del formulario -->
    <div class="form-header">
      <h2>Registro de Sistema de Calefacción</h2>
      <p>Complete los campos para registrar los datos del sistema de calefacción.</p>
    </div>

    <!-- Mensaje de estado éxito o error -->
    <div v-if="message" :class="['status-message', { 'error-message': isError, 'success-message': !isError }]">
      {{ message }}
    </div>

    <!-- Formulario principal con el evento de envío -->
    <form @submit.prevent="guardarSistemaCalefaccion" class="form-sistemacalefaccion">

      <!-- Campo para seleccionar la evaluación asociada -->
      <div class="form-group">
        <label for="evaluacion_id_evaluacion">Evaluación:</label>
        <select id="evaluacion_id_evaluacion" v-model="sistemaCalefaccion.evaluacion_id_evaluacion" required>
          <option value="" disabled>Seleccione una evaluación</option>
          <option v-for="evaluacion in evaluaciones" :key="evaluacion.id_evaluacion" :value="evaluacion.id_evaluacion">
            {{ evaluacion.nombre_evaluacion || evaluacion.id_evaluacion }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label for="tipo_distribucion">Tipo de Distribución:</label>
        <select id="tipo_distribucion" v-model="sistemaCalefaccion.tipo_distribucion" required>
          <option value="" disabled>Seleccione un tipo</option>
          <option value="Sistema Unitario Autocontenido">Sistema Unitario Autocontenido</option>
          <option value="Vivienda Unifamiliar con Sistema Centralizado">Vivienda Unifamiliar con Sistema Centralizado</option>
          <option value="Edificio con Sistema Centralizado">Edificio con Sistema Centralizado</option>
        </select>
      </div>

      <div class="form-group">
        <label for="sistema_control">Sistema de Control:</label>
        <select id="sistema_control" v-model="sistemaCalefaccion.sistema_control" required>
          <option value="" disabled>Seleccione un sistema</option>
          <option value="Control Automático (T°)">Control Automático (T°)</option>
          <option value="Control Manual (T°)">Control Manual (T°)</option>
        </select>
      </div>

      <div class="form-group">
        <label for="marca">Marca:</label>
        <input type="text" id="marca" v-model="sistemaCalefaccion.marca" required />
      </div>

      <div class="form-group">
        <label for="modelo">Modelo:</label>
        <input type="text" id="modelo" v-model="sistemaCalefaccion.modelo" required />
      </div>

      <div class="form-group">
        <label for="potencia_nominal_sc">Potencia Nominal (kW):</label>
        <input type="number" id="potencia_nominal_sc" v-model.number="sistemaCalefaccion.potencia_nominal_sc" step="0.01" required />
      </div>

      <button type="submit">
        <i class='bx bx-plus-circle'></i> Guardar Sistema
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, defineEmits, onMounted } from 'vue';
import { useSistemaCalefaccionStore } from '@/stores/SistemasTecnicos/useSistemaCalefaccionStore.js';
import { useEvaluacionStore } from '@/stores/Proceso/useEvaluacionStore.js';

// Define los eventos que el componente puede emitir
const emit = defineEmits(['registro-exitoso']);

// Importa las tiendas de Pinia para la gestión del estado
const sistemaCalefaccionStore = useSistemaCalefaccionStore();
const evaluacionStore = useEvaluacionStore();

const sistemaCalefaccion = ref({
  tipo_distribucion: '',
  sistema_control: '',
  marca: '',
  modelo: '',
  potencia_nominal_sc: null,
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


// Función asíncrona para guardar los datos del formulario
const guardarSistemaCalefaccion = async () => {
  try {
    // Crea un objeto con los datos a enviar
    const sistemaCalefaccion = {
      tipo_distribucion: sistemaCalefaccion.value.tipo_distribucion,
      sistema_control: sistemaCalefaccion.value.sistema_control,      
      marca: sistemaCalefaccion.value.marca,
      modelo: sistemaCalefaccion.value.modelo,
      potencia_nominal_sc: sistemaCalefaccion.value.potencia_nominal_sv,
      evaluacion_id_evaluacion: sistemaCalefaccion.value.evaluacion_id_evaluacion
    };

    // Llama a la acción para crear el nuevo sistema en la tienda
    await sistemaCalefaccionStore.crearSistemaCalefaccion(sistema);
    // Muestra una alerta de éxito
    alert('Sistema de Calefacción registrado exitosamente.');
    // Emite un evento para notificar al componente padre
    emit('registro-exitoso');

  
    // Aquí se enviaría el objeto `sistemaCalefaccion.value` a un servicio o store.
    // Limpia el formulario después del envío
    sistemaCalefaccion.value = {
      tipo_distribucion: '',
      sistema_control: '',
      marca: '',
      modelo: '',
      potencia_nominal_sc: null,
    };

  }
   catch (error) {
    // Maneja errores y muestra una alerta al usuario
    alert('Error al registrar el Sistema de Calefacción. Por favor, intente de nuevo.');
    console.error('Error al registrar el sistema:', error);
  }

};
</script>

<style scoped>
.form-container {
  padding-top: 0;
  max-width: 900px;
  margin: auto;
}

.form-header {
  background-color: #006666;
  color: white;
  padding: 1.5rem 2rem;
  border-radius: 8px 8px 0 0;
  text-align: center;
  border-bottom: 2px solid #006666;
  margin-bottom: 2rem;
}

h2 {
  color: white;
  font-size: 2rem;
  margin: 0;
}

p {
  font-size: 1rem;
  margin-top: 0.5rem;
}

.form-sistemacalefaccion {
  background-color: #f8f9fa;
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid #006666;
  box-shadow: 0 12px 12px #006666;
  margin-bottom: 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #006666;
}

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

input:focus,
select:focus {
  outline: none;
  border-color: #006666;
  box-shadow: 0 0 0 2px rgba(0, 102, 102, 0.2);
}

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

button[type="submit"]:hover {
  background-color: #1870e4;
}
</style>
