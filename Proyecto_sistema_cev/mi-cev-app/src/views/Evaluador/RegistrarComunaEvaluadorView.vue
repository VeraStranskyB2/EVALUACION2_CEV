<template>
  <div>
    <h2></h2>
    <FormularioComuna @registro-exitoso="refrescarComunas"/>

    

    <h2 class="section-title"></h2>
    
    <div v-if="loading" class="status-message loading">
      Cargando comunas...
    </div>
    <div v-else-if="error" class="status-message error">
      Error al cargar las comunas: {{ error }}
    </div>
    <div v-else-if="comunas.length === 0" class="status-message no-data">
      No hay comunas registradas.
    </div>
    
    <TablaComuna v-else :comunas="comunas" />
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { storeToRefs } from 'pinia'

// Stores
import { useComunaStore } from '@/stores/Ubicacion/useComunaStore.js'


// Componentes
import FormularioComuna from '@/components/Formularios/Ubicacion/FormularioComuna.vue'
import TablaComuna from '@/components/Tablas/TablasUbicacion/TablaComuna.vue';

// Instancia de stores

const comunaStore = useComunaStore()

// Desestructura con storeToRefs para mantener reactividad

const { comunas } = storeToRefs(comunaStore)



const refrescarComunas = async () => {
  await comunaStore.fetchComunas()
}

// Inicialización al montar
onMounted(async () => {
    await refrescarComunas()
})
</script>
