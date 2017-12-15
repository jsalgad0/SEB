package com.mx.sab.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mx.sab.enums.CatEstadosEnum;
import com.mx.sab.vo.CargaAfiliadosVo;

public class FormatUtil {

	public static String generarAcronimo(String texto){
		String[] words = null;
		String acronym = null;
		String nextWord = null;;
		if(texto!=null){
			texto = texto.replaceAll("/\\s{2,}/g"," ");
			texto = texto.replaceAll("/\\-/g","");
			texto = texto.replaceAll("/\\-/g","");
			if(texto!=""){
				words = texto.split(" ");
				acronym= "";
				int index = 0;
					
				while (index<words.length) {
					nextWord = words[index];
					acronym = acronym + nextWord.charAt(0);
					if(nextWord.length()>1) acronym = acronym + nextWord.charAt(1);
					if(nextWord.length()>2) acronym = acronym + nextWord.charAt(2);
					if(words.length==1) acronym = acronym + nextWord.charAt(nextWord.length()-1);
					index = index + 1 ;  
				}
			}else{
				acronym = texto;
			} 
		}else{
			acronym = texto;
		} 
		
		return acronym;
	}
	
	public static String agregaCeros(String texto, int longitud){
		while(texto.length()<longitud){
			texto = "0"+texto;
		}
		return texto;
	}
	
