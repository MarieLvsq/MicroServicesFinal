package com.urbanisation.orchestrateur.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.urbanisation.orchestrateur.DTO.ProduitDTO;

@FeignClient(name = "zuul-server", decode404 = true)
@RibbonClient(name ="microservice-produit")
public interface ProduitProxy {

	@GetMapping(path = "/microservice-produit/produits/listerProduits")
	public @ResponseBody Iterable<ProduitDTO> getAllProduits();
}
