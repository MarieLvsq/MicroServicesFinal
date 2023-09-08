package com.urbanisation_si.microservices_contrat_mongodb.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "gestionprevdb")
public class Contrat {

	@Field("contratId")
    private Integer contratId;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateDebut;

	@NotNull
	@Indexed(unique = true, direction = IndexDirection.DESCENDING)
	private Long numeroContrat;

	@NotNull
	private Long numeroAssure;

	@NotNull
	private Long numeroProduit;

	// Getters & Setters
	
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public Integer getContratId() {
		return contratId;
	}

	public void setContratId(Integer contratId) {
		this.contratId = contratId;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Long getNumeroContrat() {
		return numeroContrat;
	}

	public void setNumeroContrat(Long numeroContrat) {
		this.numeroContrat = numeroContrat;
	}

	public Long getNumeroAssure() {
		return numeroAssure;
	}

	public void setNumeroAssure(Long numeroAssure) {
		this.numeroAssure = numeroAssure;
	}

	public Long getNumeroProduit() {
		return numeroProduit;
	}

	public void setNumeroProduit(Long numeroProduit) {
		this.numeroProduit = numeroProduit;
	}

}
