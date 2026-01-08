<template>
  <div class="form-proyecto-container">
    <div class="form-proyecto-header">
      <h2>Registrar Vivienda</h2>
      <p>Complete los campos para registrar una nueva vivienda.</p>
    </div>

    <form class="form-proyecto" @submit.prevent="submit">
      <!-- Mensajes de feedback del usuario -->
      <p v-if="localError" class="error-message">{{ localError }}</p>
      <p v-if="storeError" class="error-message">{{ storeError }}</p>
      <p v-if="successMessage" class="success-message">{{ successMessage }}</p>

      <!-- Campo: Rol Vivienda -->
      <div class="form-group">
        <label for="rol_vivienda">Rol de Vivienda</label>
        <div class="rol-matriz-group">
          <input id="rol_vivienda_part1" type="text" v-model="form.rol_vivienda_part1" placeholder="00000" maxlength="5" required/>
          <span>-</span>
          <input id="rol_vivienda_part2" type="text" v-model="form.rol_vivienda_part2" placeholder="00000" maxlength="5" required/>
        </div>
      </div>

      <!--Tipo de Vivienda y Estado de la Vivienda -->
      <div class="form-group-row">
        <div class="form-group">
          <label for="tipo">Tipo de Vivienda</label>
          <select id="tipo" v-model="form.tipo" required>
            <option value="" disabled>Seleccione un tipo</option>
            <option v-for="tipo in tiposDeVivienda" :key="tipo" :value="tipo">
              {{ tipo }}
            </option>
          </select>
        </div>
        <div class="form-group">
          <label for="estado">Estado de la Vivienda</label>
          <select id="estado" v-model="form.estado" required>
            <option value="" disabled>Seleccione un estado</option>
            <option v-for="estado in estadosDeVivienda" :key="estado" :value="estado">
              {{ estado }}
            </option>
          </select>
        </div>
      </div>

      <!-- Lote y Manzana -->
      <div class="form-group-row">
        <div class="form-group">
          <label for="lote">Lote</label>
          <input id="lote" type="text" v-model="form.lote" maxlength="12"/>
        </div>
        <div class="form-group">
          <label for="manzana">Manzana</label>
          <input id="manzana" type="text" v-model="form.manzana" maxlength="12"/>
        </div>
      </div>

      <!-- Edificio y Piso -->
      <div class="form-group-row">
        <div class="form-group">
          <label for="edificio">Edificio</label>
          <input id="edificio" type="text" v-model="form.edificio" maxlength="20"/>
        </div>
        <div class="form-group">
          <label for="piso">Piso</label>
          <input id="piso" type="number" v-model.number="form.piso"/>
        </div>
      </div>

      <!-- Unidad y Orientación -->
      <div class="form-group-row">
        <div class="form-group">
          <label for="unidad">Unidad</label>
          <input id="unidad" type="text" v-model="form.unidad" maxlength="12"/>
        </div>
        <div class="form-group">
          <label for="orientacion">Orientación</label>
          <select id="orientacion" v-model="form.orientacion" required>
            <option value="" disabled>Seleccione una orientación</option>
            <option v-for="orientacion in orientaciones" :key="orientacion" :value="orientacion">
              {{ orientacion }}
            </option>
          </select>
        </div>
      </div>

      <!-- Materialidad -->
      <div class="form-group">
        <label for="materialidad">Materialidad</label>
        <select id="materialidad" v-model="form.materialidad" required>
          <option value="" disabled>Seleccione una materialidad</option>
          <option v-for="materialidad in materialidades" :key="materialidad" :value="materialidad">
            {{ materialidad }}
          </option>
        </select>
      </div>

      <!-- Botón de envío -->
      <button type="submit" :disabled="loading">
        Guardar
      </button>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref, computed, defineEmits } from 'vue';
import { useViviendaStore } from '@/stores/ProyectoVivienda/useViviendaStore';
import { storeToRefs } from 'pinia';

// Define los eventos que el componente puede emitir
const emit = defineEmits(['registro-exitoso']);

const viviendaStore = useViviendaStore();
const { loading: storeLoading, error: storeError } = storeToRefs(viviendaStore);

