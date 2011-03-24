package it.drwolf.mics.session;

import it.drwolf.mics.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("aziendaHome")
public class AziendaHome extends EntityHome<Azienda> {

	public void setAziendaId(Integer id) {
		setId(id);
	}

	public Integer getAziendaId() {
		return (Integer) getId();
	}

	@Override
	protected Azienda createInstance() {
		Azienda azienda = new Azienda();
		return azienda;
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

	public Azienda getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Simulazione> getSimulazioni() {
		return getInstance() == null ? null : new ArrayList<Simulazione>(
				getInstance().getSimulazioni());
	}

}
