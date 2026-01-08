<template>
  <div>
    <button @click="crearNuevo">Nuevo Estado</button>

    <div v-if="store.cargando">Cargando...</div>
    <div v-if="store.error">{{ store.error }}</div>

    <FormularioEstado 
      v-if="mostrarFormulario" 
      @formulario-cerrado="manejarCierreFormulario" 
    />

    <table v-if="!store.cargando && store.estados.length">
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Descripción</th>
          <th>Color</th>
          <th>Activo</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="estado in store.estados" :key="estado.id_estado">
          <td>{{ estado.nombre_estado }}</td>
          <td>{{ estado.descripcion }}</td>
          <td>
            <div :style="{ backgroundColor: estado.color, width: '30px', height: '20px' }"></div>
          </td>
          <td>{{ estado.activo ? 'Sí' : 'No' }}</td>
          <td>
            <button @click="editarEstado(estado)">Editar</button>
            <button @click="eliminarEstado(estado.id_estado)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="!store.cargando && !store.estados.length">No hay estados registrados.</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useClienteStore } from '@/stores/Usuarios/useClienteStore'
import FormularioEstado from '@/components/FormularioEstado.vue'

const store = useClienteStore()
const mostrarFormulario = ref(false)

onMounted(() => {
  store.fetchEstados()
})

function crearNuevo() {
  store.resetEstadoActual()
  mostrarFormulario.value = true
}

function editarEstado(estado) {
  store.setEstadoActual(estado)
  mostrarFormulario.value = true
}

async function eliminarEstado(id) {
  if (confirm('¿Está seguro de eliminar este estado?')) {
    try {
      await store.eliminarEstado(id)
      await store.fetchEstados()
      alert('Estado eliminado con éxito.')
    } catch (err) {
      alert('Error al eliminar el estado: ' + (err.message || err))
    }
  }
}

function manejarCierreFormulario() {
  mostrarFormulario.value = false
  store.fetchEstados()
}
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

button {
  margin-right: 5px;
  padding: 5px 10px;
  cursor: pointer;
}
</style>
