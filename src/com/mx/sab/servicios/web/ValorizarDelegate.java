package com.mx.sab.servicios.web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "ValorizarDelegate", targetNamespace = "http://web.servicios.sab.mx.com/")
public interface ValorizarDelegate {

	/**
	 * 
	 * @param arg0
	 * @return returns com.mx.sab.servicios.web.ValorizarWebResponseVo
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "valoriza", targetNamespace = "http://web.servicios.sab.mx.com/", className = "com.mx.sab.servicios.web.Valoriza")
	@ResponseWrapper(localName = "valorizaResponse", targetNamespace = "http://web.servicios.sab.mx.com/", className = "com.mx.sab.servicios.web.ValorizaResponse")
	public ValorizarWebResponseVo valoriza(
			@WebParam(name = "arg0", targetNamespace = "") ValorizarWebRequestVo arg0);

}