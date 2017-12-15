package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IHistoriaClinicaPersonalesNoPatologicosDao;
import com.mx.sab.form.HistoriaClinicaPersonalesNoPatologicosForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.service.IHistoriaClinicaPersonalesNoPatologicosService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.CatAlimentacionVo;
import com.mx.sab.vo.CatEscolaridadVo;
import com.mx.sab.vo.CatEstadoCivilVo;
import com.mx.sab.vo.CatGrupoSanguineoVo;
import com.mx.sab.vo.CatHigieneVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class HistoriaClinicaPersonalesNoPatologicosServiceImpl implements IHistoriaClinicaPersonalesNoPatologicosService {
	
	@Autowired
	private IHistoriaClinicaPersonalesNoPatologicosDao historiaClinicaPersonalesNoPatologicosDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Override
	public void inicializarForm(HistoriaClinicaPersonalesNoPatologicosForm historiaClinicaPersonalesNoPatologicosForm) {
		Afiliado afiliado = agendaDao.getAtencionMedicaById(historiaClinicaPersonalesNoPatologicosForm.getIdAtencion()).getAfiliado();
		historiaClinicaPersonalesNoPatologicosForm.setIdAfiliado(afiliado.getAfiliadoId());
		historiaClinicaPersonalesNoPatologicosForm.setFechaPersonalesPatologicos(FormatUtil.getDate(afiliado.getFechaDeNacimiento()));
		Historiaclinica historiaclinica = historiaClinicaPersonalesNoPatologicosDao.getHistoriaClinicaByIdAfiliado(historiaClinicaPersonalesNoPatologicosForm.getIdAfiliado());
		if (historiaclinica!=null) {
			Iterator<HcPersonalnopatologicos> hcPersonalNoPatologicosIterator = historiaclinica.getHcPersonalnopatologicoses().iterator();
			while (hcPersonalNoPatologicosIterator.hasNext()) {
				HcPersonalnopatologicos hcPersonalnopatologicos = (HcPersonalnopatologicos) hcPersonalNoPatologicosIterator.next();
				historiaClinicaPersonalesNoPatologicosForm.setIdAlimentacion(hcPersonalnopatologicos.getCatalimentacion().getAlimentacionId());
				historiaClinicaPersonalesNoPatologicosForm.setIdEscolaridad(hcPersonalnopatologicos.getCatescolaridad().getEscolaridadId());
				historiaClinicaPersonalesNoPatologicosForm.setIdEstadoCivil(hcPersonalnopatologicos.getCatestadocivil().getEstadoCivilId());
				historiaClinicaPersonalesNoPatologicosForm.setIdHigienePersonal(hcPersonalnopatologicos.getCathigiene().getHigieneId());
				historiaClinicaPersonalesNoPatologicosForm.setIdPersonalNoPatologicos(hcPersonalnopatologicos.getPersonalNoPatologicosId());		
			}
		}
		Afiliadodemografico afiliadodemografico = historiaClinicaPersonalesNoPatologicosDao.getAfiliadoDemograficoByIdAfiliado(historiaClinicaPersonalesNoPatologicosForm.getIdAfiliado());
		if (afiliadodemografico!=null) {
			if (afiliadodemografico.getRhpositivo()!=0) {
				historiaClinicaPersonalesNoPatologicosForm.setIdPositivoNegativo(afiliadodemografico.getRhpositivo());	
			}
			if (afiliadodemografico.getCatgrupossanguineos()!=null) {
				historiaClinicaPersonalesNoPatologicosForm.setIdTipoSangre(afiliadodemografico.getCatgrupossanguineos().getGrupoSanguineoId());	
			}
			
			historiaClinicaPersonalesNoPatologicosForm.setIdDemografico(afiliadodemografico.getDemograficoId());	
		}
	}

	@Override
	public Collection<CatGrupoSanguineoVo> getTipoSangre() {
		Collection<Catgrupossanguineos> catgrupossanguineos = historiaClinicaPersonalesNoPatologicosDao.getTipoSangre();
		Collection<CatGrupoSanguineoVo> catGrupoSanguineoVos = new ArrayList<>();
		for (Catgrupossanguineos catgrupossanguineo : catgrupossanguineos) {
			CatGrupoSanguineoVo catGrupoSanguineoVo = new CatGrupoSanguineoVo();
			catGrupoSanguineoVo.setIdSangre(catgrupossanguineo.getGrupoSanguineoId());
			catGrupoSanguineoVo.setDescripcion(catgrupossanguineo.getGrupoSanguineo());
			catGrupoSanguineoVos.add(catGrupoSanguineoVo);
		}
		return catGrupoSanguineoVos;
	}

	@Override
	public Collection<CatEscolaridadVo> getEscolaridades() {
		Collection<Catescolaridad> catescolaridads = historiaClinicaPersonalesNoPatologicosDao.getEscolaridades();
		Collection<CatEscolaridadVo> catEscolaridadVos = new ArrayList<>();
		for (Catescolaridad catescolaridad : catescolaridads) {
			CatEscolaridadVo catEscolaridadVo = new CatEscolaridadVo();
			catEscolaridadVo.setIdEscolaridad(catescolaridad.getEscolaridadId());
			catEscolaridadVo.setDescripcion(catescolaridad.getDescripcion());
			catEscolaridadVos.add(catEscolaridadVo);
		}
		return catEscolaridadVos;
	}

	@Override
	public Collection<CatAlimentacionVo> getAlimentaciones() {
		Collection<Catalimentacion> catalimentacions = historiaClinicaPersonalesNoPatologicosDao.getAlimentaciones();
		Collection<CatAlimentacionVo> catAlimentacionVos = new ArrayList<>();
		for (Catalimentacion catalimentacion : catalimentacions) {
			CatAlimentacionVo catAlimentacionVo = new CatAlimentacionVo();
			catAlimentacionVo.setIdAlimentacion(catalimentacion.getAlimentacionId());
			catAlimentacionVo.setDescripcion(catalimentacion.getDescripcion());
			catAlimentacionVos.add(catAlimentacionVo);
		}
		return catAlimentacionVos;
	}

	@Override
	public Collection<CatHigieneVo> getHigienePersonales() {
		Collection<Cathigiene> cathigienes = historiaClinicaPersonalesNoPatologicosDao.getHigienePersonales();
		Collection<CatHigieneVo> catHigieneVos = new ArrayList<>();
		for (Cathigiene cathigiene : cathigienes) {
			CatHigieneVo catHigieneVo = new CatHigieneVo();
			catHigieneVo.setIdHigiene(cathigiene.getHigieneId());
			catHigieneVo.setDescripcion(cathigiene.getDescripcion());
			catHigieneVos.add(catHigieneVo);
		}
		return catHigieneVos;
	}

	@Override
	public Collection<CatEstadoCivilVo> getEstadosCiviles() {
		Collection<Catestadocivil> catestadocivils = historiaClinicaPersonalesNoPatologicosDao.getEstadosCiviles();
		Collection<CatEstadoCivilVo> catEstadoCivilVos = new ArrayList<>();
		for (Catestadocivil catestadocivil : catestadocivils) {
			CatEstadoCivilVo catEstadoCivilVo = new CatEstadoCivilVo();
			catEstadoCivilVo.setIdEstadoCivil(catestadocivil.getEstadoCivilId());
			catEstadoCivilVo.setDescripcion(catestadocivil.getDescripcion());
			catEstadoCivilVos.add(catEstadoCivilVo);
		}
		return catEstadoCivilVos;
	}

	@Override
	public void saveHistoriaClinicaPersonalesNoPatologicos(HistoriaClinicaPersonalesNoPatologicosForm historiaClinicaPersonalesNoPatologicosForm, UserInfo userInfo) {
		if (historiaClinicaPersonalesNoPatologicosForm.getIdEscolaridad()==0) {
			historiaClinicaPersonalesNoPatologicosForm.setError("Debe de seleccionar la escolaridad");
		}else{
			if (historiaClinicaPersonalesNoPatologicosForm.getIdEstadoCivil()==0) {
				historiaClinicaPersonalesNoPatologicosForm.setError("Debe de seleccionar el estado civil");		
			}else{
				if (historiaClinicaPersonalesNoPatologicosForm.getIdAlimentacion()==0) {
					historiaClinicaPersonalesNoPatologicosForm.setError("Debe de seleccionar el tipo de alimentacion");	
				}else{
					if (historiaClinicaPersonalesNoPatologicosForm.getIdHigienePersonal()==0) {
						historiaClinicaPersonalesNoPatologicosForm.setError("Debe de seleccionar el tipo de higiene");	
					}
				}
			}
		}
		
		if (historiaClinicaPersonalesNoPatologicosForm.getError().equals("")) {
			if (historiaClinicaPersonalesNoPatologicosForm.getIdPersonalNoPatologicos()!=0) {
				HcPersonalnopatologicos hcPersonalnopatologicos = historiaClinicaPersonalesNoPatologicosDao.getHcPersonalNoPatologicosById(historiaClinicaPersonalesNoPatologicosForm.getIdPersonalNoPatologicos());
				hcPersonalnopatologicos.setCatalimentacion(historiaClinicaPersonalesNoPatologicosDao.getCatAlimentacionById(historiaClinicaPersonalesNoPatologicosForm.getIdAlimentacion()));
				hcPersonalnopatologicos.setCatescolaridad(historiaClinicaPersonalesNoPatologicosDao.getCatEscolaridadById(historiaClinicaPersonalesNoPatologicosForm.getIdEscolaridad()));
				hcPersonalnopatologicos.setCathigiene(historiaClinicaPersonalesNoPatologicosDao.getCatHigieneById(historiaClinicaPersonalesNoPatologicosForm.getIdHigienePersonal()));
				hcPersonalnopatologicos.setCatestadocivil(historiaClinicaPersonalesNoPatologicosDao.getCatEstadoCivilById(historiaClinicaPersonalesNoPatologicosForm.getIdEstadoCivil()));
				historiaClinicaPersonalesNoPatologicosDao.updateHcPersonalnopatologicos(hcPersonalnopatologicos);
			}else{
				Historiaclinica historiaclinica = historiaClinicaPersonalesNoPatologicosDao.getHistoriaClinicaByIdAfiliado(historiaClinicaPersonalesNoPatologicosForm.getIdAfiliado());
				if (historiaclinica!=null) {
					HcPersonalnopatologicos hcPersonalnopatologicos = new HcPersonalnopatologicos();
					hcPersonalnopatologicos.setCatalimentacion(historiaClinicaPersonalesNoPatologicosDao.getCatAlimentacionById(historiaClinicaPersonalesNoPatologicosForm.getIdAlimentacion()));
					hcPersonalnopatologicos.setCatescolaridad(historiaClinicaPersonalesNoPatologicosDao.getCatEscolaridadById(historiaClinicaPersonalesNoPatologicosForm.getIdEscolaridad()));
					hcPersonalnopatologicos.setCatestadocivil(historiaClinicaPersonalesNoPatologicosDao.getCatEstadoCivilById(historiaClinicaPersonalesNoPatologicosForm.getIdEstadoCivil()));
					hcPersonalnopatologicos.setCathigiene(historiaClinicaPersonalesNoPatologicosDao.getCatHigieneById(historiaClinicaPersonalesNoPatologicosForm.getIdHigienePersonal()));
					hcPersonalnopatologicos.setHistoriaclinica(historiaclinica);
					historiaClinicaPersonalesNoPatologicosDao.saveHcPersonalesNoPatologicos(hcPersonalnopatologicos);
					historiaClinicaPersonalesNoPatologicosForm.setIdPersonalNoPatologicos(hcPersonalnopatologicos.getPersonalNoPatologicosId());
				}else{
					historiaclinica = new Historiaclinica();
					Afiliado afiliado = agendaDao.getAfiliadoById(historiaClinicaPersonalesNoPatologicosForm.getIdAfiliado());
					historiaclinica.setAfiliado(afiliado);
					historiaclinica.setFechaCreacion(new Date());
					historiaclinica.setUsuarios(userInfo.getUsuarios());
					historiaClinicaPersonalesNoPatologicosDao.saveHistoriaClinica(historiaclinica);				
					HcPersonalnopatologicos hcPersonalnopatologicos = new HcPersonalnopatologicos();
					hcPersonalnopatologicos.setCatalimentacion(historiaClinicaPersonalesNoPatologicosDao.getCatAlimentacionById(historiaClinicaPersonalesNoPatologicosForm.getIdAlimentacion()));
					hcPersonalnopatologicos.setCatescolaridad(historiaClinicaPersonalesNoPatologicosDao.getCatEscolaridadById(historiaClinicaPersonalesNoPatologicosForm.getIdEscolaridad()));
					hcPersonalnopatologicos.setCatestadocivil(historiaClinicaPersonalesNoPatologicosDao.getCatEstadoCivilById(historiaClinicaPersonalesNoPatologicosForm.getIdEstadoCivil()));
					hcPersonalnopatologicos.setCathigiene(historiaClinicaPersonalesNoPatologicosDao.getCatHigieneById(historiaClinicaPersonalesNoPatologicosForm.getIdHigienePersonal()));
					hcPersonalnopatologicos.setHistoriaclinica(historiaclinica);
					historiaClinicaPersonalesNoPatologicosDao.saveHcPersonalesNoPatologicos(hcPersonalnopatologicos);
					historiaClinicaPersonalesNoPatologicosForm.setIdPersonalNoPatologicos(hcPersonalnopatologicos.getPersonalNoPatologicosId());
				}
			}
			
			if (historiaClinicaPersonalesNoPatologicosForm.getIdDemografico()!=0) {
				Afiliadodemografico afiliadodemografico = historiaClinicaPersonalesNoPatologicosDao.getAfiliadoDemograficoByIdAfiliado(historiaClinicaPersonalesNoPatologicosForm.getIdAfiliado());
				afiliadodemografico.setRhpositivo(historiaClinicaPersonalesNoPatologicosForm.getIdPositivoNegativo());
				afiliadodemografico.setCatgrupossanguineos(historiaClinicaPersonalesNoPatologicosDao.getCatGrupoSanguineo(historiaClinicaPersonalesNoPatologicosForm.getIdTipoSangre()));
				historiaClinicaPersonalesNoPatologicosDao.updateAfiliadoDemografico(afiliadodemografico);
			}else{
				Afiliadodemografico afiliadodemografico = new Afiliadodemografico();
				afiliadodemografico.setRhpositivo(historiaClinicaPersonalesNoPatologicosForm.getIdPositivoNegativo());
				afiliadodemografico.setCatgrupossanguineos(historiaClinicaPersonalesNoPatologicosDao.getCatGrupoSanguineo(historiaClinicaPersonalesNoPatologicosForm.getIdTipoSangre()));
				Afiliado afiliado = agendaDao.getAfiliadoById(historiaClinicaPersonalesNoPatologicosForm.getIdAfiliado());
				afiliadodemografico.setAfiliado(afiliado);
				afiliadodemografico.setCatescolaridad(historiaClinicaPersonalesNoPatologicosDao.getCatEscolaridadById(historiaClinicaPersonalesNoPatologicosForm.getIdEscolaridad()));
				afiliadodemografico.setCatestadocivil(historiaClinicaPersonalesNoPatologicosDao.getCatEstadoCivilById(historiaClinicaPersonalesNoPatologicosForm.getIdEstadoCivil()));
				afiliadodemografico.setHablaEs(0);
				historiaClinicaPersonalesNoPatologicosDao.saveAfiliadoDemografico(afiliadodemografico);
				historiaClinicaPersonalesNoPatologicosForm.setIdDemografico(afiliadodemografico.getDemograficoId());
			}	
		}
		
		
	}
}
