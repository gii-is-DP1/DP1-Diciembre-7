package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.EmpresaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class EmpresaController {
	
	private static final String VIEWS_EMPRESA_CREATE_OR_UPDATE = "empresa/createOrUpdateEmpresaForm";

	private final EmpresaService empresaService;

	@Autowired
	EmpresaController(EmpresaService empresaService, UserService userService, AuthoritiesService authoritiesService) {
		this.empresaService = empresaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/empresa/new")
	public String initCreationForm(Map<String, Object> model) {
		Empresa empresa = new Empresa();
		model.put("empresa", empresa);
		return VIEWS_EMPRESA_CREATE_OR_UPDATE;
	}

	@PostMapping(value = "/empresa/new")
	public String processCreationForm(@Valid Empresa empresa, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_EMPRESA_CREATE_OR_UPDATE;
		} else {
			this.empresaService.saveEmpresa(empresa);

			return "redirect:/empresa/" + empresa.getId();
		}
	}

	@GetMapping(value = "/empresa/{empresaId}/edit")
	public String initUpdateempresaForm(@PathVariable("empresaId") int empresaId, Model model) {
		Empresa empresa = this.empresaService.findEmpresaById(empresaId);
		model.addAttribute(empresa);
		return VIEWS_EMPRESA_CREATE_OR_UPDATE;
	}

	@PostMapping(value = "/empresa/{empresaId}/edit")
	public String processUpdateempresaForm(@Valid Empresa empresa, BindingResult result,
			@PathVariable("empresaId") int empresaId) {
		if (result.hasErrors()) {
			return VIEWS_EMPRESA_CREATE_OR_UPDATE;
		} else {
			empresa.setId(empresaId);
			this.empresaService.saveEmpresa(empresa);
			return "redirect:/empresa/{empresaId}";
		}
	}

	@GetMapping("/empresa/{empresaId}")
	public ModelAndView showempresa(@PathVariable("empresaId") int empresaId) {
		ModelAndView mav = new ModelAndView("empresa/empresaDetails");
		mav.addObject(this.empresaService.findEmpresaById(empresaId));
		return mav;
	}

}
