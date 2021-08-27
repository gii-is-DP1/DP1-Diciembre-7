package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Conductor;

public interface ConductorRepository extends Repository<Conductor,String>{
	
	void save(Conductor conductor) throws DataAccessException;
	
	@Query("SELECT DISTINCT conductor from Conductor conductor WHERE conductor.ciudad =:ciudad")
	public Collection<Conductor> findConductoresPorCiudad(@Param("ciudad") String ciudad) throws DataAccessException;

}
