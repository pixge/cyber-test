package it.andreamugellini.manager.ctrls;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ValidatorOperations {
	
	
	
	public static boolean validate(String operation) {
		
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			engine.eval(operation);
		} catch (ScriptException e) {
			return false;
		}
		
		return true;
	}

}
