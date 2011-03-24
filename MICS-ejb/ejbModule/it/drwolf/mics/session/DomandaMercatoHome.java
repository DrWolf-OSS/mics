package it.drwolf.mics.session;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("domandaMercatoHome")
public class DomandaMercatoHome extends EntityHome<DomandaMercato> {

	public void setDomandaMercatoId(Integer id) {
		setId(id);
	}

	public Integer getDomandaMercatoId() {
		return (Integer) getId();
	}

	@Override
	protected DomandaMercato createInstance() {
		DomandaMercato domandaMercato = new DomandaMercato();
		return domandaMercato;
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

	public DomandaMercato getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
