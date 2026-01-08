<template>
  <div>
    <h2>Registrar Direccion</h2>    
    <FormularioDireccion @registro-exitoso="refrescarDireccion"/>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useDireccionStore } from '@/stores/Ubicacion/useDireccionStore.js'
import FormularioDireccion from '@/components/Formularios/Ubicacion/FormularioDireccion.vue'


//Instancia del Store
const store = useDireccionStore()



//Función para recargar las regiones
const refrescarDirecciones = async () => {
  await store.fetchDirecciones()
}

//Llamar al cargar la vista
onMounted(refrescarDirecciones)

</script>
