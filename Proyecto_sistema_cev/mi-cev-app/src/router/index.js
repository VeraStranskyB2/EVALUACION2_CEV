import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/Usuarios/useAuthStore'

import MainLayout from '@/layouts/MainLayout.vue'
import UsuarioView from '@/views/UsuarioView.vue'
import ClienteView from '@/views/ClienteView.vue'
import EvaluadorView from '@/views/EvaluadorView.vue'

const routes = [
  // PÁGINAS PÚBLICAS
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/HomeView.vue')
  },
  {
    path: '/quienes-somos',
    name: 'quienes-somos',
    component: () => import('@/views/QuienesSomosView.vue')
  },
  {
    path: '/buscar',
    name: 'buscar',
    component: () => import('@/views/BuscarView.vue')
  },
  {
    path: '/contacto',
    name: 'contacto',
    component: () => import('@/views/ContactoView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/auth/LoginView.vue')
  },
  {
    path: '/registro',
    component: () => import('@/views/auth/RegistroView.vue')
  },

  // CLIENTE
  {
    path: '/cliente',
    component: MainLayout, /* Carga el layout con la sidebar */
    meta: { requiresAuth: true, rol: 'cliente' }, // Cambia a false para no requerir autenticación
    children: [
      {
        path: '',
        name: 'cliente-home',
        component: ClienteView
      },

      //Proceso
      {
        path: 'solicitudes/solicitud',
        name: 'cliente-generar-solicitud',
        component: () => import('@/views/Cliente/GenerarSolicitudClienteView.vue')
      },
      {
        path: 'solicitudes/generarlista',
        name: 'cliente-lista-solicitudes',
        component: () => import('@/views/Cliente/ListaSolicitudesClienteView.vue')
      },
      {
        path: 'solicitudes/documentacion',
        name: 'cliente-subir-documento',
        component: () => import('@/views/Cliente/SubirDocumentoClienteView.vue')
      },

      /*
      {
        path: 'estado',
        name: 'cliente-estado-solicitud',
        component: () => import('@/views/Cliente/VerEstadoClienteView.vue')
      },
      */

      //ProyectoVivienda
      {
        path: 'Gestion/proyecto',
        name: 'cliente-proyecto',
        component: () => import('@/views/Cliente/RegistrarProyectoClienteView.vue')
      },
      {
        path: 'Gestion/vivienda',
        name: 'cliente-vivienda',
        component: () => import('@/views/Cliente/RegistrarViviendaClienteView.vue')
      },

      //Ubicacion
      /*{
        path: 'ubicacion/region',
        name: 'cliente-ubicacion-region',
        component: () => import('@/views/Cliente/RegistrarUbicacionClienteView.vue')
      },
      {
        path: 'ubicacion/comuna',
        name: 'cliente-ubicacion-comuna',
        component: () => import('@/views/Cliente/RegistrarComunaClienteView.vue')
      },
      */
      {
        path: 'ubicacion/ubicacion',
        name: 'cliente-ubicacion-ubicacion',
        component: () => import('@/views/Cliente/RegistrarUbicacionClienteView.vue')
      },
      /*
      {
        path: 'ubicacion/ZonaTermica',
        name: 'cliente-ubicacion-ZonaTermica',
        component: () => import('@/views/Cliente/RegistrarZonaTermicaClienteView.vue')
      },
      */

      //Resultados
      {
        path: 'resultados/informes',
        name: 'cliente-informes',
        component: () => import('@/views/Cliente/VerInformeClienteView.vue')
      },
      {
        path: 'resultados/etiquetas',
        name: 'cliente-etiquetas',
        component: () => import('@/views/Cliente/VerEtiquetaClienteView.vue')
      }
    ]
  },

  // EVALUADOR
  {
    path: '/evaluador',
    component: MainLayout,
    meta: { requiresAuth: true, rol: 'evaluador' },
    children: [
      {
        path: '',
        name: 'evaluador-home',
        component: EvaluadorView
      },

      //SOLICITUDES     
      {
        path: 'solicitudes/ver',
        name: 'evaluador-ver-solicitudes',
        component: () => import('@/views/Evaluador/VerSolicitudEvaluadorView.vue')
      },

      /*
      {
        path: 'solicitudes/aceptar-rechazar',
        name: 'evaluador-aceptar-rechazar',
        component: () => import('@/views/Evaluador/AceptarRechazarSolicitudEvaluadorView.vue')
      },
      */
      {
        path: 'solicitudes/estado',
        name: 'evaluador-estado-solicitud',
        component: () => import('@/views/Evaluador/CambiarEstadoEvaluadorView.vue')
      },
      {
        path: 'solicitudes/documentacion',
        name: 'evaluador-ver-documentos',
        component: () => import('@/views/Evaluador/DescargarDocumentoEvaluadorView.vue')
      },
      {
        path: 'solicitudes/evaluacion',
        name: 'evaluador-hacer-evaluacion',
        component: () => import('@/views/Evaluador/HacerEvaluacionEvaluadorView.vue')
      },      

      //GESTIÓN      
      {
        path: 'Gestion/proyecto',
        name: 'evaluador-proyecto',
        component: () => import('@/views/Evaluador/VerProyectoEvaluadorView.vue')
      },
      {
        path: 'Gestion/vivienda',
        name: 'evaluador-vivienda',
        component: () => import('@/views/Evaluador/VerViviendaEvaluadorView.vue')
      },

      //UBICACIÓN
          
      {
        path: 'ubicacion/region',
        name: 'evaluador-ubicacion-region',
        component: () => import('@/views/Evaluador/RegistrarRegionEvaluadorView.vue')
      },
      {
        path: 'ubicacion/comuna',
        name: 'evaluador-ubicacion-comuna',
        component: () => import('@/views/Evaluador/RegistrarComunaEvaluadorView.vue')
      },
      {
        path: 'ubicacion/direccion',
        name: 'evaluador-ubicacion-direccion',
        component: () => import('@/views/Evaluador/RegistrarDireccionEvaluadorView.vue')
      },
      {
        path: 'ubicacion/zona-termica',
        name: 'evaluador-ubicacion-zona-termica',
        component: () => import('@/views/Evaluador/RegistrarZonaTermicaEvaluadorView.vue')
      },
      

      //SISTEMAS ENERGÉTICOS      
      {
        path: 'sistemas/acs',
        name: 'evaluador-sistema-acs',
        component: () => import('@/views/Evaluador/RegistrarSistemaAguaCalienteSanitariaEvaluadorView.vue')
      },
      {
        path: 'sistemas/calefaccion',
        name: 'evaluador-sistema-calefaccion',
        component: () => import('@/views/Evaluador/RegistrarSistemaCalefaccionEvaluadorView.vue')
      },
      {
        path: 'sistemas/ventilacion',
        name: 'evaluador-sistema-ventilacion',
        component: () => import('@/views/Evaluador/RegistrarSistemaVentilacionEvaluadorView.vue')
      },
      {
        path: 'sistemas/solar-calefaccion',
        name: 'evaluador-sistema-solar-calefaccion',
        component: () => import('@/views/Evaluador/RegistrarSistemaSolarCalefaccionyACSEvaluadorView.vue')
      },
      {
        path: 'sistemas/solar-iluminacion',
        name: 'evaluador-sistema-solar-iluminacion',
        component: () => import('@/views/Evaluador/RegistrarSistemaSolardeIluminacionEvaluadorView.vue')
      },

      //RESULTADOS    
            {
        path: 'resultados/crear-informe',
        name: 'evaluador-crear-informe',
        component: () => import('@/views/Evaluador/CrearInformeEvaluadorView.vue')
      },

      /*
      {
        path: 'resultados/historial-informes',
        name: 'evaluador-historial-informes',
        component: () => import('@/views/Evaluador/HistorialInformesEvaluadorView.vue')
      },
      */
      {
        path: 'resultados/editar-etiqueta',
        name: 'evaluador-editar-etiqueta',
        component: () => import('@/views/Evaluador/EditarEtiquetaEvaluadorView.vue')
      },
      /*
      {
        path: 'resultados/historial-etiquetas',
        name: 'evaluador-historial-etiquetas',
        component: () => import('@/views/Evaluador/HistorialEtiquetasEvaluadorView.vue')
      },
      */


    ]
  },

  // USUARIO con layout
  {
    path: '/usuario',
    component: MainLayout,
    meta: { requiresAuth: false },
    children: [
      {
        path: '',
        name: 'usuario-home',
        component: UsuarioView
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,

/*
//GUARDIÁN DE NAVEGACIÓN
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  const isAuthenticated = authStore.isAuthenticated
  const userRole = authStore.getRol

  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ name: 'login' })
  } else if ((to.name === 'login' || to.name === 'registro') && isAuthenticated) {
    if (userRole === 'cliente') {
      next({ name: 'cliente-home' })
    } else if (userRole === 'evaluador') {
      next({ name: 'evaluador-home' })
    } else {
      next({ name: 'home' })
    }
  } else if (to.meta.rol && to.meta.rol !== userRole) {
    // Si intenta acceder a una ruta de otro rol
    if (userRole === 'cliente') {
      next({ name: 'cliente-home' })
    } else if (userRole === 'evaluador') {
      next({ name: 'evaluador-home' })
    } else {
      next({ name: 'home' })
    }
  } else {
    next()
  }

  */
})



export default router
