package com.store.sportswear.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.PathSelectors.regex;

//http://localhost:8080/v2/api-docs
//http://localhost:8080/swagger-ui.html
@EnableWebMvc
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.store.sportswear.api"))
                .paths(PathSelectors.any())
                .build();
    }

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            SwaggerResource wsResource = new SwaggerResource();
            wsResource.setName("api2");
            wsResource.setSwaggerVersion("2.0");
            wsResource.setLocation("/swagger-api/swagger.yaml");
            resources.add(wsResource);
            return resources;
        };
    }
}
