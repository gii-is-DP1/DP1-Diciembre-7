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
class VehiculoTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void marcaVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("");
		vehiculo.setModelo("Ibiza");
		vehiculo.setPrecioBase(100.0);
		vehiculo.setPrecioPorDia(10.0);
		vehiculo.setStock(100);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculo>> constraintViolations = validator.validate(vehiculo);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vehiculo> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("marca");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void modeloVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("Seat");
		vehiculo.setModelo("");
		vehiculo.setPrecioBase(100.0);
		vehiculo.setPrecioPorDia(10.0);
		vehiculo.setStock(100);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculo>> constraintViolations = validator.validate(vehiculo);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vehiculo> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("modelo");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void precioBaseErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("Seat");
		vehiculo.setModelo("Ibiza");
		vehiculo.setPrecioBase(-100.0);
		vehiculo.setPrecioPorDia(10.0);
		vehiculo.setStock(100);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculo>> constraintViolations = validator.validate(vehiculo);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vehiculo> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("precioBase");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 0");
	}
	
	@Test
	void precioPorDiaErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("Seat");
		vehiculo.setModelo("Ibiza");
		vehiculo.setPrecioBase(100.0);
		vehiculo.setPrecioPorDia(-10.0);
		vehiculo.setStock(100);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculo>> constraintViolations = validator.validate(vehiculo);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vehiculo> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("precioPorDia");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 0");
	}
	
	@Test
	void stockErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setMarca("Seat");
		vehiculo.setModelo("Ibiza");
		vehiculo.setPrecioBase(100.0);
		vehiculo.setPrecioPorDia(10.0);
		vehiculo.setStock(0);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculo>> constraintViolations = validator.validate(vehiculo);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Vehiculo> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("stock");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 1");
	}
	
	
}
