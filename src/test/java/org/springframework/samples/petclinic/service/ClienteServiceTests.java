package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedDNIException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedUsernameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClienteServiceTests {
	
	@Autowired
	protected ClienteService clienteService;
	
	@Test
	void shouldFindClienteById() {
		Cliente cliente = this.clienteService.findClienteById(1);
		assertThat(cliente.getNombre().startsWith("Javier"));
		assertThat(cliente.getDni().startsWith("98765432B"));
		assertThat(cliente.getTelefono().startsWith("112345678"));
	}
	
	@Test
	@Transactional
	public void shouldInsertCliente() throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException, DuplicatedDNIException, DuplicatedUsernameException {
		Cliente cliente = this.clienteService.findClienteByUsername("Javier");
		List<Cliente> clientes = new ArrayList<>(); 
		clientes.add(cliente);
		int found = clientes.size();
		
		Cliente cl = new Cliente();
		cl.setNombre("Javier");
		cl.setDni("98764442B");
		cl.setEmail("prueba@prueba");
		cl.setTelefono("112345645");
		cl.setDireccion("Avenida Reina Mercedes s/n");
		cl.setReservas(new HashSet<Reserva>());
			User user = new User();
			user.setUsername("Javier");
			user.setPassword("contraseña");
			user.setEnabled(true);
			cl.setUser(user);
			
		this.clienteService.saveCliente(cl);
		assertThat(cl.getId().longValue()).isNotEqualTo(0);
		
		Cliente cl1 = this.clienteService.findClienteByUsername("Javier");
		
		clientes.add(cl1);
		assertThat(clientes.size()).isEqualTo(found + 1);
			
	}
	
	
}
