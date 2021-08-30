package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class ClienteController {

	private static final String VIEWS_CLIENTE_CREATE_OR_UPDATE = "cliente/createOrUpdateClienteForm";

	private final ClienteService clienteService;

	@Autowired
	ClienteController(ClienteService clienteService, UserService userService, AuthoritiesService authoritiesService) {
		this.clienteService = clienteService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/cliente/new")
	public String initCreationForm(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return VIEWS_CLIENTE_CREATE_OR_UPDATE;
	}

	@PostMapping(value = "/cliente/new")
	public String processCreationForm(@Valid Cliente cliente, BindingResult result)
			throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException {
		if (result.hasErrors()) {
			model.put("cliente", cliente);
			return VIEWS_CLIENTE_CREATE_OR_UPDATE;
		} else {
			try {
				this.clienteService.saveCliente(cliente);
				model.addAttribute("message", "Se ha registrado correctamente.");
			} catch(DuplicatedTelephoneException ex) {
				result.rejectValue("telefono", "duplicated", "already exists");
				model.put("cliente", cliente);
				model.addAttribute("message", "Ya existe un cliente con este telefono.");
				return VIEWS_CLIENTE_CREATE_OR_UPDATE;
			}catch(DuplicatedEmailException ex1) {
				result.rejectValue("email", "duplicated", "already exists");
				model.put("cliente", cliente);
				model.addAttribute("message", "Ya existe un cliente con este email.");
				return VIEWS_CLIENTE_CREATE_OR_UPDATE;

			}
			

			return "redirect:/cliente/" + cliente.getId();
		}
	}

	@GetMapping(value = "/cliente/{clienteId}/edit")
	public String initUpdateClienteForm(@PathVariable("clienteId") int clienteId, Model model) {
		Cliente cliente = this.clienteService.findClienteById(clienteId);
		model.addAttribute(cliente);
		return VIEWS_CLIENTE_CREATE_OR_UPDATE;
	}

	@PostMapping(value = "/cliente/{clienteId}/edit")
	public String processUpdateClienteForm(@Valid Cliente cliente, BindingResult result,
			@PathVariable("clienteId") int clienteId)
			throws DataAccessException, DuplicatedTelephoneException, DuplicatedEmailException {
		if (result.hasErrors()) {
			model.put("cliente", cliente);
			return VIEWS_CLIENTE_CREATE_OR_UPDATE;
		} else {
			try {
				cliente.setId(clienteId);
				this.clienteService.saveCliente(cliente);
				model.addAttribute("message", "Sus datos se han actualizado correctamente.");
			}catch(DuplicatedTelephoneException ex) {
				result.rejectValue("telefono", "duplicated", "already exists");
				model.put("cliente", cliente);
				model.addAttribute("message", "Ya existe un cliente con este telefono.");
				return VIEWS_CLIENTE_CREATE_OR_UPDATE;
			}catch(DuplicatedEmailException ex1) {
				result.rejectValue("email", "duplicated", "already exists");
				model.put("cliente", cliente);
				model.addAttribute("message", "Ya existe un cliente con este email.");
				return VIEWS_CLIENTE_CREATE_OR_UPDATE;
			}
			
			return "redirect:/cliente/{clienteId}";
	
		}

	}

	@GetMapping("/cliente/{clienteId}")
	public ModelAndView showCliente(@PathVariable("clienteId") int clienteId) {
		ModelAndView mav = new ModelAndView("cliente/clienteDetails");
		mav.addObject(this.clienteService.findClienteById(clienteId));
		return mav;
	}

}
