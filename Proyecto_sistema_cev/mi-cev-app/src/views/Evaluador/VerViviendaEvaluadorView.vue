<template>
  <div class="vivienda-evaluador-container">
    <div class="tabla-container">
      <h3 class="documents-section-title">Viviendas Asignadas para Evaluar</h3>

      <div v-if="loading" class="loading-message">Cargando viviendas...</div>
      <div v-else-if="error" class="error-message">Error: {{ error }}</div>

      <TablaVivienda
        v-else
        :viviendas="viviendas"
        rol="evaluador"
        @seleccionar="seleccionarVivienda"
        @eliminar="eliminarVivienda"
      />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useViviendaStore } from '@/stores/ProyectoVivienda/useViviendaStore';
import { useAuthStore } from '@/stores/Usuarios/useAuthStore';
import TablaVivienda from '@/components/Tablas/TablasProyectoVivienda/TablaVivienda.vue';
import { storeToRefs } from 'pinia';

//Stores
const viviendaStore = useViviendaStore();
const authStore = useAuthStore();

// Estado reactivo del store
const { viviendas, loading, error } = storeToRefs(viviendaStore);

// Carga las viviendas asignadas al evaluador.
const cargarViviendasEvaluador = async () => {
  if (!authStore.rut_usuario) {
    console.warn('No se encontró rut_usuario en authStore');
    return;
  }
  // Por ahora, carga todas las viviendas. Se debe implementar
  // la lógica de filtrado por evaluador en el backend/servicio.
  await viviendaStore.fetchTodasLasViviendas({ evaluador_rut: authStore.rut_usuario });
};

// Handlers de acciones
const seleccionarVivienda = (vivienda) => {
  
  // Lógica para seleccionar/editar la vivienda, por ejemplo, navegar a otra vista
  console.log('Seleccionar vivienda:', vivienda);
};

const eliminarVivienda = async (rolVivienda) => {
  if (window.confirm(`¿Estás seguro de que quieres eliminar la vivienda con rol: ${rolVivienda}?`)) {
    try {
      await viviendaStore.eliminarVivienda(rolVivienda);
      window.alert('Vivienda eliminada correctamente.');
    } catch (err) {
      window.alert('Error al eliminar la vivienda.');
      console.error(err);
    }
  }
};
// Al montar el componente, carga las viviendas del evaluador
onMounted(cargarViviendasEvaluador);
</script>


<style scoped>
.vivienda-evaluador-container {
  padding-top: 0;
  max-width: 900px;
  margin: auto;
  font-family: Arial, sans-serif;
  color: #333;
}

.tabla-container {
  margin-top: 2rem;
  padding: 1.5rem;
  background-color: #f8f9fa;
  border-radius: 12px;
  border: 1px solid #ddd;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.documents-section-title {
  color: #006666;
  font-size: 1.8rem;
  text-align: center;
  margin-bottom: 1.5rem;
}

.loading-message,
.error-message {
  text-align: center;
  font-size: 1.2rem;
  padding: 2rem;
  color: #555;
}

.error-message {
  color: #d9534f;
}
</style>