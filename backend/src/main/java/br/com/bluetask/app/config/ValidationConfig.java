package br.com.bluetask.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig implements RepositoryRestConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(ValidationConfig.class);

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		Validator validator = validator();

		validatingListener.addValidator("beforeCreate", validator);
		validatingListener.addValidator("beforeSave", validator);

		logger.info("Configura��o de valida��es... OK!");
	}

}
