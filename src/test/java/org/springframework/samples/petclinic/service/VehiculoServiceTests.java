package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedDNIException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedOfficeAddressException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedUsernameException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedVehicleModelException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VehiculoServiceTests {
	
	@Autowired
	protected VehiculoService vehiculoService;

	@Autowired
	protected OficinaService oficinaService;
	
	@Test
	void shouldFindVehiculoById() {
		Vehiculo vehiculo = this.vehiculoService.findVehiculoById(1);
		assertThat(vehiculo.getMarca().startsWith("Seat"));
		assertThat(vehiculo.getModelo().startsWith("Leon"));
		assertThat(vehiculo.getStock().equals(50));
	}
	
	@Test
	@Transactional
	public void shouldInsertVehiculo() throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException, DuplicatedDNIException, DuplicatedUsernameException, DuplicatedOfficeAddressException, DuplicatedVehicleModelException {
		Collection<Vehiculo> vehiculos = this.vehiculoService.findVehiculosPorModelo("Mustang");
		int found = vehiculos.size();
		
				
		Collection<Oficina> os = oficinaService.findOficinaByCiudad("Malaga");
		
		Set<Oficina> os1 = new HashSet<>();
		for(Oficina o: os) {
			os1.add(o);
		}
		
		TipoVehiculo tv1 = null;
		
		Collection<TipoVehiculo> tiposVehiculo = vehiculoService.findTipoVehiculo();
		for(TipoVehiculo tv: tiposVehiculo) {
			if(tv.getName().equals("Turismo")) {
				tv1 = tv;
			}
		}
		
		Vehiculo v = new Vehiculo();
		v.setMarca("Ford");
		v.setModelo("Mustang");
		v.setPrecioBase(100.0);
		v.setPrecioPorDia(20.0);
		v.setReserva(new HashSet<>());
		v.setStock(10);
		v.setTipoVehiculo(tv1);
		v.setOficinas(os1);
			
		this.vehiculoService.saveVehiculo(v);
		assertThat(v.getId().longValue()).isNotEqualTo(0);
		
		Collection<Vehiculo> vs1 = this.vehiculoService.findVehiculosPorModelo("Mustang");
		
		assertThat(vs1.size()).isEqualTo(found + 1);
			
	}

}
