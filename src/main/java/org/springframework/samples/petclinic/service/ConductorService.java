package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.repository.ConductorRepository;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.transaction.annotation.Transactional;

public class ConductorService {
	
	private ConductorRepository conductorRepository;
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public ConductorService(ConductorRepository conductorRepository) {
		this.conductorRepository = conductorRepository;
	}
	
	@Transactional(readOnly = true)
	public Conductor findConductorById(int id) throws DataAccessException {
		return conductorRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<Conductor> findConductoresPorCiudadYPermiso(String ciudad, String tipoVehiculo) throws DataAccessException{
		Collection<Conductor> conductoresCiudad = conductorRepository.findConductoresPorCiudad(ciudad);
		Collection<Conductor> conductoresCiudadYPermiso;
		Collection<TipoVehiculo> tiposVehiculos = vehiculoRepository.
		for(Conductor c:conductoresCiudad) {
			if(tipoVehiculo) {
				
			}
		}	
		return conductoresCiudadYPermiso;				
	}
	
	@Transactional
	public void SaveConductor(Conductor conductor) throws DataAccessException{
		conductorRepository.save(conductor);
		
		userService.saveUser(conductor.getUser());
		
		authoritiesService.saveAuthorities(conductor.getUser().getUsername(), "conductor");
	}

}
