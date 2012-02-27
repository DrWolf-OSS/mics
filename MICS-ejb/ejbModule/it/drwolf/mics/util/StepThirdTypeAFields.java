package it.drwolf.mics.util;

public enum StepThirdTypeAFields {

	fatturato("Fatturato", true), costoMateriePrime(
			"Costo delle materie prime e di consumo", true), numeroDipendenti(
			"Numero dipendenti", true), quantitaVendute("Quantità vendute",
			true);

	private String testo;
	private boolean required;

	private StepThirdTypeAFields(String testo, boolean required) {
		this.required = required;
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public boolean getRequired() {
		return this.required;
	}

	public String getTesto() {
		return this.testo;
	}

}
