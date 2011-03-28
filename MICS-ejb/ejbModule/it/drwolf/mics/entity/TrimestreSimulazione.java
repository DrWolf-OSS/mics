package it.drwolf.mics.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trimestre_simulazione")
public class TrimestreSimulazione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3146212234631082441L;

	private Integer trimestre;

	private String nomeTrimestre;

	public TrimestreSimulazione() {
	}

	public TrimestreSimulazione(Integer anno) {
		super();
		this.trimestre = anno;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		TrimestreSimulazione other = (TrimestreSimulazione) obj;
		if (this.trimestre == null) {
			if (other.trimestre != null) {
				return false;
			}
		} else if (!this.trimestre.equals(other.trimestre)) {
			return false;
		}
		return true;
	}

	@Column(name = "nome_trimestre", nullable = false)
	public String getNomeTrimestre() {
		return this.nomeTrimestre;
	}

	@Id
	@Column(name = "trimestre", nullable = false, unique = true)
	public Integer getTrimestre() {
		return this.trimestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.trimestre == null) ? 0 : this.trimestre.hashCode());
		return result;
	}

	public void setNomeTrimestre(String nomeTrimestre) {
		this.nomeTrimestre = nomeTrimestre;
	}

	public void setTrimestre(Integer anno) {
		this.trimestre = anno;
	}

}
