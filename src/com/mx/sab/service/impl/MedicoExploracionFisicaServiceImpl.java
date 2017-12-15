package com.mx.sab.service.impl;


import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.form.ExploracionFisicaForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.HcExploracionfisica;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMedicoExploracionFisicaService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class MedicoExploracionFisicaServiceImpl implements IMedicoExploracionFisicaService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private ISignosDao signosDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public ExploracionFisicaForm getExploracionFisica(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo) {
	
	ExploracionFisicaForm exploracionFisicaForm = new ExploracionFisicaForm();
	Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(medicoAtencionPacientesForm.getIdAtencion());
	Afiliado afiliado = agendaDao.getAfiliadoById(atencionMedica.getAfiliado().getAfiliadoId());
	Historiaclinica historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
	HcExploracionfisica hcExploracionfisica = new HcExploracionfisica();
	if(historiaClinica != null){	
		exploracionFisicaForm.setIdHistoriaClinica(historiaClinica.getHistoriaClinicaId());
		hcExploracionfisica = agendaDao.getExploracionFisica(historiaClinica.getHistoriaClinicaId());
		if(hcExploracionfisica != null){
			exploracionFisicaForm.setIdExploracionFisica(hcExploracionfisica.getExploracionFisicaId());
			exploracionFisicaForm.setAbdomen(hcExploracionfisica.getAbdomen());
			exploracionFisicaForm.setAparatoDigestivo(hcExploracionfisica.getAparatoDigestivo());
			exploracionFisicaForm.setCabeza(hcExploracionfisica.getCabeza());
			exploracionFisicaForm.setCuello(hcExploracionfisica.getCuello());
			exploracionFisicaForm.setExtremidades(hcExploracionfisica.getExtremidades());
			exploracionFisicaForm.setSistemaCardio(hcExploracionfisica.getSistemaCardio());
			exploracionFisicaForm.setSistemaMusculo(hcExploracionfisica.getSistemaMusculo());
			exploracionFisicaForm.setSistemaNervioso(hcExploracionfisica.getSistemaNervioso());
			exploracionFisicaForm.setTorax(hcExploracionfisica.getTorax());
			exploracionFisicaForm.setOtrasEF(hcExploracionfisica.getOtrasEf());
		}
		
	}
		
	TomaSignosVo tomaSignosVo = new TomaSignosVo();
	Object ultimosSignos = signosDao.getUltimosSignos(afiliado.getAfiliadoId());
	if (ultimosSignos!=null) {
		Signosvitales signosvitales = signosDao.getSignosById(Integer.parseInt(ultimosSignos.toString()));
		tomaSignosVo.setPeso(""+signosvitales.getPeso());
		tomaSignosVo.setAltura(""+signosvitales.getAltura());
		tomaSignosVo.setTensionArterial(signosvitales.getTensionArterial());
		tomaSignosVo.setFechaUltimaSomatometria(""+signosvitales.getFechaSignos());
		tomaSignosVo.setTemperatura(""+signosvitales.getTemperatura());
	}else{
		tomaSignosVo.setPeso("--");
		tomaSignosVo.setAltura("--");
		tomaSignosVo.setTensionArterial("--");
		tomaSignosVo.setFechaUltimaSomatometria("--");
		tomaSignosVo.setTemperatura("--");
				
	}
	
	long edad = FormatUtil.getEdad(afiliado.getFechaDeNacimiento().getTime());
	tomaSignosVo.setEdad(edad);
	tomaSignosVo.setNombre(afiliado.getNombre() + " " + afiliado.getApellidoPaterno() + " " + afiliado.getApellidoMaterno());
	exploracionFisicaForm.setTomaSignosVo(tomaSignosVo);
	exploracionFisicaForm.setAfiliadoId(atencionMedica.getAfiliado().getAfiliadoId());
	
	return exploracionFisicaForm;
	}
	
	@Override
	public void guardarExploracionFisica(ExploracionFisicaForm exploracionFisicaForm,UserInfo userInfo) {
		//log.info("guardarExploracionFisica service");
		int historiaClinicaId = exploracionFisicaForm.getIdHistoriaClinica();
		
		if(historiaClinicaId == 0){
			Afiliado afiliado = agendaDao.getAfiliadoById(exploracionFisicaForm.getAfiliadoId());
			Usuarios usuario = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
			Historiaclinica historiaClinica = new Historiaclinica();
			historiaClinica.setAfiliado(afiliado);
			historiaClinica.setFechaCreacion(new Date());
			historiaClinica.setUsuarios(usuario);
			agendaDao.saveHistoriaClinica(historiaClinica);
	
			historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
			
			HcExploracionfisica hcExploracion = new HcExploracionfisica();
			hcExploracion.setHistoriaclinica(historiaClinica);
			hcExploracion.setAbdomen(exploracionFisicaForm.isAbdomen());
			hcExploracion.setAparatoDigestivo(exploracionFisicaForm.isAparatoDigestivo());
			hcExploracion.setCabeza(exploracionFisicaForm.isCabeza());
			hcExploracion.setCuello(exploracionFisicaForm.isCuello());
			hcExploracion.setExtremidades(exploracionFisicaForm.isExtremidades());
			hcExploracion.setSistemaCardio(exploracionFisicaForm.isSistemaCardio());
			hcExploracion.setSistemaMusculo(exploracionFisicaForm.isSistemaMusculo());
			hcExploracion.setSistemaNervioso(exploracionFisicaForm.isSistemaNervioso());
			hcExploracion.setTorax(exploracionFisicaForm.isTorax());
			hcExploracion.setOtrasEf(exploracionFisicaForm.getOtrasEF());
			agendaDao.saveExploracionFisica(hcExploracion);
		}else{
			HcExploracionfisica hcExploracion = new HcExploracionfisica();
			
			hcExploracion = agendaDao.getExploracionFisica(historiaClinicaId);
			if(hcExploracion != null){
				hcExploracion.setAbdomen(exploracionFisicaForm.isAbdomen());
				hcExploracion.setAparatoDigestivo(exploracionFisicaForm.isAparatoDigestivo());
				hcExploracion.setCabeza(exploracionFisicaForm.isCabeza());
				hcExploracion.setCuello(exploracionFisicaForm.isCuello());
				hcExploracion.setExtremidades(exploracionFisicaForm.isExtremidades());
				hcExploracion.setSistemaCardio(exploracionFisicaForm.isSistemaCardio());
				hcExploracion.setSistemaMusculo(exploracionFisicaForm.isSistemaMusculo());
				hcExploracion.setSistemaNervioso(exploracionFisicaForm.isSistemaNervioso());
				hcExploracion.setTorax(exploracionFisicaForm.isTorax());
				hcExploracion.setOtrasEf(exploracionFisicaForm.getOtrasEF());
				agendaDao.updateHcExploracionfisica(hcExploracion);			
			}else{
				Afiliado afiliado = agendaDao.getAfiliadoById(exploracionFisicaForm.getAfiliadoId());
				Historiaclinica historiaclinicas = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
				HcExploracionfisica hcExploracion2 = new HcExploracionfisica();
				hcExploracion2.setHistoriaclinica(historiaclinicas);
				hcExploracion2.setAbdomen(exploracionFisicaForm.isAbdomen());
				hcExploracion2.setAparatoDigestivo(exploracionFisicaForm.isAparatoDigestivo());
				hcExploracion2.setCabeza(exploracionFisicaForm.isCabeza());
				hcExploracion2.setCuello(exploracionFisicaForm.isCuello());
				hcExploracion2.setExtremidades(exploracionFisicaForm.isExtremidades());
				hcExploracion2.setSistemaCardio(exploracionFisicaForm.isSistemaCardio());
				hcExploracion2.setSistemaMusculo(exploracionFisicaForm.isSistemaMusculo());
				hcExploracion2.setSistemaNervioso(exploracionFisicaForm.isSistemaNervioso());
				hcExploracion2.setTorax(exploracionFisicaForm.isTorax());
				hcExploracion2.setOtrasEf(exploracionFisicaForm.getOtrasEF());
				agendaDao.saveExploracionFisica(hcExploracion2);
			}
		}
		
	}
}
