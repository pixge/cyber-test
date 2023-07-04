package it.andreamugellini.manager.ctrls;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.andreamugellini.manager.ctrls.dto.DTOCalculation;
import it.andreamugellini.manager.exc.ExWrongParameter;
import it.andreamugellini.manager.srvs.SrvManager;

@RestController
@RequestMapping("/calculations")
public class CtrlManager {

		@Autowired
		private SrvManager service;	 
		
		
//		private final String pattern = "\\d+(\\.\\d+)?([-+*\\/%]\\d+(\\.\\d+)?)*"; 

	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<DTOCalculation> createCalculation(@RequestBody DTOCalculation dto) {

	    	
	    	if(!ValidatorOperations.validate(dto.getOperation())) throw new ExWrongParameter("Operation not valid");
	    	
	    	DTOCalculation created = this.service.create(dto);
	        return ResponseEntity.ok(created);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<DTOCalculation>  getCalculationById(@PathVariable String id) {
	        DTOCalculation getted = this.service.get(id);	        
	        return ResponseEntity.ok(getted);

	    }    
	

	    @PutMapping("/{id}")
	    public ResponseEntity<DTOCalculation>  updateCalculation(@PathVariable String id, @RequestBody DTOCalculation dto) {
	       
	    	if(!ValidatorOperations.validate(dto.getOperation())) throw new ExWrongParameter("Operation not valid");
	    	
	         DTOCalculation updated = this.service.update(id, dto);	         
		     return ResponseEntity.ok(updated);

	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String>  deleteCalculation(@PathVariable String id) {
	        
	    	this.service.delete(id);
	        return ResponseEntity.ok("");
	    }
	
	
	
}
