<script setup>
import { onMounted, ref } from 'vue';
import { useAuthStore } from '@/stores/Usuarios/useAuthStore';
import NavBar from '@/layouts/NavBar.vue';
import Sidebar from '@/layouts/Sidebar.vue';
import Footer from '@/layouts/Footer.vue';

const authStore = useAuthStore();
const loading = ref(true);

onMounted(async () => {
  await authStore.tryAutoLogin();
  loading.value = false;
});
</script>


<template>
  <div id="app">
    <div v-if="loading">
      <p>Cargando sesión...</p>
    </div>
    <div v-else>
      <template v-if="authStore.isAuthenticated">
        <NavBar />
        <div class="main-content">
          <Sidebar />
          <router-view />
        </div>
        <Footer />
      </template>
      <template v-else>
        <router-view />
      </template>
    </div>
  </div>
</template>


<style>

</style>