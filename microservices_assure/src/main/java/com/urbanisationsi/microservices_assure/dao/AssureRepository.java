package com.urbanisationsi.microservices_assure.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.urbanisationsi.microservices_assure.Assure;

public interface AssureRepository extends CrudRepository<Assure, Integer> {
	List<Assure> findByNomAndPrenom(String nom, String prenom);

}
