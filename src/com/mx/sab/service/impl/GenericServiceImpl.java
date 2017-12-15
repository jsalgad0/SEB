package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Roles;
import com.mx.sab.service.IGenericService;
import com.mx.sab.vo.CatColoniasVo;
import com.mx.sab.vo.CatMunicipiosVo;
import com.mx.sab.vo.CatTipoIdentificadorVo;
import com.mx.sab.vo.RolesVo;

@Service
@Log4j2
public class GenericServiceImpl implements IGenericService{

	@Autowired
	private IGenericDao genericDao;
	
	@Override
	public Collection<Catestados> getCatEstados() {
		return genericDao.getEstados();
	}
	
	@Override
	public Collection<Lugaresdeatencion> getLugarAtencion() {
		return genericDao.getLugarAtencion();
	}
	
	@Override
	public Collection<Cattipoidentificador> getTipoIdentificador() {
		return genericDao.getTipoIdentificador();
	}

	@Override
	public Collection<CatMunicipiosVo> getCatMunicipios(int id) {
		//log.info("municipios");
		Collection<Catmunicipios> catmunicipios = genericDao.getCatMunicipios(id);
		Collection<CatMunicipiosVo> catMunicipiosList= new ArrayList<>();
		for (Catmunicipios municipios : catmunicipios) {
			CatMunicipiosVo catMunicipiosAux = new CatMunicipiosVo();
			catMunicipiosAux.setActivo(municipios.getActivo());
			catMunicipiosAux.setMunicipio(municipios.getMunicipio());
			catMunicipiosAux.setMunicipioId(municipios.getMunicipioId());
			catMunicipiosList.add(catMunicipiosAux);
		}
		return catMunicipiosList;
	}

	@Override
	public Collection<CatColoniasVo> getCatColonias(int id) {
		Collection<Catcolonias> catcolonias = genericDao.getCatColonias(id);
		Collection<CatColoniasVo> catColoniasList = new ArrayList<>();
		for (Catcolonias colonias : catcolonias) {
			CatColoniasVo catColoniasAux = new CatColoniasVo();
			catColoniasAux.setActivo(colonias.getActivo());
			catColoniasAux.setColonia(colonias.getColonia());
			catColoniasAux.setColoniaId(colonias.getColoniaId());
			catColoniasList.add(catColoniasAux);
		}
		return catColoniasList;
	}
	
	@Override
	public Collection<RolesVo> getRoles(String id) {
		Collection<Roles> roles = genericDao.getRoles(Integer.parseInt(id));
		Collection<RolesVo> rol = new ArrayList<>();
		for (Roles r : roles) {
			RolesVo ro = new RolesVo();
			ro.setRolesId(r.getRolId());
			ro.setRol(r.getRol());
			rol.add(ro);
		}
		return rol;
	}

	@Override
	public Catcolonias getColoniaById(int i) {
		return genericDao.getColoniaById(i);
	}

	@Override
	public Catestados getEstadoById(int i) {
		return genericDao.getEstadoById(i);
	}

	@Override
	public Catmunicipios getMunicipioById(int i) {
		return genericDao.getMunicipioById(i);
	}

	@Override
	public String getRfc(String nombre, String apellidoP, String apellidoM,	String fecha) {
		String rfc = "";
		apellidoP = quitaArticulos(apellidoP);
		apellidoM = quitaArticulos(apellidoM);
		nombre = quitaNombreComun(nombre);
		
		rfc = apellidoP.substring(0, 1);
		
		for(int index = 1; index < apellidoP.length(); index++){
            String vocal = ""+apellidoP.charAt(index);
            if (esVocal(vocal)){
                rfc += vocal;
                break;
            }
        }
		
		if(apellidoM==null || apellidoM.trim().length()==0){
			rfc += nombre.substring(0,2);
		}else{
			rfc += apellidoM.substring(0, 1);
			rfc += nombre.substring(0,1);
		}
		
		rfc = quitaPalabraMala(rfc);
		
		fecha = fecha.substring(8, 10)+fecha.substring(3,5)+fecha.substring(0, 2);
		
		rfc += fecha;
		
		String homoclave = calcularHomoclave(apellidoP+" "+apellidoM+" "+nombre, rfc);
		
		rfc += homoclave;
		
		Gson gson = new GsonBuilder().create();
		rfc = gson.toJson(rfc);
		
		return rfc;
	}	
	
