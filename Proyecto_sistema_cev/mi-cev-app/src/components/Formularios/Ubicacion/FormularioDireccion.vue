<template>
  <div class="formulario-direccion">
    <h3>Ingresar Dirección</h3>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="calle">Calle:</label>
        <input id="calle" v-model="form.calle" type="text" maxlength="100" required/>
      </div>

      <div class="form-group">
        <label for="numero">Número:</label>
        <input id="numero" v-model.number="form.numero" type="number" />
      </div>

      <div class="form-group">
        <label for="complemento">Complemento:</label>
        <input id="complemento" v-model="form.complemento" type="text" maxlength="45"/>
      </div>

      <div class="form-group">
        <label for="latitud">Latitud:</label>
        <input id="latitud" v-model.number="form.latitud" step="0.00000001" type="number"/>
      </div>

      <div class="form-group">
        <label for="longitud">Longitud:</label>
        <input id="longitud" v-model.number="form.longitud" step="0.00000001" type="number"/>
      </div>

      
    </form>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, watch } from 'vue';

// Define las variables de estado para el formulario
const form = reactive({
  calle: '',
  numero: null,
  complemento: '',
  latitud: null,
  longitud: null,
  comuna_id_comuna: null,
  zona_termica_id_zona_termica: '',
});

// Define las listas para los select
const comunas = ref([]);
const zonas = ref([]);

// Variables de estado para la interfaz de usuario
const loading = ref(false);
const error = ref(null);
const successMessage = ref(null);


</script>

<style scoped>
.formulario-direccion {
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

.error-message {
  color: #c0392b;
  background-color: #fbecec;
  border: 1px solid #c0392b;
  padding: 10px;
  border-radius: 6px;
  margin-top: 1rem;
  text-align: center;
}

.success-message {
  color: #27ae60;
  background-color: #eaf7ed;
  border: 1px solid #27ae60;
  padding: 10px;
  border-radius: 6px;
  margin-top: 1rem;
  text-align: center;
}
</style>