package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Empresa;

public interface EmpresaRepository extends Repository<Empresa,String>{
	
	void save(Empresa empresa) throws DataAccessException;

}
