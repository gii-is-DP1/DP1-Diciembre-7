package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer> {
	
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.id =:id")
	public Cliente findById(@Param("id") int id);
	
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.email =:email")
	public Cliente findByEmail(@Param("email") String email);
	
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.telefono =:telefono")
	public Cliente findByTelefono(@Param("telefono") String string);
	
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.dni =:dni")
	public Cliente findByDNI(@Param("dni") String dni);

	void save(Cliente cliente) throws DataAccessException;

}
