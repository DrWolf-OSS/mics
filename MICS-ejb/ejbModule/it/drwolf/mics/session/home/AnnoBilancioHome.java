package it.drwolf.mics.session.home;

import it.drwolf.mics.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("annoBilancioHome")
public class AnnoBilancioHome extends EntityHome<AnnoBilancio> {

	public void setAnnoBilancioAnno(Integer id) {
		setId(id);
	}

	public Integer getAnnoBilancioAnno() {
		return (Integer) getId();
	}

	@Override
	protected AnnoBilancio createInstance() {
		AnnoBilancio annoBilancio = new AnnoBilancio();
		return annoBilancio;
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

	public AnnoBilancio getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DatiBilancio> getDatiBilanci() {
		return getInstance() == null ? null : new ArrayList<DatiBilancio>(
				getInstance().getDatiBilanci());
	}

}
