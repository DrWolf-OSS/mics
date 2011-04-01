package it.drwolf.mics.session.home;

import it.drwolf.mics.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("simulazioneHome")
public class SimulazioneHome extends EntityHome<Simulazione> {

	@In(create = true)
	AziendaHome aziendaHome;
	@In(create = true)
	DomandaMercatoHome domandaMercatoHome;

	public void setSimulazioneId(Integer id) {
		setId(id);
	}

	public Integer getSimulazioneId() {
		return (Integer) getId();
	}

	@Override
	protected Simulazione createInstance() {
		Simulazione simulazione = new Simulazione();
		return simulazione;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Azienda azienda = aziendaHome.getDefinedInstance();
		if (azienda != null) {
			getInstance().setAzienda(azienda);
		}
		DomandaMercato domandaMercato = domandaMercatoHome.getDefinedInstance();
		if (domandaMercato != null) {
			getInstance().setDomandaMercato(domandaMercato);
		}
	}

	public boolean isWired() {
		if (getInstance().getAzienda() == null)
			return false;
		if (getInstance().getDomandaMercato() == null)
			return false;
		return true;
	}

	public Simulazione getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DatiBilancio> getDatiBilanci() {
		return getInstance() == null ? null : new ArrayList<DatiBilancio>(
				getInstance().getDatiBilanci());
	}

}
