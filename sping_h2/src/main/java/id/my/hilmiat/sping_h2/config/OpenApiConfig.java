package id.my.hilmiat.sping_h2.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
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
