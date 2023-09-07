package com.urbanisation_si.microservices_assure.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Length(min=3, max=30, message = "Le nombre de caractères du nom de la personne doit être compris entre 3 et 30 au sens large.")    
	private String nom;

	private String prenom;

	private Long numeroPersonne;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateNaissance;
	
	
	//getters & setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Long getNumeroPersonne() {
		return numeroPersonne;
	}

	public void setNumeroPersonne(Long numeroPersonne) {
		this.numeroPersonne = numeroPersonne;
	}
}
