package com.urbanisationsi.microservices_assure.http.controleur;

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

import com.urbanisationsi.microservices_assure.Assure;
import com.urbanisationsi.microservices_assure.dao.AssureRepository;

@RestController  
@RequestMapping(path="/previt") 

public class AssureControleur {

	 @Autowired  
	    private AssureRepository assureRepository;

	    @PostMapping(path="/ajouterAssure")
	    public ResponseEntity<Void> creerAssure(@Valid  @RequestBody Assure assure) {
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
	    @GetMapping(path="/listerLesAssures")  
	    public @ResponseBody Iterable<Assure> getAllAssures() {
	        return assureRepository.findAll();
	    }
	    
	    @GetMapping(path = "/assuresNomPrenom/{nom}/{prenom}")
	    public @ResponseBody Iterable<Assure> getAssureNomPrenom(@PathVariable String nom, @PathVariable String prenom){
	    	return assureRepository.findByNomAndPrenom(nom, prenom);
	    }
	    
	    @DeleteMapping (path="/Assure/{id}")     
	       public void supprimerAssurer(@PathVariable Integer id) {
	        assureRepository.deleteById(id);        
	       }    
	    
	    @PutMapping (path="/modifierAssure")
	     public void modifierAssure(@RequestBody Assure assure) {
	     assureRepository.save(assure);
}
	    
	    
	    
}
