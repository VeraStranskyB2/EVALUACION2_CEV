<template>
  <div class="formulario-region">
    <h3>Registrar Región</h3>
    <form @submit.prevent="submit">
      <div class="form-group">
        <label for="nombreRegion">Nombre de la Región</label>
        <input id="nombreRegion" v-model="form.nombre_region" type="text" maxlength="45" placeholder="Nombre de la región" required/>
      </div>

     
    </form>
  </div>
</template>

<script setup>
import { reactive, ref, watch } from 'vue';
import { useRegionStore } from '@/stores/Ubicacion/useRegionStore';

const form = reactive({
  nombre_region: '',
});

const loading = ref(false);
const error = ref(null);
const successMessage = ref(null);

const regionStore = useRegionStore();

// Limpia los mensajes de feedback cuando el usuario empieza a escribir
watch(() => form.nombre_region, () => {
    error.value = null;
    successMessage.value = null;
  }
);

async function submit() {
  error.value = null;
  successMessage.value = null;

  // Validación básica del campo
  if (!form.nombre_region.trim()) {
    error.value = 'El nombre de la región es obligatorio.';
    return;
  }

  loading.value = true;
  try {
    await regionStore.crearRegion({ nombre_region: form.nombre_region.trim() });
    successMessage.value = 'Región registrada exitosamente.';
    form.nombre_region = ''; // Limpia el formulario
  } catch (err) {
    if (err.response && err.response.status === 409) {
      error.value = 'Ya existe una región con ese nombre.';
    } else {
      error.value = err.message || 'Error registrando la región.';
    }
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
.formulario-region-container {
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

.formulario-region {
  padding: 2rem;
  background-color: #f0f4f8;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}
</style>