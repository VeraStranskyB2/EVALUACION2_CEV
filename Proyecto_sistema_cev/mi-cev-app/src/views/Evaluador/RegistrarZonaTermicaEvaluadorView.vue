<template>
  <div>
    <h2>Registrar Zona Térmica</h2>    
    <FormularioZonaTermica @registro-exitoso="refrescarZonasTermicas" />
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useZonaTermicaStore } from '@/stores/Ubicacion/useZonaTermicaStore.js'
import FormularioZonaTermica from '@/components/Formularios/Ubicacion/FormularioZonaTermica.vue'

// Instancia del Store
const store = useZonaTermicaStore()

// Función para recargar las zonas térmicas
const refrescarZonasTermicas = async () => {
  await store.fetchZonas()
}

// Llamar al cargar la vista
onMounted(refrescarZonasTermicas)
</script>
