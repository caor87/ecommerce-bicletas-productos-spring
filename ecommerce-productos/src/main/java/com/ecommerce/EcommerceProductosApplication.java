package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

public class EcommerceProductosApplication {

	public Docket productsApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select() 
	        .apis(RequestHandlerSelectors.any()) 
	        .paths(PathSelectors.any()) 
	        .build();
	}
	public static void main(String[] args) {
		SpringApplication.run(EcommerceProductosApplication.class, args);
	}

}