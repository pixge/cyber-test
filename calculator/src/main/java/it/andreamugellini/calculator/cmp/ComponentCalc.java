package it.andreamugellini.calculator.cmp;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.andreamugellini.calculator.cmp.bean.BeanCalculation;

@Component
public class ComponentCalc {
	
	private static final Logger logger = LoggerFactory.getLogger(ComponentCalc.class);

	@Autowired
    private ObjectMapper jsonMapper;

	
	 @KafkaListener(topics = "evaluate-calc", groupId = "calcs")
	 public void receivedCal(String json) {
		 
		  ScriptEngineManager manager = new ScriptEngineManager();
	      ScriptEngine engine = manager.getEngineByName("JavaScript");
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      BeanCalculation calculation;
	      try {	          
	          calculation = objectMapper.readValue(json, BeanCalculation.class);	          

	          Object res = engine.eval(calculation.getOperation());
	          if (res instanceof Integer) calculation.setResult(((Integer) res).doubleValue());
	          else calculation.setResult((Double) res);
	        	
	          this.sendResult(calculation);				
				
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
	 } catch (Exception e) {
		 e.printStackTrace();
		 return;
	 }
		
		 
		 
	 }

	private void sendResult(BeanCalculation calculation) {
		RestTemplate restTemplate = new RestTemplate();
		
		
		logger.info("Sending "+calculation.getId()+" "+calculation.getResult());

        String url = "http://localhost:8081/results";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        
        
        
        

        String jsonPayload;
		try {
			jsonPayload = this.jsonMapper.writeValueAsString(calculation);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}

        // Create a HttpEntity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonPayload, headers);

        // Send the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Get the response body
        String responseBody = responseEntity.getBody();

        // Print the response body
        System.out.println(responseBody);
		
	}

}
