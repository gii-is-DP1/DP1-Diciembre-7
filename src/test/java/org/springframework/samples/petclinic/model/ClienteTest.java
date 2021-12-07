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
class ClienteTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void dniVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Cliente cliente = new Cliente();
		Set<Reserva> reservas = new HashSet<Reserva>();
		cliente.setDni("");
		cliente.setNombre("Prueba");
		cliente.setEmail("prueba@prueba.com");
		cliente.setTelefono("123456789");
		cliente.setDireccion("Calle Prueba");
		cliente.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cliente> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void nombreVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Cliente cliente = new Cliente();
		Set<Reserva> reservas = new HashSet<Reserva>();
		cliente.setDni("12345678A");
		cliente.setNombre("");
		cliente.setEmail("prueba@prueba.com");
		cliente.setTelefono("123456789");
		cliente.setDireccion("Calle Prueba");
		cliente.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cliente> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombre");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void emailVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Cliente cliente = new Cliente();
		Set<Reserva> reservas = new HashSet<Reserva>();
		cliente.setDni("12345678A");
		cliente.setNombre("Prueba");
		cliente.setEmail("");
		cliente.setTelefono("123456789");
		cliente.setDireccion("Calle Prueba");
		cliente.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cliente> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void emailErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Cliente cliente = new Cliente();
		Set<Reserva> reservas = new HashSet<Reserva>();
		cliente.setDni("12345678A");
		cliente.setNombre("Prueba");
		cliente.setEmail("pruebaprueba.com");
		cliente.setTelefono("123456789");
		cliente.setDireccion("Calle Prueba");
		cliente.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cliente> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("must be a well-formed email address");
	}
	
	@Test
	void telefonoVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Cliente cliente = new Cliente();
		Set<Reserva> reservas = new HashSet<Reserva>();
		cliente.setDni("12345678A");
		cliente.setNombre("Prueba");
		cliente.setEmail("prueba@prueba.com");
		cliente.setTelefono("");
		cliente.setDireccion("Calle Prueba");
		cliente.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		Iterator<ConstraintViolation<Cliente>> it = constraintViolations.iterator();
		ConstraintViolation<Cliente> violation = it.next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("numeric value out of bounds (<9 digits>.<0 digits> expected)"); 
	}
	
	@Test
	void telefonoErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Cliente cliente = new Cliente();
		Set<Reserva> reservas = new HashSet<Reserva>();
		cliente.setDni("12345678A");
		cliente.setNombre("Prueba");
		cliente.setEmail("prueba@prueba.com");
		cliente.setTelefono("123abc");
		cliente.setDireccion("Calle Prueba");
		cliente.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		Iterator<ConstraintViolation<Cliente>> it = constraintViolations.iterator();
		ConstraintViolation<Cliente> violation = it.next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("numeric value out of bounds (<9 digits>.<0 digits> expected)"); 
	}
	
	@Test
	void direccionVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Cliente cliente = new Cliente();
		Set<Reserva> reservas = new HashSet<Reserva>();
		cliente.setDni("12345678A");
		cliente.setNombre("Prueba");
		cliente.setEmail("prueba@prueba.com");
		cliente.setTelefono("123456789");
		cliente.setDireccion("");
		cliente.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Cliente> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("direccion");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

}
