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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;


@Entity
@Table(name="vehiculos")
public class Vehiculo extends BaseEntity{
	
	@Column(name="precioBase")
	@Min(0)
	private Double precioBase;
	
	@Column(name="precioPorDia")
	@Min(0)
	private Double precioPorDia;
	
	@Column(name="marca")
	@NotBlank
	private String marca;
	
	@Column(name="modelo")
	@NotBlank
	private String modelo;
	
	@Column(name="stock")
	@Min(1)
	private Integer stock;
	
	@ManyToOne
	@JoinColumn(name = "tipoVehiculo_id")
	private TipoVehiculo tipoVehiculo;
	
	@ManyToMany(mappedBy ="vehiculos")
	private Set<Oficina> oficinas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculo")
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
	protected Set<Oficina> getOficinasInternal(){
		if(this.oficinas == null) {
			this.oficinas = new HashSet<>();
		}
		return this.oficinas;
	}

	public Set<Oficina> getOficinas() {
		return getOficinasInternal();
	}

	public void setOficinas(Set<Oficina> oficinas) {
		this.oficinas = oficinas;
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
		reserva.setVehiculo(this);
	}
	
	public boolean removeReserva(Reserva reserva) {
		return getReservasInternal().remove(reserva);
	}

	public void setReserva(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public boolean removeOficina(Oficina o) {
		return getOficinas().remove(o);
	}

	
	
	

}
