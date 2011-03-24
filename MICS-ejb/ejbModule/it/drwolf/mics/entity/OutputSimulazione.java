package it.drwolf.mics.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

@Entity
@Table(name = "output_simulazione")
public class OutputSimulazione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -184344912152350758L;

	private OutputSimulazioneId id;

	private Simulazione simulazione;

	private TrimestreSimulazione TrimestreSimulazione;

	// Indicatori Produzione Aziendale

	private Double incrementoDareputazione;

	private Double incrementoDaInnovazione;

	private BigDecimal quotaVolumiAziendali;

	private BigDecimal volumiMercato;

	private BigDecimal productivity;

	// Indicatori occupazionali

	private Integer domandaLavoro;

	private Integer esuberi;

	// Indotto

	private BigDecimal indottoCongiunturale;

	private BigDecimal indottoStrutturale;

	@Column(name = "domanda_lavoro", nullable = false)
	public Integer getDomandaLavoro() {
		return this.domandaLavoro;
	}

	@Column(name = "esuberi", nullable = false)
	public Integer getEsuberi() {
		return this.esuberi;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "simulazioneId", column = @Column(name = "id_simulazione", nullable = false)),
			@AttributeOverride(name = "trimestreSimulazione", column = @Column(name = "trimestre", nullable = false)) })
	@NotNull
	public OutputSimulazioneId getId() {
		return this.id;
	}

	@Column(name = "incremento_da_innovazione", nullable = false)
	public Double getIncrementoDaInnovazione() {
		return this.incrementoDaInnovazione;
	}

	@Column(name = "incremento_da_reputazione", nullable = false)
	public Double getIncrementoDareputazione() {
		return this.incrementoDareputazione;
	}

	@Column(name = "indotto_congiunturale", nullable = false)
	public BigDecimal getIndottoCongiunturale() {
		return this.indottoCongiunturale;
	}

	@Column(name = "indotto_strutturale", nullable = false)
	public BigDecimal getIndottoStrutturale() {
		return this.indottoStrutturale;
	}

	@Column(name = "productivity", nullable = false)
	public BigDecimal getProductivity() {
		return this.productivity;
	}

	@Column(name = "quota_volumi_aziendali", nullable = false)
	public BigDecimal getQuotaVolumiAziendali() {
		return this.quotaVolumiAziendali;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_simulazione", nullable = false, insertable = false, updatable = false)
	@NotNull
	public Simulazione getSimulazione() {
		return this.simulazione;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trimestre", nullable = false, insertable = false, updatable = false)
	@NotNull
	public TrimestreSimulazione getTrimestreSimulazione() {
		return this.TrimestreSimulazione;
	}

	@Column(name = "volumi_mercato", nullable = false)
	public BigDecimal getVolumiMercato() {
		return this.volumiMercato;
	}

	public void setDomandaLavoro(Integer domandaLavoro) {
		this.domandaLavoro = domandaLavoro;
	}

	public void setEsuberi(Integer esuberi) {
		this.esuberi = esuberi;
	}

	public void setId(OutputSimulazioneId id) {
		this.id = id;
	}

	public void setIncrementoDaInnovazione(Double incrementoDaInnovazione) {
		this.incrementoDaInnovazione = incrementoDaInnovazione;
	}

	public void setIncrementoDareputazione(Double incrementoDareputazione) {
		this.incrementoDareputazione = incrementoDareputazione;
	}

	public void setIndottoCongiunturale(BigDecimal indottoCongiunturale) {
		this.indottoCongiunturale = indottoCongiunturale;
	}

	public void setIndottoStrutturale(BigDecimal indottoStrutturale) {
		this.indottoStrutturale = indottoStrutturale;
	}

	public void setProductivity(BigDecimal productivity) {
		this.productivity = productivity;
	}

	public void setQuotaVolumiAziendali(BigDecimal quotaVolumiAziendali) {
		this.quotaVolumiAziendali = quotaVolumiAziendali;
	}

	public void setSimulazione(Simulazione simulazione) {
		this.simulazione = simulazione;
	}

	public void setTrimestreSimulazione(
			TrimestreSimulazione trimestreSimulazione) {
		this.TrimestreSimulazione = trimestreSimulazione;
	}

	public void setVolumiMercato(BigDecimal volumiMercato) {
		this.volumiMercato = volumiMercato;
	}

}
