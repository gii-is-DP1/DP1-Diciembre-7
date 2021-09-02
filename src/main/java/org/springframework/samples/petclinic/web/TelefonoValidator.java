package org.springframework.samples.petclinic.web;

public class TelefonoValidator {
	
	public static boolean validarTelefono(String telefono) {
		boolean res = false;
		if(telefono.length()!=9) {
			return res;
		}else {
			res = true;
			return res;
		}	 
	}

}
