package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.samples.petclinic.repository.OficinaRepository;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedVehicleModelException;
import org.springframework.samples.petclinic.service.exceptions.VehiculoReservadoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehiculoService {

	private VehiculoRepository vehiculoRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private OficinaRepository oficinaRepository;

	@Autowired
	public VehiculoService(VehiculoRepository vehiculoRepository, ReservaRepository reservaRepository) {
		this.vehiculoRepository = vehiculoRepository;
		this.reservaRepository = reservaRepository;
	}

	@Transactional(readOnly = true)
	public Collection<TipoVehiculo> findTipoVehiculo() throws DataAccessException {
		return vehiculoRepository.findTipoVehiculo();
	}

	@Transactional(readOnly = true)
	public Vehiculo findVehiculoById(int id) throws DataAccessException {
		return vehiculoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Vehiculo> findVehiculosPorCiudadYFechaDisponibles(String ciudad, LocalDate fechaInicio,
			LocalDate fechaFin) throws DataAccessException {
		Collection<Collection<Vehiculo>> vehiculosCiudad = vehiculoRepository.findVehiculosPorCiudad(ciudad);
		Collection<Vehiculo> vehiculosCiudadYFechaDisponibles = new ArrayList<>();
		Set<Vehiculo> vehiculosCiudadAplanado = new HashSet<>();
		if(vehiculosCiudad != null) {
		for(Collection<Vehiculo> vehiculos:vehiculosCiudad) {
			for(Vehiculo v: vehiculos) {
				vehiculosCiudadAplanado.add(v);
			}
		}
		for (Vehiculo v : vehiculosCiudadAplanado) {
			Integer stock = v.getStock();
			Integer acum = 0;
			Collection<Reserva> reservasVehiculo = reservaRepository.findReservasByVehiculo(v);
			for (Reserva r : reservasVehiculo) {
				if (r.getFechaInicio().equals(fechaInicio)
						|| (r.getFechaInicio().isAfter(fechaInicio) && r.getFechaInicio().isBefore(fechaFin))
						|| r.getFechaFin().equals(fechaFin)
						|| (r.getFechaFin().isAfter(fechaInicio) && r.getFechaFin().isBefore(fechaFin))
						|| (r.getFechaInicio().isBefore(fechaInicio) && r.getFechaFin().isAfter(fechaFin))) {
					acum = acum + 1;
				}
			}
			Integer res = stock - acum;
			if (res >= 1) {
				vehiculosCiudadYFechaDisponibles.add(v);
			}
		}
		}
		return vehiculosCiudadYFechaDisponibles;
	}

	@Transactional(rollbackFor = DuplicatedVehicleModelException.class)
	public void saveVehiculo(Vehiculo vehiculo) throws DataAccessException, DuplicatedVehicleModelException {
		Set<Oficina> oficinas = vehiculo.getOficinas();
		if(oficinas !=null) {
		for (Oficina o : oficinas) {
			Set<Vehiculo> vehiculos = o.getVehiculos();
			for (Vehiculo v : vehiculos) {
				if (v.getMarca().equals(vehiculo.getMarca()) && v.getModelo().equals(vehiculo.getModelo()) && vehiculo.getId()!=v.getId()) {
					throw new DuplicatedVehicleModelException();
				}
			}
		}
		}
		vehiculoRepository.save(vehiculo);
	}
	@Transactional()	
	public void deleteVehiculoById(int vehiculoId) throws VehiculoReservadoException {
		LocalDate now = LocalDate.now();
		Vehiculo v = vehiculoRepository.findById(vehiculoId);
		Collection<Reserva> reservas = reservaRepository.findReservasByVehiculo(v);
		if(reservas!=null || reservas.isEmpty()) {
			for(Reserva r:reservas) {
				if(r.getFechaInicio().equals(now) || r.getFechaInicio().isAfter(now) || r.getFechaFin().equals(now) || r.getFechaFin().isAfter(now)) {
					throw new VehiculoReservadoException();
				}
			}
		}
		vehiculoRepository.deleteById(vehiculoId);
	}

}
