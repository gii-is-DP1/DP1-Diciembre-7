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
class EmpresaTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void dniVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Empresa empresa = new Empresa();
		Set<Oficina> oficinas = new HashSet<Oficina>();
		empresa.setNombre("");
		empresa.setEmail("prueba@prueba.com");
		empresa.setTelefono("123456789");
		empresa.setPais("España");
		empresa.setOficinas(oficinas);
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Empresa> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("nombre");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	@Test
	void emailVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Empresa empresa = new Empresa();
		Set<Oficina> oficinas = new HashSet<Oficina>();
		empresa.setNombre("Prueba");
		empresa.setEmail("");
		empresa.setTelefono("123456789");
		empresa.setPais("España");
		empresa.setOficinas(oficinas);
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Empresa> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}
	
	
	@Test
	void telefonoVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Empresa empresa = new Empresa();
		Set<Oficina> oficinas = new HashSet<Oficina>();
		empresa.setNombre("Prueba");
		empresa.setEmail("prueba@prueba.com");
		empresa.setTelefono("");
		empresa.setPais("España");
		empresa.setOficinas(oficinas);
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Empresa> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("numeric value out of bounds (<9 digits>.<0 digits> expected)");
	}
	
	@Test
	void paisVacio() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Empresa empresa = new Empresa();
		Set<Oficina> oficinas = new HashSet<Oficina>();
		empresa.setNombre("Prueba");
		empresa.setEmail("prueba@prueba.com");
		empresa.setTelefono("123456789");
		empresa.setPais("");
		empresa.setOficinas(oficinas);
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Empresa> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("pais");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

}
