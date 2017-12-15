package com.mx.sab.service.impl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.log4j.Log4j2;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serviceimplementation.services.sass.mediaccess._2014._08.ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage;
import serviceimplementation.services.sass.mediaccess._2014._08.ISvcSassc_BuscarCitasDeClinicaConFecha_DefaultFaultContractFault_FaultMessage;
import serviceimplementation.services.sass.mediaccess._2014._08.ISvcSassc_ObtenerAfiliadoSiEsElegible_DefaultFaultContractFault_FaultMessage;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.AfiliadoCto;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ArrayOfCitaCto;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.BuscarAfiliado;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.BuscarAfiliadoResponse;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.BuscarCitasDeClinicaConFecha;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.BuscarCitasDeClinicaConFechaResponse;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.CitaCto;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ObtenerAfiliadoSiEsElegible;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ObtenerAfiliadoSiEsElegibleResponse;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAseguradorDao;
import com.mx.sab.dao.IAtencionMedicaDao;
import com.mx.sab.dao.IConveniosDao;
import com.mx.sab.dao.IEstudiosMedicosDao;
import com.mx.sab.dao.IFoliosDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.IIsssteDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.INotasNoFirmadasDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.dao.IPrestadoresDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.AgendadoPorEnum;
import com.mx.sab.enums.AseguradoresEnum;
import com.mx.sab.enums.CatEspecialidadesMedicasEnum;
import com.mx.sab.enums.CatEstadosEnum;
import com.mx.sab.enums.CatEstatusAtencionIdentidadEnum;
import com.mx.sab.enums.CatEstatusAtencionVigenciaEnum;
import com.mx.sab.enums.CatEstatusFirmaEnum;
import com.mx.sab.enums.CatEstatusRecepcionEnum;
import com.mx.sab.enums.CatSexoEnum;
import com.mx.sab.enums.CatTipoValorAseguradorEnum;
import com.mx.sab.enums.EstatusCitasEnum;
import com.mx.sab.enums.FoliosEnum;
import com.mx.sab.enums.RolesEnum;
import com.mx.sab.enums.TipoAfiliadoEnum;
import com.mx.sab.enums.TipoAtencionMedicaEnum;
import com.mx.sab.enums.TipoIdentificadorEnum;
import com.mx.sab.form.AtencionMedicaForm;
import com.mx.sab.form.CitasPresencialesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.AfiliadoAsegurador;
import com.mx.sab.model.AfiliadoAseguradorId;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.AfiliadotipoidentificadorId;
import com.mx.sab.model.Afiliadotipovalorasegurador;
import com.mx.sab.model.AfiliadotipovaloraseguradorId;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.AtencionprestacionId;
import com.mx.sab.model.Catagendadopor;
import com.mx.sab.model.Catestados;
import com.mx.sab.model.Catestatuscitas;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Catsexos;
import com.mx.sab.model.Cattipoafiliado;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.ConvenioCuadroprestaciones;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.Folio;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesportomar;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.model.Prestadores;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuariorol;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.ICitasPresencialesService;
import com.mx.sab.servicios.web.Datos;
import com.mx.sab.servicios.web.ValorizarDelegate;
import com.mx.sab.servicios.web.ValorizarService;
import com.mx.sab.servicios.web.ValorizarWebRequestVo;
import com.mx.sab.servicios.web.ValorizarWebResponseVo;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AfiliadoIsssteVo;
import com.mx.sab.vo.AfiliadoSinAseguradorVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.ConveniosVo;
import com.mx.sab.vo.PrestacionesPorTomarVo;
import com.mx.sab.vo.PrestadoresVo;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.ws.vo.AfiliadoCtoVo;

@Service
@Log4j2
public class CitasPresencialesServiceImpl implements ICitasPresencialesService {
	
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
	private ILugarAtencionDao lugarAtencionDao;

	@Autowired
	private IAseguradorDao aseguradorDao;
	
	@Autowired 
	private IPrestadoresDao prestadoresDao;
	
	@Autowired
	private IIsssteDao iIsssteDao;
	
	@Autowired
	private IAtencionMedicaDao atencionMedicaDao;
	
	@Autowired
	private INotasNoFirmadasDao notasNoFirmadasDao;
	
	@Autowired
	private IFoliosDao foliosDao;
	
	@Autowired
	private IEstudiosMedicosDao estudiosMedicosDao;
	
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
		
		if (atencionMedicaForm.getAgenda().getPrestadores().getUsarTablaPrestacionesSab() == 1) {
			Catprestacion catprestacion = prestacionesDao.getCatPrestacion(atencionMedicaForm.getAgenda().getPrestacion());
			atencionMedicaForm.setCatPrestacion(catprestacion);
		}else {
			Prestacionprestador prestacionprestador = prestacionesDao.getPrestacionPrestador(atencionMedicaForm.getAgenda().getPrestacion());
			atencionMedicaForm.setPrestacionprestador(prestacionprestador);
		}
		
		atencionMedicaForm.setFechaDeNacimiento(FormatUtil.getDate(agenda.getAfiliado().getFechaDeNacimiento()));
		
		Afiliadotipoidentificador afiliadotipoidentificador = genericDao.getAfiliadoTipoIdentificador(agenda.getAfiliado().getAfiliadoId(), TipoIdentificadorEnum.RFC.getId());
		atencionMedicaForm.setDato(afiliadotipoidentificador.getTipoIdValor());
		
		//log.info(agenda.getAfiliado().getFechaDeNacimiento().getTime());
		//log.info(agenda.getAfiliado().getFechaDeNacimiento());
		//log.info(new Date().getTime());
		//log.info(new Date());		
		long diff = new Date().getTime() - agenda.getAfiliado().getFechaDeNacimiento().getTime();
		//log.info(diff);
		long days = (24 * 60 * 60 * 1000);
		long diffdays = diff / days;
		long diffyears = diffdays / 365;
		
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
	public void inicializaCitasPresencialesForm(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo) {
		//Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByCodigo(userInfo.getTx_Marca());
		Iterator<Convenios> conveniosIterator = userInfo.getLugaresdeatencion().getConvenioses().iterator();
		ArrayList<Aseguradores> aseguradores = new ArrayList<>();
		
		HashMap<Integer, Aseguradores> aseguradoresMap = new HashMap<>();
		while (conveniosIterator.hasNext()) {
			Convenios convenios = (Convenios) conveniosIterator.next();
			if (convenios.getActivo()==1) {
				if (aseguradoresMap.get(convenios.getAseguradores().getAseguradorId()) == null) {
					aseguradoresMap.put(convenios.getAseguradores().getAseguradorId(), convenios.getAseguradores());
				}
			}
		}
		
	    Iterator<Entry<Integer, Aseguradores>> it = aseguradoresMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Integer, Aseguradores> pair = (Map.Entry<Integer, Aseguradores>)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        aseguradores.add(pair.getValue());
	    }

