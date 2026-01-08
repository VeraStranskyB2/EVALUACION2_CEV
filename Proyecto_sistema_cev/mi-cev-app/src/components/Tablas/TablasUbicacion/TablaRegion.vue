<template>
  <div class="tabla-region">
    <h2>Lista de Regiones</h2>
    
    <table v-if="regionStore.regiones.length" border="1" cellspacing="0" cellpadding="8">
      <thead>
        <tr>
          <th>ID Región</th>
          <th>Nombre Región</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="region in regiones" :key="region.id_region">
          <td>{{ region.id_region }}</td>
          <td>{{ region.nombre_region }}</td>
          <td>
            <button @click="eliminar(region.id_region)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div v-else>No hay regiones para mostrar.</div>
    <div v-if="regionStore.error" class="error">{{ regionStore.error }}</div>
  </div>
</template>


<script setup>
import { onMounted } from 'vue'
import { useRegionStore } from '@/stores/Ubicacion/useRegionStore'


const regionStore = useRegionStore()

const eliminar = async (id_region) => {
  if (!confirm(`¿Confirma eliminar la región con ID: ${id_region}?`)) return
  await regionStore.eliminarRegion(id_region)
}

onMounted(() => {
  regionStore.fetchRegiones()
})


</script>

<style scoped>
.tabla-region {
  max-width: 800px;
  margin: 2rem auto;
  font-family: Arial, sans-serif;
}

h2 {
  text-align: center;
  color: #006666;
  margin-bottom: 1.5rem;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background-color: #006666;
  color: white;
  text-align: left;
}

td, th {
  padding: 8px;
  border: 1px solid #cccccc;
}

button {
  background-color: #cc3333;
  color: white;
  border: none;
  padding: 6px 12px;
  cursor: pointer;
  border-radius: 6px;
}

button:hover {
  background-color: #a62828;
}

.error {
  margin-top: 1rem;
  color: red;
  text-align: center;
}
</style>