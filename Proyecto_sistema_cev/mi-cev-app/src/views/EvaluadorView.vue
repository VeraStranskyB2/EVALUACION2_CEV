<template>
  <div class="page-container">
    <NavBar />
    <main class="background-section">
      <div class="welcome-message">
        <h2 class="bienvenido">BIENVENIDO EVALUADOR</h2>
        <p>Por favor, revise las solicitudes</p>
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
  display: flex;
  justify-content: center;
  align-items: center;
  
  
}

/* Contenedor del mensaje para apilar el texto */
.welcome-message {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  padding: 2rem;  
  border-radius: 10px;
}

/* Estilos para el texto */
.bienvenido {
  color: white;
  font-size: 4rem;
  margin: 0;
  font-weight: bold;
  text-shadow: 6px 6px 12px rgba(12, 1, 1, 0.7); /* Sombra para mejor legibilidad */
}

p {
  font-size: 2.5rem;
  color: white;
  margin-top: 0.5rem;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.7); /* Sombra para mejor legibilidad */
}
</style>
