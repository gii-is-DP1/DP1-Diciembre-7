package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedDNIException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedOfficeAddressException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedUsernameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class OficinaServiceTests {
	
	@Autowired
	protected OficinaService oficinaService;
	
	@Autowired
	protected EmpresaService empresaService;
	
	@Test
	void shouldFindOficinaById() {
		Oficina oficina = this.oficinaService.findOficinaById(1);
		assertThat(oficina.getCiudad().startsWith("Sevilla"));
		assertThat(oficina.getCodigoPostal().equals(41400));
		assertThat(oficina.getDireccion().startsWith("Avenida Reina Mercedes s/n"));
	}
	
	@Test
	@Transactional
	public void shouldInsertOficina() throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException, DuplicatedDNIException, DuplicatedUsernameException, DuplicatedOfficeAddressException {
		Collection<Oficina> oficinas = this.oficinaService.findOficinaByCiudad("Valencia");
		int found = oficinas.size();
		
		Empresa e = empresaService.findEmpresaById(1);
		
		Oficina o = new Oficina();
		o.setCiudad("Valencia");
		o.setCodigoPostal(41013);
		o.setDireccion("Estadio Mestalla");
		o.setVehiculos(new HashSet<>());
		o.setEmpresa(e);
			
		this.oficinaService.saveOficina(o);
		assertThat(o.getId().longValue()).isNotEqualTo(0);
		
		Collection<Oficina> os1 = this.oficinaService.findOficinaByCiudad("Valencia");
		
		assertThat(os1.size()).isEqualTo(found + 1);
			
	}
	


}
