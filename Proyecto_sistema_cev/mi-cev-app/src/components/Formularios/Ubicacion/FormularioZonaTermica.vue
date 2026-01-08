<template>
  <div class="formulario-zona-termica">
    <h3>Registrar Zona Térmica</h3>
    <form @submit.prevent="submit">
      <div class="form-group">
        <label for="idZonaTermica">ID Zona Térmica</label>
        <input id="idZonaTermica" v-model="form.id_zona_termica" type="text" maxlength="12" placeholder="Ej: A, B, C..." required/>
      </div>

      <div class="form-group">
        <label for="tipoZonaTermica">Tipo de Zona Térmica</label>
        <input id="tipoZonaTermica" v-model="form.tipo_zona_termica" type="text" maxlength="45" placeholder="Ej: Zonas desérticas" required/>
      </div>

      <div class="form-group">
        <label for="temperatura">Temperatura (°C)</label>
        <input id="temperatura" v-model.number="form.temperatura" type="number" step="0.01" placeholder="Ej: 25.50" required/>
      </div>

     
    </form>
  </div>
</template>

<script setup>
import { reactive, ref, watch } from 'vue';
import { useZonaTermicaStore } from '@/stores/Ubicacion/useZonaTermicaStore';

const form = reactive({
  id_zona_termica: '',
  tipo_zona_termica: '',
  temperatura: null,
});

const loading = ref(false);
const error = ref(null);
const successMessage = ref(null);

// Asume que este store ya ha sido creado
const zonaTermicaStore = useZonaTermicaStore();

// Limpia los mensajes de feedback cuando el usuario empieza a editar
watch(
  () => [form.id_zona_termica, form.tipo_zona_termica, form.temperatura],
  () => {
    error.value = null;
    successMessage.value = null;
  }
);

async function submit() {
  error.value = null;
  successMessage.value = null;

  // Validación básica del formulario
  if (
    !form.id_zona_termica.trim() ||
    !form.tipo_zona_termica.trim() ||
    form.temperatura === null
  ) {
    error.value = 'Todos los campos son obligatorios.';
    return;
  }

  loading.value = true;
  try {
    await zonaTermicaStore.crearZonaTermica(form);
    successMessage.value = 'Zona térmica registrada exitosamente.';
    // Restablecer el formulario
    form.id_zona_termica = '';
    form.tipo_zona_termica = '';
    form.temperatura = null;
  } catch (err) {
    if (err.response && err.response.status === 409) {
      error.value = 'Ya existe una zona térmica con ese ID.';
    } else {
      error.value = err.message || 'Error registrando la zona térmica.';
    }
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
.formulario-zona-termica {
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

input[type='text'],
input[type='number'],
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
</style>