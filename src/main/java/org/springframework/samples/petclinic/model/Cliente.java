/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends Actor {

	@Column(name = "dni", unique = true)
	@NotBlank
	private String dni;
	
	@Column(name = "direccion")
	@NotBlank
	private String direccion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cliente")
	private Set<Reserva> reservas;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	protected Set<Reserva> getReservasInternal(){
		if(this.reservas == null) {
			this.reservas = new HashSet<>();		
			}
		return this.reservas;
	}
	
	protected void setReservasInternal(Set<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public List<Reserva> getReservas(){
		List<Reserva> sortedReservas = new ArrayList<>(getReservasInternal());
		PropertyComparator.sort(sortedReservas, new MutableSortDefinition("fechaInicial", false, false));
		return Collections.unmodifiableList(sortedReservas);
	}
	
	public void addReserva(Reserva reserva) {
		getReservasInternal().add(reserva);
		reserva.setCliente(this);
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("nombre", this.getNombre()).append("dni", this.getDni()).append("direccion", this.getDireccion())
				.append("telefono", this.getTelefono()).append("email", this.getEmail()).toString();
	}
		
	
}
