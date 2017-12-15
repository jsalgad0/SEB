package com.mx.sab.service.impl;


import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.form.AntecedentesFamiliaresForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMedicoAntecedentesFamiliaresService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;

import java.util.Date;

@Service
@Log4j2
public class MedicoAntecedentesFamiliaresServiceImpl implements IMedicoAntecedentesFamiliaresService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private ISignosDao signosDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public AntecedentesFamiliaresForm getAntecedentesFamiliares(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo) {
	AntecedentesFamiliaresForm antecedentesFamiliaresForm = new AntecedentesFamiliaresForm();
	Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(medicoAtencionPacientesForm.getIdAtencion());
	Afiliado afiliado = agendaDao.getAfiliadoById(atencionMedica.getAfiliado().getAfiliadoId());
	Historiaclinica historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
	HcAntecedentesfamiliares hcAntecedentesfamiliares = new HcAntecedentesfamiliares();
	if(historiaClinica != null){	
		antecedentesFamiliaresForm.setIdHistoriaClinica(historiaClinica.getHistoriaClinicaId());
		hcAntecedentesfamiliares = agendaDao.getAntecedentesFamiliares(historiaClinica.getHistoriaClinicaId());
		if(hcAntecedentesfamiliares != null){
			antecedentesFamiliaresForm.setIdAntecedentesFamiliares(hcAntecedentesfamiliares.getAntecedentesFamiliaresId());
			antecedentesFamiliaresForm.setAlcoholismo(hcAntecedentesfamiliares.getAlcoholismo());
			antecedentesFamiliaresForm.setAlergias(hcAntecedentesfamiliares.getAlergias());
			antecedentesFamiliaresForm.setCardiopatias(hcAntecedentesfamiliares.getCardiopatias());
			antecedentesFamiliaresForm.setDependenciaADroga(hcAntecedentesfamiliares.getDependenciaAdroga());
			antecedentesFamiliaresForm.setDependenciaAMedicamentos(hcAntecedentesfamiliares.getDependenciaAmedicamentos());
			antecedentesFamiliaresForm.setDiabeteMellitus(hcAntecedentesfamiliares.getDiabeteMellitus());
			antecedentesFamiliaresForm.setDisfuncionesFamiliares(hcAntecedentesfamiliares.getDisfuncionesFamiliares());
			antecedentesFamiliaresForm.setHipertensionArterial(hcAntecedentesfamiliares.getHipertensionArterial());
			antecedentesFamiliaresForm.setMalformaciones(hcAntecedentesfamiliares.getMalformaciones());	
			antecedentesFamiliaresForm.setNeoplasias(hcAntecedentesfamiliares.getNeoplasias());
			antecedentesFamiliaresForm.setObesidad(hcAntecedentesfamiliares.getObesidad());
			antecedentesFamiliaresForm.setOtras(hcAntecedentesfamiliares.getOtras());
			antecedentesFamiliaresForm.setQuirurgias(hcAntecedentesfamiliares.getQuirurgias());
			antecedentesFamiliaresForm.setTabaquismo(hcAntecedentesfamiliares.getTabaquismo());
			antecedentesFamiliaresForm.setTuberculosis(hcAntecedentesfamiliares.getTuberculosis());
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
	}else{
		tomaSignosVo.setPeso("--");
		tomaSignosVo.setAltura("--");
		tomaSignosVo.setTensionArterial("--");
		tomaSignosVo.setFechaUltimaSomatometria("--");
	}
	
	long edad = FormatUtil.getEdad(afiliado.getFechaDeNacimiento().getTime());
	tomaSignosVo.setEdad(edad);
	tomaSignosVo.setNombre(afiliado.getNombre() + " " + afiliado.getApellidoPaterno() + " " + afiliado.getApellidoMaterno());
	antecedentesFamiliaresForm.setTomaSignosVo(tomaSignosVo);
	antecedentesFamiliaresForm.setAfiliadoId(afiliado.getAfiliadoId());
	
	return antecedentesFamiliaresForm;
	}
	
