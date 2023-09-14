package com.urbanisation.orchestrateur.configuration;

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
