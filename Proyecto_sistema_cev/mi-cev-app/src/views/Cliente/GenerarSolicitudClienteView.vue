<template>
  <div class="solicitud-view-container">
    <FormularioSolicitudCliente @solicitud-creada="refrescarSolicitudes" />

    <hr class="divider">

    <h2 class="section-title">Historial de Solicitudes</h2>
    
    <div v-if="loading" class="status-message loading">
      Cargando solicitudes...
    </div>
    <div v-else-if="error" class="status-message error">
      Error al cargar las solicitudes: {{ error }}
    </div>
    <div v-else-if="solicitudes.length === 0" class="status-message no-data">
      No hay solicitudes registradas.
    </div>
    
    <TablaSolicitudCliente v-else :solicitudes="solicitudes" />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import FormularioSolicitudCliente from '@/components/Formularios/Proceso/FormularioSolicitudCliente.vue';
import { useSolicitudClienteStore } from '@/stores/Proceso/useSolicitudClienteStore';
import { useAuthStore } from '@/stores/Usuarios/useAuthStore';
import TablaSolicitudCliente from '@/components/Tablas/TablasProcesos/TablaSolicitudCliente.vue';


// Instancias del store y del router
const solicitudClienteStore = useSolicitudClienteStore();
const authStore = useAuthStore();

// Obtencion de propiedades reactivas del store
const { solicitudes, loading, error } = storeToRefs(solicitudClienteStore);

// Función para recargar la lista de solicitudes
const refrescarSolicitudes = async () => {
    const rutCliente = authStore.rut_usuario;
    if (rutCliente) {
        await solicitudClienteStore.fetchSolicitudesCliente(rutCliente);
    }
};

// Llamar a la función al montar el componente para obtener los datos iniciales
onMounted(refrescarSolicitudes);
</script>

<style scoped>
.solicitud-view-container {
  padding: 2rem;
  font-family: Arial, sans-serif;
  max-width: 1200px;
  margin: auto;
}

.divider {
  border: 0;
  height: 1px;
  background: #ccc;
  margin: 3rem 0;
}

.section-title {
  text-align: center;
  color: #006666;
  font-size: 2rem;
  border-bottom: 2px solid #006666;
  padding-bottom: 0.5rem;
  margin-bottom: 2rem;
}

.status-message {
  text-align: center;
  padding: 1rem;
  border-radius: 8px;
  font-weight: bold;
  margin-bottom: 1.5rem;
}

.loading {
  background-color: #e0f2f1;
  color: #006666;
}

.error {
  background-color: #f2dede;
  color: #d9534f;
}

.no-data {
  background-color: #f8f9fa;
  color: #6c757d;
  border: 1px solid #ddd;
}
</style>