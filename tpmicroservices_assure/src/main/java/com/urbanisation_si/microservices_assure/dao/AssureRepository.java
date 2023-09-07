package com.urbanisation_si.microservices_assure.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.urbanisation_si.microservices_assure.model.Assure;

public interface AssureRepository extends CrudRepository<Assure, Integer>{

	List<Assure> findByNomAndPrenom(String nom, String prenom);

}
