package it.andreamugellini.manager.ctrls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.andreamugellini.manager.rps.entity.EntCalculation;
import it.andreamugellini.manager.srvs.SrvManager;

@RestController
@RequestMapping("/calculations")
public class CtrlManager {

		@Autowired
		private SrvManager service;	    

	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public EntCalculation createCalculation(@RequestBody EntCalculation calculation) {
	        return this.service.create(calculation);
	    }

	    @GetMapping("/{id}")
	    public EntCalculation getCalculationById(@PathVariable Long id) {
	       return this.service.get(id);
	    }

	    @PutMapping("/{id}")
	    public EntCalculation updateCalculation(@PathVariable Long id, @RequestBody EntCalculation calculation) {
	       
	        return this.service.update(id, calculation);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteCalculation(@PathVariable Long id) {
	        this.service.delete(id);
	    }
	
	
	
}
