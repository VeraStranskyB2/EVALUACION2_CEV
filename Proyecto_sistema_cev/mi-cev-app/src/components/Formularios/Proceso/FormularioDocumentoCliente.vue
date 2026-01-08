<template>
  <div class="subir-documentos-container">
    <!-- Formulario -->
    <form @submit.prevent="subir" class="form-subir-documento"> 
      <div class="form-group">
        <label for="archivo">Archivo:</label>
        <div class="file-input-container">
          <input type="file" id="archivo" @change="handleFileUpload" required />
          <span class="file-chosen-text">
            {{ archivo ? archivo.name : 'No se eligió ningún archivo' }}
          </span>
          <label for="archivo" class="file-input-label">Elegir archivo</label>
        </div>
      </div>

      <div class="form-group">
        <label for="tipo">Tipo:</label>
        <input type="text" id="tipo" v-model="tipo" placeholder="Ej: Certificado DOM, Certificado SEC, Específicaciones Técnicas, planos de Arquitectura, Plano estructural, Plano de instalaciones, etc" required />
      </div>

      <div class="form-group">
        <label for="comentarios">Comentarios:</label>
        <textarea id="comentarios" v-model="comentarios" placeholder="Añade un comentario (opcional)"></textarea>
      </div>

      <!-- El botón utiliza el estado 'loading' del store para mostrar un indicador visual -->
      <button type="submit" :disabled="loading">
        {{ loading ? 'Subiendo...' : 'Subir' }}
      </button>
    </form>
    
    <!-- Muestra los errores directamente del store, si existen -->
    <div v-if="error" class="error-message">
      {{ error }}
    </div>
  </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';
import { useDocumentoClienteStore } from '@/stores/Proceso/useDocumentoClienteStore';
import { storeToRefs } from 'pinia';

// Se define el evento que este componente puede emitir
const emits = defineEmits(['documento-subido']);

const documentoClienteStore = useDocumentoClienteStore();
const { loading, error } = storeToRefs(documentoClienteStore);

const archivo = ref(null);
const tipo = ref('');
const comentarios = ref('');
const solicitudId = 'ID_DE_SOLICITUD_AQUI'; 

function handleFileUpload(event) {
  archivo.value = event.target.files[0];
}

async function subir() {
  if (!archivo.value || !tipo.value) {
    documentoClienteStore.error = "Faltan campos por completar";
    return;
  }

  const formData = new FormData();
  formData.append('archivo', archivo.value);
  formData.append('tipo', tipo.value);
  formData.append('comentarios', comentarios.value);
  formData.append('solicitud_id', solicitudId);

  // Se llama a la acción de subida del store
  await documentoClienteStore.subirDocumento(formData);

  // Emite el evento para notificar al componente padre que la subida ha terminado
  emits('documento-subido');

  // Limpiar el formulario
  archivo.value = null;
  tipo.value = '';
  comentarios.value = '';
}
</script>

<style scoped>
.subir-documentos-container {
  padding-top: 0; 
  padding-right: 2rem;
  padding-bottom: 2rem;
  padding-left: 2rem;
  font-family: Arial, sans-serif;
  max-width: 900px;
  margin: auto;
}

.header-container {
  text-align: center;
  margin-bottom: 2rem;
}

.section-title {
  color: #006666;
  font-size: 2rem;
  padding-bottom: 1rem;
  border-bottom: 5px solid #006666;
}

.section-subtitle {
  margin-top: 1rem;
  color: #006666;
}

.form-subir-documento {
  background-color: #f8f9fa;
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid #006666; 
  box-shadow: 0 12px 12px #006666;
  margin-bottom: 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
  padding: 8px 0px;
}

label {
  display: block;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #006666;
}

input,
textarea {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

textarea {
  height: 100px;
  resize: vertical;
}

.file-input-container {
  display: flex;
  align-items: center;
  gap: 10px;
}

.file-input-container input[type="file"] {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}

.file-input-label {
  background-color: #006666;
  color: white;
  padding: 12px 10px;
  border-radius: 4px;
  cursor: pointer;
  font-weight: normal;
  transition: background-color 0.3s;
  flex-basis: 20%;
  text-align: center;
}

.file-input-label:hover {
  background-color: #1870e4;
}

.file-chosen-text {
  flex-grow: 1;
  border: 1px solid #ccc;
  padding: 12px 15px;
  border-radius: 4px;
  background-color: #f0f0f0;
  color: #555;
  min-height: 48px;
  display: flex;
}

button {
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
}

button:hover {
  background-color: #1870e4;
}

.error-message {
  color: #d9534f;
  background-color: #f2dede;
  border: 1px solid #ebccd1;
  padding: 1rem;
  border-radius: 4px;
  text-align: center;
  margin-bottom: 1rem;
}
</style>
