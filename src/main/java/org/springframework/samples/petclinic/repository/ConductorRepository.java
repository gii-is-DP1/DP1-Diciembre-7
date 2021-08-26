package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Conductor;

public interface ConductorRepository extends Repository<Conductor,String>{
	
	void save(Conductor conductor) throws DataAccessException;

}
