<template>
  <div class="evaluacion-view-container">
    <FormularioEvaluacion />
    
    <div class="tabla-evaluacion-section">
      <TablaEvaluacion 
        :evaluaciones="evaluaciones"
        :cargando="evaluacionStore.loading"
        :error="evaluacionStore.error"
      />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useEvaluacionStore } from '@/stores/Proceso/useEvaluacionStore';
import { useAuthStore } from '@/stores/Usuarios/useAuthStore';
import { storeToRefs } from 'pinia';

import FormularioEvaluacion from '@/components/Formularios/Proceso/FormularioEvaluacion.vue';
import TablaEvaluacion from '@/components/Tablas/TablasProcesos/TablaEvaluacion.vue';



const evaluacionStore = useEvaluacionStore();
const authStore = useAuthStore();
const { evaluaciones } = storeToRefs(evaluacionStore);

const cargarEvaluaciones = async () => {
  const rutEvaluador = authStore.rut_usuario;
  await evaluacionStore.fetchEvaluacionesPorEvaluador(rutEvaluador);
};

onMounted(async () => {
  await cargarEvaluaciones();
});
</script>

<style scoped>
/* Estilos básicos del layout */
.evaluacion-view-container {
  font-family: Arial, sans-serif;
  max-width: 1200px;
  margin: auto;
  padding: 0 2rem 2rem 2rem; 
}

.tabla-evaluacion-section {
  margin-top: 3rem;
}
</style>