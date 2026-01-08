
import { defineStore } from 'pinia'
import { UsuarioService } from '@/services/Usuarios/UsuarioService'

export const useUsuarioStore = defineStore('usuario', {
  state: () => ({
    usuarios: [],
    cargando: false,
    error: null,
  }),

  actions: {
    async fetchUsuarios() {
      this.cargando = true
      this.error = null
      try {
        this.usuarios = await UsuarioService.obtenerTodos()
      } catch (e) {
        this.error = e.message || 'Error al cargar usuarios'
      } finally {
        this.cargando = false
      }
    },

    async crearUsuario(usuario) {
      this.cargando = true
      this.error = null
      try {
        const nuevo = await UsuarioService.crear(usuario)
        this.usuarios.push(nuevo)
      } catch (e) {
        this.error = e.message || 'Error al crear usuario'
        throw e
      } finally {
        this.cargando = false
      }
    },

    async actualizarUsuario(rut_usuario, usuario) {
      this.cargando = true
      this.error = null
      try {
        const actualizado = await UsuarioService.actualizar(rut_usuario, usuario)
        const index = this.usuarios.findIndex(u => u.rut_usuario === rut_usuario)
        if (index !== -1) this.usuarios[index] = actualizado
      } catch (e) {
        this.error = e.message || 'Error al actualizar usuario'
        throw e
      } finally {
        this.cargando = false
      }
    },

    async eliminarUsuario(rut_usuario) {
      this.cargando = true
      this.error = null
      try {
        await UsuarioService.eliminar(rut_usuario)
        this.usuarios = this.usuarios.filter(u => u.rut_usuario !== rut_usuario)
      } catch (e) {
        this.error = e.message || 'Error al eliminar usuario'
        throw e
      } finally {
        this.cargando = false
      }
    }
  }
})
