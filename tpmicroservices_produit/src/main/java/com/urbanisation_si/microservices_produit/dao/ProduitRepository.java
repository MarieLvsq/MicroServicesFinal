package com.urbanisation_si.microservices_produit.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.urbanisation_si.microservices_produit.model.Produit;


public interface ProduitRepository extends CrudRepository<Produit, Integer> {
	@Query("SELECT p FROM Produit p WHERE p.numeroProduit = :numeroProduit")
	Produit findByNumeroProduit(@Param("numeroProduit") Long numeroProduit);
}