package cl.iplacex.sistema_cev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig{
    //Método que configura CORS para la aplicación
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //Configura las reglas de CORS
                //Permite todas las rutas
                registry.addMapping("/**")
                //Reemplaza con tus origenes Permitidos
                .allowedOrigins("http://localhost:5173")
                //Métodos HTTP Permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Permite todos los encabezados
                .allowedHeaders("*")
                        // Expone encabezados específicos si es necesario 
                        .exposedHeaders("Authorization") 
                        // Permite el envío de credenciales (cookies, autenticación HTTP)
                        .allowCredentials(true) 
                        // Establece la duración de caché para la respuesta preflight (1 hora)
                        .maxAge(3600);
            }
            
        };
    }
}

