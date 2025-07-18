package fr.eni.caveavin.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/*
 * Configuration globale de Swagger
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Une superbe documentation pour un superbe projet",
                description = "Exemple de documentation de la cave à vin avec Open API",
                version = "1.0"
        )
)
public class OpenApiConfig {
}
