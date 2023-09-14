package com.urbanisationsi.microservicessecurite.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class Role {

	@Id // Identifiant JPA
	private String id;

	@Indexed(unique = true) // Index unique sur le champ role
	private String role;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
