<template>
  <div class="tabla-container">
    <div v-if="cargando" class="loading-message">Cargando solicitudes...</div>
    <div v-else-if="error" class="error-message">Error: {{ error }}</div>
    <div v-else-if="solicitudes.length === 0" class="sin-datos">No hay solicitudes pendientes.</div>
    <table v-else>
      <thead>
        <tr>
          <th>ID Solicitud</th>
          <th>Cliente</th>
          <th>Estado</th>
          <th>Fecha de Creación</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="solicitud in solicitudes" :key="solicitud.id_solicitud">
          <td>{{ solicitud.id_solicitud }}</td>
          <td>{{ solicitud.nombre_cliente }}</td>
          <td>
            <span :class="['estado-badge', `estado-${solicitud.estado_actual.nombre_estado.toLowerCase().replace(' ', '-')}`]">
              {{ solicitud.estado_actual.nombre_estado }}
            </span>
          </td>
          <td>{{ new Date(solicitud.fecha_creacion).toLocaleDateString() }}</td>
          <td>
            <button class="action-btn ver" @click="$emit('ver-detalle', solicitud.id_solicitud)">Ver</button>
            <button class="action-btn aceptar" @click="$emit('aceptar', solicitud.id_solicitud)">Aceptar</button>
            <button class="action-btn rechazar" @click="$emit('rechazar', solicitud.id_solicitud)">Rechazar</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';

defineProps({
  solicitudes: { type: Array, required: true },
  cargando: { type: Boolean, default: false },
  error: { type: String, default: null },
});

defineEmits(['aceptar', 'rechazar', 'ver-detalle']);
</script>

<style scoped>

</style>