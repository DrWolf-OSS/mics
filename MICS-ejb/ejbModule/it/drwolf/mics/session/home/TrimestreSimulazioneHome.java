package it.drwolf.mics.session.home;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("trimestreSimulazioneHome")
public class TrimestreSimulazioneHome extends EntityHome<TrimestreSimulazione> {

	public void setTrimestreSimulazioneTrimestre(Integer id) {
		setId(id);
	}

	public Integer getTrimestreSimulazioneTrimestre() {
		return (Integer) getId();
	}

	@Override
	protected TrimestreSimulazione createInstance() {
		TrimestreSimulazione trimestreSimulazione = new TrimestreSimulazione();
		return trimestreSimulazione;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public TrimestreSimulazione getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
