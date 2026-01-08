<script setup>
import { ref } from 'vue'

const isOpen = ref(false)

const toggleMenu = () => {
  isOpen.value = !isOpen.value
}
</script>

<template>
  <header>
    <nav class="navbar">
      <!-- Logo -->
      <div class="logo">
        <img src="@/assets/img/Logo_cev.png" alt="Logo_CEV" class="Logo cev-logo" />
      </div>
      <!-- Botón menú para móviles -->
      <div class="menu-toggle" @click="toggleMenu">
        <span></span>
        <span></span>
        <span></span>
      </div>
      <!-- Menú de navegación -->
      <ul :class="['nav-menu', { open: isOpen }]">
        <li><router-link to="/login">Acceso</router-link></li>
        <li><router-link to="/registro">Regístrate</router-link></li>
        <li><router-link to="/quienes-somos">Quiénes somos</router-link></li>
        <li><router-link to="/buscar">Buscar</router-link></li>
        <li><router-link to="/contacto" class="contact-btn">Contacto</router-link></li>
      </ul>
    </nav>
  </header>
</template>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  /* Ojo: evita font-size global aquí */
}

/* Resto de estilos igual, sin cambios */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #006666;
  padding: 5px 5px;
}

.logo {
  display: flex;
  align-items: center;
}

.logo img {
  height: 50px;
  margin-right: 5px;
}

.menu-toggle {
  display: none;
  flex-direction: column;
  cursor: pointer;
}

.menu-toggle span {
  width: 25px;
  height: 3px;
  background-color: white;
  margin: 4px 0;
}

.nav-menu {
  list-style: none;
  display: flex;
}


.nav-menu li {
  margin-left: 20px;
}

.nav-menu a {
  color: white;
  text-decoration: none;
  font-size: 20px;
}

.nav-menu a:hover,
.nav-menu a.active {
  text-decoration: none;
}

.navbar a:hover {
  color: #1870e4;
  transition: color 0.3s ease;
  font-size: 22.5px;
}

.contact-btn {
  background-color: #1870e4;
  padding: 5px 10px;
  border-radius: 15px;
}

.contact-btn:hover {
  background-color: #deeaf1;
}

@media (max-width: 768px) {
  .nav-menu {
    display: none;
    width: 100%;
    flex-direction: column;
    align-items: center;
    position: absolute;
    top: 60px;
    left: 0;
    background-color: #006666;
  }

  .nav-menu.open {
    display: flex;
  }

  .nav-menu li {
    margin: 10px 0;
  }

  .menu-toggle {
    display: flex;
  }
}
</style>
