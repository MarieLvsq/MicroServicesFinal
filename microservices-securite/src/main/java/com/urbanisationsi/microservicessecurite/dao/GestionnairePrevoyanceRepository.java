package com.urbanisationsi.microservicessecurite.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.urbanisationsi.microservicessecurite.model.GestionnairePrevoyance;

public interface GestionnairePrevoyanceRepository extends MongoRepository<GestionnairePrevoyance, String> {
	
	GestionnairePrevoyance findByEmail(String email);

}
