package it.andreamugellini.manager;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication
public class Application {
	

	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
//		boolean matches = Pattern.matches("\\d+(\\.\\d+)?([-+*\\/%]\\d+(\\.\\d+)?)*", "2+1");
//		System.out.println(matches);

	}

}
