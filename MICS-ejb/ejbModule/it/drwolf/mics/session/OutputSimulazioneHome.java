package it.drwolf.mics.session;

import it.drwolf.mics.entity.OutputSimulazioneId;
import it.drwolf.mics.entity.OutputSimulazione;
import it.drwolf.mics.entity.Simulazione;
import it.drwolf.mics.entity.TrimestreSimulazione;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("outputSimulazioneHome")
public class OutputSimulazioneHome extends EntityHome<OutputSimulazione> {

	@In(create = true)
	SimulazioneHome simulazioneHome;
	@In(create = true)
	TrimestreSimulazioneHome trimestreSimulazioneHome;

	public OutputSimulazioneHome() {
		this.setOutputSimulazioneId(new OutputSimulazioneId());
	}

	@Override
	protected OutputSimulazione createInstance() {
		OutputSimulazione outputSimulazione = new OutputSimulazione();
		outputSimulazione.setId(new OutputSimulazioneId());
		return outputSimulazione;
	}

	public OutputSimulazione getDefinedInstance() {
		return this.isIdDefined() ? this.getInstance() : null;
	}

	public OutputSimulazioneId getOutputSimulazioneId() {
		return (OutputSimulazioneId) this.getId();
	}

	@Override
	public boolean isIdDefined() {
		if (this.getOutputSimulazioneId().getSimulazioneId() == null) {
			return false;
		}
		if (this.getOutputSimulazioneId().getTrimestreSimulazione() == null) {
			return false;
		}
		return true;
	}

	public boolean isWired() {
		if (this.getInstance().getSimulazione() == null) {
			return false;
		}
		if (this.getInstance().getTrimestreSimulazione() == null) {
			return false;
		}
		return true;
	}

	public void load() {
		if (this.isIdDefined()) {
			this.wire();
		}
	}

	public void setOutputSimulazioneId(OutputSimulazioneId id) {
		this.setId(id);
	}

	public void wire() {
		this.getInstance();
		Simulazione simulazione = this.simulazioneHome.getDefinedInstance();
		if (simulazione != null) {
			this.getInstance().setSimulazione(simulazione);
		}
		TrimestreSimulazione trimestreSimulazione = this.trimestreSimulazioneHome
				.getDefinedInstance();
		if (trimestreSimulazione != null) {
			this.getInstance().setTrimestreSimulazione(trimestreSimulazione);
		}
	}

}
