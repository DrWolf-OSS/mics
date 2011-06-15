package it.drwolf.mics.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domanda_mercato")
public class DomandaMercato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5483289304883923896L;

	private Integer id;

	private BigDecimal volumeMercatoAnno0;

	private BigDecimal volumeMercatoAnno1;

	private BigDecimal volumeMercatoAnno2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	@Column(name = "volume_mercato_anno_0", nullable = false)
	public BigDecimal getVolumeMercatoAnno0() {
		return this.volumeMercatoAnno0;
	}

	@Column(name = "volume_mercato_anno_1", nullable = false)
	public BigDecimal getVolumeMercatoAnno1() {
		return this.volumeMercatoAnno1;
	}

	@Column(name = "volume_mercato_anno_2", nullable = false)
	public BigDecimal getVolumeMercatoAnno2() {
		return this.volumeMercatoAnno2;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVolumeMercatoAnno0(BigDecimal volumeMercatoAnno0) {
		this.volumeMercatoAnno0 = volumeMercatoAnno0;
	}

	public void setVolumeMercatoAnno1(BigDecimal volumeMercatoAnno1) {
		this.volumeMercatoAnno1 = volumeMercatoAnno1;
	}

	public void setVolumeMercatoAnno2(BigDecimal volumeMercatoAnno2) {
		this.volumeMercatoAnno2 = volumeMercatoAnno2;
	}

}
