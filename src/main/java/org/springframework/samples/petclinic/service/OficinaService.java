package org.springframework.samples.petclinic.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.repository.OficinaRepository;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedOfficeAddressException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OficinaService {
	
	private OficinaRepository oficinaRepository;
	
	@Autowired
	public OficinaService(OficinaRepository oficinaRepository) {
		this.oficinaRepository = oficinaRepository;
	}
	
	@Transactional(readOnly = true)
	public Oficina findOficinaById(int id) throws DataAccessException {
		return oficinaRepository.findById(id);
	}
	
	@Transactional(rollbackFor = DuplicatedOfficeAddressException.class)
	public void saveOficina(Oficina oficina) throws DataAccessException, DuplicatedOfficeAddressException{
		Set<Oficina> oficinas =  oficina.getEmpresa().getOficinas();
		for(Oficina o : oficinas) {
			if((o.getCiudad().equals(oficina.getCiudad())) &&
			   (o.getCodigoPostal().equals(oficina.getCodigoPostal())) &&
			   (o.getDireccion().equals(oficina.getDireccion()))){
				throw new DuplicatedOfficeAddressException();
			}
		}
		oficinaRepository.save(oficina);
	}
	
	

}
