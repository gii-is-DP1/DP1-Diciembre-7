package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedDNIException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedOfficeAddressException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedUsernameException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedVehicleModelException;
import org.springframework.samples.petclinic.service.exceptions.OverStockedVehicleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReservaServiceTests {
	
	
	@Autowired
	protected VehiculoService vehiculoService;

	@Autowired
	protected ClienteService clienteService;
	
	@Autowired
	protected ConductorService conductorService;
	
	@Autowired
	protected ReservaService reservaService;
	
	@Test
	void shouldFindReservaById() {
		Reserva reserva = this.reservaService.findReservaById(1);
		assertThat(reserva.getCiudad().startsWith("Sevilla"));
		assertThat(reserva.getPrecioFinal().equals(200.0));
		assertThat(reserva.getFechaInicio().equals(LocalDate.of(2020, 10, 12)));
		assertThat(reserva.getFechaFin().equals(LocalDate.of(2020, 10, 15)));
	}
	
	@Test
	@Transactional
	public void shouldInsertReserva() throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException, DuplicatedDNIException, DuplicatedUsernameException, DuplicatedOfficeAddressException, DuplicatedVehicleModelException, OverStockedVehicleException {
		Collection<Reserva> reservas = this.reservaService.findReservasByCiudad("Malaga");
		int found = reservas.size();
		
		Cliente cl1 = clienteService.findClienteById(1);
		
		Vehiculo v1 = vehiculoService.findVehiculoById(1);
		
		Conductor c1 = conductorService.findConductorById(1);
		
		Reserva r = new Reserva();
		r.setCiudad("Malaga");
		r.setFechaInicio(LocalDate.of(2020, 11, 12));
		r.setFechaFin(LocalDate.of(2020, 11, 20));
		r.setPrecioFinal(400.0);
		r.setConductor(c1);
		r.setCliente(cl1);
		r.setVehiculo(v1);
			
		this.reservaService.saveReserva(r);;
		assertThat(r.getId().longValue()).isNotEqualTo(0);
		
		Collection<Reserva> vs1 = this.reservaService.findReservasByCiudad("Malaga");
		
		assertThat(vs1.size()).isEqualTo(found + 1);
			
	}


}
