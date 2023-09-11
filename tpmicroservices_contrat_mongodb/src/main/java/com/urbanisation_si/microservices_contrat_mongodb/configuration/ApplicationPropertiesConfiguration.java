package com.urbanisation_si.microservices_contrat_mongodb.configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("urbanisation-si.clairprev")
public class ApplicationPropertiesConfiguration {
	private int limiteNombreContrat;

	public int getlimiteNombreContrat() {
		return limiteNombreContrat;
	}

	public void setlimiteNombreContrat(int limiteNombreContrat) {
		this.limiteNombreContrat = limiteNombreContrat;
	}

	
	
}
