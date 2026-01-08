<template>
  <form @submit.prevent="submit" class="form-estado">
    <h3>{{ estadoActual.id_estado ? 'Editar Estado' : 'Nuevo Estado' }}</h3>

    <label for="nombre_estado">Nombre</label>
    <input id="nombre_estado" v-model="estadoActual.nombre_estado" required maxlength="50" />

    <label for="descripcion">Descripción</label>
    <textarea id="descripcion" v-model="estadoActual.descripcion" maxlength="255"></textarea>

    <label for="color">Color</label>
    <input id="color" type="color" v-model="estadoActual.color" />

    <label>
      <input type="checkbox" v-model="estadoActual.activo" />
      Activo
    </label>

    <button type="submit">Guardar</button>
    <button type="button" @click="cancelar">Cancelar</button>
  </form>
</template>

<script setup>
import { useClienteStore } from '@/stores/Usuarios/useClienteStore'
import { reactive, toRefs } from 'vue'

const store = useClienteStore()
const { estadoActual } = toRefs(store)

function submit() {
  store.guardarEstado(estadoActual.value).then(() => {
    store.fetchEstados()
    store.resetEstadoActual()
  })
}

function cancelar() {
  store.resetEstadoActual()
}
</script>

<style scoped>

.form-estado {
  max-width: 400px;
  margin: 1rem auto;
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}
</style>
