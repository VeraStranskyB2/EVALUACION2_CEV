<template>
  <table class="table">
    <thead>
      <tr>
        <th>RUT</th>
        <th>Email</th>
        <th>Tipo</th>
        <th>Fecha Creación</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="usuario in usuarios" :key="usuario.rut_usuario">
        <td>{{ usuario.rut_usuario }}</td>
        <td>{{ usuario.email }}</td>
        <td>{{ usuario.tipo }}</td>
        <td>{{ formatFecha(usuario.fecha_creacion) }}</td>
        <td>
          <button @click="$emit('editar', usuario)">Editar</button>
          <button @click="$emit('eliminar', usuario.rut_usuario)">Eliminar</button>
        </td>
      </tr>
      <tr v-if="usuarios.length === 0">
        <td colspan="5">No hay usuarios registrados.</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  usuarios: {
    type: Array,
    default: () => []
  }
})

const emit = defineEmits(['editar', 'eliminar'])

function formatFecha(fecha) {
  if (!fecha) return ''
  return new Date(fecha).toLocaleDateString()
}
</script>

<style scoped>
.table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  padding: 8px;
  border: 1px solid #ccc;
  text-align: left;
}
button {
  margin-right: 5px;
  padding: 4px 10px;
  cursor: pointer;
}
</style>
