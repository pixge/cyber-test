package it.andreamugellini.manager.exc;

public class ExWrongParameter extends RuntimeException {
	
	public ExWrongParameter(String param) {
		super("Wrong Parameters: "+param);
	}
}