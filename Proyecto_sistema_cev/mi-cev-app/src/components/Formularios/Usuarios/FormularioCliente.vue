<template>
  <div class="form-cliente">       
    <div class="form-group">
      <label for="razonSocial">Razón social</label>
      <input id="razonSocial" v-model="localForm.razonSocial" placeholder="Razón social" required />
    </div>

    <div class="form-group">
      <label for="representanteLegal">Representante legal</label>
      <input id="representanteLegal" v-model="localForm.representanteLegal" placeholder="Representante legal" />
    </div>

    <div class="form-group">
      <label for="giroComercial">Giro Comercial</label>
      <input id="giroComercial" v-model="localForm.giroComercial" placeholder="Giro Comercial" />
    </div>

    <div class="form-group">
      <label for="fechaRegistro">Fecha de Registro</label>
      <input id="fechaRegistro" type="date" v-model="localForm.fechaRegistro" required />
    </div>   

  </div>
</template>

<script setup>
import { reactive, watch } from 'vue'

// Props para recibir el modelo desde el padre
const props = defineProps({
  modelValue: {
    type: Object,
    default: () => ({})
  }
})

// Emit para enviar actualizaciones hacia el padre
const emit = defineEmits(['update:modelValue'])

// Inicializamos reactive con el valor recibido para que se pueda modificar localmente
const localForm = reactive({
  razonSocial: props.modelValue.razonSocial || '',
  representanteLegal: props.modelValue.representanteLegal || '',
  giroComercial: props.modelValue.giroComercial || '',
  fechaRegistro: props.modelValue.fechaRegistro || ''
})

// Sincronizamos los cambios del formulario hacia el padre, emitiendo el nuevo objeto
watch(
  localForm,
  (newVal) => {emit('update:modelValue', { ...newVal })},
  { deep: true }
)
</script>

<style scoped>
.form-cliente {
  background: white;  
  
  max-width: 450px;
  width: 100%;
  
  display: flex;
  flex-direction: column;
  gap: 18px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  font-weight: 600;
  color: #2f5233;
  margin-bottom: 6px;
  font-size: 1.03em;
}

.form-group input {
  padding: 9px 12px;
  font-size: 1em;
  border-radius: 6px;
  border: 1.5px solid #b9b9b9;
  background: #f3f3f3;
  box-sizing: border-box;
  transition: border-color 0.2s;
}

.form-group input:focus {
  outline: none;
  border-color: #1870e4;
  background: #eaf6fc;
}

button[type="submit"] {
  background: #006666;
  color: white;
  font-weight: 700;
  padding: 12px 0;
  border: none;
  border-radius: 10px;
  font-size: 1.1em;
  cursor: pointer;
  transition: background 0.3s;
}

button[type="submit"]:hover {
  background: #406b38;
}
</style>
