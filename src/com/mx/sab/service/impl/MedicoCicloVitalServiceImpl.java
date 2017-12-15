package com.mx.sab.service.impl;


import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IHistoriaClinicaCicloVitalDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.dao.impl.AgendaDaoImpl;
import com.mx.sab.form.AntecedentesFamiliaresForm;
import com.mx.sab.form.AntecedentesPersonalesForm;
import com.mx.sab.form.CicloVitalForm;
import com.mx.sab.form.GinecoObstentricosForm;
import com.mx.sab.form.HistoriaClinicaPersonalesNoPatologicosForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMedicoAntecedentesFamiliaresService;
import com.mx.sab.service.IMedicoCicloVitalService;
import com.mx.sab.service.IMedicoGinecoObstentricosService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.CatGrupoSanguineoVo;
import com.mx.sab.vo.CatMpfVo;
import com.mx.sab.vo.CatTipoFamiliaVo;
import com.mx.sab.vo.TomaSignosVo;
import com.mx.sab.vo.UserInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Service
@Log4j2
public class MedicoCicloVitalServiceImpl implements IMedicoCicloVitalService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IHistoriaClinicaCicloVitalDao cicloVitalDao;
	
	@Autowired
	private ISignosDao signosDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public Collection<CatTipoFamiliaVo> getCatTipoFamilia() {
		Collection<Cattipofamilia> cattipofamilias = cicloVitalDao.getTipoFamilia();
		Collection<CatTipoFamiliaVo> catTipoFamiliaVos = new ArrayList<>();
		for (Cattipofamilia cattipofamilia : cattipofamilias) {
			CatTipoFamiliaVo catTipoFamiliaVo = new CatTipoFamiliaVo();
			catTipoFamiliaVo.setIdTipoFamilia(cattipofamilia.getTipoFamiliaId());
			catTipoFamiliaVo.setDescripcion(cattipofamilia.getDescripcion());
			catTipoFamiliaVos.add(catTipoFamiliaVo);
		}
		return catTipoFamiliaVos;
	}
	
	@Override
	public CicloVitalForm getCicloVital(MedicoAtencionPacientesForm medicoAtencionPacientesForm, UserInfo userInfo) {
		CicloVitalForm cicloVitalForm = new CicloVitalForm();
		Atencionmedica atencionMedica = agendaDao.getAtencionMedicaById(medicoAtencionPacientesForm.getIdAtencion());
		Afiliado afiliado = agendaDao.getAfiliadoById(atencionMedica.getAfiliado().getAfiliadoId());
		Historiaclinica historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
		HcFasedelciclo hcFasedelciclo = new HcFasedelciclo();
		if(historiaClinica != null){
			cicloVitalForm.setIdHistoriaClinica(historiaClinica.getHistoriaClinicaId());
			hcFasedelciclo = cicloVitalDao.getFasedelciclo(historiaClinica.getHistoriaClinicaId());
			if(hcFasedelciclo!= null){
				
				cicloVitalForm.setIdCicloVital(hcFasedelciclo.getFaseDelCicloId());
				cicloVitalForm.setMatrimonio(hcFasedelciclo.getMatrimonio());
				cicloVitalForm.setExpansion(hcFasedelciclo.getExpansion());
				cicloVitalForm.setDispersion(hcFasedelciclo.getDispersion());
				cicloVitalForm.setIndependencia(hcFasedelciclo.getIndependencia());
				cicloVitalForm.setRetiroymuerte(hcFasedelciclo.getRetiroMuerte());
				if(hcFasedelciclo.getCattipofamilia()!=null){
					cicloVitalForm.setIdTipoFamilia(hcFasedelciclo.getCattipofamilia().getTipoFamiliaId());
				}
				
			}
			
		}
			
		cicloVitalForm.setAfiliadoId(afiliado.getAfiliadoId());
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
		cicloVitalForm.setTomaSignosVo(tomaSignosVo);
		
		return cicloVitalForm;		
		
	}
	

	@Override
	public void guardarCicloVital(CicloVitalForm cicloVitalForm,UserInfo userInfo) {
		//log.info("guardarCicloVital");
		int historiaClinicaId = cicloVitalForm.getIdHistoriaClinica();
		
		if(historiaClinicaId == 0){
			Afiliado afiliado = agendaDao.getAfiliadoById(cicloVitalForm.getAfiliadoId());
			Usuarios usuario = usuarioDao.getUsuarioById(userInfo.getUsuarios().getUsuarioId());
			Historiaclinica historiaClinica = new Historiaclinica();
			historiaClinica.setAfiliado(afiliado);
			historiaClinica.setFechaCreacion(new Date());
			historiaClinica.setUsuarios(usuario);
			agendaDao.saveHistoriaClinica(historiaClinica);
	
			historiaClinica = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
			HcFasedelciclo hcFasedelciclo = new HcFasedelciclo();		
			hcFasedelciclo.setHistoriaclinica(historiaClinica);
			hcFasedelciclo.setMatrimonio(cicloVitalForm.isMatrimonio());
			hcFasedelciclo.setExpansion(cicloVitalForm.isExpansion());
			hcFasedelciclo.setDispersion(cicloVitalForm.isDispersion());
			hcFasedelciclo.setIndependencia(cicloVitalForm.isIndependencia());
			hcFasedelciclo.setRetiroMuerte(cicloVitalForm.isRetiroymuerte());
			hcFasedelciclo.setCattipofamilia(cicloVitalDao.getCatTipoFamiliaById(cicloVitalForm.getIdTipoFamilia()));


			cicloVitalDao.saveFasedelciclo(hcFasedelciclo);
		}else{
			HcFasedelciclo hcFasedelciclo = new HcFasedelciclo();	
			hcFasedelciclo = cicloVitalDao.getFasedelciclo(historiaClinicaId);
			if(hcFasedelciclo!= null){
				hcFasedelciclo.setMatrimonio(cicloVitalForm.isMatrimonio());
				hcFasedelciclo.setExpansion(cicloVitalForm.isExpansion());
				hcFasedelciclo.setDispersion(cicloVitalForm.isDispersion());
				hcFasedelciclo.setIndependencia(cicloVitalForm.isIndependencia());
				hcFasedelciclo.setRetiroMuerte(cicloVitalForm.isRetiroymuerte());
				hcFasedelciclo.setCattipofamilia(cicloVitalDao.getCatTipoFamiliaById(cicloVitalForm.getIdTipoFamilia()));
				cicloVitalDao.updateFasedelciclo(hcFasedelciclo);
				
			}else{
				HcFasedelciclo hcFasedelciclo2 = new HcFasedelciclo();	
				Afiliado afiliado = agendaDao.getAfiliadoById(cicloVitalForm.getAfiliadoId());
				
				Historiaclinica historiaclinicas = agendaDao.getHistoriaClinicaByAfiliadoId(afiliado.getAfiliadoId());
				hcFasedelciclo2.setHistoriaclinica(historiaclinicas);
				hcFasedelciclo2.setMatrimonio(cicloVitalForm.isMatrimonio());
				hcFasedelciclo2.setExpansion(cicloVitalForm.isExpansion());
				hcFasedelciclo2.setDispersion(cicloVitalForm.isDispersion());
				hcFasedelciclo2.setIndependencia(cicloVitalForm.isIndependencia());
				hcFasedelciclo2.setRetiroMuerte(cicloVitalForm.isRetiroymuerte());
				hcFasedelciclo2.setCattipofamilia(cicloVitalDao.getCatTipoFamiliaById(cicloVitalForm.getIdTipoFamilia()));
				
				cicloVitalDao.saveFasedelciclo(hcFasedelciclo2);
			}
			
		}
	}


}
