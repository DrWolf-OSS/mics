package it.drwolf.mics.util;

public enum Settori {

	CAMPERISTICA("Camperistica"),

	MECCANOCARTARIO("Meccano cartario");

	private String testo;

	private Settori(String testo) {
		this.testo = testo;
	}

	public String getNome() {
		return this.name();
	}

	public String getTesto() {
		return this.testo;
	}

}
