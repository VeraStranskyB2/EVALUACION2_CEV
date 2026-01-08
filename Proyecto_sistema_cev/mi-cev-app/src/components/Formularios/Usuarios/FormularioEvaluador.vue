<template>
  <div class="form-evaluador">
    <div class="form-group">
      <label for="nombreEvaluador">Nombre Evaluador</label>
      <input id="nombreEvaluador" v-model="localForm.nombreEvaluador" placeholder="Nombre Evaluador" required />
    </div>

    <div class="form-group">
      <label for="apellidos">Apellidos</label>
      <input id="apellidos" v-model="localForm.apellidos" placeholder="Apellidos" required />
    </div>

    <div class="form-group">
      <label for="profesion">Profesión</label>
      <input id="profesion" v-model="localForm.profesion" placeholder="Profesión" />
    </div>

    <div class="form-group">
      <label for="fechaAcreditacion">Fecha de Acreditación</label>
      <input id="fechaAcreditacion" type="date" v-model="localForm.fechaAcreditacion" />
    </div>  
    
  </div>
</template>

<script setup>
import { reactive, watch } from 'vue'

const props = defineProps({
  modelValue: {
    type: Object,
    default: () => ({})
  }
})

const emit = defineEmits(['update:modelValue'])

const localForm = reactive({
  nombreEvaluador: props.modelValue.nombreEvaluador || '',
  apellidos: props.modelValue.apellidos || '',
  profesion: props.modelValue.profesion || '',
  fechaAcreditacion: props.modelValue.fechaAcreditacion || ''
})

watch(
  localForm,
  (newVal) => {
    emit('update:modelValue', { ...newVal })
  },
  { deep: true }
)
</script>


<style scoped>
.form-evaluador {
  background: white;
  
  
  max-width: 450px;
  width: 100%;
  
  display: flex;
  flex-direction: column;
  gap: 8px;
}


.form-group {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.form-group label {
  font-weight: 600;
  color: #2f5233;
  margin-bottom: 26px;
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
