import { defineStore } from 'pinia';
import { ref, computed } from 'vue'; // Importa ref y computed
import { EstadoService } from '@/services/Proceso/EstadoService';

export const useEstadoStore = defineStore('estado', () => {
    // Estado
    const estados = ref([]);
    const estadoSeleccionado = ref(null);
    const cargando = ref(false);
    const error = ref(null);

    
    const activos = computed(() => estados.value.filter(e => e.activo));
    const totalEstados = computed(() => estados.value.length);

    // Acciones
    // Carga todos los estados desde el servicio.
    async function cargarEstados() {
      cargando.value = true;
      error.value = null;
      try {
        estados.value = await EstadoService.obtenerTodos();
      } catch (err) {
        error.value = err.message || 'Error cargando estados';
      } finally {
        cargando.value = false;
      }
    }

    // Selecciona un estado por su objeto.   
    function seleccionarEstado(estado) {
      estadoSeleccionado.value = estado;
    }

    // Limpia la selección actual de estado.
    function limpiarSeleccion() {
      estadoSeleccionado.value = null;
    }

    // Guarda un nuevo estado o actualiza uno existente.     
    async function guardarEstado(estado) {
      cargando.value = true;
      error.value = null;
      try {
        if (estado.id_estado) {
          await EstadoService.actualizar(estado.id_estado, estado);
        } else {
          await EstadoService.crear(estado);
        }
        // Después de guardar, recarga la lista completa de estados para reflejar los cambios
        await cargarEstados();
      } catch (err) {
          error.value = err.message || 'Error guardando estado';
          console.error('Detalle del error:', err);
          throw err;
      } finally {
          cargando.value = false;
      }
    }

    //Elimina un estado por su ID.  
    async function eliminarEstado(id_estado) {
      cargando.value = true;
      error.value = null;
      try {
        await EstadoService.eliminar(id_estado);
        // Después de eliminar, recarga la lista para reflejar los cambios
        await cargarEstados();
      } catch (err) {
        error.value = err.message || 'Error eliminando estado';
        console.error('Detalle del error:', err);
        throw err;
      } finally {
        cargando.value = false;
      }
    }

    // Retorna el estado y las acciones para que sean accesibles desde los componentes
    return {
      estados,
      estadoSeleccionado,
      cargando,
      error,
      activos,
      totalEstados,
      cargarEstados,
      seleccionarEstado,
      limpiarSeleccion,
      guardarEstado,
      eliminarEstado,
    };
});