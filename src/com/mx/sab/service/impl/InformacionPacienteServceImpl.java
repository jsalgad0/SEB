package com.mx.sab.service.impl;

import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.IInformacionPacienteDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.form.InformacionPacienteForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadoadicionales;
import com.mx.sab.model.Afiliadodemografico;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catcolonias;
import com.mx.sab.model.Catescolaridad;
import com.mx.sab.model.Catestadocivil;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Catmunicipios;
import com.mx.sab.model.Catocupacion;
import com.mx.sab.service.IInformacionPacienteService;

@Service
@Log4j2
@Repository("informacionPacienteService")
@Transactional(readOnly = false)
public class InformacionPacienteServceImpl implements IInformacionPacienteService {
	
	@Autowired private ISignosDao signosDao;
	@Autowired private IInformacionPacienteDao informacionPacienteDao;
	@Autowired private IGenericDao genericDao;
	@Autowired private IAgendaDao agendaDao;
	
	@Override
	public InformacionPacienteForm getForm(InformacionPacienteForm form) {
		//log.info("informacionPaciente");
		InformacionPacienteForm informacionPacienteForm = new InformacionPacienteForm();
		informacionPacienteForm.setIdAgenda(form.getIdAgenda());
		Agenda agenda = agendaDao.getAgendaById(form.getIdAgenda());
		form.setIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
		Atencionmedica atencionmedica = signosDao.getAtencionMedicaById(form.getIdAtencionMedica());
		Afiliado afiliado = atencionmedica.getAfiliado();
		
		if(afiliado.getCatestadosByEstadoId() != null){
			informacionPacienteForm.setIdEstado(afiliado.getCatestadosByEstadoId().getEstadoId());
		}
		if(afiliado.getCatmunicipios() != null){
			informacionPacienteForm.setIdMunicipio(afiliado.getCatmunicipios().getMunicipioId());
		}
		if(afiliado.getCatcolonias() != null){
			informacionPacienteForm.setIdColonia(afiliado.getCatcolonias().getColoniaId());
		}		
		informacionPacienteForm.setCalle(afiliado.getCalle());
		informacionPacienteForm.setNumInt(afiliado.getNoInterior());
		informacionPacienteForm.setNumExt(afiliado.getNoExterior());
		if(afiliado.getCp() != null){
			informacionPacienteForm.setCodigoPostal(""+afiliado.getCp());
		}
		informacionPacienteForm.setTelefono(afiliado.getTelefono1());
		informacionPacienteForm.setMail(afiliado.getMail());
		
		Iterator<Afiliadodemografico> demografico = afiliado.getAfiliadodemograficos().iterator();
		while(demografico.hasNext()){
			informacionPacienteForm.setEditar(true);
			Afiliadodemografico afiliadodemografico = demografico.next();
			if(afiliadodemografico.getCatestadocivil() != null){
				informacionPacienteForm.setIdEstadoCivil(afiliadodemografico.getCatestadocivil().getEstadoCivilId());
			}
			if(afiliadodemografico.getCatescolaridad() != null){
				informacionPacienteForm.setIdEscolaridad(afiliadodemografico.getCatescolaridad().getEscolaridadId());
			}
			if(afiliadodemografico.getCatocupacion() != null){
				informacionPacienteForm.setIdOcupacion(afiliadodemografico.getCatocupacion().getOcupacionId());
			}			
			informacionPacienteForm.setNacionalidad(afiliadodemografico.getNacionalidad());
			informacionPacienteForm.setReligion(afiliadodemografico.getReligion());
			informacionPacienteForm.setNivelSocioEconomico(afiliadodemografico.getNivelSocEco());
			if(afiliadodemografico.getCatgrupossanguineos() != null){
				informacionPacienteForm.setIdGrupoSanguineo(afiliadodemografico.getCatgrupossanguineos().getGrupoSanguineoId());
			}			
			informacionPacienteForm.setTipoSanguineo(afiliadodemografico.getRhpositivo());
			informacionPacienteForm.setIdInmigrante(afiliadodemografico.getInmigrante());
			informacionPacienteForm.setIdIndigena(afiliadodemografico.getIndigena());
		}
		
		Iterator<Afiliadoadicionales> adicionales = afiliado.getAfiliadoadicionaleses().iterator();
		while(adicionales.hasNext()){
			informacionPacienteForm.setEditar(true);
			Afiliadoadicionales afiliadoadicionales = adicionales.next();
			informacionPacienteForm.setResponsableNombre(afiliadoadicionales.getNombreResponsable());
			informacionPacienteForm.setResponsableEdad(""+afiliadoadicionales.getEdadResponsable());
			informacionPacienteForm.setResponsableParentezco(afiliadoadicionales.getParentescoResponsable());
			informacionPacienteForm.setResponsableDireccion(afiliadoadicionales.getDireccionResponsable());
			informacionPacienteForm.setResponsableLugar(afiliadoadicionales.getLugarResponsable());
			informacionPacienteForm.setResponsableTelefono(afiliadoadicionales.getTelefonoResponsable());
		}
		return informacionPacienteForm;
	}

