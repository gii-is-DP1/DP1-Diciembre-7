package org.springframework.samples.petclinic.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.repository.OficinaRepository;
import org.springframework.samples.petclinic.repository.VehiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OficinaService {
	
	private OficinaRepository oficinaRepository;
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	public OficinaService(OficinaRepository oficinaRepository, VehiculoRepository vehiculoRepository) {
		this.oficinaRepository = oficinaRepository;
		this.vehiculoRepository = vehiculoRepository;
	}
	
	@Transactional(readOnly = true)
	public Oficina findOficinaById(int id) throws DataAccessException {
		return oficinaRepository.findById(id);
	}
	
	@Transactional(rollbackFor = DuplicatedOfficeAddress.class)
	public void saveOficina(Oficina oficina) {
		Set<Oficina> oficinas =  oficina.getEmpresa().get
	}
	
	

}
