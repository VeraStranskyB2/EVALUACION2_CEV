<template>
  <div class="Login-page">
    
    <div class="login-content">
      
      <div class="login-wrapper">
        <form class="form-login" @submit.prevent="loginUser">
          <h2>Iniciar Sesión</h2>
          <div>
            <label for="rut">RUT:</label>
            <input v-model="rut_usuario" id="rut" required />
          </div>
          <div>
            <label for="password">CONTRASEÑA:</label>
            <input type="password" v-model="user_password" id="password" required />
          </div>
          <button type="submit" :disabled="loading">Ingresar</button>
          <p v-if="error" style="color: red;">{{ error }}</p>
        </form>
      </div>

      <div class="login-image-container">
        <img src="@/assets/img/Logo_CEV.png" alt="Imagen de bienvenida" />
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/Usuarios/useAuthStore'

const rut_usuario = ref('')
const user_password = ref('')
const error = ref(null)
const loading = ref(false)
const router = useRouter()
const authStore = useAuthStore()

async function loginUser() {
  error.value = null
  loading.value = true

  try {
    // Llamamos al store para autenticar
    await authStore.login({
      rut_usuario: rut_usuario.value,
      password: user_password.value
    })

    // Redirige según rol
    if (authStore.getRol === 'cliente') router.push('/cliente/home')
    else if (authStore.getRol === 'evaluador') router.push('/evaluador/home')
    else router.push('/') // fallback si no hay rol

  } catch (err) {
    error.value = err.message || 'Error de autenticación. Por favor, revisa tus credenciales.'
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-page {
  /* Contenedor principal para centrar todo el contenido en la página */
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #e2f0cb;
  padding: 20px;
  box-sizing: border-box;

}

.login-content {
  /* Contenedor que agrupa el formulario y la imagen */
  display: grid;
  grid-template-columns: 2fr 3fr; /* Divide el espacio: 1/3 para el formulario, 2/3 para la imagen */
  width: 100%;  
  background: #e2f0cb;
  border-radius: 20px;
  box-shadow: 0 4px 24px rgba(0,0,0,0.07);
  overflow: hidden; /* Evita que la imagen se salga del contenedor */
  
}

/* Contenedor del formulario */
.login-form-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center; /* Centra el formulario verticalmente */
  align-items: center; /* Centra el formulario horizontalmente */
  padding: 32px 24px;
  
}

/* Contenedor de la imagen */
.login-image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  object-fit: contain; 
  
}

.login-image-container img {
  width: 100%;
  height: auto;
  object-fit: contain;
  
 
}

/* Estilos del formulario*/
.form-login {
  width: 100%;
  max-width: 400px;  
  margin-top: 150px;
  margin-left: 70px ;
  padding: 34px 24px;
  background: #f2fce3;  
  border: 1px solid #006666; 
  border-radius: 8px;
  box-shadow: 0 12px 12px #006666;
  display: flex;
  flex-direction: column;
  align-items: stretch;  /*Este hace que inputs y botones tengan mismo ancho */
}

.form-login h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #006666;
  font-size: 2rem;
  font-weight: 700;
}

.form-login label {
  display: block;
  width: 100%;
  text-align: left;
  margin: 12px 0  6px 0;
  font-weight: 600;
  color: #006666;
  font-size: 1.03em;
}

.form-login input {
  width: 100%;
  padding: 14px 8px;
  margin-bottom: 12px;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 8px;
  transition: border 0.2s;
  background: #f3f3f3; 
  margin-top: 20px;
  display: block;
  box-sizing: border-box; /* Este asegura que padding+border no rompa el ancho */

}

.form-login input:focus {
  outline: none;
  border-color: #1870e4;
  background: hwb(200 92% 1%);
}

.form-login button[type="submit"] {
  width: 100%;
  background: #006666;
  color: white;
  font-weight: 600;
  padding: 14px 8px;
  border: none;
  border-radius: 8px;  
  font-size: 1.12em;
  margin-top: 20px;
  cursor: pointer;  
  transition: background 0.2s;
}

.form-login button[type="submit"]:hover {
  background: #1870e4;
  color: #fff;
}

/* Media query para dispositivos móviles */
@media (max-width: 768px) {
  .login-content {
    grid-template-columns: 1fr; /* Apila el formulario y la imagen en una sola columna */
  }

  .login-image-container {
    order: -1; /* Mueve la imagen arriba en el orden */
  }
}
</style>