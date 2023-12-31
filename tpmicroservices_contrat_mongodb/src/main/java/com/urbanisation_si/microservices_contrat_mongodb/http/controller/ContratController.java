package com.urbanisation_si.microservices_contrat_mongodb.http.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbanisation_si.exceptions.ResourceNotFoundException;
import com.urbanisation_si.microservices_contrat_mongodb.configuration.ApplicationPropertiesConfiguration;
import com.urbanisation_si.microservices_contrat_mongodb.dao.ContratRepository;
import com.urbanisation_si.microservices_contrat_mongodb.model.Contrat;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/contrats")
public class ContratController {

	@Autowired
	private ContratRepository contratRepository;

	@Autowired
	private ApplicationPropertiesConfiguration appProperties;
	
//	@ApiOperation(value = "Ajoute un Contrat.")
//	@PostMapping(path = "/ajouterContrat")
//	public ResponseEntity<Void> creerContrat(@Valid @RequestBody Contrat contrat) {
//		Contrat contratAjoute = contratRepository.save(contrat);
//
//		if (contratAjoute == null)
//			return ResponseEntity.noContent().build();
//
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(contratAjoute.getContratId()).toUri();
//
//		return ResponseEntity.created(uri).build();
//	}

	@ApiOperation(value = "Ajoute un Contrat.")
	@GetMapping(path = "/ajouterContrat")
	public ResponseEntity<Void> creerContrat(@RequestParam Long numeroAssure) {
		// Create a new Contrat instance and set the necessary data
		Contrat contrat = new Contrat();
		contrat.setNumeroAssure(numeroAssure);

		// Save the contract
		Contrat contratAjoute = contratRepository.save(contrat);

		if (contratAjoute == null) {
			return ResponseEntity.noContent().build();
		}

		// Respond with a URI to the newly created contract
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(contratAjoute.getContratId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Affecte un numéro de produit à un contrat existant")
	@GetMapping(path = "/affecterNumeroProduit/{numeroAssure}/{numeroProduit}")
	public ResponseEntity<Void> affecterNumeroProduit(@RequestParam Long numeroAssure,
			@RequestParam Long numeroProduit) {
		// Fetch the contract by numeroAssure (assuming you have a method to do this)
		Contrat contrat = contratRepository.findByNumeroAssure(numeroAssure);

		if (contrat != null) {
			// Assign the product to the existing contract
			contrat.setNumeroProduit(numeroProduit);
			contratRepository.save(contrat);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build(); // Contract with the given numeroAssure not found
		}
	}

	@ApiOperation(value = "Affiche la liste des contrats.")
	@GetMapping(path = "/listerContrats")
	public @ResponseBody Iterable<Contrat> getAllContrats() {
		Iterable<Contrat> contratsIterable = contratRepository.findAll();
		List<Contrat> contratsList = StreamSupport.stream(contratsIterable.spliterator(), false)
				.collect(Collectors.toList());
		List<Contrat> listeLimitee = contratsList.subList(0, appProperties.getlimiteNombreContrat());
		return listeLimitee;
	}

	@ApiOperation(value = "Trouve un contrat par numéro de contrat.")
	@GetMapping(path = "/trouverContrat/{numeroContrat}")
	public ResponseEntity<Contrat> findContratByNumeroContrat(@PathVariable Long numeroContrat) {
		Contrat contrat = contratRepository.findByNumeroContrat(numeroContrat);

		if (contrat != null) {
			return ResponseEntity.ok(contrat);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "Trouve un contrat par numéro d'assuré.")
	@GetMapping(path = "/trouverContratAssure/{numeroAssure}")
	public ResponseEntity<?> findContratByNumeroAssure(@PathVariable Long numeroAssure) {
		Contrat contrat = contratRepository.findByNumeroAssure(numeroAssure);

		if (contrat != null) {
			return ResponseEntity.ok(contrat);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND) // Use 404 Not Found status code
					.body("Contrat not found with numeroAssure: " + numeroAssure);
		}
	}

//	@ApiOperation(value = "Trouve un contrat par numéro d'assuré.")
//	@GetMapping(path = "/trouverContratAssure/{numeroAssure}")
//	public ResponseEntity<?> findContratByNumeroAssure(@PathVariable Long numeroAssure) {
//		try {
//			Iterable<Contrat> produits = contratRepository.findByNumeroAssure(numeroAssure);
//			return ResponseEntity.ok(produits);
//		} catch (ResourceNotFoundException ex) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND) // Use 404 Not Found status code
//					.body("Produit not found with numeroProduit: " + numeroAssure);
//		}
//	}

	@ApiOperation(value = "Trouve un contrat par numéro de produit.")
	@GetMapping(path = "/trouverContratProduit/{numeroProduit}")
	public ResponseEntity<?> findByNumeroProduit(@PathVariable Long numeroProduit) {
		try {
			Iterable<Contrat> contrats = contratRepository.findByNumeroProduit(numeroProduit);
			return ResponseEntity.ok(contrats);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND) // Use 404 Not Found status code
					.body("Produit not found with numeroProduit: " + numeroProduit);
		}
	}

	@ApiOperation(value = "Supprime un Contrat grâce à son ID")
	@DeleteMapping(path = "/deleteContrat/{id}")
	public void supprimerContrat(@PathVariable Integer id) {
		contratRepository.deleteById(id);
	}

	@ApiOperation(value = "Modifie un Contrat")
	@PutMapping(path = "/modifierContrat")
	public void modifierContrat(@RequestBody Contrat contrat) {

		contratRepository.save(contrat);
	}
}
