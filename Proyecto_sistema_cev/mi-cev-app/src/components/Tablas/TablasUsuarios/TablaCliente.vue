<template>
  <table class="table">
    <thead>
      <tr>
        <th>RUT Usuario</th>
        <th>Razón Social</th>
        <th>Representante Legal</th>
        <th>Giro Comercial</th>
        <th>Fecha Registro</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="cliente in clientes" :key="cliente.usuario_rut_usuario">
        <td>{{ cliente.usuario_rut_usuario }}</td>
        <td>{{ cliente.razon_social }}</td>
        <td>{{ cliente.representante_legal || '-' }}</td>
        <td>{{ cliente.giro_comercial || '-' }}</td>
        <td>{{ formatFecha(cliente.fecha_registro) }}</td>
        <td>
          <button @click="$emit('editar', cliente)">Editar</button>
          <button @click="$emit('eliminar', cliente.usuario_rut_usuario)">Eliminar</button>
        </td>
      </tr>
      <tr v-if="clientes.length === 0">
        <td colspan="6">No hay clientes registrados.</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  clientes: {
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
