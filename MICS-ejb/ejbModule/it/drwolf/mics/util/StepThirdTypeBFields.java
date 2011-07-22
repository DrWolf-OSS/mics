package it.drwolf.mics.util;

public enum StepThirdTypeBFields {

	quantitaProdottiStandard("uantit� di prodotti standard vendute"), fatturato(
			"Fatturato"), valoreProduzione("Valore della produzione"), quantitaProdotta(
			"Quantit� prodotta"), numeroDipendenti("Numero dipendenti"), fatturatoComplessivoSettore(
			"Fatturato complessivo del comparto/settore");

	private String testo;

	private StepThirdTypeBFields(String testo) {
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public String getTesto() {
		return this.testo;
	}
}
