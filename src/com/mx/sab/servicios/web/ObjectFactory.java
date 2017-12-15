package com.mx.sab.servicios.web;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.mx.sab.servicios.web package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _Valoriza_QNAME = new QName(
			"http://web.servicios.sab.mx.com/", "valoriza");
	private final static QName _ValorizaResponse_QNAME = new QName(
			"http://web.servicios.sab.mx.com/", "valorizaResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.mx.sab.servicios.web
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link Datos }
	 * 
	 */
	public Datos createDatos() {
		return new Datos();
	}

	/**
	 * Create an instance of {@link Valoriza }
	 * 
	 */
	public Valoriza createValoriza() {
		return new Valoriza();
	}

	/**
	 * Create an instance of {@link ValorizarWebRequestVo }
	 * 
	 */
	public ValorizarWebRequestVo createValorizarWebRequestVo() {
		return new ValorizarWebRequestVo();
	}

	/**
	 * Create an instance of {@link ValorizaResponse }
	 * 
	 */
	public ValorizaResponse createValorizaResponse() {
		return new ValorizaResponse();
	}

	/**
	 * Create an instance of {@link ValorizarWebResponseVo }
	 * 
	 */
	public ValorizarWebResponseVo createValorizarWebResponseVo() {
		return new ValorizarWebResponseVo();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link Valoriza }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.servicios.sab.mx.com/", name = "valoriza")
	public JAXBElement<Valoriza> createValoriza(Valoriza value) {
		return new JAXBElement<Valoriza>(_Valoriza_QNAME, Valoriza.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ValorizaResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://web.servicios.sab.mx.com/", name = "valorizaResponse")
	public JAXBElement<ValorizaResponse> createValorizaResponse(
			ValorizaResponse value) {
		return new JAXBElement<ValorizaResponse>(_ValorizaResponse_QNAME,
				ValorizaResponse.class, null, value);
	}

}
