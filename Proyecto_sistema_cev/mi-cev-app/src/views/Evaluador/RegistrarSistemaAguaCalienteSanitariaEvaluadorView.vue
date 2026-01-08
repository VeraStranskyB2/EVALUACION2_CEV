<template>
  <div class="main-container">
    <!-- Componente del formulario para registrar un nuevo sistema ACS -->
    <FormularioSistemaAguaCalienteSanitaria @registro-exitoso="refrescarSistemasAguaCalienteSanitaria" />

    <hr class="divider">

    <!-- Mensajes de estado basados en la lógica de la tienda (loading, error, no-data) -->
    <div v-if="loading" class="status-message loading">
      Cargando sistemas de agua caliente sanitaria...
    </div>
    <div v-else-if="error" class="status-message error">
      Error: {{ error }}
    </div>
    <div v-else-if="sistemasAguaCalienteSanitaria.length === 0" class="status-message no-data">
      No hay sistemas de agua caliente sanitaria registrados.
    </div> 
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import { useSistemaAguaCalienteSanitariaStore } from '@/stores/SistemasTecnicos/useSistemaAguaCalienteSanitariaStore.js';
import FormularioSistemaAguaCalienteSanitaria from '@/components/Formularios/SistemasTecnicos/FormularioSistemaAguaCalienteSanitaria.vue';

// Instancia del store de sistemas de agua caliente sanitaria
const sistemaAguaCalienteSanitariaStore = useSistemaAguaCalienteSanitariaStore();

// Obtencion de propiedades reactivas del store
const { sistemasAguaCalienteSanitaria, loading, error } = storeToRefs(sistemaAguaCalienteSanitariaStore);


// Función para recargar la lista de sistemas llamando a la acción del store.
const refrescarSistemasAguaCalienteSanitaria = async () => {
  await sistemaAguaCalienteSanitariaStore.fetchSistemasAguaCalienteSanitaria();
};

// Llamar a la función al montar el componente para obtener los datos iniciales
onMounted(refrescarSistemasAguaCalienteSanitaria);
</script>

<style scoped>
/* Estilos generales del contenedor principal */
.main-container {
  padding: 2rem;
  font-family: Arial, sans-serif;
  max-width: 1200px;
  margin: auto;
}

/* Estilo para la línea divisora */
.divider {
  border: 0;
  height: 1px;
  background: #ccc;
  margin: 3rem 0;
}

/* Estilos comunes para los mensajes de estado */
.status-message {
  text-align: center;
  padding: 1rem;
  border-radius: 8px;
  font-weight: bold;
  margin-bottom: 1.5rem;
}

/* Estilos específicos para el estado de carga */
.loading {
  background-color: #e0f2f1;
  color: #006666;
}

/* Estilos específicos para el estado de error */
.error {
  background-color: #f2dede;
  color: #d9534f;
}

/* Estilos específicos cuando no hay datos */
.no-data {
  background-color: #f8f9fa;
  color: #6c757d;
  border: 1px solid #ddd;
}
</style>