package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Actor;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer> {


		/**
		 * Save an <code>Cliente</code> to the data store, either inserting or updating it.
		 * @param cliente the <code>Cliente</code> to save
		 * @see BaseEntity#isNew
		 */
		void save(Cliente cliente) throws DataAccessException;
/**
		 * Retrieve an <code>Cliente</code> from the data store by id.
		 * @param id the id to search for
		 * @return the <code>Cliente</code> if found
		 * @throws org.springframework.dao.DataRetrievalFailureException if not found
		 */	
		@Query("SELECT cliente FROM Client cliente left join fetch owner.pets WHERE client.id =:id")
		public Cliente findById(@Param("id") int id);


}
