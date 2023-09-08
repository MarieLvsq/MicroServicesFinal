package com.urbanisation.orchestrateur.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "mcontrat", url = "http://localhost:9996/contrats")
public interface ContratProxy {

    @GetMapping(path = "/ajouterContrat")
    ResponseEntity<Void> creerContrat(@RequestParam Long numeroAssure);
}
