package it.drwolf.mics.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "azienda")
public class Azienda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5828739099562163863L;

	private Integer id;
	private String azienda;
	private Set<Simulazione> simulazioni = new HashSet<Simulazione>(0);

	@Column(name = "azienda", nullable = false)
	public String getAzienda() {
		return this.azienda;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_azienda", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "azienda")
	public Set<Simulazione> getSimulazioni() {
		return this.simulazioni;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSimulazioni(Set<Simulazione> simulazioni) {
		this.simulazioni = simulazioni;
	}

}
