<template>
  <div class="tabla-documentos">
    <div v-if="cargando">Cargando Documento...</div>
    <div v-else-if="error" class="error">Error: {{ error }}</div>
    <table v-else-if="documentos && documentos.length">
      <thead>
        <tr>
          <th>Id Documento</th>
          <th>Tipo</th>
          <th>Comentarios</th>
          <th>Fecha de Ingreso</th>
          <th>Id Solicitud</th>
          <th>Documento</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="documento in documentos" :key="documento.id_documento">
          <td>{{ documento.id_documento }}</td>
          <td>{{ documento.tipo }}</td>
          <td>{{ documento.comentarios }}</td>
          <td>{{ documento.fecha_ingreso }}</td>
          <td>{{ documento.solicitud_id_solicitud }}</td>
          <td>
            <a :href="documento.url" target="_blank">Ver Documento</a>
          </td>
          <td>
            <button class="editar" @click="$emit('editar', documento)">
              <i class='bx bxs-pencil'></i>
            </button>
            <button class="eliminar" @click="$emit('eliminar', documento.id_documento)">
              <i class='bx bxs-trash'></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <div v-else class="sin-documentos">No hay documentos para mostrar.</div>
    
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';

// Definición de las props que el componente recibirá
defineProps({
  documentos: {
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

// Definición de los eventos que el componente puede emitir
defineEmits(['editar', 'eliminar']);
</script>

<style scoped>
.tabla-documentos {
  max-width: 900px;
  margin: 2rem auto;
  font-family: Arial, sans-serif;
}

h2 {
  text-align: center;
  color: #006666;
  margin-bottom: 1.5rem;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background-color: #006666;
  color: white;
  text-align: center;
}

td, th {
  padding: 10px;
  border: 1px solid #006666;
}

td a {
  color: #006666;
  text-decoration: none;
}

td a:hover {
  text-decoration: underline;
}

.tabla-documentos button {
  border: none;
  padding: 6px 10px;
  cursor: pointer;
  border-radius: 6px;
  margin: 0 2px;
  transition: background-color 0.3s ease;
}

.tabla-documentos button i {
  color: white;
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

.error, .sin-documentos {
  margin-top: 1rem;
  color: #dc3545;
  text-align: center;
}
</style>
