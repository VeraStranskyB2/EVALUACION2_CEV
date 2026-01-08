import { defineStore } from 'pinia';
import ProyectoService from '@/services/ProyectoVivienda/ProyectoService';

export const useProyectoStore = defineStore('proyectos', {
  state: () => ({
    proyectos: [],
    proyectoSeleccionado: null,
    loading: false,
    error: null,
  }),

  actions: {
    // Obtiene todos los proyectos para el rol de evaluador.
    
    async fetchTodosLosProyectos() {
      this.loading = true;
      this.error = null;
      try {
        const response = await ProyectoService.obtenerProyectos();
        this.proyectos = response.data ?? response;
      } catch (err) {
        this.error = err.message || 'Error cargando todos los proyectos';
      } finally {
        this.loading = false;
      }
    },

    // Obtiene los proyectos de un cliente específico.
     
    async fetchProyectosPorCliente(rutUsuario) {
      this.loading = true;
      this.error = null;
      try {
        // Se asume que el servicio puede filtrar por rutUsuario.
        const response = await ProyectoService.obtenerProyectos(rutUsuario);
        this.proyectos = response.data ?? response;
      } catch (err) {
        this.error = err.message || 'Error cargando proyectos del cliente';
      } finally {
        this.loading = false;
      }
    },

    async fetchProyectoPorId(idProyecto) {
      this.loading = true;
      this.error = null;
      try {
        const response = await ProyectoService.obtenerProyectoPorId(idProyecto);
        this.proyectoSeleccionado = response.data ?? response;
      } catch (err) {
        this.error = err.message || 'Error cargando proyecto';
      } finally {
        this.loading = false;
      }
    },

    async crearProyecto(proyecto) {
      this.loading = true;
      this.error = null;
      try {
        const response = await ProyectoService.crearProyecto(proyecto);
        this.proyectos.push(response.data ?? response);
        return response.data ?? response;
      } catch (err) {
        this.error = err.message || 'Error al crear proyecto';
        throw err;
      } finally {
        this.loading = false;
      }
    },

    async actualizarProyecto(idProyecto, proyecto) {
      this.loading = true;
      this.error = null;
      try {
        const response = await ProyectoService.actualizarProyecto(idProyecto, proyecto);
        const index = this.proyectos.findIndex(p => p.id_proyecto === idProyecto);
        if (index !== -1) this.proyectos[index] = response.data ?? response;
        if (this.proyectoSeleccionado?.id_proyecto === idProyecto) {
          this.proyectoSeleccionado = response.data ?? response;
        }
        return response.data ?? response;
      } catch (err) {
        this.error = err.message || 'Error actualizando proyecto';
        throw err;
      } finally {
        this.loading = false;
      }
    },

    async eliminarProyecto(idProyecto) {
      this.loading = true;
      this.error = null;
      try {
        await ProyectoService.eliminarProyecto(idProyecto);
        this.proyectos = this.proyectos.filter(p => p.id_proyecto !== idProyecto);
        if (this.proyectoSeleccionado?.id_proyecto === idProyecto) {
          this.proyectoSeleccionado = null;
        }
      } catch (err) {
        this.error = err.message || 'Error eliminando proyecto';
        throw err;
      } finally {
        this.loading = false;
      }
    }
  }
});