	public String quitaArticulos(String palabra){
		palabra = palabra.toUpperCase();
		palabra = palabra.replaceAll("\\b(DE(L)?|LA(S)?|LOS|Y|A|MAC|VON|VAN)\\s+", "");
		palabra = palabra.replaceAll(" ", "");
        return palabra;
	}
	
	public String quitaNombreComun(String palabra){
		palabra = palabra.toUpperCase();
		palabra = palabra.replaceAll("\\b(J(OSE|\\.)?|MA(RIA|\\.)?)\\s+", "");
		palabra = palabra.replaceAll("\\b(DE(L)?|LA(S)?|LOS|Y|A|VON|VAN)\\s+", "");
		palabra = palabra.replaceAll(" ", "");
        return palabra;
    }
	
	public boolean esVocal(String letra){
        letra = letra.toUpperCase();
        java.util.List<String> vocales = Arrays.asList("A", "E", "I", "O", "U", "Á", "É", "Í", "Ó", "Ú");
        return vocales.contains(letra);
    }

	@Override
	public Collection<Cattipoidentificador> getTipoIdentificadorPersonaConfianza() {
		return genericDao.getTipoIdentificadorPersonaConfianza();
	}
	
	public String quitaPalabraMala(String palabra){
        java.util.List<String> malas = Arrays.asList("BUEI", "BUEY", "CACA", "CACO", "CAGA", "CAGO", "CAKA", "CAKO", "COGE", "COJA",
                       "KOGE", "KOJO", "KAKA", "KULO", "MAME", "MAMO", "MEAR", "MEAS", "MEON", "MION",
                       "COJE", "COJI", "COJO", "CULO", "FETO", "GUEY", "JOTO", "KACA", "KACO", "KAGA",
                       "KAGO", "MOCO", "MULA", "PEDA", "PEDO", "PENE", "PUTA", "PUTO", "QULO", "RATA",
                       "RUIN", "BUEI", "BUEX", "KOGE", "KOGX", "BUEY", "BUEX", "KOJO", "KOJX", "CACA",
					   "CACX", "KAKA", "KAKX", "CACO", "CACX", "KULO", "KULX", "CAGA", "CAGX", "MAME",
					   "MAMX", "CAGO", "CAGX", "MAMO", "MAMX", "CAKA", "CAKX", "MEAR", "MEAX", "COGE",
					   "COGX", "MEON", "MEOX", "COJA", "COJX", "MION", "MIOX", "COJE", "COJX", "MOCO",
					   "MOCX", "COJI", "COJX", "MULA", "MULX", "COJO", "COJX", "PEDA", "PEDX", "CULO",
					   "CULX", "PEDO", "PEDX", "FETO", "FETX", "PENE", "PENX", "GUEY", "GUEX", "PUTA",
					   "PUTX", "JOTO", "JOTX", "PUTO", "PUTX", "KACA", "KACX", "QULO", "QULX", "KACO",
					   "KACX", "RATA", "RATX", "KAGA", "KAGX", "RUIN", "RUIX", "KAGO", "KAGX");
        if(malas.contains(palabra)){
        	palabra = palabra.substring(0, 3)+"X";
        }
        return palabra;
    }
	
