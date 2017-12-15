package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAtencionMedicaDao;
import com.mx.sab.dao.IAutorizacionEspecialDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.CatEspecialidadesMedicasEnum;
import com.mx.sab.form.RecepcionAtencionesPendientesForm;
import com.mx.sab.form.RecuperarAtencionForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.AtencionprestacionId;
import com.mx.sab.model.Catestatusrecepcion;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesportomar;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IRecepcionAtencionesPendientesService;
import com.mx.sab.servicios.web.Datos;
import com.mx.sab.servicios.web.ValorizarDelegate;
import com.mx.sab.servicios.web.ValorizarService;
import com.mx.sab.servicios.web.ValorizarWebRequestVo;
import com.mx.sab.servicios.web.ValorizarWebResponseVo;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.PermisoEspecialVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class RecepcionAtencionesPendientesServiceImpl implements IRecepcionAtencionesPendientesService {

	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IAtencionMedicaDao atencionMedicaDao;
	
	@Autowired
	private IAutorizacionEspecialDao autorizacionEspecialDao;
	
	@Autowired
	private IPrestacionesDao prestacionesDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public Collection<PermisoEspecialVo> getAtencionesPendientes(RecepcionAtencionesPendientesForm recepcionAtencionesPendientesForm, UserInfo userInfo) {
		//log.info("getAtencionesPendientes service");
		
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<Atencionmedica> atencionesmedicas = null;
		Collection<PermisoEspecialVo> permisoEspecialVos = new ArrayList<>();
		int totalPermisosEspeciales  = 0;
		totalPermisosEspeciales = atencionMedicaDao.getAtencionesPendientesCount(userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		if (totalPermisosEspeciales>0) {
			paginasTotal = totalPermisosEspeciales / filas;
			if (totalPermisosEspeciales % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				recepcionAtencionesPendientesForm.setDisplay(7);
			}else {
				recepcionAtencionesPendientesForm.setDisplay(paginasTotal);
			}
			
			recepcionAtencionesPendientesForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			atencionesmedicas= atencionMedicaDao.getAtencionesPendientes(inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId());
			
			
			for (Atencionmedica atencionmedica : atencionesmedicas) {
				Afiliado afiliado = atencionmedica.getAfiliado();
				PermisoEspecialVo permisoEspecialVo = new PermisoEspecialVo();				
				permisoEspecialVo.setIdAfiliado(afiliado.getAfiliadoId());
				permisoEspecialVo.setIdAtencionMedica(atencionmedica.getAtencionMedicaId());
				permisoEspecialVo.setNombre(afiliado.getNombre() +" "+ afiliado.getApellidoPaterno() +" "+ afiliado.getApellidoMaterno());

				int vigencia = atencionmedica.getCatestatusatencionvigencia().getId();
				int identidad = atencionmedica.getCatestatusatencionidentidad().getId();
				int huellaMedico = atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId();
				String textoCausa = "";
				String textoIdentidad = "";
				String textoVigencia = "";
				
				if(huellaMedico == 2){
					textoVigencia = atencionmedica.getCatestatusatencionvigencia().getDescripcion();
					textoIdentidad = atencionmedica.getCatestatusatencionidentidad().getDescripcion();
					
					if(textoVigencia != "" && textoIdentidad != ""){
						textoCausa = textoVigencia+" / "+ textoIdentidad;	
					}else{
						textoCausa = textoVigencia+" "+ textoIdentidad;
					}
					
					permisoEspecialVo.setMotivo(textoCausa); 
					permisoEspecialVo.setAutorizado(false);
					
					if((identidad == 2 || identidad == 3) && (vigencia == 2 || vigencia == 4)){
						permisoEspecialVo.setAutorizado(true);
					}					
					
					permisoEspecialVos.add(permisoEspecialVo);	
				}
			}
		}
		
		return permisoEspecialVos;
	}

	@Override
	public Collection<PermisoEspecialVo> getAtencionesPendientes(int page, UserInfo userInfo) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<PermisoEspecialVo> permisoEspecialVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Atencionmedica> atencionmedicasVigencia = atencionMedicaDao.getAtencionesPendientes(inicio,fin, userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		
		for (Atencionmedica atencionmedica : atencionmedicasVigencia) {
			
			Afiliado afiliado = atencionmedica.getAfiliado();
			PermisoEspecialVo permisoEspecialVo = new PermisoEspecialVo();				
			permisoEspecialVo.setIdAfiliado(afiliado.getAfiliadoId());
			permisoEspecialVo.setIdAtencionMedica(atencionmedica.getAtencionMedicaId());
			permisoEspecialVo.setNombre(afiliado.getNombre() +" "+ afiliado.getApellidoPaterno() +" "+ afiliado.getApellidoMaterno());

			int vigencia = atencionmedica.getCatestatusatencionvigencia().getId();
			int identidad = atencionmedica.getCatestatusatencionidentidad().getId();
			int huellaMedico = atencionmedica.getCatestatusfirmaByEstatusFirmaMedico().getId();
			String textoCausa = "";
			String textoIdentidad = "";
			String textoVigencia = "";
			
			if(huellaMedico == 2){
				textoVigencia = atencionmedica.getCatestatusatencionvigencia().getDescripcion();
				textoIdentidad = atencionmedica.getCatestatusatencionidentidad().getDescripcion();
				
				if(textoVigencia != "" && textoIdentidad != ""){
					textoCausa = textoVigencia+" / "+ textoIdentidad;	
				}else{
					textoCausa = textoVigencia+" "+ textoIdentidad;
				}
				
				permisoEspecialVo.setMotivo(textoCausa); 
				permisoEspecialVo.setAutorizado(false);
				
				if((identidad == 2 || identidad == 3) && (vigencia == 2 || vigencia == 3)){
					permisoEspecialVo.setAutorizado(true);
				}					
				
				permisoEspecialVos.add(permisoEspecialVo);	
			}
		}
		
		
		return permisoEspecialVos;
	}

	@Override
	public RecuperarAtencionForm getAtencionById(int idAtencion){
		RecuperarAtencionForm recuperarAtencionForm = new RecuperarAtencionForm();
		Atencionmedica atencionBase = atencionMedicaDao.getAtencionMedicaById(idAtencion);
		recuperarAtencionForm.setNombre(atencionBase.getAfiliado().getNombre());
		recuperarAtencionForm.setApellidoPaterno(atencionBase.getAfiliado().getApellidoPaterno());
		recuperarAtencionForm.setApellidoMaterno(atencionBase.getAfiliado().getApellidoMaterno());
		recuperarAtencionForm.setIdAfiliado(atencionBase.getAfiliado().getAfiliadoId());
		recuperarAtencionForm.setIdAsegurador(atencionBase.getAseguradores().getAseguradorId());
		recuperarAtencionForm.setIdAtencionMedica(atencionBase.getAtencionMedicaId());
		
		recuperarAtencionForm.setDescripcionAsegurador(atencionBase.getAseguradores().getNombreRazonSocial());
		recuperarAtencionForm.setIdPrestador(atencionBase.getPrestadores().getPrestadorId());
		recuperarAtencionForm.setDescripcionPrestador(atencionBase.getPrestadores().getNombreRazonSocial());
		recuperarAtencionForm.setIdConvenio(atencionBase.getConvenios().getConvenioId());
		recuperarAtencionForm.setDescripcionConvenio(atencionBase.getConvenios().getConvenio());
		recuperarAtencionForm.setIdIdentificador(atencionBase.getCattipoidentificador().getTipoIdentificadorId());
		recuperarAtencionForm.setFolio(atencionBase.getFolio());
		recuperarAtencionForm.setDescripcionIdentificador(atencionBase.getCattipoidentificador().getTipoIdentificador());
		if(atencionBase.getPersonasdeconfianza() == null){
			recuperarAtencionForm.setAsistio(true);
		}else{
			recuperarAtencionForm.setAsistio(false);
		}
		Iterator<Agenda> agendaIterator = atencionBase.getAgendas().iterator();
		while(agendaIterator.hasNext()){
			Agenda agenda = agendaIterator.next();
			recuperarAtencionForm.setIdAgenda(agenda.getAgendaId());
		}
		
		Iterator<Afiliadotipoidentificador> afiliadoTipoIterator = atencionBase.getAfiliado().getAfiliadotipoidentificadors().iterator();
		while(afiliadoTipoIterator.hasNext()){
			Afiliadotipoidentificador afiliadoTipoIdentificador = afiliadoTipoIterator.next();
			recuperarAtencionForm.setIdPaciente(afiliadoTipoIdentificador.getTipoIdValor());	
		}
		
		recuperarAtencionForm.setFechaDeNac(FormatUtil.getDate(atencionBase.getAfiliado().getFechaDeNacimiento()));
		recuperarAtencionForm.setMail(atencionBase.getAfiliado().getMail());
		recuperarAtencionForm.setTelefono(atencionBase.getAfiliado().getTelefono1());
		
		Collection<Atencionprestacion> prestacionesPorTomar = atencionMedicaDao.getPrestacionesPendientes(idAtencion);
		List<AutocompleteVo> porTomarVos = new ArrayList<>(); 
		for(Atencionprestacion prestacion : prestacionesPorTomar ){
			AutocompleteVo porTomarVo = new AutocompleteVo();
			porTomarVo.setCantidad(prestacion.getCantidad());		
			Iterator<Prestacionesaseguradorequivalencias> equivalenciasIterator = prestacion.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator(); 
			while(equivalenciasIterator.hasNext()){
				Prestacionesaseguradorequivalencias equivalencia = equivalenciasIterator.next();
				if(atencionBase.getAseguradores().getAseguradorId() == equivalencia.getPrestacionasegurador().getAseguradores().getAseguradorId()){
					porTomarVo.setCodigo(equivalencia.getPrestacionasegurador().getCodigo());
					porTomarVo.setLabel(equivalencia.getPrestacionasegurador().getDescripcion());
					porTomarVo.setValue(""+equivalencia.getPrestacionasegurador().getPrestacionAseguradorId());					
				}

			}
			porTomarVos.add(porTomarVo);
		}
		recuperarAtencionForm.setAutocompleteVos(porTomarVos);
		
		return recuperarAtencionForm;
		
	}
	
	@Override
	public void valorizar(RecuperarAtencionForm recuperarAtencionForm, UserInfo userInfo) {
		
//		ValorizarService valorizarService = new ValorizarService();
//		ValorizarDelegate delegate = valorizarService.getValorizarPort();
//		ValorizarWebRequestVo valorizarWebRequestVo = new ValorizarWebRequestVo();
//		for (AutocompleteVo autocompleteVo : recuperarAtencionForm.getAutocompleteVos()) {
//			if(autocompleteVo.getValue() != null){
//				valorizarWebRequestVo.getPrestaciones().add(Integer.parseInt(autocompleteVo.getValue()));
//			}
//			
//		}
//			ValorizarWebResponseVo valorizarWebResponseVo = delegate.valoriza(valorizarWebRequestVo);
//			for (Datos datos : valorizarWebResponseVo.getDatos()) {
				for (AutocompleteVo autocompleteVo : recuperarAtencionForm.getAutocompleteVos()) {
					if(autocompleteVo.getValue() != null){
//						if(datos.getIdPrestacion() == Integer.parseInt(autocompleteVo.getValue())){
//							autocompleteVo.setAporte(""+datos.getAporte());
//							autocompleteVo.setCopago(""+datos.getCopago());
//							autocompleteVo.setValor(""+datos.getValor());
							autocompleteVo.setAporte(""+0);
							autocompleteVo.setCopago(""+0);
							autocompleteVo.setValor(""+0);							
//							break;
//						} 
					}	
				}
//			}
		
			
	}

	@Override
	public void updateAtencion(RecuperarAtencionForm recuperarAtencionForm, UserInfo userInfo) {
		
		Atencionmedica atencionBase = atencionMedicaDao.getAtencionMedicaById(recuperarAtencionForm.getIdAtencionMedica());
		Afiliado afiliado = agendaDao.getAfiliadoById(atencionBase.getAfiliado().getAfiliadoId());
		afiliado.setTelefono1(recuperarAtencionForm.getTelefono());
		afiliado.setMail(recuperarAtencionForm.getMail());
		agendaDao.updateAfiliado(afiliado);
		
		Collection<Prestacionesportomar> prestacionesOrden = atencionMedicaDao.getPrestacionesPorTomar(recuperarAtencionForm.getIdAtencionMedica());		
		if(prestacionesOrden != null){
			for(Prestacionesportomar prestacionOrden : prestacionesOrden){
				boolean flag = false;
				for(AutocompleteVo prestacionForm : recuperarAtencionForm.getAutocompleteVos()){
					if(prestacionForm.getValue() != null){
						Prestacionesaseguradorequivalencias prestacionEquivalencia = prestacionesDao.getPrestacionEquivalenciaByPrestacionAseguradorId(Integer.parseInt((String) prestacionForm.getValue()));		
						if(prestacionOrden.getCatprestacion().getPrestacionId() == prestacionEquivalencia.getCatprestacion().getPrestacionId()){
							flag = true;
						}
					}
				}
				
				if(flag==false){
					prestacionOrden.setAtencionmedicaByAtencionMedicaIdTomada(null);
					atencionMedicaDao.updatePrestacionPorTomar(prestacionOrden);
				}
			}
		}
		
		Atencionprestacion atencionPrestacion = new Atencionprestacion();
		Collection<Atencionprestacion> prestacionesBase = atencionMedicaDao.getPrestacionesPendientes(recuperarAtencionForm.getIdAtencionMedica());
		for(AutocompleteVo prestacionForm : recuperarAtencionForm.getAutocompleteVos()){
			boolean flag = false;
			for(Atencionprestacion prestacionBase : prestacionesBase){
				if(prestacionForm.getValue() != null){
					Prestacionesaseguradorequivalencias prestacionEquivalencia = prestacionesDao.getPrestacionEquivalenciaByPrestacionAseguradorId(Integer.parseInt((String) prestacionForm.getValue()));		
					if(prestacionBase.getCatprestacion().getPrestacionId() == prestacionEquivalencia.getCatprestacion().getPrestacionId()){
						prestacionBase.setAporteAsegurador(Float.parseFloat((String)prestacionForm.getAporte()));
						prestacionBase.setCantidad(prestacionForm.getCantidad());
						prestacionBase.setCopagoAfiliado(Float.parseFloat((String)prestacionForm.getCopago()));
						prestacionBase.setValorPrestacionConvenio(Float.parseFloat((String)prestacionForm.getValor()));
						agendaDao.updateAtencionPrestacion(prestacionBase);
						flag = true;						
					}
				}
			}
		
			if(flag==false && prestacionForm.getValue() != null){
				Prestacionesaseguradorequivalencias prestacionEquivalencia = prestacionesDao.getPrestacionEquivalenciaByPrestacionAseguradorId(Integer.parseInt((String) prestacionForm.getValue()));			
				AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
				atencionprestacionId.setAtencionMedicaId(atencionBase.getAtencionMedicaId());
				atencionprestacionId.setPrestacionId(prestacionEquivalencia.getCatprestacion().getPrestacionId());
				atencionPrestacion.setId(atencionprestacionId);
				atencionPrestacion.setAporteAsegurador(Float.parseFloat((String)prestacionForm.getAporte()));
				atencionPrestacion.setAtencionmedica(atencionBase);
				atencionPrestacion.setCantidad(prestacionForm.getCantidad());
				atencionPrestacion.setCatprestacion(prestacionEquivalencia.getCatprestacion());
				atencionPrestacion.setCopagoAfiliado(Float.parseFloat((String)prestacionForm.getCopago()));
				atencionPrestacion.setValorPrestacionConvenio(Float.parseFloat((String)prestacionForm.getValor()));
				agendaDao.saveAtencionPrestacion(atencionPrestacion);
			}
		}
		
		
		for(Atencionprestacion prestacionBase : prestacionesBase){
			boolean flag = false;
			for(AutocompleteVo prestacionForm : recuperarAtencionForm.getAutocompleteVos()){
				if(prestacionForm.getValue() != null){
					Prestacionesaseguradorequivalencias prestacionEquivalencia = prestacionesDao.getPrestacionEquivalenciaByPrestacionAseguradorId(Integer.parseInt((String) prestacionForm.getValue()));		
					if(prestacionBase.getCatprestacion().getPrestacionId() == prestacionEquivalencia.getCatprestacion().getPrestacionId()){
						flag = true;
					}
				}
			}
			
			if(flag==false){
				atencionMedicaDao.deleteAtencionPrestacion(prestacionBase);
			}
		}
			
			
		Atencionprestacion atencionPrestacionPrincipal = atencionMedicaDao.getAtencionPrestacionByPrincipal(recuperarAtencionForm.getIdAtencionMedica());
			
		if(atencionPrestacionPrincipal != null){
			Collection<Atencionprestacion> atencionPrestaciones = atencionMedicaDao.getPrestacionesPendientes(recuperarAtencionForm.getIdAtencionMedica());
			int i = 0;
			for(Atencionprestacion atencionPrestacionAux : atencionPrestaciones){
				if(i==0){
					atencionPrestacionAux.setPrincipal(1);
					agendaDao.updateAtencionPrestacion(atencionPrestacionAux);
				}else{
					atencionPrestacionAux.setPrincipal(0);
					agendaDao.updateAtencionPrestacion(atencionPrestacionAux);
				}
				i++;
			}
		}
			
		if(recuperarAtencionForm.getIdMedico() == 0){	
			if(recuperarAtencionForm.getIdAgenda() != 0){
				Agenda agenda = agendaDao.getAgendaById(recuperarAtencionForm.getIdAgenda());
				agendaDao.deleteAgenda(agenda);
			}
		}else{
			boolean validacionMedico = true;
			String mensajeError = ""; 
			Usuarios usuario = usuarioDao.getUsuarioById(recuperarAtencionForm.getIdMedico());
				
			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = usuario.getUsuarioespecialidadeses().iterator();
			Collection<Integer> especialidadesMedico = new ArrayList<>();
			while (usuarioEspecialidadesIterator.hasNext()) {
				Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				especialidadesMedico.add(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
			}
				
			Collection<Integer> especialidades = new ArrayList<>();
			Collection<Agenda> agendas = agendaDao.getAgendaByIdAfiliado(recuperarAtencionForm.getIdAfiliado());
			for (Agenda agendaAux : agendas) {
				Iterator<Usuarioespecialidades> usuarioEspecialidadesIteratorAux =	agendaAux.getUsuarios().getUsuarioespecialidadeses().iterator();
				while (usuarioEspecialidadesIteratorAux.hasNext()) {
					Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIteratorAux.next();
					especialidades.add(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedicaId());
				}
					
				if(agendaAux.getUsuarios().getUsuarioId() == recuperarAtencionForm.getIdMedico()){
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
				
				
			if (mensajeError.length()!=0) {
				recuperarAtencionForm.setError(mensajeError);
			}else{
				
				if(recuperarAtencionForm.getIdAgenda() != 0){
					Agenda agenda = agendaDao.getAgendaById(recuperarAtencionForm.getIdAgenda());
					agenda.setHoraCita(FormatUtil.getTime(recuperarAtencionForm.getIdTiempo()));
					agenda.setUsuarios(usuario);
					agenda.setAtencionmedica(atencionBase);
					agendaDao.update(agenda);
					atencionBase.setUsuariosByUsuarioMedicoId(usuario);
					agendaDao.updateAtencionMedica(atencionBase);
				}else{
					Agenda agenda = new Agenda();
					agenda.setAfiliado(atencionBase.getAfiliado());
					agenda.setAseguradores(atencionBase.getAseguradores());
					agenda.setCattipoidentificador(atencionBase.getCattipoidentificador());
					agenda.setFechaCita(atencionBase.getFechaAsistio());
					agenda.setHoraCita(FormatUtil.getTime(recuperarAtencionForm.getIdTiempo()));
					agenda.setLugaresdeatencion(atencionBase.getLugaresdeatencion());
					agenda.setPrestadores(atencionBase.getPrestadores());			
					agenda.setUsuarios(usuario);
					agenda.setAtencionmedica(atencionBase);
					atencionBase.setUsuariosByUsuarioMedicoId(usuario);
					agendaDao.updateAtencionMedica(atencionBase);
					
					Prestacionasegurador prestacionaseguradorPrincipal = null;
					for (AutocompleteVo autocompleteVo : recuperarAtencionForm.getAutocompleteVos()) {
						if (autocompleteVo.getValue()!=null) {
							prestacionaseguradorPrincipal = prestacionesDao.getPrestacionAseguradorById(Integer.parseInt((String) autocompleteVo.getValue())); 
							break;
						}
					}
						
					agenda.setPrestacion(prestacionaseguradorPrincipal.getCodigo());				
										
					if(atencionBase.getPersonasdeconfianza() == null){
						agenda.setAsistio(1);
					}else{
						agenda.setAsistio(0);
					}
						
					agendaDao.saveAgenda(agenda);
				}
			}			
		}
		Catestatusrecepcion catestatusrecepcion = atencionMedicaDao.getCatEstatusRecepcion(3);
		atencionBase.setCatestatusrecepcion(catestatusrecepcion);
		atencionBase.setUsuariosByUsuarioRecibioId(userInfo.getUsuarios());
		agendaDao.updateAtencionMedica(atencionBase);		
	}
}
