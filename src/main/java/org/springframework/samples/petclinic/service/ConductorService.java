package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.repository.ConductorRepository;
import org.springframework.transaction.annotation.Transactional;

public class ConductorService {
	
	private ConductorRepository conductorRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public ConductorService(ConductorRepository conductorRepository) {
		this.conductorRepository = conductorRepository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Conductor> findConductoresPorCiudad(String ciudad) throws DataAccessException{
		return conductorRepository.findConductoresPorCiudad(ciudad);
	}
	
	@Transactional
	public void SaveConductor(Conductor conductor) throws DataAccessException{
		conductorRepository.save(conductor);
		
		userService.saveUser(conductor.getUser());
		
		authoritiesService.saveAuthorities(conductor.getUser().getUsername(), "conductor");
	}

}
