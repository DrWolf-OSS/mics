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

@Entity
@Table(name = "dati_bilancio")
public class DatiBilancio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -729533508850818850L;

	private DatiBilancioId id;

	// Quadro generale della performance aziendale

	private BigDecimal roe;

	private BigDecimal rendimentoInvestimentiSicuri;

	private BigDecimal debitiBancheSuFatturato;

	private BigDecimal roi;

	private BigDecimal costoLavoroProCapite;

	private BigDecimal costoLavoroMedioComparto;

	private BigDecimal ebitda;

	// Concorrenza

	private BigDecimal quotaAmmortamentoBeniMateriali;

	private BigDecimal costiProdGodimentoBeniTerzi;

	private BigDecimal quotaAmmortamentoBeniImmateriali;

	// Innovazioni di prodotto / processo

	// Soluzione A
	private BigDecimal valoreProduzione;

	private BigDecimal costiProduzioneServizi;

	private BigDecimal costoPersonale;

	private BigDecimal costoMateriePrime;

	private Integer numeroDipendenti;

	// Soluzione B

	private Integer quantitaProdottiStandard;

	private BigDecimal fatturato;

	// valore produzione (dalla soluzione A)

	private Integer quantitaProdotta;

	// addetti immagino sia equiparabile al numero dipendenti (soluzione A)

	// Quota volumi aziendali

	// Soluzione A

	private Integer quantitaVendute;

	// Soluzione B

	// Fatturato

	private BigDecimal fatturatoComplessivoSettore;

	// Domanda di mercato

	// Soluzione A
	private Integer produzioneTotaleMercatoRiferimento;

	// Soluzione B

	private BigDecimal valoreProduzioneTotaleMercatoRiferimento;

	// Fornitura/Indotto

	private Integer percentualeCostiProduzioneTerritorio;

	private Integer percentualeIndottoCongiunturaleTerritorio;

	private Simulazione simulazione;

	private AnnoBilancio annoBilancio;

	public DatiBilancio() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		DatiBilancio other = (DatiBilancio) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "anno", nullable = true, insertable = false, updatable = false)
	public AnnoBilancio getAnnoBilancio() {
		return this.annoBilancio;
	}

	// deve essere not null
	@Column(name = "costi_prod_godimento_beni_terzi", nullable = true)
	public BigDecimal getCostiProdGodimentoBeniTerzi() {
		return this.costiProdGodimentoBeniTerzi;
	}

	@Column(name = "costi_produzione_serivzi", nullable = true)
	public BigDecimal getCostiProduzioneServizi() {
		return this.costiProduzioneServizi;
	}

	// deve essere not null
	@Column(name = "costo_lavoro_medio_comparto", nullable = true)
	public BigDecimal getCostoLavoroMedioComparto() {
		return this.costoLavoroMedioComparto;
	}

	// deve essere not null
	@Column(name = "costo_lavoro_pro_capite", nullable = true)
	public BigDecimal getCostoLavoroProCapite() {
		return this.costoLavoroProCapite;
	}

	@Column(name = "costo_materie_prime", nullable = true)
	public BigDecimal getCostoMateriePrime() {
		return this.costoMateriePrime;
	}

	@Column(name = "costo_personale", nullable = true)
	public BigDecimal getCostoPersonale() {
		return this.costoPersonale;
	}

	// deve essere not null
	@Column(name = "debiti_banche_su_fatturato", nullable = true)
	public BigDecimal getDebitiBancheSuFatturato() {
		return this.debitiBancheSuFatturato;
	}

	// deve essere not null
	@Column(name = "ebitda", nullable = true)
	public BigDecimal getEbitda() {
		return this.ebitda;
	}

	@Column(name = "fatturato", nullable = true)
	public BigDecimal getFatturato() {
		return this.fatturato;
	}

	@Column(name = "fatturato_complessivo_settore", nullable = true)
	public BigDecimal getFatturatoComplessivoSettore() {
		return this.fatturatoComplessivoSettore;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "simulazioneId", column = @Column(name = "id_Simulazione", nullable = true)),
			@AttributeOverride(name = "annoBilancio", column = @Column(name = "anno", nullable = true)) })
	public DatiBilancioId getId() {
		return this.id;
	}

	@Column(name = "numero_dipendenti", nullable = true)
	public Integer getNumeroDipendenti() {
		return this.numeroDipendenti;
	}

	@Column(name = "percentuale_costi_produzione_territorio", nullable = true)
	public Integer getPercentualeCostiProduzioneTerritorio() {
		return this.percentualeCostiProduzioneTerritorio;
	}

	@Column(name = "percentuale_indotto_congiunturale_territorio", nullable = true)
	public Integer getPercentualeIndottoCongiunturaleTerritorio() {
		return this.percentualeIndottoCongiunturaleTerritorio;
	}

	@Column(name = "produzione_totale_mercato_riferimento", nullable = true)
	public Integer getProduzioneTotaleMercatoRiferimento() {
		return this.produzioneTotaleMercatoRiferimento;
	}

	@Column(name = "quantita_prodotta", nullable = true)
	public Integer getQuantitaProdotta() {
		return this.quantitaProdotta;
	}

	@Column(name = "quantita_prodotti_standard", nullable = true)
	public Integer getQuantitaProdottiStandard() {
		return this.quantitaProdottiStandard;
	}

	@Column(name = "quantita_vendute", nullable = true)
	public Integer getQuantitaVendute() {
		return this.quantitaVendute;
	}

	// deve essere not null
	@Column(name = "quota_ammortamenti_beni_immateriali", nullable = true)
	public BigDecimal getQuotaAmmortamentoBeniImmateriali() {
		return this.quotaAmmortamentoBeniImmateriali;
	}

	// deve essere not null
	@Column(name = "quota_ammortamento_beni_materiali", nullable = true)
	public BigDecimal getQuotaAmmortamentoBeniMateriali() {
		return this.quotaAmmortamentoBeniMateriali;
	}

	// deve essere not null
	@Column(name = "rendimento_investimenti_sicuri", nullable = true)
	public BigDecimal getRendimentoInvestimentiSicuri() {
		return this.rendimentoInvestimentiSicuri;
	}

	// deve essere not null
	@Column(name = "roe", nullable = true)
	public BigDecimal getRoe() {
		return this.roe;
	}

	// deve essere not null
	@Column(name = "roi", nullable = true)
	public BigDecimal getRoi() {
		return this.roi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_simulazione", nullable = true, insertable = false, updatable = false)
	public Simulazione getSimulazione() {
		return this.simulazione;
	}

	@Column(name = "valore_produzione", nullable = true)
	public BigDecimal getValoreProduzione() {
		return this.valoreProduzione;
	}

	@Column(name = "valore_produzione_totale_mercato_riferimento", nullable = true)
	public BigDecimal getValoreProduzioneTotaleMercatoRiferimento() {
		return this.valoreProduzioneTotaleMercatoRiferimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	public void setAnnoBilancio(AnnoBilancio annoBilancio) {
		this.annoBilancio = annoBilancio;
	}

	public void setCostiProdGodimentoBeniTerzi(
			BigDecimal costiProdGodimentoBeniTerzi) {
		this.costiProdGodimentoBeniTerzi = costiProdGodimentoBeniTerzi;
	}

	public void setCostiProduzioneServizi(BigDecimal costiProduzioneServizi) {
		this.costiProduzioneServizi = costiProduzioneServizi;
	}

	public void setCostoLavoroMedioComparto(BigDecimal costoLavoroMedioComparto) {
		this.costoLavoroMedioComparto = costoLavoroMedioComparto;
	}

	public void setCostoLavoroProCapite(BigDecimal costoLavoroProCapite) {
		this.costoLavoroProCapite = costoLavoroProCapite;
	}

	public void setCostoMateriePrime(BigDecimal costoMateriePrime) {
		this.costoMateriePrime = costoMateriePrime;
	}

	public void setCostoPersonale(BigDecimal costoPersonale) {
		this.costoPersonale = costoPersonale;
	}

	public void setDebitiBancheSuFatturato(BigDecimal debitiBamcheSuFatturato) {
		this.debitiBancheSuFatturato = debitiBamcheSuFatturato;
	}

	public void setEbitda(BigDecimal ebitda) {
		this.ebitda = ebitda;
	}

	public void setFatturato(BigDecimal fatturato) {
		this.fatturato = fatturato;
	}

	public void setFatturatoComplessivoSettore(
			BigDecimal fatturatoComplessivoSettore) {
		this.fatturatoComplessivoSettore = fatturatoComplessivoSettore;
	}

	public void setId(DatiBilancioId id) {
		this.id = id;
	}

	public void setNumeroDipendenti(Integer numeroDipendenti) {
		this.numeroDipendenti = numeroDipendenti;
	}

	public void setPercentualeCostiProduzioneTerritorio(
			Integer percentualeCostiProduzioneTerritorio) {
		this.percentualeCostiProduzioneTerritorio = percentualeCostiProduzioneTerritorio;
	}

	public void setPercentualeIndottoCongiunturaleTerritorio(
			Integer percentualeIndottoCongiunturaleTerritotio) {
		this.percentualeIndottoCongiunturaleTerritorio = percentualeIndottoCongiunturaleTerritotio;
	}

	public void setProduzioneTotaleMercatoRiferimento(
			Integer produzioneTotaleMercatoRiferimento) {
		this.produzioneTotaleMercatoRiferimento = produzioneTotaleMercatoRiferimento;
	}

	public void setQuantitaProdotta(Integer quantitaProdotta) {
		this.quantitaProdotta = quantitaProdotta;
	}

	public void setQuantitaProdottiStandard(Integer quantitaProdottiStandard) {
		this.quantitaProdottiStandard = quantitaProdottiStandard;
	}

	public void setQuantitaVendute(Integer quantitaVendute) {
		this.quantitaVendute = quantitaVendute;
	}

	public void setQuotaAmmortamentoBeniImmateriali(
			BigDecimal quotaAmmortamentoBeniImmateriali) {
		this.quotaAmmortamentoBeniImmateriali = quotaAmmortamentoBeniImmateriali;
	}

	public void setQuotaAmmortamentoBeniMateriali(
			BigDecimal quotaAmmortamentoBeniMateriali) {
		this.quotaAmmortamentoBeniMateriali = quotaAmmortamentoBeniMateriali;
	}

	public void setRendimentoInvestimentiSicuri(
			BigDecimal rendimentoInvestimentiSicuri) {
		this.rendimentoInvestimentiSicuri = rendimentoInvestimentiSicuri;
	}

	public void setRoe(BigDecimal roe) {
		this.roe = roe;
	}

	public void setRoi(BigDecimal roi) {
		this.roi = roi;
	}

	public void setSimulazione(Simulazione simulazione) {
		this.simulazione = simulazione;
	}

	public void setValoreProduzione(BigDecimal valoreProduzione) {
		this.valoreProduzione = valoreProduzione;
	}

	public void setValoreProduzioneTotaleMercatoRiferimento(
			BigDecimal valoreProduzioneTotaleMercatoRiferimento) {
		this.valoreProduzioneTotaleMercatoRiferimento = valoreProduzioneTotaleMercatoRiferimento;
	}

}
