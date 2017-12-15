package com.mx.sab.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorsUtil {
	
	public static boolean validaRFC(String rfc){
		Pattern p = Pattern.compile("^(([A-Z]|[a-z]){4})([0-9]{6})");
	    Matcher m = p.matcher(rfc);
	    boolean res1 = m.matches();
	    
	    p = Pattern.compile("^(([A-Z]|[a-z]){4})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))");
	    m = p.matcher(rfc);
	    boolean res2 = m.matches();
		return res1 || res2;
	}

	public static boolean validaCorreo(String correo){
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
	    Matcher m = p.matcher(correo);
		return m.matches();
	}
	
	public static boolean validaCP(String cp){
		Pattern p = Pattern.compile("^([0-9]{5})");
	    Matcher m = p.matcher(cp);
		return m.matches();
	}
	
	public static boolean validaCelular(String celular){
		Pattern p = Pattern.compile("^([0-9]{8,10})");
	    Matcher m = p.matcher(celular);
		return m.matches();
	}

	public static boolean validaCURP(String identificador) {
		identificador=identificador.toUpperCase();
		return identificador.matches("[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[0-9]{2}");
	}

	public static boolean validaIFE(String identificador) {
		 return identificador.matches("[0-9]{13}");
	}
	
	public static boolean validaNumeros(String cadena) {
		 return cadena.matches(".*\\d.*");
	}
	
	public static boolean validaLetraMinuscula(String cadena) {
		return cadena.matches(".*[a-z].*");
	}
	
	public static boolean validaLetraMayuscula(String cadena) {
		 return cadena.matches(".*[A-Z].*");
	}
	
	public static boolean validaLetrasRfc(String cadena, String rfc) {
		String letras = rfc.substring(0,4);
        if (cadena.indexOf(letras)!=-1) {
			return false;
		}else{
			return true;	
		}
	}
	
	public static boolean validaNumerosRfc(String cadena, String rfc) {
		String numeros = rfc.substring(4,10); 
		if (cadena.indexOf(numeros)!=-1) {
			return false;
		}else{
			return true;	
		}
	}
}
