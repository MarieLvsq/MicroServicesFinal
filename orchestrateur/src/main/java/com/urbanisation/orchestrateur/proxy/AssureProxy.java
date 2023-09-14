/**
 * 
 */
package com.urbanisation.orchestrateur.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.urbanisation.orchestrateur.DTO.AssureDTO;

/**
 * @author fanti
 *
 */
//@FeignClient(name = "msassure", url = "localhost:9999/previt")
@FeignClient(name = "zuul-server", decode404 = true)
@RibbonClient(name = "microservice-assure")
public interface AssureProxy {

	@GetMapping(path = "/microservice-assure/previt/assuresNomPrenom/{nom}/{prenom}")
	public @ResponseBody Iterable<AssureDTO> getAssureNomPrenom(@PathVariable String nom, @PathVariable String prenom);

	@GetMapping(path = "/microservice-assure/previt/listerLesAssures")
	public @ResponseBody Iterable<AssureDTO> getAllAssures();

}
