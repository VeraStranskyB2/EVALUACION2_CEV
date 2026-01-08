<template>
  <div class="subir-documentos-container">
    <div class="header-container">
      <h2 class="section-title">Subir Documento Cliente</h2>
      <p class="section-subtitle">Por favor, suba los documentos que validan su solicitud para avanzar con el proceso.</p>
    </div>

    <!-- El formulario solo se encarga de la subida. Le pasamos el estado de carga y el error. -->
    <FormularioDocumentoCliente @documento-subido="refrescarDocumentos" :cargando="loading" :error="error" />

    <div class="documentos-subidos-section">
      <h3 class="documents-section-title">Documentos Subidos</h3>
      
      <!-- Mostramos un mensaje de carga mientras se obtienen los datos -->
      <div v-if="loading" class="loading-message">
        Cargando documentos...
      </div>
      
      <!-- Si no hay documentos o están cargando, mostramos un mensaje -->
      <div v-else-if="!documentos || documentos.length === 0" class="sin-documentos">
        No hay documentos para mostrar.
      </div>
      
      <!-- Si hay documentos, mostramos la tabla -->
      <TablaDocumentoCliente v-else :documentos="documentos" />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useDocumentoClienteStore } from '@/stores/Proceso/useDocumentoClienteStore';
import FormularioDocumentoCliente from '@/components/Formularios/Proceso/FormularioDocumentoCliente.vue';
import TablaDocumentoCliente from '@/components/Tablas/TablasProcesos/TablaDocumentoCliente.vue';
import { storeToRefs } from 'pinia';

// Instancia del Store y obtencion de propiedades reactivas
const store = useDocumentoClienteStore();
const { documentos, loading, error } = storeToRefs(store);

// Aquí puedes definir el ID de la solicitud
const solicitudId = 'ID_DE_SOLICITUD_AQUI';

// Función para recargar la lista de documentos
const refrescarDocumentos = async () => {
  await store.fetchDocumentosPorSolicitud(solicitudId);
};

// Llamar al cargar la vista
onMounted(refrescarDocumentos);
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

.documentos-subidos-section {
  padding-top: 1rem;
}

.documents-section-title {
  color: #006666;
  font-size: 1.5rem;
  text-align: center;
  margin-bottom: 1rem;
}
</style>