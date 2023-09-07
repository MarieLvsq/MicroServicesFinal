package com.urbanisation_si.microservices_assure.model;

import javax.persistence.Entity;


@Entity
public class Assure extends Personne {
	
	
	private String dossierMedical;
	
	private Long numeroAssure;

	
	
	//getters & setters
	
	public String getDossierMedical() {
		return dossierMedical;
	}

	public void setDossierMedical(String dossierMedical) {
		this.dossierMedical = dossierMedical;
	}

	public Long getNumeroAssure() {
		return numeroAssure;
	}

	public void setNumeroAssure(Long numeroAssure) {
		this.numeroAssure = numeroAssure;
	}
	
	
}
