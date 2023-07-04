package it.andreamugellini.manager.ctrls.adv;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.andreamugellini.manager.exc.ExCalculationNotFound;
import it.andreamugellini.manager.exc.ExWrongParameter;

@ControllerAdvice
public class AdvManager {
	
	  @ResponseBody
	  @ExceptionHandler(ExCalculationNotFound.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String calculatoinsNotFound(ExCalculationNotFound ex) {
	    return ex.getMessage();
	  }
	  
	  @ResponseBody
	  @ExceptionHandler(ExWrongParameter.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  String exWrongParameter(ExWrongParameter ex) {
	    return ex.getMessage();
	  }
	  
	  
	  

}