	@Override
	public void guardarAntecedentesFamiliares(AntecedentesFamiliaresForm antecedentesFamiliaresForm,UserInfo userInfo) {
		//log.info("guardarAntecedentesFamiliares controller");
		int historiaClinicaId = antecedentesFamiliaresForm.getIdHistoriaClinica();
		
		if(historiaClinicaId == 0){
			Afiliado afiliado = agendaDao.getAfiliadoById(antecedentesFamiliaresForm.getAfiliadoId());
			Usuarios usuario = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
			Historiaclinica historiaClinica = new Historiaclinica();
			historiaClinica.setAfiliado(afiliado);
			historiaClinica.setFechaCreacion(new Date());
			historiaClinica.setUsuarios(usuario);
			agendaDao.saveHistoriaClinica(historiaClinica);
	
			historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
			HcAntecedentesfamiliares hcAntecedentesfamiliares = new HcAntecedentesfamiliares();		
			hcAntecedentesfamiliares.setHistoriaclinica(historiaClinica);
			hcAntecedentesfamiliares.setAlcoholismo(antecedentesFamiliaresForm.isAlcoholismo());
			hcAntecedentesfamiliares.setAlergias(antecedentesFamiliaresForm.isAlergias());
			hcAntecedentesfamiliares.setCardiopatias(antecedentesFamiliaresForm.isCardiopatias());
			hcAntecedentesfamiliares.setDependenciaAdroga(antecedentesFamiliaresForm.isDependenciaADroga());
			hcAntecedentesfamiliares.setDependenciaAmedicamentos(antecedentesFamiliaresForm.isDependenciaAMedicamentos());
			hcAntecedentesfamiliares.setDiabeteMellitus(antecedentesFamiliaresForm.isDiabeteMellitus());
			hcAntecedentesfamiliares.setDisfuncionesFamiliares(antecedentesFamiliaresForm.isDisfuncionesFamiliares());
			hcAntecedentesfamiliares.setHipertensionArterial(antecedentesFamiliaresForm.isHipertensionArterial());
			hcAntecedentesfamiliares.setMalformaciones(antecedentesFamiliaresForm.isMalformaciones());	
			hcAntecedentesfamiliares.setNeoplasias(antecedentesFamiliaresForm.isNeoplasias());
			hcAntecedentesfamiliares.setObesidad(antecedentesFamiliaresForm.isObesidad());
			hcAntecedentesfamiliares.setOtras(antecedentesFamiliaresForm.getOtras());
			hcAntecedentesfamiliares.setQuirurgias(antecedentesFamiliaresForm.isQuirurgias());
			hcAntecedentesfamiliares.setTabaquismo(antecedentesFamiliaresForm.isTabaquismo());
			hcAntecedentesfamiliares.setTuberculosis(antecedentesFamiliaresForm.isTuberculosis());
			agendaDao.saveAntecedentesFamiliares(hcAntecedentesfamiliares);
		}else{
			HcAntecedentesfamiliares hcAntecedentesfamiliares = new HcAntecedentesfamiliares();
			hcAntecedentesfamiliares = agendaDao.getAntecedentesFamiliares(historiaClinicaId);
			if(hcAntecedentesfamiliares != null){
				hcAntecedentesfamiliares.setAlcoholismo(antecedentesFamiliaresForm.isAlcoholismo());
				hcAntecedentesfamiliares.setAlergias(antecedentesFamiliaresForm.isAlergias());
				hcAntecedentesfamiliares.setCardiopatias(antecedentesFamiliaresForm.isCardiopatias());
				hcAntecedentesfamiliares.setDependenciaAdroga(antecedentesFamiliaresForm.isDependenciaADroga());
				hcAntecedentesfamiliares.setDependenciaAmedicamentos(antecedentesFamiliaresForm.isDependenciaAMedicamentos());
				hcAntecedentesfamiliares.setDiabeteMellitus(antecedentesFamiliaresForm.isDiabeteMellitus());
				hcAntecedentesfamiliares.setDisfuncionesFamiliares(antecedentesFamiliaresForm.isDisfuncionesFamiliares());
				hcAntecedentesfamiliares.setHipertensionArterial(antecedentesFamiliaresForm.isHipertensionArterial());
				hcAntecedentesfamiliares.setMalformaciones(antecedentesFamiliaresForm.isMalformaciones());	
				hcAntecedentesfamiliares.setNeoplasias(antecedentesFamiliaresForm.isNeoplasias());
				hcAntecedentesfamiliares.setObesidad(antecedentesFamiliaresForm.isObesidad());
				hcAntecedentesfamiliares.setOtras(antecedentesFamiliaresForm.getOtras());
				hcAntecedentesfamiliares.setQuirurgias(antecedentesFamiliaresForm.isQuirurgias());
				hcAntecedentesfamiliares.setTabaquismo(antecedentesFamiliaresForm.isTabaquismo());
				hcAntecedentesfamiliares.setTuberculosis(antecedentesFamiliaresForm.isTuberculosis());
				agendaDao.updateAntecedentesFamiliares(hcAntecedentesfamiliares);
			}else{
				HcAntecedentesfamiliares hcAntecedentesfamiliares2 = new HcAntecedentesfamiliares();
				Afiliado afiliado = agendaDao.getAfiliadoById(antecedentesFamiliaresForm.getAfiliadoId());
				Historiaclinica historiaclinicas = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
				hcAntecedentesfamiliares2.setHistoriaclinica(historiaclinicas);
				hcAntecedentesfamiliares2.setAlcoholismo(antecedentesFamiliaresForm.isAlcoholismo());
				hcAntecedentesfamiliares2.setAlergias(antecedentesFamiliaresForm.isAlergias());
				hcAntecedentesfamiliares2.setCardiopatias(antecedentesFamiliaresForm.isCardiopatias());
				hcAntecedentesfamiliares2.setDependenciaAdroga(antecedentesFamiliaresForm.isDependenciaADroga());
				hcAntecedentesfamiliares2.setDependenciaAmedicamentos(antecedentesFamiliaresForm.isDependenciaAMedicamentos());
				hcAntecedentesfamiliares2.setDiabeteMellitus(antecedentesFamiliaresForm.isDiabeteMellitus());
				hcAntecedentesfamiliares2.setDisfuncionesFamiliares(antecedentesFamiliaresForm.isDisfuncionesFamiliares());
				hcAntecedentesfamiliares2.setHipertensionArterial(antecedentesFamiliaresForm.isHipertensionArterial());
				hcAntecedentesfamiliares2.setMalformaciones(antecedentesFamiliaresForm.isMalformaciones());	
				hcAntecedentesfamiliares2.setNeoplasias(antecedentesFamiliaresForm.isNeoplasias());
				hcAntecedentesfamiliares2.setObesidad(antecedentesFamiliaresForm.isObesidad());
				hcAntecedentesfamiliares2.setOtras(antecedentesFamiliaresForm.getOtras());
				hcAntecedentesfamiliares2.setQuirurgias(antecedentesFamiliaresForm.isQuirurgias());
				hcAntecedentesfamiliares2.setTabaquismo(antecedentesFamiliaresForm.isTabaquismo());
				hcAntecedentesfamiliares2.setTuberculosis(antecedentesFamiliaresForm.isTuberculosis());
				agendaDao.saveAntecedentesFamiliares(hcAntecedentesfamiliares2);
			}
		}
		
	}
}
