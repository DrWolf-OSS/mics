package it.drwolf.mics.util;

public enum StepThirdTypeAFields {

	valoreProduzione("Valore della produzione"), costiProduzioneServizi(
			"Costi di produzione per servizi"), costoPersonale(
			"Costo del personale"), costoMateriePrime(
			"Costo delle materie prime e di consumo"), numeroDipendenti(
			"Numero dipendenti"), quantitaVendute("Quantit� vendute");

	private String testo;

	private StepThirdTypeAFields(String testo) {
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public String getTesto() {
		return this.testo;
	}

}
