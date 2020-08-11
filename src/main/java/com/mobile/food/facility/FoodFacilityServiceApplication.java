package com.mobile.food.facility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class FoodFacilityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodFacilityServiceApplication.class, args);
	}
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Food Facility Service").apiInfo(apiInfo()).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Food Facility Micro Service").description("Zelish assignment\n")
				.license("Apache License Version 2.0").version("1.0").build();
	}

}
