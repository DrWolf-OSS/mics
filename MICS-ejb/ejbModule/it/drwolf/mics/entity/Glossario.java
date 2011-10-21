package it.drwolf.mics.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name = "glossario")
public class Glossario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2196806255856573576L;

	private String name;

	private String vocabolo;

	private String definizione;

	private String unitaDiMisura;

	@Column(name = "definizione", nullable = false)
	@Length(max = 4000)
	public String getDefinizione() {
		return this.definizione;
	}

	@Id
	public String getName() {
		return this.name;
	}

	public String getUnitaDiMisura() {
		return this.unitaDiMisura;
	}

	@Column(name = "vocabolo", nullable = false)
	public String getVocabolo() {
		return this.vocabolo;
	}

	public void setDefinizione(String definizione) {
		this.definizione = definizione;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUnitaDiMisura(String unitaDiMisura) {
		this.unitaDiMisura = unitaDiMisura;
	}

	public void setVocabolo(String vocabolo) {
		this.vocabolo = vocabolo;
	}

}
