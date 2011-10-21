package it.drwolf.mics.model;

import it.drwolf.mics.entity.DatiBilancio;
import it.drwolf.mics.entity.DomandaMercato;
import it.drwolf.mics.entity.OutputSimulazione;
import it.drwolf.mics.entity.TrimestreSimulazione;
import it.drwolf.mics.util.SimulationBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("micsiThinkModel")
@Scope(ScopeType.CONVERSATION)
public class MiICSiThinkModel {

	SimulationBean simulationBean;

	EntityManager entityManager;

	double[] delayDomandaLavoro = { 0, 0, 0 };

	double[] delayEsuberi = { 0, 0 };

	private Double percentualeCostiProduzioneTerritorio;

	public MiICSiThinkModel(EntityManager entityManager,
			SimulationBean simulationBean,
			Double percentualeCostiProduzioneTerritorio) {
		this.entityManager = entityManager;
		this.simulationBean = simulationBean;
		this.percentualeCostiProduzioneTerritorio = percentualeCostiProduzioneTerritorio;
	}

	private boolean delayDL() {
		if ((this.delayDomandaLavoro[0] > 0)
				&& (this.delayDomandaLavoro[1] > 0)
				&& (this.delayDomandaLavoro[2] > 0)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean delayE() {
		if ((this.delayEsuberi[0] > 0) && (this.delayEsuberi[1] > 0)) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<OutputSimulazione> execute(DatiBilancio db,
			DomandaMercato dm) {
		BigDecimal quotaVolumeAziendale1 = new BigDecimal(0);

		BigDecimal quotaVolumeAziendale2 = new BigDecimal(0);

		BigDecimal inputMercatoAnnuali = new BigDecimal(0);

		BigDecimal outputMercatoAnnuale = new BigDecimal(0);

		BigDecimal effettiFluttuazioneMercato = new BigDecimal(0);

		BigDecimal quotaVolumeAziendaleIniziale = null;
		if (this.simulationBean.getSolutionType().equals("A")) {
			quotaVolumeAziendaleIniziale = new BigDecimal(
					db.getQuantitaVendute());
		} else if (this.simulationBean.getSolutionType().equals("B")) {
			quotaVolumeAziendaleIniziale = db.getFatturato();
		}
		BigDecimal definisceSegnoEffettiIdp = new BigDecimal(1);

		BigDecimal effettiIdp = new BigDecimal(1);

		BigDecimal correzioneVolumiAziendali = new BigDecimal(0);

		Integer lavoratoriInForza = db.getNumeroDipendenti();

		Integer lnaqva = new Integer(0);

		Double unitaLavoro = new Double(0);

		Double rubinettoNuoveUnitaLavorative = new Double(0);

		Double domandaLavoro = new Double(0);

		Double esuberoLavoratori = new Double(0);

		Double domandaLavoroRitardata = new Double(0);

		Double rubinettoEsuberi = new Double(0);

		Double esuberi = new Double(0);

		Double esuberiRitardati = new Double(0);

		BigDecimal contenitoreVolumiAziendali = new BigDecimal(0);

		BigDecimal contenitoreVolumiDiMercato = new BigDecimal(0);

		Double innovazioneDiProdottoM = new Double(0.3);

		Double reputazioneM = new Double(0.4);

		Double reputazione = new Double(0.2);

		Double innovazioneDiProdotto = new Double(0.1);

		Double incrementoDaInnovazioneProdoto = new Double(0);

		Double incrementoDaReputazione = new Double(0);

		Double incrementoTotale = new Double(0);

		Double quotaVolumiAziendali = new Double(0);

		Double variazioneQuotaVolumiAziendali = new Double(0);

		Double volumiDiMercato = new Double(0);

		// Nel caso A:

		// Nel caso B:
		Double productivityIniziale = new Double(11);

		Double productivity = new Double(0);

		Double productivityPassoPrecedente = new Double(0);

		Double innovazioneDiProcesso = new Double(1);

		BigDecimal acquistiTotali = new BigDecimal(0);

		BigDecimal indottoCongiunturale = new BigDecimal(0);

		BigDecimal indottoStrutturale = new BigDecimal(0);

		BigDecimal indottoTerritoriale = new BigDecimal(0);

		BigDecimal fornitureEsterne = new BigDecimal(0);

		double[] sensibiltaInnovazioneProdotto = { 0, 0.001, 0.02, 0.08, 0.16,
				0.3, 0.5, 0.7, 0.86, 0.94, 0.98, 0.998 };

		double[] sensibiltaReputazione = { 0, 0.001, 0.02, 0.08, 0.16, 0.3,
				0.5, 0.7, 0.86, 0.94, 0.98, 0.998 };

		quotaVolumeAziendale1 = quotaVolumeAziendaleIniziale
				.add(quotaVolumeAziendaleIniziale.multiply(dm
						.getVolumeMercatoAnno1()
						.subtract(dm.getVolumeMercatoAnno0())
						.divide(dm.getVolumeMercatoAnno0(), 5,
								RoundingMode.FLOOR)));

		quotaVolumeAziendale2 = quotaVolumeAziendale1.add(quotaVolumeAziendale1
				.multiply(dm
						.getVolumeMercatoAnno2()
						.subtract(dm.getVolumeMercatoAnno1())
						.divide(dm.getVolumeMercatoAnno1(), 5,
								RoundingMode.FLOOR)));

		List<TrimestreSimulazione> listaTrimestri = this.loadTrimestri();
		ArrayList<OutputSimulazione> result = new ArrayList<OutputSimulazione>();

		// considero una unità come un trimestre, quindi 3 anni = 12 trimestri
		for (int time = 0; time < 12; time++) {
			OutputSimulazione outputSimulazione = new OutputSimulazione();
			outputSimulazione.setTrimestreSimulazione(listaTrimestri.get(time));

			productivityPassoPrecedente = productivity;
			// Incremento Da Innovazione di Prodotto
			incrementoDaInnovazioneProdoto = contenitoreVolumiAziendali
					.doubleValue()
					* (innovazioneDiProdotto - innovazioneDiProdottoM)
					* (sensibiltaInnovazioneProdotto[time]) * 0.6;

			// Incremento Da Reputaizone
			incrementoDaReputazione = 0.4
					* contenitoreVolumiAziendali.doubleValue()
					* (reputazione - reputazioneM)
					* sensibiltaReputazione[time];

			// Incremento Totale
			incrementoTotale = incrementoDaInnovazioneProdoto
					+ incrementoDaReputazione;

			// Volumi di Mercato
			volumiDiMercato = contenitoreVolumiDiMercato.doubleValue()
					+ contenitoreVolumiAziendali.doubleValue()
					- quotaVolumiAziendali;

			// Quota Volumi Aziendali
			// memorizzo il valore dello step prima
			variazioneQuotaVolumiAziendali = quotaVolumiAziendali;
			quotaVolumiAziendali = contenitoreVolumiAziendali.doubleValue()
					+ incrementoTotale;

			// faccio la differenza tra quello allo step attuale e quello allo
			// step prima
			variazioneQuotaVolumiAziendali = quotaVolumiAziendali
					- variazioneQuotaVolumiAziendali;

			// Unità Lavoro
			if (productivity == 0) {
				unitaLavoro = 0.0;
			} else {
				unitaLavoro = (variazioneQuotaVolumiAziendali / productivity);
			}
			if (time > 4) {
				rubinettoNuoveUnitaLavorative = unitaLavoro;
			}

			// Esubero Lavoratori
			esuberoLavoratori = Math.abs(unitaLavoro);

			// Domanda Lavoro ritardata
			if (this.delayDL()) {
				domandaLavoroRitardata = domandaLavoro;
			}

			// Rubinetto esuberi
			if (unitaLavoro < 0) {
				rubinettoEsuberi = esuberoLavoratori;
			}

			// Esuberi ritardati
			if (this.delayE()) {
				esuberiRitardati = esuberi;
			}

			// Lavoratori in forza
			if ((db.getNumeroDipendenti() - esuberiRitardati.intValue() + domandaLavoroRitardata
					.intValue()) < 0) {
				lavoratoriInForza = 0;
			} else if (quotaVolumiAziendali == 0) {
				lavoratoriInForza = 0;
			} else {
				lavoratoriInForza = db.getNumeroDipendenti()
						- esuberiRitardati.intValue()
						+ domandaLavoroRitardata.intValue();
			}

			// Productivity
			// fattore moltiplicativos
			Double fm = new Double(0);
			if (innovazioneDiProcesso == 1) {
				fm = 1.125;
			} else if (innovazioneDiProcesso == 2) {
				fm = 1.25;
			} else if (innovazioneDiProcesso == 3) {
				fm = 1.375;
			} else if (innovazioneDiProcesso == 4) {
				fm = 1.5;
			} else {
				fm = 1.0;
			}
			if (time < 4) {
				productivity = fm * productivityIniziale;
			} else if (lavoratoriInForza == 0) {
				productivity = 0.0;
			} else {
				productivity = quotaVolumiAziendali / lavoratoriInForza;
			}

			if ((quotaVolumiAziendali - quotaVolumeAziendaleIniziale
					.doubleValue()) > 0) {
				acquistiTotali = this.simulationBean
						.getCostoMateriePrime()
						.multiply(
								new BigDecimal(1).add((new BigDecimal(
										quotaVolumiAziendali)
										.subtract(quotaVolumeAziendaleIniziale))
										.divide(quotaVolumeAziendaleIniziale)));
			} else {
				acquistiTotali = this.simulationBean
						.getCostoMateriePrime()
						.multiply(
								new BigDecimal(1).subtract(((new BigDecimal(
										quotaVolumiAziendali)
										.subtract(quotaVolumeAziendaleIniziale))
										.divide(quotaVolumeAziendaleIniziale,
												5, RoundingMode.FLOOR)).abs()));
			}

			if (productivityIniziale == 0) {
				lnaqva = 0;
			} else {
				lnaqva = (int) (quotaVolumiAziendali / productivityPassoPrecedente);
			}
			if ((lnaqva - lavoratoriInForza) > 0) {
				indottoCongiunturale = new BigDecimal(productivity
						* (lnaqva - lavoratoriInForza)
						* db.getFatturato().doubleValue());
			}

			indottoStrutturale = acquistiTotali.subtract(indottoCongiunturale);

			indottoTerritoriale = acquistiTotali.multiply(new BigDecimal(
					this.percentualeCostiProduzioneTerritorio / 100));

			fornitureEsterne = acquistiTotali.subtract(indottoTerritoriale);

			outputSimulazione.setProductivity(productivity);

			outputSimulazione
					.setIncrementoDaInnovazione(incrementoDaInnovazioneProdoto);
			outputSimulazione
					.setIncrementoDareputazione(incrementoDaReputazione);
			outputSimulazione.setQuotaVolumiAziendali(new BigDecimal(
					quotaVolumiAziendali));
			outputSimulazione.setVolumiMercato(new BigDecimal(volumiDiMercato));

			outputSimulazione.setDomandaLavoro(domandaLavoro.intValue());

			outputSimulazione.setLavoratoriInForza(lavoratoriInForza);

			outputSimulazione.setDomandaLavoro(domandaLavoroRitardata
					.intValue());
			if ((esuberiRitardati > db.getNumeroDipendenti())
					|| (quotaVolumiAziendali == 0)) {
				outputSimulazione.setEsuberi(0);
			} else {
				outputSimulazione.setEsuberi(esuberiRitardati.intValue());
			}

			outputSimulazione.setAcquistiTotali(acquistiTotali);

			outputSimulazione.setIndottoCongiunturale(indottoCongiunturale);

			outputSimulazione.setIndottoStrutturale(indottoStrutturale);

			outputSimulazione.setIndottoTerritoriale(indottoTerritoriale);

			outputSimulazione.setFornitureEsterne(fornitureEsterne);

			result.add(outputSimulazione);

			if ((time >= 0) && (time < 4)) {
				inputMercatoAnnuali = dm.getVolumeMercatoAnno0().subtract(
						quotaVolumeAziendaleIniziale);
				effettiFluttuazioneMercato = quotaVolumeAziendale1;
				if (effettiFluttuazioneMercato.compareTo(new BigDecimal(1)) < 0) {
					definisceSegnoEffettiIdp = new BigDecimal(-1);
				}
				correzioneVolumiAziendali = effettiIdp
						.multiply(effettiFluttuazioneMercato);
			} else if ((time >= 4) && (time < 8)) {
				inputMercatoAnnuali = dm.getVolumeMercatoAnno1().subtract(
						quotaVolumeAziendale1);
				outputMercatoAnnuale = dm.getVolumeMercatoAnno0().subtract(
						quotaVolumeAziendaleIniziale);
				effettiFluttuazioneMercato = quotaVolumeAziendaleIniziale
						.multiply(dm
								.getVolumeMercatoAnno1()
								.subtract(dm.getVolumeMercatoAnno0())
								.divide(dm.getVolumeMercatoAnno0(), 5,
										RoundingMode.FLOOR));
				if (effettiFluttuazioneMercato.compareTo(new BigDecimal(1)) < 0) {
					definisceSegnoEffettiIdp = new BigDecimal(-1);
				}
				// qui devo valutare se entra in gioco l'innovazione di processo
				correzioneVolumiAziendali = effettiIdp
						.multiply(effettiFluttuazioneMercato);
			} else {
				inputMercatoAnnuali = dm.getVolumeMercatoAnno2().subtract(
						quotaVolumeAziendale2);
				outputMercatoAnnuale = dm.getVolumeMercatoAnno1().subtract(
						quotaVolumeAziendale1);

				effettiFluttuazioneMercato = quotaVolumeAziendaleIniziale
						.multiply(new BigDecimal(1).add(
								(dm.getVolumeMercatoAnno1().subtract(dm
										.getVolumeMercatoAnno0())).divide(
										dm.getVolumeMercatoAnno0(), 5,
										RoundingMode.FLOOR)).multiply(
								(dm.getVolumeMercatoAnno2().subtract(dm
										.getVolumeMercatoAnno1())).divide(
										dm.getVolumeMercatoAnno1(), 5,
										RoundingMode.FLOOR)));
				if (effettiFluttuazioneMercato.compareTo(new BigDecimal(1)) < 0) {
					definisceSegnoEffettiIdp = new BigDecimal(-1);
				}
				// qui devo valutare se entra in gioco l'innovazione di processo
				correzioneVolumiAziendali = effettiIdp
						.multiply(effettiFluttuazioneMercato);
			}
			contenitoreVolumiAziendali = contenitoreVolumiAziendali
					.add(correzioneVolumiAziendali
							.multiply(new BigDecimal(0.25)));
			contenitoreVolumiDiMercato = contenitoreVolumiDiMercato
					.add((inputMercatoAnnuali.subtract(outputMercatoAnnuale))
							.multiply(new BigDecimal(0.25)));
			domandaLavoro = domandaLavoro + rubinettoNuoveUnitaLavorative
					* 0.25;
			// quest è da verificare.... se domanda lavoro non può essere
			// negativo
			if (domandaLavoro < 0) {
				domandaLavoro = 0.0;
			}

			esuberi = esuberi + rubinettoEsuberi * 0.25;

			this.putInDelayDomandaLavoro(domandaLavoro);

			this.putInDelayEsuberi(esuberi);

		}

		return result;
	}

	private List<TrimestreSimulazione> loadTrimestri() {
		ArrayList<TrimestreSimulazione> listaTrimestri = new ArrayList<TrimestreSimulazione>();
		return listaTrimestri = (ArrayList<TrimestreSimulazione>) this.entityManager
				.createQuery("from TrimestreSimulazione order by trimestre")
				.getResultList();
	}

	private void putInDelayDomandaLavoro(Double domandaLavoro) {
		// faccio scorrere i valori
		this.delayDomandaLavoro[0] = this.delayDomandaLavoro[1];
		this.delayDomandaLavoro[1] = this.delayDomandaLavoro[2];
		this.delayDomandaLavoro[2] = domandaLavoro;
	}

	private void putInDelayEsuberi(Double esuberi) {
		this.delayEsuberi[0] = this.delayEsuberi[1];
		this.delayEsuberi[1] = esuberi;
	}
}
