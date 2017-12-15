package com.mx.sab.properties;

public enum PropertiesKeys {
	
	URL_PAGO_SANTANDER("url.pago.santander"),
	URL_PAGO_RESPUESTA_SANTANDER("url.pago.respuesta.santander"),
	
	EMAIL_SMTP_HOSTNAME("email.smtp.hostname"),
	EMAIL_SMTP_USER("email.smtp.user"),
	EMAIL_SMTP_PASS("email.smtp.pass"),
	EMAIL_SMTP_PORT("email.smtp.port"),
	EMAIL_SMTP_FROM("email.smtp.from"),
	
	FTP_HOST("ftp.host"),
	FTP_USUARIO("ftp.usu"),
	FTP_PASSWORD("ftp.pass"),
	FTP_RUTA_SANTANDER("ftp.ruta.santander.consolida"),
	FTP_RUTA_LOCAL("ftp.ruta.local"),
	
	FTP_DOMI_HOST("ftp.domi.host"),
	FTP_DOMI_USUARIO("ftp.domi.usuario"),
	FTP_DOMI_PASSWORD("ftp.domi.pass");
	
	
	private String key;

	PropertiesKeys(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

}

