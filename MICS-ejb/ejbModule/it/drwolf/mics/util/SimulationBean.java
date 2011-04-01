package it.drwolf.mics.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("simulationBean")
@Scope(ScopeType.CONVERSATION)
public class SimulationBean {

	// Step One
	private String azienda;
	private String settore;
	private Integer anno0;

	// Step Two

	public List<Integer> getAnni() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		for (int i = 1; i < 4; i++) {
			lista.add(currentYear - i);
		}

		return lista;
	}

	public List<Integer> getAnniBilancio() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < 2; i++) {
			lista.add(this.anno0 - 1);
		}
		return lista;
	}

	public Integer getAnno0() {
		return this.anno0;
	}

	public String getAzienda() {
		return this.azienda;
	}

	public String getSettore() {
		return this.settore;
	}

	public List<Settori> getSettori() {
		return Arrays.asList(Settori.values());
	}

	public List<StepTwoFields> getStepTwoFields() {
		return Arrays.asList(StepTwoFields.values());
	}

	public void setAnno0(Integer anno0) {
		this.anno0 = anno0;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}
}
