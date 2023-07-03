package it.andreamugellini.manager.rps.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "calculations")

public class EntCalculation {
	
    @Id  
    
    private String id;

    private String operation;    
    private double result;
	
    public String getId() {
		return id;
	}
    
    public void setId(String id) {
		this.id = id;
	}
    
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}	
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
    
    
    

}
