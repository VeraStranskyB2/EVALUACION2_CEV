<template>
  <div>  
     
    <FormularioRegion @registro-exitoso="refrescarRegiones" />
   
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRegionStore } from '@/stores/Ubicacion/useRegionStore.js'
import FormularioRegion from '@/components/Formularios/Ubicacion/FormularioRegion.vue'


//Instancia del Store
const store = useRegionStore()



//Función para recargar las regiones
const refrescarRegiones = async () => {
  await store.fetchRegiones()
}

//Llamar al cargar la vista
onMounted(refrescarRegiones)
</script>
