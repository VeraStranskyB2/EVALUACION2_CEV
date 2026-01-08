<template>
  <div class="page-container">
    <NavBar />
    <main class="background-section">
      <div class="welcome-message">
        <h2 class="bienvenido">BIENVENIDO</h2>
        <p>Por favor, ingrese su solicitud</p>
      </div>
    </main>
    <Footer />
  </div>
</template>

<script setup>

</script>

<style scoped>
/* Estilos del contenedor principal */
.page-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* Estilos de la sección principal con la imagen de fondo */
.background-section {
  flex-grow: 1;
  width: 100%;
  background-image: url('@/assets/img/calificacion-energetica-fondo.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  
  /* Modificaciones para la alineación */
  display: flex;
  justify-content: center; 
  align-items: flex-start;
  padding-top: 0rem;
}

/* Contenedor del mensaje para apilar el texto */
.welcome-message {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  padding: 0rem;  
  border-radius: 0px;
}

/* Estilos para el texto */
.bienvenido {
  color: white; 
  font-size: 4rem;
  margin: 0;
  font-weight: bold;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7); /* Sombra para mejor legibilidad */
}

p {
  font-size: 2.5rem;
  color: white;
  margin-top: 0.5rem;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.7); /* Sombra para mejor legibilidad */
}
</style>