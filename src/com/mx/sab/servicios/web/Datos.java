package com.mx.sab.servicios.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for datos complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="datos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aporte" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="copago" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPrestacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datos", propOrder = { "aporte", "copago", "idPrestacion",
		"valor" })
public class Datos {

	protected int aporte;
	protected int copago;
	protected int idPrestacion;
	protected int valor;

	/**
	 * Gets the value of the aporte property.
	 * 
	 */
	public int getAporte() {
		return aporte;
	}

	/**
	 * Sets the value of the aporte property.
	 * 
	 */
	public void setAporte(int value) {
		this.aporte = value;
	}

	/**
	 * Gets the value of the copago property.
	 * 
	 */
	public int getCopago() {
		return copago;
	}

	/**
	 * Sets the value of the copago property.
	 * 
	 */
	public void setCopago(int value) {
		this.copago = value;
	}

	/**
	 * Gets the value of the idPrestacion property.
	 * 
	 */
	public int getIdPrestacion() {
		return idPrestacion;
	}

	/**
	 * Sets the value of the idPrestacion property.
	 * 
	 */
	public void setIdPrestacion(int value) {
		this.idPrestacion = value;
	}

	/**
	 * Gets the value of the valor property.
	 * 
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Sets the value of the valor property.
	 * 
	 */
	public void setValor(int value) {
		this.valor = value;
	}

}
