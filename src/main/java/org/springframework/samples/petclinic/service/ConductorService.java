package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.repository.ConductorRepository;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.transaction.annotation.Transactional;

public class ConductorService {
	
	private ConductorRepository conductorRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
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
	public Collection<Conductor> findConductoresPorCiudadPermiso(String ciudad, TipoVehiculo tipoVehiculo, LocalDate fechaInicio, LocalDate fechaFinal) throws DataAccessException{
		Collection<Conductor> conductoresCiudad = conductorRepository.findConductoresPorCiudad(ciudad);
		Collection<Conductor> conductoresCiudadYPermiso = new HashSet<Conductor>();
		Collection<TipoVehiculo> tiposVehiculos = vehiculoRepository.findTipoVehiculo();
		Collection<Conductor> conductoresCiudadPermisoYFecha = new HashSet<Conductor>();
		for(Conductor c:conductoresCiudad) {
			if(tiposVehiculos.contains(tipoVehiculo) && c.getPermisoCoche()==true &&(tipoVehiculo.getName().equals("SUV") || tipoVehiculo.getName().equals("Turismo") || tipoVehiculo.getName().equals("Todoterreno") || tipoVehiculo.getName().equals("Furgoneta")) ) {
				conductoresCiudadYPermiso.add(c);
			}else if(tiposVehiculos.contains(tipoVehiculo) && c.getPermisoBarco()==true && (tipoVehiculo.getName().equals("Lancha") || tipoVehiculo.getName().equals("Velero") || tipoVehiculo.getName().equals("Yate") || tipoVehiculo.getName().equals("motoAgua"))){
				conductoresCiudadYPermiso.add(c);
			}
		}
		for(Conductor c: conductoresCiudadYPermiso) {
			Collection<Reserva> reservasConductor = reservaRepository.findReservasByConductor(c);
			for(Reserva r: reservasConductor) {
				if(r.getFechaInicio().) {
					
				}
				
			}
			if() {
				)
			}else {
				conductoresCiudadPermisoYFecha.add(c);
			}
		}
		return conductoresCiudadPermisoYFecha;				
	}
	
	@Transactional
	public void SaveConductor(Conductor conductor) throws DataAccessException{
		conductorRepository.save(conductor);
		
		userService.saveUser(conductor.getUser());
		
		authoritiesService.saveAuthorities(conductor.getUser().getUsername(), "conductor");
	}

}
