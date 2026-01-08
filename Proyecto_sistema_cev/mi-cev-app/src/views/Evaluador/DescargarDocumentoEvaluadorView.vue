<template>
  <div class="descargar-documentos-container">
    <div class="header-container">
      <h2 class="section-title">Revisar Documentos</h2>
      <p class="section-subtitle">A continuación, se muestran los documentos subidos por el cliente para su revisión y descarga.</p>
    </div>

    <!-- Muestra un mensaje de carga si el store está cargando -->
    <div v-if="loading" class="loading-message">
      Cargando documentos...
    </div>

    <!-- Muestra un mensaje si no hay documentos -->
    <div v-else-if="!documentos || documentos.length === 0" class="sin-documentos">
      No hay documentos para mostrar en esta solicitud.
    </div>

    <!-- Muestra un mensaje de error si el store lo tiene -->
    <div v-else-if="error" class="error-message">
      {{ error }}
    </div>

    <!-- Muestra la tabla si los documentos están cargados y no hay errores -->
    <TablaDocumentoEvaluador v-else :documentos="documentos" />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useDocumentoEvaluadorStore } from '@/stores/Proceso/useDocumentoEvaluadorStore';
import TablaDocumentoEvaluador from '@/components/Tablas/TablasProcesos/TablaDocumentoEvaluador.vue';
import { storeToRefs } from 'pinia';

// Instancia del store y obtencion de propiedades reactivas del store
const store = useDocumentoEvaluadorStore();
const { documentos, loading, error } = storeToRefs(store);

// Para este ejemplo, el ID de solicitud es un valor fijo.
const solicitudId = 'ID_DE_SOLICITUD_EVALUADOR_AQUI';

// Llama a la acción para cargar los documentos al iniciar la vista
onMounted(() => {
  store.fetchDocumentosPorSolicitud(solicitudId);
});
</script>

<style scoped>
.descargar-documentos-container {
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

.loading-message,
.sin-documentos,
.error-message {
  text-align: center;
  padding: 1rem;
  border-radius: 8px;
  margin-top: 2rem;
}

.loading-message {
  color: #006666;
  background-color: #e0f2f1;
}

.sin-documentos {
  color: #6c757d;
  background-color: #f8f9fa;
  border: 1px solid #ddd;
}

.error-message {
  color: #d9534f;
  background-color: #f2dede;
  border: 1px solid #ebccd1;
}
</style>
