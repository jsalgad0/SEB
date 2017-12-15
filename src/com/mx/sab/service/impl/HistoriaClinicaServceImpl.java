package com.mx.sab.service.impl;

import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.IHistoriaClinicaDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.form.HistoriaClincaForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcExploracionfisica;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.service.IHistoriaClinicaService;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class HistoriaClinicaServceImpl implements IHistoriaClinicaService {
	
	@Autowired private IHistoriaClinicaDao historiaClinicaDao;
	@Autowired private IGenericDao genericDao;
	@Autowired private ISignosDao signosDao;
	@Autowired private IAgendaDao agendaDao;

	@Override
	public Collection<Catalimentacion> getAlimentacion() {
		//log.info("catAlimentacion");
		Collection<Catalimentacion> alimentacion = historiaClinicaDao.getCatAlimentacion();
		return alimentacion;
	}
	
	@Override
	public Collection<Cathigiene> getHigiene() {
		Collection<Cathigiene> higiene = historiaClinicaDao.getCatHigiene();
		return higiene;
	}

	@Override
	public Collection<Catmpf> getMpf() {
		Collection<Catmpf> mpf = historiaClinicaDao.getMpf();
		return mpf;
	}

	@Override
	public Collection<Cattipofamilia> getFamilia() {
		Collection<Cattipofamilia> familia = historiaClinicaDao.getFamilia();
		return familia;
	}

	@Override
	public HistoriaClincaForm getHistoria(HistoriaClincaForm historiaClincaForm) {
		Agenda agenda = agendaDao.getAgendaById(historiaClincaForm.getIdAgenda());
		Historiaclinica historiaclinica = historiaClinicaDao.getHistoriaClinica(agenda.getAfiliado().getAfiliadoId());
		Signosvitales signosvitales = null;
		try{
//			int idSignos = signosDao.getUltimosSignos(agenda.getAfiliado().getAfiliadoId());
//			signosvitales = signosDao.getSignosById(idSignos);
		}catch(NullPointerException nullPointerException){
			nullPointerException.printStackTrace();
		}
		if(signosvitales == null){
			signosvitales = new Signosvitales();
			historiaClincaForm.setSignosvitales(signosvitales);
		}else{
			historiaClincaForm.setSignosvitales(signosvitales);
		}
		
		if(historiaclinica != null){
			if(historiaclinica.getHcAntecedentesfamiliareses() != null){
				Iterator<HcAntecedentesfamiliares> familiares = historiaclinica.getHcAntecedentesfamiliareses().iterator();
				while(familiares.hasNext()){
					HcAntecedentesfamiliares antecedentesfamiliares = familiares.next();
					historiaClincaForm.setHcAntecedentesfamiliares(antecedentesfamiliares);
				}
			}
			
			if(historiaclinica.getHcPersonalnopatologicoses() != null){
				Iterator<HcPersonalnopatologicos> noPatologicos = historiaclinica.getHcPersonalnopatologicoses().iterator();
				while(noPatologicos.hasNext()){
					HcPersonalnopatologicos personalnopatologicos = noPatologicos.next();
					if(personalnopatologicos.getCatestadocivil() != null){
						historiaClincaForm.setIdEstadoCivil(personalnopatologicos.getCatestadocivil().getEstadoCivilId());
					}
					if(personalnopatologicos.getCatescolaridad() != null){
						historiaClincaForm.setIdEscolaridad(personalnopatologicos.getCatescolaridad().getEscolaridadId());
					}
					if(personalnopatologicos.getCatalimentacion() != null){
						historiaClincaForm.setIdAlimentacion(personalnopatologicos.getCatalimentacion().getAlimentacionId());
					}
					if(personalnopatologicos.getCathigiene() != null){
						historiaClincaForm.setIdHigiene(personalnopatologicos.getCathigiene().getHigieneId());
					}					
				}
			}
			
			if(historiaclinica.getHcAntecedentespersonals() != null){
				Iterator<HcAntecedentespersonal> personal = historiaclinica.getHcAntecedentespersonals().iterator();
				while(personal.hasNext()){
					HcAntecedentespersonal antecedentespersonal = personal.next();
					historiaClincaForm.setHcAntecedentespersonal(antecedentespersonal);
				}
			}
			
			if(historiaclinica.getHcGinecos() != null){
				Iterator<HcGineco> gineco = historiaclinica.getHcGinecos().iterator();
				while(gineco.hasNext()){
					HcGineco hcGineco = gineco.next();
					if(hcGineco.getCatmpf() != null){
						historiaClincaForm.setIdMpf(hcGineco.getCatmpf().getMpfId());
					}					
					historiaClincaForm.setHcGineco(hcGineco);
				}
			}
			
			if(historiaclinica.getHcExploracionfisicas() != null){
				Iterator<HcExploracionfisica> exploracion = historiaclinica.getHcExploracionfisicas().iterator();
				while(exploracion.hasNext()){
					HcExploracionfisica hcExploracionfisica = exploracion.next();
					historiaClincaForm.setHcExploracionfisica(hcExploracionfisica);
				}
			}
			
			if(historiaclinica.getHcFasedelciclos() != null){
				Iterator<HcFasedelciclo> fase = historiaclinica.getHcFasedelciclos().iterator();
				while(fase.hasNext()){
					HcFasedelciclo faseCiclo = fase.next();
					historiaClincaForm.setHcFasedelciclo(faseCiclo);
					if(faseCiclo.getCattipofamilia() != null){
						historiaClincaForm.setIdTipoFamilia(faseCiclo.getCattipofamilia().getTipoFamiliaId());
					}					
				}
			}
		}
		return historiaClincaForm;
	}

	@Override
	public void saveHistoria(HistoriaClincaForm historiaClincaForm, UserInfo userInfo) {
		Atencionmedica atencionmedica = signosDao.getAtencionMedicaById(historiaClincaForm.getIdAtencion());
		Historiaclinica historiaclinica = null;
		if(atencionmedica.getAfiliado().getHistoriaclinicas() != null ){
			Iterator<Historiaclinica> historiaClinica = atencionmedica.getAfiliado().getHistoriaclinicas().iterator();
			while(historiaClinica.hasNext()){
				historiaclinica = historiaClinica.next();
			}
		}
		if(historiaclinica == null){
			historiaclinica = new Historiaclinica();
			historiaclinica.setAfiliado(atencionmedica.getAfiliado());
			historiaclinica.setUsuarios(userInfo.getUsuarios());
			historiaclinica.setFechaCreacion(atencionmedica.getFechaAsistio());
			historiaClinicaDao.saveHistoria(historiaclinica);
		}
		
		boolean existe = false;
		HcAntecedentespersonal hcAntecedentespersonal = null;
		Iterator<HcAntecedentespersonal> personal = historiaclinica.getHcAntecedentespersonals().iterator();
		while(personal.hasNext()){
			hcAntecedentespersonal = personal.next();
			existe = true;
		}
		if(!existe){
			hcAntecedentespersonal = new HcAntecedentespersonal();
			hcAntecedentespersonal.setHistoriaclinica(historiaclinica);
		}
		hcAntecedentespersonal.setAlcoholismoPer(historiaClincaForm.getHcAntecedentespersonal().getAlcoholismoPer());
		hcAntecedentespersonal.setAlergiasPer(historiaClincaForm.getHcAntecedentespersonal().getAlergiasPer());
		hcAntecedentespersonal.setCardiopatiasPer(historiaClincaForm.getHcAntecedentespersonal().getCardiopatiasPer());
		hcAntecedentespersonal.setDependenciaAmedicamentosPer(historiaClincaForm.getHcAntecedentespersonal().getDependenciaAmedicamentosPer());
		hcAntecedentespersonal.setDependencuAdrogaPer(historiaClincaForm.getHcAntecedentespersonal().getDependencuAdrogaPer());
		hcAntecedentespersonal.setDiabeteMellitusPer(historiaClincaForm.getHcAntecedentespersonal().getDiabeteMellitusPer());
		hcAntecedentespersonal.setDisfuncionesFamiliaresPer(historiaClincaForm.getHcAntecedentespersonal().getDisfuncionesFamiliaresPer());
		hcAntecedentespersonal.setHipertensionArterialPer(historiaClincaForm.getHcAntecedentespersonal().getHipertensionArterialPer());
		hcAntecedentespersonal.setMalformacionesPer(historiaClincaForm.getHcAntecedentespersonal().getMalformacionesPer());
		hcAntecedentespersonal.setNeoplasiasPer(historiaClincaForm.getHcAntecedentespersonal().getNeoplasiasPer());
		hcAntecedentespersonal.setObesidadPer(historiaClincaForm.getHcAntecedentespersonal().getObesidadPer());
		hcAntecedentespersonal.setOtrasPer(historiaClincaForm.getHcAntecedentespersonal().getOtrasPer());
		hcAntecedentespersonal.setQuirurgiasPer(historiaClincaForm.getHcAntecedentespersonal().getQuirurgiasPer());
		hcAntecedentespersonal.setTabaquismoPer(historiaClincaForm.getHcAntecedentespersonal().getTabaquismoPer());
		hcAntecedentespersonal.setTuberculosisPer(historiaClincaForm.getHcAntecedentespersonal().getTuberculosisPer());
		if(existe){
			historiaClinicaDao.updateHcPersonales(hcAntecedentespersonal);
		}else{
			historiaClinicaDao.saveHcPersonales(hcAntecedentespersonal);
		}
		
		existe = false;
		HcAntecedentesfamiliares hcAntecedentesfamiliares = null;
		Iterator<HcAntecedentesfamiliares> familiares = historiaclinica.getHcAntecedentesfamiliareses().iterator();
		while(familiares.hasNext()){
			hcAntecedentesfamiliares = familiares.next();
			existe = true;
		}
		if(!existe){
			hcAntecedentesfamiliares = new HcAntecedentesfamiliares();
			hcAntecedentesfamiliares.setHistoriaclinica(historiaclinica);
		}
		hcAntecedentesfamiliares.setAlcoholismo(historiaClincaForm.getHcAntecedentesfamiliares().getAlcoholismo());
		hcAntecedentesfamiliares.setAlergias(historiaClincaForm.getHcAntecedentesfamiliares().getAlergias());
		hcAntecedentesfamiliares.setCardiopatias(historiaClincaForm.getHcAntecedentesfamiliares().getCardiopatias());
		hcAntecedentesfamiliares.setDependenciaAdroga(historiaClincaForm.getHcAntecedentesfamiliares().getDependenciaAdroga());
		hcAntecedentesfamiliares.setDependenciaAmedicamentos(historiaClincaForm.getHcAntecedentesfamiliares().getDependenciaAmedicamentos());
		hcAntecedentesfamiliares.setDiabeteMellitus(historiaClincaForm.getHcAntecedentesfamiliares().getDiabeteMellitus());
		hcAntecedentesfamiliares.setDisfuncionesFamiliares(historiaClincaForm.getHcAntecedentesfamiliares().getDisfuncionesFamiliares());
		hcAntecedentesfamiliares.setHipertensionArterial(historiaClincaForm.getHcAntecedentesfamiliares().getHipertensionArterial());
		hcAntecedentesfamiliares.setMalformaciones(historiaClincaForm.getHcAntecedentesfamiliares().getMalformaciones());
		hcAntecedentesfamiliares.setNeoplasias(historiaClincaForm.getHcAntecedentesfamiliares().getNeoplasias());
		hcAntecedentesfamiliares.setObesidad(historiaClincaForm.getHcAntecedentesfamiliares().getObesidad());
		hcAntecedentesfamiliares.setOtras(historiaClincaForm.getHcAntecedentesfamiliares().getOtras());
		hcAntecedentesfamiliares.setQuirurgias(historiaClincaForm.getHcAntecedentesfamiliares().getQuirurgias());
		hcAntecedentesfamiliares.setTabaquismo(historiaClincaForm.getHcAntecedentesfamiliares().getTabaquismo());
		hcAntecedentesfamiliares.setTuberculosis(historiaClincaForm.getHcAntecedentesfamiliares().getTuberculosis());
		if (existe) {
			historiaClinicaDao.updateHcFamiliares(hcAntecedentesfamiliares);
		}else{
			historiaClinicaDao.saveHcFamiliares(hcAntecedentesfamiliares);
		}
		
		existe = false;
		HcPersonalnopatologicos hcPersonalnopatologicos = null;
		Iterator<HcPersonalnopatologicos> noPatologicos = historiaclinica.getHcPersonalnopatologicoses().iterator();
		while(noPatologicos.hasNext()){
			hcPersonalnopatologicos = noPatologicos.next();
			existe = true;
		}
		if(!existe){
			hcPersonalnopatologicos = new HcPersonalnopatologicos();
			hcPersonalnopatologicos.setHistoriaclinica(historiaclinica);
		}
		Catalimentacion catalimentacion = historiaClinicaDao.getAlimentacionById(historiaClincaForm.getIdAlimentacion());
		hcPersonalnopatologicos.setCatalimentacion(catalimentacion);
		Cathigiene cathigiene = historiaClinicaDao.getHigieneById(historiaClincaForm.getIdHigiene());
		hcPersonalnopatologicos.setCathigiene(cathigiene);
		Catescolaridad catescolaridad = genericDao.getEscolaridadById(historiaClincaForm.getIdEscolaridad());
		hcPersonalnopatologicos.setCatescolaridad(catescolaridad);
		Catestadocivil catestadocivil = genericDao.getEstadoCivilById(historiaClincaForm.getIdEstadoCivil());
		hcPersonalnopatologicos.setCatestadocivil(catestadocivil);
		if(existe){
			historiaClinicaDao.updateHcNoPatologicos(hcPersonalnopatologicos);
		}else{
			historiaClinicaDao.saveHcNoPatologicos(hcPersonalnopatologicos);
		}
		
		existe = false;
		HcGineco hcGineco = null;
		Iterator<HcGineco> gineco = historiaclinica.getHcGinecos().iterator();
		while(gineco.hasNext()){
			hcGineco = gineco.next();
			existe = true;
		}
		if(!existe){
			hcGineco = new HcGineco();
			hcGineco.setHistoriaclinica(historiaclinica);
		}
		Catmpf catmpf = historiaClinicaDao.getMpfById(historiaClincaForm.getIdMpf());
		hcGineco.setCatmpf(catmpf);
		hcGineco.setA(historiaClincaForm.getHcGineco().getA());
		hcGineco.setC(historiaClincaForm.getHcGineco().getC());
		hcGineco.setFur(historiaClincaForm.getHcGineco().getFur());
		hcGineco.setG(historiaClincaForm.getHcGineco().getG());
		hcGineco.setMenarca(historiaClincaForm.getHcGineco().getMenarca());
		hcGineco.setP(historiaClincaForm.getHcGineco().getP());
		hcGineco.setRitmo(historiaClincaForm.getHcGineco().getRitmo());
		hcGineco.setVsa(historiaClincaForm.getHcGineco().getVsa());
		if(existe){
			historiaClinicaDao.updateGineco(hcGineco);
		}else{
			historiaClinicaDao.saveGineco(hcGineco);
		}
		
		existe = false;
		Signosvitales signosvitales = null;
		try{
			signosvitales = signosDao.getSignosByAtencion(atencionmedica.getAtencionMedicaId());
		}catch(NullPointerException nullPointerException){
			nullPointerException.printStackTrace();
		}		
		if(signosvitales == null){
			signosvitales = new Signosvitales();
		}else{
			existe = true;
		}
		signosvitales.setPeso(historiaClincaForm.getSignosvitales().getPeso());
		signosvitales.setAltura(historiaClincaForm.getSignosvitales().getAltura());
		signosvitales.setTensionArterial(historiaClincaForm.getSignosvitales().getTensionArterial());
		signosvitales.setTemperatura(historiaClincaForm.getSignosvitales().getTemperatura());
		if (existe) {
			signosDao.update(signosvitales);
		} else {
			signosvitales.setAtencionmedica(atencionmedica);
			signosvitales.setUsuarios(userInfo.getUsuarios());
			signosvitales.setAfiliado(atencionmedica.getAfiliado());
			signosvitales.setFechaSignos(atencionmedica.getFechaAsistio());
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
			signosDao.save(signosvitales);
		}
		
		existe = false;
		HcExploracionfisica hcExploracionfisica = null;
		Iterator<HcExploracionfisica> exploracion = historiaclinica.getHcExploracionfisicas().iterator();
		while (exploracion.hasNext()) {
			hcExploracionfisica = (HcExploracionfisica) exploracion.next();
			existe = true;
		}
		if(!existe){
			hcExploracionfisica = new HcExploracionfisica();
			hcExploracionfisica.setHistoriaclinica(historiaclinica);
		}
		hcExploracionfisica.setAbdomen(historiaClincaForm.getHcExploracionfisica().getAbdomen());
		hcExploracionfisica.setAparatoDigestivo(historiaClincaForm.getHcExploracionfisica().getAparatoDigestivo());
		hcExploracionfisica.setCabeza(historiaClincaForm.getHcExploracionfisica().getCabeza());
		hcExploracionfisica.setCuello(historiaClincaForm.getHcExploracionfisica().getCuello());
		hcExploracionfisica.setExtremidades(historiaClincaForm.getHcExploracionfisica().getExtremidades());
		hcExploracionfisica.setOtrasEf(historiaClincaForm.getHcExploracionfisica().getOtrasEf());
		hcExploracionfisica.setSistemaCardio(historiaClincaForm.getHcExploracionfisica().getSistemaCardio());
		hcExploracionfisica.setSistemaMusculo(historiaClincaForm.getHcExploracionfisica().getSistemaMusculo());
		hcExploracionfisica.setSistemaNervioso(historiaClincaForm.getHcExploracionfisica().getSistemaNervioso());
		hcExploracionfisica.setTorax(historiaClincaForm.getHcExploracionfisica().getTorax());
		if(existe){
			historiaClinicaDao.updateHcExploracion(hcExploracionfisica);
		}else{
			historiaClinicaDao.saveHcExploracion(hcExploracionfisica);
		}
		
		existe = false;
		HcFasedelciclo hcFasedelciclo = null;
		Iterator<HcFasedelciclo> fase = historiaclinica.getHcFasedelciclos().iterator();
		while(fase.hasNext()){
			hcFasedelciclo = fase.next();
			existe = true;
		}
		if(!existe){
			hcFasedelciclo = new HcFasedelciclo();
			hcFasedelciclo.setHistoriaclinica(historiaclinica);
		}
		Cattipofamilia cattipofamilia = historiaClinicaDao.getFamiliaById(historiaClincaForm.getIdTipoFamilia());
		hcFasedelciclo.setCattipofamilia(cattipofamilia);
		hcFasedelciclo.setDispersion(historiaClincaForm.getHcFasedelciclo().getDispersion());
		hcFasedelciclo.setExpansion(historiaClincaForm.getHcFasedelciclo().getExpansion());
		hcFasedelciclo.setIndependencia(historiaClincaForm.getHcFasedelciclo().getIndependencia());
		hcFasedelciclo.setMatrimonio(historiaClincaForm.getHcFasedelciclo().getMatrimonio());
		hcFasedelciclo.setRetiroMuerte(historiaClincaForm.getHcFasedelciclo().getRetiroMuerte());
		if (existe) {
			historiaClinicaDao.updateHcFase(hcFasedelciclo);
		} else {
			historiaClinicaDao.saveHcFase(hcFasedelciclo);
		}
		
	}

}
