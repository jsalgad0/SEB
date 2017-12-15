package com.mx.sab.service.impl;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IConveniosDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.IIsssteDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.AgendadoPorEnum;
import com.mx.sab.enums.EstatusCitasEnum;
import com.mx.sab.enums.RolesEnum;
import com.mx.sab.enums.TipoAfiliadoEnum;
import com.mx.sab.enums.TipoIdentificadorEnum;
import com.mx.sab.form.AgendaForm;
import com.mx.sab.form.AtencionMedicaForm;
import com.mx.sab.form.PersonaConfianzaForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadopersonaconfianza;
import com.mx.sab.model.AfiliadopersonaconfianzaId;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.AtencionprestacionId;
import com.mx.sab.model.Catagendadopor;
import com.mx.sab.model.Catestatuscitas;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.ConvenioCuadroprestaciones;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.Personasdeconfianza;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IAgendaService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AfiliadoIsssteVo;
import com.mx.sab.vo.AfiliadoVo;
import com.mx.sab.vo.AgendaVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class AgendaServiceImpl implements IAgendaService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IGenericDao genericDao;
	
	@Autowired
	private IConveniosDao conveniosDao;
	
	@Autowired
	private IPrestacionesDao prestacionesDao;
	
	@Autowired
	private IIsssteDao isssteDao;

	@Override
	public Collection<Agenda> getAgenda(AgendaForm agendaForm, UserInfo userInfo) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<Agenda> agendas = null;
		
		if (agendaForm==null) {
			agendaForm = new AgendaForm();
		}
		
		if (agendaForm.getBusquedaM()==null && agendaForm.getBusquedaA()==null && agendaForm.getBusquedaF()==null && agendaForm.getBusquedaE()==null) {
			agendaForm.setBusquedaM("-1");
			agendaForm.setBusquedaA("");
			agendaForm.setBusquedaF(FormatUtil.getDate());
			agendaForm.setBusquedaE("0");
		}
		
		int totalLugarAtencion = 0;
		if (agendaForm.getBusquedaM().equals("-1")) {
			totalLugarAtencion = agendaDao.getAgendaCountSinMedico(agendaForm.getBusquedaA(), FormatUtil.getDate(agendaForm.getBusquedaF()), agendaForm.getBusquedaE(), userInfo.getTx_Marca());	
		}else {
			totalLugarAtencion = agendaDao.getAgendaCountConMedico(Integer.parseInt(agendaForm.getBusquedaM()), agendaForm.getBusquedaA(), FormatUtil.getDate(agendaForm.getBusquedaF()), agendaForm.getBusquedaE(), userInfo.getTx_Marca());
		}
		
		if (totalLugarAtencion>0) {
			paginasTotal = totalLugarAtencion / filas;
			if (totalLugarAtencion % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				agendaForm.setDisplay(7);
			}else {
				agendaForm.setDisplay(paginasTotal);
			}
			
			agendaForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			if (agendaForm.getBusquedaM().equals("-1")) {
				agendas = agendaDao.getAgendaSinMedico(agendaForm.getBusquedaA(), FormatUtil.getDate(agendaForm.getBusquedaF()), agendaForm.getBusquedaE(), inicio, fin, userInfo.getTx_Marca());	
			}else {
				agendas = agendaDao.getAgendaConMedico(Integer.parseInt(agendaForm.getBusquedaM()), agendaForm.getBusquedaA(), FormatUtil.getDate(agendaForm.getBusquedaF()), agendaForm.getBusquedaE(), inicio, fin, userInfo.getTx_Marca());
			}
			
		}else {
			//log.info("No hay agenda");
		}
		return agendas;
	}

	@Override
	public Collection<AgendaVo> getAgenda(String busquedaM, String busquedaA,
			String busquedaF, String busquedaE, int page, UserInfo userInfo) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<AgendaVo> agendaVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Agenda> agendas = null; 

		if (busquedaM.equals("-1")) {
			agendas = agendaDao.getAgendaSinMedico(busquedaA, FormatUtil.getDate(busquedaF), busquedaE, inicio, fin, userInfo.getTx_Marca());	
		}else {
			agendas = agendaDao.getAgendaConMedico(Integer.parseInt(busquedaM), busquedaA, FormatUtil.getDate(busquedaF), busquedaE, inicio, fin, userInfo.getTx_Marca());
		}
		
		for (Agenda agenda : agendas) {
			AgendaVo agendaVo = new AgendaVo();
			agendaVo.setAgendaId(agenda.getAgendaId());
			agendaVo.setAsistio(agenda.getAsistio());
			agendaVo.setConsultorio(agenda.getConsultorio());
			agendaVo.setFechaCita(agenda.getFechaCita());
			agendaVo.setHoraCita(agenda.getHoraCita());
			AfiliadoVo afiliadoVo = new AfiliadoVo();
			afiliadoVo.setAfiliadoId(agenda.getAfiliado().getAfiliadoId());
			afiliadoVo.setApellidoMaterno(agenda.getAfiliado().getApellidoMaterno());
			afiliadoVo.setApellidoPaterno(agenda.getAfiliado().getApellidoPaterno());
			afiliadoVo.setNombre(agenda.getAfiliado().getNombre());
			agendaVo.setAfiliado(afiliadoVo);
			agendaVos.add(agendaVo);
		}
		
		
		return agendaVos;
	}

	@Override
	public Collection<Usuarios> getMedicos(UserInfo userInfo) {
		Collection<Usuariolugaratencion> usuariolugaratencions = usuarioDao.getUsuariosByLugarAtencion(userInfo.getTx_Marca());
		Collection<Usuarios> usuarios = new ArrayList<>();
		for (Usuariolugaratencion usuariolugaratencion : usuariolugaratencions) {
			Iterator<Usuariorol> iteratorUsuarioRol = usuariolugaratencion.getUsuarios().getUsuariorols().iterator();
			while (iteratorUsuarioRol.hasNext()) {
				Usuariorol usuariorol = (Usuariorol) iteratorUsuarioRol.next();
				if (usuariorol.getRoles().getRolId() == RolesEnum.MEDICO.getId()) {
					usuarios.add(usuariorol.getUsuarios());
					break;
				}
			}
		}
		return usuarios;
	}

	@Override
	public void save(PersonaConfianzaForm personaConfianzaForm) {
		//log.info("save");
		try{
			personaConfianzaForm.getPersonasdeconfianza().setCattipoidentificador(genericDao.getCatTipoIdentificadorById(personaConfianzaForm.getIdTipoIdentificador()));
			if (personaConfianzaForm.getIdTipoIdentificador() == 10) {
				personaConfianzaForm.getPersonasdeconfianza().setValorTipoIdentificador("");
			}
			personaConfianzaForm.getPersonasdeconfianza().setActivo(1);
			personaConfianzaForm.getPersonasdeconfianza().setFechaNacimiento(FormatUtil.getDate(personaConfianzaForm.getFechaNacimiento()));
			personaConfianzaForm.getPersonasdeconfianza().setCatsexos(genericDao.getCatSexos(personaConfianzaForm.getIdSexo()));
			agendaDao.savePersonaConfianza(personaConfianzaForm.getPersonasdeconfianza());
			Afiliadopersonaconfianza afiliadopersonaconfianza = new Afiliadopersonaconfianza();
			AfiliadopersonaconfianzaId afiliadopersonaconfianzaId = new AfiliadopersonaconfianzaId();
			if (personaConfianzaForm.getIdAfiliado()!=0) {
				afiliadopersonaconfianzaId.setAfiliadoId(personaConfianzaForm.getIdAfiliado());
			}else {
				Agenda agenda = agendaDao.getAgendaById(personaConfianzaForm.getIdAgenda());
				afiliadopersonaconfianzaId.setAfiliadoId(agenda.getAfiliado().getAfiliadoId());				
			}

			afiliadopersonaconfianza.setEsAcompaniante(1);
			afiliadopersonaconfianza.setFechaRegistro(new Date());
			afiliadopersonaconfianzaId.setPersonaId(personaConfianzaForm.getPersonasdeconfianza().getPersonaId());
			afiliadopersonaconfianza.setId(afiliadopersonaconfianzaId);
			agendaDao.saveAfiliadoPersonaConfianza(afiliadopersonaconfianza);
		}catch(Exception ex){
			ex.printStackTrace();
			personaConfianzaForm.setBanderaError(true);
			personaConfianzaForm.setError("Surgio un error");
		}
	}

	@Override
	public Collection<Personasdeconfianza> getPersonasConfianza(PersonaConfianzaForm personaConfianzaForm) {
		Afiliado afiliado = null;
		if (personaConfianzaForm.getIdAgenda()!=0) {
			Agenda agenda = agendaDao.getAgendaById(personaConfianzaForm.getIdAgenda());
			afiliado = agenda.getAfiliado();	
		}else {
			afiliado = agendaDao.getAfiliadoById(personaConfianzaForm.getIdAfiliado());
		}
		personaConfianzaForm.setIdSexo(1);
		
		Collection<Personasdeconfianza> personasdeconfianzas = new ArrayList<>();
		Collection<Afiliadopersonaconfianza> afiliadopersonaconfianzas = null;
		
		afiliadopersonaconfianzas = agendaDao.getAfiliadoPersonasConfianza(afiliado.getAfiliadoId());
		for (Afiliadopersonaconfianza afiliadopersonaconfianza : afiliadopersonaconfianzas) {
			personasdeconfianzas.add(afiliadopersonaconfianza.getPersonasdeconfianza());
		}
		return personasdeconfianzas;
	}

	@Override
	public void inicializaAtencionMedicaForm(AtencionMedicaForm atencionMedicaForm) {
		Agenda agenda = agendaDao.getAgendaById(atencionMedicaForm.getIdAgenda());
		atencionMedicaForm.setAgenda(agenda);
		Collection<Convenios> convenios = conveniosDao.getConveniosByAgenda(agenda);
		atencionMedicaForm.setConvenios(convenios);
		
		if (atencionMedicaForm.getAgenda().getAfiliado().getCattipoafiliado().getTipoAfiliadoId() == TipoAfiliadoEnum.TRABAJADOR.getId() || atencionMedicaForm.getAgenda().getAfiliado().getCattipoafiliado().getTipoAfiliadoId() == TipoAfiliadoEnum.TRABAJADORA.getId()) {
			Iterator<Afiliadotipoidentificador> iterator = atencionMedicaForm.getAgenda().getAfiliado().getAfiliadotipoidentificadors().iterator();
			while (iterator.hasNext()) {
				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) iterator.next();
				if (afiliadotipoidentificador.getId().getTipoIdentificadorId() == TipoIdentificadorEnum.NUMISSSTE.getId()) {
					atencionMedicaForm.setAfiliadotipoidentificador(afiliadotipoidentificador);	
					atencionMedicaForm.setTipoAfiliado("D");
					break;
				}
			}	
		}else {
			Iterator<Afiliadotipoidentificador> iterator = atencionMedicaForm.getAgenda().getAfiliado().getAfiliadotipoidentificadors().iterator();
			while (iterator.hasNext()) {
				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) iterator.next();
				if (afiliadotipoidentificador.getId().getTipoIdentificadorId() == TipoIdentificadorEnum.NUMBENEFICIARIOISSSTE.getId()) {
					atencionMedicaForm.setAfiliadotipoidentificador(afiliadotipoidentificador);
					atencionMedicaForm.setTipoAfiliado("B");
					break;
				}
			}
		}
		
		Collection<AfiliadoIsssteVo> afiliadoIsssteVos = null;;
		try {
			afiliadoIsssteVos = isssteDao.getAfiliado(atencionMedicaForm.getAfiliadotipoidentificador().getCattipoidentificador().getTipoIdentificadorId(), 
													  agenda.getAfiliado().getNombre(), 
													  agenda.getAfiliado().getApellidoPaterno(), 
													  agenda.getAfiliado().getApellidoMaterno(), 
													  atencionMedicaForm.getAfiliadotipoidentificador().getCattipoidentificador().getTipoIdentificador());
			if (afiliadoIsssteVos.size()>0) {
				for (AfiliadoIsssteVo afiliadoIsssteVo : afiliadoIsssteVos) {
					if (!afiliadoIsssteVo.getVigencia().equals("A")) {
						atencionMedicaForm.setError("El usuario no se encuentra vigente");	
					}
				}	
			}else {
				atencionMedicaForm.setError("El usuario no se encuentra vigente");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		if (atencionMedicaForm.getAgenda().getPrestadores().getUsarTablaPrestacionesSab() == 1) {
			Catprestacion catprestacion = prestacionesDao.getCatPrestacion(atencionMedicaForm.getAgenda().getPrestacion());
			atencionMedicaForm.setCatPrestacion(catprestacion);
		}else {
			Prestacionprestador prestacionprestador = prestacionesDao.getPrestacionPrestador(atencionMedicaForm.getAgenda().getPrestacion());
			atencionMedicaForm.setPrestacionprestador(prestacionprestador);
		}
		
		atencionMedicaForm.setFechaDeNacimiento(FormatUtil.getDate(agenda.getAfiliado().getFechaDeNacimiento()));
		
		Afiliadotipoidentificador afiliadotipoidentificador = genericDao.getAfiliadoTipoIdentificador(agenda.getAfiliado().getAfiliadoId(), TipoIdentificadorEnum.RFC.getId());
		atencionMedicaForm.setRfc(afiliadotipoidentificador.getTipoIdValor());
		atencionMedicaForm.setDato(""+agenda.getAfiliado().getAfiliadoId());
		atencionMedicaForm.setTipoDato("AfiliadoId");
		
		long diffyears = FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime());
		if (diffyears<5) {
			atencionMedicaForm.setMenorDeEdad(true);
		}

	}

	@Override
	public void save(AtencionMedicaForm atencionMedicaForm, UserInfo userInfo) {
		try{
			Agenda agenda = agendaDao.getAgendaById(atencionMedicaForm.getIdAgenda());
			
			int idPrestacion = 0;
			if (atencionMedicaForm.getPrestacionprestador()!=null) {
				Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = prestacionesDao.getPrestacionesPrestadorEquivalencias(atencionMedicaForm.getPrestacionprestador().getPrestacionPrestadorId());
				idPrestacion = prestacionesprestadorequivalencias.getId().getPrestacionSabid();
			}else {
				idPrestacion = atencionMedicaForm.getCatPrestacion().getPrestacionId();
			}
			
			ConvenioCuadroprestaciones convenioCuadroprestaciones = agendaDao.getConvenioCuadroPrestaciones(atencionMedicaForm.getIdConvenio());
			
			if (agendaDao.getCuadroPrestacionPrestacion(convenioCuadroprestaciones.getCuadroprestaciones().getCuadroPrestacionId(), idPrestacion)!=null) {
				if (atencionMedicaForm.getIdPersona()==0) {
					Atencionmedica atencionmedica = new Atencionmedica();
					atencionmedica.setAfiliado(agenda.getAfiliado());
					atencionmedica.setAseguradores(agenda.getAseguradores());
					Catagendadopor catagendadopor = agendaDao.getCatAgendadoPor(AgendadoPorEnum.WC.getId());//
					atencionmedica.setCatagendadopor(catagendadopor);
					Catestatuscitas catestatuscitas = agendaDao.getCatEstatusCitas(EstatusCitasEnum.A.getId());//
					atencionmedica.setCatestatuscitas(catestatuscitas);
					Cattipoidentificador cattipoidentificador = genericDao.getCatTipoIdentificadorById(atencionMedicaForm.getAfiliadotipoidentificador().getCattipoidentificador().getTipoIdentificadorId());
					atencionmedica.setCattipoidentificador(cattipoidentificador);
					atencionmedica.setConvenios(conveniosDao.getConveniosById(atencionMedicaForm.getIdConvenio()));
					atencionmedica.setFechaAsistio(new Date());
					atencionmedica.setHoraAsistio(new Time(new Date().getTime()));
					atencionmedica.setLugaresdeatencion(agenda.getLugaresdeatencion());
					atencionmedica.setPrestadores(agenda.getPrestadores());
					atencionmedica.setUsuariosByUsuarioMedicoId(agenda.getUsuarios());
					atencionmedica.setUsuariosByUsuarioRecibioId(userInfo.getUsuarios());
					atencionmedica.setEnrolamiento(1);
					agendaDao.saveAtencionMedica(atencionmedica);
					agenda.setAsistio(1);
					agenda.setAtencionmedica(atencionmedica);
					agendaDao.update(agenda);

					Atencionprestacion atencionprestacion = new Atencionprestacion();
					atencionprestacion.setAtencionmedica(agendaDao.getAtencionMedicaById(atencionmedica.getAtencionMedicaId()));
					Catprestacion catprestacion = prestacionesDao.getCatPrestacionById(idPrestacion);
					atencionprestacion.setCatprestacion(catprestacion);
					
					AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
					atencionprestacionId.setAtencionMedicaId(atencionmedica.getAtencionMedicaId());
					atencionprestacionId.setPrestacionId(catprestacion.getPrestacionId());
					atencionprestacion.setId(atencionprestacionId);
					agendaDao.saveAtencionPrestacion(atencionprestacion);					
				}else{
					Atencionmedica atencionmedica = new Atencionmedica();
					atencionmedica.setAfiliado(agenda.getAfiliado());
					atencionmedica.setAseguradores(agenda.getAseguradores());
					Catagendadopor catagendadopor = agendaDao.getCatAgendadoPor(AgendadoPorEnum.WC.getId());//
					atencionmedica.setCatagendadopor(catagendadopor);
					Catestatuscitas catestatuscitas = agendaDao.getCatEstatusCitas(EstatusCitasEnum.A.getId());//
					atencionmedica.setCatestatuscitas(catestatuscitas);
					Cattipoidentificador cattipoidentificador = genericDao.getCatTipoIdentificadorById(atencionMedicaForm.getAfiliadotipoidentificador().getCattipoidentificador().getTipoIdentificadorId());
					atencionmedica.setCattipoidentificador(cattipoidentificador);
					atencionmedica.setConvenios(conveniosDao.getConveniosById(atencionMedicaForm.getIdConvenio()));
					atencionmedica.setFechaAsistio(new Date());
					atencionmedica.setHoraAsistio(new Time(new Date().getTime()));
					atencionmedica.setLugaresdeatencion(agenda.getLugaresdeatencion());
					atencionmedica.setPrestadores(agenda.getPrestadores());
					atencionmedica.setUsuariosByUsuarioMedicoId(agenda.getUsuarios());
					atencionmedica.setUsuariosByUsuarioRecibioId(userInfo.getUsuarios());
					agendaDao.saveAtencionMedica(atencionmedica);
					agenda.setAsistio(1);
					agenda.setAtencionmedica(atencionmedica);
					agendaDao.update(agenda);

					Atencionprestacion atencionprestacion = new Atencionprestacion();
					atencionprestacion.setAtencionmedica(agendaDao.getAtencionMedicaById(atencionmedica.getAtencionMedicaId()));
					Catprestacion catprestacion = prestacionesDao.getCatPrestacionById(idPrestacion);
					atencionprestacion.setCatprestacion(catprestacion);
					
					AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
					atencionprestacionId.setAtencionMedicaId(atencionmedica.getAtencionMedicaId());
					atencionprestacionId.setPrestacionId(catprestacion.getPrestacionId());
					atencionprestacion.setId(atencionprestacionId);
					agendaDao.saveAtencionPrestacion(atencionprestacion);					
				}
	
			}else {
				atencionMedicaForm.setBanderaError(true);
				atencionMedicaForm.setError("La prestacion no se encuentra en el convenio");
			}
			
			
		
		}catch(Exception e){
			e.printStackTrace();
			atencionMedicaForm.setBanderaError(true);
			atencionMedicaForm.setError("Surgio un error");
		}

	}

	@Override
	public void hola() {
		//log.info("hola Joel");
		
	}

}
