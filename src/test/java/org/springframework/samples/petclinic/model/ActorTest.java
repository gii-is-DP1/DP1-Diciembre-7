package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ActorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void nombreVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Actor actor = new Actor();
		actor.setNombre("");
		actor.setEmail("aa@aa.com");
		actor.setTelefono("123456789");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Actor>> constraintViolations = validator.validate(actor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Actor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombre");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	
	@Test
	void emailVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Actor actor = new Actor();
		actor.setNombre("Prueba");
		actor.setEmail("");
		actor.setTelefono("123456789");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Actor>> constraintViolations = validator.validate(actor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Actor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void emailErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Actor actor = new Actor();
		actor.setNombre("Prueba");
		actor.setEmail("aaaa.com");
		actor.setTelefono("123456789");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Actor>> constraintViolations = validator.validate(actor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Actor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("must be a well-formed email address");
	}
	
	@Test
	void telefonoVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Actor actor = new Actor();
		actor.setNombre("Prueba");
		actor.setEmail("aa@aa.com");
		actor.setTelefono("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Actor>> constraintViolations = validator.validate(actor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Actor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("numeric value out of bounds (<9 digits>.<0 digits> expected)");
	}
	
	@Test
	void telefonoErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Actor actor = new Actor();
		actor.setNombre("Prueba");
		actor.setEmail("aa@aa.com");
		actor.setTelefono("abc12");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Actor>> constraintViolations = validator.validate(actor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Actor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("numeric value out of bounds (<9 digits>.<0 digits> expected)");
	}
	
	
	
}
