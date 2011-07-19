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

	private Double productivity;

	// Indicatori occupazionali

	private Integer domandaLavoro;

	private Integer esuberi;

	private Integer lavoratoriInForza;

	// Indotto

	private BigDecimal indottoCongiunturale;

	private BigDecimal indottoStrutturale;

	private BigDecimal acquistiTotali;

	private BigDecimal indottoTerritoriale;

	private BigDecimal fornitureEsterne;

	@Column(name = "acquisti_totali", nullable = false)
	public BigDecimal getAcquistiTotali() {
		return this.acquistiTotali;
	}

	@Column(name = "domanda_lavoro", nullable = false)
	public Integer getDomandaLavoro() {
		return this.domandaLavoro;
	}

	@Column(name = "esuberi", nullable = false)
	public Integer getEsuberi() {
		return this.esuberi;
	}

	@Column(name = "forniture_esterne", nullable = false)
	public BigDecimal getFornitureEsterne() {
		return this.fornitureEsterne;
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

	@Column(name = "indotto_territoriale", nullable = false)
	public BigDecimal getIndottoTerritoriale() {
		return this.indottoTerritoriale;
	}

	@Column(name = "lavoratori_in_forza", nullable = false)
	public Integer getLavoratoriInForza() {
		return this.lavoratoriInForza;
	}

	@Column(name = "productivity", nullable = false)
	public Double getProductivity() {
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

	public void setAcquistiTotali(BigDecimal acquistiTotali) {
		this.acquistiTotali = acquistiTotali;
	}

	public void setDomandaLavoro(Integer domandaLavoro) {
		this.domandaLavoro = domandaLavoro;
	}

	public void setEsuberi(Integer esuberi) {
		this.esuberi = esuberi;
	}

	public void setFornitureEsterne(BigDecimal fornitureEsterne) {
		this.fornitureEsterne = fornitureEsterne;
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

	public void setIndottoTerritoriale(BigDecimal indottoTerritoriale) {
		this.indottoTerritoriale = indottoTerritoriale;
	}

	public void setLavoratoriInForza(Integer lavoratoriInForza) {
		this.lavoratoriInForza = lavoratoriInForza;
	}

	public void setProductivity(Double productivity) {
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
