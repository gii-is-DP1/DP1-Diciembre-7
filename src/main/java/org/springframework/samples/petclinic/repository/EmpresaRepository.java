package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Empresa;

public interface EmpresaRepository extends Repository<Empresa,String>{
	
	@Query("SELECT empresa FROM Empresa empresa WHERE empresa.id =:id")
	public Empresa findById(@Param("id") int id);
	
	@Query("SELECT empresa FROM Empresa empresa WHERE empresa.email =:email")
	public Empresa findByEmail(@Param("email") String email);
	
	@Query("SELECT empresa FROM Empresa empresa WHERE empresa.telefono =:telefono")
	public Empresa findByTelefono(@Param("telefono") String string);
	
	void save(Empresa empresa) throws DataAccessException;

}
