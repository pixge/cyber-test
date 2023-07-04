package it.andreamugellini.manager.exc;

public class ExCalculationNotFound extends RuntimeException {
	
	public ExCalculationNotFound(String id) {
		super("Id not found "+id);
	}
}
