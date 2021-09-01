package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.samples.petclinic.service.exceptions.CloseDateBookingException;
import org.springframework.samples.petclinic.service.exceptions.OverStockedVehicleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

	private ReservaRepository reservaRepository;

	@Autowired
	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Reserva> findReservasByCliente(Cliente cliente) throws DataAccessException {
		return reservaRepository.findReservasByCliente(cliente);
	}

	@Transactional(readOnly = true)
	public Collection<Reserva> findReservasByConductor(Conductor conductor) throws DataAccessException {
		return reservaRepository.findReservasByConductor(conductor);
	}

	@Transactional(readOnly = true)
	public Collection<Reserva> findReservasByVehiculo(Vehiculo vehiculo) throws DataAccessException {
		return reservaRepository.findReservasByVehiculo(vehiculo);
	}

	@Transactional(readOnly = true)
	public Reserva findReservaById(int id) throws DataAccessException {
		return reservaRepository.findById(id);
	}

	@Transactional(rollbackFor = OverStockedVehicleException.class)
	public void saveReserva(Reserva reserva) throws DataAccessException, OverStockedVehicleException {
		Vehiculo vehiculo = reserva.getVehiculo();
		Collection<Reserva> reservasVehiculo = findReservasByVehiculo(vehiculo);
		Set<Reserva> reservasVehiculoActuales = new HashSet<Reserva>();
		for (Reserva r : reservasVehiculo) {
			if (r.getFechaInicio().equals(reserva.getFechaInicio())
					|| (r.getFechaInicio().isAfter(reserva.getFechaInicio())
							&& r.getFechaInicio().isBefore(reserva.getFechaFin()))
					|| r.getFechaFin().equals(reserva.getFechaFin())
					|| (r.getFechaFin().isAfter(reserva.getFechaInicio())
							&& r.getFechaFin().isBefore(reserva.getFechaFin()))
					|| (r.getFechaInicio().isBefore(reserva.getFechaInicio())
							&& r.getFechaFin().isAfter(reserva.getFechaFin()))) {
				reservasVehiculoActuales.add(r);
			}
		}
		if ((reservasVehiculoActuales.size() + 1) > vehiculo.getStock()) {
			throw new OverStockedVehicleException();
		} else {
			reservaRepository.save(reserva);
		}
	}

	@Transactional(rollbackFor = CloseDateBookingException.class)
	public void deleteReservaById(int id) throws DataAccessException, CloseDateBookingException {
		Reserva reserva = findReservaById(id);
		if (reserva.getFechaInicio().getYear() == LocalDate.now().getYear()) {
			int diasParaInicio = reserva.getFechaInicio().getDayOfYear() - LocalDate.now().getDayOfYear();
			if (diasParaInicio <= 3) {
				throw new CloseDateBookingException();
			}
		}
		reservaRepository.deleteById(id);
	}

}
