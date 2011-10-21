package it.drwolf.mics.util;

public enum StepThirdTypeAFields {

	fatturato("Fatturato"), costoMateriePrime(
			"Costo delle materie prime e di consumo"), numeroDipendenti(
			"Numero dipendenti"), quantitaVendute("Quantità vendute");

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
