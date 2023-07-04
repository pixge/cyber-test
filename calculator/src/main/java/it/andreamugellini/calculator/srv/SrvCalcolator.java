package it.andreamugellini.calculator.srv;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.andreamugellini.calculator.cmp.bean.BeanCalculation;
import jakarta.annotation.PostConstruct;

@Service
public class SrvCalcolator {

	private static final Logger logger = LoggerFactory.getLogger(SrvCalcolator.class);
	
	@Autowired
	private ObjectMapper jsonMapper;
	
	
	@Value("${ENDPOINT_MANAGER}")
	String ENDPOINT_MANAGER; 

	
	@PostConstruct
	private void init() {
		if(this.ENDPOINT_MANAGER == null) throw new RuntimeException("ENDPOINT_MANAGER IS NOT DEFINED "+ENDPOINT_MANAGER);
	}
	
	

	public void calc(BeanCalculation calc){

		try {

			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			Object res = engine.eval(calc.getOperation());
			
			if (res instanceof Integer)
				calc.setResult(((Integer) res).doubleValue());
			else  
				calc.setResult((Double) res);
			
			
			sendResult(calc);
			
			
		} catch (ScriptException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (ClassCastException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private void sendResult(BeanCalculation calc) {
		RestTemplate restTemplate = new RestTemplate();
		
		
		String url = ENDPOINT_MANAGER+"/"+calc.getId();
		logger.info("Sending "+calc.getId()+" "+calc.getResult()+" to "+url);

		
        

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        
        
        
        

//        String jsonPayload;
//		try {
//			jsonPayload = this.jsonMapper.writeValueAsString(calc);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return;
//		}


        HttpEntity<Double> requestEntity = new HttpEntity<Double>(calc.getResult(), headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        
	}



}
