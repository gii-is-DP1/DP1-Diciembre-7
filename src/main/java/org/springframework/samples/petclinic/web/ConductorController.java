package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Conductor;
import org.springframework.samples.petclinic.model.Conductores;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ConductorService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public class ConductorController {

	private static final String VIEWS_CONDUCTOR_CREATE_OR_UPDATE = "conductor/createOrUpdateConductorForm";

	private final ConductorService conductorService;

	@Autowired
	ConductorController(ConductorService conductorService, UserService userService,
			AuthoritiesService authoritiesService) {
		this.conductorService = conductorService;
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
			throws DataAccessException, DuplicatedEmailException, DuplicatedTelephoneException {
		if (result.hasErrors()) {
			model.put("conductor", conductor);
			return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
		} else {
			try {
				this.conductorService.saveConductor(conductor);
				model.addAttribute("Message", "Se ha registrado correctamente");
			} catch (DuplicatedTelephoneException ex) {
				result.rejectValue("telefono", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			} catch (DuplicatedEmailException ex2) {
				result.rejectValue("email", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			model.put("conductor", conductor);
			return "redirect:/conductor/" + conductor.getId();
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
			throws DataAccessException, DuplicatedEmailException, DuplicatedTelephoneException {
		if (result.hasErrors()) {
			model.put("conductor", conductor);
			return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
		} else {
			Conductor conductorToUpdate = this.conductorService.findConductorById(conductorId);
			BeanUtils.copyProperties(conductor, conductorToUpdate, "id", "reserva");
			try {
				this.conductorService.saveConductor(conductorToUpdate);
				model.addAttribute("Message", "Se ha actualizado correctamente");
			} catch (DuplicatedTelephoneException ex1) {
				result.rejectValue("telefono", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			} catch (DuplicatedEmailException ex2) {
				result.rejectValue("email", "duplicate", "already exists");
				model.put("conductor", conductor);
				return VIEWS_CONDUCTOR_CREATE_OR_UPDATE;
			}
			return "redirect:/conductor/{conductorId}";
		}
	}

	@GetMapping(value = { "/conductores" })
	public String showConductoresDisponiblesList(String ciudad, TipoVehiculo tipoVehiculo, LocalDate fechaInicio,
			LocalDate fechaFin, Map<String, Object> model) {
		Conductores conductores = new Conductores();
		conductores.getConductoresList().addAll(this.conductorService.findConductoresPorCiudadPermisoYFecha(ciudad,
				tipoVehiculo, fechaInicio, fechaFin));
		model.put("conductores", conductores);
		return "conductores/conductorList";

	}

	/*
	 * @GetMapping(value = { "/conductores.xml"}) public @ResponseBody Conductores
	 * showResourcesConductorList() { Conductores conductores = new Conductores();
	 * conductores.getConductoresList().addAll(this.conductorService.
	 * findConductoresPorCiudadPermisoYFecha(VIEWS_CONDUCTOR_CREATE_OR_UPDATE, null,
	 * null, null)); return conductores; }
	 */

	@GetMapping("/conductor/{conductorId}")
	public ModelAndView showConductor(@PathVariable("conductorId") int conductorId) {
		ModelAndView mav = new ModelAndView("conductor/conductorDetails");
		mav.addObject(this.conductorService.findConductorById(conductorId));
		return mav;
	}

}
