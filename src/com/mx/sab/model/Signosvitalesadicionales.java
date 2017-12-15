package com.mx.sab.model;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Signosvitalesadicionales entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "signosvitalesadicionales", catalog = "my_db_rrg")
public class Signosvitalesadicionales implements java.io.Serializable {

	// Fields

	private SignosvitalesadicionalesId id;
	private Catsignosvitalesadicionales catsignosvitalesadicionales;

	// Constructors

	/** default constructor */
	public Signosvitalesadicionales() {
	}

	/** full constructor */
	public Signosvitalesadicionales(SignosvitalesadicionalesId id,
			Catsignosvitalesadicionales catsignosvitalesadicionales) {
		this.id = id;
		this.catsignosvitalesadicionales = catsignosvitalesadicionales;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "signosVitalesId", column = @Column(name = "SignosVitalesId", nullable = false)),
			@AttributeOverride(name = "catSignosVitalesAdicionalesId", column = @Column(name = "CatSignosVitalesAdicionalesId", nullable = false)),
			@AttributeOverride(name = "valor", column = @Column(name = "Valor", nullable = false, length = 10)) })
	public SignosvitalesadicionalesId getId() {
		return this.id;
	}

	public void setId(SignosvitalesadicionalesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatSignosVitalesAdicionalesId", nullable = false, insertable = false, updatable = false)
	public Catsignosvitalesadicionales getCatsignosvitalesadicionales() {
		return this.catsignosvitalesadicionales;
	}

	public void setCatsignosvitalesadicionales(
			Catsignosvitalesadicionales catsignosvitalesadicionales) {
		this.catsignosvitalesadicionales = catsignosvitalesadicionales;
	}

}