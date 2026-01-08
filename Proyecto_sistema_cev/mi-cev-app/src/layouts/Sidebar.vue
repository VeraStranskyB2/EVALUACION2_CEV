<template>
  <div class="sidebar">
    <template v-if="rol === 'cliente'">
      <div class="sidebar-header">
        <i class='bx bx-user' style="font-size: 3rem; color: white;"></i>
        <span class="title">MENÚ CLIENTE</span>
      </div>
      
      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('solicitudes')">
          <i class="bx bx-file icon"></i>
          Solicitudes
          <span>
            <i v-if="openMenu === 'solicitudes'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'solicitudes'" class="submenu">
          <li><router-link :to="{ name: 'cliente-generar-solicitud' }"><i class='bx bx-file-blank'></i> Generar Solicitud</router-link></li>
          <li><router-link :to="{ name: 'cliente-lista-solicitudes' }"><i class='bx bx-list-ul'></i> Mis Solicitudes</router-link></li>
          <li><router-link :to="{ name: 'cliente-subir-documento' }"><i class='bx bxs-cloud-upload'></i> Subir Documento</router-link></li>
          <!--<li><router-link :to="{ name: 'cliente-estado-solicitud' }"><i class='bx bx-check-circle'></i> Estado</router-link></li>-->
        </ul>
      </div>

      <hr class="divider" />

      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('gestion-cliente')">
          <i class="bx bx-cog icon"></i>
          Gestión
          <span>
            <i v-if="openMenu === 'gestion-cliente'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'gestion-cliente'" class="submenu">
          <li><router-link :to="{ name: 'cliente-proyecto' }"><i class='bx bx-sitemap'></i> Proyecto</router-link></li>
          <li><router-link :to="{ name: 'cliente-vivienda' }"><i class='bx bx-home'></i> Vivienda</router-link></li>
        </ul>
      </div>
      
      <hr class="divider" />
          
      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('ubicacion-cliente')">
          <i class='bx bx-location-plus'></i> 
          Ubicación
          <span>
            <i v-if="openMenu === 'ubicacion-cliente'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'ubicacion-cliente'" class="submenu">
          <!--<li><router-link :to="{ name: 'cliente-ubicacion-region' }"><i class='bx bxs-building-house'></i> Región</router-link></li>-->
          <!--<li><router-link :to="{ name: 'cliente-ubicacion-comuna' }"><i class='bx bx-buildings'></i> Comuna</router-link></li>-->
          <li><router-link :to="{ name: 'cliente-ubicacion-ubicacion' }"><i class='bx bx-map-pin'></i> Registro de Ubicación</router-link></li>
          <!--<li><router-link :to="{ name: 'cliente-ubicacion-ZonaTermica' }"><i class='bx bx-sun'></i> Zona Térmica</router-link></li>-->
        </ul>
      </div>

      <hr class="divider" />

      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('resultados-cliente')">
          <i class="bx bx-bar-chart icon"></i>
          Resultados
          <span>
            <i v-if="openMenu === 'resultados-cliente'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'resultados-cliente'" class="submenu">
          <li><router-link :to="{ name: 'cliente-informes' }"><i class="bx bx-file-blank sub-icon"></i> Informes</router-link></li>
          <li><router-link :to="{ name: 'cliente-etiquetas' }"><i class="bx bx-tag sub-icon"></i> Etiquetas</router-link></li>
        </ul>
      </div>
      <hr class="divider" />



    </template>
    
    <template v-else-if="rol === 'evaluador'">
      <div class="sidebar-header">
        <i class='bx bx-user' style="font-size: 3rem; color: white;"></i>
        <span class="title">MENÚ EVALUADOR</span>
      </div>
      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('solicitudes-evaluador')">
          <i class="bx bx-file icon"></i>
          Solicitudes
          <span>
            <i v-if="openMenu === 'solicitudes-evaluador'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'solicitudes-evaluador'" class="submenu">
          <li><router-link :to="{ name: 'evaluador-ver-solicitudes' }"><i class='bx bx-file-blank'></i> Ver Solicitud</router-link></li>
          <!--<li><router-link :to="{name: 'evaluador-aceptar-rechazar'}"><i class='bx  bx-move-vertical'></i> Aceptar/Rechazar</router-link></li>
          <li><router-link :to="{name: 'evaluador-estado-solicitud'}"><i class='bx  bx-edit'></i> Cambiar Estado</router-link></li> -->
          <li><router-link :to="{name: 'evaluador-ver-documentos'}"><i class='bx bx-cloud-download'></i> Descargar Documentación</router-link></li>
          <li><router-link :to="{name: 'evaluador-hacer-evaluacion'}"><i class='bx bx-list-check'></i> Evaluar</router-link></li>
        </ul>
      </div>

      <hr class="divider" />
      
      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('gestion-evaluador')">
          <i class="bx bx-cog icon"></i>
          Gestión
          <span>
            <i v-if="openMenu === 'gestion-evaluador'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'gestion-evaluador'" class="submenu">
          <li><router-link :to="{ name: 'evaluador-proyecto' }"><i class='bx bx-sitemap'></i> Proyecto</router-link></li>
          <li><router-link :to="{ name: 'evaluador-vivienda' }"><i class='bx bx-home'></i> Vivienda</router-link></li>
        </ul>
      </div>
      
      <hr class="divider" />

      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('ubicacion-evaluador')">
          <i class='bx  bx-location-plus'  ></i> 
          Ubicación
          <span>
            <i v-if="openMenu === 'ubicacion-evaluador'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'ubicacion-evaluador'" class="submenu">
          <li><router-link :to="{ name: 'evaluador-ubicacion-region' }"><i class='bx bxs-building-house'></i> Región</router-link></li>
          <li><router-link :to="{ name: 'evaluador-ubicacion-comuna' }"><i class='bx bx-buildings'></i> Comuna</router-link></li>
          <li><router-link :to="{ name: 'evaluador-ubicacion-direccion' }"><i class='bx bx-map-pin'></i> Dirección</router-link></li>
          <li><router-link :to="{ name: 'evaluador-ubicacion-zona-termica' }"><i class='bx bx-sun'></i> Zona Térmica</router-link></li>
        </ul>
      </div>

      <hr class="divider" />

      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('sistemas-energeticos-evaluador')">
          <i class="bx bx-bolt-circle sub-icon"></i>
          Sistemas Energéticos
          <span>
            <i v-if="openMenu === 'sistemas-energeticos-evaluador'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'sistemas-energeticos-evaluador'" class="submenu">
          <li><router-link :to="{name: 'evaluador-sistema-calefaccion' }"><i class='bx bxs-hot'></i> Calefacción</router-link></li>
          <li><router-link :to="{name: 'evaluador-sistema-ventilacion' }"><i class='bx bx-wind'></i> Ventilación</router-link></li>
          <li><router-link :to="{name: 'evaluador-sistema-acs' }"><i class='bx bxs-droplet-half'></i> Agua Caliente Sanitaria</router-link></li>
          <li><router-link :to="{name: 'evaluador-sistema-solar-calefaccion' }"><i class='bx bxs-sun'></i>Solar de Calefacción</router-link></li>
          <li><router-link :to="{name: 'evaluador-sistema-solar-iluminacion' }"><i class='bx bxs-bulb'></i> Solar de Iluminación</router-link></li>
        </ul>
      </div>

      <hr class="divider" />

      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('resultados-evaluador')">
          <i class="bx bx-bar-chart icon"></i>
          Resultados
          <span>
            <i v-if="openMenu === 'resultados-evaluador'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'resultados-evaluador'" class="submenu">
          <li>
            <a href="#" class="submenu-title" @click.prevent="toggleMenu('informes-sub')">
              <i class="bx bx-file-blank sub-icon"></i>
              Informes
              <span>
                <i v-if="openMenu === 'informes-sub'" class='bx bx-chevron-up'></i>
                <i v-else class='bx bx-chevron-down'></i>
              </span>
            </a>
            <ul v-show="openMenu === 'informes-sub'" class="sub-submenu">
              <li><router-link to="/evaluador/resultados/crear-informe">Crear/Modificar</router-link></li>
              <li><router-link to="/evaluador/resultados/historial-informes">Historial</router-link></li>
            </ul>
          </li>
          <li>
            <a href="#" class="submenu-title" @click.prevent="toggleMenu('etiquetas-sub')">
              <i class="bx bx-tag sub-icon"></i>
              Etiquetas
              <span>
                <i v-if="openMenu === 'etiquetas-sub'" class='bx bx-chevron-up'></i>
                <i v-else class='bx bx-chevron-down'></i>
              </span>
            </a>
            <ul v-show="openMenu === 'etiquetas-sub'" class="sub-submenu">
              <li><router-link to="/evaluador/resultados/editar-etiqueta">Editar Etiqueta</router-link></li>
              <li><router-link to="/evaluador/resultados/historial-etiquetas">Historial</router-link></li>
            </ul>
          </li>
        </ul>
      </div>

      <hr class="divider" />
    </template>

    <template v-else-if="rol === 'admin'">
      <div class="sidebar-header">
        <i class='bx bx-user-circle' style="font-size: 3rem; color: white;"></i>
        <span class="title">MENÚ ADMIN</span>
      </div>
      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('admin-usuarios')">
          <i class="bx bx-group icon"></i>
          Gestión de Usuarios
          <span>
            <i v-if="openMenu === 'admin-usuarios'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'admin-usuarios'" class="submenu">
          <li><router-link to="/admin/usuarios">Listar Usuarios</router-link></li>
          <li><router-link to="/admin/usuarios/crear">Crear Usuario</router-link></li>
        </ul>
      </div>

      <hr class="divider" />

      <div class="menu-section">
        <a href="#" class="menu-title" @click.prevent="toggleMenu('admin-reportes')">
          <i class="bx bx-stats icon"></i>
          Reportes y Auditoría
          <span>
            <i v-if="openMenu === 'admin-reportes'" class='bx bx-chevron-up'></i>
            <i v-else class='bx bx-chevron-down'></i>
          </span>
        </a>
        <ul v-show="openMenu === 'admin-reportes'" class="submenu">
          <li><router-link to="/admin/reportes">Reportes</router-link></li>
          <li><router-link to="/admin/auditoria">Auditoría</router-link></li>
        </ul>
      </div>
    </template>

    <div class="logout">
      <button @click="cerrarSesion" class="logout-button">
        <i class='bx bxs-lock-alt'></i> Cerrar sesión
      </button>
    </div>
  </div>
