package it.drwolf.mics.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

@Entity
@Table(name = "simulazione")
public class Simulazione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3544697096729841939L;

	private Integer id;

	private Date dataInserimento;

	private Azienda azienda;

	private Set<DatiBilancio> datiBilanci = new HashSet<DatiBilancio>(0);

	private DomandaMercato domandaMercato;

	public Simulazione() {
	}

	public Simulazione(int id) {
		this.id = id;
	}

	public Simulazione(Integer id, Date dataInserimento, Azienda azienda,
			Set<DatiBilancio> datiBilanci) {
		super();
		this.id = id;
		this.dataInserimento = dataInserimento;
		this.azienda = azienda;
		this.datiBilanci = datiBilanci;
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
		Simulazione other = (Simulazione) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@ManyToOne
	@JoinColumn(name = "id_azienda", nullable = false)
	@NotNull
	public Azienda getAzienda() {
		return this.azienda;
	}

	@Column(name = "data_inserimento", nullable = false)
	public Date getDataInserimento() {
		return this.dataInserimento;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "simulazione")
	public Set<DatiBilancio> getDatiBilanci() {
		return this.datiBilanci;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public DomandaMercato getDomandaMercato() {
		return this.domandaMercato;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_simulazione", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public void setDatiBilanci(Set<DatiBilancio> datiBilanci) {
		this.datiBilanci = datiBilanci;
	}

	public void setDomandaMercato(DomandaMercato domandaMercato) {
		this.domandaMercato = domandaMercato;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
