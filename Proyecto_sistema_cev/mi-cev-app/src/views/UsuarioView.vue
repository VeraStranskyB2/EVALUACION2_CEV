<template>
  <section>
    <h1>Usuarios</h1>

    <div v-if="cargando">Cargando usuarios...</div>
    <div v-if="error" class="error">{{ error }}</div>

    <TablaUsuario
      v-if="!cargando && usuarios.length"
      :usuarios="usuarios"
      @editar="editarUsuario"
      @eliminar="handleEliminarUsuario"
    />

    <div v-if="!cargando && usuarios.length === 0">No hay usuarios para mostrar.</div>
  </section>
</template>

<script setup>
import { onMounted } from 'vue'
import { useUsuarioStore } from '@/stores/Usuarios/useUsuarioStore'
import TablaUsuario from '@/components/Tablas/TablasUsuarios/TablaUsuario.vue'

const usuarioStore = useUsuarioStore()
const { usuarios, cargando, error, fetchUsuarios, eliminarUsuario: eliminarUsuarioFromStore } = usuarioStore

onMounted(() => {
  fetchUsuarios()
})

function editarUsuario(usuario) {
  alert(`Función editar para usuario: ${usuario.rut_usuario} (implementa formulario)`)
}

async function handleEliminarUsuario(rut) {
  if (confirm('¿Confirma eliminar este usuario?')) {
    try {
      await eliminarUsuarioFromStore(rut)
      alert('Usuario eliminado correctamente')
    } catch (e) {
      alert(`Error al eliminar: ${e.message || e}`)
    }
  }
}
</script>

<style scoped>
.error {
  color: red;
  margin: 12px 0;
}
</style>
