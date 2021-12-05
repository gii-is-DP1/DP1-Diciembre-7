package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.PreReserva;
import org.springframework.samples.petclinic.repository.PreReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PreReservaService {
	
	private PreReservaRepository preReservaRepository;
	
	@Autowired
	public PreReservaService(PreReservaRepository preReservaRepository) {
		this.preReservaRepository = preReservaRepository;
	}
	
	@Transactional
	public void save(PreReserva p) throws DataAccessException{
		preReservaRepository.save(p);
	}
	
	@Transactional
	public void deleteAll() throws DataAccessException{
		preReservaRepository.deleteAll();
	}
	
	@Transactional
	public Collection<PreReserva> findAllPreReservas() throws DataAccessException{
		return preReservaRepository.findAll();
	}

}
