package com.graphixstory.usuarios.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfi {

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                        .title("Api 2025 Usuarios")  
                        .version("version 1.0")
                        .description("Documentacion de todos los metodos de usarios")) ;  


    }

}
