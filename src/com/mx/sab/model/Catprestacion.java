package com.mx.sab.model;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Catprestacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catprestacion", catalog = "my_db_rrg")
public class Catprestacion implements java.io.Serializable {

	// Fields

	private Integer prestacionId;
	private Timestamp fechaHoraEtl;
	private String codigo;
	private String subcodigo;
	private String descripcion;
	private Integer servicioId;
	private String nivelI;
	private String nivelIi;
	private String nivelIii;
	private String nivelIv;
	private String nivelV;
	private String especialidad1;
	private String especialidad2;
	private String especialidad3;
	private String especialidad4;
	private String especialidad5;
	private String especialidad6;
	private Set<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalenciases = new HashSet<Prestacionesaseguradorequivalencias>(
			0);
	private Set<CuadroprestacionPrestacion> cuadroprestacionPrestacions = new HashSet<CuadroprestacionPrestacion>(
			0);
	private Set<Prestacionesportomar> prestacionesportomars = new HashSet<Prestacionesportomar>(
			0);
	private Set<Atencionprestacion> atencionprestacions = new HashSet<Atencionprestacion>(
			0);
	private Set<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciases = new HashSet<Prestacionesprestadorequivalencias>(
			0);

	// Constructors

	/** default constructor */
	public Catprestacion() {
	}

	/** minimal constructor */
	public Catprestacion(Timestamp fechaHoraEtl, String codigo,
			String descripcion) {
		this.fechaHoraEtl = fechaHoraEtl;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/** full constructor */
	public Catprestacion(
			Timestamp fechaHoraEtl,
			String codigo,
			String subcodigo,
			String descripcion,
			Integer servicioId,
			String nivelI,
			String nivelIi,
			String nivelIii,
			String nivelIv,
			String nivelV,
			String especialidad1,
			String especialidad2,
			String especialidad3,
			String especialidad4,
			String especialidad5,
			String especialidad6,
			Set<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalenciases,
			Set<CuadroprestacionPrestacion> cuadroprestacionPrestacions,
			Set<Prestacionesportomar> prestacionesportomars,
			Set<Atencionprestacion> atencionprestacions,
			Set<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciases) {
		this.fechaHoraEtl = fechaHoraEtl;
		this.codigo = codigo;
		this.subcodigo = subcodigo;
		this.descripcion = descripcion;
		this.servicioId = servicioId;
		this.nivelI = nivelI;
		this.nivelIi = nivelIi;
		this.nivelIii = nivelIii;
		this.nivelIv = nivelIv;
		this.nivelV = nivelV;
		this.especialidad1 = especialidad1;
		this.especialidad2 = especialidad2;
		this.especialidad3 = especialidad3;
		this.especialidad4 = especialidad4;
		this.especialidad5 = especialidad5;
		this.especialidad6 = especialidad6;
		this.prestacionesaseguradorequivalenciases = prestacionesaseguradorequivalenciases;
		this.cuadroprestacionPrestacions = cuadroprestacionPrestacions;
		this.prestacionesportomars = prestacionesportomars;
		this.atencionprestacions = atencionprestacions;
		this.prestacionesprestadorequivalenciases = prestacionesprestadorequivalenciases;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PrestacionId", unique = true, nullable = false)
	public Integer getPrestacionId() {
		return this.prestacionId;
	}

	public void setPrestacionId(Integer prestacionId) {
		this.prestacionId = prestacionId;
	}

	@Column(name = "FechaHoraETL", nullable = false, length = 19)
	public Timestamp getFechaHoraEtl() {
		return this.fechaHoraEtl;
	}

	public void setFechaHoraEtl(Timestamp fechaHoraEtl) {
		this.fechaHoraEtl = fechaHoraEtl;
	}

	@Column(name = "Codigo", nullable = false, length = 20)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "Subcodigo", length = 11)
	public String getSubcodigo() {
		return this.subcodigo;
	}

	public void setSubcodigo(String subcodigo) {
		this.subcodigo = subcodigo;
	}

	@Column(name = "Descripcion", nullable = false)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "ServicioId")
	public Integer getServicioId() {
		return this.servicioId;
	}

