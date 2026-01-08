<template>
  <div class="ubicacion-container">
    <div class="form-ubicacion-header">
      <h2>Registrar Ubicación del Proyecto</h2>
      <p>Complete los campos de la ubicación del Proyecto en estudio.</p>
    </div>
    
    <form @submit.prevent="guardarUbicacion" class="form-ubicacion">
      <div class="form-group">
        <FormularioRegion @update:data="regionData = $event" />
      </div>

      <div class="form-group">
        <FormularioComuna @update:data="comunaData = $event" />
      </div>
      
      <div class="form-group">
        <FormularioDireccion @update:data="direccionData = $event" />
      </div>
      
      <div class="form-group">
        <FormularioZonaTermica @update:data="zonaTermicaData = $event" />
      </div>

      <div v-if="loading" class="status-message loading">
        Guardando Ubicación...
      </div>
      <div v-if="error" class="status-message error">
        Error al guardar la ubicación: {{ error }}
      </div>

      <button type="submit" :disabled="loading">
        {{ loading ? 'Guardando...' : 'Registrar Ubicación' }}
      </button>
    </form>

    <div v-if="!hasUbicaciones" class="status-message no-data">
      No hay ubicaciones registradas.
    </div>
  </div> 
</template>

<script setup>
import { onMounted, computed } from 'vue';
import { storeToRefs } from 'pinia';

// Importar stores
import { useRegionStore } from '@/stores/Ubicacion/useRegionStore.js';
import { useComunaStore } from '@/stores/Ubicacion/useComunaStore.js';
import { useDireccionStore } from '@/stores/Ubicacion/useDireccionStore.js';
import { useZonaTermicaStore } from '@/stores/Ubicacion/useZonaTermicaStore.js';

// Importar componentes de formulario
import FormularioRegion from '@/components/Formularios/Ubicacion/FormularioRegion.vue';
import FormularioComuna from '@/components/Formularios/Ubicacion/FormularioComuna.vue';
import FormularioDireccion from '@/components/Formularios/Ubicacion/FormularioDireccion.vue';
import FormularioZonaTermica from '@/components/Formularios/Ubicacion/FormularioZonaTermica.vue';

// Instancias de stores
const regionStore = useRegionStore();
const comunaStore = useComunaStore();
const direccionStore = useDireccionStore();
const zonaTermicaStore = useZonaTermicaStore();

// Obtencion de propiedades reactivas del store
const { regiones, loading: loadingRegiones, error: errorRegiones } = storeToRefs(regionStore);
const { comunas, loading: loadingComunas, error: errorComunas } = storeToRefs(comunaStore);
const { direcciones, loading: loadingDirecciones, error: errorDirecciones } = storeToRefs(direccionStore);
const { zonas, loading: loadingZonas, error: errorZonas } = storeToRefs(zonaTermicaStore);

// Combinar estados de carga y error en propiedades computadas
const loading = computed(() => loadingRegiones.value || loadingComunas.value || loadingZonas.value || loadingDirecciones.value);
const error = computed(() => errorRegiones.value || errorComunas.value || errorZonas.value || errorDirecciones.value);

// Verificar si hay datos en alguno de los stores
const hasUbicaciones = computed(() => regiones.value.length > 0 || comunas.value.length > 0 || zonas.value.length > 0 || direcciones.value.length > 0);


//Carga todos los datos de ubicación al iniciar o después de un registro exitoso.
const cargarDatos = async () => {
  await Promise.all([
    regionStore.fetchRegiones(),
    comunaStore.fetchComunas(),    
    direccionStore.fetchDirecciones(),
    zonaTermicaStore.fetchZonas(),
  ]);
};

// Cargar los datos al montar el componente
onMounted(cargarDatos);
</script>

<style scoped>
.ubicacion-container {
  padding: 2rem;
  font-family: Arial, sans-serif;
  max-width: 900px;
  margin: auto;
}

.form-ubicacion-header {
  background-color: #006666;
  color: white;
  padding: 1.5rem 2rem;
  text-align: center;
  border-bottom: 2px solid #006666;
  margin-bottom: 2rem;
}

h2 {
  color: white;
  font-size: 2rem;
  margin: 0;
}

p {
  font-size: 1rem;
  margin-top: 0.5rem;
}

.form-ubicacion {
  background-color: #f8f9fa;
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid #006666;
  box-shadow: 0 12px 12px #006666;
  margin-bottom: 2rem;
}

.status-message {
  text-align: center;
  padding: 1rem;
  border-radius: 8px;
  font-weight: bold;
  margin-top: 2rem;
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
button[type="submit"] {
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
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
}

button[type="submit"]:hover {
  background-color: #1870e4;
}

</style>