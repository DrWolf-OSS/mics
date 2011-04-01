package it.drwolf.mics.session.home;

import it.drwolf.mics.entity.Azienda;
import it.drwolf.mics.entity.Simulazione;
import it.drwolf.mics.util.Settori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("aziendaHome")
public class AziendaHome extends EntityHome<Azienda> {

	@Override
	protected Azienda createInstance() {
		Azienda azienda = new Azienda();
		return azienda;
	}

	public List<Integer> getAnni() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		for (int i = 1; i < 4; i++) {
			lista.add(currentYear - i);
		}

		return lista;
	}

	public Integer getAziendaId() {
		return (Integer) this.getId();
	}

	public Azienda getDefinedInstance() {
		return this.isIdDefined() ? this.getInstance() : null;
	}

	public List<Settori> getSettori() {
		return Arrays.asList(Settori.values());
	}

	public List<Simulazione> getSimulazioni() {
		return this.getInstance() == null ? null : new ArrayList<Simulazione>(
				this.getInstance().getSimulazioni());
	}

	public boolean isWired() {
		return true;
	}

	public void load() {
		if (this.isIdDefined()) {
			this.wire();
		}
	}

	public void setAziendaId(Integer id) {
		this.setId(id);
	}

	public void wire() {
		this.getInstance();
	}

}
