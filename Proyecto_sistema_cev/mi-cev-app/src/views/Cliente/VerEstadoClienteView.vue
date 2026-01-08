<template>
  <section>
    <h2>Gestión de Estados de Cliente</h2>
    <FormularioEstado />
    <TablaEstadosComponent />
  </section>
</template>

<script setup>
import FormularioEstado from '@/components/Proceso/FormularioEstado.vue'
 
</script>

<style scoped>
h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}
</style>
