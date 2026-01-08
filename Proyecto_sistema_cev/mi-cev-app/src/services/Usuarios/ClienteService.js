import axios from 'axios'

const BASE_URL = '/api/cliente'

const ClienteService = {

  //Obtener todos los clientes con filtros opcionales (params)
  obtenerClientes(params) {
    return axios.get(BASE_URL, { params })
  },

  //Obtener un Cliente específico por su ID
  obtenerClientePorId(id) {
    if (!id) throw Error('ID de Cliente requerido')
    return axios.get(`${BASE_URL}/${id}`)
  },

  //Crear un nuevo Clinete
  crearCliente(cliente) {   
    if (!cliente || typeof cliente.nombre_cliente !== 'string' || !cliente.nombre_cliente.trim()) {
      throw new Error('Datos de Cliente inválidos: nombre_cliente es obligatorio')
    }
    return axios.post(BASE_URL, cliente)
  },

  //Actualizar un Cliente existente por ID
  actualizarCliente(id, cliente) {
    if (!id) throw new Error('ID requerido para actualizar Cliente')
    return axios.put(`${BASE_URL}/${id}`, cliente)
  },

  //Eliminar un Cliente por su ID
  eliminarCliente(id) {
    if (!id) throw new Error('ID requerido para eliminar cliente')
    return axios.delete(`${BASE_URL}/${id}`)
  }
}

export default ClienteService