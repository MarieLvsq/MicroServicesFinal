package com.urbanisation.orchestrateur.DTO;

import java.time.LocalDate;

/**
 * @author Marie LEVESQUE pas d'annotation un proxy patern = pseudo Objet
 *         archiceture distribue= liaison entre 2 programme diff par API REST
 */
public class ContratDTO {

//	private Integer contratId;
	private LocalDate dateDebut;
	private Long numeroContrat;
	private Long numeroAssure;
	private Long numeroProduit;

	//Getters & Setters
	
//	public Integer getContratId() {
//		return contratId;
//	}
//
//	public void setContratId(Integer contratId) {
//		this.contratId = contratId;
//	}
	public LocalDate getDateDebut() {
		return dateDebut;
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
