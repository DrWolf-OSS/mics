package it.drwolf.mics.util;

public enum StepThirdTypeBFields {

	fatturato("Fatturato", true), numeroDipendenti("Numero dipendenti", true), fatturatoComplessivoSettore(
			"Fatturato complessivo del comparto/settore", true);

	private String testo;
	private boolean required;

	private StepThirdTypeBFields(String testo, boolean required) {
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
