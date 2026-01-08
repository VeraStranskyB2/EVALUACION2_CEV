package cl.iplacex.sistema_cev.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor para funcionalidades de administración.
 * Implementa HandlerInterceptor para procesar solicitudes antes y después de los controladores.
 */

public class InterceptorAdmin implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Lógica a ejecutar antes de que la solicitud llegue al controlador
        // Retorna true para continuar con el procesamiento de la solicitud, false para detenerla
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Lógica a ejecutar después de que el controlador procese la solicitud, pero antes de que se renderice la vista
        // Útil para añadir atributos adicionales al modelo
    }
    

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Lógica a ejecutar después de que se complete todo el procesamiento de la solicitud
        // Se puede usar para tareas de limpieza o logging
    
    }
    
}


