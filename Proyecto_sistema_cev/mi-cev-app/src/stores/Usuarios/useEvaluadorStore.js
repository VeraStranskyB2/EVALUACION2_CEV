import { defineStore } from 'pinia'
import { EvaluadorService } from '@/services/Usuarios/EvaluadorService'

export const useEvaluadorStore = defineStore('evaluador', {
  state: () => ({
    evaluadores: [],
    cargando: false,
    error: null,
  }),

  actions: {
    async fetchEvaluadores() {
      this.cargando = true
      this.error = null
      try {
        this.evaluadores = await EvaluadorService.obtenerTodos()
      } catch (e) {
        this.error = e.message || 'Error al cargar evaluadores'
      } finally {
        this.cargando = false
      }
    },

    async crearEvaluador(evaluador) {
      this.cargando = true
      this.error = null
      try {
        const nuevo = await EvaluadorService.crear(evaluador)
        this.evaluadores.push(nuevo)
      } catch (e) {
        this.error = e.message || 'Error al crear evaluador'
        throw e
      } finally {
        this.cargando = false
      }
    },

    async actualizarEvaluador(rut_usuario, evaluador) {
      this.cargando = true
      this.error = null
      try {
        const actualizado = await EvaluadorService.actualizar(rut_usuario, evaluador)
        const index = this.evaluadores.findIndex(e => e.usuario_rut_usuario === rut_usuario)
        if (index !== -1) this.evaluadores[index] = actualizado
      } catch (e) {
        this.error = e.message || 'Error al actualizar evaluador'
        throw e
      } finally {
        this.cargando = false
      }
    },

    async eliminarEvaluador(rut_usuario) {
      this.cargando = true
      this.error = null
      try {
        await EvaluadorService.eliminar(rut_usuario)
        this.evaluadores = this.evaluadores.filter(e => e.usuario_rut_usuario !== rut_usuario)
      } catch (e) {
        this.error = e.message || 'Error al eliminar evaluador'
        throw e
      } finally {
        this.cargando = false
      }
    }
  }
})
