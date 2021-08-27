package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Empresa;

public interface EmpresaRepository extends Repository<Empresa,String>{
	
	@Query("Select empresa from Empresa empresa where empresa.id =:id")
	public Empresa findById(@Param("id") int id);
	
	void save(Empresa empresa) throws DataAccessException;

}