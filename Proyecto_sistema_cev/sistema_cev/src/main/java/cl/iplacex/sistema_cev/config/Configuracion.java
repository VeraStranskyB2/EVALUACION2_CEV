package cl.iplacex.sistema_cev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//Indica que esta clase es una configuración de Spring
@Configuration
public class Configuracion {
    
    //Define un bean para ser utilizado en la aplicación
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