	    citasPresencialesForm.setTipoAfiliado("D");
	    citasPresencialesForm.setPersonaConfianza(true);
	    if (aseguradores.size() == 1) {
			citasPresencialesForm.setIdAsegurador(aseguradores.get(0).getAseguradorId());
		}
		citasPresencialesForm.setAseguradores(aseguradores);
		citasPresencialesForm.setIdLugarAtencion(userInfo.getLugaresdeatencion().getLugarDeAtencionId());
	}

	@Override
	public Collection<PrestadoresVo> getPrestadoresByAseguradorLugar(int id, Collection<Aseguradores> aseguradores) {
		Collection<Prestadores> prestadores = new ArrayList<>();
		for (Aseguradores asegurador : aseguradores) {
			if (asegurador.getAseguradorId() == id) {
				Iterator<Convenios> conveniosIterator = asegurador.getConvenioses().iterator();
				
				HashMap<Integer, Prestadores> prestadoresMap = new HashMap<>();
				while (conveniosIterator.hasNext()) {
					Convenios convenios = (Convenios) conveniosIterator.next();
					if (convenios.getActivo()==1) {
						if (prestadoresMap.get(convenios.getPrestadores().getPrestadorId()) == null) {
							prestadoresMap.put(convenios.getPrestadores().getPrestadorId(), convenios.getPrestadores());
						}
					}
				}
				
			    Iterator<Entry<Integer, Prestadores>> it = prestadoresMap.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry<Integer, Prestadores> pair = (Map.Entry<Integer, Prestadores>)it.next();
			        System.out.println(pair.getKey() + " = " + pair.getValue());
			        prestadores.add(pair.getValue());
			    }
			}
		}
		
		Collection<PrestadoresVo> prestadoresVos = new ArrayList<>();
		for (Prestadores prestador : prestadores) {
			PrestadoresVo prestadoresVo = new PrestadoresVo();
			prestadoresVo.setPrestadorId(prestador.getPrestadorId());
			prestadoresVo.setNombreRazonSocial(prestador.getNombreRazonSocial());
			prestadoresVos.add(prestadoresVo);
		}
		
		return prestadoresVos;
	}

	@Override
	public Collection<ConveniosVo> getConvenios(int idAsegurador, int idPrestador, UserInfo userInfo) {
		Collection<Convenios> convenios = conveniosDao.getConveniosByIds(idAsegurador, idPrestador, userInfo.getTx_Marca());
		Collection<ConveniosVo> conveniosVos = new ArrayList<>();
		for (Convenios convenio : convenios) {
			ConveniosVo conveniosVo = new ConveniosVo();
			conveniosVo.setConvenioId(convenio.getConvenioId());
			conveniosVo.setConvenio(convenio.getConvenio());
			conveniosVos.add(conveniosVo);
		}
		return conveniosVos;
	}

	@Override
	public void getAfiliados(CitasPresencialesForm citasPresencialesForm) {
		AfiliadoCto[] afiliadoCtos = null;
		Collection<AfiliadoCtoVo> afiliadoCtoVos = new ArrayList<>();
		Collection<String> errores = new ArrayList<>();
		try {
			
			if (citasPresencialesForm.getIdAsegurador()==-1 && !citasPresencialesForm.isBanderaError()) {
				citasPresencialesForm.setError("Debe seleccionar campo Asegurador");
				citasPresencialesForm.setBanderaError(true);
			}
			
			if (citasPresencialesForm.getIdPrestador()==-1 && !citasPresencialesForm.isBanderaError()) {
				citasPresencialesForm.setError("Debe seleccionar campo Prestador");
				citasPresencialesForm.setBanderaError(true);
			}			
			
			if (citasPresencialesForm.getIdConvenio()==-1 && !citasPresencialesForm.isBanderaError()) {
				citasPresencialesForm.setError("Debe seleccionar campo Convenio");
				citasPresencialesForm.setBanderaError(true);
			}
			
			if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.MEDIACCESS.getId()) {
				
				if (citasPresencialesForm.getAgenda().getAfiliado().getNombre().trim().length()==0) {
					errores.add("Debe de ingresar un Nombre");	
				}
				
				if (citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno().trim().length()==0) {
					errores.add("Debe de ingresar un Apellido Paterno");	
				}
				
				if (errores.size()==0) {
					SvcSassSiStub svcSassSiStub = new SvcSassSiStub();
					BuscarAfiliado buscarAfiliado = new BuscarAfiliado();
					buscarAfiliado.setApellidoPaterno(citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno());
					buscarAfiliado.setApellidoMaterno(citasPresencialesForm.getAgenda().getAfiliado().getApellidoMaterno());
					buscarAfiliado.setNombre(citasPresencialesForm.getAgenda().getAfiliado().getNombre());
					if (citasPresencialesForm.getIdIdentificador() == TipoIdentificadorEnum.CODAFILIADO.getId()) {
						buscarAfiliado.setCodAfiliado(citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor());
					}else if (citasPresencialesForm.getIdIdentificador() == TipoIdentificadorEnum.CODEMPRESA.getId()) {
						buscarAfiliado.setCodEmpresa(Integer.parseInt(citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor()));
					}else if (citasPresencialesForm.getIdIdentificador() == TipoIdentificadorEnum.CORRELATIVO.getId()) {
						buscarAfiliado.setCorrelativo(Integer.parseInt(citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor()));
					}
					BuscarAfiliadoResponse buscarAfiliadoResponse = svcSassSiStub.buscarAfiliado(buscarAfiliado);
					afiliadoCtos = buscarAfiliadoResponse.getBuscarAfiliadoResult().getAfiliadoCto();
					
					for (int i = 0; i < afiliadoCtos.length; i++) {
						AfiliadoCto afiliadoCto = afiliadoCtos[i];
						AfiliadoCtoVo afiliadoCtoVo = new AfiliadoCtoVo();
						afiliadoCtoVo.setNombre(afiliadoCto.getNombre());
						afiliadoCtoVo.setApellidoPaterno(afiliadoCto.getApellidoPaterno());
						afiliadoCtoVo.setApellidoMaterno(afiliadoCto.getApellidoMaterno());
						afiliadoCtoVo.setFecha(FormatUtil.getCalendar(afiliadoCto.getFechaNacimiento()));
						afiliadoCtoVo.setCodAfiliado(afiliadoCto.getCodAfiliado());
						afiliadoCtoVo.setCodEmpresa(afiliadoCto.getCodEmpresa());
						afiliadoCtoVo.setCorrelativo(afiliadoCto.getCorrelativo());
						afiliadoCtoVo.setCodProducto(afiliadoCto.getCodProducto());
						afiliadoCtoVos.add(afiliadoCtoVo);
					}
					
					citasPresencialesForm.setAfiliadoCtoVos(afiliadoCtoVos);
					
				}
			}else if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.ISSSTE.getId()) {
//				if (citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno().trim().length()==0 && !citasPresencialesForm.isBanderaError()) {
//					citasPresencialesForm.setError("Debe ingresar campo Apellido Paterno");
//					citasPresencialesForm.setBanderaError(true);
//				}
//				
//				if (citasPresencialesForm.getAgenda().getAfiliado().getNombre().trim().length()==0 && !citasPresencialesForm.isBanderaError()) {
//					citasPresencialesForm.setError("Debe ingresar campo Nombre");
//					citasPresencialesForm.setBanderaError(true);
//				}
				if (!citasPresencialesForm.isBanderaError()) {
					if(citasPresencialesForm.getTipoAfiliado().equals("B")){
						Collection<AfiliadoIsssteVo> afiliadoIsssteVos = null;;
						afiliadoIsssteVos = iIsssteDao.getAfiliado(citasPresencialesForm.getIdIdentificador(), 
																   citasPresencialesForm.getAgenda().getAfiliado().getNombre(), 
																   citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno(), 
																   citasPresencialesForm.getAgenda().getAfiliado().getApellidoMaterno(), 
																   citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor());
						HashMap<Integer, AfiliadoIsssteVo> mapaNumIssste = new HashMap<>();
						for (AfiliadoIsssteVo afiliadoIsssteVo : afiliadoIsssteVos) {
							if(mapaNumIssste.get(afiliadoIsssteVo.getNumIssste())==null){
								mapaNumIssste.put(afiliadoIsssteVo.getNumIssste(), afiliadoIsssteVo);
							}
						}
						afiliadoIsssteVos = new ArrayList<>();
						Iterator<Entry<Integer, AfiliadoIsssteVo>> it = mapaNumIssste.entrySet().iterator();
					    while (it.hasNext()) {
					        Entry<Integer, AfiliadoIsssteVo> pair = (Entry<Integer, AfiliadoIsssteVo>)it.next();
					        afiliadoIsssteVos.add(pair.getValue());
					    }
						citasPresencialesForm.setAfiliadoIsssteVos(afiliadoIsssteVos);	
					}else {
						Collection<AfiliadoIsssteVo> afiliadoIsssteVos = null;;
						afiliadoIsssteVos = iIsssteDao.getAfiliado(citasPresencialesForm.getIdIdentificador(), 
																   citasPresencialesForm.getAgenda().getAfiliado().getNombre(), 
																   citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno(), 
																   citasPresencialesForm.getAgenda().getAfiliado().getApellidoMaterno(), 
																   citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor());
						HashMap<Integer, AfiliadoIsssteVo> mapaNumIssste = new HashMap<>();
						for (AfiliadoIsssteVo afiliadoIsssteVo : afiliadoIsssteVos) {
							if(mapaNumIssste.get(afiliadoIsssteVo.getNumIssste())==null){
								mapaNumIssste.put(afiliadoIsssteVo.getNumIssste(), afiliadoIsssteVo);
							}
						}
						afiliadoIsssteVos = new ArrayList<>();
						Iterator<Entry<Integer, AfiliadoIsssteVo>> it = mapaNumIssste.entrySet().iterator();
					    while (it.hasNext()) {
					        Entry<Integer, AfiliadoIsssteVo> pair = (Entry<Integer, AfiliadoIsssteVo>)it.next();
					        afiliadoIsssteVos.add(pair.getValue());
					    }
						citasPresencialesForm.setAfiliadoIsssteVos(afiliadoIsssteVos);		
					}
				}
			}else if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.SIN_ASEGURADOR.getId()) {
				if (citasPresencialesForm.getIdIdentificador() == -1) {
					errores.add("Debe de seleccionar Identificador");
				}else{
					if (citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor().trim().length()==0) {
						errores.add("Debe de ingresar el valor del identificador");	
					}
				}
				
				if (citasPresencialesForm.getSexoAfiliado()==null) {
					errores.add("Debe de seleccionar el sexo");
				}
				
				if (citasPresencialesForm.getAgenda().getAfiliado().getNombre().trim().length()==0) {
					errores.add("Debe de ingresar un Nombre");	
				}
				
				if (citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno().trim().length()==0) {
					errores.add("Debe de ingresar un Apellido Paterno");	
				}
				
				if (citasPresencialesForm.getFechaDeNacimiento().trim().length()==0) {
					errores.add("Debe de ingresar una fecha de nacimiento");	
				}
				
				if (citasPresencialesForm.getAgenda().getAfiliado().getTelefono1().trim().length()==0) {
					errores.add("Debe de ingresar un Telefono");	
				}
				
				if (errores.size() == 0) {
					Collection<Afiliado> afiliados = null;
					Collection<Afiliadotipoidentificador> afiliadotipoidentificadors = agendaDao.getAfiliadosByIdentificador(citasPresencialesForm.getIdIdentificador(),citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor().trim());
					if (afiliadotipoidentificadors.size()==0) {
						 afiliados = agendaDao.getAfiliadosByNombre(citasPresencialesForm.getAgenda().getAfiliado().getNombre().trim(), citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno().trim(), citasPresencialesForm.getAgenda().getAfiliado().getApellidoMaterno());
					}else {
						afiliados = new ArrayList<>();
						for (Afiliadotipoidentificador afiliadotipoidentificador : afiliadotipoidentificadors) {
							afiliados.add(afiliadotipoidentificador.getAfiliado());
						}
					}
					
					Collection<AfiliadoSinAseguradorVo> afiliadoSinAseguradorVos = new ArrayList<>();
					for (Afiliado afiliado : afiliados) {
						AfiliadoSinAseguradorVo afiliadoSinAseguradorVo = new AfiliadoSinAseguradorVo();
						afiliadoSinAseguradorVo.setIdAfiliado(afiliado.getAfiliadoId());
						afiliadoSinAseguradorVo.setApellidoMaterno(afiliado.getApellidoMaterno());
						afiliadoSinAseguradorVo.setApellidoPaterno(afiliado.getApellidoPaterno());
						afiliadoSinAseguradorVo.setFecha(FormatUtil.getDate(afiliado.getFechaDeNacimiento()));
						afiliadoSinAseguradorVo.setNombre(afiliado.getNombre());
						afiliadoSinAseguradorVos.add(afiliadoSinAseguradorVo);
					}
					citasPresencialesForm.setAfiliadoSinAseguradorVos(afiliadoSinAseguradorVos);
				}	
			}
			if (!citasPresencialesForm.isBanderaError()) {
				Aseguradores aseguradores = aseguradorDao.getAseguradorById(citasPresencialesForm.getIdAsegurador());
				citasPresencialesForm.setMostrarBotonPacienteNoEnLista(aseguradores.getFuncionPacienteNoEnLista());
			}
			citasPresencialesForm.setErrores(errores);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ISvcSassc_BuscarAfiliado_DefaultFaultContractFault_FaultMessage e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void isVigente(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo) {
		Collection<String> errores = new ArrayList<>();
		try {
			Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByCodigo(userInfo.getTx_Marca());
			
			if (citasPresencialesForm.getIdAsegurador()==-1) {
				errores.add("Debe de seleccionar un Asegurador");
			}
			
			if (citasPresencialesForm.getIdPrestador()==-1) {
				errores.add("Debe de seleccionar un Prestador");
			}
			
			if (citasPresencialesForm.getIdConvenio()==-1) {
				errores.add("Debe de seleccionar un Convenio");
			}
			
			if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.MEDIACCESS.getId()) {
				SvcSassSiStub svcSassSiStub = new SvcSassSiStub();
				ObtenerAfiliadoSiEsElegible obtenerAfiliadoSiEsElegible = new ObtenerAfiliadoSiEsElegible();
				obtenerAfiliadoSiEsElegible.setCodAfiliado(citasPresencialesForm.getCodAfiliado());
				obtenerAfiliadoSiEsElegible.setCodCuenta(61204);
				obtenerAfiliadoSiEsElegible.setCodEmpresa(citasPresencialesForm.getCodEmpresa());
				obtenerAfiliadoSiEsElegible.setCorrelativo(citasPresencialesForm.getCorrelativo());
				
				ObtenerAfiliadoSiEsElegibleResponse obtenerAfiliadoSiEsElegibleResponse = svcSassSiStub.obtenerAfiliadoSiEsElegible(obtenerAfiliadoSiEsElegible);
				AfiliadoCto afiliadoCto = obtenerAfiliadoSiEsElegibleResponse.getObtenerAfiliadoSiEsElegibleResult();
				
				if (afiliadoCto.getCodEstatusAfiliado()==1) {
					Afiliadotipoidentificador afiliadotipoidentificadorAux = null;
					Collection<Afiliadotipoidentificador> afiliadotipoidentificadors = agendaDao.getAfiliadoTipoIdentificadorMediaccess(afiliadoCto.getCodEmpresa(), afiliadoCto.getCodAfiliado(), afiliadoCto.getCorrelativo());
					HashMap<Integer, Integer> repeticiones = new HashMap<>();
					HashMap<Integer, Afiliadotipoidentificador> mapaAfiliadoTipoIdentificador = new HashMap<>();
					int repeticion = 0;
					for (Afiliadotipoidentificador afiliadotipoidentificador : afiliadotipoidentificadors) {
						if(repeticiones.get(afiliadotipoidentificador.getAfiliado().getAfiliadoId())!=null){
							repeticion = repeticiones.get(afiliadotipoidentificador.getAfiliado().getAfiliadoId())+1;
							repeticiones.put(afiliadotipoidentificador.getAfiliado().getAfiliadoId(), repeticion);
						}else {
							repeticion = 1;
							repeticiones.put(afiliadotipoidentificador.getAfiliado().getAfiliadoId(), repeticion);
							mapaAfiliadoTipoIdentificador.put(afiliadotipoidentificador.getAfiliado().getAfiliadoId(), afiliadotipoidentificador);
						}
					}
					
					Iterator<Entry<Integer, Integer>> iterator = repeticiones.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) iterator.next();
						if (entry.getValue() == 3) {
							afiliadotipoidentificadorAux = mapaAfiliadoTipoIdentificador.get(entry.getKey());
							break;
						}
					}
					
					if (afiliadotipoidentificadorAux==null) {
						Afiliado afiliado = new Afiliado();
						afiliado.setActivo(1);
						afiliado.setNombre(afiliadoCto.getNombre());
						afiliado.setApellidoMaterno(afiliadoCto.getApellidoMaterno());
						afiliado.setApellidoPaterno(afiliadoCto.getApellidoPaterno());
						afiliado.setFechaDeNacimiento(afiliadoCto.getFechaNacimiento().getTime());
						Catestados catestados = genericDao.getEstadoById(CatEstadosEnum.DISTRITO_FEDERAL.getId());
						afiliado.setCatestadosByEstadoId(catestados);
						afiliado.setCatestadosByEstadoDeNacimientoId(catestados);
						Catsexos catsexos = null;
						if (afiliadoCto.getSexo().equals(CatSexoEnum.MASCULINO.getName())) {
							catsexos = genericDao.getCatSexos(CatSexoEnum.MASCULINO.getId());
						}else {
							catsexos = genericDao.getCatSexos(CatSexoEnum.FEMENINO.getId());
						}
						afiliado.setCatsexos(catsexos);
						Cattipoafiliado cattipoafiliado = genericDao.getCatTipoAfiliadoByTipo(afiliadoCto.getParentesco(),citasPresencialesForm.getIdAsegurador());
						afiliado.setCattipoafiliado(cattipoafiliado);
						afiliado.setFechaAlta(new Date());
						agendaDao.saveAfiliado(afiliado);
						
						AfiliadotipovaloraseguradorId afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						Afiliadotipovalorasegurador afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.COD_PLAN.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(afiliadoCto.getCodAfiliado());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);

						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.COD_PRODUCTO.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(afiliadoCto.getCodProducto());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.PLAN.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(afiliadoCto.getPlan());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						if (afiliadotipovalorasegurador.getId().getTipoIdValor()!=null) {
							agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);	
						}
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.PRODUCTO.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(afiliadoCto.getProducto());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						if (afiliadotipovalorasegurador.getId().getTipoIdValor()!=null) {
							agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);	
						}							
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.POLIZA.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(afiliadoCto.getPoliza());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						if (afiliadotipovalorasegurador.getId().getTipoIdValor()!=null) {
							agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);	
						}	
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.COD_PYME.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(""+afiliadoCto.getCodPymeColectivo());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						if (afiliadotipovalorasegurador.getId().getTipoIdValor()!=null) {
							agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);	
						}	
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.PYME.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(afiliadoCto.getDescPymeColectivo());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						if (afiliadotipovalorasegurador.getId().getTipoIdValor()!=null) {
							agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);	
						}	
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.COD_PARENTESCO.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(""+afiliadoCto.getCodParentesco());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						if (afiliadotipovalorasegurador.getId().getTipoIdValor()!=null) {
							agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);	
						}	
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.PARENTESCO.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(afiliadoCto.getParentesco());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						if (afiliadotipovalorasegurador.getId().getTipoIdValor()!=null) {
							agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);	
						}							
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.REGLAS_fARMACIA.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(afiliadoCto.getReglasFarmacia());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);

						if (afiliadotipovalorasegurador.getId().getTipoIdValor()!=null) {
							agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);	
						}
						
						afiliadotipovaloraseguradorId = new AfiliadotipovaloraseguradorId();
						afiliadotipovalorasegurador = new Afiliadotipovalorasegurador();
						
						afiliadotipovaloraseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipovaloraseguradorId.setTipoValorAseguradorId(CatTipoValorAseguradorEnum.SUMA_ASEG_FARMACIA.getId());
						afiliadotipovaloraseguradorId.setTipoIdValor(""+afiliadoCto.getSumaAsegFarmacia());
						afiliadotipovalorasegurador.setId(afiliadotipovaloraseguradorId);
						
						agendaDao.saveAfiliadoTipoValorAsegurador(afiliadotipovalorasegurador);
						
						Afiliadotipoidentificador afiliadotipoidentificador = new Afiliadotipoidentificador();
						AfiliadotipoidentificadorId afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
						
						afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipoidentificadorId.setTipoIdentificadorId(TipoIdentificadorEnum.CODEMPRESA.getId());
						afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
						afiliadotipoidentificador.setTipoIdValor(""+afiliadoCto.getCodEmpresa());
						
						genericDao.saveAfiliadotipoIdentificar(afiliadotipoidentificador);

						afiliadotipoidentificador = new Afiliadotipoidentificador();
						afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
						
						afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipoidentificadorId.setTipoIdentificadorId(TipoIdentificadorEnum.CODAFILIADO.getId());
						afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
						afiliadotipoidentificador.setTipoIdValor(afiliadoCto.getCodAfiliado());
						
						genericDao.saveAfiliadotipoIdentificar(afiliadotipoidentificador);
						
						afiliadotipoidentificador = new Afiliadotipoidentificador();
						afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
						
						afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
						afiliadotipoidentificadorId.setTipoIdentificadorId(TipoIdentificadorEnum.CORRELATIVO.getId());
						afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
						afiliadotipoidentificador.setTipoIdValor(""+afiliadoCto.getCorrelativo());
						
						genericDao.saveAfiliadotipoIdentificar(afiliadotipoidentificador);
						
						citasPresencialesForm.setIdIdentificador(TipoIdentificadorEnum.CODAFILIADO.getId());
						citasPresencialesForm.getAfiliadotipoidentificador().setTipoIdValor(""+afiliadoCto.getCodAfiliado());
						if (cattipoafiliado.getTipoAfiliadoId() == TipoAfiliadoEnum.TITULAR.getId()) {
							citasPresencialesForm.setTipoAfiliado("D");	
						}else {
							citasPresencialesForm.setTipoAfiliado("B");
						}
						
						citasPresencialesForm.setDato(""+afiliado.getAfiliadoId());
						citasPresencialesForm.setTipoDato("AfiliadoId");
						
		    			citasPresencialesForm.setFechaDeNacimiento(FormatUtil.getDate(afiliadoCto.getFechaNacimiento().getTime()));
		    			citasPresencialesForm.getAgenda().getAfiliado().setNombre(afiliadoCto.getNombre());
		    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoPaterno(afiliadoCto.getApellidoPaterno());
		    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoMaterno(afiliadoCto.getApellidoMaterno());
		    			citasPresencialesForm.setSexo(catsexos.getSexoId());
		    			citasPresencialesForm.setEstado(catestados.getEstadoId());
		    			citasPresencialesForm.setRfc(citasPresencialesForm.getCodEmpresa() + "" + citasPresencialesForm.getCodAfiliado() + "" + citasPresencialesForm.getCorrelativo());
		    			citasPresencialesForm.setIdAfiliado(afiliado.getAfiliadoId());
		    			citasPresencialesForm.setVigencia(true);
		    			citasPresencialesForm.setCodProducto(afiliadoCto.getCodProducto());
					}else{
						Afiliado afiliado = afiliadotipoidentificadorAux.getAfiliado();
						Afiliadotipoidentificador afiliadotipoidentificador = genericDao.getAfiliadoTipoIdentificador(afiliado.getAfiliadoId(), TipoIdentificadorEnum.CODAFILIADO.getId());
						citasPresencialesForm.setIdIdentificador(TipoIdentificadorEnum.CODAFILIADO.getId());
						citasPresencialesForm.getAfiliadotipoidentificador().setTipoIdValor(afiliadotipoidentificador.getTipoIdValor());
						if (afiliado.getCattipoafiliado().getTipoAfiliadoId() == TipoAfiliadoEnum.TITULAR.getId()) {
							citasPresencialesForm.setTipoAfiliado("D");	
						}else {
							citasPresencialesForm.setTipoAfiliado("B");
						}
		    			citasPresencialesForm.setFechaDeNacimiento(FormatUtil.getDate(afiliado.getFechaDeNacimiento()));
		    			citasPresencialesForm.getAgenda().getAfiliado().setNombre(afiliado.getNombre());
		    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoPaterno(afiliado.getApellidoPaterno());
		    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoMaterno(afiliado.getApellidoMaterno());
		    			citasPresencialesForm.setSexo(afiliado.getCatsexos().getSexoId());
		    			citasPresencialesForm.setEstado(afiliado.getCatestadosByEstadoDeNacimientoId().getEstadoId());
		    			citasPresencialesForm.setRfc(citasPresencialesForm.getCodEmpresa() + "" + citasPresencialesForm.getCodAfiliado() + "" + citasPresencialesForm.getCorrelativo());
		    			citasPresencialesForm.setIdAfiliado(afiliado.getAfiliadoId());
		    			citasPresencialesForm.setVigencia(true);
		    			citasPresencialesForm.setCodProducto(afiliadoCto.getCodProducto());
					}
				}else{
					errores.add("El usuario no se encuentra vigente");
				}				
			}else if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.ISSSTE.getId()) {
				AfiliadoIsssteVo afiliadoIsssteVo = null;
				for (AfiliadoIsssteVo afiliadoIsssteVoAux : citasPresencialesForm.getAfiliadoIsssteVos()) {
					if (afiliadoIsssteVoAux.getNumIssste() == citasPresencialesForm.getNumIssste()) {
						afiliadoIsssteVo = afiliadoIsssteVoAux;
						break;
					}
				}
				
				Afiliadotipoidentificador afiliadotipoidentificadorAux = null;
				boolean vigencia = false;
				if (afiliadoIsssteVo.getVigencia().equals("A")) {
					vigencia = true;
				}else{
					vigencia = false;
				}
				
				Collection<Afiliadotipoidentificador> afiliadotipoidentificadors = agendaDao.getAfiliadoTipoIdentificadorIssste(afiliadoIsssteVo.getNumIssste());
				
				for (Afiliadotipoidentificador afiliadotipoidentificador : afiliadotipoidentificadors) {
					if(afiliadotipoidentificador.getAfiliado().getCattipoafiliado().getTipoAfiliadoId().equals(TipoAfiliadoEnum.TRABAJADOR.getId()) || afiliadotipoidentificador.getAfiliado().getCattipoafiliado().getTipoAfiliadoId().equals(TipoAfiliadoEnum.TRABAJADORA.getId()) || afiliadotipoidentificador.getAfiliado().getCattipoafiliado().getTipoAfiliadoId().equals(TipoAfiliadoEnum.PENSIONADO.getId())){
						afiliadotipoidentificadorAux = afiliadotipoidentificador;
						break;
					}
				}				
				
				if (afiliadotipoidentificadorAux==null) {
					Afiliado afiliado = new Afiliado();
					afiliado.setActivo(1);
					afiliado.setNombre(afiliadoIsssteVo.getNombre());
					afiliado.setApellidoMaterno(afiliadoIsssteVo.getApellidoMaterno());
					afiliado.setApellidoPaterno(afiliadoIsssteVo.getApellidoPaterno());
					afiliado.setFechaDeNacimiento(FormatUtil.getDate(afiliadoIsssteVo.getFechaNacimiento()));
					Catestados catestados = genericDao.getEstadoById(CatEstadosEnum.DISTRITO_FEDERAL.getId());
					afiliado.setCatestadosByEstadoId(catestados);
					afiliado.setCatestadosByEstadoDeNacimientoId(catestados);
					Catsexos catsexos = null;
					
					
					if (afiliadoIsssteVo.getSexo().equals(CatSexoEnum.MASCULINO.getName())) {
						catsexos = genericDao.getCatSexos(CatSexoEnum.MASCULINO.getId());
					}else {
						catsexos = genericDao.getCatSexos(CatSexoEnum.FEMENINO.getId());
					}
					
					if(citasPresencialesForm.getTipoAfiliado().equals("D")){
						if(afiliadoIsssteVo.getTipo().equals(TipoAfiliadoEnum.PENSIONADO.getTipo())){
							afiliadoIsssteVo.setParentesco(TipoAfiliadoEnum.PENSIONADO.getParentesco());
						}else {
							if (afiliadoIsssteVo.getSexo().equals(CatSexoEnum.MASCULINO.getName())) {
								afiliadoIsssteVo.setParentesco(TipoAfiliadoEnum.TRABAJADOR.getParentesco());	
							}else {
								afiliadoIsssteVo.setParentesco(TipoAfiliadoEnum.TRABAJADORA.getParentesco());
							}
						}
					}
					
					afiliado.setCatsexos(catsexos);
					Cattipoafiliado cattipoafiliado = genericDao.getCatTipoAfiliadoByTipo(afiliadoIsssteVo.getParentesco(),citasPresencialesForm.getIdAsegurador());
					afiliado.setCattipoafiliado(cattipoafiliado);
					afiliado.setFechaAlta(new Date());
					agendaDao.saveAfiliado(afiliado);
					
					Afiliadotipoidentificador afiliadotipoidentificador = new Afiliadotipoidentificador();
					AfiliadotipoidentificadorId afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
					
					afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
					afiliadotipoidentificadorId.setTipoIdentificadorId(TipoIdentificadorEnum.NUMISSSTE.getId());
					afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
					afiliadotipoidentificador.setTipoIdValor(""+afiliadoIsssteVo.getNumIssste());
					
					genericDao.saveAfiliadotipoIdentificar(afiliadotipoidentificador);
					
					citasPresencialesForm.setIdIdentificador(TipoIdentificadorEnum.NUMISSSTE.getId());
					citasPresencialesForm.getAfiliadotipoidentificador().setTipoIdValor(""+afiliadoIsssteVo.getNumIssste());
					citasPresencialesForm.setTipoAfiliado("D");	
	    			
					citasPresencialesForm.setFechaDeNacimiento(afiliadoIsssteVo.getFechaNacimiento());
	    			citasPresencialesForm.getAgenda().getAfiliado().setNombre(afiliadoIsssteVo.getNombre());
	    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoPaterno(afiliadoIsssteVo.getApellidoPaterno());
	    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoMaterno(afiliadoIsssteVo.getApellidoMaterno());
	    			citasPresencialesForm.setSexo(catsexos.getSexoId());
	    			citasPresencialesForm.setEstado(catestados.getEstadoId());
	    			citasPresencialesForm.setRfc(citasPresencialesForm.getCodEmpresa() + "" + citasPresencialesForm.getCodAfiliado() + "" + citasPresencialesForm.getCorrelativo());
	    			citasPresencialesForm.setIdAfiliado(afiliado.getAfiliadoId());
	    			citasPresencialesForm.setVigencia(vigencia);
	    			
	    			long edad = FormatUtil.getEdad(citasPresencialesForm.getAgenda().getAfiliado().getFechaDeNacimiento().getTime());
	    			
	    			if (edad<6) {
	    				citasPresencialesForm.setMenorDeEdad(true);
	    			}
	    			
				}else{
					Afiliado afiliado = afiliadotipoidentificadorAux.getAfiliado();
					Afiliadotipoidentificador afiliadotipoidentificador = genericDao.getAfiliadoTipoIdentificador(afiliado.getAfiliadoId(), TipoIdentificadorEnum.NUMISSSTE.getId());
					citasPresencialesForm.setIdIdentificador(TipoIdentificadorEnum.NUMISSSTE.getId());
					citasPresencialesForm.getAfiliadotipoidentificador().setTipoIdValor(afiliadotipoidentificador.getTipoIdValor());
					citasPresencialesForm.setTipoAfiliado("D");	
	    			citasPresencialesForm.setFechaDeNacimiento(FormatUtil.getDate(afiliado.getFechaDeNacimiento()));
	    			citasPresencialesForm.getAgenda().getAfiliado().setNombre(afiliado.getNombre());
	    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoPaterno(afiliado.getApellidoPaterno());
	    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoMaterno(afiliado.getApellidoMaterno());
	    			citasPresencialesForm.getAgenda().getAfiliado().setTelefono1((afiliado.getTelefono1()));
	    			citasPresencialesForm.getAgenda().getAfiliado().setMail((afiliado.getMail()));
	    			citasPresencialesForm.setSexo(afiliado.getCatsexos().getSexoId());
	    			citasPresencialesForm.setEstado(afiliado.getCatestadosByEstadoDeNacimientoId().getEstadoId());
	    			citasPresencialesForm.setRfc(citasPresencialesForm.getCodEmpresa() + "" + citasPresencialesForm.getCodAfiliado() + "" + citasPresencialesForm.getCorrelativo());
	    			citasPresencialesForm.setIdAfiliado(afiliado.getAfiliadoId());
	    			citasPresencialesForm.setVigencia(vigencia);
	    			
	    			long edad = FormatUtil.getEdad(afiliado.getFechaDeNacimiento().getTime());
	    			
	    			if (edad<6) {
	    				citasPresencialesForm.setMenorDeEdad(true);
	    			}
				}							
			}else if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.SIN_ASEGURADOR.getId()) {
				Afiliado afiliado = agendaDao.getAfiliadoById(citasPresencialesForm.getIdAfiliadoSinAsegurador());
				Afiliadotipoidentificador afiliadotipoidentificador = genericDao.getAfiliadoTipoIdentificador(afiliado.getAfiliadoId(), citasPresencialesForm.getIdIdentificador());
				citasPresencialesForm.getAfiliadotipoidentificador().setTipoIdValor(afiliadotipoidentificador.getTipoIdValor());
				citasPresencialesForm.setTipoAfiliado("D");
				citasPresencialesForm.setSexo(afiliado.getCatsexos().getSexoId());
    			citasPresencialesForm.setFechaDeNacimiento(FormatUtil.getDate(afiliado.getFechaDeNacimiento()));
    			citasPresencialesForm.getAgenda().getAfiliado().setNombre(afiliado.getNombre());
    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoPaterno(afiliado.getApellidoPaterno());
    			citasPresencialesForm.getAgenda().getAfiliado().setApellidoMaterno(afiliado.getApellidoMaterno());
    			citasPresencialesForm.setSexo(afiliado.getCatsexos().getSexoId());
    			citasPresencialesForm.setRfc(afiliadotipoidentificador.getTipoIdValor());
    			citasPresencialesForm.setEstado(afiliado.getCatestadosByEstadoDeNacimientoId().getEstadoId());
    			citasPresencialesForm.setIdAfiliado(afiliado.getAfiliadoId());					
			}else{
				errores.add("El usuario no se encuentra vigente");
			}	
		} catch (ISvcSassc_ObtenerAfiliadoSiEsElegible_DefaultFaultContractFault_FaultMessage e) {
			e.printStackTrace();
			errores.add(e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			errores.add(e.getMessage());
		}
		
		citasPresencialesForm.setErrores(errores);
	}

	@Override
	public void save(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo) {
		try{
			boolean citaDeAgenda = false;
			Agenda agenda = null;
			if (citasPresencialesForm.getIdAgenda()!=0) {
				agenda = agendaDao.getAgendaById(citasPresencialesForm.getIdAgenda());
				if(citasPresencialesForm.getAutocompleteVos().size()>1){
					citaDeAgenda = false;	
				}else{
					AutocompleteVo autocompleteVoAux = citasPresencialesForm.getAutocompleteVos().get(0);
					Catprestacion catprestacionAux = prestacionesDao.getCatPrestacionById(Integer.parseInt((String) autocompleteVoAux.getValue()));
					Catprestacion catprestacion = prestacionesDao.getCatPrestacion(agenda.getPrestacion());
					if (catprestacion.getPrestacionId() == catprestacionAux.getPrestacionId()) {
						if(agenda.getUsuarios().getUsuarioId() == citasPresencialesForm.getIdMedico()){
							if (FormatUtil.getTime(agenda.getHoraCita()).equals(citasPresencialesForm.getIdTiempo())) {
								citaDeAgenda = true;
							}
						}
					}
				}
			}
			
			
			int i = 0;
			Afiliado afiliado = agendaDao.getAfiliadoById(citasPresencialesForm.getIdAfiliado());
			Aseguradores aseguradores = aseguradorDao.getAseguradorById(citasPresencialesForm.getIdAsegurador());
			Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByCodigo(userInfo.getTx_Marca());
			Prestadores prestadores = prestadoresDao.getPrestadorById(citasPresencialesForm.getIdPrestador());
			Usuarios usuarioMedico = usuarioDao.getUsuarioById(citasPresencialesForm.getIdMedico());
			
			Cattipoidentificador cattipoidentificador = genericDao.getCatTipoIdentificadorById(citasPresencialesForm.getAfiliadotipoidentificador().getCattipoidentificador().getTipoIdentificadorId());
			
			citasPresencialesForm.getAgenda().setAfiliado(afiliado);
			citasPresencialesForm.getAgenda().setAseguradores(aseguradores);
			citasPresencialesForm.getAgenda().setCattipoidentificador(cattipoidentificador);
			citasPresencialesForm.getAgenda().setFechaCita(new Date());
			citasPresencialesForm.getAgenda().setHoraCita(FormatUtil.getTime(citasPresencialesForm.getIdTiempo()));
			citasPresencialesForm.getAgenda().setLugaresdeatencion(lugaresdeatencion);
			citasPresencialesForm.getAgenda().setPrestadores(prestadores);
			citasPresencialesForm.getAgenda().setUsuarios(usuarioMedico);
			citasPresencialesForm.getAgenda().setAsistio(1);
			
			AutocompleteVo autocompleteVoAux = citasPresencialesForm.getAutocompleteVos().get(0);
			Catprestacion catprestacionAux = prestacionesDao.getCatPrestacionById(Integer.parseInt((String) autocompleteVoAux.getValue()));
			
			citasPresencialesForm.getAgenda().setPrestacion(catprestacionAux.getCodigo());
			if (!citaDeAgenda) {
				agendaDao.saveAgenda(citasPresencialesForm.getAgenda());	
			}else{
				citasPresencialesForm.setAgenda(agenda);
			}
			
			if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.SIN_ASEGURADOR.getId()) {
				Collection<AfiliadoAsegurador> afiliadoAseguradors = agendaDao.getAfiliadoAseguradoByAfiliadoId(afiliado.getAfiliadoId());
				boolean existeSinAsegurador = false;
				if (afiliadoAseguradors!=null) {
					for (AfiliadoAsegurador afiliadoAsegurador : afiliadoAseguradors) {
						if(afiliadoAsegurador.getAseguradores().getAseguradorId() == AseguradoresEnum.SIN_ASEGURADOR.getId()){
							existeSinAsegurador = true;
						}
					}
				}
				if (!existeSinAsegurador) {
					AfiliadoAsegurador afiliadoAsegurador = new AfiliadoAsegurador();
					afiliadoAsegurador.setAfiliado(afiliado);
					afiliadoAsegurador.setAseguradores(aseguradorDao.getAseguradorById(citasPresencialesForm.getIdAsegurador()));
					AfiliadoAseguradorId afiliadoAseguradorId = new AfiliadoAseguradorId();
					afiliadoAseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
					afiliadoAseguradorId.setAseguradorId(citasPresencialesForm.getIdAsegurador());
					afiliadoAsegurador.setId(afiliadoAseguradorId);
					agendaDao.saveAfiliadoAsegurador(afiliadoAsegurador);
				}
			}
			
			if (citasPresencialesForm.getIdPersona()==0) {
				Atencionmedica atencionmedica = new Atencionmedica();
				atencionmedica.setAfiliado(afiliado);
				atencionmedica.setAseguradores(aseguradores);
				Catagendadopor catagendadopor = agendaDao.getCatAgendadoPor(AgendadoPorEnum.WC.getId());//
				atencionmedica.setCatagendadopor(catagendadopor);
				Catestatuscitas catestatuscitas = agendaDao.getCatEstatusCitas(EstatusCitasEnum.A.getId());//
				atencionmedica.setCatestatuscitas(catestatuscitas);
				atencionmedica.setCattipoidentificador(cattipoidentificador);
				atencionmedica.setConvenios(conveniosDao.getConveniosById(citasPresencialesForm.getIdConvenio()));
				atencionmedica.setFechaAsistio(new Date());
				atencionmedica.setHoraAsistio(new Time(new Date().getTime()));
				atencionmedica.setLugaresdeatencion(lugaresdeatencion);
				atencionmedica.setPrestadores(prestadores);
				atencionmedica.setUsuariosByUsuarioMedicoId(usuarioMedico);
				atencionmedica.setUsuariosByUsuarioRecibioId(userInfo.getUsuarios());
				atencionmedica.setEnrolamiento(1);
				agendaDao.saveAtencionMedica(atencionmedica);
				
				citasPresencialesForm.getAgenda().setAsistio(1);
				citasPresencialesForm.getAgenda().setAtencionmedica(atencionmedica);
				agendaDao.update(citasPresencialesForm.getAgenda());
				
				for (AutocompleteVo autocompleteVo : citasPresencialesForm.getAutocompleteVos()) {
					Atencionprestacion atencionprestacion = new Atencionprestacion();
					atencionprestacion.setAtencionmedica(agendaDao.getAtencionMedicaById(atencionmedica.getAtencionMedicaId()));
					Catprestacion catprestacion = prestacionesDao.getCatPrestacionById(Integer.parseInt((String) autocompleteVo.getValue()));
					atencionprestacion.setCatprestacion(catprestacion);
					
					AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
					atencionprestacionId.setAtencionMedicaId(atencionmedica.getAtencionMedicaId());
					atencionprestacionId.setPrestacionId(catprestacion.getPrestacionId());
					atencionprestacion.setId(atencionprestacionId);
					if (i==0) {
						atencionprestacion.setPrincipal(1);
					}else{
						atencionprestacion.setPrincipal(0);
					} 
					agendaDao.saveAtencionPrestacion(atencionprestacion);
					i++;
				}				
			}else{
				Atencionmedica atencionmedica = new Atencionmedica();
				atencionmedica.setAfiliado(afiliado);
				atencionmedica.setAseguradores(aseguradores);
				Catagendadopor catagendadopor = agendaDao.getCatAgendadoPor(AgendadoPorEnum.WC.getId());//
				atencionmedica.setCatagendadopor(catagendadopor);
				Catestatuscitas catestatuscitas = agendaDao.getCatEstatusCitas(EstatusCitasEnum.A.getId());//
				atencionmedica.setCatestatuscitas(catestatuscitas);
				atencionmedica.setCattipoidentificador(cattipoidentificador);
				atencionmedica.setConvenios(conveniosDao.getConveniosById(citasPresencialesForm.getIdConvenio()));
				atencionmedica.setFechaAsistio(new Date());
				atencionmedica.setHoraAsistio(new Time(new Date().getTime()));
				atencionmedica.setLugaresdeatencion(lugaresdeatencion);
				atencionmedica.setPrestadores(prestadores);
				atencionmedica.setUsuariosByUsuarioMedicoId(usuarioMedico);
				atencionmedica.setUsuariosByUsuarioRecibioId(userInfo.getUsuarios());
				atencionmedica.setPersonasdeconfianza(agendaDao.getPersonaDeConfianzaById(citasPresencialesForm.getIdPersona()));
				agendaDao.saveAtencionMedica(atencionmedica);

				citasPresencialesForm.getAgenda().setAsistio(1);
				citasPresencialesForm.getAgenda().setAtencionmedica(atencionmedica);
				agendaDao.update(citasPresencialesForm.getAgenda());
				
				for (AutocompleteVo autocompleteVo : citasPresencialesForm.getAutocompleteVos()) {
					Atencionprestacion atencionprestacion = new Atencionprestacion();
					atencionprestacion.setAtencionmedica(agendaDao.getAtencionMedicaById(atencionmedica.getAtencionMedicaId()));
					Catprestacion catprestacion = prestacionesDao.getCatPrestacionById(Integer.parseInt((String) autocompleteVo.getValue()));
					atencionprestacion.setCatprestacion(catprestacion);
					
					AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
					atencionprestacionId.setAtencionMedicaId(atencionmedica.getAtencionMedicaId());
					atencionprestacionId.setPrestacionId(catprestacion.getPrestacionId());
					atencionprestacion.setId(atencionprestacionId);
					if (i==0) {
						atencionprestacion.setPrincipal(1);
					}else{
						atencionprestacion.setPrincipal(0);
					} 
					agendaDao.saveAtencionPrestacion(atencionprestacion);
					i++;
				}
					
			}
		}catch(Exception e){
			e.printStackTrace();
			citasPresencialesForm.setBanderaError(true);
			citasPresencialesForm.setError("Surgio un error");
		}
	}

	@Override
	public void nuevoAfiliado(CitasPresencialesForm citasPresencialesForm,UserInfo userInfo) {
		Collection<String> errores = new ArrayList<>();
		if (citasPresencialesForm.getIdAsegurador()==-1) {
			errores.add("Debe de seleccionar un Asegurador");
		}
		
		if (citasPresencialesForm.getIdPrestador()==-1) {
			errores.add("Debe de seleccionar un Prestador");
		}
		
		if (citasPresencialesForm.getIdConvenio()==-1) {
			errores.add("Debe de seleccionar un Convenio");
		}
		
		if (citasPresencialesForm.getIdIdentificador() == -1) {
			errores.add("Debe de seleccionar Identificador");
		}else{
			if (citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor().trim().length()==0) {
				errores.add("Debe de ingresar el valor del identificador");	
			}
		}
		
		if (citasPresencialesForm.getSexoAfiliado()==null) {
			errores.add("Debe de seleccionar el sexo");
		}
		
		if (citasPresencialesForm.getAgenda().getAfiliado().getNombre().trim().length()==0) {
			errores.add("Debe de ingresar un Nombre");	
		}
		
		if (citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno().trim().length()==0) {
			errores.add("Debe de ingresar un Apellido Paterno");	
		}
		
		if (citasPresencialesForm.getFechaDeNacimiento().trim().length()==0) {
			errores.add("Debe de ingresar una fecha de nacimiento");	
		}
		
		if (citasPresencialesForm.getAgenda().getAfiliado().getTelefono1().trim().length()==0) {
			errores.add("Debe de ingresar un Telefono");	
		}
		
		if (errores.size() == 0) {
			Afiliado afiliado = new Afiliado();
			afiliado.setActivo(1);
			afiliado.setApellidoMaterno(citasPresencialesForm.getAgenda().getAfiliado().getApellidoMaterno());
			afiliado.setApellidoPaterno(citasPresencialesForm.getAgenda().getAfiliado().getApellidoPaterno());
			afiliado.setCatsexos(genericDao.getCatSexos(Integer.parseInt(citasPresencialesForm.getSexoAfiliado())));
			afiliado.setCattipoafiliado(genericDao.getCatTipoAfiliadoByTipo(TipoAfiliadoEnum.TITULAR_SIN_ASEGURADOR.getParentesco(), citasPresencialesForm.getIdAsegurador()));
			afiliado.setFechaAlta(new Date());
			afiliado.setFechaDeNacimiento(FormatUtil.getDate(citasPresencialesForm.getFechaDeNacimiento()));
			afiliado.setMail(citasPresencialesForm.getAgenda().getAfiliado().getMail());
			afiliado.setNombre(citasPresencialesForm.getAgenda().getAfiliado().getNombre());
			afiliado.setTelefono1(citasPresencialesForm.getAgenda().getAfiliado().getTelefono1());
			afiliado.setCatestadosByEstadoId(genericDao.getEstadoById(CatEstadosEnum.DISTRITO_FEDERAL.getId()));
			afiliado.setCatestadosByEstadoDeNacimientoId(genericDao.getEstadoById(CatEstadosEnum.DISTRITO_FEDERAL.getId()));
			agendaDao.saveAfiliado(afiliado);
			
			Afiliadotipoidentificador afiliadotipoidentificador = new Afiliadotipoidentificador();
			afiliadotipoidentificador.setAfiliado(afiliado);
			afiliadotipoidentificador.setCattipoidentificador(genericDao.getCatTipoIdentificadorById(citasPresencialesForm.getIdIdentificador()));
			afiliadotipoidentificador.setTipoIdValor(citasPresencialesForm.getAfiliadotipoidentificador().getTipoIdValor());
			AfiliadotipoidentificadorId afiliadotipoidentificadorId = new AfiliadotipoidentificadorId();
			afiliadotipoidentificadorId.setAfiliadoId(afiliado.getAfiliadoId());
			afiliadotipoidentificadorId.setTipoIdentificadorId(citasPresencialesForm.getIdIdentificador());
			afiliadotipoidentificador.setId(afiliadotipoidentificadorId);
			genericDao.saveAfiliadotipoIdentificar(afiliadotipoidentificador);
			
			AfiliadoAsegurador afiliadoAsegurador = new AfiliadoAsegurador();
			afiliadoAsegurador.setAfiliado(afiliado);
			afiliadoAsegurador.setAseguradores(aseguradorDao.getAseguradorById(citasPresencialesForm.getIdAsegurador()));
			AfiliadoAseguradorId afiliadoAseguradorId = new AfiliadoAseguradorId();
			afiliadoAseguradorId.setAfiliadoId(afiliado.getAfiliadoId());
			afiliadoAseguradorId.setAseguradorId(citasPresencialesForm.getIdAsegurador());
			afiliadoAsegurador.setId(afiliadoAseguradorId);
			agendaDao.saveAfiliadoAsegurador(afiliadoAsegurador);
			
			citasPresencialesForm.setIdAfiliado(afiliado.getAfiliadoId());
		}
		
		citasPresencialesForm.setErrores(errores);
		
	}

	@Override
	public void verificaAgenda(CitasPresencialesForm citasPresencialesForm) {
		if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.ISSSTE.getId()) {
			Collection<Agenda> agendas = agendaDao.getAgendaByCitaPresencial(citasPresencialesForm);
			Agenda agenda = null;
			for (Agenda agendaAux : agendas) {
				agenda = agendaAux;
				break;
			}
			
			if (agenda!=null) {
				citasPresencialesForm.setIdAgenda(agenda.getAgendaId());
				Prestacionasegurador prestacionasegurador = prestacionesDao.getPrestacionAsegurador(agenda.getPrestacion());
				AutocompleteVo autocompleteVo = new AutocompleteVo();
				autocompleteVo.setValue(""+prestacionasegurador.getPrestacionAseguradorId());
				autocompleteVo.setLabel(prestacionasegurador.getDescripcion());
				autocompleteVo.setCantidad(1);
				citasPresencialesForm.setAutocompleteVo(autocompleteVo);
				citasPresencialesForm.setIdMedico(agenda.getUsuarios().getUsuarioId());
				citasPresencialesForm.setIdTiempo(FormatUtil.getTime(agenda.getHoraCita()));	
			}	
		}else if (citasPresencialesForm.getIdAsegurador() == AseguradoresEnum.MEDIACCESS.getId()) {
			try {
				SvcSassSiStub svcSassSiStub = new SvcSassSiStub();
				BuscarCitasDeClinicaConFecha buscarCitasDeClinicaConFecha = new BuscarCitasDeClinicaConFecha();
				buscarCitasDeClinicaConFecha.setCodClinica(5);
				buscarCitasDeClinicaConFecha.setFechaCita(Calendar.getInstance());
				buscarCitasDeClinicaConFecha.setCodEmpresa(citasPresencialesForm.getCodEmpresa());
				buscarCitasDeClinicaConFecha.setCodProducto(citasPresencialesForm.getCodProducto());
				BuscarCitasDeClinicaConFechaResponse buscarCitasDeClinicaConFechaResponse = svcSassSiStub.buscarCitasDeClinicaConFecha(buscarCitasDeClinicaConFecha);
				ArrayOfCitaCto arrayOfCitaCto = buscarCitasDeClinicaConFechaResponse.getBuscarCitasDeClinicaConFechaResult();
				CitaCto[] citaCtos = arrayOfCitaCto.getCitaCto();
				if (citaCtos!=null) {
					for (CitaCto citaCto : citaCtos) {
						//FALTA IMPLEMENTACION
					}	
				}
				
			} catch (ISvcSassc_BuscarCitasDeClinicaConFecha_DefaultFaultContractFault_FaultMessage e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void autorizacionEspecial(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo) {
//		Permisoespecialafiliado permisoespecialafiliado = new Permisoespecialafiliado();
//		Afiliado afiliado = agendaDao.getAfiliadoById(citasPresencialesForm.getIdAfiliado());
//		permisoespecialafiliado.setAfiliado(afiliado);
//		permisoespecialafiliado.setAseguradores(aseguradorDao.getAseguradorById(citasPresencialesForm.getIdAsegurador()));
//		permisoespecialafiliado.setFechaInicio(new Date());
//		PermisoespecialafiliadoId permisoespecialafiliadoId = new PermisoespecialafiliadoId();
//		permisoespecialafiliadoId.setAfiliadoId(citasPresencialesForm.getIdAfiliado());
//		permisoespecialafiliado.setId(permisoespecialafiliadoId);
//		
//		permisoespecialafiliado.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(citasPresencialesForm.getIdLugarAtencion()));
//		if (citasPresencialesForm.getAutorizacionEspecial() == 1) {
//			permisoespecialafiliado.setTipoAutorizacion(CatTipoAutorizacionEnum.VIGENCIA.getId());	
//		}else{
//			permisoespecialafiliado.setTipoAutorizacion(CatTipoAutorizacionEnum.AUTENTIA.getId());
//			if (FormatUtil.getEdad(afiliado.getFechaDeNacimiento().getTime()) > 65) {
//				permisoespecialafiliado.setUsuarioAutorizaId(userInfo.getUsuarios().getUsuarioId());
//				permisoespecialafiliado.setCatcausas(agendaDao.getCatCausas(CatCausasEnum.MAYOR_DE_65.getId()));
//				permisoespecialafiliado.setFechaFin(new Date());
//				permisoespecialafiliado.setDuracion(CatTipoDuracionEnum.PERMANENTE.getId());
//				citasPresencialesForm.setMayorEdad(1);
//			}
//		}
//		
//		
//		
//		permisoespecialafiliado.setUsuarios(userInfo.getUsuarios());
//		agendaDao.savePermisoEspecialAfiliado(permisoespecialafiliado);
//		
	}

	@Override
	public void verificaOrdenServicio(CitasPresencialesForm citasPresencialesForm) {
		Afiliado afiliado = agendaDao.getAfiliadoById(citasPresencialesForm.getIdAfiliado());
		Iterator<Atencionmedica> atencionMedicaIterator = afiliado.getAtencionmedicas().iterator();
		Collection<Prestacionesportomar> prestacionesportomars = new ArrayList<>();
		Collection<PrestacionesPorTomarVo> prestacionesPorTomarVos = new ArrayList<>();
		while (atencionMedicaIterator.hasNext()) {
			Atencionmedica atencionmedica = (Atencionmedica) atencionMedicaIterator.next();
			Iterator<Prestacionesportomar> prestacionesPorTomarIterator = atencionmedica.getPrestacionesportomarsForAtencionMedicaId().iterator();
			while (prestacionesPorTomarIterator.hasNext()) {
				Prestacionesportomar prestacionesportomar = (Prestacionesportomar) prestacionesPorTomarIterator.next();
				if(prestacionesportomar.getOrden().equals(citasPresencialesForm.getOrdenServicio())){
					prestacionesportomars.add(prestacionesportomar);
				}
			}
		}
		for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
			PrestacionesPorTomarVo prestacionesPorTomarVo = new PrestacionesPorTomarVo();
			if (prestacionesportomar.getAtencionmedicaByAtencionMedicaIdTomada()!=null) {
				prestacionesPorTomarVo.setIdAtencionMedicaPorTomar(prestacionesportomar.getAtencionmedicaByAtencionMedicaIdTomada().getAtencionMedicaId());	
				prestacionesPorTomarVo.setFecha(FormatUtil.getDate(prestacionesportomar.getAtencionmedicaByAtencionMedicaIdTomada().getFechaAsistio()));
			}else{
				prestacionesPorTomarVo.setIdAtencionMedicaPorTomar(0);
			}
			
			prestacionesPorTomarVo.setIdPrestacion(prestacionesportomar.getCatprestacion().getPrestacionId());
			prestacionesPorTomarVo.setPrestacion(prestacionesportomar.getCatprestacion().getDescripcion());	
//			Iterator<Prestacionesaseguradorequivalencias> equivalenciasIterator = prestacionesportomar.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
//			while(equivalenciasIterator.hasNext()){
//				Prestacionesaseguradorequivalencias equivalencia = equivalenciasIterator.next();
//				if (equivalencia.getPrestacionasegurador().getAseguradores().getAseguradorId() == citasPresencialesForm.getIdAsegurador()) {
//					prestacionesPorTomarVo.setIdPrestacion(equivalencia.getPrestacionasegurador().getPrestacionAseguradorId());
//					prestacionesPorTomarVo.setPrestacion(equivalencia.getPrestacionasegurador().getDescripcion());					
//				}
//			}
			
			
			
			prestacionesPorTomarVo.setOrden(prestacionesportomar.getOrden());
			prestacionesPorTomarVo.setCantidad(prestacionesportomar.getCantidad());
			prestacionesPorTomarVos.add(prestacionesPorTomarVo);
		}
		citasPresencialesForm.setPrestacionesPorTomarVos(prestacionesPorTomarVos);
	}

	@Override
	public void valorizar(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo) {
//		ValorizarService valorizarService = new ValorizarService();
//		ValorizarDelegate delegate = valorizarService.getValorizarPort();
//		ValorizarWebRequestVo valorizarWebRequestVo = new ValorizarWebRequestVo();
//		for (AutocompleteVo autocompleteVo : citasPresencialesForm.getAutocompleteVos()) {
//			if (autocompleteVo.getValue()!=null) {
//				valorizarWebRequestVo.getPrestaciones().add(Integer.parseInt(autocompleteVo.getValue()));	
//			}
//		}
//		ValorizarWebResponseVo valorizarWebResponseVo = delegate.valoriza(valorizarWebRequestVo);
//		for (Datos datos : valorizarWebResponseVo.getDatos()) {
			for (AutocompleteVo autocompleteVo : citasPresencialesForm.getAutocompleteVos()) {
				if (autocompleteVo.getValue()!=null) {
//					if(datos.getIdPrestacion() == Integer.parseInt(autocompleteVo.getValue())){
//						autocompleteVo.setAporte(""+datos.getAporte());
//						autocompleteVo.setCopago(""+datos.getCopago());
//						autocompleteVo.setValor(""+datos.getValor());
						autocompleteVo.setAporte(""+0);
						autocompleteVo.setCopago(""+0);
						autocompleteVo.setValor(""+0);
						
						break;
						
//					} 					
				}
			}
//		}
	}
	
	public void guardar(CitasPresencialesForm citasPresencialesForm, UserInfo userInfo) {
		try{
			int i=0;
			boolean citaDeAgenda = false;
			Agenda agenda = null;
			int tipoAtencionMedica = TipoAtencionMedicaEnum.OTRO.getId();
			if (citasPresencialesForm.getIdAgenda()!=0) {
				citaDeAgenda = true;
				agenda = agendaDao.getAgendaById(citasPresencialesForm.getIdAgenda());
			}
			
			Afiliado afiliado = agendaDao.getAfiliadoById(citasPresencialesForm.getIdAfiliado());
			afiliado.setTelefono1(citasPresencialesForm.getAgenda().getAfiliado().getTelefono1());
			afiliado.setMail(citasPresencialesForm.getAgenda().getAfiliado().getMail());
			
			Aseguradores aseguradores = aseguradorDao.getAseguradorById(citasPresencialesForm.getIdAsegurador());
			Lugaresdeatencion lugaresdeatencion = userInfo.getLugaresdeatencion();
			Prestadores prestadores = prestadoresDao.getPrestadorById(citasPresencialesForm.getIdPrestador());
			Usuarios usuarioMedico = usuarioDao.getUsuarioById(citasPresencialesForm.getIdMedico());
			
			Cattipoidentificador cattipoidentificador = genericDao.getCatTipoIdentificadorById(citasPresencialesForm.getAfiliadotipoidentificador().getCattipoidentificador().getTipoIdentificadorId());
			boolean validacionMedico = true;
			String mensajeError = "";
			if (citasPresencialesForm.getIdMedico()!=0 && !citaDeAgenda) {
				Usuarios usuario = usuarioDao.getUsuarioById(citasPresencialesForm.getIdMedico());
				Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = usuario.getUsuarioespecialidadeses().iterator();
				Collection<Integer> especialidadesMedico = new ArrayList<>();
				while (usuarioEspecialidadesIterator.hasNext()) {
					Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
					especialidadesMedico.add(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
				}
				
				Iterator<Usuariorol> usuarioRolIterator = usuarioMedico.getUsuariorols().iterator();
				while (usuarioRolIterator.hasNext()) {
					Usuariorol usuariorol = (Usuariorol) usuarioRolIterator.next();
					if (usuariorol.getRoles().getRolId() == RolesEnum.MEDICO.getId()) {
						tipoAtencionMedica = TipoAtencionMedicaEnum.MEDICO.getId();
						break;
					}else if (usuariorol.getRoles().getRolId() == RolesEnum.DENTISTA.getId()) {
						tipoAtencionMedica = TipoAtencionMedicaEnum.DENTAL.getId();
						break;
					}
				}
				
				Collection<Integer> especialidades = new ArrayList<>();
				Collection<Agenda> agendas = agendaDao.getAgendaByIdAfiliado(citasPresencialesForm.getIdAfiliado());
				for (Agenda agendaAux : agendas) {
					Iterator<Usuarioespecialidades> usuarioEspecialidadesIteratorAux =	agendaAux.getUsuarios().getUsuarioespecialidadeses().iterator();
					while (usuarioEspecialidadesIteratorAux.hasNext()) {
						Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIteratorAux.next();
						especialidades.add(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
					}
					
					if(agendaAux.getUsuarios().getUsuarioId() == citasPresencialesForm.getIdMedico()){
						validacionMedico = false;
					}
				}
				
				if (validacionMedico) {
					for (Integer idEspecialidad : especialidades) {
						for (Integer idEspecialidadMedico : especialidadesMedico) {
							if (idEspecialidad == idEspecialidadMedico) {
								if(idEspecialidad != CatEspecialidadesMedicasEnum.MEDICINA_GENERAL.getId()){
									mensajeError = "Se selecciono otro medico con la misma especialidad";
									break;									
								}
							}
						}
					}
				}else{
					validacionMedico = false;
					mensajeError = "Ya existe una consulta con el mismo medico";
				}
				
				agenda = new Agenda();
				agenda.setAfiliado(afiliado);
				agenda.setAseguradores(aseguradores);
				agenda.setCattipoidentificador(cattipoidentificador);
				agenda.setFechaCita(new Date());
				agenda.setHoraCita(FormatUtil.getTime(citasPresencialesForm.getIdTiempo()));
				agenda.setLugaresdeatencion(lugaresdeatencion);
				agenda.setPrestadores(prestadores);
				agenda.setUsuarios(usuarioMedico);
				agenda.setAsistio(1);
			}
			
			if (mensajeError.length()!=0) {
				citasPresencialesForm.setError(mensajeError);
			}else{
				agendaDao.updateAfiliado(afiliado);
				Catprestacion prestacionPrincipal = null;
				for (AutocompleteVo autocompleteVo : citasPresencialesForm.getAutocompleteVos()) {
					if (autocompleteVo.getValue()!=null) {
						prestacionPrincipal = prestacionesDao.getCatPrestacionById(Integer.parseInt((String) autocompleteVo.getValue())); 
						break;
					}
				}
				
				if(citasPresencialesForm.getIdMedico()!=0){
					agenda.setPrestacion(prestacionPrincipal.getCodigo());				
				}

				if (!citaDeAgenda && citasPresencialesForm.getIdMedico()!=0) {
					agendaDao.saveAgenda(agenda);	
				}
				
				if (citasPresencialesForm.getIdPersona()==0) {
					Atencionmedica atencionmedica = new Atencionmedica();
					atencionmedica.setAfiliado(afiliado);
					atencionmedica.setAseguradores(aseguradores);
					Catagendadopor catagendadopor = agendaDao.getCatAgendadoPor(AgendadoPorEnum.WC.getId());//
					atencionmedica.setCatagendadopor(catagendadopor);
					Catestatuscitas catestatuscitas = agendaDao.getCatEstatusCitas(EstatusCitasEnum.A.getId());//
					atencionmedica.setCatestatuscitas(catestatuscitas);
					atencionmedica.setCattipoidentificador(cattipoidentificador);
					atencionmedica.setConvenios(conveniosDao.getConveniosById(citasPresencialesForm.getIdConvenio()));
					atencionmedica.setFechaAsistio(new Date());
					atencionmedica.setHoraAsistio(new Time(new Date().getTime()));
					atencionmedica.setLugaresdeatencion(lugaresdeatencion);
					atencionmedica.setPrestadores(prestadores);
					atencionmedica.setUsuariosByUsuarioMedicoId(usuarioMedico);
					atencionmedica.setUsuariosByUsuarioRecibioId(userInfo.getUsuarios());
					atencionmedica.setEnrolamiento(1);
					atencionmedica.setCatestatusfirmaByEstatusFirmaMedico(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_PENDIENTE.getId()));
					atencionmedica.setCatestatusfirmaByEstatusFirmaPaciente(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_PENDIENTE.getId()));
					atencionmedica.setTipoatencionmedica(atencionMedicaDao.getTipoAtencionMedicaById(tipoAtencionMedica));
					if(citasPresencialesForm.isAutentia()){
						atencionmedica.setCatestatusatencionidentidad(atencionMedicaDao.getCatEstatusAtencionIdentidadById(CatEstatusAtencionIdentidadEnum.IDENTIFICACION_AUTORIZADA.getId()) );	
					}else{
						atencionmedica.setCatestatusatencionidentidad(atencionMedicaDao.getCatEstatusAtencionIdentidadById(CatEstatusAtencionIdentidadEnum.IDENTIFICACION_PENDIENTE.getId()) );
					}
					
					if(citasPresencialesForm.isVigencia()){
						atencionmedica.setCatestatusatencionvigencia(atencionMedicaDao.getCatEstatusAtencionVigenciaById(CatEstatusAtencionVigenciaEnum.VIGENCIA_AUTORIZADA.getId()));
					}else{
						atencionmedica.setCatestatusatencionvigencia(atencionMedicaDao.getCatEstatusAtencionVigenciaById(CatEstatusAtencionVigenciaEnum.VIGENCIA_PENDIENTE.getId()));
					}	
					
					if(citasPresencialesForm.isVigencia() && citasPresencialesForm.isAutentia()){
						atencionmedica.setCatestatusrecepcion(agendaDao.getCatEstatusRecepcionById(CatEstatusRecepcionEnum.AUTORIZADO.getId()));	
					}else{
						atencionmedica.setCatestatusrecepcion(agendaDao.getCatEstatusRecepcionById(CatEstatusRecepcionEnum.PENDIENTE.getId()));
					}
					
					agendaDao.saveAtencionMedica(atencionmedica);
					citasPresencialesForm.setIdAtencion(atencionmedica.getAtencionMedicaId());
					Folio folio = foliosDao.getFolioById(FoliosEnum.FOLIO_DE_ATENCION.getId(),userInfo.getLugaresdeatencion().getLugarDeAtencionId());
					String folioS = ""+atencionmedica.getAtencionMedicaId();
					atencionmedica.setFolio(folioS);
					agendaDao.updateAtencionMedica(atencionmedica);
					citasPresencialesForm.setFolio(folioS);
				
					if (citasPresencialesForm.getIdMedico()!=0) {
						agenda.setAsistio(1);
						agenda.setAtencionmedica(atencionmedica);
						agendaDao.update(agenda);					
					}

					
					for (AutocompleteVo autocompleteVo : citasPresencialesForm.getAutocompleteVos()) {
						if(autocompleteVo.getValue()!=null){
							Atencionprestacion atencionprestacion = new Atencionprestacion();
							atencionprestacion.setAtencionmedica(agendaDao.getAtencionMedicaById(atencionmedica.getAtencionMedicaId()));
							atencionprestacion.setCatprestacion(prestacionesDao.getCatPrestacionById(Integer.parseInt((String) autocompleteVo.getValue())));	
							
							AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
							atencionprestacionId.setAtencionMedicaId(atencionmedica.getAtencionMedicaId());
							atencionprestacionId.setPrestacionId(atencionprestacion.getCatprestacion().getPrestacionId());
							atencionprestacion.setId(atencionprestacionId);
							if (!autocompleteVo.getAporte().equals("")) {
								atencionprestacion.setAporteAsegurador(Float.parseFloat(autocompleteVo.getAporte()));	
							}
							if (!autocompleteVo.getCopago().equals("")) {
								atencionprestacion.setCopagoAfiliado(Float.parseFloat(autocompleteVo.getCopago()));	
							}
							if (!autocompleteVo.getValor().equals("")) {
								atencionprestacion.setValorPrestacionConvenio(Float.parseFloat(autocompleteVo.getValor()));	
							}
							
							atencionprestacion.setCantidad(autocompleteVo.getCantidad());
							if (i==0) {
								atencionprestacion.setPrincipal(1);
							}else{
								atencionprestacion.setPrincipal(0);
							} 
							agendaDao.saveAtencionPrestacion(atencionprestacion);
							
							if (autocompleteVo.getOrdenServicio()!=null) {
								Prestacionesportomar prestacionesportomar = estudiosMedicosDao.getPrestacionPorTomarByOrden(autocompleteVo.getOrdenServicio(),atencionprestacionId.getPrestacionId());
								prestacionesportomar.setAtencionmedicaByAtencionMedicaIdTomada(atencionmedica);
								estudiosMedicosDao.updatePrestacionPorTomar(prestacionesportomar);
							}
							i++;							
						}
					}				
				}else{
					Atencionmedica atencionmedica = new Atencionmedica();
					atencionmedica.setAfiliado(afiliado);
					atencionmedica.setAseguradores(aseguradores);
					Catagendadopor catagendadopor = agendaDao.getCatAgendadoPor(AgendadoPorEnum.WC.getId());//
					atencionmedica.setCatagendadopor(catagendadopor);
					Catestatuscitas catestatuscitas = agendaDao.getCatEstatusCitas(EstatusCitasEnum.A.getId());//
					atencionmedica.setCatestatuscitas(catestatuscitas);
					atencionmedica.setCattipoidentificador(cattipoidentificador);
					atencionmedica.setConvenios(conveniosDao.getConveniosById(citasPresencialesForm.getIdConvenio()));
					atencionmedica.setFechaAsistio(new Date());
					atencionmedica.setHoraAsistio(new Time(new Date().getTime()));
					atencionmedica.setLugaresdeatencion(lugaresdeatencion);
					atencionmedica.setPrestadores(prestadores);
					atencionmedica.setUsuariosByUsuarioMedicoId(usuarioMedico);
					atencionmedica.setUsuariosByUsuarioRecibioId(userInfo.getUsuarios());
					atencionmedica.setPersonasdeconfianza(agendaDao.getPersonaDeConfianzaById(citasPresencialesForm.getIdPersona()));
					atencionmedica.setCatestatusfirmaByEstatusFirmaMedico(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_PENDIENTE.getId()));
					atencionmedica.setCatestatusfirmaByEstatusFirmaPaciente(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_PENDIENTE.getId()));
					
					if(citasPresencialesForm.isAutentia()){
						atencionmedica.setCatestatusatencionidentidad(atencionMedicaDao.getCatEstatusAtencionIdentidadById(CatEstatusAtencionIdentidadEnum.IDENTIFICACION_AUTORIZADA.getId()) );	
					}else{
						atencionmedica.setCatestatusatencionidentidad(atencionMedicaDao.getCatEstatusAtencionIdentidadById(CatEstatusAtencionIdentidadEnum.IDENTIFICACION_PENDIENTE.getId()) );
					}
					
					if(citasPresencialesForm.isVigencia()){
						atencionmedica.setCatestatusatencionvigencia(atencionMedicaDao.getCatEstatusAtencionVigenciaById(CatEstatusAtencionVigenciaEnum.VIGENCIA_AUTORIZADA.getId()));
					}else{
						atencionmedica.setCatestatusatencionvigencia(atencionMedicaDao.getCatEstatusAtencionVigenciaById(CatEstatusAtencionVigenciaEnum.VIGENCIA_PENDIENTE.getId()));
					}
					
					if(citasPresencialesForm.isVigencia() && citasPresencialesForm.isAutentia()){
						atencionmedica.setCatestatusrecepcion(agendaDao.getCatEstatusRecepcionById(CatEstatusRecepcionEnum.AUTORIZADO.getId()));	
					}else{
						atencionmedica.setCatestatusrecepcion(agendaDao.getCatEstatusRecepcionById(CatEstatusRecepcionEnum.PENDIENTE.getId()));
					}					
					
					agendaDao.saveAtencionMedica(atencionmedica);
					citasPresencialesForm.setIdAtencion(atencionmedica.getAtencionMedicaId());
					Folio folio = foliosDao.getFolioById(FoliosEnum.FOLIO_DE_ATENCION.getId(),userInfo.getLugaresdeatencion().getLugarDeAtencionId());
					String folioS = ""+atencionmedica.getAtencionMedicaId();
					atencionmedica.setFolio(folioS);
					agendaDao.updateAtencionMedica(atencionmedica);
					citasPresencialesForm.setFolio(folioS);
					
					if (citasPresencialesForm.getIdMedico()!=0) {
						agenda.setAsistio(1);
						agenda.setAtencionmedica(atencionmedica);
						agendaDao.update(agenda);
					}
					for (AutocompleteVo autocompleteVo : citasPresencialesForm.getAutocompleteVos()) {
						if(autocompleteVo.getValue()!=null){
							Atencionprestacion atencionprestacion = new Atencionprestacion();
							atencionprestacion.setAtencionmedica(agendaDao.getAtencionMedicaById(atencionmedica.getAtencionMedicaId()));
							atencionprestacion.setCatprestacion(prestacionesDao.getCatPrestacionById(Integer.parseInt((String) autocompleteVo.getValue())));	
							
							AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
							atencionprestacionId.setAtencionMedicaId(atencionmedica.getAtencionMedicaId());
							atencionprestacionId.setPrestacionId(atencionprestacion.getCatprestacion().getPrestacionId());					
							if (!autocompleteVo.getAporte().equals("")) {
								atencionprestacion.setAporteAsegurador(Float.parseFloat(autocompleteVo.getAporte()));	
							}
							if (!autocompleteVo.getCopago().equals("")) {
								atencionprestacion.setCopagoAfiliado(Float.parseFloat(autocompleteVo.getCopago()));	
							}
							if (!autocompleteVo.getValor().equals("")) {
								atencionprestacion.setValorPrestacionConvenio(Float.parseFloat(autocompleteVo.getValor()));	
							}
							atencionprestacion.setCantidad(autocompleteVo.getCantidad());
							atencionprestacion.setId(atencionprestacionId);
							if (i==0) {
								atencionprestacion.setPrincipal(1);
							}else{
								atencionprestacion.setPrincipal(0);
							} 
							agendaDao.saveAtencionPrestacion(atencionprestacion);
							
							if (autocompleteVo.getOrdenServicio()!=null) {
								Prestacionesportomar prestacionesportomar = estudiosMedicosDao.getPrestacionPorTomarByOrden(autocompleteVo.getOrdenServicio(),atencionprestacionId.getPrestacionId());
								prestacionesportomar.setAtencionmedicaByAtencionMedicaIdTomada(atencionmedica);
								estudiosMedicosDao.updatePrestacionPorTomar(prestacionesportomar);
							}
							i++;							
						}
					}
						
				}
			}


		}catch(Exception e){
			e.printStackTrace();
			citasPresencialesForm.setBanderaError(true);
			citasPresencialesForm.setError("Surgio un error");
		}		
	}

}