const orientaciones = ['Norte', 'Sur', 'Oriente', 'Poniente', 'Nor-Oriente', 'Nor-Poniente', 'Sur-Oriente', 'Sur-Poniente'];
const materialidades = [
  'A(acero)',
  'B(Hormigón armado)',
  'C(Albañilería)',
  'E(Madera)',
  'F(Adobe)',
  'G(Acero Galvanizado)',
];
const tiposDeVivienda = ['Casa Aislada', 'Pareada o en Bloque', 'Departamento'];
const estadosDeVivienda = ['Nueva', 'Usada'];

const form = reactive({
  rol_vivienda_part1: '',
  rol_vivienda_part2: '',
  tipo: '',
  estado: '',
  lote: '',
  manzana: '',
  edificio: '',
  piso: null,
  unidad: '',
  orientacion: '',
  materialidad: '',
});

const loading = computed(() => storeLoading.value);
const localError = ref(null);
const successMessage = ref(null);

function validarFormulario() {
  localError.value = null;

  if (!form.rol_vivienda_part1 || !/^\d{5}$/.test(form.rol_vivienda_part1)) {
    localError.value = 'La primera parte del Rol Vivienda debe tener 5 dígitos.';
    return false;
  }
  if (!form.rol_vivienda_part2 || !/^\d{5}$/.test(form.rol_vivienda_part2)) {
    localError.value = 'La segunda parte del Rol Vivienda debe tener 5 dígitos.';
    return false;
  }
  if (!form.tipo) {
    localError.value = 'Debe seleccionar un tipo de vivienda.';
    return false;
  }
  if (!form.estado) {
    localError.value = 'Debe seleccionar un estado.';
    return false;
  }
  if (form.lote && form.lote.length > 12) {
    localError.value = 'Lote máximo 12 caracteres.';
    return false;
  }
  if (form.manzana && form.manzana.length > 12) {
    localError.value = 'Manzana máximo 12 caracteres.';
    return false;
  }
  if (form.edificio && form.edificio.length > 20) {
    localError.value = 'Edificio máximo 20 caracteres.';
    return false;
  }
  if (form.piso !== null && (isNaN(form.piso) || !Number.isInteger(form.piso) || form.piso < 0)) {
    localError.value = 'Piso debe ser un número entero positivo o cero.';
    return false;
  }
  if (form.unidad && form.unidad.length > 12) {
    localError.value = 'Unidad máximo 12 caracteres.';
    return false;
  }
  if (!form.orientacion) {
    localError.value = 'Debe seleccionar una Orientación.';
    return false;
  }
  if (!form.materialidad) {
    localError.value = 'Debe seleccionar una Materialidad.';
    return false;
  }
  return true;
}

async function submit() {
  localError.value = null;
  successMessage.value = null;
  viviendaStore.error = null;

  if (!validarFormulario()) {
    return;
  }

  try {
    const viviendaData = {
      rol_vivienda: `${form.rol_vivienda_part1}-${form.rol_vivienda_part2}`,
      tipo: form.tipo,
      estado: form.estado,
      lote: form.lote || null,
      manzana: form.manzana || null,
      edificio: form.edificio || null,
      piso: form.piso || null,
      unidad: form.unidad || null,
      orientacion: form.orientacion,
      materialidad: form.materialidad,
      // Se asume que estos campos se autogeneran en el backend
      id_direccion: null,
      proyecto_id_proyecto: props.proyectoId,
    };

    await viviendaStore.registrarVivienda(viviendaData);
    successMessage.value = 'Vivienda registrada exitosamente.';

    // Emite evento para que el componente padre sepa que se ha registrado una vivienda
    emit('registro-exitoso');

    // Resetea formulario
    for (const key in form) {
      form[key] = key === 'piso' ? null : '';
    }
  } catch (err) {
    localError.value = 'Error al registrar la vivienda. Revise la consola para más detalles.';
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

/* Nuevo estilo para las filas de 2 columnas */
.form-group-row {
  display: flex;
  gap: 1.5rem;
}
.form-group-row .form-group {
  flex: 1;
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
.success-message {
  color: #28a745;
  margin-top: 10px;
}
</style>
