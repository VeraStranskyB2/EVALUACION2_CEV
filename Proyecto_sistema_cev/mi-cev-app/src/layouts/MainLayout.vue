<template>
  <div class="main-layout">
    <NavBar />
    <div class="layout-content">
      <Sidebar />
      <main class="content-area">
        <router-view />
      </main>
    </div>
    <Footer />
  </div>
</template>

<script setup>
import NavBar from '@/layouts/NavBar.vue'
import Sidebar from '@/layouts/Sidebar.vue'
import Footer from '@/layouts/Footer.vue'
</script>

<style scoped>
.main-layout {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  width: 100%;  
}

.navbar{
  width: 100%;
}

.layout-content {
  display: flex;
  flex: 1;
  width: 100%; 
}

.sidebar {
  width: 240px; /* ancho fijo de sidebar */
  flex-shrink: 0;
}

.content-area {
  flex: 1;  
  padding: 24px 32px;
  background: #f9f9f9;
  border-left: 1px solid #e2e8f0;
  border-top: 1px solid #e2e8f0;
  box-shadow: inset 0 1px 0 rgba(0,0,0,.04);
  overflow-y: auto;
  padding-bottom: 60px; /* coincide con la altura del footer */
}


</style>
