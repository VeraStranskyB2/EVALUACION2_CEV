
import { defineStore } from 'pinia';
import authService from '@/services/authService';
import axios from 'axios'; // Se importa axios para configurar headers

export const useAuthStore = defineStore('auth', {
  // STATE: Se guarda el token y el usuario en memoria.
  state: () => ({
    usuario: null,
    token: null,
  }),

  // GETTERS: Derivan el estado.
  getters: {
    isAuthenticated: (state) => !!state.token,
    getRol: (state) => state.usuario?.rol || null, // Acceso seguro por si el usuario es nulo
    getUsuario: (state) => state.usuario,
  },

  // ACTIONS: Encapsulan toda la lógica, incluyendo las llamadas a la API.
  actions: {
    // Login
    async login(credentials) {
      try {
        const responseData = await authService.login(credentials)
        this.usuario = responseData.user
        this.token = responseData.token

        // Guarda el token en localStorage
        localStorage.setItem('token', this.token)

        // Se configura Axios para usar el token automáticamente
        axios.defaults.headers.common['Authorization'] = `Bearer ${this.token}`
      } catch (error) {
        console.error('Error en login:', error)
        throw error
      }
    },

    // Logout
    logout() {
      this.usuario = null
      this.token = null
      localStorage.removeItem('token')
      delete axios.defaults.headers.common['Authorization']
    },

    // Restaurar sesión automáticamente
    async tryAutoLogin() {
      const token = localStorage.getItem('token')
      if (!token) return

      try {
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`

        // Obtener perfil del usuario desde backend
        const responseData = await authService.getProfile()

        this.usuario = responseData.user
        this.token = token
      } catch (error) {
        console.warn('Sesión inválida o expirada, cerrando sesión.')
        this.logout()
      }
    },
  },

});