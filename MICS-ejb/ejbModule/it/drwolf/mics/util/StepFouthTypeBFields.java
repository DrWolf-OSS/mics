package it.drwolf.mics.util;

public enum StepFouthTypeBFields {

	quantitaProdottiStandard("uantitˆ di prodotti standard vendute"), fatturato(
			"Fatturato"), valoreProduzione("Valore della produzione"), quantitaProdotta(
			"Quantitˆ prodotta"), numeroDipendenti("Numero dipendenti"), fatturatoComplessivoSettore(
			"Fatturato complessivo del comparto/settore");

	private String testo;

	private StepFouthTypeBFields(String testo) {
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public String getTesto() {
		return this.testo;
	}
}
