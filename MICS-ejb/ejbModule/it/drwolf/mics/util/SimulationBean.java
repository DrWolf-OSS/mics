package it.drwolf.mics.util;

import it.drwolf.mics.entity.AnnoBilancio;
import it.drwolf.mics.entity.Azienda;
import it.drwolf.mics.entity.DatiBilancio;
import it.drwolf.mics.entity.DatiBilancioId;
import it.drwolf.mics.entity.DomandaMercato;
import it.drwolf.mics.entity.Glossario;
import it.drwolf.mics.entity.OutputSimulazione;
import it.drwolf.mics.entity.Simulazione;
import it.drwolf.mics.entity.TrimestreSimulazione;
import it.drwolf.mics.model.MiICSiThinkModel;
import it.drwolf.mics.session.home.DatiBilancioHome;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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
	private BigDecimal costoMateriePrime;
	private Integer percentualeCostiProduzioneTerritorio;

	private Integer percentualeIndottoCongiunturaleTerritorio;

	private boolean datiOk = true;

	private Integer innovazioneProdottoSpinner;

	private Integer innovazioneProcessoSpinner;

	private Integer reputazioneSpinner;

	private Double innovazioneDiProdotto = new Double(0.1);

	private TreeMap<Integer, DatiBilancio> datiBilancio = new TreeMap<Integer, DatiBilancio>();

	private DomandaMercato domandaMercato = new DomandaMercato();

	private ArrayList<OutputSimulazione> risultati = new ArrayList<OutputSimulazione>();

	private HashMap<String, ArrayList<ArrayList<Integer>>> visualized = new HashMap<String, ArrayList<ArrayList<Integer>>>();

	private HashMap<String, String> labels = new HashMap<String, String>();

	private List<String> alertMessages = new ArrayList<String>();

	public boolean checkAlert() {
		boolean result = false;
		this.setAlertMessages(new ArrayList<String>());
		this.getAlertMessages()
				.add("Attenzione! Dall'analisi dei dati di bilanio inseriti sono emersi i seguenti problemi:");
		if (this.datiBilancio.get(this.anno0).getRoe() != null
				&& this.datiBilancio.get(this.anno0)
						.getRendimentoInvestimentiSicuri() != null) {
			if (this.datiBilancio
					.get(this.anno0)
					.getRoe()
					.compareTo(
							this.getDatiBilancio(this.anno0)
									.getRendimentoInvestimentiSicuri()) <= 0) {
				result = true;
				this.getAlertMessages()
						.add("Il valore del ROE non dobvrebbe essere uguale o inferiore al valore del rendimento in investimenti sicuri");

			}
		}
		if (this.datiBilancio.get(this.anno0).getEbitda() != null) {
			if (this.datiBilancio.get(this.anno0).getEbitda()
					.compareTo(new BigDecimal(0)) < 0) {
				result = true;
				this.getAlertMessages().add(
						"Il valore del EBITDA non dobvrebbe essere negativo");
			}
		}
		if (this.datiBilancio.get(this.anno0).getDebitiBancheSuFatturato() != null) {
			if (this.datiBilancio.get(this.anno0).getDebitiBancheSuFatturato()
					.compareTo(new BigDecimal(50)) > 0) {
				result = true;
				this.getAlertMessages()
						.add("Il valore dei  Debiti v/banche su fatturato non dobvrebbe essere superiore al 50%");
			}
		}
		if (this.datiBilancio.get(this.anno0).getRoi() != null) {
			if (this.datiBilancio.get(this.anno0).getRoi()
					.compareTo(new BigDecimal(0)) < 0) {
				result = true;
				this.getAlertMessages().add(
						"Il valore del ROI non dobvrebbe essere negativo");
			}
		}
		return result;
	}

	public boolean checkCalcoloModello() {
		if (this.isDatiOk()) {
			return true;
		}
		return false;
	}

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

	public List<String> getAlertMessages() {
		return this.alertMessages;
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

	public BigDecimal getCostoMateriePrime() {
		return this.costoMateriePrime;
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

	public ArrayList<Entry<String, ArrayList<ArrayList<Integer>>>> getEntries() {
		return new ArrayList<Entry<String, ArrayList<ArrayList<Integer>>>>(
				this.visualized.entrySet());
	}

	public Double getInnovazioneDiProdotto() {
		return this.innovazioneDiProdotto;
	}

	public Integer getInnovazioneProcessoSpinner() {
		return this.innovazioneProcessoSpinner;
	}

	public Integer getInnovazioneProdottoSpinner() {
		return this.innovazioneProdottoSpinner;
	}

	public Integer getPercentualeCostiProduzioneTerritorio() {
		return this.percentualeCostiProduzioneTerritorio;
	}

	public Integer getPercentualeIndottoCongiunturaleTerritorio() {
		return this.percentualeIndottoCongiunturaleTerritorio;
	}

	public Integer getReputazioneSpinner() {
		return this.reputazioneSpinner;
	}

	public boolean getRequiredForFiled(String field) {
		StepTwoFields v = StepTwoFields.valueOf(field);
		return v.getRequired();

	}

	public ArrayList<OutputSimulazione> getRisultati() {
		return this.risultati;
	}

	public ArrayList<ArrayList<Integer>> getRisultatiByName(String name) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int index = 3; index < this.risultati.size(); index++) {
			OutputSimulazione os = this.risultati.get(index);
			try {
				ArrayList<Integer> trimestre = new ArrayList<Integer>();
				trimestre.add(os.getTrimestreSimulazione().getTrimestre());
				Object res = os.getClass().getMethod(name, null)
						.invoke(os, null);
				if (res instanceof Double) {
					Double d = (Double) res;
					trimestre.add(d.intValue());
				} else if (res instanceof BigDecimal) {
					BigDecimal bd = (BigDecimal) res;
					trimestre.add(bd.intValue());
				} else if (res instanceof Integer) {
					Integer i = (Integer) res;
					trimestre.add(i);
				} else {
					trimestre.add(0);
				}
				result.add(trimestre);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public OutputSimulazione getRisultatiTrimestre(TrimestreSimulazione ts) {
		for (OutputSimulazione os : this.risultati) {
			if (os.getTrimestreSimulazione().equals(ts)) {
				return os;
			}
		}
		return null;
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

	public List<?> getStepThirdFields() {
		if (this.solutionType.equals("A")) {
			return Arrays.asList(StepThirdTypeAFields.values());
		} else {
			return Arrays.asList(StepThirdTypeBFields.values());
		}

	}

	public List<StepTwoFields> getStepTwoFields() {
		return Arrays.asList(StepTwoFields.values());
	}

	public String getUnitaDiMisur(String name) {
		String unita = "";
		try {
			Glossario voce = (Glossario) this.entityManager
					.createQuery("from Glossario where name= :name")
					.setParameter("name", name).getSingleResult();
			unita = voce.getUnitaDiMisura();
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return unita;
	}

	public HashMap<String, ArrayList<ArrayList<Integer>>> getVisualized() {
		return this.visualized;
	}

	public String getVocabolo(String name) {
		String vocabolo = "";
		try {
			Glossario voce = (Glossario) this.entityManager
					.createQuery("from Glossario where name = :name")
					.setParameter("name", name).getSingleResult();
			vocabolo = voce.getVocabolo();
		} catch (NoResultException e) {
			vocabolo = "Da inserire";
		}
		return vocabolo;
	}

	public String getVocaboloDef(String name) {
		String definizione = "";
		try {
			Glossario voce = (Glossario) this.entityManager
					.createQuery("from Glossario where name = :name")
					.setParameter("name", name).getSingleResult();
			definizione = voce.getDefinizione();
		} catch (NoResultException e) {
		}
		return definizione;
	}

	public boolean isDatiOk() {
		return this.datiOk;
	}

	public void setAlertMessages(List<String> alertMessages) {
		this.alertMessages = alertMessages;
	}

	// Step Two

	public void setAnno0(Integer anno0) {
		this.anno0 = anno0;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public void setCostoMateriePrime(BigDecimal costoMateriePrime) {
		this.costoMateriePrime = costoMateriePrime;
	}

	public void setDatiOk(boolean datiOk) {
		this.datiOk = datiOk;
	}

	public void setDomandaMercato(DomandaMercato domandaMercato) {
		this.domandaMercato = domandaMercato;
	}

	public void setInnovazioneDiProdotto(Double innovazioneDiProdotto) {
		this.innovazioneDiProdotto = innovazioneDiProdotto;
	}

	public void setInnovazioneProcessoSpinner(Integer innovazioneProcessoSpinner) {
		this.innovazioneProcessoSpinner = innovazioneProcessoSpinner;
	}

	public void setInnovazioneProdottoSpinner(Integer innovazioneProdottoSpinner) {
		this.innovazioneProdottoSpinner = innovazioneProdottoSpinner;
	}

	public void setPercentualeCostiProduzioneTerritorio(
			Integer percentualeCostiProduzioneTerritorio) {
		this.percentualeCostiProduzioneTerritorio = percentualeCostiProduzioneTerritorio;
	}

	public void setPercentualeIndottoCongiunturaleTerritorio(
			Integer percentualeIndottoCongiunturaleTerritorio) {
		this.percentualeIndottoCongiunturaleTerritorio = percentualeIndottoCongiunturaleTerritorio;
	}

	public void setReputazioneSpinner(Integer reputazioneSpinner) {
		this.reputazioneSpinner = reputazioneSpinner;
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

	public void setVisualized(
			HashMap<String, ArrayList<ArrayList<Integer>>> visualized) {
		this.visualized = visualized;
	}

	public void startSimulation() {
		System.out.println("Avvio la simulazione");
		System.out.println(this.datiBilancioHome.getInstance().getId());

		MiICSiThinkModel micsModel = new MiICSiThinkModel(this.entityManager,
				this, new Double(
						this.getPercentualeIndottoCongiunturaleTerritorio()));
		this.risultati = micsModel.execute(this.datiBilancioHome.getInstance(),
				this.domandaMercato);
		this.labels.put("Incremento da Innovazione",
				"getIncrementoDaInnovazione");
		this.labels.put("Incremento da Reputazione",
				"getIncrementoDareputazione");
		this.labels.put("Producivity", "getProductivity");
		this.labels.put("Quota volumi aziendali", "getQuotaVolumiAziendali");
		this.labels.put("Volumi di Mercato", "getVolumiMercato");
		this.labels.put("Domanda Lavoro", "getDomandaLavoro");
		this.labels.put("Esuberi", "getEsuberi");
		this.labels.put("Lavoratori in Forza", "getLavoratoriInForza");
		for (String label : this.labels.keySet()) {
			this.visualized.put(label,
					this.getRisultatiByName(this.labels.get(label)));

		}

	}
}
