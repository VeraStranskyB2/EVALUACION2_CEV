<template>
  <div class="registro-page">
    <div class="registro-container">
      <div class="registro-form-container">
        <form class="form-registro" @submit.prevent="handleOnSubmit">
          <h2>Registro de Usuario</h2>
          
          <div class="form-container">
            <div class="usuario-form">
              <FormularioUsuario v-model:form="localFormData" />
              <div class="form-group">
                <label for="tipoUsuario">Tipo de Usuario</label>
                <select id="tipoUsuario" v-model="localFormData.tipo">
                  <option disabled value="">Selecciona tipo</option>
                  <option value="cliente">Cliente</option>
                  <option value="evaluador">Evaluador</option>
                </select>
              </div>
            </div>
            
            <div class="extra-form">
              <FormularioCliente v-if="localFormData.tipo === 'cliente'" v-model="localFormData.cliente" />
              <FormularioEvaluador v-else-if="localFormData.tipo === 'evaluador'" v-model="localFormData.evaluador" />
            </div>
          </div>
          
          <button type="submit">Registrar</button>
        </form>
      </div>
      
      <div class="registro-image-container">
        <img src="@/assets/img/registro.jpg" alt="Regisitro de Eficiencia Energética" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import FormularioUsuario from './FormularioUsuario.vue';
import FormularioCliente from './FormularioCliente.vue';
import FormularioEvaluador from './FormularioEvaluador.vue';

// 1. Definir props y eventos. La prop 'formData' contendrá los datos del padre.
const props = defineProps({
  formData: {
    type: Object,
    required: true  
  }
});
const emit = defineEmits(['submit', 'update:formData']);

// 2. Crear una referencia local reactiva para el formulario
//    Esto permite modificar el objeto sin mutar directamente la prop.
const localFormData = ref(props.formData);

// 3. Ver los cambios de la referencia local para emitirlos al padre.
import { watch } from 'vue';
watch(localFormData, (newValue) => {
  emit('update:formData', newValue);
}, { deep: true });

// 4. Función para validar RUT
function validarRut(rut) {
  const rutRegex = /^\d{1,2}\.\d{3}\.\d{3}-[\dkK]$/;
  return rutRegex.test(rut);
}

// 5. Esta función maneja el envío del formulario.
function handleOnSubmit() {
  const usuario = localFormData.value; // Ahora 'localFormData' existe y es correcto
  
  // 6. Validaciones básicas
  if (!usuario.rutUsuario || !usuario.email || !usuario.password || !usuario.tipo) { 
    alert('Complete los campos obligatorios del usuario.');
    return;
  }

  // 7. Validar RUT
  if (!validarRut(usuario.rutUsuario)) {
    alert('El RUT no tiene un formato válido (Ej: 12.345.678-9).');
    return;
  }

  // 8. Validar correo electrónico
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(usuario.email)) {
    alert('Por favor, ingresa un correo válido.');
    return;
  }

  // 9. Validar que la contraseña tenga al menos 6 caracteres
  if (usuario.password.length < 6) {
    alert('La contraseña debe tener al menos 6 caracteres.');
    return;
  }

  // 10. Se emite el evento para que el componente padre (RegistroView) se encargue del resto.
  emit('submit', usuario);
}
</script>

<style scoped>
.registro-page {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #e2f0cb;
  padding: 20px;
  box-sizing: border-box;
}

.registro-container {
  display: flex; /* control de altura */
  gap: 3rem;
  align-items: stretch; /* hace que los hijos se estiren para coincidir en altura */
  max-width: 1200px;
  width: 100%;
  background: white; 
  border-radius: 20px;
  box-shadow: 0 4px 24px rgba(0,0,0,0.07);
  overflow: hidden;
}

.registro-form-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
}

.form-registro {
  width: 100%;
  padding: 34px 24px;
  background-color: white;    
  border-radius: 20px;
  box-shadow: 0 4px 12px #006666;
  overflow: hidden;
}


.form-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 2rem;
}

.usuario-form {
  padding: 1rem;
  background: white;
}

.extra-form {
  padding: 1rem;
    
}

.form-registro h2 {
  text-align: center;
  margin-bottom: 2rem;
  font-size: 1.8rem;
  color: #006666;
}

.form-registro label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 600;
  color: #006666;
}

.form-registro input,
.form-registro select {
  width: 100%;
  padding: 0.75rem;
  margin-bottom: 1.2rem;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 1em;
}

.form-registro button {
  display: block;
  width: 100%;              
  max-width: 690px;         
  margin: 2rem auto 0 auto; 
  padding: 0.85rem;
  background-color: #006666;
  color: white;
  border: 2px solid #006666;
  border-radius: 8px;
  font-weight: 600;
  font-size: 1.1em;
  cursor: pointer;
  transition: all 0.3s ease;
}

.form-registro button[type="submit"]:hover {
    background: #1870e4;
    color: #ffffff;
}

.registro-image-container {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
}

.registro-image-container img {
    width: 100%;
    height: 100%; /* La imagen se estira para llenar la altura de su contenedor */
    object-fit: contain; /* Evita que la imagen se distorsione */
    border-radius: 20px;
}

@media (max-width: 768px) {
    .registro-container {
        flex-direction: column; 
    }
    .registro-image-container {
        margin-top: 2rem;
    }
}
</style>