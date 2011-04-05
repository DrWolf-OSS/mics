package it.drwolf.mics.util;

public enum StepFourthTypeAFields {

	valoreProduzione("Valore della produzione"), costiProduzioneServizi(
			"Costi di produzione per servizi"), costoPersonale(
			"Costo del personale"), costoMateriePrime(
			"Costo delle materie prime e di consumo"), numeroDipendenti(
			"Numero dipendenti"), quantitaVendute("Quantitˆ vendute"), produzioneTotaleMercatoRiferimento(
			"Produzione totale del mercato di riferimento");

	private String testo;

	private StepFourthTypeAFields(String testo) {
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public String getTesto() {
		return this.testo;
	}

}
