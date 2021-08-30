package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
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
	
	@Transactional(rollbackFor = DuplicatedTelephoneOrEmailException.class)
	public void saveCliente(Cliente cliente) throws DataAccessException, DuplicatedTelephoneOrEmailException{
		Cliente cE = clienteRepository.findByEmail(cliente.getEmail());
		Cliente cT = clienteRepository.findByTelefono(cliente.getTelefono());
		if((cT != null) || (cE != null)) {
			throw new DuplicatedTelephoneOrEmailException();
		}else {
			
			clienteRepository.save(cliente);
			
			userService.saveUser(cliente.getUser());
			
			authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "cliente");
			
		}
		
	}

}
