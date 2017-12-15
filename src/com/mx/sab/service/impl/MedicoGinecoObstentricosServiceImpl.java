package com.mx.sab.service.impl;


import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.dao.impl.AgendaDaoImpl;
import com.mx.sab.form.AntecedentesFamiliaresForm;
import com.mx.sab.form.AntecedentesPersonalesForm;
import com.mx.sab.form.GinecoObstentricosForm;
import com.mx.sab.form.HistoriaClinicaPersonalesNoPatologicosForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMedicoAntecedentesFamiliaresService;
import com.mx.sab.service.IMedicoGinecoObstentricosService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.CatGrupoSanguineoVo;
import com.mx.sab.vo.CatMpfVo;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Service
@Log4j2
public class MedicoGinecoObstentricosServiceImpl implements IMedicoGinecoObstentricosService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private ISignosDao signosDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public Collection<CatMpfVo> getCatMpf() {
		Collection<Catmpf> catmpfs = agendaDao.getMpf();
		Collection<CatMpfVo> catMpfVos = new ArrayList<>();
		for (Catmpf catmpf : catmpfs) {
			CatMpfVo catMpfVo = new CatMpfVo();
			catMpfVo.setIdMpf(catmpf.getMpfId());
			catMpfVo.setDescripcion(catmpf.getDescripcion());
			catMpfVos.add(catMpfVo);
		}
		return catMpfVos;
	}
	
	@Override
	public GinecoObstentricosForm getGinecoObstentricos(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo) {
		GinecoObstentricosForm ginecoObstentricosForm = new GinecoObstentricosForm();
		Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(medicoAtencionPacientesForm.getIdAtencion());
		Afiliado afiliado = agendaDao.getAfiliadoById(atencionMedica.getAfiliado().getAfiliadoId());
		Historiaclinica historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
		HcGineco hcGinecoObstentricos = new HcGineco();
		if(historiaClinica != null){
			ginecoObstentricosForm.setIdHistoriaClinica(historiaClinica.getHistoriaClinicaId());
			hcGinecoObstentricos = agendaDao.getGinecoObstentrico(historiaClinica.getHistoriaClinicaId());
			if(hcGinecoObstentricos!= null){
				
				ginecoObstentricosForm.setIdGinecoObstentricos(hcGinecoObstentricos.getGinecoId());
				if(hcGinecoObstentricos.getCatmpf()!= null){
					ginecoObstentricosForm.setIdMpf(hcGinecoObstentricos.getCatmpf().getMpfId());
				}
				ginecoObstentricosForm.setMenarca(hcGinecoObstentricos.getMenarca());
				ginecoObstentricosForm.setRitmo(hcGinecoObstentricos.getRitmo());
				ginecoObstentricosForm.setVsa(hcGinecoObstentricos.getVsa());
				ginecoObstentricosForm.setFur(hcGinecoObstentricos.getFur());
				ginecoObstentricosForm.setG(hcGinecoObstentricos.getG());
				ginecoObstentricosForm.setP(hcGinecoObstentricos.getP());
				ginecoObstentricosForm.setA(hcGinecoObstentricos.getA());
				ginecoObstentricosForm.setC(hcGinecoObstentricos.getC());
				
			}
			
		}
			
		ginecoObstentricosForm.setAfiliadoId(afiliado.getAfiliadoId());
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
		ginecoObstentricosForm.setTomaSignosVo(tomaSignosVo);
		
		return ginecoObstentricosForm;		
		
	}
	

	@Override
	public void guardarGinecoObstentricos(GinecoObstentricosForm ginecoObstentricosForm,UserInfo userInfo) {
		//log.info("guardarGinecoObstentricos controller");
		int historiaClinicaId = ginecoObstentricosForm.getIdHistoriaClinica();
		
		if(historiaClinicaId == 0){
			Afiliado afiliado = agendaDao.getAfiliadoById(ginecoObstentricosForm.getAfiliadoId());
			Usuarios usuario = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
			Historiaclinica historiaClinica = new Historiaclinica();
			historiaClinica.setAfiliado(afiliado);
			historiaClinica.setFechaCreacion(new Date());
			historiaClinica.setUsuarios(usuario);
			agendaDao.saveHistoriaClinica(historiaClinica);
	
			historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
			HcGineco hcGinecoObstentricos = new HcGineco();		
			hcGinecoObstentricos.setHistoriaclinica(historiaClinica);
			hcGinecoObstentricos.setCatmpf(agendaDao.getCatMpfById(ginecoObstentricosForm.getIdMpf()));
			hcGinecoObstentricos.setMenarca(ginecoObstentricosForm.getMenarca());
			hcGinecoObstentricos.setRitmo(ginecoObstentricosForm.getRitmo());
			hcGinecoObstentricos.setVsa(ginecoObstentricosForm.getVsa());
			hcGinecoObstentricos.setFur(ginecoObstentricosForm.getFur());
			hcGinecoObstentricos.setG(ginecoObstentricosForm.getG());
			hcGinecoObstentricos.setP(ginecoObstentricosForm.getP());
			hcGinecoObstentricos.setA(ginecoObstentricosForm.getA());
			hcGinecoObstentricos.setC(ginecoObstentricosForm.getC());


			agendaDao.saveGinecoObstentrico(hcGinecoObstentricos);
		}else{
			HcGineco hcGinecoObstentricos = new HcGineco();		
			hcGinecoObstentricos = agendaDao.getGinecoObstentrico(historiaClinicaId);
			if(hcGinecoObstentricos!= null){
				hcGinecoObstentricos.setCatmpf(agendaDao.getCatMpfById(ginecoObstentricosForm.getIdMpf()));
				hcGinecoObstentricos.setMenarca(ginecoObstentricosForm.getMenarca());
				hcGinecoObstentricos.setRitmo(ginecoObstentricosForm.getRitmo());
				hcGinecoObstentricos.setVsa(ginecoObstentricosForm.getVsa());
				hcGinecoObstentricos.setFur(ginecoObstentricosForm.getFur());
				hcGinecoObstentricos.setG(ginecoObstentricosForm.getG());
				hcGinecoObstentricos.setP(ginecoObstentricosForm.getP());
				hcGinecoObstentricos.setA(ginecoObstentricosForm.getA());
				hcGinecoObstentricos.setC(ginecoObstentricosForm.getC());
				agendaDao.updateGinecoObstentrico(hcGinecoObstentricos);
				
			}else{
				HcGineco hcGinecoObstentricos2 = new HcGineco();	
				Afiliado afiliado = agendaDao.getAfiliadoById(ginecoObstentricosForm.getAfiliadoId());
				
				Historiaclinica historiaclinicas = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
				hcGinecoObstentricos2.setHistoriaclinica(historiaclinicas);
				hcGinecoObstentricos2.setCatmpf(agendaDao.getCatMpfById(ginecoObstentricosForm.getIdMpf()));
				hcGinecoObstentricos2.setMenarca(ginecoObstentricosForm.getMenarca());
				hcGinecoObstentricos2.setRitmo(ginecoObstentricosForm.getRitmo());
				hcGinecoObstentricos2.setVsa(ginecoObstentricosForm.getVsa());
				hcGinecoObstentricos2.setFur(ginecoObstentricosForm.getFur());
				hcGinecoObstentricos2.setG(ginecoObstentricosForm.getG());
				hcGinecoObstentricos2.setP(ginecoObstentricosForm.getP());
				hcGinecoObstentricos2.setA(ginecoObstentricosForm.getA());
				hcGinecoObstentricos2.setC(ginecoObstentricosForm.getC());
				agendaDao.saveGinecoObstentrico(hcGinecoObstentricos2);
			}
			
		}
		
	}


}
