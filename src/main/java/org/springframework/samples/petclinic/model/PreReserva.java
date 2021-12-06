package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="prereserva")
public class PreReserva extends BaseEntity{
	
	@Column(name="fechaInicio")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaInicio;
	
	@Column(name="fechaFin")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFin;
	
	@Column(name="ciudad")
	@NotBlank
	private String ciudad;
	
	@ManyToOne
	@JoinColumn(name = "tipoVehiculo_id")
	private TipoVehiculo tipoVehiculo;

}
