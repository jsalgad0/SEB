package com.mx.sab.service.impl;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAtencionMedicaDao;
import com.mx.sab.dao.IInformacionPacienteDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.form.InformacionPacienteForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadoadicionales;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.model.Catsexos;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.service.IMedicoInformacionPacienteService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.InformacionPacienteVo;
import com.mx.sab.vo.TomaSignosVo;

@Service
@Log4j2
public class MedicoInformacionPacienteServiceImpl implements IMedicoInformacionPacienteService {
	
	@Autowired IAtencionMedicaDao atencionMedicaDao;
	@Autowired IAgendaDao agendaDao;
	@Autowired ISignosDao signosDao;
	@Autowired IInformacionPacienteDao informacionPacienteDao;
	
	@Override
	public Atencionmedica getAtencion(int idAtencion) {
		//log.info("afiliado");
		Atencionmedica atencionmedica = atencionMedicaDao.getAtencionMedicaById(idAtencion);
		return atencionmedica;
	}

	@Override
	public TomaSignosVo getSignos(int idAtencion) {
		TomaSignosVo tomaSignosVo = new TomaSignosVo();
		String nombre;
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(idAtencion);
		nombre = atencionmedica.getAfiliado().getNombre()+" "+atencionmedica.getAfiliado().getApellidoPaterno()+" "+atencionmedica.getAfiliado().getApellidoMaterno();
		
		long edad = FormatUtil.getEdad(atencionmedica.getAfiliado().getFechaDeNacimiento().getTime());
		String fechaNacimiento = atencionmedica.getAfiliado().getFechaDeNacimiento().toString().substring(8, 10)+"/"+atencionmedica.getAfiliado().getFechaDeNacimiento().toString().substring(5, 7)+"/"+atencionmedica.getAfiliado().getFechaDeNacimiento().toString().substring(0, 4);
		
		Object ultimosSignos = signosDao.getUltimosSignos(atencionmedica.getAfiliado().getAfiliadoId());
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
		
		tomaSignosVo.setIdAtencion(idAtencion);
		tomaSignosVo.setNombre(nombre);
		tomaSignosVo.setEdad(edad);
		tomaSignosVo.setGuardado(false);
		tomaSignosVo.setFechaNacimiento(fechaNacimiento);
			
		return tomaSignosVo;
	}

	@Override
	public InformacionPacienteVo getInformacionPaciente(int idAtencion) {
		InformacionPacienteVo informacionPacienteVo = new InformacionPacienteVo();
		
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(idAtencion);
		Afiliadodemografico afiliadodemografico = informacionPacienteDao.getDemografico(atencionmedica.getAfiliado());
		if(afiliadodemografico != null){
			informacionPacienteVo.setIdOcupacion(afiliadodemografico.getDemograficoId());
			informacionPacienteVo.setNacionalidad(afiliadodemografico.getNacionalidad());
			informacionPacienteVo.setReligion(afiliadodemografico.getReligion());
			informacionPacienteVo.setNivelSocioeconomico(afiliadodemografico.getNivelSocEco());
		}else{
			informacionPacienteVo.setIdOcupacion(-1);
		}
		Afiliadoadicionales afiliadoadicionales = informacionPacienteDao.getAdicionales(atencionmedica.getAfiliado());
		if(afiliadoadicionales != null){
			informacionPacienteVo.setNombreResponsable(afiliadoadicionales.getNombreResponsable());
			informacionPacienteVo.setParentescoResponsable(afiliadoadicionales.getParentescoResponsable());
			informacionPacienteVo.setEdadResponsable(afiliadoadicionales.getEdadResponsable());
			informacionPacienteVo.setDireccionResponsable(afiliadoadicionales.getDireccionResponsable());
			informacionPacienteVo.setLugarResponsable(afiliadoadicionales.getLugarResponsable());
			informacionPacienteVo.setTelefonoResponsable(afiliadoadicionales.getTelefonoResponsable());
		}		
		
		return informacionPacienteVo;
	}

