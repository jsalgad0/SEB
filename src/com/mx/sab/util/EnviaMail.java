package com.mx.sab.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mx.sab.vo.EmailVo;

public class EnviaMail {
		
	public void enviar(EmailVo emailVo){
		final String usuario = emailVo.getUsuario();
		final String password = emailVo.getPassword();
		
//		HtmlEmail email = new HtmlEmail();
//		System.out.println(propertiesManager.getValue(PropertiesKeys.EMAIL_SMTP_PORT));
//		email.setSmtpPort(Integer.parseInt(propertiesManager.getValue(PropertiesKeys.EMAIL_SMTP_PORT)));
//		email.setAuthenticator((new DefaultAuthenticator(propertiesManager.getValue(PropertiesKeys.EMAIL_SMTP_USER), propertiesManager.getValue(PropertiesKeys.EMAIL_SMTP_PASS))));
//        email.setDebug(true);		
//        email.setHostName(propertiesManager.getValue(PropertiesKeys.EMAIL_SMTP_HOSTNAME));
//        email.setTLS(true);
//        email.setSSL(true);
//        email.setAuthentication(propertiesManager.getValue(PropertiesKeys.EMAIL_SMTP_USER), propertiesManager.getValue(PropertiesKeys.EMAIL_SMTP_PASS));
//
//        try {
//			email.addTo(correo,"Demo");
//			email.setFrom(propertiesManager.getValue(PropertiesKeys.EMAIL_SMTP_FROM));
//	        email.setSubject("Password");
//	        
//	        email.setHtmlMsg("<html>Usuario: "+usuario+"<br> Password: "+pass+"</html>");
//	        String response = email.send();
//	        System.out.println("Correo enviado");
//		} catch (EmailException e) {
//			e.printStackTrace();
//		}
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailVo.getHostname());
		props.put("mail.smtp.port", emailVo.getPuerto());
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailVo.getForm()));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailVo.getCorreo()));
			message.setSubject("Password");
			message.setText("Cuerpo del texto: ");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
