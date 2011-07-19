package it.drwolf.mics.util;

import it.drwolf.mics.entity.AnnoBilancio;
import it.drwolf.mics.entity.Azienda;
import it.drwolf.mics.entity.DatiBilancio;
import it.drwolf.mics.entity.DatiBilancioId;
import it.drwolf.mics.entity.DomandaMercato;
import it.drwolf.mics.entity.OutputSimulazione;
import it.drwolf.mics.entity.Simulazione;
import it.drwolf.mics.entity.TrimestreSimulazione;
import it.drwolf.mics.model.MiICSiThinkModel;
import it.drwolf.mics.session.home.DatiBilancioHome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("simulationBean")
@Scope(ScopeType.CONVERSATION)
public class SimulationBean {

	@In
	EntityManager entityManager;

	@In(create = true)
	DatiBilancioHome datiBilancioHome;

	// Step One
	private String azienda;
	private String settore;
	private Integer anno0;
	private String solutionType;
	private Integer percentualeCostiProduzioneTerritorio;
	private Integer percentualeIndottoCongiunturaleTerritorio;
	private TreeMap<Integer, DatiBilancio> datiBilancio = new TreeMap<Integer, DatiBilancio>();
	private DomandaMercato domandaMercato = new DomandaMercato();

	private ArrayList<OutputSimulazione> risultati = new ArrayList<OutputSimulazione>();

	public String checkFinalStep() {
		// prima dovresi verificare la correttezza dei dati
		// tipo la congruenza dei dati di bilancio

		// a questo punto dovrei persistere
		// Persisto l'azienda
		Azienda azienda = new Azienda();
		azienda.setAzienda(this.azienda);
		azienda.setSettore(this.settore);
		this.entityManager.persist(azienda);

		// salvo la domanda di mercato
		this.entityManager.persist(this.domandaMercato);

		// poi persisto al Simulazione
		Simulazione simulazione = new Simulazione();
		simulazione.setDataInserimento(new Date());
		simulazione.setAzienda(azienda);
		this.entityManager.persist(simulazione);

		// a questo punto devo salvare i Dati di Bilancio
		for (Integer anno : this.datiBilancio.keySet()) {
			AnnoBilancio annoBilancio = this.entityManager.find(
					AnnoBilancio.class, anno);
			DatiBilancioId datiBilancioId = new DatiBilancioId(
					simulazione.getId(), anno);
			DatiBilancio datiBilancio = this.datiBilancio.get(anno);
			datiBilancio.setAnnoBilancio(annoBilancio);
			datiBilancio.setSimulazione(simulazione);
			datiBilancio.setId(datiBilancioId);
			// this.entityManager.persist(datiBilancio);
			this.datiBilancioHome.setInstance(datiBilancio);
			this.datiBilancioHome.persist();
		}

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

	public List<Integer> getAnni(int indice) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		for (int i = 1; i < indice; i++) {
			lista.add(currentYear - i);
		}

		return lista;
	}

	public List<Integer> getAnniBilancio(int indice) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < indice; i++) {
			lista.add(this.anno0 - i);
		}
		return lista;
	}

	public Integer getAnno0() {
		return this.anno0;
	}

	public Integer getAnnoSimulazione(int indice) {
		return this.anno0 + indice;
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

	public DomandaMercato getDomandaMercato() {
		return this.domandaMercato;
	}

	public Integer getPercentualeCostiProduzioneTerritorio() {
		return this.percentualeCostiProduzioneTerritorio;
	}

	public Integer getPercentualeIndottoCongiunturaleTerritorio() {
		return this.percentualeIndottoCongiunturaleTerritorio;
	}

	public ArrayList<OutputSimulazione> getRisultati() {
		return this.risultati;
	}

	public OutputSimulazione getRisultatiTrimestre(TrimestreSimulazione ts) {
		for (OutputSimulazione os : this.risultati) {
			if (os.getTrimestreSimulazione().equals(ts)) {
				return os;
			}
		}
		return null;
	}

	// Step Two

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

	public void setDomandaMercato(DomandaMercato domandaMercato) {
		this.domandaMercato = domandaMercato;
	}

	public void setPercentualeCostiProduzioneTerritorio(
			Integer percentualeCostiProduzioneTerritorio) {
		this.percentualeCostiProduzioneTerritorio = percentualeCostiProduzioneTerritorio;
	}

	public void setPercentualeIndottoCongiunturaleTerritorio(
			Integer percentualeIndottoCongiunturaleTerritorio) {
		this.percentualeIndottoCongiunturaleTerritorio = percentualeIndottoCongiunturaleTerritorio;
	}

	public void setRisultati(ArrayList<OutputSimulazione> risultati) {
		this.risultati = risultati;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

	public void setSolutionType(String solutionType) {
		this.solutionType = solutionType;
	}

	public void startSimulation() {
		System.out.println("Avvio la simulazione");
		System.out.println(this.datiBilancioHome.getInstance().getId());

		MiICSiThinkModel micsModel = new MiICSiThinkModel(this.entityManager,
				new Double(this.getPercentualeIndottoCongiunturaleTerritorio()));
		this.risultati = micsModel.execute(this.datiBilancioHome.getInstance(),
				this.domandaMercato);

	}
}
