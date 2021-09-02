package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Empresa;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.repository.OficinaRepository;
import org.springframework.samples.petclinic.service.EmpresaService;
import org.springframework.samples.petclinic.service.OficinaService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedOfficeAddressException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/empresa/{empresaId}")
public class OficinaController {
	
	private static final String VIEWS_OFICINA_CREATE_FORM = "oficina/createOficinaForm";
	
	private final OficinaService oficinaService;
	private final EmpresaService empresaService;
	
	@Autowired
	public OficinaController(OficinaService oficinaService, EmpresaService empresaService) {
		this.oficinaService = oficinaService;
		this.empresaService = empresaService;
	}
	
	@ModelAttribute("empresa")
	public Empresa findEmpresa(@PathVariable("empresaId") int empresaId) {
		return this.empresaService.findEmpresaById(empresaId);
	}
	
	@InitBinder("empresa")
	public void initEmpresaBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("oficina")
	public void initOficinaBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/oficina/new")
	public String initCreationForm(Empresa empresa, Map<String, Object> model) {
		Oficina oficina = new Oficina();
		empresa.addOficina(oficina);
		model.put("oficina", oficina);
		return VIEWS_OFICINA_CREATE_FORM;
	}
	
	@PostMapping(value = "/oficina/new")
	public String processCreationForm(Empresa empresa, @Valid Oficina oficina, BindingResult result, ModelMap model)
			throws DataAccessException, DuplicatedOfficeAddressException{
		if(result.hasErrors()) {
			model.put("oficina", oficina);
			return VIEWS_OFICINA_CREATE_FORM;
		}else {
			try {
				empresa.addOficina(oficina);
				this.oficinaService.saveOficina(oficina);
				model.addAttribute("message", "La oficina se ha añadido correctamente");
			}catch(DuplicatedOfficeAddressException ex) {
				result.rejectValue("direccion", "duplicated", "already exists");
				model.put("oficina", oficina);
				model.addAttribute("message", "Ya existe una oficina con esta direccion");
				return VIEWS_OFICINA_CREATE_FORM;
			}
			return "redirect:/empresa/"+ empresa.getId() +"/oficina/" + oficina.getId();
		}
	}
	
	@GetMapping(value = "/oficinas/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("oficina", new Oficina());
		return "oficinas/findOficinas";
	}
	
	@GetMapping(value = "/oficinas/findOficinas")
	public String processFindForm(Oficina oficina, BindingResult result, ModelMap model) {
		if (oficina.getCiudad() == null) {
			oficina.setCiudad("");
		}
		
		Collection<Oficina> results = this.oficinaService.findOficinaByCiudad(oficina.getCiudad());
		if(results.isEmpty()) {
			result.rejectValue("ciudad", "notFound", "not found");
			return "oficinas/findOficinas";
		}else {
			model.put("selections", results);
			return "oficinas/oficinasList";
		}
	}
	
	@GetMapping("/oficina/{oficinaId}")
	public ModelAndView showOficina(@PathVariable("oficinaId") int oficinaId) {
		ModelAndView mav = new ModelAndView("oficina/oficinaDetails");
		mav.addObject(this.oficinaService.findOficinaById(oficinaId));
		return mav;
	}
	
	@GetMapping("/oficina/{oficinaId}/delete")
 	public String deleteOficina(@PathVariable("oficinaId") int oficinaId, @PathVariable("empresaId") int empresaId, ModelMap model) {
 		Oficina o = oficinaService.findOficinaById(oficinaId);
 		Empresa e = empresaService.findEmpresaById(empresaId);
 		if(o != null) {
 			Set<Vehiculo> vehiculos = o.getVehiculos();
 			if(!(vehiculos.isEmpty()) || vehiculos!=null){
 			for(Vehiculo v:vehiculos) {
 				v.removeOficina(o);
 			}
 			}
 			o.getVehiculos().removeAll(vehiculos);
 			e.removeOficina(o);
 			oficinaService.deleteOficinaById(oficinaId);
 			model.addAttribute("message", "La oficina se ha eliminado correctamente.");
 			return "redirect:/empresa/{empresaId}";
 		}else {
 			model.addAttribute("message", "No se ha encontrado la oficina que quiere eliminar.");
 			return "redirect:/empresa/{empresaId}";
 		}
 	}
	
	

}