</template>

<script setup>
import {ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from '@/stores/Usuarios/useAuthStore'

const openMenu = ref(null)
const router = useRouter()
const route = useRoute()
const authStore = useAuthStore()
const rol = computed(() => route.meta.rol || null);

function toggleMenu(menuName) {
  openMenu.value = openMenu.value === menuName ? null : menuName;
}

function cerrarSesion() {
  authStore.logout();
  router.push('/login');
}

console.log("ROL DETECTADO EN SIDEBAR:", rol.value);
</script>

<style scoped>
.sidebar {
  width: 240px;
  background-color: #006666;
  padding: 15px;
  color: white;
  min-height: 95vh;
  border-top: 2px solid white;
  border-bottom: 2px solid white;
}
.sidebar-header {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 10px;
}
.sidebar h2 {
  font-size: 1.15em;
  margin-top: 18px;
  margin-bottom: 10px;
  text-align: left;
}
.sidebar ul {
  list-style: none;
  padding-left: 0;
  margin-bottom: 16px;
}
.sidebar ul li {
  margin-bottom: 8px;
}
.sidebar a,
.sidebar a:visited {
  color: white;
  text-decoration: none;
  padding: 3px 0;
  display: block;
}
.sidebar a:hover {
  color: #1870e4;
}
.submenu {
  padding-left: 0;
  background: #006666;
  border: 1px solid white;
  border-radius: 5px;
  margin-top: 5px;
}
.submenu li {
  padding-left: 18px;
}
.logout {
  margin-top: 20px;
}
.logout button {
  width: 100%;
  background: white;
  color: #006666;
  padding: 8px 12px;
  border: none;
  border-radius: 6px;
  margin-top: 50px;
  cursor: pointer;
  transition: background 0.2s;
}
.logout button:hover {
  background: #1870e4;
}
.logout-button {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
}
</style>