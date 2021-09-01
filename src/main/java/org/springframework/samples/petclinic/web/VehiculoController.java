package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Oficina;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.model.Vehiculo;
import org.springframework.samples.petclinic.service.OficinaService;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedOfficeAddressException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedVehicleModelException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VehiculoController {
	
	private static final String VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM = "vehiculo/createOrUpdateVehiculoForm";
	
	private final VehiculoService vehiculoService;
	private final OficinaService oficinaService;
	
	@Autowired
	public VehiculoController(VehiculoService vehiculoService, OficinaService oficinaService) {
		this.vehiculoService = vehiculoService;
		this.oficinaService = oficinaService;
	}
	
	@ModelAttribute("tipoVehiculo")
	public Collection<TipoVehiculo> populateTipoVehiculo() {
		return this.vehiculoService.findTipoVehiculo();
	}

	@ModelAttribute("oficinas")
	public Oficina findOficina(@PathVariable("oficinaId") int oficinaId) {
		return this.oficinaService.findOficinaById(oficinaId);
	}
	
	@InitBinder("vehiculo")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("oficina")
	public void initOficinaBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/vehiculo/new")
	public String initCreationForm(Oficina oficina, Map<String, Object> model) {
		Vehiculo vehiculo = new Vehiculo();
		oficina.addVehiculo(vehiculo);
		model.put("vehiculo", vehiculo);
		return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/vehiculo/new")
	public String processCreationForm(Oficina oficina, @Valid Vehiculo vehiculo, BindingResult result, ModelMap model)
			throws DataAccessException, DuplicatedVehicleModelException{
		if(result.hasErrors()) {
			model.put("vehiculo", vehiculo);
			return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		}else {
			try {
				oficina.addVehiculo(vehiculo);
				this.vehiculoService.saveVehiculo(vehiculo);
				model.addAttribute("message", "El vehiculo se ha añadido correctamente");
			}catch(DuplicatedVehicleModelException ex) {
				result.rejectValue("modelo", "duplicated", "already exists");
				model.put("vehiculo", vehiculo);
				model.addAttribute("message", "Ya existe un vehiculo con estas especificaciones");
				return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
			}
			return "redirect:/vehiculo/" + vehiculo.getId();
		}
	}
	
	@GetMapping(value = "/vehiculo/{vehiculoId}/edit")
	public String initUpdateForm(@PathVariable("vehiculoId") int vehiculoId, ModelMap model) {
		Vehiculo vehiculo = this.vehiculoService.findVehiculoById(vehiculoId);
		model.put("vehiculo", vehiculo);
		return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
	}
	
    @PostMapping(value = "/vehiculo/{vehiculoId}/edit")
	public String processUpdateForm(@Valid Vehiculo vehiculo, BindingResult result, Oficina oficina,@PathVariable("vehiculoId") int vehiculoId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("vehiculo", vehiculo);
			return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
		}else {
			Vehiculo vehiculoToUpdate=this.vehiculoService.findVehiculoById(vehiculoId);
			BeanUtils.copyProperties(vehiculo, vehiculoToUpdate, "id","oficinas","reservas");                                                                                  
                    try {                    
                        this.vehiculoService.saveVehiculo(vehiculoToUpdate);                    
                    } catch (DuplicatedVehicleModelException ex) {
                        result.rejectValue("model", "duplicate", "already exists");
                        return VIEWS_VEHICULO_CREATE_OR_UPDATE_FORM;
                    }
			return "redirect:/vehiculo/{vehiculoId}";
		}
	}
    
    @GetMapping("/vehiculo/{vehiculoId}")
	public ModelAndView showVehiculo(@PathVariable("vehiculoId") int vehiculoId) {
		ModelAndView mav = new ModelAndView("vehiculo/vehiculoDetails");
		mav.addObject(this.vehiculoService.findVehiculoById(vehiculoId));
		return mav;
	}
	
	

}
