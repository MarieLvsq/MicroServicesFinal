package com.urbanisation_si.microservices_contrat_mongodb.configuration;

import org.springframework.context.annotation.Configuration;
import brave.sampler.Sampler;

/**
 * @author Patrice
 *
 */
@Configuration
public class SleuthConfig {
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}