	public void setServicioId(Integer servicioId) {
		this.servicioId = servicioId;
	}

	@Column(name = "NivelI", length = 120)
	public String getNivelI() {
		return this.nivelI;
	}

	public void setNivelI(String nivelI) {
		this.nivelI = nivelI;
	}

	@Column(name = "NivelII", length = 120)
	public String getNivelIi() {
		return this.nivelIi;
	}

	public void setNivelIi(String nivelIi) {
		this.nivelIi = nivelIi;
	}

	@Column(name = "NivelIII", length = 120)
	public String getNivelIii() {
		return this.nivelIii;
	}

	public void setNivelIii(String nivelIii) {
		this.nivelIii = nivelIii;
	}

	@Column(name = "NivelIV", length = 120)
	public String getNivelIv() {
		return this.nivelIv;
	}

	public void setNivelIv(String nivelIv) {
		this.nivelIv = nivelIv;
	}

	@Column(name = "NivelV", length = 120)
	public String getNivelV() {
		return this.nivelV;
	}

	public void setNivelV(String nivelV) {
		this.nivelV = nivelV;
	}

	@Column(name = "Especialidad1", length = 120)
	public String getEspecialidad1() {
		return this.especialidad1;
	}

	public void setEspecialidad1(String especialidad1) {
		this.especialidad1 = especialidad1;
	}

	@Column(name = "Especialidad2", length = 120)
	public String getEspecialidad2() {
		return this.especialidad2;
	}

	public void setEspecialidad2(String especialidad2) {
		this.especialidad2 = especialidad2;
	}

	@Column(name = "Especialidad3", length = 120)
	public String getEspecialidad3() {
		return this.especialidad3;
	}

	public void setEspecialidad3(String especialidad3) {
		this.especialidad3 = especialidad3;
	}

	@Column(name = "Especialidad4", length = 120)
	public String getEspecialidad4() {
		return this.especialidad4;
	}

	public void setEspecialidad4(String especialidad4) {
		this.especialidad4 = especialidad4;
	}

	@Column(name = "Especialidad5", length = 120)
	public String getEspecialidad5() {
		return this.especialidad5;
	}

	public void setEspecialidad5(String especialidad5) {
		this.especialidad5 = especialidad5;
	}

	@Column(name = "Especialidad6", length = 120)
	public String getEspecialidad6() {
		return this.especialidad6;
	}

	public void setEspecialidad6(String especialidad6) {
		this.especialidad6 = especialidad6;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catprestacion")
	public Set<Prestacionesaseguradorequivalencias> getPrestacionesaseguradorequivalenciases() {
		return this.prestacionesaseguradorequivalenciases;
	}

	public void setPrestacionesaseguradorequivalenciases(
			Set<Prestacionesaseguradorequivalencias> prestacionesaseguradorequivalenciases) {
		this.prestacionesaseguradorequivalenciases = prestacionesaseguradorequivalenciases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catprestacion")
	public Set<CuadroprestacionPrestacion> getCuadroprestacionPrestacions() {
		return this.cuadroprestacionPrestacions;
	}

	public void setCuadroprestacionPrestacions(
			Set<CuadroprestacionPrestacion> cuadroprestacionPrestacions) {
		this.cuadroprestacionPrestacions = cuadroprestacionPrestacions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catprestacion")
	public Set<Prestacionesportomar> getPrestacionesportomars() {
		return this.prestacionesportomars;
	}

	public void setPrestacionesportomars(
			Set<Prestacionesportomar> prestacionesportomars) {
		this.prestacionesportomars = prestacionesportomars;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catprestacion")
	public Set<Atencionprestacion> getAtencionprestacions() {
		return this.atencionprestacions;
	}

	public void setAtencionprestacions(
			Set<Atencionprestacion> atencionprestacions) {
		this.atencionprestacions = atencionprestacions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catprestacion")
	public Set<Prestacionesprestadorequivalencias> getPrestacionesprestadorequivalenciases() {
		return this.prestacionesprestadorequivalenciases;
	}

	public void setPrestacionesprestadorequivalenciases(
			Set<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciases) {
		this.prestacionesprestadorequivalenciases = prestacionesprestadorequivalenciases;
	}

}