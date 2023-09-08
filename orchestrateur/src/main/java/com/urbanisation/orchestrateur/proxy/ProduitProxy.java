package com.urbanisation.orchestrateur.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.urbanisation.orchestrateur.DTO.ProduitDTO;

@FeignClient(name = "mproduit", url = "localhost:9998/produits")
public interface ProduitProxy {

	@GetMapping(path = "/listerProduits")
	public @ResponseBody Iterable<ProduitDTO> getAllProduits();
}
