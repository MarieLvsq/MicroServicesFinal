package com.urbanisation_si.microservices_produit.http.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbanisation_si.microservices_produit.configuration.ApplicationPropertiesConfiguration;
import com.urbanisation_si.microservices_produit.dao.ProduitRepository;
import com.urbanisation_si.microservices_produit.model.Produit;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/produits")
public class ProduitController {

	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	ApplicationPropertiesConfiguration appProperties;

	 @ApiOperation(value = "Ajoute un Produit.")    
	    @PostMapping(path="/ajouterProduit")
	    public ResponseEntity<Void> creerAssure(@Valid @RequestBody Produit produit) {
	        Produit produitAjoute = produitRepository.save(produit);

	             if (produitAjoute == null)
	                        return ResponseEntity.noContent().build();

	                URI uri = ServletUriComponentsBuilder
	                        .fromCurrentRequest()
	                        .path("/{id}")
	                        .buildAndExpand(produitAjoute.getId())
	                        .toUri();

	                return ResponseEntity.created(uri).build(); 
	    }
	 
	 @ApiOperation(value = "Affiche la liste des produits.")    
	    @GetMapping(path="/listerProduits")  
	    public @ResponseBody Iterable<Produit> getAllProduits() {
	        return produitRepository.findAll();
	    }
	 
	 @ApiOperation(value = "Affiche la liste des Assurés.")
		@GetMapping(path = "/listerLesProduits")
		public @ResponseBody Iterable<Produit> allProduits() {
			Iterable<Produit> produitsIterable = produitRepository.findAll();
			List<Produit> assuresList = StreamSupport.stream(produitsIterable.spliterator(), false)
					.collect(Collectors.toList());
			List<Produit> listeLimitee = assuresList.subList(0, appProperties.getLimiteNombreProduit());
			return listeLimitee;
		}
	 
	@GetMapping(path = "/chercherProduit/{numeroProduit}")
	public ResponseEntity<Produit> findProduitByNum(@PathVariable Long numeroProduit) {
		Produit produit = produitRepository.findByNumeroProduit(numeroProduit);

		if (produit != null) {
			return ResponseEntity.ok(produit);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}