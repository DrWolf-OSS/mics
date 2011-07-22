package it.drwolf.mics.util;

public enum _StepThirdFields {

	quotaAmmortamentoBeniMateriali("Quota ammortamento beni materiali"), costiProdGodimentoBeniTerzi(
			"Costi di prod. per godimento beni di terzi"), quotaAmmortamentoBeniImmateriali(
			"Quota ammortamento beni immateriali");

	private String testo;

	private _StepThirdFields(String testo) {
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public String getTesto() {
		return this.testo;
	}
}
