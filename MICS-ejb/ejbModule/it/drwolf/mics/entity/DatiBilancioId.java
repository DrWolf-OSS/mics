package it.drwolf.mics.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DatiBilancioId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7481630621275153001L;

	private Integer simulazioneId;
	private Integer annoBilancio;

	public DatiBilancioId() {
	}

	public DatiBilancioId(Integer simulazioneId, Integer annoBilancio) {
		this.simulazioneId = simulazioneId;
		this.annoBilancio = annoBilancio;
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
		DatiBilancioId other = (DatiBilancioId) obj;
		if (this.annoBilancio == null) {
			if (other.annoBilancio != null) {
				return false;
			}
		} else if (!this.annoBilancio.equals(other.annoBilancio)) {
			return false;
		}
		if (this.simulazioneId == null) {
			if (other.simulazioneId != null) {
				return false;
			}
		} else if (!this.simulazioneId.equals(other.simulazioneId)) {
			return false;
		}
		return true;
	}

	@Column(name = "anno_bilancio", nullable = false)
	public Integer getAnnoBilancio() {
		return this.annoBilancio;
	}

	@Column(name = "simulaizone_id", nullable = false)
	public Integer getSimulazioneId() {
		return this.simulazioneId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.annoBilancio == null) ? 0 : this.annoBilancio
						.hashCode());
		result = prime
				* result
				+ ((this.simulazioneId == null) ? 0 : this.simulazioneId
						.hashCode());
		return result;
	}

	public void setAnnoBilancio(Integer annoBilancio) {
		this.annoBilancio = annoBilancio;
	}

	public void setSimulazioneId(Integer simulazioneId) {
		this.simulazioneId = simulazioneId;
	}

}