	public String calcularHomoclave(String nombre, String rfc){
        String nombreEnNumero = ""; //Guardara el nombre en su correspondiente numérico
        int valorSuma = 0; //La suma de la secuencia de números de nombreEnNumero
        HashMap<Integer, String> tablaRFC1 = new HashMap<Integer, String>();
        tablaRFC1.put(0, "0");
        tablaRFC1.put(1, "1");
        tablaRFC1.put(2, "2");
        tablaRFC1.put(3, "3");
        tablaRFC1.put(4, "4");
        tablaRFC1.put(5, "5");
        tablaRFC1.put(6, "6");
        tablaRFC1.put(7, "7");
        tablaRFC1.put(8, "8");
        tablaRFC1.put(9, "9");
        tablaRFC1.put(10, "&");
        tablaRFC1.put(11, "A");
        tablaRFC1.put(12, "B");
        tablaRFC1.put(13, "C");
        tablaRFC1.put(14, "D");
        tablaRFC1.put(15, "E");
        tablaRFC1.put(16, "F");
        tablaRFC1.put(17, "G");
        tablaRFC1.put(18, "H");
        tablaRFC1.put(19, "I");
        tablaRFC1.put(21, "J");
        tablaRFC1.put(22, "K");
        tablaRFC1.put(23, "L");
        tablaRFC1.put(24, "M");
        tablaRFC1.put(25, "N");
        tablaRFC1.put(26, "O");
        tablaRFC1.put(27, "P");
        tablaRFC1.put(28, "Q");
        tablaRFC1.put(29, "R");
        tablaRFC1.put(32, "S");
        tablaRFC1.put(33, "T");
        tablaRFC1.put(34, "U");
        tablaRFC1.put(35, "V");
        tablaRFC1.put(36, "W");
        tablaRFC1.put(37, "X");
        tablaRFC1.put(38, "Y");
        tablaRFC1.put(39, "Z");
        tablaRFC1.put(10, "Ñ");

        Collection<String> tablaRFC2 = new ArrayList<String>();
        tablaRFC2.add("1");
        tablaRFC2.add("2");
        tablaRFC2.add("3");
        tablaRFC2.add("4");
        tablaRFC2.add("5");
        tablaRFC2.add("6");
        tablaRFC2.add("7");
        tablaRFC2.add("8");
        tablaRFC2.add("9");
        tablaRFC2.add("A");
        tablaRFC2.add("B");
        tablaRFC2.add("C");
        tablaRFC2.add("D");
        tablaRFC2.add("E");
        tablaRFC2.add("F");
        tablaRFC2.add("G");
        tablaRFC2.add("H");
        tablaRFC2.add("I");
        tablaRFC2.add("J");
        tablaRFC2.add("K");
        tablaRFC2.add("L");
        tablaRFC2.add("M");
        tablaRFC2.add("N");
        tablaRFC2.add("P");
        tablaRFC2.add("Q");
        tablaRFC2.add("R");
        tablaRFC2.add("S");
        tablaRFC2.add("T");
        tablaRFC2.add("U");
        tablaRFC2.add("V");
        tablaRFC2.add("W");
        tablaRFC2.add("X");
        tablaRFC2.add("Y");
        tablaRFC2.add("Z");

        HashMap<Integer, String> tablaRFC3 = new HashMap<Integer, String>();
        tablaRFC3.put(0, "0");
        tablaRFC3.put(1, "1");
        tablaRFC3.put(2, "2");
        tablaRFC3.put(3, "3");
        tablaRFC3.put(4, "4");
        tablaRFC3.put(5, "5");
        tablaRFC3.put(6, "6");
        tablaRFC3.put(7, "7");
        tablaRFC3.put(8, "8");
        tablaRFC3.put(9, "9");
        tablaRFC3.put(10, "A");
        tablaRFC3.put(11, "B");
        tablaRFC3.put(12, "C");
        tablaRFC3.put(13, "D");
        tablaRFC3.put(14, "E");
        tablaRFC3.put(15, "F");
        tablaRFC3.put(16, "G");
        tablaRFC3.put(17, "H");
        tablaRFC3.put(18, "I");
        tablaRFC3.put(19, "J");
        tablaRFC3.put(20, "K");
        tablaRFC3.put(21, "L");
        tablaRFC3.put(22, "M");
        tablaRFC3.put(23, "N");
        tablaRFC3.put(24, "&");
        tablaRFC3.put(25, "O");
        tablaRFC3.put(26, "P");
        tablaRFC3.put(27, "Q");
        tablaRFC3.put(28, "R");
        tablaRFC3.put(29, "S");
        tablaRFC3.put(30, "T");
        tablaRFC3.put(31, "U");
        tablaRFC3.put(32, "V");
        tablaRFC3.put(33, "W");
        tablaRFC3.put(34, "X");
        tablaRFC3.put(35, "Y");
        tablaRFC3.put(36, "Z");
        tablaRFC3.put(37, " ");
        tablaRFC3.put(38, "Ñ");
        
        //agregamos un cero al inicio de la representación númerica del nombre
        nombreEnNumero = "0"; 
        
        //Recorremos el nombre y vamos convirtiendo las letras en su valor numérico
        for(int i = 0; i < nombre.length(); i++){
            String caracter = ""+nombre.charAt(i);
            for(Map.Entry<Integer, String> obj : tablaRFC1.entrySet()){
            	Integer key = obj.getKey();
            	Object value = obj.getValue();
            	if(value.equals(caracter)){
                	nombreEnNumero += key;
                }else{
                	nombreEnNumero += "00";
                }
			}
        }
        //Calculamos la suma de la secuencia de números calculados anteriormente. 
        //la formula es:( (el caracter actual multiplicado por diez) mas el valor del caracter siguiente )
        //(y lo anterior multiplicado por el valor del caracter siguiente)
        for (int i = 0; i < nombreEnNumero.length() - 1; i++){
            int i2 = i + 1;
            valorSuma += ((Integer.parseInt(""+nombreEnNumero.charAt(i)) * 10) + Integer.parseInt(""+nombreEnNumero.charAt(i2))) * Integer.parseInt(""+nombreEnNumero.charAt(i2));
        }
		//Se toman los 3 ultimos digitos y se dividen entre el factor 34:
		String ultimos3digitos = ""+valorSuma;
		ultimos3digitos = ultimos3digitos.substring(ultimos3digitos.length()-4, ultimos3digitos.length()-1);
		
		Double cociente = Math.floor(Integer.parseInt(ultimos3digitos) / 34);
		Double residuo = (double) (Integer.parseInt(ultimos3digitos) % 34);
		cociente.intValue();
        String hc = "";  //los dos primeros caracteres de la homoclave
        
        if(tablaRFC2.toArray()[cociente.intValue()]!=null){
        	hc += tablaRFC2.toArray()[cociente.intValue()];
        }else{
        	hc += "Z";
        }
        if(tablaRFC2.toArray()[residuo.intValue()]!=null){
        	hc += tablaRFC2.toArray()[residuo.intValue()];
        }else{
        	hc += "Z";
        }
        
        rfc += hc;

        //Aqui empieza el calculo del digito verificador basado en lo que tenemos del RFC
        //En esta parte tampoco conozco el origen matemático del algoritmo como para dar 
        //una explicación del proceso, así que ¡tengamos fe hermanos!.
        int sumaParcial = 0;
        nombreEnNumero = "";
        for(int i = 0; i < rfc.length(); i++){
        	String caracter = ""+rfc.charAt(i);
            for(Map.Entry<Integer, String> obj : tablaRFC3.entrySet()){
            	Integer key = obj.getKey();
            	Object value = obj.getValue();
            	if(value.equals(caracter)){
                	sumaParcial += (key * (13-i));
                }
			}
        }
		
        int moduloVerificador = sumaParcial % 11;
		
        if(moduloVerificador == 0)
            hc += "0";
        else{
            sumaParcial = 11 - moduloVerificador;
            if (sumaParcial == 0)
                hc += "0";
            else if (sumaParcial == 10)
                hc += "A";
            else
                hc += sumaParcial;
        }
        return hc;
    }

