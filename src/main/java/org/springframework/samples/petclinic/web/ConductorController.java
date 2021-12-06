package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ConductorService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedDNIException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedEmailException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedTelephoneException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ConductorController {

	private static final String VIEWS_CONDUCTOR_CREATE_OR_UPDATE = "conductor/createOrUpdateConductorForm";

	private final ConductorService conductorService;
	
	private final ReservaService reservaService;

	@Autowired
	ConductorController(ConductorService conductorService, ReservaService reservaService, UserService userService,
			AuthoritiesService authoritiesService) {
		this.conductorService = conductorService;
		this.reservaService = reservaService;
	}
	@ModelAttribute("conductor")
	public Conductor findConductor(@PathVariable("conductorId") int conductorId) {
		return this.conductorService.findConductorById(conductorId);
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/conductor/new")
	public String initCreationForm(Map<String, Object> model) {
		Conductor conductor = new Conductor();
		model.put("conductor", conductor);
		return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
	}

	@PostMapping(value = "/conductor/new")
	public String processCreationForm(@Valid Conductor conductor, BindingResult result, ModelMap model)
			throws DataAccessException, DuplicatedEmailException, DuplicatedTelephoneException, DuplicatedDNIException {
		if (result.hasErrors()) {
			model.put("conductor", conductor);
			return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
		} else {
			if(conductor.getPermisoBarco().equals(false) && conductor.getPermisoCoche().equals(false)) {
				model.put("conductor", "conductor");
				model.addAttribute("message","Tiene que tener algun permiso como minimo");
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			if (!(DNIValidator.validarDNI(conductor.getDni()))) {
				model.put("conductor", conductor);
				model.addAttribute("message",
						"El DNI introducido no cumple el patron de un DNI. Son 8 digitos y 1 letra");
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			if(!(TelefonoValidator.validarTelefono(conductor.getTelefono()))) {
				model.put("conductor", conductor);
				model.addAttribute("message",
						"El telefono debe tener 9 numeros");
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			try {
				this.conductorService.saveConductor(conductor);
				model.addAttribute("Message", "Se ha registrado correctamente");
			} catch (DuplicatedTelephoneException ex) {
				result.rejectValue("telefono", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			} catch (DuplicatedEmailException ex1) {
				result.rejectValue("email", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			} catch (DuplicatedDNIException ex2) {
				result.rejectValue("dni", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			model.put("conductor", conductor);
			return "/welcome";
		}
	}

	@GetMapping(value = "/conductor/{conductorId}/edit")
	public String initUpdateConductorForm(@PathVariable("conductorId") int conductorId, Model model) {
		Conductor conductor = this.conductorService.findConductorById(conductorId);
		model.addAttribute(conductor);
		return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
	}

	@PostMapping(value = "/conductor/{conductorId}/edit")
	public String processUpdateConductorForm(@Valid Conductor conductor, BindingResult result,
			@PathVariable("conductorId") int conductorId, ModelMap model)
			throws DataAccessException, DuplicatedEmailException, DuplicatedTelephoneException, DuplicatedDNIException {
		if (result.hasErrors()) {
			model.put("conductor", conductor);
			return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
		} else {
			Conductor conductorToUpdate = this.conductorService.findConductorById(conductorId);
			BeanUtils.copyProperties(conductor, conductorToUpdate, "id", "reserva");
			if (!(DNIValidator.validarDNI(conductor.getDni()))) {
				model.put("conductor", conductor);
				model.addAttribute("message",
						"El DNI introducido no cumple el patron de un DNI. Son 8 digitos y 1 letra");
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			if(!(TelefonoValidator.validarTelefono(conductor.getTelefono()))) {
				model.put("conductor", conductor);
				model.addAttribute("message",
						"El telefono debe tener 9 numeros");
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			try {
				this.conductorService.saveConductorUpdate(conductorToUpdate);
				model.addAttribute("Message", "Se ha actualizado correctamente");
			} catch (DuplicatedTelephoneException ex1) {
				result.rejectValue("telefono", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			} catch (DuplicatedEmailException ex1) {
				result.rejectValue("email", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			} catch (DuplicatedDNIException ex2) {
				result.rejectValue("dni", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			return "redirect:/conductor/{conductorId}";
		}
	}

	@GetMapping("/conductor/{conductorId}")
	public ModelAndView showConductor(@PathVariable("conductorId") int conductorId) {
		ModelAndView mav = new ModelAndView("conductor/conductorDetails");
		mav.addObject(this.conductorService.findConductorById(conductorId));
		return mav;
	}
	
	@GetMapping(value="/conductor/{conductorId}/servicios")
	public String allServicios(Conductor conductor, BindingResult result, Map<String,Object> model){
		Collection<Reserva> results = this.reservaService.findReservasByConductor(conductor);
		model.put("selections", results);
		return "conductor/serviciosList";
	}


}
