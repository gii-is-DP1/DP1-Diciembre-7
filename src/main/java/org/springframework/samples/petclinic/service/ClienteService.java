package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.transaction.annotation.Transactional;

public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@Transactional(readOnly = true)
	public Cliente findClienteById(int id) throws DataAccessException {
		return clienteRepository.findById(id);
	}
	
	@Transactional(rollbackFor = {DuplicatedTelephoneException.class, DuplicatedEmailException.class})
	public void saveCliente(Cliente cliente) throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException{
		Cliente cE = clienteRepository.findByEmail(cliente.getEmail());
		Cliente cT = clienteRepository.findByTelefono(cliente.getTelefono());
		if((cT != null) && !(cT.getId().equals(cliente.getId()))) {
			throw new DuplicatedTelephoneException();
		}else if((cE != null) && !(cE.getId().equals(cliente.getId()))){
			throw new DuplicatedEmailException();
		}else {		
			clienteRepository.save(cliente);
			
			userService.saveUser(cliente.getUser());
			
			authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "cliente");		
		}
		
	}

}
