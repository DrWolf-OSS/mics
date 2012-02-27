package it.drwolf.mics.util;

public enum StepTwoFields {

	roe("ROE", false), rendimentoInvestimentiSicuri(
			"Rendimento in investimenti sicuri", false), debitiBancheSuFatturato(
			"Debiti v/banche su fatturato", false), roi("ROI", false), costoLavoroProCapite(
			"Costo del lavoro pro-capite", false), costoLavoroMedioComparto(
			"Costo del lavoro, Media del comparto", false), ebitda("EBITDA",
			false), quotaAmmortamentoBeniMateriali(
			"Quota ammortamento beni materiali", false), costiProdGodimentoBeniTerzi(
			"Costi di prod. per godimento beni di terzi", false), quotaAmmortamentoBeniImmateriali(
			"Quota ammortamento beni immateriali", false);

	private String testo;
	private boolean required;

	private StepTwoFields(String testo, boolean required) {
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
