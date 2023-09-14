package com.urbanisationsi.microservicessecurite.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gestionprevdb")
public class GestionnairePrevoyance {

	@Id // Identifiant JPA
	private String id;
	@Indexed(unique = true)
	private String email;
	private String nomcomplet;
	private String motdepasse;
	private boolean active;
	@DBRef
	private Set<Role> roles;

	public GestionnairePrevoyance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GestionnairePrevoyance(String id, String email, String nomcomplet, String motdepasse, boolean active) {
		super();
		this.id = id;
		this.email = email;
		this.nomcomplet = nomcomplet;
		this.motdepasse = motdepasse;
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomcomplet() {
		return nomcomplet;
	}

	public void setNomcomplet(String nomcomplet) {
		this.nomcomplet = nomcomplet;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
