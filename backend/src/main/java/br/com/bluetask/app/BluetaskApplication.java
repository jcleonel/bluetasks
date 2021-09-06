package br.com.bluetask.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BluetaskApplication {

	private static final Logger logger = LoggerFactory.getLogger(BluetaskApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BluetaskApplication.class, args);
		logger.info("Aplicação iniciada!");
	}
}
