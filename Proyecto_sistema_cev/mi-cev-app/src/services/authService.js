import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth'; // Ajustado al backend

export default {
  async login(credentials) {
    const response = await axios.post(`${API_URL}/login`, credentials);
    const token = response.data.token;

    if (token) {
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    }

    return response.data;
  },

  async register(userData) {
    const response = await axios.post(`${API_URL}/registro`, userData);
    return response.data;
  },

  async getProfile() {   
    const response = await axios.get(`${API_URL}/profile`);
    return response.data;
  },

  logout() {
    localStorage.removeItem('token');
    delete axios.defaults.headers.common['Authorization'];
  },

  getToken() {
    return localStorage.getItem('token');
  },

  isLoggedIn() {
    return !!localStorage.getItem('token');
  }
};
