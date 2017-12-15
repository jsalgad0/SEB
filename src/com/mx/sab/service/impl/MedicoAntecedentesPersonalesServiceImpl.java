package com.mx.sab.service.impl;


import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.form.AntecedentesPersonalesForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMedicoAntecedentesPersonalesService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class MedicoAntecedentesPersonalesServiceImpl implements IMedicoAntecedentesPersonalesService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private ISignosDao signosDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public AntecedentesPersonalesForm getAntecedentesPersonales(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo) {
	AntecedentesPersonalesForm antecedentesPersonalesForm = new AntecedentesPersonalesForm();
	Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(medicoAtencionPacientesForm.getIdAtencion());
	Afiliado afiliado = agendaDao.getAfiliadoById(atencionMedica.getAfiliado().getAfiliadoId());
	Historiaclinica historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
	HcAntecedentespersonal hcAntecedentespersonales = new HcAntecedentespersonal();
	if(historiaClinica != null){
		antecedentesPersonalesForm.setIdHistoriaClinica(historiaClinica.getHistoriaClinicaId());
		hcAntecedentespersonales = agendaDao.getAntecedentesPersonales(historiaClinica.getHistoriaClinicaId());
		if(hcAntecedentespersonales!= null){
			
			antecedentesPersonalesForm.setIdAntecedentesPersonales(hcAntecedentespersonales.getAtencedentesPersonalesId());
			antecedentesPersonalesForm.setAlcoholismo(hcAntecedentespersonales.getAlcoholismoPer());
			antecedentesPersonalesForm.setAlergias(hcAntecedentespersonales.getAlergiasPer());
			antecedentesPersonalesForm.setCardiopatias(hcAntecedentespersonales.getCardiopatiasPer());
			antecedentesPersonalesForm.setDependenciaADroga(hcAntecedentespersonales.getDependencuAdrogaPer());
			antecedentesPersonalesForm.setDependenciaAMedicamentos(hcAntecedentespersonales.getDependenciaAmedicamentosPer());
			antecedentesPersonalesForm.setDiabeteMellitus(hcAntecedentespersonales.getDiabeteMellitusPer());
			antecedentesPersonalesForm.setDisfuncionesFamiliares(hcAntecedentespersonales.getDisfuncionesFamiliaresPer());
			antecedentesPersonalesForm.setHipertensionArterial(hcAntecedentespersonales.getHipertensionArterialPer());
			antecedentesPersonalesForm.setMalformaciones(hcAntecedentespersonales.getMalformacionesPer());	
			antecedentesPersonalesForm.setNeoplasias(hcAntecedentespersonales.getNeoplasiasPer());
			antecedentesPersonalesForm.setObesidad(hcAntecedentespersonales.getObesidadPer());
			antecedentesPersonalesForm.setOtras(hcAntecedentespersonales.getOtrasPer());
			antecedentesPersonalesForm.setQuirurgias(hcAntecedentespersonales.getQuirurgiasPer());
			antecedentesPersonalesForm.setTabaquismo(hcAntecedentespersonales.getTabaquismoPer());
			antecedentesPersonalesForm.setTuberculosis(hcAntecedentespersonales.getTuberculosisPer());
		}
		
	}
		
	antecedentesPersonalesForm.setAfiliadoId(afiliado.getAfiliadoId());
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
	antecedentesPersonalesForm.setTomaSignosVo(tomaSignosVo);
	
	return antecedentesPersonalesForm;
	}
	
	@Override
	public void guardarAntecedentesPersonales(AntecedentesPersonalesForm antecedentesPersonalesForm,UserInfo userInfo) {
		//log.info("guardarAntecedentesFamiliares controller");
		int historiaClinicaId = antecedentesPersonalesForm.getIdHistoriaClinica();
		
		if(historiaClinicaId == 0){
			Afiliado afiliado = agendaDao.getAfiliadoById(antecedentesPersonalesForm.getAfiliadoId());
			Usuarios usuario = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
			Historiaclinica historiaClinica = new Historiaclinica();
			historiaClinica.setAfiliado(afiliado);
			historiaClinica.setFechaCreacion(new Date());
			historiaClinica.setUsuarios(usuario);
			agendaDao.saveHistoriaClinica(historiaClinica);
	
			historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
			HcAntecedentespersonal hcAntecedentespersonales = new HcAntecedentespersonal();		
			hcAntecedentespersonales.setHistoriaclinica(historiaClinica);
			hcAntecedentespersonales.setAlcoholismoPer(antecedentesPersonalesForm.isAlcoholismo());
			hcAntecedentespersonales.setAlergiasPer(antecedentesPersonalesForm.isAlergias());
			hcAntecedentespersonales.setCardiopatiasPer(antecedentesPersonalesForm.isCardiopatias());
			hcAntecedentespersonales.setDependencuAdrogaPer(antecedentesPersonalesForm.isDependenciaADroga());
			hcAntecedentespersonales.setDependenciaAmedicamentosPer(antecedentesPersonalesForm.isDependenciaAMedicamentos());
			hcAntecedentespersonales.setDiabeteMellitusPer(antecedentesPersonalesForm.isDiabeteMellitus());
			hcAntecedentespersonales.setDisfuncionesFamiliaresPer(antecedentesPersonalesForm.isDisfuncionesFamiliares());
			hcAntecedentespersonales.setHipertensionArterialPer(antecedentesPersonalesForm.isHipertensionArterial());
			hcAntecedentespersonales.setMalformacionesPer(antecedentesPersonalesForm.isMalformaciones());	
			hcAntecedentespersonales.setNeoplasiasPer(antecedentesPersonalesForm.isNeoplasias());
			hcAntecedentespersonales.setObesidadPer(antecedentesPersonalesForm.isObesidad());
			hcAntecedentespersonales.setOtrasPer(antecedentesPersonalesForm.getOtras());
			hcAntecedentespersonales.setQuirurgiasPer(antecedentesPersonalesForm.isQuirurgias());
			hcAntecedentespersonales.setTabaquismoPer(antecedentesPersonalesForm.isTabaquismo());
			hcAntecedentespersonales.setTuberculosisPer(antecedentesPersonalesForm.isTuberculosis());
			agendaDao.saveAntecedentesPersonales(hcAntecedentespersonales);
		}else{
			HcAntecedentespersonal hcAntecedentespersonales = new HcAntecedentespersonal();
			hcAntecedentespersonales = agendaDao.getAntecedentesPersonales(historiaClinicaId);
			if(hcAntecedentespersonales!= null){
				hcAntecedentespersonales.setAlcoholismoPer(antecedentesPersonalesForm.isAlcoholismo());
				hcAntecedentespersonales.setAlergiasPer(antecedentesPersonalesForm.isAlergias());
				hcAntecedentespersonales.setCardiopatiasPer(antecedentesPersonalesForm.isCardiopatias());
				hcAntecedentespersonales.setDependencuAdrogaPer(antecedentesPersonalesForm.isDependenciaADroga());
				hcAntecedentespersonales.setDependenciaAmedicamentosPer(antecedentesPersonalesForm.isDependenciaAMedicamentos());
				hcAntecedentespersonales.setDiabeteMellitusPer(antecedentesPersonalesForm.isDiabeteMellitus());
				hcAntecedentespersonales.setDisfuncionesFamiliaresPer(antecedentesPersonalesForm.isDisfuncionesFamiliares());
				hcAntecedentespersonales.setHipertensionArterialPer(antecedentesPersonalesForm.isHipertensionArterial());
				hcAntecedentespersonales.setMalformacionesPer(antecedentesPersonalesForm.isMalformaciones());	
				hcAntecedentespersonales.setNeoplasiasPer(antecedentesPersonalesForm.isNeoplasias());
				hcAntecedentespersonales.setObesidadPer(antecedentesPersonalesForm.isObesidad());
				hcAntecedentespersonales.setOtrasPer(antecedentesPersonalesForm.getOtras());
				hcAntecedentespersonales.setQuirurgiasPer(antecedentesPersonalesForm.isQuirurgias());
				hcAntecedentespersonales.setTabaquismoPer(antecedentesPersonalesForm.isTabaquismo());
				hcAntecedentespersonales.setTuberculosisPer(antecedentesPersonalesForm.isTuberculosis());
				agendaDao.updateAntecedentesPersonales(hcAntecedentespersonales);
				
			}else{
				HcAntecedentespersonal hcAntecedentespersonales2 = new HcAntecedentespersonal();
				Afiliado afiliado = agendaDao.getAfiliadoById(antecedentesPersonalesForm.getAfiliadoId());
				
				Historiaclinica historiaclinicas = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
				hcAntecedentespersonales2.setHistoriaclinica(historiaclinicas);
				hcAntecedentespersonales2.setAlcoholismoPer(antecedentesPersonalesForm.isAlcoholismo());
				hcAntecedentespersonales2.setAlergiasPer(antecedentesPersonalesForm.isAlergias());
				hcAntecedentespersonales2.setCardiopatiasPer(antecedentesPersonalesForm.isCardiopatias());
				hcAntecedentespersonales2.setDependencuAdrogaPer(antecedentesPersonalesForm.isDependenciaADroga());
				hcAntecedentespersonales2.setDependenciaAmedicamentosPer(antecedentesPersonalesForm.isDependenciaAMedicamentos());
				hcAntecedentespersonales2.setDiabeteMellitusPer(antecedentesPersonalesForm.isDiabeteMellitus());
				hcAntecedentespersonales2.setDisfuncionesFamiliaresPer(antecedentesPersonalesForm.isDisfuncionesFamiliares());
				hcAntecedentespersonales2.setHipertensionArterialPer(antecedentesPersonalesForm.isHipertensionArterial());
				hcAntecedentespersonales2.setMalformacionesPer(antecedentesPersonalesForm.isMalformaciones());	
				hcAntecedentespersonales2.setNeoplasiasPer(antecedentesPersonalesForm.isNeoplasias());
				hcAntecedentespersonales2.setObesidadPer(antecedentesPersonalesForm.isObesidad());
				hcAntecedentespersonales2.setOtrasPer(antecedentesPersonalesForm.getOtras());
				hcAntecedentespersonales2.setQuirurgiasPer(antecedentesPersonalesForm.isQuirurgias());
				hcAntecedentespersonales2.setTabaquismoPer(antecedentesPersonalesForm.isTabaquismo());
				hcAntecedentespersonales2.setTuberculosisPer(antecedentesPersonalesForm.isTuberculosis());
				agendaDao.saveAntecedentesPersonales(hcAntecedentespersonales2);
			}
			
		}
		
	}
}