	public static Date getDate(String fecha){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = simpleDateFormat.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDate(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = simpleDateFormat.format(new Date());
		return fecha;
	}
	
	public static String getDate(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = simpleDateFormat.format(date);
		return fecha;
	}	
		
	
	public static String getCalendar(Calendar calendar){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = simpleDateFormat.format(calendar.getTime());
		return fecha;
	}

	public static String getTime(Date time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		String fecha = simpleDateFormat.format(time);
		return fecha;		
	}

	public static String claveIssste(String claveDeLaUnidad) {
		while (claveDeLaUnidad.length()<8) {
			claveDeLaUnidad = "0"+claveDeLaUnidad;
		}
		String claveFormateada = claveDeLaUnidad.substring(0, 3) + "-" + claveDeLaUnidad.substring(3, 6) + "-" + claveDeLaUnidad.substring(6, 8);
		return claveFormateada;
	}
	
	public static long getEdad(long nacimiento){
		long diff = new Date().getTime() - nacimiento;
		long days = (24 * 60 * 60 * 1000);
		long diffdays = diff / days;
		long diffyears = diffdays / 365;
		return diffyears;
	}
	
	public static long getHoras(long fecha){
		long diff = new Date().getTime() - fecha;
		long horas = (60 * 60 * 1000);
		long diffhoras = diff / horas;
		return diffhoras;
	}	

	public static Time getTime(String idTiempo) {
		Time time = null;
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			Date fecha = simpleDateFormat.parse(idTiempo);
			time = new Time(fecha.getTime());			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static void getFechaFromCurp(CargaAfiliadosVo cargaAfiliadosVo) {
		String homoclave = ""; 
		try{
			cargaAfiliadosVo.getCurp().substring(16, 18);
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
		String diaNac = cargaAfiliadosVo.getCurp().substring(8,10);
		String mesNac = cargaAfiliadosVo.getCurp().substring(6,8);
		String anioNac = cargaAfiliadosVo.getCurp().substring(4,6);
		/**
		 La homoclave es el único dígito que asigna la RENAPO, aunque por lo general es un número del 0 al 9 para los nacidos antes del año 2000 o es una letra entre la 'A' y la 'Z' para los nacidos a partir del año 2000. Cuando la RENAPO encuentra que en los primeros 16 dígitos ya existe una clave igual en su base de datos, modifica la homoclave para evitar la repetición. Por lo tanto, aunque éste programa es capaz de generar la homoclave, es necesario verificar la homoclave oficial. 
		*/
		
		try{
			Integer.parseInt(homoclave);
			anioNac = "19" + anioNac;
		}catch(Exception ex){
			int anioNacAux = Integer.parseInt(anioNac);
			Calendar calendar = Calendar.getInstance();
			String anioAux = ("" +calendar.get(Calendar.YEAR)).substring(2, 4);
			int anio = Integer.parseInt(anioAux);
			if (anioNacAux>anio) {
				anioNac = "19" + anioNac;
			}else{
				anioNac = "20" + anioNac;	
			}
			
		}
		
		cargaAfiliadosVo.setFechaNacimiento(diaNac+"/"+mesNac+"/"+anioNac);
		String estadoNacimiento= cargaAfiliadosVo.getCurp().substring(11, 13);
		
		switch(estadoNacimiento){
			case "AS": cargaAfiliadosVo.setEstado(CatEstadosEnum.AGUASCALIENTES.getId()); break;
			case "BC": cargaAfiliadosVo.setEstado(CatEstadosEnum.BAJA_CALIFORNIA.getId()); break;
			case "BS": cargaAfiliadosVo.setEstado(CatEstadosEnum.BAJA_CALIFORNIA_SUR.getId()); break;
			case "CC": cargaAfiliadosVo.setEstado(CatEstadosEnum.CAMPECHE.getId()); break;
			case "CL": cargaAfiliadosVo.setEstado(CatEstadosEnum.COAHUILA.getId()); break;
			case "CM": cargaAfiliadosVo.setEstado(CatEstadosEnum.COLIMA.getId()); break;
			case "CS": cargaAfiliadosVo.setEstado(CatEstadosEnum.CHIAPAS.getId()); break;
			case "CH": cargaAfiliadosVo.setEstado(CatEstadosEnum.CHIHUAHUA.getId()); break;
			case "DF": cargaAfiliadosVo.setEstado(CatEstadosEnum.DISTRITO_FEDERAL.getId()); break;
			case "DG": cargaAfiliadosVo.setEstado(CatEstadosEnum.DURANGO.getId()); break;
			case "GT": cargaAfiliadosVo.setEstado(CatEstadosEnum.GUANAJUATO.getId()); break;
			case "GR": cargaAfiliadosVo.setEstado(CatEstadosEnum.GUERRERO.getId()); break;
			case "HG": cargaAfiliadosVo.setEstado(CatEstadosEnum.HIDALGO.getId()); break;
			case "JC": cargaAfiliadosVo.setEstado(CatEstadosEnum.JALISCO.getId()); break;
			case "MC": cargaAfiliadosVo.setEstado(CatEstadosEnum.ESTADO_MEXICO.getId()); break;
			case "MN": cargaAfiliadosVo.setEstado(CatEstadosEnum.MICHOACAN.getId()); break;
			case "MS": cargaAfiliadosVo.setEstado(CatEstadosEnum.MORELOS.getId()); break;
			case "NT": cargaAfiliadosVo.setEstado(CatEstadosEnum.NAYARIT.getId()); break;
			case "NL": cargaAfiliadosVo.setEstado(CatEstadosEnum.NUEVO_LEON.getId()); break;
			case "OC": cargaAfiliadosVo.setEstado(CatEstadosEnum.OAXACA.getId()); break;
			case "PL": cargaAfiliadosVo.setEstado(CatEstadosEnum.PUEBLA.getId()); break;
			case "QT": cargaAfiliadosVo.setEstado(CatEstadosEnum.QUERETARO.getId()); break;
			case "QR": cargaAfiliadosVo.setEstado(CatEstadosEnum.QUINTANA_ROO.getId()); break;
			case "SP": cargaAfiliadosVo.setEstado(CatEstadosEnum.SAN_LUIS_POTOSI.getId()); break;
			case "SL": cargaAfiliadosVo.setEstado(CatEstadosEnum.SINALOA.getId()); break;
			case "SR": cargaAfiliadosVo.setEstado(CatEstadosEnum.SONORA.getId()); break;
			case "TC": cargaAfiliadosVo.setEstado(CatEstadosEnum.TABASCO.getId()); break;
			case "TS": cargaAfiliadosVo.setEstado(CatEstadosEnum.TAMAULIPAS.getId()); break;
			case "TL": cargaAfiliadosVo.setEstado(CatEstadosEnum.TLAXCALA.getId()); break;
			case "VZ": cargaAfiliadosVo.setEstado(CatEstadosEnum.VERACRUZ.getId()); break;
			case "YN": cargaAfiliadosVo.setEstado(CatEstadosEnum.YUCATAN.getId()); break;
			case "ZS": cargaAfiliadosVo.setEstado(CatEstadosEnum.ZACATECAS.getId()); break;
			case "NE": cargaAfiliadosVo.setEstado(CatEstadosEnum.EXTRANJERO.getId()); break;
		}
	}

	public static String getDateTxt(Date dateCellValue) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = simpleDateFormat.format(dateCellValue);
		return fecha;
	}
	
	public static String getDateddmmmyyyy(Date dateCellValue) {
		String fecha = "";
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			fecha = simpleDateFormat.format(dateCellValue);			
		}catch(Exception ex){}

		return fecha;
	}	
	
	public static String getDateddmmyyyy(Date dateCellValue) {
		String fecha = "";
		try{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			fecha = simpleDateFormat.format(dateCellValue);			
		}catch(Exception ex){}

		return fecha;
	}	
	
	public static Date getDateTxt(String fecha){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getFechaCarpeta() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
		String fecha = simpleDateFormat.format(new Date());
		return fecha;
	}
	
	public static String getMd5(String texto){
		MessageDigest md = null;
		String hashtext = "";
		try{
			md = MessageDigest.getInstance("MD5");
	        byte[] messageDigest = md.digest(texto.getBytes());
	        BigInteger number = new BigInteger(1, messageDigest);
	        hashtext = number.toString(16);
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }
		}catch (Exception e){
			e.printStackTrace();
		} 
        
        return hashtext;
	}
	
	public static String getDateClave(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String fecha = simpleDateFormat.format(new Date());
		return fecha;
	}

	
}
