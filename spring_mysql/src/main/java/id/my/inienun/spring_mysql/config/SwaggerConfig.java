package id.my.inienun.spring_mysql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI(){
        return new OpenAPI()
        .components(
            new Components()
        )
        .info(
            new Info()
                .title("API test dengan springboot")
                .description("Ini API yang dibuat dengan springboot dan H2 database")
        );
    }
}
