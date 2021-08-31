package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.core.style.ToStringCreator;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa extends Actor{
	
	@Column(name = "pais")
	@NotBlank
	private String pais;
	
	@OneToMany(mappedBy ="empresa")
	private Set<Oficina> oficinas;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public Set<Oficina> getOficinas() {
		return oficinas;
	}

	public void setOficinas(Set<Oficina> oficinas) {
		this.oficinas = oficinas;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("nombre", this.getNombre()).append("email", this.getEmail()).append("pais", this.getPais()).toString();
	}
	
}
