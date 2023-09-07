package com.urbanisation_si.microservices_assure.http.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbanisation_si.microservices_assure.dao.AssureRepository;
import com.urbanisation_si.microservices_assure.model.Assure;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "API pour les opérations CRUD pour les assurés")  
@RestController  
@RequestMapping(path="/previt")  
public class AssureController {


	    @Autowired
	    private AssureRepository assureRepository;

	    @ApiOperation(value = "Ajoute un Assuré.")    
	    @PostMapping(path="/ajouterAssure")
	    public ResponseEntity<Void> creerAssure(@Valid @RequestBody Assure assure) {
	        Assure assureAjoute = assureRepository.save(assure);

	             if (assureAjoute == null)
	                        return ResponseEntity.noContent().build();

	                URI uri = ServletUriComponentsBuilder
	                        .fromCurrentRequest()
	                        .path("/{id}")
	                        .buildAndExpand(assureAjoute.getId())
	                        .toUri();

	                return ResponseEntity.created(uri).build(); 
	    }

	    @ApiOperation(value = "Affiche la liste des Assurés.")    
	    @GetMapping(path="/listerLesAssures")  
	    public @ResponseBody Iterable<Assure> getAllAssures() {
	        return assureRepository.findAll();
	    }

	    @ApiOperation(value = "Recherche un assuré grâce à ses nom puis prenom à condition que celui-ci existe.")    
	    @GetMapping(path = "/assuresNomPrenom/{nom}/{prenom}")
	    public @ResponseBody Iterable<Assure> getAssureNomPrenom(@PathVariable String nom, @PathVariable String prenom){
	    	return assureRepository.findByNomAndPrenom(nom, prenom);
	    }
	    
	    @ApiOperation(value = "Recherche un assuré grâce à son ID à condition que celui-ci existe.")    
	    @DeleteMapping (path="/Assure/{id}")     
	       public void supprimerAssure(@PathVariable Integer id) {
	        assureRepository.deleteById(id);        
	       }
	    
	    @ApiOperation(value = "Modifie une ou plusieurs informations d'un assuré.")    
	    @PutMapping (path="/modifierAssure")    
	      public void modifierAssure(@RequestBody Assure assure) {
	        assureRepository.save(assure);
	      }
}
