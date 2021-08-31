package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.Vehiculo;

public interface ReservaRepository extends CrudRepository<Reserva, Integer>{
	
	@Query("SELECT reserva FROM Reserva reserva WHERE reserva.conductor=:conductor")
	Collection<Reserva> findReservasByConductor(@Param("conductor") Conductor conductor) throws DataAccessException;
	
	@Query("SELECT reserva FROM Reserva reserva WHERE reserva.cliente=:cliente")
	Collection<Reserva> findReservasByCliente(@Param("cliente") Cliente cliente) throws DataAccessException;
	
	@Query("SELECT reserva FROM Reserva reserva WHERE reserva.vehiculo=:vehiculo")
	Collection<Reserva> findReservasByVehiculo(@Param("vehiculo") Vehiculo vehiculo) throws DataAccessException;

	Reserva findById(int id) throws DataAccessException;
	
}
