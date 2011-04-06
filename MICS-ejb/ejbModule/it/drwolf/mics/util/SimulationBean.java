package it.drwolf.mics.util;

import it.drwolf.mics.entity.DatiBilancio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

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
	private String solutionType;
	private Integer percentualeCostiProduzioneTerritorio;
	private Integer percentualeIndottoCongiunturaleTerritorio;
	private TreeMap<Integer, DatiBilancio> datiBilancio = new TreeMap<Integer, DatiBilancio>();

	public String checkFinalStep() {
		return "OK";
	}

	public String checkStepFifth() {
		return "OK";
	}

	public String checkStepFourth() {
		return "OK";
	}

	public String checkStepOne() {
		return "OK";
	}

	public String checkStepThird() {
		return "OK";
	}

	public String checkStepTwo() {
		return "OK";
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

	public List<Integer> getAnniBilancio() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < 2; i++) {
			lista.add(this.anno0 - i);
		}
		return lista;
	}

	public Integer getAnno0() {
		return this.anno0;
	}

	public String getAzienda() {
		return this.azienda;
	}

	public DatiBilancio getDatiBilancio(int anno) {
		if (this.datiBilancio.get(anno) == null) {
			this.datiBilancio.put(anno, new DatiBilancio());
		}
		return this.datiBilancio.get(anno);

	}

	public Integer getPercentualeCostiProduzioneTerritorio() {
		return this.percentualeCostiProduzioneTerritorio;
	}

	// Step Two

	public Integer getPercentualeIndottoCongiunturaleTerritorio() {
		return this.percentualeIndottoCongiunturaleTerritorio;
	}

	public String getSettore() {
		return this.settore;
	}

	public List<Settori> getSettori() {
		return Arrays.asList(Settori.values());
	}

	public String getSolutionType() {
		return this.solutionType;
	}

	public List<?> getStepFourthFields() {
		if (this.solutionType.equals("A")) {
			return Arrays.asList(StepFourthTypeAFields.values());
		} else {
			return Arrays.asList(StepFouthTypeBFields.values());
		}

	}

	public List<StepThirdFields> getStepThirdFields() {
		return Arrays.asList(StepThirdFields.values());
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

	public void setPercentualeCostiProduzioneTerritorio(
			Integer percentualeCostiProduzioneTerritorio) {
		this.percentualeCostiProduzioneTerritorio = percentualeCostiProduzioneTerritorio;
	}

	public void setPercentualeIndottoCongiunturaleTerritorio(
			Integer percentualeIndottoCongiunturaleTerritorio) {
		this.percentualeIndottoCongiunturaleTerritorio = percentualeIndottoCongiunturaleTerritorio;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}
}
