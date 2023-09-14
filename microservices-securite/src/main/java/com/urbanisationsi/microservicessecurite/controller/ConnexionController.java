package com.urbanisationsi.microservicessecurite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.urbanisationsi.microservicessecurite.model.GestionnairePrevoyance;
import com.urbanisationsi.microservicessecurite.service.GestionnairePrevoyanceService;

@Controller
public class ConnexionController {

	@Autowired
	GestionnairePrevoyanceService gestionnairePrevService;

	@RequestMapping(value = { "/", "/accueil" }, method = RequestMethod.GET)
	public ModelAndView accueil() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("accueil");
		return modelAndView;
	}

	@RequestMapping(value = "/connecter", method = RequestMethod.GET)
	public ModelAndView connecter() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("connecter"); // Nom de la vue pour la page /connecter
		return modelAndView;
	}

	@RequestMapping(value = "/enregistrer", method = RequestMethod.GET)
	public ModelAndView enregistrer() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("enregistrer"); // Nom de la vue pour la page /enregistrer
		modelAndView.addObject("gestionnairePrevoyance", new GestionnairePrevoyance()); // Instancier un
																						// GestionnairePrevoyance par
																						// défaut
		return modelAndView;
	}

	@RequestMapping(value = "/enregistrer", method = RequestMethod.POST)
	public ModelAndView creerGestionnairePrevoyance(@Valid GestionnairePrevoyance gestionnairePrevoyance,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		// Rechercher dans la base de données si un gestionnaire de prévoyance avec le
		// même email existe
		GestionnairePrevoyance existingGestionnaire = gestionnairePrevService
				.findByEmail(gestionnairePrevoyance.getEmail());

		if (existingGestionnaire != null) {
			// Si un gestionnaire de prévoyance avec le même email existe, ajoutez une
			// erreur au BindingResult
			bindingResult.rejectValue("email", "error.gestionnairePrevoyance",
					"Un gestionnaire avec cet e-mail existe déjà.");
		}

		// Vérifiez s'il y a des erreurs dans le BindingResult
		if (bindingResult.hasErrors()) {
			// S'il y a des erreurs, retournez à la vue "enregistrer" avec les erreurs
			modelAndView.setViewName("enregistrer");
		} else {
			// S'il n'y a pas d'erreurs, enregistrez le gestionnaire de prévoyance
			gestionnairePrevService.sauveGestionnairePrevoyance(gestionnairePrevoyance);

			// Activez la vue "connecter" après l'enregistrement réussi
			modelAndView.setViewName("connecter");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/afficher", method = RequestMethod.GET)
	public ModelAndView afficher() {
		ModelAndView modelAndView = new ModelAndView();

		// Récupérez l'objet d'autorisation (Authentication) de l'utilisateur
		// actuellement authentifié
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			// Récupérez le nom de l'utilisateur actuellement authentifié
			String userEmail = authentication.getName();

			// Appelez la recherche par e-mail dans la couche service en utilisant userEmail
			GestionnairePrevoyance gestionnairePrevoyance = gestionnairePrevService.findByEmail(userEmail);

			if (gestionnairePrevoyance != null) {
				modelAndView.addObject("gestionnairePrevoyance", gestionnairePrevoyance);
				modelAndView.setViewName("afficher"); // Définissez la vue à afficher
			} else {
				// Gestionnaire de prévoyance introuvable, gérer l'erreur ici
				modelAndView.addObject("error", "Gestionnaire de prévoyance introuvable.");
				modelAndView.setViewName("erreur"); // Définissez une vue d'erreur appropriée
			}
		} else {
			// Utilisateur non authentifié, gérer l'erreur ici
			modelAndView.addObject("error", "Utilisateur non authentifié.");
			modelAndView.setViewName("erreur"); // Définissez une vue d'erreur appropriée
		}

		return modelAndView;
	}

}
