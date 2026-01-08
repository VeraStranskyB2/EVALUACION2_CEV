<template>
  <section class="tabla-viviendas">
    <div v-if="cargando" class="estado-mensaje">Cargando viviendas...</div>
    <div v-else-if="error" class="estado-mensaje error-mensaje">
      Error al cargar las viviendas: {{ error }}
    </div>
    <div v-else-if="viviendas && viviendas.length > 0">
      <table>
        <thead>
          <tr>
            <th>Rol Vivienda</th>
            <th>ID Dirección</th>
            <th>Tipo</th>
            <th>Estado</th>
            <th>Lote</th>
            <th>Manzana</th>
            <th>Edificio</th>
            <th>Piso</th>
            <th>Unidad</th>
            <th>Orientación</th>
            <th>Materialidad</th>
            <th>ID Proyecto</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="vivienda in viviendas" :key="vivienda.rol_vivienda">
            <td>{{ vivienda.rol_vivienda }}</td>
            <td>{{ vivienda.id_direccion }}</td>
            <td>{{ vivienda.tipo }}</td>
            <td>{{ vivienda.estado }}</td>
            <td>{{ vivienda.lote || '-' }}</td>
            <td>{{ vivienda.manzana || '-' }}</td>
            <td>{{ vivienda.edificio || '-' }}</td>
            <td>{{ vivienda.piso !== null ? vivienda.piso : '-' }}</td>
            <td>{{ vivienda.unidad || '-' }}</td>
            <td>{{ vivienda.orientacion || '-' }}</td>
            <td>{{ vivienda.materialidad || '-' }}</td>
            <td>{{ vivienda.proyecto_id_proyecto }}</td>
            <td class="acciones">
              <button class="editar" @click="$emit('editar', vivienda)">
                <i class='bx bxs-pencil'></i>
              </button>
              <button class="eliminar" @click="$emit('eliminar', vivienda.rol_vivienda)">
                <i class='bx bxs-trash'></i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else class="estado-mensaje sin-viviendas">No hay viviendas para mostrar.</div>
  </section>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';

defineProps({
  viviendas: {
    type: Array,
    default: () => []
  },
  cargando: {
    type: Boolean,
    default: false
  },
  error: {
    type: String,
    default: null
  }
});

// El componente emite un evento 'eliminar' para que el padre maneje la lógica
defineEmits(['editar', 'eliminar']);
</script>

<style scoped>
.tabla-viviendas {
  max-width: 900px;
  margin: 2rem auto;
  font-family: Arial, sans-serif;
  overflow-x: auto;
}

.estado-mensaje {
  text-align: center;
  font-size: 1.2rem;
  padding: 2rem;
  background-color: #f8f9fa;
  border-radius: 8px;
  border: 1px solid #ddd;
}

.error-mensaje {
  color: #d9534f;
  border-color: #d9534f;
}

.sin-viviendas {
  color: #555;
  border-color: #ccc;
}

table {
  width: 100%;
  border-collapse: collapse;
}

thead {
  background-color: #006666;
  color: white;
}

th {
  background-color: #006666;
  color: white;
  text-align: center;
}

td, th {
  padding: 10px;
  border: 1px solid #006666;
  white-space: nowrap;
}

td a {
  color: #006666;
  text-decoration: none;
}

td a:hover {
  text-decoration: underline;
}

tbody tr:nth-child(even) {
  background-color: #f8f9fa;
}

tbody tr:hover {
  background-color: #eaf6fc;
}

.acciones {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
}

.acciones button {
  border: none;
  padding: 6px 10px;
  cursor: pointer;
  border-radius: 6px;
  margin: 0 2px;
  transition: background-color 0.3s ease;
}

.acciones button i {
  color: white;
}

.editar {
  background-color: #006666;
}

.editar:hover {
  background-color: #1870e4;
}

.eliminar {
  background-color: #dc3545;
}

.eliminar:hover {
  background-color: #c82333;
}
</style>
