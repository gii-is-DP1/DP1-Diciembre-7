package org.springframework.samples.petclinic.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DNIValidator {
		
	public static boolean validarDNI(String dni) {
		String dniUpper = dni.toUpperCase();
		Pattern patron = Pattern.compile("[0-9]{8}[A-Z]");
		Matcher mat = patron.matcher(dniUpper);
		return mat.matches();
        
    }

}
