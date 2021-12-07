package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedDNIException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedUsernameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ConductorServiceTests {
	
	@Autowired
	protected ConductorService conductorService;
	
	@Test
	void shouldFindConductorById() {
		Conductor conductor = this.conductorService.findConductorById(2);
		assertThat(conductor.getNombre().startsWith("Javier"));
		assertThat(conductor.getDni().startsWith("98765432B"));
		assertThat(conductor.getTelefono().startsWith("112345678"));
	}
	
	@Test
	@Transactional
	public void shouldInsertConductor() throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException, DuplicatedDNIException, DuplicatedUsernameException {
		Conductor conductor = this.conductorService.findConductorByUsername("Javier");
		List<Conductor> conductors = new ArrayList<>(); 
		conductors.add(conductor);
		int found = conductors.size();
		
		Conductor c = new Conductor();
		c.setNombre("Javier");
		c.setDni("98764442B");
		c.setEmail("prueba@prueba");
		c.setTelefono("112345645");
		c.setExperiencia(6);
		c.setPermisoBarco(true);
		c.setPermisoCoche(true);
		c.setCiudad("Sevilla");
		c.setReservas(new HashSet<Reserva>());
			User user = new User();
			user.setUsername("Javier");
			user.setPassword("contraseña");
			user.setEnabled(true);
			c.setUser(user);
			
		this.conductorService.saveConductor(c);
		assertThat(c.getId().longValue()).isNotEqualTo(0);
		
		Conductor c1 = this.conductorService.findConductorByUsername("Javier");
		
		conductors.add(c1);
		assertThat(conductors.size()).isEqualTo(found + 1);
			
	}
	

}
