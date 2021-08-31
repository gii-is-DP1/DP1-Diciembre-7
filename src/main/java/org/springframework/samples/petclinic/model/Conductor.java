package org.springframework.samples.petclinic.model;

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
	@NotBlank
	@Min(3)
	private Integer experiencia;
	
	@Column(name = "permisoCoche")
	private Boolean permisoCoche;
	
	@Column(name = "permisoBarco")
	private Boolean permisoBarco;
	
	@Column(name = "salarioBase")
	@NotBlank
	@Min(0)
	private Double salarioBase;
	
	@Column(name = "salarioPorDia")
	@NotBlank
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
	
	public Set<Reserva> getReservas() {
		return reservas;
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
