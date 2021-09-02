package org.springframework.samples.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.persistence.JoinColumn;

@Entity
@Table(name="oficinas")
public class Oficina extends BaseEntity{
	
	@Column(name="ciudad")
	@NotBlank
	private String ciudad;
	
	@Column(name="direccion")
	@NotBlank
	private String direccion;
	
	@Column(name="codigoPostal")
	private Integer codigoPostal;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="vehiculosOficinas",
	joinColumns = @JoinColumn(name = "oficina_id"),
	inverseJoinColumns = @JoinColumn(name = "vehiculo_id"))
	private Set<Vehiculo> vehiculos;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	private Empresa empresa;

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	protected Set<Vehiculo> getVehiculosInternal(){
		if(this.vehiculos == null) {
			this.vehiculos = new HashSet<>();
		}
		return this.vehiculos;
	}

	public Set<Vehiculo> getVehiculos() {
		return getVehiculosInternal();
	}

	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	public void addVehiculo(Vehiculo vehiculo) {
		getVehiculos().add(vehiculo);
		vehiculo.getOficinas().add(this);
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean removeVehiculo(Vehiculo v) {
		return getVehiculos().remove(v);
	}
	
	

}
