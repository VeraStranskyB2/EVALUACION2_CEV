<template>
  <div class="tabla-solicitudes-container">
    <div v-if="cargando" class="mensaje-estado">Cargando...</div>
    <div v-else-if="error" class="mensaje-estado error-mensaje">Error: {{ error }}</div>
    
    <table v-else-if="solicitudes && solicitudes.length">
      <thead>
        <tr>
          <th>Id Solicitud</th>
          <th>Fecha de Ingreso</th>
          <th>Tipo</th>
          <th>Permiso de Edificación</th>
          <th>Recepción Final</th>
          <th>Nombre del Proyecto</th>
          <th>Estado</th>
          <th>Rut Cliente</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="solicitud in solicitudes" :key="solicitud.id_solicitud">
          <td>{{ solicitud.id_solicitud }}</td>
          <td>{{ solicitud.fecha_ingreso }}</td>
          <td>{{ solicitud.tipo }}</td>
          <td>{{ solicitud.permiso_edificacion_numero }}/{{ solicitud.permiso_edificacion_anio }}</td>
          <td>{{ solicitud.recepcion_final_numero }}/{{ solicitud.recepcion_final_anio }}</td>
          <td>{{ solicitud.proyecto_nombre || 'N/A' }}</td>
          <td>{{ solicitud.estado_nombre || 'N/A' }}</td>
          <td>{{ solicitud.cliente_rut_usuario }}</td>
          <td>
            <button class="editar" @click="$emit('editar', solicitud)">
              <i class='bx bxs-pencil'></i>
            </button>
            <button class="eliminar" @click="$emit('eliminar', solicitud.id_solicitud)">
              <i class='bx bxs-trash'></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    
    <!--
    <div v-else class="mensaje-estado sin-solicitudes-mensaje">
      No hay solicitudes para mostrar.
    </div>
    -->
    
  </div>
</template>

<script setup>
defineProps({
  solicitudes: {
    type: Array,
    default: () => []
  },
  cargando: {
    type: Boolean,
    default: false
  },
  error: {
    type: String,
    default: null
  }
});

defineEmits(['editar', 'eliminar']);
</script>

<style scoped>
.tabla-solicitudes-container {
  max-width: 900px;
  margin: 2rem auto;
  font-family: Arial, sans-serif;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background-color: #006666;
  color: white;
  text-align: left;
}

td, th {
  padding: 8px;
  border: 1px solid #006666;
}

.mensaje-estado {
  text-align: center;
  font-size: 1.2rem;
  padding: 1rem;
}

.error-mensaje {
  color: #dc3545;
  font-weight: bold;
}

.sin-solicitudes-mensaje {
  color: #6c757d;
}

.editar, .eliminar {
  border: none;
  padding: 6px 10px;
  cursor: pointer;
  border-radius: 6px;
  margin: 0 2px;
  transition: background-color 0.3s ease;
}

.editar {
  background-color: #006666;
}

.editar:hover {
  background-color: #1870e4;
}

.eliminar {
  background-color: #dc3545;
}

.eliminar:hover {
  background-color: #c82333;
}
</style>