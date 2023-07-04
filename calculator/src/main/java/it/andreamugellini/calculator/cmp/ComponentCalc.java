package it.andreamugellini.calculator.cmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.andreamugellini.calculator.cmp.bean.BeanCalculation;
import it.andreamugellini.calculator.srv.SrvCalcolator;

@Component
public class ComponentCalc {
	
	private static final Logger logger = LoggerFactory.getLogger(ComponentCalc.class);

	
	@Autowired
	private ObjectMapper jsonMapper;
	
	@Autowired
	private SrvCalcolator service;

	
	 @KafkaListener(topics = "evaluate-calc", groupId = "calcs")
	 public void receivedCal(String json) {
		 
		 
		 BeanCalculation decode = this.decode(json, BeanCalculation.class);
		 if(decode == null) return; 
		 
		
			service.calc(decode);
		
		
		 
		 
	 }

	
	
	public <T> T decode(String json, Class<T> clazz) {
		
	      try {
			return (T) jsonMapper.readValue(json, clazz);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();			
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}	
	     return null;
	}

}
