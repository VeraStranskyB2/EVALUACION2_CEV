<template>
  <div class="admin-usuarios-container">
    <h1 class="admin-title">Administración de Usuarios</h1>

    <div class="tabs-container">
      <button 
        :class="{ active: vistaActual === 'usuarios' }" 
        @click="vistaActual = 'usuarios'">
        Usuarios
      </button>
      <button 
        :class="{ active: vistaActual === 'clientes' }" 
        @click="vistaActual = 'clientes'">
        Clientes
      </button>
      <button 
        :class="{ active: vistaActual === 'evaluadores' }" 
        @click="vistaActual = 'evaluadores'">
        Evaluadores
      </button>
    </div>

    <hr class="divider">

    <section v-if="vistaActual === 'usuarios'">
      <h2>Gestión de Usuarios</h2>
      <div v-if="cargandoUsuarios" class="status-message loading">Cargando usuarios...</div>
      <div v-if="errorUsuarios" class="status-message error">{{ errorUsuarios }}</div>
      <TablaUsuario
        v-if="!cargandoUsuarios && usuarios.length"
        :usuarios="usuarios"
        @editar="editarUsuario"
        @eliminar="handleEliminarUsuario"
      />
      <div v-if="!cargandoUsuarios && usuarios.length === 0" class="status-message no-data">
        No hay usuarios para mostrar.
      </div>
    </section>

    <section v-if="vistaActual === 'clientes'">
      <h2>Gestión de Clientes</h2>
      <div v-if="cargandoClientes" class="status-message loading">Cargando clientes...</div>
      <div v-if="errorClientes" class="status-message error">{{ errorClientes }}</div>
      <TablaCliente
        v-if="!cargandoClientes && clientes.length"
        :clientes="clientes"
        @editar="editarCliente"
        @eliminar="handleEliminarCliente"
      />
      <div v-if="!cargandoClientes && clientes.length === 0" class="status-message no-data">
        No hay clientes para mostrar.
      </div>
    </section>

    <section v-if="vistaActual === 'evaluadores'">
      <h2>Gestión de Evaluadores</h2>
      <div v-if="cargandoEvaluadores" class="status-message loading">Cargando evaluadores...</div>
      <div v-if="errorEvaluadores" class="status-message error">{{ errorEvaluadores }}</div>
      <TablaEvaluador
        v-if="!cargandoEvaluadores && evaluadores.length"
        :evaluadores="evaluadores"
        @editar="editarEvaluador"
        @eliminar="manejareliminarEvaluador"
      />
      <div v-if="!cargandoEvaluadores && evaluadores.length === 0" class="status-message no-data">
        No hay evaluadores para mostrar.
      </div>
    </section>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue';
import { useUsuarioStore } from '@/stores/Usuarios/useUsuarioStore';
import { useClienteStore } from '@/stores/Usuarios/useClienteStore';
import { useEvaluadorStore } from '@/stores/Usuarios/useEvaluadorStore';
import { storeToRefs } from 'pinia';

// Componentes de tabla
import TablaUsuario from '@/components/Tablas/TablasUsuarios/TablaUsuario.vue';
import TablaCliente from '@/components/Tablas/TablasUsuarios/TablaCliente.vue';
import TablaEvaluador from '@/components/Tablas/TablasUsuarios/TablaEvaluador.vue';

// Estado de la vista actual
const vistaActual = ref('usuarios');

// Instancias de los stores y sus propiedades
const usuarioStore = useUsuarioStore();
const clienteStore = useClienteStore();
const evaluadorStore = useEvaluadorStore();

const { usuarios, cargando: cargandoUsuarios, error: errorUsuarios } = storeToRefs(usuarioStore);
const { clientes, cargando: cargandoClientes, error: errorClientes } = storeToRefs(clienteStore);
const { evaluadores, cargando: cargandoEvaluadores, error: errorEvaluadores } = storeToRefs(evaluadorStore);

// Cargar todos los datos al montar el componente
onMounted(() => {
  usuarioStore.fetchUsuarios();
  clienteStore.fetchClientes();
  evaluadorStore.fetchEvaluadores();
});

// Funciones para editar y eliminar usuarios
function editarUsuario(usuario) {
  alert(`Función editar para usuario: ${usuario.rut_usuario}`);
}

async function handleEliminarUsuario(rut) {
  if (confirm('¿Confirma eliminar este usuario?')) {
    try {
      await usuarioStore.eliminarUsuario(rut);
      alert('Usuario eliminado correctamente');
    } catch (e) {
      alert(`Error al eliminar: ${e.message || e}`);
    }
  }
}

// Funciones para editar y eliminar clientes
function editarCliente(cliente) {
  alert(`Editar cliente ${cliente.usuario_rut_usuario}`);
}

async function handleEliminarCliente(rut) {
  if (confirm('¿Confirmar eliminar cliente?')) {
    try {
      await clienteStore.eliminarCliente(rut);
      alert('Cliente eliminado exitosamente');
    } catch (e) {
      alert(`Error al eliminar: ${e.message || e}`);
    }
  }
}

// Funciones para editar y eliminar evaluadores
function editarEvaluador(evaluador) {
  alert(`Función editar para evaluador: ${evaluador.usuario_rut_usuario}`);
}

async function manejareliminarEvaluador(rut) {
  if (confirm('¿Confirma eliminar este evaluador?')) {
    try {
      await evaluadorStore.eliminarEvaluador(rut);
      alert('Evaluador eliminado correctamente');
    } catch (e) {
      alert(`Error al eliminar: ${e.message || e}`);
    }
  }
}
</script>

<style scoped>
.admin-usuarios-container {
  padding: 2rem;
  max-width: 1200px;
  margin: auto;
  font-family: Arial, sans-serif;
}

.admin-title {
  text-align: center;
  color: #006666;
  font-size: 2rem;
  border-bottom: 2px solid #006666;
  padding-bottom: 0.5rem;
  margin-bottom: 2rem;
}

.tabs-container {
  display: flex;
  justify-content: center;
  margin-bottom: 2rem;
  gap: 1rem;
}

.tabs-container button {
  padding: 0.75rem 1.5rem;
  border: 1px solid #006666;
  background-color: transparent;
  color: #006666;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.tabs-container button.active {
  background-color: #006666;
  color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.tabs-container button:hover {
  background-color: #e0f2f1;
}

.tabs-container button.active:hover {
  background-color: #006666;
}

.divider {
  border: 0;
  height: 1px;
  background: #ccc;
  margin: 2rem 0;
}

h2 {
  color: #006666;
  margin-bottom: 1.5rem;
  text-align: center;
}

.status-message {
  text-align: center;
  padding: 1rem;
  border-radius: 8px;
  font-weight: bold;
  margin-bottom: 1.5rem;
}

.loading {
  background-color: #e0f2f1;
  color: #006666;
}

.error {
  background-color: #f2dede;
  color: #d9534f;
}

.no-data {
  background-color: #f8f9fa;
  color: #6c757d;
  border: 1px solid #ddd;
}
</style>