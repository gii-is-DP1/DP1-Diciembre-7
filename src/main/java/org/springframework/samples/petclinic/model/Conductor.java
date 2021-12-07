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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "conductor")
public class Conductor extends Actor{
	
	@Column(name = "dni", unique=true)
	@NotBlank
	private String dni;
	
	@Column(name = "ciudad")
	@NotBlank
	private String ciudad;
	
	@Column(name = "experiencia")
	@Min(3)
	private Integer experiencia;
	
	@Column(name = "permisoCoche")
	@NotNull
	private Boolean permisoCoche;
	
	@Column(name = "permisoBarco")
	@NotNull
	private Boolean permisoBarco;
	
	@Column(name = "salarioBase")
	@Min(0)
	private Double salarioBase;
	
	@Column(name = "salarioPorDia")
	@Min(0)
	private Double salarioPorDia;
	
	@OneToMany(mappedBy ="conductor")
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

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Integer getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}

	public Boolean getPermisoCoche() {
		return permisoCoche;
	}

	public void setPermisoCoche(Boolean permisoCoche) {
		this.permisoCoche = permisoCoche;
	}

	public Boolean getPermisoBarco() {
		return permisoBarco;
	}

	public void setPermisoBarco(Boolean permisoBarco) {
		this.permisoBarco = permisoBarco;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public Double getSalarioPorDia() {
		return salarioPorDia;
	}

	public void setSalarioPorDia(Double salarioPorDia) {
		this.salarioPorDia = salarioPorDia;
	}
	
	
	protected Set<Reserva> getReservasInternal() {
		if(this.reservas == null) {
			this.reservas = new HashSet<>();		
			}
		return this.reservas;
	}
	
	public List<Reserva> getReservas(){
		List<Reserva> sortedReservas = new ArrayList<>(getReservasInternal());
		PropertyComparator.sort(sortedReservas, new MutableSortDefinition("fechaInicial", false, false));
		return Collections.unmodifiableList(sortedReservas);
	}
	
	public void addReserva(Reserva reserva) {
		getReservasInternal().add(reserva);
		reserva.setConductor(this);
	}
	public boolean removeReserva(Reserva reserva) {
		return getReservasInternal().remove(reserva);
	}
	
	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("nombre", this.getNombre()).append("dni", this.getDni()).append("ciudad", this.getCiudad())
				.append("telefono", this.getTelefono()).append("email", this.getEmail()).append("experiencia", this.getExperiencia()).append("salarioBase", this.getSalarioBase()).append("salarioPorDia", this.getSalarioPorDia()).toString();
	}
	
}
