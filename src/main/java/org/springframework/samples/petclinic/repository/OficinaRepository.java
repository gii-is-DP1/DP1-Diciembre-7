package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Oficina;


public interface OficinaRepository extends CrudRepository<Oficina, Integer>{
	
	Oficina findById(int id) throws DataAccessException;
	

}