	@Override
	public Collection<Catocupacion> getOcupacion() {
		Collection<Catocupacion> catocupacion = informacionPacienteDao.getOcupacion();
		return catocupacion;
	}

	@Override
	public Collection<Catgrupossanguineos> getGrupoSanguineo() {
		Collection<Catgrupossanguineos> catgrupossanguineos = informacionPacienteDao.getGrupoSanguineo();
		return catgrupossanguineos;
	}

	@Override
	public void save(InformacionPacienteForm form) {
		Agenda agenda = agendaDao.getAgendaById(form.getIdAgenda());
		Atencionmedica atencionmedica = agenda.getAtencionmedica();
		Afiliado afiliado = atencionmedica.getAfiliado();
		Catestados catestados = genericDao.getEstadoById(form.getIdEstado());
		afiliado.setCatestadosByEstadoId(catestados);
		Catmunicipios catmunicipios = genericDao.getMunicipioById(form.getIdMunicipio());
		afiliado.setCatmunicipios(catmunicipios);
		Catcolonias catcolonias = genericDao.getColoniaById(form.getIdColonia());
		afiliado.setCatcolonias(catcolonias);
		afiliado.setCalle(form.getCalle());
		afiliado.setNoInterior(form.getNumInt());
		afiliado.setNoExterior(form.getNumExt());
		afiliado.setCp(form.getCodigoPostal());
		afiliado.setMail(form.getMail());
		afiliado.setTelefono1(form.getTelefono());
		System.out.println("---------- "+afiliado.getCalle());
		informacionPacienteDao.updateAfiliado(afiliado);
		
		Afiliadodemografico afiliadodemografico = informacionPacienteDao.getDemografico(afiliado); 
		if(afiliadodemografico == null){
			afiliadodemografico = new Afiliadodemografico(); 
		}		
		afiliadodemografico.setAfiliado(afiliado);
		Catescolaridad catescolaridad = genericDao.getEscolaridadById(form.getIdEscolaridad());
		afiliadodemografico.setCatescolaridad(catescolaridad);
		Catestadocivil catestadocivil = genericDao.getEstadoCivilById(form.getIdEstadoCivil());
		afiliadodemografico.setCatestadocivil(catestadocivil);
		Catgrupossanguineos catgrupossanguineos = informacionPacienteDao.getGrupoSanguineoById(form.getIdGrupoSanguineo());
		afiliadodemografico.setCatgrupossanguineos(catgrupossanguineos);
		Catocupacion catocupacion = informacionPacienteDao.getOcupacionById(form.getIdOcupacion());
		afiliadodemografico.setCatocupacion(catocupacion);
		afiliadodemografico.setIndigena(form.getIdIndigena());
		afiliadodemografico.setInmigrante(form.getIdInmigrante());
		afiliadodemografico.setNacionalidad(form.getNacionalidad());
		afiliadodemografico.setNivelSocEco(form.getNivelSocioEconomico());
		afiliadodemografico.setReligion(form.getReligion());
		afiliadodemografico.setRhpositivo(form.getTipoSanguineo());
		afiliadodemografico.setHablaEs(1);
		if(afiliadodemografico.getDemograficoId() != null){
			informacionPacienteDao.updateDemografico(afiliadodemografico);
		}else{
			informacionPacienteDao.saveDemografico(afiliadodemografico);
		}
		
		Afiliadoadicionales afiliadoadicionales = informacionPacienteDao.getAdicionales(afiliado);
		if(afiliadoadicionales == null){
			afiliadoadicionales = new Afiliadoadicionales(); 
		}				
		afiliadoadicionales.setAfiliado(afiliado);
		afiliadoadicionales.setDireccionResponsable(form.getResponsableDireccion());
		if(form.getResponsableEdad() != null && form.getResponsableEdad().replaceAll(" ", "").length() > 0){
			afiliadoadicionales.setEdadResponsable(Integer.parseInt(form.getResponsableEdad()));
		}
		afiliadoadicionales.setLugarResponsable(form.getResponsableLugar());
		afiliadoadicionales.setParentescoResponsable(form.getResponsableParentezco());
		afiliadoadicionales.setTelefonoResponsable(form.getResponsableTelefono());
		afiliadoadicionales.setNombreResponsable(form.getResponsableNombre());
		if(afiliadoadicionales.getAfiliadoAdicionalesId() != null){
			informacionPacienteDao.updateAdicional(afiliadoadicionales);
		}else{
			informacionPacienteDao.saveAdicional(afiliadoadicionales);
		}		
		
	}
	
}
