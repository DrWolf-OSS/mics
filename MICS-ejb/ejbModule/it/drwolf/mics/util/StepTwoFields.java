package it.drwolf.mics.util;

public enum StepTwoFields {

	roe("ROE"), rendimentoInvestimentiSicuri(
			"Rendimento in investimenti sicuri"), debitiBancheSuFatturato(
			"Debiti v/banche su fatturato"), roi("ROI"), costoLavoroProCapite(
			"Costo del lavoro pro-capite"), costoLavoroMedioComparto(
			"Costo del lavoro, Media del comparto"), ebitda("EBITDA");

	private String testo;

	private StepTwoFields(String testo) {
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public String getTesto() {
		return this.testo;
	}

}
