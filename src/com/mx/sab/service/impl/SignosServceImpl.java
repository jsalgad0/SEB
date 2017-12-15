package com.mx.sab.service.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IHistoriaClinicaPersonalesNoPatologicosDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.INotaMedicaDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.form.ListaSignosForm;
import com.mx.sab.form.SignosVitalesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catsignosvitalesadicionales;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Signosvitalesadicionales;
import com.mx.sab.model.SignosvitalesadicionalesId;
import com.mx.sab.service.ISignosService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.ListaSignosVo;
import com.mx.sab.vo.SignosVitalesAdicionalesVo;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class SignosServceImpl implements ISignosService {
	
	@Autowired private ISignosDao signosDao;
	@Autowired private IAgendaDao agendaDao;
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private INotaMedicaDao notaMedicaDao;
	
	@Autowired
	private IHistoriaClinicaPersonalesNoPatologicosDao historiaClinicaPersonalesNoPatologicosDao;

	@Override
	public Collection<ListaSignosVo> getAtenciones(ListaSignosForm listaSignosForm, UserInfo userInfo) {
		//log.info("ateciones");
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		
		if (listaSignosForm==null) {
			listaSignosForm = new ListaSignosForm();
		}
		
		if (listaSignosForm.getBusquedaApellido()==null) {
			listaSignosForm.setBusquedaApellido("");	
		}
		
		String fecha = FormatUtil.getDate();
		fecha = fecha.substring(6, 10)+"-"+fecha.substring(3, 5)+"-"+fecha.substring(0, 2);
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		
		Collection<Atencionmedica> atencionmedicas = new ArrayList<>();
		if(listaSignosForm.getIdEstatus()==1){
			atencionmedicas = signosDao.getAtencionesAtendidos(fecha, lugaresdeatencion.getCodigoLugarAtencion(), listaSignosForm.getBusquedaApellido(), 0, 7);
		}else if (listaSignosForm.getIdEstatus() == 0) {
			 atencionmedicas = signosDao.getAtencionesPorAtender(fecha, lugaresdeatencion.getCodigoLugarAtencion(), listaSignosForm.getBusquedaApellido(), 0, 7);
		}else{
			 atencionmedicas = signosDao.getAtenciones(fecha, lugaresdeatencion.getCodigoLugarAtencion(), listaSignosForm.getBusquedaApellido(), 0, 7);
		}
				
		Collection<ListaSignosVo> listaSignosVos = new ArrayList<ListaSignosVo>();
		int totalAtenciones;
		if(listaSignosForm.getIdEstatus()==1){
			totalAtenciones = signosDao.getAtencionesAtendidosCount(fecha, lugaresdeatencion.getCodigoLugarAtencion(), listaSignosForm.getBusquedaApellido());
		}else if (listaSignosForm.getIdEstatus() == 0) {
			totalAtenciones = signosDao.getAtencionesPorAtenderCount(fecha, lugaresdeatencion.getCodigoLugarAtencion(), listaSignosForm.getBusquedaApellido());
		}else{
			totalAtenciones = signosDao.getAtencionesCount(fecha, lugaresdeatencion.getCodigoLugarAtencion(), listaSignosForm.getBusquedaApellido());
		}
		
		if (totalAtenciones>0) {
			paginasTotal = totalAtenciones / filas;
			if (totalAtenciones % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				listaSignosForm.setDisplay(7);
			}else {
				listaSignosForm.setDisplay(paginasTotal);
			}
			
			listaSignosForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			for (Atencionmedica atencion : atencionmedicas) {
				ListaSignosVo listaSignosVo = new ListaSignosVo();
				listaSignosVo.setNombrePaciente(atencion.getAfiliado().getNombre()+" "+atencion.getAfiliado().getApellidoPaterno()+" "+atencion.getAfiliado().getApellidoMaterno());
				listaSignosVo.setHoraCita(FormatUtil.getTime(new Date(atencion.getHoraAsistio().getTime())));
				listaSignosVo.setIdAtencion(atencion.getAtencionMedicaId());
				listaSignosVo.setFechaCita(FormatUtil.getDate(atencion.getFechaAsistio()));
				if(atencion.getSignosvitaleses().size() == 0){
					listaSignosVo.setEstatusCita(0);
				}else{
					listaSignosVo.setEstatusCita(1);
				}
				listaSignosVos.add(listaSignosVo);
			}
		}else {
			//log.info("No hay prestadores");
		}
		
		return listaSignosVos;
	}
	
	@Override
	public String getNombre(SignosVitalesForm signosVitalesForm) {
		String nombre = "";
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(signosVitalesForm.getIdAtencion());
	nombre = atencionmedica.getAfiliado().getNombre()+" "+atencionmedica.getAfiliado().getApellidoPaterno()+" "+atencionmedica.getAfiliado().getApellidoMaterno();
		return nombre;
	}

	@Override
	public void save(SignosVitalesForm signosVitalesForm, UserInfo userInfo)throws Exception {
		try{
			Atencionmedica atencionmedica = signosDao.getAtencionMedicaById(signosVitalesForm.getIdAtencion());
			Signosvitales signosvitales = new Signosvitales();
			signosvitales.setAfiliado(atencionmedica.getAfiliado());
			signosvitales.setFechaSignos(new Date());
			signosvitales.setUsuarios(userInfo.getUsuarios());
			signosvitales.setPeso(Double.parseDouble(signosVitalesForm.getPeso()));
			signosvitales.setAltura(Double.parseDouble(signosVitalesForm.getAltura()));
			if(!signosVitalesForm.getCintura().equals("")){
				signosvitales.setCintura(signosvitales.getCintura());
			}
			if(!signosVitalesForm.getIndiceMasaCoporal().equals("")){
				signosvitales.setImc(Double.parseDouble(signosVitalesForm.getIndiceMasaCoporal()));
			}
			
			signosvitales.setTensionArterial(signosVitalesForm.getTensionArterial().replace("_", ""));			
			if(!signosVitalesForm.getGlucosa().equals("")){
				signosvitales.setGlucosa(Double.parseDouble(signosVitalesForm.getGlucosa()));
			}
			signosvitales.setTemperatura(Double.parseDouble(signosVitalesForm.getTemperatura()));
			if(!signosVitalesForm.getFrecuenciaCardiaca().equals("")){
				signosvitales.setPulso(Double.parseDouble(signosVitalesForm.getFrecuenciaCardiaca()));
			}
			if(!signosVitalesForm.getFrecuenciaRespiratoria().equals("")){
				signosvitales.setResp(Double.parseDouble(signosVitalesForm.getFrecuenciaRespiratoria()));
			}
			signosvitales.setObservaciones(signosVitalesForm.getObservaciones());
			long folio = 0;
			try{
				folio = signosDao.getFolio();
				if(folio == 999999999){
					folio = 1;
				}
			}catch(Exception nullPointerException){
				nullPointerException.printStackTrace();
				folio = 1;
			}
			signosvitales.setNumFolio(""+folio);
			int primeraVez = 0;
			try{
				Signosvitales signos = signosDao.getAtencionAnterior(userInfo.getUsuarios().getUsuarioId());
				//log.info(signos);
				primeraVez = 0;
			}catch(Exception nullPointerException){
				nullPointerException.printStackTrace();
				primeraVez = 1;
			}
			signosvitales.setPrimeraVez(primeraVez);
			signosvitales.setAtencionmedica(atencionmedica);
			signosvitales.setSaturacionOxigeno(signosVitalesForm.getSaturacionOxigeno());
			signosDao.save(signosvitales);
			
			if (signosVitalesForm.isEnfermeria()) {
				Signosvitalesadicionales signosvitalesadicional = null;
				for (SignosVitalesAdicionalesVo signosVitalesAdicionalesVo : signosVitalesForm.getSignosVitalesAdicionalesVos()) {
					signosvitalesadicional = new Signosvitalesadicionales();
					SignosvitalesadicionalesId signosvitalesadicionalesId = new SignosvitalesadicionalesId();
					signosvitalesadicionalesId.setSignosVitalesId(signosvitales.getSignosVitalesId());
					signosvitalesadicionalesId.setValor(signosVitalesAdicionalesVo.getValor());
					signosvitalesadicionalesId.setCatSignosVitalesAdicionalesId(signosVitalesAdicionalesVo.getIdServicioAdicional());
					signosvitalesadicional.setId(signosvitalesadicionalesId);
					signosDao.saveSignosVitalesAdicionales(signosvitalesadicional);
				}			
				signosvitales.setSignosVitalesAdicionealesId(signosvitales.getSignosVitalesId());
				signosDao.update(signosvitales);	
			}
			
			atencionmedica.setFechaAtendidoEnfermeria(new Date());
			atencionmedica.setHoraAtendidoEnfermeria(new Time(new Date().getTime()));
			agendaDao.updateAtencionMedica(atencionmedica);
			
		}catch(Exception exception){
			exception.printStackTrace();
			throw exception;
		}
	}
	
	@Override
	public Collection<ListaSignosVo> getLista(int page, UserInfo userInfo) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<ListaSignosVo> listaSignosVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		String fecha = FormatUtil.getDate();
		fecha = fecha.substring(6, 10)+"-"+fecha.substring(3, 5)+"-"+fecha.substring(0, 2);
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		
		
		
		Collection<Atencionmedica> atencionmedicas = signosDao.getAtenciones(fecha, lugaresdeatencion.getCodigoLugarAtencion(),"", inicio, fin);
		for (Atencionmedica atencion : atencionmedicas) {
			ListaSignosVo listaSignosVo = new ListaSignosVo();
			listaSignosVo.setNombrePaciente(atencion.getAfiliado().getNombre()+" "+atencion.getAfiliado().getApellidoPaterno()+" "+atencion.getAfiliado().getApellidoMaterno());
			listaSignosVo.setIdAtencion(atencion.getAtencionMedicaId());
			listaSignosVo.setHoraCita(FormatUtil.getTime(new Date(atencion.getHoraAsistio().getTime())));
			if(atencion.getSignosvitaleses().size() == 0){
				listaSignosVo.setEstatusCita(0);
			}else{
				listaSignosVo.setEstatusCita(1);
			}
			listaSignosVos.add(listaSignosVo);
		}
		
		return listaSignosVos;
	}
	
	@Override
	public TomaSignosVo getTomaSignos(int idAtencion) {
		TomaSignosVo tomaSignosVo = new TomaSignosVo();
		String nombre;
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(idAtencion);
		nombre = atencionmedica.getAfiliado().getNombre()+" "+atencionmedica.getAfiliado().getApellidoPaterno()+" "+atencionmedica.getAfiliado().getApellidoMaterno();
		
		long edad = FormatUtil.getEdad(atencionmedica.getAfiliado().getFechaDeNacimiento().getTime());
		
		Object ultimosSignos = signosDao.getUltimosSignos(atencionmedica.getAfiliado().getAfiliadoId());
		if (ultimosSignos!=null) {
			Signosvitales signosvitales = signosDao.getSignosById(Integer.parseInt(ultimosSignos.toString()));
			tomaSignosVo.setPeso(""+signosvitales.getPeso());
			tomaSignosVo.setAltura(""+signosvitales.getAltura());
			tomaSignosVo.setTensionArterial(signosvitales.getTensionArterial());
			tomaSignosVo.setFechaUltimaSomatometria(""+signosvitales.getFechaSignos());
			if (idAtencion == signosvitales.getAtencionmedica().getAtencionMedicaId()) {
				tomaSignosVo.setLlenado(1);	
			}else{
				tomaSignosVo.setLlenado(0);
			}
		}else{
			tomaSignosVo.setPeso("--");
			tomaSignosVo.setAltura("--");
			tomaSignosVo.setTensionArterial("--");
			tomaSignosVo.setFechaUltimaSomatometria("--");
			tomaSignosVo.setLlenado(0);
		}
		
		//Atencionmedica atencionmedica = agenda.getAtencionmedica();
		tomaSignosVo.setIdAtencion(idAtencion);
		tomaSignosVo.setNombre(nombre);
		tomaSignosVo.setEdad(edad);
		tomaSignosVo.setGuardado(false);
						
			
		return tomaSignosVo;
	}

	@Override
	public SignosVitalesForm getSignosVitales(ListaSignosForm listaSignosForm) {
		SignosVitalesForm signosVitalesForm = new SignosVitalesForm();
		signosVitalesForm.setEnfermeria(listaSignosForm.isEnfermeria());
		Agenda agenda = agendaDao.getAgendaById(listaSignosForm.getIdAgenda());
		Atencionmedica atencionmedica = agenda.getAtencionmedica();
		signosVitalesForm.setIdAtencion(atencionmedica.getAtencionMedicaId());
		Iterator<Signosvitales> iterator = atencionmedica.getSignosvitaleses().iterator();
		if (!iterator.hasNext()) {
			if (listaSignosForm.isEnfermeria()) {
				Collection<Catsignosvitalesadicionales> catsignosvitalesadicionales = signosDao.getCatSignosVitalesAdicionalesByIdAsegurador(agenda.getAseguradores().getAseguradorId());
				List<SignosVitalesAdicionalesVo> signosVitalesAdicionalesVos = new ArrayList<>();
				SignosVitalesAdicionalesVo signosVitalesAdicionalesVo = new SignosVitalesAdicionalesVo();
				for (Catsignosvitalesadicionales catsignosvitalesadicional : catsignosvitalesadicionales) {
					signosVitalesAdicionalesVo = new SignosVitalesAdicionalesVo();
					signosVitalesAdicionalesVo.setIdServicioAdicional(catsignosvitalesadicional.getCatSignosVitalesAdicionalesId());
					signosVitalesAdicionalesVo.setValor("");
					signosVitalesAdicionalesVo.setDescripcion(catsignosvitalesadicional.getDescripcion());
					signosVitalesAdicionalesVos.add(signosVitalesAdicionalesVo);
				}
				signosVitalesForm.setSignosVitalesAdicionalesVos(signosVitalesAdicionalesVos);	
			}
			
		}
		while(iterator.hasNext()){
			Signosvitales signosvitales = iterator.next();
			signosVitalesForm.setPeso(""+signosvitales.getPeso());
			signosVitalesForm.setAltura(""+signosvitales.getAltura());
			signosVitalesForm.setTensionArterial(signosvitales.getTensionArterial());
			signosVitalesForm.setTemperatura(""+signosvitales.getTemperatura());
			signosVitalesForm.setIndiceMasaCoporal(""+signosvitales.getImc());
			signosVitalesForm.setSaturacionOxigeno(signosvitales.getSaturacionOxigeno());
			
			if(signosvitales.getGlucosa()==null){
				signosVitalesForm.setGlucosa("");
			}else{
				signosVitalesForm.setGlucosa(""+signosvitales.getGlucosa());
			}
			if(signosvitales.getResp()==null){
				signosVitalesForm.setFrecuenciaRespiratoria("");
			}else{
				signosVitalesForm.setFrecuenciaRespiratoria(signosvitales.getResp()+"");
			}
			if(signosvitales.getCintura()==null){
				signosVitalesForm.setCintura("");
			}else{
				signosVitalesForm.setCintura(""+signosvitales.getCintura());
			}
			if(signosvitales.getPulso()==null){
				signosVitalesForm.setFrecuenciaCardiaca("");
			}else{
				signosVitalesForm.setFrecuenciaCardiaca(""+signosvitales.getPulso());
			}		
			
			if (listaSignosForm.isEnfermeria()) {
				Collection<Signosvitalesadicionales> signosvitalesadicionales = signosDao.getSignosVitalesAdicionales(signosvitales.getSignosVitalesId());
				if (!signosvitalesadicionales.isEmpty()) {
					List<SignosVitalesAdicionalesVo> signosVitalesAdicionalesVos = new ArrayList<>();
					SignosVitalesAdicionalesVo signosVitalesAdicionalesVo = new SignosVitalesAdicionalesVo();
					for (Signosvitalesadicionales signosvitalesadicional : signosvitalesadicionales) {
						signosVitalesAdicionalesVo = new SignosVitalesAdicionalesVo();
						signosVitalesAdicionalesVo.setValor(signosvitalesadicional.getId().getValor());
						signosVitalesAdicionalesVo.setIdServicioAdicional(signosvitalesadicional.getCatsignosvitalesadicionales().getCatSignosVitalesAdicionalesId());
						signosVitalesAdicionalesVo.setDescripcion(signosvitalesadicional.getCatsignosvitalesadicionales().getDescripcion());
						signosVitalesAdicionalesVos.add(signosVitalesAdicionalesVo);
					}
					signosVitalesForm.setSignosVitalesAdicionalesVos(signosVitalesAdicionalesVos);
				}else{
					Collection<Catsignosvitalesadicionales> catsignosvitalesadicionales = signosDao.getCatSignosVitalesAdicionalesByIdAsegurador(agenda.getAseguradores().getAseguradorId());
					List<SignosVitalesAdicionalesVo> signosVitalesAdicionalesVos = new ArrayList<>();
					SignosVitalesAdicionalesVo signosVitalesAdicionalesVo = new SignosVitalesAdicionalesVo();
					for (Catsignosvitalesadicionales catsignosvitalesadicional : catsignosvitalesadicionales) {
						signosVitalesAdicionalesVo = new SignosVitalesAdicionalesVo();
						signosVitalesAdicionalesVo.setIdServicioAdicional(catsignosvitalesadicional.getCatSignosVitalesAdicionalesId());
						signosVitalesAdicionalesVo.setValor("");
						signosVitalesAdicionalesVo.setDescripcion(catsignosvitalesadicional.getDescripcion());
						signosVitalesAdicionalesVos.add(signosVitalesAdicionalesVo);
					}
					signosVitalesForm.setSignosVitalesAdicionalesVos(signosVitalesAdicionalesVos);
				}	
			}
			
			
			
			signosVitalesForm.setEditar(true);
		}
		return signosVitalesForm;
	}

	@Override
	public void update(SignosVitalesForm signosVitalesForm, UserInfo userInfo) {
		Signosvitales signosvitales = signosDao.getSignosByAtencion(signosVitalesForm.getIdAtencion());
		signosvitales.setUsuarios(userInfo.getUsuarios());
		signosvitales.setPeso(Double.parseDouble(signosVitalesForm.getPeso()));
		signosvitales.setAltura(Double.parseDouble(signosVitalesForm.getAltura()));
		signosvitales.setSaturacionOxigeno(signosVitalesForm.getSaturacionOxigeno());
		signosvitales.setSignosVitalesAdicionealesId(signosvitales.getSignosVitalesId());
		if(signosVitalesForm.getCintura()!=null && signosVitalesForm.getCintura().trim().length()>0){
			signosvitales.setCintura(Double.parseDouble(signosVitalesForm.getCintura()));
		}
		signosvitales.setImc(Double.parseDouble(signosVitalesForm.getIndiceMasaCoporal()));
		signosvitales.setTensionArterial(signosVitalesForm.getTensionArterial());
		if(signosVitalesForm.getGlucosa()!=null && signosVitalesForm.getGlucosa().trim().length()>0){
			signosvitales.setGlucosa(Double.parseDouble(signosVitalesForm.getGlucosa()));
		}
		signosvitales.setTemperatura(Double.parseDouble(signosVitalesForm.getTemperatura()));
		if(signosVitalesForm.getFrecuenciaCardiaca()!=null && signosVitalesForm.getFrecuenciaCardiaca().trim().length()>0){
			signosvitales.setPulso(Double.parseDouble(signosVitalesForm.getFrecuenciaCardiaca()));
		}
		if(signosVitalesForm.getFrecuenciaRespiratoria()!=null && signosVitalesForm.getFrecuenciaRespiratoria().trim().length()>0){
			signosvitales.setResp(Double.parseDouble(signosVitalesForm.getFrecuenciaRespiratoria()));
		}
		
		if (signosVitalesForm.isEnfermeria()) {
			Collection<Signosvitalesadicionales> signosvitalesadicionales = signosDao.getSignosVitalesAdicionales(signosvitales.getSignosVitalesId());
			if (!signosvitalesadicionales.isEmpty()) {
				for (Signosvitalesadicionales signosvitalesadicional : signosvitalesadicionales) {
					for (SignosVitalesAdicionalesVo signosVitalesAdicionalesVo : signosVitalesForm.getSignosVitalesAdicionalesVos()) {
						if(signosVitalesAdicionalesVo.getIdServicioAdicional() == signosvitalesadicional.getId().getCatSignosVitalesAdicionalesId()){
							signosDao.deleteSignosVitalesAdicionales(signosvitalesadicional);
							signosvitalesadicional.getId().setValor(signosVitalesAdicionalesVo.getValor());
							signosDao.saveSignosVitalesAdicionales(signosvitalesadicional);	
							break;
						}
					}
				}
			}else{
				Signosvitalesadicionales signosvitalesadicional = null;
				for (SignosVitalesAdicionalesVo signosVitalesAdicionalesVo : signosVitalesForm.getSignosVitalesAdicionalesVos()) {
					signosvitalesadicional = new Signosvitalesadicionales();
					SignosvitalesadicionalesId signosvitalesadicionalesId = new SignosvitalesadicionalesId();
					signosvitalesadicionalesId.setSignosVitalesId(signosvitales.getSignosVitalesId());
					signosvitalesadicionalesId.setValor(signosVitalesAdicionalesVo.getValor());
					signosvitalesadicionalesId.setCatSignosVitalesAdicionalesId(signosVitalesAdicionalesVo.getIdServicioAdicional());
					signosvitalesadicional.setId(signosvitalesadicionalesId);
					signosDao.saveSignosVitalesAdicionales(signosvitalesadicional);
				}
			}
		}
		

		
		signosDao.update(signosvitales);
		
	}

	@Override
	public void getAtencionTomaSignos(SignosVitalesForm signosVitalesForm) {
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(signosVitalesForm.getIdAtencion());
		Iterator<Signosvitales> signosVitalesIterator = atencionmedica.getSignosvitaleses().iterator();
		while (signosVitalesIterator.hasNext()) {
			Signosvitales signosvitales = (Signosvitales) signosVitalesIterator.next();
			signosVitalesForm.setPeso(""+signosvitales.getPeso());
			signosVitalesForm.setAltura(""+signosvitales.getAltura());
			signosVitalesForm.setTemperatura(""+signosvitales.getTemperatura());
			signosVitalesForm.setIndiceMasaCoporal(""+signosvitales.getImc());
			signosVitalesForm.setTensionArterial(signosvitales.getTensionArterial());
			
			
			if (signosvitales.getCintura()!=null) {
				signosVitalesForm.setCintura(""+signosvitales.getCintura());	
			}
			if (signosvitales.getResp()!=null) {
				signosVitalesForm.setFrecuenciaRespiratoria(""+signosvitales.getResp());
			}
			if (signosvitales.getGlucosa()!=null) {
				signosVitalesForm.setGlucosa(""+signosvitales.getGlucosa());
			}
			if (signosvitales.getObservaciones()!=null) {
				signosVitalesForm.setObservaciones(signosvitales.getObservaciones());	
			}
			if (signosvitales.getSaturacionOxigeno()!=null) {
				signosVitalesForm.setSaturacionOxigeno(signosvitales.getSaturacionOxigeno());	
			}
			if (signosvitales.getPulso()!=null) {
				signosVitalesForm.setFrecuenciaCardiaca(""+signosvitales.getPulso());	
			}
			
			signosVitalesForm.setPrimeraVez(1);
			
		}
		
		Afiliadodemografico afiliadodemografico = historiaClinicaPersonalesNoPatologicosDao.getAfiliadoDemograficoByIdAfiliado(atencionmedica.getAfiliado().getAfiliadoId());
		if (afiliadodemografico!=null) {
			signosVitalesForm.setIdDemografico(afiliadodemografico.getDemograficoId());
			if(afiliadodemografico.getRhpositivo()!=0){
				signosVitalesForm.setIdPositivoNegativo(afiliadodemografico.getRhpositivo());	
			}
			if(afiliadodemografico.getCatgrupossanguineos()!=null){
				signosVitalesForm.setIdTipoSangre(afiliadodemografico.getCatgrupossanguineos().getGrupoSanguineoId());	
			}
			
		}	
		
		Notamedica notaMedica = notaMedicaDao.getNotaMedicaByIdAtencion(signosVitalesForm.getIdAtencion());
		if(notaMedica != null){	
			signosVitalesForm.setNotaMedicaLlenada(1);
		}		
	}

	@Override
	public void saveAtencionSignosVitales(SignosVitalesForm signosVitalesForm, UserInfo userInfo) {
		try{
			Atencionmedica atencionmedica = signosDao.getAtencionMedicaById(signosVitalesForm.getIdAtencion());
			boolean existenSignosVitales = false;
			Iterator<Signosvitales> signosVitalesIterator = atencionmedica.getSignosvitaleses().iterator();
			while (signosVitalesIterator.hasNext()) {
				existenSignosVitales = true;
				Signosvitales signosvitales = (Signosvitales) signosVitalesIterator.next();
				signosvitales.setFechaSignos(new Date());
				signosvitales.setUsuarios(userInfo.getUsuarios());
				signosvitales.setPeso(Double.parseDouble(signosVitalesForm.getPeso()));
				signosvitales.setAltura(Double.parseDouble(signosVitalesForm.getAltura()));
				if(!signosVitalesForm.getCintura().equals("")){
					signosvitales.setCintura(Double.parseDouble(signosVitalesForm.getCintura()));
				}
				if(!signosVitalesForm.getIndiceMasaCoporal().equals("")){
					signosvitales.setImc(Double.parseDouble(signosVitalesForm.getIndiceMasaCoporal()));					
				}				
				signosvitales.setTensionArterial(signosVitalesForm.getTensionArterial().replace("_", ""));			
				if(!signosVitalesForm.getGlucosa().equals("")){
					signosvitales.setGlucosa(Double.parseDouble(signosVitalesForm.getGlucosa()));
				}
				signosvitales.setTemperatura(Double.parseDouble(signosVitalesForm.getTemperatura()));
				if(!signosVitalesForm.getFrecuenciaCardiaca().equals("")){
					signosvitales.setPulso(Double.parseDouble(signosVitalesForm.getFrecuenciaCardiaca()));
				}
				if(!signosVitalesForm.getFrecuenciaRespiratoria().equals("")){
					signosvitales.setResp(Double.parseDouble(signosVitalesForm.getFrecuenciaRespiratoria()));
				}	
				
				signosvitales.setObservaciones(signosVitalesForm.getObservaciones());

				int primeraVez = 0;
				try{
					Signosvitales signos = signosDao.getAtencionAnterior(userInfo.getUsuarios().getUsuarioId());
					//log.info(signos);
					primeraVez = 0;
				}catch(Exception nullPointerException){
					nullPointerException.printStackTrace();
					primeraVez = 1;
				}
				signosvitales.setPrimeraVez(primeraVez);
				signosvitales.setAtencionmedica(atencionmedica);
				signosvitales.setSaturacionOxigeno(signosVitalesForm.getSaturacionOxigeno());
				signosDao.update(signosvitales);				
			}
			
			if (existenSignosVitales == false) {
				Signosvitales signosvitales = new Signosvitales();
				signosvitales.setAfiliado(atencionmedica.getAfiliado());
				signosvitales.setFechaSignos(new Date());
				signosvitales.setUsuarios(userInfo.getUsuarios());
				signosvitales.setPeso(Double.parseDouble(signosVitalesForm.getPeso()));
				signosvitales.setAltura(Double.parseDouble(signosVitalesForm.getAltura()));
				if(!signosVitalesForm.getCintura().equals("")){
					signosvitales.setCintura(signosvitales.getCintura());
				}
				if(!signosVitalesForm.getIndiceMasaCoporal().equals("")){
					signosvitales.setImc(Double.parseDouble(signosVitalesForm.getIndiceMasaCoporal()));
				}
				
				signosvitales.setTensionArterial(signosVitalesForm.getTensionArterial().replace("_", ""));			
				if(!signosVitalesForm.getGlucosa().equals("")){
					signosvitales.setGlucosa(Double.parseDouble(signosVitalesForm.getGlucosa()));
				}
				signosvitales.setTemperatura(Double.parseDouble(signosVitalesForm.getTemperatura()));
				if(!signosVitalesForm.getFrecuenciaCardiaca().equals("")){
					signosvitales.setPulso(Double.parseDouble(signosVitalesForm.getFrecuenciaCardiaca()));
				}
				if(!signosVitalesForm.getFrecuenciaRespiratoria().equals("")){
					signosvitales.setResp(Double.parseDouble(signosVitalesForm.getFrecuenciaRespiratoria()));
				}
				signosvitales.setObservaciones(signosVitalesForm.getObservaciones());
				long folio = 0;
				try{
					folio = signosDao.getFolio();
					if(folio == 999999999){
						folio = 1;
					}
				}catch(Exception nullPointerException){
					nullPointerException.printStackTrace();
					folio = 1;
				}
				signosvitales.setNumFolio(""+folio);
				int primeraVez = 0;
				try{
					Signosvitales signos = signosDao.getAtencionAnterior(userInfo.getUsuarios().getUsuarioId());
					//log.info(signos);
					primeraVez = 0;
				}catch(Exception nullPointerException){
					nullPointerException.printStackTrace();
					primeraVez = 1;
				}
				signosvitales.setPrimeraVez(primeraVez);
				signosvitales.setAtencionmedica(atencionmedica);
				signosvitales.setSaturacionOxigeno(signosVitalesForm.getSaturacionOxigeno());
				signosDao.save(signosvitales);
			}
			
			if (signosVitalesForm.getIdDemografico()!=0) {
				Afiliadodemografico afiliadodemografico = historiaClinicaPersonalesNoPatologicosDao.getAfiliadoDemograficoByIdAfiliado(atencionmedica.getAfiliado().getAfiliadoId());
				afiliadodemografico.setRhpositivo(signosVitalesForm.getIdPositivoNegativo());
				afiliadodemografico.setCatgrupossanguineos(historiaClinicaPersonalesNoPatologicosDao.getCatGrupoSanguineo(signosVitalesForm.getIdTipoSangre()));
				historiaClinicaPersonalesNoPatologicosDao.updateAfiliadoDemografico(afiliadodemografico);
			}else{
				Afiliadodemografico afiliadodemografico = new Afiliadodemografico();
				afiliadodemografico.setRhpositivo(signosVitalesForm.getIdPositivoNegativo());
				afiliadodemografico.setCatgrupossanguineos(historiaClinicaPersonalesNoPatologicosDao.getCatGrupoSanguineo(signosVitalesForm.getIdTipoSangre()));
				Afiliado afiliado = agendaDao.getAfiliadoById(atencionmedica.getAfiliado().getAfiliadoId());
				afiliadodemografico.setAfiliado(afiliado);
				afiliadodemografico.setHablaEs(0);
				historiaClinicaPersonalesNoPatologicosDao.saveAfiliadoDemografico(afiliadodemografico);
				signosVitalesForm.setIdDemografico(afiliadodemografico.getDemograficoId());
			}
			signosVitalesForm.setEditar(true);
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	
}