	@Override
	public Collection<CatTipoIdentificadorVo> getTipoIdentificadorByAsegurador(int idAsegurador) {
		Collection<Cattipoidentificador> cattipoidentificadors = genericDao.getTipoIdentificadorByAsegurador(idAsegurador);
		Collection<CatTipoIdentificadorVo> catTipoIdentificadorVos = new ArrayList<>();
		for (Cattipoidentificador cattipoidentificador : cattipoidentificadors) {
			CatTipoIdentificadorVo catTipoIdentificadorVo = new CatTipoIdentificadorVo();
			catTipoIdentificadorVo.setTipoIdentificador(cattipoidentificador.getTipoIdentificador());
			catTipoIdentificadorVo.setTipoIdentificadorId(cattipoidentificador.getTipoIdentificadorId());
			catTipoIdentificadorVos.add(catTipoIdentificadorVo);
		}
		return catTipoIdentificadorVos;
	}
			
	@Override
	public Collection<Catestadocivil> getEstadoCivil() {
		Collection<Catestadocivil> catestadocivils = genericDao.getEstadoCivil();
		return catestadocivils;
	}

	@Override
	public Collection<Catescolaridad> getEscolaridad() {
		Collection<Catescolaridad> catescolaridads = genericDao.getEscolaridad();
		return catescolaridads;
	}

	@Override
	public Collection<Catocupacion> getOcupacion() {
		Collection<Catocupacion> catocupacion = genericDao.getOcupacion();
		return catocupacion;
	}
}
