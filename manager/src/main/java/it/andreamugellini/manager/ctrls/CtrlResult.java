package it.andreamugellini.manager.ctrls;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.andreamugellini.manager.exc.ExCalculationNotFound;
import it.andreamugellini.manager.rps.RepoCalculation;
import it.andreamugellini.manager.rps.entity.EntCalculation;

@RestController
@RequestMapping("/results")
public class CtrlResult {
	
		@Autowired
		private  RepoCalculation repo;
	
	  @PutMapping(path = "/{id}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity createCalculation(@PathVariable String id, @RequestBody Double result) {
		  	
		  Optional<EntCalculation> c = repo.findById(id);		  
		  if(!c.isPresent()) throw new ExCalculationNotFound(id);
		  
		  c.get().setResult(result);
		  c.get().setResultDate(Calendar.getInstance().getTime());
		  repo.save(c.get());
				  
		  return ResponseEntity.ok("");		  
	        
	    }

}
