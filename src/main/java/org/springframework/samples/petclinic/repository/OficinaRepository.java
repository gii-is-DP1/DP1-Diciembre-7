package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.model.Oficina;


public interface OficinaRepository extends CrudRepository<Oficina, Integer>{
	
	Oficina findById(int id) throws DataAccessException;
	
	@Query("SELECT oficina FROM Oficina oficina WHERE oficina.ciudad=:ciudad")
	Collection<Oficina> findOficinaByCiudad(@Param("ciudad") String ciudad) throws DataAccessException;
	
	@Query("SELECT oficina FROM Oficina oficina WHERE oficina.empresa=:empresa")
	Collection<Oficina> findOficinaByEmpresa(@Param("empresa") Empresa empresa) throws DataAccessException;

}
