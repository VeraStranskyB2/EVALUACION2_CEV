<template>
  <div class="proyecto-evaluador-container">

    <div class="tabla-container">
      <h3 class="documents-section-title">Proyectos Disponibles para Evaluar</h3>

      <div v-if="loading" class="loading-message">Cargando proyectos...</div>
      <div v-else-if="error" class="error-message">Error: {{ error }}</div>
      
      <TablaProyecto
        v-else
        :proyectos="proyectos"
        rol="evaluador"
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
import TablaProyecto from '@/components/Tablas/TablasProyectoVivienda/TablaProyecto.vue'
import { storeToRefs } from 'pinia'

const proyectoStore = useProyectoStore()
const authStore = useAuthStore()

const { proyectos, loading, error } = storeToRefs(proyectoStore)

// Cargar proyectos asignados al evaluador
const cargarProyectosEvaluador = async () => {
  if (!authStore.rut_usuario) return
  await proyectoStore.fetchProyectos()  
}

// Handlers de acciones editar y eliminar
const editarProyecto = (proyecto) => {
  console.log('Editar proyecto:', proyecto)
}

const eliminarProyecto = async (idProyecto) => {
  await proyectoStore.eliminarProyecto(idProyecto)
}

onMounted(cargarProyectosEvaluador)
</script>

<style scoped>
.proyecto-evaluador-container {
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