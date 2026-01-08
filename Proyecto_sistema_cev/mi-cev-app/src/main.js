import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createPinia } from 'pinia';
import { useAuthStore } from './stores/Usuarios/useAuthStore';

const pinia = createPinia();
const app = createApp(App);


app.use(pinia);

// Crea la instancia del store
const authStore = useAuthStore();

// Llama a la acción tryAutoLogin de forma asíncrona ANTES de montar la app
// Esto permite restaurar el estado de la sesión antes de que el router se active
authStore.tryAutoLogin().then(() => {
app.use(router);
app.mount('#app');

});