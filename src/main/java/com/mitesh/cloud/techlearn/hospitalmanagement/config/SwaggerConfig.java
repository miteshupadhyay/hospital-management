package com.mitesh.cloud.techlearn.hospitalmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;
import com.mitesh.cloud.techlearn.hospitalmanagement.constant.SwaggerConstant;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiVersion1() {
		return new Docket(DocumentationType.SWAGGER_2)
					.groupName("v1")
					.select()
					.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
					.apis(RequestHandlerSelectors.basePackage("com.mitesh.cloud.techlearn.hospitalmanagement"))
					.paths(PathSelectors.any())
					.build()
					.useDefaultResponseMessages(false)
					.apiInfo(apiInfo("v1"));
					//.tags(new Tag("Patient","PatientController"));
			}
	 
		private ApiInfo apiInfo(final String version) {
			return new ApiInfoBuilder()
						.title("Hospital Management Microservice")
						.description("This Service will hold the details of Patient, doctors & Appointment")
						.termsOfServiceUrl("https://www.mitesh.com")
						.version(version)
						.build();
		}
	
}
