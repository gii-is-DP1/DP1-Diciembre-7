package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer> {
	
	@Query("Select cliente from Cliente cliente where cliente.id =:id")
	public Cliente findById(@Param("id") int id);

	void save(Cliente cliente) throws DataAccessException;

}
