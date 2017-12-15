package com.mx.sab.servicios.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * ValorizarService service = new ValorizarService();
 * ValorizarDelegate portType = service.getValorizarPort();
 * portType.valoriza(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "ValorizarService", targetNamespace = "http://web.servicios.sab.mx.com/", wsdlLocation = "http://54.153.29.217/serviciosWeb/ValorizarPort?wsdl")
public class ValorizarService extends Service {

	private final static URL VALORIZARSERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.mx.sab.servicios.web.ValorizarService.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.mx.sab.servicios.web.ValorizarService.class
					.getResource(".");
			url = new URL(baseUrl,
					"http://seb:8080/serviciosWeb/ValorizarPort?wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://54.153.29.217/serviciosWeb/ValorizarPort?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		VALORIZARSERVICE_WSDL_LOCATION = url;
	}

	public ValorizarService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public ValorizarService() {
		super(VALORIZARSERVICE_WSDL_LOCATION, new QName(
				"http://web.servicios.sab.mx.com/", "ValorizarService"));
	}

	/**
	 * 
	 * @return returns ValorizarDelegate
	 */
	@WebEndpoint(name = "ValorizarPort")
	public ValorizarDelegate getValorizarPort() {
		return super.getPort(new QName("http://web.servicios.sab.mx.com/",
				"ValorizarPort"), ValorizarDelegate.class);
	}

}
