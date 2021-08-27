package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Integer>{
	
	//Reservas por conductor
	@Query("SELECT reserva FROM Reserva reserva WHERE reserva.conductor=:conductor")
	public Collection<Reserva> findReservaByConductor(@Param("conductor") Conductor conductor);

}
