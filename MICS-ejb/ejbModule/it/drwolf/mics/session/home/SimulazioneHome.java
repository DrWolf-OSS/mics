package it.drwolf.mics.session.home;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import it.drwolf.mics.entity.Azienda;
import it.drwolf.mics.entity.DatiBilancio;
import it.drwolf.mics.entity.DomandaMercato;
import it.drwolf.mics.entity.OutputSimulazione;
import it.drwolf.mics.entity.OutputSimulazioneId;
import it.drwolf.mics.entity.Simulazione;
import it.drwolf.mics.entity.TrimestreSimulazione;

@Name("simulazioneHome")
public class SimulazioneHome extends EntityHome<Simulazione> {

	@In(create = true)
	AziendaHome aziendaHome;
	@In(create = true)
	DomandaMercatoHome domandaMercatoHome;

	private ArrayList<OutputSimulazione> risultati = new ArrayList<OutputSimulazione>();

	private HashMap<String, ArrayList<ArrayList<Integer>>> visualized = new HashMap<String, ArrayList<ArrayList<Integer>>>();

	private HashMap<String, String> labels = new HashMap<String, String>();

	@Override
	protected Simulazione createInstance() {
		Simulazione simulazione = new Simulazione();
		return simulazione;
	}

	public List<DatiBilancio> getDatiBilanci() {
		return this.getInstance() == null ? null : new ArrayList<DatiBilancio>(this.getInstance().getDatiBilanci());
	}

	public Simulazione getDefinedInstance() {
		return this.isIdDefined() ? this.getInstance() : null;
	}

	public ArrayList<Entry<String, ArrayList<ArrayList<Integer>>>> getEntries() {
		return new ArrayList<Entry<String, ArrayList<ArrayList<Integer>>>>(this.visualized.entrySet());
	}

	public HashMap<String, String> getLabels() {
		return this.labels;
	}

	public ArrayList<OutputSimulazione> getRisultati() {
		if (this.risultati.size() == 0) {
			this.loadRisultati();
		}
		return this.risultati;
	}

	public ArrayList<ArrayList<Integer>> getRisultatiByName(String name) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (this.risultati.size() > 0) {
			for (int index = 3; index < this.risultati.size(); index++) {
				OutputSimulazione os = this.risultati.get(index);
				try {
					ArrayList<Integer> trimestre = new ArrayList<Integer>();
					trimestre.add(os.getTrimestreSimulazione().getTrimestre());
					Object res = os.getClass().getMethod(name, null).invoke(os, null);
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
		}
		return result;
	}

	public Integer getSimulazioneId() {
		this.loadRisultati();
		return (Integer) this.getId();
	}

	public HashMap<String, ArrayList<ArrayList<Integer>>> getVisualized() {
		return this.visualized;
	}

	public boolean isWired() {
		if (this.getInstance().getAzienda() == null) {
			return false;
		}
		if (this.getInstance().getDomandaMercato() == null) {
			return false;
		}
		return true;
	}

	public void load() {
		if (this.isIdDefined()) {
			this.wire();
		}
	}

	private void loadRisultati() {
		List<TrimestreSimulazione> trimestri = this.getEntityManager().createQuery("from TrimestreSimulazione")
				.getResultList();
		for (TrimestreSimulazione trimestreSimulazione : trimestri) {
			OutputSimulazioneId id = new OutputSimulazioneId(this.getInstance().getId(),
					trimestreSimulazione.getTrimestre());
			OutputSimulazione o = this.getEntityManager().find(OutputSimulazione.class, id);
			if (o != null) {
				this.risultati.add(o);
			}

		}

		this.labels.put("Incremento da Innovazione", "getIncrementoDaInnovazione");
		this.labels.put("Incremento da Reputazione", "getIncrementoDareputazione");
		this.labels.put("Producivity", "getProductivity");
		this.labels.put("Quota volumi aziendali", "getQuotaVolumiAziendali");
		this.labels.put("Volumi di Mercato", "getVolumiMercato");
		this.labels.put("Domanda Lavoro", "getDomandaLavoro");
		this.labels.put("Esuberi", "getEsuberi");
		this.labels.put("Lavoratori in Forza", "getLavoratoriInForza");
		for (String label : this.labels.keySet()) {
			this.visualized.put(label, this.getRisultatiByName(this.labels.get(label)));

		}
	}

	public void setLabels(HashMap<String, String> labels) {
		this.labels = labels;
	}

	public void setRisultati(ArrayList<OutputSimulazione> risultati) {
		this.risultati = risultati;
	}

	public void setSimulazioneId(Integer id) {
		this.setId(id);
	}

	public void setVisualized(HashMap<String, ArrayList<ArrayList<Integer>>> visualized) {
		this.visualized = visualized;
	}

	public void wire() {
		this.getInstance();
		Azienda azienda = this.aziendaHome.getDefinedInstance();
		if (azienda != null) {
			this.getInstance().setAzienda(azienda);
		}
		DomandaMercato domandaMercato = this.domandaMercatoHome.getDefinedInstance();
		if (domandaMercato != null) {
			this.getInstance().setDomandaMercato(domandaMercato);
		}
	}

}