	@Override
	public void updateInformacionPaciente(InformacionPacienteForm informacionPacienteForm) {
		
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(informacionPacienteForm.getIdAtencionMedica());
		Afiliado afiliado = atencionmedica.getAfiliado();
		if(informacionPacienteForm.getCalle() != null && informacionPacienteForm.getCalle().trim().length() > 0){
			afiliado.setCalle(informacionPacienteForm.getCalle());
		}
		if(informacionPacienteForm.getCodigoPostal() != null && informacionPacienteForm.getCodigoPostal().trim().length() > 0){
			afiliado.setCp(informacionPacienteForm.getCodigoPostal());
		}
		if(informacionPacienteForm.getTelefono() != null && informacionPacienteForm.getTelefono().trim().length() > 0){
			afiliado.setTelefono1(informacionPacienteForm.getTelefono());
		}
		if(informacionPacienteForm.getMail() != null && informacionPacienteForm.getMail().trim().length() > 0){
			afiliado.setMail(informacionPacienteForm.getMail());
		}
		if(informacionPacienteForm.getFechaNacimiento() != null){
			afiliado.setFechaDeNacimiento(informacionPacienteForm.getFechaNacimiento());
		}
		
		Catsexos catsexos = informacionPacienteDao.getSexo(informacionPacienteForm.getIdSexo());
		afiliado.setCatsexos(catsexos);
		
		if(informacionPacienteForm.getIdEstado() != -1 ){
			Catestados catestados = informacionPacienteDao.getEstadosById(informacionPacienteForm.getIdEstado());
			afiliado.setCatestadosByEstadoId(catestados);
		}
		if(informacionPacienteForm.getIdMunicipio() != -1 ){
			Catmunicipios catmunicipios = informacionPacienteDao.getMunicipiosById(informacionPacienteForm.getIdMunicipio());
			afiliado.setCatmunicipios(catmunicipios);
		}
		if(informacionPacienteForm.getIdColonia() != -1 ){
			Catcolonias catcolonias = informacionPacienteDao.getColoniasById(informacionPacienteForm.getIdColonia());
			afiliado.setCatcolonias(catcolonias);
		}
		
		informacionPacienteDao.updateAfiliado(afiliado);
		
		Afiliadodemografico afiliadodemografico = informacionPacienteDao.getDemografico(afiliado);
		if(afiliadodemografico == null){
			afiliadodemografico = new Afiliadodemografico();
			afiliadodemografico.setAfiliado(afiliado);
			afiliadodemografico.setHablaEs(1);
		}		
		if(informacionPacienteForm.getNacionalidad() != null && informacionPacienteForm.getNacionalidad().trim().length() > 0){
			afiliadodemografico.setNacionalidad(informacionPacienteForm.getNacionalidad());
		}
		if(informacionPacienteForm.getReligion() != null && informacionPacienteForm.getReligion().trim().length() > 0){
			afiliadodemografico.setReligion(informacionPacienteForm.getReligion());
		}
		if(informacionPacienteForm.getNivelSocioEconomico() != null && informacionPacienteForm.getNivelSocioEconomico().trim().length() > 0){
			afiliadodemografico.setNivelSocEco(informacionPacienteForm.getNivelSocioEconomico());
		}
		if(informacionPacienteForm.getIdOcupacion() != -1 ){
			Catocupacion catocupacion = informacionPacienteDao.getOcupacionById(informacionPacienteForm.getIdOcupacion());
			afiliadodemografico.setCatocupacion(catocupacion);
		}

		informacionPacienteDao.updateDemografico(afiliadodemografico);
		
		Afiliadoadicionales afiliadoadicionales = informacionPacienteDao.getAdicionales(afiliado);
		if(afiliadoadicionales == null){
			afiliadoadicionales = new Afiliadoadicionales();
			afiliadoadicionales.setAfiliado(afiliado);
		}
		if(informacionPacienteForm.getResponsableNombre() != null && informacionPacienteForm.getResponsableNombre().trim().length() > 0){
			afiliadoadicionales.setNombreResponsable(informacionPacienteForm.getResponsableNombre());
		}
		if(informacionPacienteForm.getResponsableParentezco() != null && informacionPacienteForm.getResponsableParentezco().trim().length() > 0){
			afiliadoadicionales.setParentescoResponsable(informacionPacienteForm.getResponsableParentezco());
		}
		if(informacionPacienteForm.getResponsableEdad() != null && informacionPacienteForm.getResponsableEdad().trim().length() > 0){
			afiliadoadicionales.setEdadResponsable(Integer.parseInt(informacionPacienteForm.getResponsableEdad()));
		}
		if(informacionPacienteForm.getResponsableDireccion() != null && informacionPacienteForm.getResponsableDireccion().trim().length() > 0){
			afiliadoadicionales.setDireccionResponsable(informacionPacienteForm.getResponsableDireccion());
		}
		if(informacionPacienteForm.getResponsableLugar() != null && informacionPacienteForm.getResponsableLugar().trim().length() > 0){
			afiliadoadicionales.setLugarResponsable(informacionPacienteForm.getResponsableLugar());
		}
		if(informacionPacienteForm.getResponsableTelefono() != null && informacionPacienteForm.getResponsableTelefono().trim().length() > 0){
			afiliadoadicionales.setTelefonoResponsable(informacionPacienteForm.getResponsableTelefono());
		}
		
		informacionPacienteDao.updateAdicional(afiliadoadicionales);
		
	}	
	
}
