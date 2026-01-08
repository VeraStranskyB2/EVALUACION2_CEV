import { defineStore } from 'pinia';
import ViviendaService from '@/services/ProyectoVivienda/ViviendaService';

export const useViviendaStore = defineStore('viviendas', {
  state: () => ({
    viviendas: [],
    viviendaSeleccionada: null,
    loading: false,
    error: null,
  }),

  actions: {
    // Obtiene todas las viviendas.
    
    async fetchTodasLasViviendas(params = {}) {
      this.loading = true;
      this.error = null;
      try {
        const response = await ViviendaService.obtenerViviendas(params);
        this.viviendas = response.data ?? response;
      } catch (err) {
        this.error = err.message || 'Error cargando todas las viviendas';
      } finally {
        this.loading = false;
      }
    },

    // Obtiene las viviendas de un proyecto específico.
    
    async fetchViviendasPorProyecto(idProyecto) {
      this.loading = true;
      this.error = null;
      try {
        const response = await ViviendaService.obtenerViviendasPorProyecto(idProyecto);
        this.viviendas = response.data ?? response;
      } catch (err) {
        this.error = err.message || `Error cargando viviendas para el proyecto ${idProyecto}`;
      } finally {
        this.loading = false;
      }
    },

    // Obtiene una vivienda por su rol.

    async fetchViviendaPorRol(rolVivienda) {
      this.loading = true;
      this.error = null;
      try {
        const response = await ViviendaService.obtenerViviendaPorRol(rolVivienda);
        this.viviendaSeleccionada = response.data ?? response;
      } catch (err) {
        this.error = err.message || `Error cargando vivienda con rol ${rolVivienda}`;
      } finally {
        this.loading = false;
      }
    },

    // Crea una nueva vivienda.

    async crearVivienda(vivienda) {
      this.loading = true;
      this.error = null;
      try {
        const response = await ViviendaService.crearVivienda(vivienda);
        this.viviendas.push(response.data ?? response);
        return response.data ?? response;
      } catch (err) {
        this.error = err.message || 'Error al crear vivienda';
        throw err;
      } finally {
        this.loading = false;
      }
    },

    // Actualiza una vivienda existente.
    
    async actualizarVivienda(rolVivienda, vivienda) {
      this.loading = true;
      this.error = null;
      try {
        const response = await ViviendaService.actualizarVivienda(rolVivienda, vivienda);
        const index = this.viviendas.findIndex(v => v.rol_vivienda === rolVivienda);
        if (index !== -1) {
          this.viviendas[index] = response.data ?? response;
        }
        if (this.viviendaSeleccionada?.rol_vivienda === rolVivienda) {
          this.viviendaSeleccionada = response.data ?? response;
        }
        return response.data ?? response;
      } catch (err) {
        this.error = err.message || 'Error actualizando vivienda';
        throw err;
      } finally {
        this.loading = false;
      }
    },

    // Elimina una vivienda.
   
    async eliminarVivienda(rolVivienda) {
      this.loading = true;
      this.error = null;
      try {
        await ViviendaService.eliminarVivienda(rolVivienda);
        this.viviendas = this.viviendas.filter(v => v.rol_vivienda !== rolVivienda);
        if (this.viviendaSeleccionada?.rol_vivienda === rolVivienda) {
          this.viviendaSeleccionada = null;
        }
      } catch (err) {
        this.error = err.message || 'Error eliminando vivienda';
        throw err;
      } finally {
        this.loading = false;
      }
    }
  },
});
