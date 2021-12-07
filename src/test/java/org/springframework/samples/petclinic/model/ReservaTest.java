package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
class ReservaTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void ciudadVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Reserva reserva = new Reserva();
		LocalDate fechaInicio = LocalDate.now().plusYears(1);
		LocalDate fechaFin = fechaInicio.plusDays(10);
		reserva.setCiudad("");
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFin(fechaFin);
		reserva.setPrecioFinal(500.0);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Reserva>> constraintViolations = validator.validate(reserva);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Reserva> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("ciudad");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void fechaInicioVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Reserva reserva = new Reserva();
		LocalDate fechaInicio = LocalDate.now().plusYears(1);
		LocalDate fechaFin = fechaInicio.plusDays(10);
		reserva.setCiudad("Sevilla");
		reserva.setFechaInicio(null);
		reserva.setFechaFin(fechaFin);
		reserva.setPrecioFinal(500.0);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Reserva>> constraintViolations = validator.validate(reserva);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Reserva> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaInicio");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
	
	@Test
	void fechaFinVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Reserva reserva = new Reserva();
		LocalDate fechaInicio = LocalDate.now().plusYears(1);
		LocalDate fechaFin = fechaInicio.plusDays(10);
		reserva.setCiudad("Sevilla");
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFin(null);
		reserva.setPrecioFinal(500.0);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Reserva>> constraintViolations = validator.validate(reserva);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Reserva> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("fechaFin");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
	
	@Test
	void precioFinalErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Reserva reserva = new Reserva();
		LocalDate fechaInicio = LocalDate.now().plusYears(1);
		LocalDate fechaFin = fechaInicio.plusDays(10);
		reserva.setCiudad("Sevilla");
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFin(fechaFin);
		reserva.setPrecioFinal(-500.0);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Reserva>> constraintViolations = validator.validate(reserva);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Reserva> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("precioFinal");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 0");
	}
	
}
