package com.urbanisation_si.microservices_produit.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("urbanisation-si.clairprev")
public class ApplicationPropertiesConfiguration {
	private int limiteNombreProduit;

	public int getLimiteNombreProduit() {
		return limiteNombreProduit;
	}

	public void setLimiteNombreProduit(int limiteNombreProduit) {
		this.limiteNombreProduit = limiteNombreProduit;
	}

	
}
