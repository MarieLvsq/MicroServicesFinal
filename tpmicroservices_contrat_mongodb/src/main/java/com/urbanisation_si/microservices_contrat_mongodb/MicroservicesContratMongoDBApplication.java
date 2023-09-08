package com.urbanisation_si.microservices_contrat_mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MicroservicesContratMongoDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesContratMongoDBApplication.class, args);
	}

}
