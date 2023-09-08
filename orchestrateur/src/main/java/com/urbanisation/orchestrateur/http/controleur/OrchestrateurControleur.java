/**
 * 
 */
package com.urbanisation.orchestrateur.http.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.urbanisation.orchestrateur.DTO.AssureDTO;
import com.urbanisation.orchestrateur.proxy.AssureProxy;


/**
 * @author fanti
 *
 *il y pas d'api REST
 *
 */

@Controller
public class OrchestrateurControleur {
	
	 @Autowired  
	    private AssureProxy assureProxy;

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
//	log.info("---------------------------- Envoi requête vers saisirAssure");
	Iterable<AssureDTO> assures = assureProxy.getAssureNomPrenom(assureDto.getNom(), assureDto.getPrenom());
	model.addAttribute("assures", assures);
	return "ListeAssure";
}

}
