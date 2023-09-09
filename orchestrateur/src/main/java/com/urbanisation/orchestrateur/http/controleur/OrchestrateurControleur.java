/**
 * 
 */
package com.urbanisation.orchestrateur.http.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.urbanisation.orchestrateur.DTO.AssureDTO;
import com.urbanisation.orchestrateur.DTO.ContratDTO;
import com.urbanisation.orchestrateur.DTO.ProduitDTO;
import com.urbanisation.orchestrateur.proxy.AssureProxy;
import com.urbanisation.orchestrateur.proxy.ContratProxy;
import com.urbanisation.orchestrateur.proxy.ProduitProxy;

/**
 * @author fanti
 *
 *         il y pas d'api REST
 *
 */

@Controller
public class OrchestrateurControleur {

	@Autowired
	private AssureProxy assureProxy;

	@Autowired
	private ContratProxy contratProxy;

	@Autowired
	private ProduitProxy produitProxy;

	@GetMapping("/")
	public String index(Model m) {
		AssureDTO assureDto = new AssureDTO();
		m.addAttribute("recherche", assureDto);
		return "index";
	}

//methode pour afficher toute la liste des assurés
//	 @GetMapping("/")
//public String index(Model m) {
//	Iterable<AssureDTO> listeAssureDTO = assureProxy.getAllAssures();
//	m.addAttribute("marie", listeAssureDTO);
//	
//	return "index";
//}

	@PostMapping(value = "/saisir-assure")
	public String saisirAssure(AssureDTO assureDto, Model model) {
		Iterable<AssureDTO> assures = assureProxy.getAssureNomPrenom(assureDto.getNom(), assureDto.getPrenom());
		model.addAttribute("assures", assures);
		return "ListeAssure";
	}

	@GetMapping(value = "/ajouterContrat/{numeroAssure}")
	public String creerContrat(@PathVariable Long numeroAssure, Model m) {
		// Use the ContratProxy to make a POST request to the external service
		ResponseEntity<Void> response = contratProxy.creerContrat(numeroAssure);

		Iterable<ProduitDTO> produits = produitProxy.getAllProduits();
		m.addAttribute("numeroAssure", numeroAssure);
		m.addAttribute("produits", produits);
		return "contratAssure";
	}

	@GetMapping(value = "/affecterNumeroProduit/{numeroAssure}/{numeroProduit}")
	public String affecterNumeroProduit(@PathVariable Long numeroAssure, @PathVariable Long numeroProduit, Model m) {
		// Use the ContratProxy to make a request to assign the product to the existing
		// contract
		ResponseEntity<Void> response = contratProxy.affecterNumeroProduit(numeroAssure, numeroProduit);
		m.addAttribute("numeroAssure", numeroAssure);
		m.addAttribute("numeroProduit", numeroProduit);
		return "contratProduit";
	}

}
