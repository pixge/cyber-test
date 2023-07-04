package it.andreamugellini.manager.rps.entity;


import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "calculations")

public class EntCalculation {
	
    @Id      
    private String id;
    private String operation;    
    private Double result;
    private Date creationDate = Calendar.getInstance().getTime();
    private Date resultDate;
	
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
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}

	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public Date getResultDate() {
		return resultDate;
	}
	
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
    
    

}
