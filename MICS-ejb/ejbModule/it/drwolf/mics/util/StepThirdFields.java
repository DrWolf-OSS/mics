package it.drwolf.mics.util;

public enum StepThirdFields {

	quotaAmmortamentoBeniMateriali("Quota ammortamento beni materiali"), costiProdGodimentoBeniTerzi(
			"Costi di prod. per godimento beni di terzi"), quotaAmmortamentoBeniImmateriali(
			"Quota ammortamento beni immateriali"), avviamento("Avviamento"), quotaAmmortamentoAvviamento(
			"Quota di ammortamento Avviamento");

	private String testo;

	private StepThirdFields(String testo) {
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public String getTesto() {
		return this.testo;
	}
}
