<template>
  <div class="registro-wrapper">
    <FormularioRegistro :form-data="usuario" @submit="registrarUsuario" />

    <p v-if="error" style="color: red;">{{ error }}</p>
    <p v-if="successMessage" style="color: green;">{{ successMessage }}</p>
    <p v-if="loading">Registrando usuario...</p>     
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/Usuarios/useAuthStore'
import FormularioRegistro from '@/components/Formularios/Usuarios/FormularioRegistro.vue'

const router = useRouter()
const authStore = useAuthStore()

// 1. Estructura de datos que se pasará al formulario.
//    Es reactiva para que el componente hijo la pueda modificar.
const usuario = reactive({
  // Datos de Usuario
  rutUsuario: '',
  email: '',
  telefono: '',
  password: '',
  tipo: '', // 'cliente' o 'evaluador'
  
  // Datos de Cliente anidados
  cliente: {
    razonSocial: '',
    representanteLegal: '',
    giroComercial: '',
    fechaRegistro: '',
  },

  // Datos de Evaluador anidados
  evaluador: {
    nombreEvaluador: '',
    apellidos: '',
    profesion: '',
    fechaAcreditacion: ''
  },
})

const error = ref(null)
const successMessage = ref('')
const loading = ref(false)

// 2. Función para manejar el registro que se llama al recibir el evento del hijo.
async function registrarUsuario(formData) {
  error.value = null
  successMessage.value = ''
  loading.value = true
  try {
    // Usar el store para registrar usuario
    await authStore.register(formData)
    successMessage.value = 'Registro exitoso, puedes iniciar sesión.'
    setTimeout(() => router.push('/login'), 1500)
  } catch (e) {
    error.value = authStore.error || e.message || 'Error en registro'
  } finally {
    loading.value = false
  }
}
</script>


<style scoped>
.registro-wrapper {
  min-height: 100vh;
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #e2f0cb;
  box-sizing: border-box;
  padding: 20px;
}
</style>