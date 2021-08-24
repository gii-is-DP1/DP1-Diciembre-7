package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "conductor")
public class Conductor extends Actor{
	
	@Column(name = "dni")
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
	
}
