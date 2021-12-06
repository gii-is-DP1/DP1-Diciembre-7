package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.PreReserva;

public interface PreReservaRepository extends CrudRepository<PreReserva, Integer>{
	
	Collection<PreReserva> findAll() throws DataAccessException;
	

}
