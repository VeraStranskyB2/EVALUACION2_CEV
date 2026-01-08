<template>
  <div class="ver-solicitudes-evaluador-container">
    <h2 class="page-title">Solicitudes Pendientes de Evaluación</h2>
    
    <div v-if="loading" class="loading-message">
      Cargando solicitudes...
    </div>
    <div v-else-if="error" class="error-message">
      Error: {{ error }}
    </div>
    <TablaSolicitudesEvaluador 
      v-else 
      :solicitudes="solicitudes" 
      :cargando="loading"
      :error="error"
      @aceptar="handleAceptar" 
      @rechazar="handleRechazar" 
      @ver-detalle="handleVerDetalle"
    />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useSolicitudEvaluadorStore } from '@/stores/Proceso/useSolicitudEvaluadorStore';
import { useAuthStore } from '@/stores/Usuarios/useAuthStore';
import { storeToRefs } from 'pinia';
import TablaSolicitudesEvaluador from '@/components/Tablas/TablasProcesos/TablaSolicitudesEvaluador.vue';

const solicitudEvaluadorStore = useSolicitudEvaluadorStore();
const authStore = useAuthStore();
const router = useRouter();
const { solicitudes, loading, error } = storeToRefs(solicitudEvaluadorStore);

onMounted(async () => {
  const rutEvaluador = authStore.rut_usuario;
  if (rutEvaluador) {
    await solicitudEvaluadorStore.fetchSolicitudesPorEvaluador(rutEvaluador);
  } else {
    // Manejar caso donde no hay RUT de evaluador
    console.error('No se ha encontrado el RUT del evaluador.');
  }
});

const handleAceptar = async (solicitudId) => {
  if (confirm('¿Está seguro de aceptar esta solicitud?')) {
    const rutEvaluador = authStore.rut_usuario;
    try {
      await solicitudEvaluadorStore.aceptarSolicitud(solicitudId, rutEvaluador);
      alert('Solicitud aceptada exitosamente.');
    } catch (err) {
      alert(`Error al aceptar la solicitud: ${err.message}`);
    }
  }
};

const handleRechazar = async (solicitudId) => {
  if (confirm('¿Está seguro de rechazar esta solicitud?')) {
    const rutEvaluador = authStore.rut_usuario;
    try {
      await solicitudEvaluadorStore.rechazarSolicitud(solicitudId, rutEvaluador);
      alert('Solicitud rechazada exitosamente.');
    } catch (err) {
      alert(`Error al rechazar la solicitud: ${err.message}`);
    }
  }
};

const handleVerDetalle = (solicitudId) => {
  router.push({ name: 'evaluador-detalle-solicitud', params: { id: solicitudId } });
};
</script>

<style scoped>
.ver-solicitudes-evaluador-container {
  padding-top: 0; 
  padding-right: 2rem;
  padding-bottom: 2rem;
  padding-left: 2rem;
  font-family: Arial, sans-serif;
  max-width: 900px;
  margin: auto;
}

.page-title {
  text-align: center;
  color: #006666;
  font-size: 2rem;
  padding-bottom: 0.5rem;
  margin-bottom: 2rem;
  border-bottom: 5px solid #006666;
}

.loading-message,
.error-message {
  text-align: center;
  padding: 1.5rem;
  border-radius: 8px;
  margin-top: 2rem;
  font-weight: bold;
}

.loading-message {
  color: #006666;
  background-color: #e0f2f1;
  border: 1px solid #006666;
}

.error-message {
  color: #d9534f;
  background-color: #f2dede;
  border: 1px solid #ebccd1;
}

</style>