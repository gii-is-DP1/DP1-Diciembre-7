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
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedDNIException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedUsernameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EmpresaServiceTests {
	
	@Autowired
	protected EmpresaService empresaService;
	
	@Test
	void shouldFindEmpresaById() {
		Empresa empresa = this.empresaService.findEmpresaById(2);
		assertThat(empresa.getNombre().startsWith("Aytos"));
		assertThat(empresa.getTelefono().startsWith("123456789"));
		assertThat(empresa.getPais().startsWith("España"));
	}
	
	@Test
	@Transactional
	public void shouldInsertEmpresa() throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException, DuplicatedDNIException, DuplicatedUsernameException {
		Empresa empresa = this.empresaService.findEmpresaByUsername("Javier");
		List<Empresa> empresas = new ArrayList<>(); 
		empresas.add(empresa);
		int found = empresas.size();
		
		Empresa e = new Empresa();
		e.setNombre("Empresa de prueba");
		e.setEmail("prueba@prueba");
		e.setTelefono("112345645");
		e.setPais("España");
		e.setOficinas(new HashSet<>());
			User user = new User();
			user.setUsername("Javier");
			user.setPassword("contraseña");
			user.setEnabled(true);
			e.setUser(user);
			
		this.empresaService.saveEmpresa(e);
		assertThat(e.getId().longValue()).isNotEqualTo(0);
		
		Empresa e1 = this.empresaService.findEmpresaByUsername("Javier");
		
		empresas.add(e1);
		assertThat(empresas.size()).isEqualTo(found + 1);
			
	}
	


}
