package com.urbanisation_si.microservices_contrat_mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.urbanisation_si.microservices_contrat_mongodb.model.Contrat;



public interface ContratRepository extends MongoRepository<Contrat, Integer>{

	Contrat findByNumeroContrat(Long numeroContrat);
	List<Contrat> findByNumeroAssure(Long numeroAssure);
	List<Contrat> findByNumeroProduit(Long numeroProduit);

}
