package it.drwolf.mics.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OutputSimulazioneId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8855996881503598871L;

	private Integer simulazioneId;
	private Integer trimestreSimulazione;

	public OutputSimulazioneId() {
	}

	public OutputSimulazioneId(Integer simulazioneId,
			Integer trimestreSimulazione) {
		super();
		this.simulazioneId = simulazioneId;
		this.trimestreSimulazione = trimestreSimulazione;
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
		OutputSimulazioneId other = (OutputSimulazioneId) obj;
		if (this.trimestreSimulazione == null) {
			if (other.trimestreSimulazione != null) {
				return false;
			}
		} else if (!this.trimestreSimulazione
				.equals(other.trimestreSimulazione)) {
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

	@Column(name = "simulazione_id", nullable = false)
	public Integer getSimulazioneId() {
		return this.simulazioneId;
	}

	@Column(name = "trimestre_simulazione", nullable = false)
	public Integer getTrimestreSimulazione() {
		return this.trimestreSimulazione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.trimestreSimulazione == null) ? 0
						: this.trimestreSimulazione.hashCode());
		result = prime
				* result
				+ ((this.simulazioneId == null) ? 0 : this.simulazioneId
						.hashCode());
		return result;
	}

	public void setSimulazioneId(Integer simulazioneId) {
		this.simulazioneId = simulazioneId;
	}

	public void setTrimestreSimulazione(Integer trimestreSimulazione) {
		this.trimestreSimulazione = trimestreSimulazione;
	}

}
