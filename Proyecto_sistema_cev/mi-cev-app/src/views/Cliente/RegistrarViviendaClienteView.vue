<template>
  <div class="vivienda-container">
        <!-- El formulario emite un evento al crear una vivienda con éxito -->
    <FormularioVivienda @registro-exitoso="cargarViviendas" />

    <div class="tabla-container">
      <h3 class="documents-section-title">Listado de Viviendas</h3>

      <!-- Se muestran estados de carga y error antes de la tabla -->
      <div v-if="loading" class="loading-message">
        Cargando viviendas...
      </div>
      <div v-else-if="error" class="error-message">
        Error: {{ error }}
      </div>
      <TablaVivienda
        v-else
        :viviendas="viviendas"
        :cargando="loading"
        :error="error"
        @eliminar="eliminarVivienda"
      />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useViviendaStore } from '@/stores/ProyectoVivienda/useViviendaStore';
import FormularioVivienda from '@/components/Formularios/ProyectoVivienda/FormularioVivienda.vue';
import TablaVivienda from '@/components/Tablas/TablasProyectoVivienda/TablaVivienda.vue';
import { storeToRefs } from 'pinia';

//Stores
const viviendaStore = useViviendaStore();

// Obtencion de propiedades reactivas del store
const { viviendas, loading, error } = storeToRefs(viviendaStore);

// Carga las viviendas al iniciar o después de un evento.

const cargarViviendas = async () => {
  await viviendaStore.fetchTodasLasViviendas();
};

// Elimina una vivienda capturando el evento de la tabla.
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

// Al montar el componente, carga las viviendas
onMounted(cargarViviendas);
</script>

<style scoped>
.vivienda-container {
  padding-top: 0;
  max-width: 900px;
  margin: auto;
  font-family: 'Inter', sans-serif;
  color: #333;
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
  color: #555;
  font-size: 1.1rem;
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
