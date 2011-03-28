package it.drwolf.mics.session;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("outputSimulazioneHome")
public class OutputSimulazioneHome extends EntityHome<OutputSimulazione> {

	@In(create = true)
	SimulazioneHome simulazioneHome;
	@In(create = true)
	TrimestreSimulazioneHome trimestreSimulazioneHome;

	public void setOutputSimulazioneId(OutputSimulazioneId id) {
		setId(id);
	}

	public OutputSimulazioneId getOutputSimulazioneId() {
		return (OutputSimulazioneId) getId();
	}

	public OutputSimulazioneHome() {
		setOutputSimulazioneId(new OutputSimulazioneId());
	}

	@Override
	public boolean isIdDefined() {
		if (getOutputSimulazioneId().getSimulazioneId() == null)
			return false;
		if (getOutputSimulazioneId().getTrimestreSimulazione() == null)
			return false;
		return true;
	}

	@Override
	protected OutputSimulazione createInstance() {
		OutputSimulazione outputSimulazione = new OutputSimulazione();
		outputSimulazione.setId(new OutputSimulazioneId());
		return outputSimulazione;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Simulazione simulazione = simulazioneHome.getDefinedInstance();
		if (simulazione != null) {
			getInstance().setSimulazione(simulazione);
		}
		TrimestreSimulazione trimestreSimulazione = trimestreSimulazioneHome
				.getDefinedInstance();
		if (trimestreSimulazione != null) {
			getInstance().setTrimestreSimulazione(trimestreSimulazione);
		}
	}

	public boolean isWired() {
		if (getInstance().getSimulazione() == null)
			return false;
		if (getInstance().getTrimestreSimulazione() == null)
			return false;
		return true;
	}

	public OutputSimulazione getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
