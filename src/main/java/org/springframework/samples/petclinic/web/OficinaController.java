package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.repository.OficinaRepository;
import org.springframework.samples.petclinic.service.OficinaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class OficinaController {
	
	private static final String VIEWS_OFICINA_CREATE_OR_UPDATE_FORM = "oficina/createOrUpdateOficinaForm";
	
	private final OficinaService oficinaService;
	
	@Autowired
	public OficinaController(OficinaService oficinaService) {
		this.oficinaService = oficinaService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/oficina/new")
	public String initCreationForm(Map<String, Object> model) {
		Oficina oficina = new Oficina();
		model.put("oficina", oficina);
		return VIEWS_OFICINA_CREATE_OR_UPDATE_FORM;
	}
	
	//@PostMapping(value = "/oficina/new")
	//public String processCreationForm(@Valid )

}
