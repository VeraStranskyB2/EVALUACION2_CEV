<template>
  <table class="table">
    <thead>
      <tr>
        <th>RUT Usuario</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Profesión</th>
        <th>Fecha Acreditación</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="evaluador in evaluadores" :key="evaluador.usuario_rut_usuario">
        <td>{{ evaluador.usuario_rut_usuario }}</td>
        <td>{{ evaluador.nombre_evaluador }}</td>
        <td>{{ evaluador.apellidos }}</td>
        <td>{{ evaluador.profesion || '-' }}</td>
        <td>{{ formatFecha(evaluador.fecha_acreditacion) }}</td>
        <td>
          <button @click="$emit('editar', evaluador)">Editar</button>
          <button @click="$emit('eliminar', evaluador.usuario_rut_usuario)">Eliminar</button>
        </td>
      </tr>
      <tr v-if="evaluadores.length === 0">
        <td colspan="6">No hay evaluadores registrados.</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  evaluadores: {
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
