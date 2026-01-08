<template>
  <div class="solicitudes-view">
    <h2 class="titulo-pagina">Lista de Solicitudes</h2>

    <div v-if="loading" class="mensaje-estado">
      <p>Cargando solicitudes...</p>
    </div>
    
    <div v-else-if="error" class="mensaje-estado error-mensaje">
      <p>Error: {{ error }}</p>
    </div>

    <TablaSolicitudCliente
      v-else
      :solicitudes="solicitudes"
      :cargando="loading"
      :error="error"
      @editar="editarSolicitud"
      @eliminar="eliminarSolicitud"
    />
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { storeToRefs } from 'pinia';

// Stores
import { useAuthStore } from '@/stores/Usuarios/useAuthStore';
import { useSolicitudClienteStore } from '@/stores/Proceso/useSolicitudClienteStore';

// Componentes
import TablaSolicitudCliente from '@/components/Tablas/TablasProcesos/TablaSolicitudCliente.vue';

const router = useRouter();
const authStore = useAuthStore();
const solicitudClienteStore = useSolicitudClienteStore();

// Reactividad de Solicitudes
const { solicitudes, loading, error } = storeToRefs(solicitudClienteStore);

// Función para cargar las solicitudes del cliente
const cargarSolicitudesCliente = async () => {
    const rutUsuario = authStore.rut_usuario;
    if (rutUsuario) {
        await solicitudClienteStore.fetchSolicitudesCliente(rutUsuario);
    } 
    //else {
    //    console.error('RUT del usuario no disponible. Redirigiendo a login.');
    //    router.push('/login');
    //}
};

// Funciones para la tabla
const editarSolicitud = (solicitud) => {
    console.log('Editar solicitud:', solicitud);
    // Lógica para editar la solicitud
};

const eliminarSolicitud = async (solicitudId) => {
    try {
        await solicitudClienteStore.eliminarSolicitud(solicitudId);
        console.log(`Solicitud con ID ${solicitudId} eliminada correctamente.`);
    } catch (err) {
        console.error('Error al eliminar la solicitud:', err);
    }
};

// Se ejecuta la función al montar el componente
onMounted(cargarSolicitudesCliente);
</script>

<style scoped>
.solicitudes-view {
  padding: 2rem;
  max-width: 900px;
  margin: 0 auto;
}

.titulo-pagina {
  text-align: center;
  color: #006666;
  margin-bottom: 2rem;
}

.mensaje-estado {
  text-align: center;
  padding: 1rem;
}

.error-mensaje {
  color: #d9534f;
  background-color: #f2dede;
  border: 1px solid #ebccd1;
  border-radius: 4px;
}

.logout-btn {
  position: fixed;
  bottom: 2rem;
  right: 2rem;
  background-color: #ff4444;
  color: white;
  border: none;
  padding: 0.75rem 1rem;
  border-radius: 50px;
  font-weight: bold;
  cursor: pointer;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

.logout-btn:hover {
  background-color: #cc0000;
}
</style>