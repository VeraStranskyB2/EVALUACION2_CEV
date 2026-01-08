<template>
  <div class="tabla-evaluaciones">
    <h3 class="evaluaciones-section-title">Evaluaciones Realizadas</h3>
    <div v-if="cargando" class="loading-message">Cargando Evaluacion...</div>
    <div v-else-if="error" class="error-message">Error: {{ error }}</div>
    <table v-else-if="evaluaciones && evaluaciones.length">  
      <thead>
        <tr>
          <th>ID Evaluación</th>
          <th>ID Proyecto</th>
          <th>Nombre Proyecto</th>
          <th>Fecha Evaluación</th>
          <th>Estado</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="evaluacion in evaluaciones" :key="evaluacion.id_evaluacion">
          <td>{{ evaluacion.id_evaluacion }}</td>
          <td>{{ evaluacion.proyecto_id_proyecto }}</td>
          <td>{{ evaluacion.nombre_proyecto }}</td>
          <td>{{ new Date(evaluacion.fecha_evaluacion).toLocaleDateString() }}</td>
          <td>
            <span :class="['estado-badge', `estado-${evaluacion.estado_evaluacion.toLowerCase().replace(' ', '-')}`]">
              {{ evaluacion.estado_evaluacion }}
            </span>
          </td>
          <td>
            <button class="ver" @click="verDetalles(evaluacion.id_evaluacion)" title="Ver detalles de la evaluación">
              <i class='bx bxs-search'></i>
            </button>
            <button class="continuar" @click="continuarEvaluacion(evaluacion.id_evaluacion)" title="Continuar con la evaluación">
              <i class='bx bxs-right-arrow-alt'></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <div v-else class="sin-evaluaciones">No hay evaluaciobes para mostrar.</div>    
  </div>
</template>

<script setup>

import { defineProps, defineEmits } from 'vue';
import { useRouter } from 'vue-router';
defineProps({
  evaluaciones: { type: Array, default: () => [] },
  cargando: { type: Boolean, default: false },
  error: { type: String, default: null }
});
const emit = defineEmits(['ver-detalles', 'continuar-evaluacion']);
const router = useRouter();
const verDetalles = (id) => {
  emit('ver-detalles', id);
};
const continuarEvaluacion = (id) => {
  router.push({ name: 'evaluador-sistema-calefaccion', params: { id_evaluacion: id } });
};
</script>

<style scoped>

.tabla-evaluaciones {
  padding: 2rem;
  background-color: #f8f9fa;
  border-radius: 8px;
  border: 1px solid #006666; 
  box-shadow: 0 12px 12px #006666;
}
.evaluaciones-section-title {
  color: #006666;
  font-size: 1.5rem;
  text-align: center;
  margin-bottom: 1.5rem;
}
.loading-message, .error-message, .sin-datos {
  text-align: center;
  margin: 1rem 0;
  padding: 1rem;
  border-radius: 4px;
}
.loading-message {
  color: #006666;
  font-style: italic;
}
.error-message {
  color: #d9534f;
  background-color: #f2dede;
  border: 1px solid #ebccd1;
}
.sin-datos {
  color: #888;
  background-color: #f5f5f5;
  border: 1px solid #ddd;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  padding: 12px;
  text-align: left;
  border: 1px solid #ddd;
}
thead th {
  background-color: #006666;
  color: white;
  text-align: center;
  white-space: nowrap;
}
tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}
.estado-badge {
  display: inline-block;
  padding: 0.3rem 0.6rem;
  border-radius: 12px;
  font-weight: bold;
  font-size: 0.8em;
  color: white;
  text-transform: capitalize;
  white-space: nowrap;
}
.estado-pendiente { background-color: #f0ad4e; }
.estado-en-proceso { background-color: #5bc0de; }
.estado-completada { background-color: #5cb85c; }
.estado-rechazada { background-color: #d9534f; }
td button {
  border: none;
  padding: 8px 12px;
  cursor: pointer;
  border-radius: 4px;
  margin-right: 5px;
  transition: background-color 0.3s ease;
  color: white;
}
.ver { background-color: #006666; }
.ver:hover { background-color: #1870e4 }
.continuar { background-color: #006666; }
.continuar:hover { background-color: #004d4d; }
</style>