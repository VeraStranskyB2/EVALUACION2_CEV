<template>
  <div class="table-container">
    <table class="document-table">
      <thead>
        <tr>
          <th>Id Documento</th>
          <th>Tipo</th>
          <th>Nombre del Archivo</th>
          <th>Comentarios</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="documento in documentos" :key="documento.id">
          <td>{{ documento.id_documento }}</td>
          <td>{{ documento.tipo }}</td>
          <td>{{ documento.nombre }}</td>
          <td>{{ documento.comentarios }}</td>
          <td>
            <!-- Botón para descargar el documento -->
            <a :href="documento.url" target="_blank" class="download-button">
              Descargar
            </a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  documentos: {
    type: Array,
    required: true
  }
});
</script>

<style scoped>
.table-container {
  overflow-x: auto;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.document-table {
  width: 100%;
  border-collapse: collapse;
  font-family: Arial, sans-serif;
  color: #333;
}

.document-table th,
.document-table td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.document-table thead {
  background-color: #006666;
  color: white;
}

.document-table tbody tr:hover {
  background-color: #f5f5f5;
}

.download-button {
  display: inline-block;
  padding: 8px 12px;
  background-color: #006666;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s;
  font-size: 0.9rem;
}

.download-button:hover {
  background-color: #1870e4;
}
</style>
