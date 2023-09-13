package com.urbanisation_si.microservices_produit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class MicroservicesProduitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesProduitApplication.class, args);

	}

}