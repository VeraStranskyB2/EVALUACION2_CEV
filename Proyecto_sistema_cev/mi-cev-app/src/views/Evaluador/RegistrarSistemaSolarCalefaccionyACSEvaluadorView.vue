<template>
  <div class="main-container"> 
    <!-- Componente de formulario para registrar un nuevo sistema solar -->
    <FormularioSistemaSolarCalefaccionyACS @registro-exitoso="refrescarSistemas" />

    <hr class="divider">

    <!-- Mensajes de estado: cargando, error o sin datos -->
    <div v-if="loading" class="status-message loading">
      Cargando sistemas solares...
    </div>
    <div v-else-if="error" class="status-message error">
      Error: {{ error }}
    </div>
    <div v-else-if="sistemas.length === 0" class="status-message no-data">
      No hay sistemas solares registrados.
    </div> 
    
    
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import { useSistemaSolarCalefaccionyACSStore } from '@/stores/SistemasTecnicos/useSistemaSolarCalefaccionyACSStore.js';
import FormularioSistemaSolarCalefaccionyACS from '@/components/Formularios/SistemasTecnicos/FormularioSistemaSolarCalefaccionyACS.vue';

// Instancia del store de sistemas solares
const sistemaSolarCalefaccionyACSStore = useSistemaSolarCalefaccionyACSStore();

// Obtencion de propiedades reactivas del store
const { sistemas, loading, error } = storeToRefs(sistemaSolarCalefaccionyACSStore);

// Función para recargar la lista de sistemas
const refrescarSistemas = async () => {
  await sistemaSolarCalefaccionyACSStore.fetchSistemas();
};

// Llamar a la función al montar el componente para obtener los datos iniciales
onMounted(refrescarSistemas);
</script>

<style scoped>
.main-container {
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