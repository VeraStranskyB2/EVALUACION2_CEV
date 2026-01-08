// src/stores/Usuarios/useClienteStore.js

import { defineStore } from 'pinia';
import { ref, reactive } from 'vue';
import ClienteService from '@/services/Usuarios/ClienteService.js';

export const useClienteStore = defineStore('cliente', () => {
  // STATE
  const clientes = ref([]);
  const clienteActual = reactive({
    usuario_rut_usuario: '',
    razon_social: '',
    representante_legal: '',
    giro_comercial: '',
    fecha_registro: ''
  });

  const cargando = ref(false);
  const error = ref(null);

  // ACTIONS
  async function fetchClientes(params = {}) {
    cargando.value = true;
    error.value = null;
    try {
      const res = await ClienteService.obtenerClientes(params);
      clientes.value = res.data || res;
    } catch (e) {
      error.value = e?.message || 'Error al cargar clientes';
    } finally {
      cargando.value = false;
    }
  }

  async function guardarCliente(cliente) {
    cargando.value = true;
    error.value = null;
    try {
      if (cliente.usuario_rut_usuario) {
        await ClienteService.actualizarCliente(cliente.usuario_rut_usuario, cliente);
      } else {
        await ClienteService.crearCliente(cliente);
      }
      await fetchClientes(); // refresca la lista automáticamente
    } catch (e) {
      error.value = e?.message || 'Error guardando cliente';
      throw e;
    } finally {
      cargando.value = false;
    }
  }

  // Elimina una de las funciones duplicadas
  async function eliminarCliente(rut) {
    cargando.value = true;
    error.value = null;
    try {
      await ClienteService.eliminarCliente(rut);
      clientes.value = clientes.value.filter(c => c.usuario_rut_usuario !== rut);
    } catch (e) {
      error.value = e?.message || 'Error eliminando cliente';
      throw e;
    } finally {
      cargando.value = false;
    }
  }

  function setClienteActual(cliente) {
    Object.assign(clienteActual, cliente);
  }

  function resetClienteActual() {
    clienteActual.usuario_rut_usuario = '';
    clienteActual.razon_social = '';
    clienteActual.representante_legal = '';
    clienteActual.giro_comercial = '';
    clienteActual.fecha_registro = '';
  }

  return {
    clientes,
    clienteActual,
    cargando,
    error,
    fetchClientes,
    guardarCliente,
    eliminarCliente,
    setClienteActual,
    resetClienteActual
  };
});