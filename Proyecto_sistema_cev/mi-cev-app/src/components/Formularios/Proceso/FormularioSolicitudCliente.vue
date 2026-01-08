<template>
  <div class="generar-solicitud-container">
    <div class="form-solicitud-header">
      <h2>Generar Solicitud</h2>
      <p>Complete los campos para iniciar el proceso de solicitud.</p>
    </div>

    <form @submit.prevent="guardarSolicitud" class="form-solicitud">
      <div class="form-group">
        <label for="tipo">Tipo de Solicitud:</label>
        <select id="tipo" v-model="solicitud.tipo" required>
          <option value="" disabled>Seleccione un tipo</option>
          <option value="Calificación">Calificación</option>
          <option value="Precalificación">Precalificación</option>
        </select>
      </div>

      <div class="form-group">
        <label>Fecha de Ingreso:</label>
        <div class="fecha-group">
          <input type="text" v-model="solicitud.fecha.dia" placeholder="Día" maxlength="2" required />
          <span>-</span>
          <input type="text" v-model="solicitud.fecha.mes" placeholder="Mes" maxlength="2" required />
          <span>-</span>
          <input type="text" v-model="solicitud.fecha.anio" placeholder="Año" maxlength="4" required />
        </div>
      </div>

      <div class="form-group">
        <label>Permiso de Edificación:</label>
        <div class="permiso-group">
          <input type="text" v-model="solicitud.permiso.numero" placeholder="N°" required />
          <span>/</span>
          <input type="text" v-model="solicitud.permiso.anio" placeholder="Año" maxlength="4" required />
        </div>
      </div>

      <div class="form-group">
        <label>Recepción Final:</label>
        <div class="recepcion-group">
          <input type="text" v-model="solicitud.recepcion.numero" placeholder="N°" required />
          <span>/</span>
          <input type="text" v-model="solicitud.recepcion.anio" placeholder="Año" maxlength="4" required />
        </div>
      </div>

      <div class="form-group">
        <label for="proyecto">Proyecto:</label>
        <select id="proyecto" v-model="solicitud.proyecto_id_proyecto" required>
          <option value="" disabled>Seleccione un proyecto</option>
          <option v-for="proyecto in proyectos" :key="proyecto.id_proyecto" :value="proyecto.id_proyecto">
            {{ proyecto.nombre }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label for="estado">Estado:</label>
        <select id="estado" v-model="solicitud.estado_id_estado" required>
          <option value="" disabled>Seleccione un estado</option>
          <option v-for="estado in estados" :key="estado.id_estado" :value="estado.id_estado">
            {{ estado.nombre_estado }}
          </option>
        </select>
      </div>
      
      <div class="form-group">
        <label for="cliente-rut">RUT Cliente:</label>
        <input id="cliente-rut" v-model="solicitud.cliente_rut_usuario" disabled />
      </div>

      <button type="submit">
        <i class='bx bx-plus-circle'></i> Crear Solicitud
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { storeToRefs } from 'pinia';
import { useSolicitudClienteStore } from '@/stores/Proceso/useSolicitudClienteStore';
import { useProyectoStore } from '@/stores/ProyectoVivienda/useProyectoStore';
import { useAuthStore } from '@/stores/Usuarios/useAuthStore';

// Stores
const solicitudClienteStore = useSolicitudClienteStore();
const proyectoStore = useProyectoStore();
const authStore = useAuthStore();

// Estados reactivos de los stores
const { proyectos } = storeToRefs(proyectoStore);
const { estados } = storeToRefs(solicitudClienteStore);

// Estado local del formulario
const solicitud = ref({
  tipo: '',
  permiso: { numero: '', anio: '' },
  recepcion: { numero: '', anio: '' },
  fecha: { dia: '', mes: '', anio: '' },
  proyecto_id_proyecto: '',
  estado_id_estado: '',
  cliente_rut_usuario: authStore.rut_usuario || ''
});

// Lógica de carga de datos al montar el componente
onMounted(async () => {
    // Carga proyectos del cliente a través de la acción del store
    const rutCliente = authStore.rut_usuario;
    if (rutCliente) {
        await proyectoStore.fetchProyectosPorCliente(rutCliente);
    }
    // Cargar estados de solicitud a través de la acción del store
    await solicitudClienteStore.fetchEstados();
});

// Función para guardar la solicitud
const guardarSolicitud = async () => {
  const fechaIngreso = `${solicitud.value.fecha.anio}-${solicitud.value.fecha.mes.padStart(2, '0')}-${solicitud.value.fecha.dia.padStart(2, '0')}`;
  
  const nuevaSolicitud = {
    tipo: solicitud.value.tipo,
    permiso_edificacion_numero: solicitud.value.permiso.numero,
    permiso_edificacion_anio: solicitud.value.permiso.anio,
    recepcion_final_numero: solicitud.value.recepcion.numero,
    recepcion_final_anio: solicitud.value.recepcion.anio,
    fecha_ingreso: fechaIngreso,
    proyecto_id_proyecto: solicitud.value.proyecto_id_proyecto,
    estado_id_estado: solicitud.value.estado_id_estado,
    cliente_rut_usuario: solicitud.value.cliente_rut_usuario
  };

  try {
    // La creación de la solicitud también se hace a través del store
    await solicitudClienteStore.crearSolicitud(nuevaSolicitud);
    alert('Solicitud creada exitosamente.');

    // Limpia el formulario
    solicitud.value = {
      tipo: '',
      permiso: { numero: '', anio: '' },
      recepcion: { numero: '', anio: '' },
      fecha: { dia: '', mes: '', anio: '' },
      proyecto_id_proyecto: '',
      estado_id_estado: '',
      cliente_rut_usuario: authStore.rut_usuario || ''
    };
  } catch (error) {
    alert('Error al crear la solicitud. Por favor, intente de nuevo.');
    console.error('Error al crear la solicitud:', error);
  }
};
</script>

<style scoped>
.generar-solicitud-container {
  padding: 2rem;
  font-family: Arial, sans-serif;
  max-width: 900px;
  margin: auto;
}

.form-solicitud-header {
  background-color: #006666;
  color: white;
  padding: 1.5rem 2rem;
  text-align: center;
  border-bottom: 2px solid #006666;
  margin-bottom: 2rem;
}

h2 {
  color: white;
  font-size: 2rem;
  margin: 0;
}

p {
  font-size: 1rem;
  margin-top: 0.5rem;
}

.form-solicitud {
  background-color: #f8f9fa;
  padding: 2rem;
  border-radius: 8px;
  border: 1px solid #006666;
  box-shadow: 0 12px 12px #006666;
  margin-bottom: 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  font-weight: 600;
  margin-bottom: 0.5rem;
  color: #006666;
}

input,
select {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #555;
  background-color: white;
}

.fecha-group,
.permiso-group,
.recepcion-group {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.fecha-group input,
.permiso-group input,
.recepcion-group input {
  text-align: center;
}

.permiso-group input,
.recepcion-group input {
  flex-grow: 1;
}

.fecha-group input:first-child,
.fecha-group input:nth-child(3) {
  width: 25%;
}

.fecha-group input:last-child {
  width: 50%;
}

.fecha-group span,
.permiso-group span,
.recepcion-group span {
  font-weight: bold;
  color: #006666;
}

button[type="submit"] {
  width: 100%;
  padding: 1rem;
  background-color: #006666;
  color: white;
  font-size: 1.1rem;
  font-weight: bold;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
}

button[type="submit"]:hover {
  background-color: #1870e4;
}
</style>