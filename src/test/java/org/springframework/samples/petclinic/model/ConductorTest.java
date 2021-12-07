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
class ConductorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void dniVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("");
		conductor.setNombre("Prueba");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("123456789");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("dni");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void nombreVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("123456789");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombre");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void emailVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("Prueba");
		conductor.setEmail("");
		conductor.setTelefono("123456789");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void telefonoVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("Prueba");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("numeric value out of bounds (<9 digits>.<0 digits> expected)");
	}
	
	@Test
	void ciudadVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("Prueba");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("123456789");
		conductor.setCiudad("");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("ciudad");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void experienciaErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("Prueba");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("123456789");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(0);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("experiencia");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 3");
	}
	
	@Test
	void permisoBarcoErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("Prueba");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("123456789");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(null);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("permisoBarco");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
	
	@Test
	void permisoCocheErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("Prueba");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("123456789");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(null);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("permisoCoche");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}
	
	@Test
	void salarioBaseErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("Prueba");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("123456789");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(-1.0);
		conductor.setSalarioPorDia(5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("salarioBase");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 0");
	}
	
	@Test
	void salarioPorDiaErroneo() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Conductor conductor = new Conductor();
		Set<Reserva> reservas = new HashSet<Reserva>();
		conductor.setDni("12345678A");
		conductor.setNombre("Prueba");
		conductor.setEmail("prueba@prueba.com");
		conductor.setTelefono("123456789");
		conductor.setCiudad("Sevilla");
		conductor.setExperiencia(4);
		conductor.setPermisoBarco(true);
		conductor.setPermisoCoche(true);
		conductor.setSalarioBase(100.0);
		conductor.setSalarioPorDia(-5.0);
		conductor.setReservas(reservas);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Conductor>> constraintViolations = validator.validate(conductor);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Conductor> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("salarioPorDia");
		assertThat(violation.getMessage()).isEqualTo("must be greater than or equal to 0");
	}

}
