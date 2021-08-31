package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Conductores {
	
	private List<Conductor> conductores;
	
	@XmlElement
	public List<Conductor> getConductoresList(){
		if(conductores == null) {
			conductores = new ArrayList<>();
			
		}
		return conductores;
	}

}
