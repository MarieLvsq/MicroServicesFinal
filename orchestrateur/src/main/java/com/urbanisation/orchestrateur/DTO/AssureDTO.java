/**
 * 
 */
package com.urbanisation.orchestrateur.DTO;

/**
 * @author fanti
 *pas d'annotation
 *un proxy patern = pseudo Objet
 *archiceture distribue= liaison entre 2 programme diff par API REST
 */
public class AssureDTO {

	private Long numeroAssure;
	private String nom;	
	private String prenom;
	
	
	public Long getNumeroAssure() {
		return numeroAssure;
	}
	public void setNumeroAssure(Long numeroAssure) {
		this.numeroAssure = numeroAssure;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
}
