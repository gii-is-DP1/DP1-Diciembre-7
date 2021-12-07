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
class OficinaTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void ciudadVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Oficina oficina = new Oficina();
		Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
		oficina.setCiudad("");
		oficina.setCodigoPostal(41400);
		oficina.setDireccion("Calle Prueba");
		oficina.setVehiculos(vehiculos);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Oficina>> constraintViolations = validator.validate(oficina);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Oficina> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("ciudad");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void direccionVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Oficina oficina = new Oficina();
		Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
		oficina.setCiudad("Sevilla");
		oficina.setCodigoPostal(41400);
		oficina.setDireccion("");
		oficina.setVehiculos(vehiculos);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Oficina>> constraintViolations = validator.validate(oficina);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Oficina> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("direccion");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	
	
	
}
