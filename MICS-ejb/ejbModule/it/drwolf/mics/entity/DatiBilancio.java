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

	// Innovazioni di prodotto

	private BigDecimal avviamento;

	private BigDecimal quotaAmmortamentoImmImmateriali;

	// Innovazioni di processo

	private BigDecimal quotaAmmortamentoImmMateriali;

	// Produttivita’ media del lavoro/rendimento dei dipendenti

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
	@JoinColumn(name = "anno", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AnnoBilancio getAnnoBilancio() {
		return this.annoBilancio;
	}

	@Column(name = "avviamento", nullable = false)
	public BigDecimal getAvviamento() {
		return this.avviamento;
	}

	@Column(name = "costi_prod_godimento_beni_terzi", nullable = false)
	public BigDecimal getCostiProdGodimentoBeniTerzi() {
		return this.costiProdGodimentoBeniTerzi;
	}

	@Column(name = "costi_produzione_serivzi", nullable = false)
	public BigDecimal getCostiProduzioneServizi() {
		return this.costiProduzioneServizi;
	}

	@Column(name = "costo_lavoro_medio_comparto", nullable = false)
	public BigDecimal getCostoLavoroMedioComparto() {
		return this.costoLavoroMedioComparto;
	}

	@Column(name = "costo_lavoro_pro_capite", nullable = false)
	public BigDecimal getCostoLavoroProCapite() {
		return this.costoLavoroProCapite;
	}

	@Column(name = "costo_materie_prime", nullable = false)
	public BigDecimal getCostoMateriePrime() {
		return this.costoMateriePrime;
	}

	@Column(name = "costo_personale", nullable = false)
	public BigDecimal getCostoPersonale() {
		return this.costoPersonale;
	}

	@Column(name = "debiti_banche_su_fatturato", nullable = false)
	public BigDecimal getDebitiBancheSuFatturato() {
		return this.debitiBancheSuFatturato;
	}

	@Column(name = "ebitda", nullable = false)
	public BigDecimal getEbitda() {
		return this.ebitda;
	}

	@Column(name = "fatturato", nullable = false)
	public BigDecimal getFatturato() {
		return this.fatturato;
	}

	@Column(name = "fatturato_complessivo_settore", nullable = false)
	public BigDecimal getFatturatoComplessivoSettore() {
		return this.fatturatoComplessivoSettore;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "simulazioneId", column = @Column(name = "id_Simulazione", nullable = false)),
			@AttributeOverride(name = "annoBilancio", column = @Column(name = "anno", nullable = false)) })
	@NotNull
	public DatiBilancioId getId() {
		return this.id;
	}

	@Column(name = "numero_dipendenti", nullable = false)
	public Integer getNumeroDipendenti() {
		return this.numeroDipendenti;
	}

	@Column(name = "percentuale_costi_produzione_territorio", nullable = false)
	public Integer getPercentualeCostiProduzioneTerritorio() {
		return this.percentualeCostiProduzioneTerritorio;
	}

	@Column(name = "percentuale_indotto_congiunturale_territorio", nullable = false)
	public Integer getPercentualeIndottoCongiunturaleTerritorio() {
		return this.percentualeIndottoCongiunturaleTerritorio;
	}

	@Column(name = "produzione_totale_mercato_riferimento", nullable = false)
	public Integer getProduzioneTotaleMercatoRiferimento() {
		return this.produzioneTotaleMercatoRiferimento;
	}

	@Column(name = "quantita_prodotta", nullable = false)
	public Integer getQuantitaProdotta() {
		return this.quantitaProdotta;
	}

	@Column(name = "quantita_prodotti_standard", nullable = false)
	public Integer getQuantitaProdottiStandard() {
		return this.quantitaProdottiStandard;
	}

	@Column(name = "quantita_vendute", nullable = false)
	public Integer getQuantitaVendute() {
		return this.quantitaVendute;
	}

	@Column(name = "quota_ammortamenti_beni_immateriali", nullable = false)
	public BigDecimal getQuotaAmmortamentoBeniImmateriali() {
		return this.quotaAmmortamentoBeniImmateriali;
	}

	@Column(name = "quota_ammortamento_beni_materiali", nullable = false)
	public BigDecimal getQuotaAmmortamentoBeniMateriali() {
		return this.quotaAmmortamentoBeniMateriali;
	}

	@Column(name = "quota_ammortamento_imm_immateriali", nullable = false)
	public BigDecimal getQuotaAmmortamentoImmImmateriali() {
		return this.quotaAmmortamentoImmImmateriali;
	}

	@Column(name = "quota_ammortamento_imm_materiali", nullable = false)
	public BigDecimal getQuotaAmmortamentoImmMateriali() {
		return this.quotaAmmortamentoImmMateriali;
	}

	@Column(name = "rendimento_investimenti_sicuri", nullable = false)
	public BigDecimal getRendimentoInvestimentiSicuri() {
		return this.rendimentoInvestimentiSicuri;
	}

	@Column(name = "roe", nullable = false)
	public BigDecimal getRoe() {
		return this.roe;
	}

	@Column(name = "roi", nullable = false)
	public BigDecimal getRoi() {
		return this.roi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_simulazione", nullable = false, insertable = false, updatable = false)
	@NotNull
	public Simulazione getSimulazione() {
		return this.simulazione;
	}

	@Column(name = "valore_produzione", nullable = false)
	public BigDecimal getValoreProduzione() {
		return this.valoreProduzione;
	}

	@Column(name = "valore_produzione_totale_mercato_riferimento", nullable = false)
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

	public void setAvviamento(BigDecimal avviamento) {
		this.avviamento = avviamento;
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

	public void setQuotaAmmortamentoImmImmateriali(
			BigDecimal quotaAmmortamentoImmImmateriali) {
		this.quotaAmmortamentoImmImmateriali = quotaAmmortamentoImmImmateriali;
	}

	public void setQuotaAmmortamentoImmMateriali(
			BigDecimal quotaAmmortamentoImmMateriali) {
		this.quotaAmmortamentoImmMateriali = quotaAmmortamentoImmMateriali;
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
