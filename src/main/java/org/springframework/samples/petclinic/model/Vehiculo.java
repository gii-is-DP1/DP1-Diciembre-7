package org.springframework.samples.petclinic.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="vehiculos")
public class Vehiculo extends BaseEntity{
	
	@Column(name="precioBase")
	@NotBlank
	@Min(0)
	private Double precioBase;
	
	@Column(name="precioPorDia")
	@NotBlank
	@Min(0)
	private Double precioPorDia;
	
	@Column(name="marca")
	@NotBlank
	private String marca;
	
	@Column(name="modelo")
	@NotBlank
	private String modelo;
	
	@Column(name="stock")
	@NotBlank
	@Min(1)
	private Integer stock;
	
	@ManyToOne
	@JoinColumn(name = "tipoVehiculo_id")
	private TipoVehiculo tipoVehiculo;
	
	@ManyToMany(mappedBy ="vehiculos")
	private Set<Oficina> oficinas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
	@JoinColumn(name="reserva_id")
	private Set<Reserva> reservas;

	public Double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	public Double getPrecioPorDia() {
		return precioPorDia;
	}

	public void setPrecioPorDia(Double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Set<Oficina> getOficinas() {
		return oficinas;
	}

	public void setOficinas(Set<Oficina> oficinas) {
		this.oficinas = oficinas;
	}

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReserva(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	
	
	

}
