package com.urbanisation_si.microservices_contrat.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.urbanisation_si.microservices_contrat.model.Contrat;

public interface ContratRepository extends CrudRepository<Contrat, Integer>{

	Contrat findByNumeroContrat(Long numeroContrat);
	List<Contrat> findByNumeroAssure(Long numeroAssure);
	List<Contrat> findByNumeroProduit(Long numeroProduit);

}
