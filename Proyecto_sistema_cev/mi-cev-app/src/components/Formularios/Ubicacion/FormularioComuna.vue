<template>
  <div class="formulario-comuna-container">
    <h3 class="formulario-title">Registrar Comuna</h3>

    <form @submit.prevent="submit">
      <div class="formulario-content">
        <div class="form-group">
          <label for="nombreComuna">Nombre de la Comuna:</label>
          <input id="nombreComuna" v-model="form.nombre_comuna" type="text" maxlength="45" placeholder="Ej: La Florida" required/>
        </div>
      </div>

      <div class="formulario-footer">
        
        <div v-if="error" class="status-message error">
          {{ error }}
        </div>
        <div v-if="successMessage" class="status-message success">
          {{ successMessage }}
        </div>
      </div>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref, onMounted, watch } from 'vue';
import { useRegionStore } from '@/stores/Ubicacion/useRegionStore';
import { useComunaStore } from '@/stores/Ubicacion/useComunaStore';
import { storeToRefs } from 'pinia';

// Definir los stores
const regionStore = useRegionStore();
const comunaStore = useComunaStore();

// Definir el estado del formulario
const form = reactive({
  nombre_comuna: '',
  region_id_region: null,
});

// Definir las propiedades reactivas para el estado de la vista
const loading = ref(false);
const error = ref(null);
const successMessage = ref(null);

// Obtener la lista de regiones desde el store para el dropdown
const { regiones } = storeToRefs(regionStore);

// Emitir evento para notificar al componente padre
const emit = defineEmits(['registro-exitoso']);

// Limpia los mensajes de feedback cuando el usuario empieza a interactuar
watch(
  () => [form.nombre_comuna, form.region_id_region],
  () => {
    error.value = null;
    successMessage.value = null;
  }
);

// Carga las regiones al montar el componente
onMounted(async () => {
  await regionStore.fetchRegiones();
});

const submit = async () => {
  error.value = null;
  successMessage.value = null;

  // Validaciones de los campos
  if (!form.nombre_comuna.trim()) {
    error.value = 'El nombre de la comuna es obligatorio.';
    return;
  }
  if (!form.region_id_region) {
    error.value = 'Debe seleccionar una región.';
    return;
  }

  loading.value = true;
  try {
    const payload = {
      nombre_comuna: form.nombre_comuna.trim(),
      region_id_region: form.region_id_region,
    };
    await comunaStore.crearComuna(payload);
    successMessage.value = 'Comuna registrada exitosamente.';
    form.nombre_comuna = '';
    form.region_id_region = null; // Limpiar el formulario
    emit('registro-exitoso'); // Notifica al componente padre
  } catch (err) {
    if (err.response && err.response.data && err.response.data.message) {
      error.value = err.response.data.message;
    } else {
      error.value = err.message || 'Error al registrar la comuna.';
    }
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.formulario-comuna-container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #f0f4f8;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

h3 {
  text-align: center;
  color: #006666;
  margin-bottom: 1.5rem;
}

.form-group {
  margin-bottom: 1.2rem;
}

label {
  display: block;
  font-weight: bold;
  margin-bottom: 0.5rem;
  color: #006666;
}

input,
select {
  width: 100%;
  padding: 10px;
  border: 1px solid #bdc3c7;
  border-radius: 6px;
  box-sizing: border-box;
  font-size: 1rem;
}

input:focus,
select:focus {
  border-color: #1870e4;
  outline: none;
  box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
}

button {
  width: 100%;
  padding: 12px;
  background-color: #1870e4;
  color: white;
  border: none;
  border-radius: 6px;
  font-size: 1.1rem;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover:not(:disabled) {
  background-color: #2980b9;
}

button:disabled {
  background-color: #95a5a6;
  cursor: not-allowed;
}

.status-message {
  text-align: center;
  padding: 1rem;
  border-radius: 8px;
  font-weight: bold;
}

.success {
  background-color: #eaf7ed;
  color: #27ae60;
  border: 1px solid #27ae60;
}

.error {
  background-color: #fbecec;
  color: #c0392b;
  border: 1px solid #c0392b;
}

.formulario-footer {
  text-align: center;
}

.formulario-comuna {
  padding: 2rem;
  background-color: #f0f4f8;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}
</style>