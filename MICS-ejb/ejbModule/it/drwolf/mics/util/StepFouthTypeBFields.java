package it.drwolf.mics.util;

public enum StepFouthTypeBFields {

	quantitaProdottiStandard("uantit� di prodotti standard vendute"), fatturato(
			"Fatturato"), valoreProduzione("Valore della produzione"), quantitaProdotta(
			"Quantit� prodotta"), numeroDipendenti("Numero dipendenti"), fatturatoComplessivoSettore(
			"Fatturato complessivo del comparto/settore"), valoreProduzioneTotaleMercatoRiferimento(
			"Valore della produzione totale del mercato di riferimento");

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