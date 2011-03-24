package it.drwolf.mics.session;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("datiBilancioHome")
public class DatiBilancioHome extends EntityHome<DatiBilancio> {

	@In(create = true)
	AnnoBilancioHome annoBilancioHome;
	@In(create = true)
	SimulazioneHome simulazioneHome;

	public void setDatiBilancioId(DatiBilancioId id) {
		setId(id);
	}

	public DatiBilancioId getDatiBilancioId() {
		return (DatiBilancioId) getId();
	}

	public DatiBilancioHome() {
		setDatiBilancioId(new DatiBilancioId());
	}

	@Override
	public boolean isIdDefined() {
		if (getDatiBilancioId().getAnnoBilancio() == null)
			return false;
		if (getDatiBilancioId().getSimulazioneId() == null)
			return false;
		return true;
	}

	@Override
	protected DatiBilancio createInstance() {
		DatiBilancio datiBilancio = new DatiBilancio();
		datiBilancio.setId(new DatiBilancioId());
		return datiBilancio;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		AnnoBilancio annoBilancio = annoBilancioHome.getDefinedInstance();
		if (annoBilancio != null) {
			getInstance().setAnnoBilancio(annoBilancio);
		}
		Simulazione simulazione = simulazioneHome.getDefinedInstance();
		if (simulazione != null) {
			getInstance().setSimulazione(simulazione);
		}
	}

	public boolean isWired() {
		if (getInstance().getAnnoBilancio() == null)
			return false;
		if (getInstance().getSimulazione() == null)
			return false;
		return true;
	}

	public DatiBilancio getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
