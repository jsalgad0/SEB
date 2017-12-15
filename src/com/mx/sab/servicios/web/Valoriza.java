package com.mx.sab.servicios.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for valoriza complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="valoriza">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://web.servicios.sab.mx.com/}valorizarWebRequestVo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valoriza", propOrder = { "arg0" })
public class Valoriza {

	protected ValorizarWebRequestVo arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link ValorizarWebRequestVo }
	 * 
	 */
	public ValorizarWebRequestVo getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link ValorizarWebRequestVo }
	 * 
	 */
	public void setArg0(ValorizarWebRequestVo value) {
		this.arg0 = value;
	}

}
