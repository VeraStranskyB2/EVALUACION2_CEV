<template>
  <section class="tabla-proyectos">
    <div v-if="cargando" class="estado-mensaje">Cargando proyectos...</div>
    <div v-else-if="error" class="estado-mensaje error-mensaje">
      Error al cargar los proyectos: {{ error }}
    </div>
    <div v-else-if="proyectos && proyectos.length > 0">
      <table>
        <thead>
          <tr>
            <th>ID Proyecto</th>
            <th>Rol Matriz</th>
            <th>Nombre Proyecto</th>
            <th>ID Dirección</th>
            <th>ID Zona Térmica</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="proyecto in proyectos" :key="proyecto.id_proyecto">
            <td>{{ proyecto.id_proyecto }}</td>
            <td>{{ proyecto.rol_matriz }}</td>
            <td>{{ proyecto.nombre_proyecto }}</td>
            <td>{{ proyecto.id_direccion }}</td>
            <td>{{ proyecto.zona_termica_id_zona_termica }}</td>
            <td class="acciones">
              <button class="editar" @click="$emit('editar', proyecto)">
                <i class='bx bxs-pencil'></i>
              </button>
              <button class="eliminar" @click="$emit('eliminar', proyecto.id_proyecto)">
                <i class='bx bxs-trash'></i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else class="estado-mensaje sin-proyectos">No hay proyectos para mostrar.</div>
  </section>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';

defineProps({
  proyectos: {
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
.tabla-proyectos {
  max-width: 900px;
  margin: 2rem auto;
  font-family: Arial, sans-serif;
}

.estado-mensaje {
  text-align: center;
  font-size: 1.2rem;
  padding: 2rem;
  background-color: #f8f9fa;
  border-radius: 8px;
  border: 1px solid #ddd;
}

.error-mensaje {
  color: #d9534f;
  border-color: #d9534f;
}

.sin-proyectos {
  color: #555;
  border-color: #ccc;
}

table {
  width: 100%;
  border-collapse: collapse;
}

thead {
  background-color: #006666;
  color: white;
}

th, td {
  padding: 10px;
  border: 1px solid #006666;
  text-align: left;
}

th {
  text-align: center;
}

tbody tr:nth-child(even) {
  background-color: #f8f9fa;
}

tbody tr:hover {
  background-color: #eaf6fc;
}

.acciones {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
}

.acciones button {
  border: none;
  padding: 6px 10px;
  cursor: pointer;
  border-radius: 6px;
  margin: 0 2px;
  transition: background-color 0.3s ease;
}

.acciones button i {
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
</style>