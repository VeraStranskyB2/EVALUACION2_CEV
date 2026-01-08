<template>
  <div class="proyecto-cliente-container">
    
    <!-- Formulario de registro de proyecto -->
    <FormularioProyecto @proyecto-creado="cargarProyectos" />
    
    <div class="tabla-container">
      <h3 class="documents-section-title">Mis Proyectos Registrados</h3>

      <!-- Estado de carga -->
      <div v-if="loading" class="loading-message">
        Cargando proyectos...
      </div>

      <!-- Error en la carga -->
      <div v-else-if="error" class="error-message">
        Error: {{ error }}
      </div>

      <!-- Tabla de proyectos -->
      <TablaProyecto
        v-else
        :proyectos="proyectos"
        @editar="editarProyecto"
        @eliminar="eliminarProyecto"
      />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useProyectoStore } from '@/stores/ProyectoVivienda/useProyectoStore'
import { useAuthStore } from '@/stores/Usuarios/useAuthStore'
import FormularioProyecto from '@/components/Formularios/ProyectoVivienda/FormularioProyecto.vue'
import TablaProyecto from '@/components/Tablas/TablasProyectoVivienda/TablaProyecto.vue'
import { storeToRefs } from 'pinia'

//Stores
const proyectoStore = useProyectoStore()
const authStore = useAuthStore()

// Estado reactivo del store
const { proyectos, loading, error } = storeToRefs(proyectoStore)

// Obtener proyectos por cliente
const cargarProyectos = async () => {
  if (!authStore.rut_usuario) {
    console.warn('No se encontró rut_usuario en authStore')
    return
  }
  await proyectoStore.fetchProyectosPorCliente(authStore.rut_usuario)
}

// Handlers de acciones
const editarProyecto = (proyecto) => {
  console.log('Editar proyecto:', proyecto)  
}

const eliminarProyecto = async (idProyecto) => {
  await proyectoStore.eliminarProyecto(idProyecto)
}

// Al montar, cargar proyectos
onMounted(cargarProyectos)
</script>

<style scoped>
.proyecto-cliente-container {
  padding-top: 0;
  max-width: 900px;
  margin: auto;
  font-family: Arial, sans-serif;
  color: #333;
}

.tabla-container {
  margin-top: 2rem;
  padding: 1.5rem;
  background-color: #f8f9fa;
  border-radius: 12px;
  border: 1px solid #ddd;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.documents-section-title {
  color: #006666;
  font-size: 1.8rem;
  text-align: center;
  margin-bottom: 1.5rem;
}

.loading-message,
.error-message {
  text-align: center;
  font-size: 1.2rem;
  padding: 2rem;
  color: #555;
}

.error-message {
  color: #d9534f;
}
</style>
