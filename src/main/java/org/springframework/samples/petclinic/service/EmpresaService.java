package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.repository.EmpresaRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
	
	@Transactional(readOnly = true)
	public Empresa findEmpresaByUsername(String username) throws DataAccessException {
		return empresaRepository.findByUsername(username);
	}
	
	@Transactional(rollbackFor = { DuplicatedTelephoneException.class, DuplicatedEmailException.class })
	public void saveEmpresa(Empresa empresa)
			throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException {
		Empresa eE = empresaRepository.findByEmail(empresa.getEmail());
		Empresa eT = empresaRepository.findByTelefono(empresa.getTelefono());
		if (eT != null && !(eT.getId().equals(empresa.getId()))) {
			throw new DuplicatedTelephoneException();
		} else if (eE != null && (eE.getId().equals(empresa.getId()))) {
			throw new DuplicatedEmailException();
		} else {
			empresaRepository.save(empresa);

			userService.saveUser(empresa.getUser());

			authoritiesService.saveAuthorities(empresa.getUser().getUsername(), "empresa");
		}

	}
	
	@Transactional(rollbackFor = { DuplicatedTelephoneException.class, DuplicatedEmailException.class })
	public void saveEmpresaUpdate(Empresa empresa)
			throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException {
		Empresa eE = empresaRepository.findByEmail(empresa.getEmail());
		Empresa eT = empresaRepository.findByTelefono(empresa.getTelefono());
		if (eT != null && !(eT.getId().equals(empresa.getId()))) {
			throw new DuplicatedTelephoneException();
		} else if (eE != null && (eE.getId().equals(empresa.getId()))) {
			throw new DuplicatedEmailException();
		} else {
			empresaRepository.save(empresa);

			userService.saveUser(empresa.getUser());

		}

	}

}
