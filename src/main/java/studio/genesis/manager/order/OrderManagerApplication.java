package studio.genesis.manager.order;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API", version = "2.0", description = "Order Manager API"))
public class OrderManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagerApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OrderManagerApplication.class);
    }
}
