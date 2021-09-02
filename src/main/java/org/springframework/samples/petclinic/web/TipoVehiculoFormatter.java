package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.TipoVehiculo;
import org.springframework.samples.petclinic.service.VehiculoService;
import org.springframework.stereotype.Component;

@Component
public class TipoVehiculoFormatter implements Formatter<TipoVehiculo>{
	
	private final VehiculoService vehiculoService;
	
	@Autowired
	public TipoVehiculoFormatter(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}
	
	@Override
	public String print(TipoVehiculo tipoVehiculo, Locale locale) {
		return tipoVehiculo.getName();
	}
	
	@Override
	public TipoVehiculo parse(String text, Locale locale) throws ParseException	{
		Collection<TipoVehiculo> findTiposVehiculo = this.vehiculoService.findTipoVehiculo();
		for(TipoVehiculo tipoVehiculo: findTiposVehiculo) {
			if(tipoVehiculo.getName().equals(text)) {
				return tipoVehiculo;
			}
		}
		throw new ParseException("tipo de vehiculo not found: " + text, 0);
	}

}
