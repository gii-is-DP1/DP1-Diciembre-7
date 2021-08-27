package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.repository.EmpresaRepository;
import org.springframework.transaction.annotation.Transactional;

public class EmpresaService {
	
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public EmpresaService(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}
	
	@Transactional(readOnly = true)
	public Empresa findEmpresaById(int id) throws DataAccessException {
		return empresaRepository.findById(id);
	}
	
	@Transactional
	public void saveEmpresa(Empresa empresa) throws DataAccessException{
		empresaRepository.save(empresa);
		
		userService.saveUser(empresa.getUser());
		
		authoritiesService.saveAuthorities(empresa.getUser().getUsername(), "empresa");
	}

}
