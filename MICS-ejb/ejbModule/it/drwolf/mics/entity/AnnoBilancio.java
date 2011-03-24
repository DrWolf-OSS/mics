package it.drwolf.mics.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "anno_bilancio")
public class AnnoBilancio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 604643208027495235L;

	private Integer anno;
	private Set<DatiBilancio> datiBilanci = new HashSet<DatiBilancio>(0);

	public AnnoBilancio() {
	}

	public AnnoBilancio(int anno) {
		this.anno = anno;
	}

	public AnnoBilancio(int anno, Set<DatiBilancio> datiBilanci) {
		this.anno = anno;
		this.datiBilanci = datiBilanci;
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
		AnnoBilancio other = (AnnoBilancio) obj;
		if (this.anno == null) {
			if (other.anno != null) {
				return false;
			}
		} else if (!this.anno.equals(other.anno)) {
			return false;
		}
		if (this.datiBilanci == null) {
			if (other.datiBilanci != null) {
				return false;
			}
		} else if (!this.datiBilanci.equals(other.datiBilanci)) {
			return false;
		}
		return true;
	}

	@Id
	@Column(name = "anno", nullable = false, unique = true)
	public Integer getAnno() {
		return this.anno;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "annoBilancio")
	public Set<DatiBilancio> getDatiBilanci() {
		return this.datiBilanci;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.anno == null) ? 0 : this.anno.hashCode());
		result = prime
				* result
				+ ((this.datiBilanci == null) ? 0 : this.datiBilanci.hashCode());
		return result;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public void setDatiBilanci(Set<DatiBilancio> datiBilanci) {
		this.datiBilanci = datiBilanci;
	}

}
