package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer>{
	
	@Query("SELECT tipoVehiculo FROM TipoVehiculo tipoVehiculo ORDER BY tipoVehiculo.name")
	List<TipoVehiculo> findTipoVehiculo() throws DataAccessException;
	
	@Query("SELECT vehiculos FROM Oficina oficina WHERE oficina.ciudad =:ciudad")
	public Collection<Collection<Vehiculo>> findVehiculosPorCiudad(@Param("ciudad") String ciudad);	
	
	Vehiculo findById(int id) throws DataAccessException;

}
