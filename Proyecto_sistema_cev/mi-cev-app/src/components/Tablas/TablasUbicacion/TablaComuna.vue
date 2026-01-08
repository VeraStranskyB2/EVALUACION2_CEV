<template>
  <div class="tabla-comuna">
    <h2>Listado de Proyectos por Comunas</h2>

    <table  v-if="comunas && comunas.length">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre Comuna</th>
          <th>Región</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="comuna in comunas" :key="comuna.id_comuna">
          <td>{{ comuna.id_comuna }}</td>
          <td>{{ comuna.nombre_comuna }}</td>
          <td>{{ comuna.region?.nombre_region || 'Sin región' }}</td>
        </tr>
      </tbody>
    </table>
    <p v-else-if="loading">Cargando comunas...</p>
    <p v-else>No hay comunas para mostrar.</p>
    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useComunaStore } from '@/stores/Ubicacion/useComunaStore'


const comunaStore = useComunaStore()

const eliminar = async (id_comuna) => {
  if (!confirm(`¿Confirma eliminar la comuna con ID: ${id_comuna}?`)) return
  await comunaStore.eliminarComuna(id_comuna)
}

onMounted(() => {
  comunaStore.fetchComunas()
})

</script>

<style scoped>
.tabla-comuna {
  max-width: 800px;
  margin: auto;
  margin-top: 2rem;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
}

th,
td {
  padding: 0.75rem;
  border: 1px solid #ccc;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}
</style>
