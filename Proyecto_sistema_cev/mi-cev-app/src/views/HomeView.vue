<template>
  <div class="page-container">
    <NavBar />
    <main class="background-section">
      <h1 class="bienvenido">Bienvenido</h1>
      
    </main>
    <Footer />
  </div>
</template>


<script setup>
import NavBar from '@/layouts/NavBar.vue'
import Footer from '@/layouts/Footer.vue'

</script>

<style scoped>
.page-container {
  display: flex;
  flex-direction: column;  
  height:100vh; /* toda la ventana */
}
.background-section {
  flex: 1;
  width: 100%;
  background-image: url('@/assets/img/Entrada.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  display: flex;
  justify-content: center;
  align-items: center;
}
.bienvenido {
  color: white;
  font-size: 4rem;
  font-weight: bold;
  text-shadow: 2px 4px 6px #000;
}


</style>
