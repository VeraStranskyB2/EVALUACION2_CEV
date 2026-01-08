<template>
  <div class="form-proyecto-container">
    <div class="form-proyecto-header">
      <h2>Registrar Proyecto</h2>
      <p>Complete los campos para registrar un nuevo proyecto.</p>
    </div>

    <form class="form-proyecto" @submit.prevent="submit" novalidate>
      <div class="form-group">
        <label for="rolMatriz">Rol Matriz</label>
        <div class="rol-matriz-group">
          <input id="rolMatrizParte1" v-model="formproyecto.rol_matriz_parte1" type="text" maxlength="5" placeholder="00000" required />
          <span>-</span>
          <input id="rolMatrizParte2" v-model="formproyecto.rol_matriz_parte2" type="text" maxlength="5" placeholder="00000" required />
        </div>
      </div>

      <div class="form-group">
        <label for="nombreProyecto">Nombre Proyecto</label>
        <input id="nombreProyecto" v-model="formproyecto.nombre_proyecto" type="text" placeholder="Nombre Proyecto" required />
      </div>

      <div class="form-group">
        <label for="numeroViviendas">Número de Viviendas</label>
        <input id="numeroViviendas" v-model.number="formproyecto.numero_viviendas" type="number" placeholder="Número de Viviendas" />
      </div>

      <div class="form-group">
        <label for="zonaTermica">Zona Térmica</label>
        <select id="zonaTermica" v-model="formproyecto.zona_termica" required>
          <option value="" disabled>Seleccione una zona</option>
          <option value="A">A</option>
          <option value="B">B</option>
          <option value="B1">B1</option>
          <option value="C">C</option>
          <option value="D">D</option>
          <option value="E">E</option>
          <option value="F">F</option>
          <option value="G">G</option>
          <option value="H">H</option>
          <option value="I">I</option>
        </select>
      </div>

      <button type="submit" :disabled="loading">Guardar</button>

      <p v-if="localError" class="error-message">{{ localError }}</p>
      <p v-if="storeError" class="error-message">{{ storeError }}</p>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref, computed, defineEmits } from 'vue';
import { useProyectoStore } from '@/stores/ProyectoVivienda/useProyectoStore';
import { storeToRefs } from 'pinia';

const emit = defineEmits(['proyecto-creado']);

const proyectoStore = useProyectoStore();
const { loading: storeLoading, error: storeError } = storeToRefs(proyectoStore);

// Objeto reactivo para el formulario
const formproyecto = reactive({
  rol_matriz_parte1: '',
  rol_matriz_parte2: '',
  nombre_proyecto: '',
  numero_viviendas: null,
  zona_termica: ''
});

const loading = computed(() => storeLoading.value);
const localError = ref(null);

function validarFormulario() {
  localError.value = null;
  const rolMatriz = `${formproyecto.rol_matriz_parte1}-${formproyecto.rol_matriz_parte2}`;

  if (!formproyecto.rol_matriz_parte1 || !formproyecto.rol_matriz_parte2 || rolMatriz.length > 11) {
    localError.value = 'Rol Matriz es obligatorio y debe tener un formato de 5-5 (ejemplo: 12345-67890).';
    return false;
  }
  if (!formproyecto.nombre_proyecto || formproyecto.nombre_proyecto.length > 45) {
    localError.value = 'Nombre Proyecto es obligatorio y debe tener menos de 45 caracteres.';
    return false;
  }
  if (!formproyecto.zona_termica) {
    localError.value = 'Zona Térmica es obligatoria.';
    return false;
  }
  return true;
}

async function submit() {
  localError.value = null;
  proyectoStore.error = null;

  if (!validarFormulario()) {
    return;
  }

  try {
    const proyectoData = {
      rol_matriz: `${formproyecto.rol_matriz_parte1}-${formproyecto.rol_matriz_parte2}`,
      nombre_proyecto: formproyecto.nombre_proyecto,
      numero_viviendas: formproyecto.numero_viviendas,
      zona_termica: formproyecto.zona_termica
    };

    await proyectoStore.crearProyecto(proyectoData);

    emit('proyecto-creado');

    // Limpiar el formulario después de un envío exitoso
    formproyecto.rol_matriz_parte1 = '';
    formproyecto.rol_matriz_parte2 = '';
    formproyecto.nombre_proyecto = '';
    formproyecto.numero_viviendas = null;
    formproyecto.zona_termica = '';

  } catch (error) {
    console.error('Error en el envío del formulario:', error);
  }
}
</script>

<style scoped>
.form-proyecto-container {
  padding-top: 0;
  font-family: Arial, sans-serif;
  max-width: 900px;
  margin: auto;
}

.form-proyecto-header {
  background-color: #006666;
  color: white;
  padding: 1.5rem 2rem;
  text-align: center;
  border-bottom: 2px solid #004d40;
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

.form-proyecto {
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

.rol-matriz-group {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.rol-matriz-group input {
  text-align: center;
}

.rol-matriz-group input:first-child,
.rol-matriz-group input:last-child {
  width: 50%;
}

.rol-matriz-group span {
  font-weight: bold;
  color: #006666;
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

.error-message {
  color: #d9534f;
  margin-top: 10px;
}
</style>