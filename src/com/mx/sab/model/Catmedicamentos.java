package com.mx.sab.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catmedicamentos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catmedicamentos", catalog = "my_db_rrg")
public class Catmedicamentos implements java.io.Serializable {

	// Fields

	private Integer medicamentoId;
	private Aseguradores aseguradores;
	private Principiosactivos principiosactivos;
	private Catformasfarmaceuticas catformasfarmaceuticas;
	private String medicamento;
	private Integer sustanciasActivasId;
	private String clave;
	private String ean;
	private String clasificacion;
	private String convenio;
	private Set<Medicamentosreceta> medicamentosrecetas = new HashSet<Medicamentosreceta>(
			0);

	// Constructors

	/** default constructor */
	public Catmedicamentos() {
	}

	/** full constructor */
	public Catmedicamentos(Aseguradores aseguradores,
			Principiosactivos principiosactivos,
			Catformasfarmaceuticas catformasfarmaceuticas, String medicamento,
			Integer sustanciasActivasId, String clave, String ean,
			String clasificacion, String convenio,
			Set<Medicamentosreceta> medicamentosrecetas) {
		this.aseguradores = aseguradores;
		this.principiosactivos = principiosactivos;
		this.catformasfarmaceuticas = catformasfarmaceuticas;
		this.medicamento = medicamento;
		this.sustanciasActivasId = sustanciasActivasId;
		this.clave = clave;
		this.ean = ean;
		this.clasificacion = clasificacion;
		this.convenio = convenio;
		this.medicamentosrecetas = medicamentosrecetas;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MedicamentoId", unique = true, nullable = false)
	public Integer getMedicamentoId() {
		return this.medicamentoId;
	}

	public void setMedicamentoId(Integer medicamentoId) {
		this.medicamentoId = medicamentoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AseguradorId")
	public Aseguradores getAseguradores() {
		return this.aseguradores;
	}

	public void setAseguradores(Aseguradores aseguradores) {
		this.aseguradores = aseguradores;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PrincipioActivoId")
	public Principiosactivos getPrincipiosactivos() {
		return this.principiosactivos;
	}

	public void setPrincipiosactivos(Principiosactivos principiosactivos) {
		this.principiosactivos = principiosactivos;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FormaFarmaceuticaId")
	public Catformasfarmaceuticas getCatformasfarmaceuticas() {
		return this.catformasfarmaceuticas;
	}

	public void setCatformasfarmaceuticas(
			Catformasfarmaceuticas catformasfarmaceuticas) {
		this.catformasfarmaceuticas = catformasfarmaceuticas;
	}

	@Column(name = "Medicamento")
	public String getMedicamento() {
		return this.medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	@Column(name = "SustanciasActivasId")
	public Integer getSustanciasActivasId() {
		return this.sustanciasActivasId;
	}

	public void setSustanciasActivasId(Integer sustanciasActivasId) {
		this.sustanciasActivasId = sustanciasActivasId;
	}

	@Column(name = "Clave", length = 12)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "EAN", length = 45)
	public String getEan() {
		return this.ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	@Column(name = "Clasificacion", length = 45)
	public String getClasificacion() {
		return this.clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	@Column(name = "Convenio", length = 45)
	public String getConvenio() {
		return this.convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catmedicamentos")
	public Set<Medicamentosreceta> getMedicamentosrecetas() {
		return this.medicamentosrecetas;
	}

	public void setMedicamentosrecetas(
			Set<Medicamentosreceta> medicamentosrecetas) {
		this.medicamentosrecetas = medicamentosrecetas;
	}

}