package it.drwolf.mics.util;

public enum StepTwoFields {

	ROE("ROE"), RIS("Rendimento in investimenti sicuri"), DBF(
			"Debiti v/banche su fatturato"), ROI("ROI"), CLP(
			"Costo del lavoro pro-capite"), CMC(
			"Costo del lavoro, Media del comparto"), EBITDA("EBITDA");

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
