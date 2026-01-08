<template>
  <div class="tabla-direccion">
    <h2>Listado de Direcciones</h2>

    <table v-if="direcciones && direcciones.length">
      <thead>
        <tr>
          <th>ID</th>
          <th>Calle</th>
          <th>Número</th>
          <th>Complemento</th>
          <th>Latitud</th>
          <th>Longitud</th>
          <th>Comuna</th>
          <th>Zona Térmica</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="dir in direcciones" :key="dir.id_direccion">
          <td>{{ dir.id_direccion }}</td>
          <td>{{ dir.calle }}</td>
          <td>{{ dir.numero }}</td>
          <td>{{ dir.complemento || '—' }}</td>
          <td>{{ dir.latitutd }}</td>
          <td>{{ dir.longitud }}</td>
          <td>{{ dir.comuna?.nombre_comuna || 'N/A' }}</td>
          <td>{{ dir.zona_termica?.nombre_zona_termica || dir.zona_termica_id_zona_termica }}</td>
        </tr>
      </tbody>
    </table>
    <p v-else>No hay direcciones para mostrar.</p>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useDireccionStore } from '@/stores/Ubicacion/useDireccionStore.js';
import { storeToRefs } from 'pinia';

// Usa el store de Pinia
const direccionStore = useDireccionStore();

// Para usar propiedades del store como referencias reactivas en el template
const { direcciones, loading, error } = storeToRefs(direccionStore);


onMounted(() => {
  direccionStore.fetchDirecciones();
});
</script>

<style scoped>
.tabla-direccion {
  max-width: 900px;
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
