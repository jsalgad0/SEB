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
import com.mx.sab.form.RecepcionBuscarAtencionForm;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.service.IRecepcionBuscarAtencionesService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AtencionMedicaAuxVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class RecepcionBuscarAtencionesServiceImpl implements IRecepcionBuscarAtencionesService {

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
	public void getAtencionesHoy(RecepcionBuscarAtencionForm recepcionBuscarAtencionForm, UserInfo userInfo) {
		//log.info("getAtencionesPendientes service");
		int tipoBusqueda = recepcionBuscarAtencionForm.getTipoBusqueda();
		List<AtencionMedicaAuxVo> atencionesmedicas = new ArrayList<>();
		AtencionMedicaAuxVo atencionMedicaAuxVo = new AtencionMedicaAuxVo() ;
			
			if(tipoBusqueda == 1){				
				Atencionmedica atencionBase = atencionMedicaDao.getAtencionMedicaByFolio(recepcionBuscarAtencionForm.getFolio()); 
				
				if(atencionBase != null){
					recepcionBuscarAtencionForm.setNombre(atencionBase.getAfiliado().getNombre());
					recepcionBuscarAtencionForm.setApellidoPaterno(atencionBase.getAfiliado().getApellidoPaterno());
					recepcionBuscarAtencionForm.setApellidoMaterno(atencionBase.getAfiliado().getApellidoMaterno());
					recepcionBuscarAtencionForm.setIdAfiliado(atencionBase.getAfiliado().getAfiliadoId());
					recepcionBuscarAtencionForm.setIdAsegurador(atencionBase.getAseguradores().getAseguradorId());
					recepcionBuscarAtencionForm.setIdAtencionMedica(atencionBase.getAtencionMedicaId());		
					recepcionBuscarAtencionForm.setDescripcionAsegurador(atencionBase.getAseguradores().getNombreRazonSocial());
					recepcionBuscarAtencionForm.setIdPrestador(atencionBase.getPrestadores().getPrestadorId());
					recepcionBuscarAtencionForm.setDescripcionPrestador(atencionBase.getPrestadores().getNombreRazonSocial());
					recepcionBuscarAtencionForm.setIdConvenio(atencionBase.getConvenios().getConvenioId());
					recepcionBuscarAtencionForm.setDescripcionConvenio(atencionBase.getConvenios().getConvenio());
					recepcionBuscarAtencionForm.setIdIdentificador(atencionBase.getCattipoidentificador().getTipoIdentificadorId());
					recepcionBuscarAtencionForm.setDescripcionIdentificador(atencionBase.getCattipoidentificador().getTipoIdentificador());
					recepcionBuscarAtencionForm.setFolio(atencionBase.getFolio());					
					recepcionBuscarAtencionForm.setFechaAtencion(FormatUtil.getDate(atencionBase.getFechaAsistio()));
					
					if(atencionBase.getPersonasdeconfianza() == null){
						recepcionBuscarAtencionForm.setAsistio(true);
						recepcionBuscarAtencionForm.setPersonaConfianza(true);
					}else{
						recepcionBuscarAtencionForm.setAsistio(false);
						recepcionBuscarAtencionForm.setPersonaConfianza(false);
					}
					Iterator<Agenda> agendaIterator = atencionBase.getAgendas().iterator();
					while(agendaIterator.hasNext()){
						Agenda agenda = agendaIterator.next();
						recepcionBuscarAtencionForm.setIdAgenda(agenda.getAgendaId());
					}
					
					Iterator<Afiliadotipoidentificador> afiliadoTipoIterator = atencionBase.getAfiliado().getAfiliadotipoidentificadors().iterator();
					while(afiliadoTipoIterator.hasNext()){
						Afiliadotipoidentificador afiliadoTipoIdentificador = afiliadoTipoIterator.next();
						recepcionBuscarAtencionForm.setIdPaciente(afiliadoTipoIdentificador.getTipoIdValor());	
					}
					
					recepcionBuscarAtencionForm.setFechaDeNac(FormatUtil.getDate(atencionBase.getAfiliado().getFechaDeNacimiento()));
					recepcionBuscarAtencionForm.setMail(atencionBase.getAfiliado().getMail());
					recepcionBuscarAtencionForm.setTelefono(atencionBase.getAfiliado().getTelefono1());
					
					Collection<Atencionprestacion> prestacionesPorTomar = atencionMedicaDao.getPrestacionesPendientes(atencionBase.getAtencionMedicaId());
					List<AutocompleteVo> porTomarVos = new ArrayList<>(); 
					for(Atencionprestacion prestacion : prestacionesPorTomar ){
						AutocompleteVo porTomarVo = new AutocompleteVo();
						porTomarVo.setCantidad(prestacion.getCantidad());
						porTomarVo.setAporte(""+prestacion.getAporteAsegurador());
						porTomarVo.setCopago(""+prestacion.getCopagoAfiliado());
						porTomarVo.setValor(""+prestacion.getValorPrestacionConvenio());
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
					
					if(atencionBase.getUsuariosByUsuarioMedicoId() != null){
						recepcionBuscarAtencionForm.setNombreMedico(atencionBase.getUsuariosByUsuarioMedicoId().getNombre()+" "+atencionBase.getUsuariosByUsuarioMedicoId().getApellidoPaterno()+" "+atencionBase.getUsuariosByUsuarioMedicoId().getApellidoMaterno());
						recepcionBuscarAtencionForm.setIdTiempo(FormatUtil.getTime(atencionBase.getHoraAsistio()));
					}
					
					
					recepcionBuscarAtencionForm.setAutocompleteVos(porTomarVos);
					atencionMedicaAuxVo.setFolio(atencionBase.getFolio());
					atencionesmedicas.add(atencionMedicaAuxVo);
					recepcionBuscarAtencionForm.setAtenciones(atencionesmedicas);
					
				}else{
					recepcionBuscarAtencionForm.setError("No se encontraron atenciones médicas");
				}
				
				
			}else if(tipoBusqueda == 2){
				
				String fechaAtencionString = recepcionBuscarAtencionForm.getFechaAtencion();
				String nombre = recepcionBuscarAtencionForm.getNombre();
				String apellidoPaterno = recepcionBuscarAtencionForm.getApellidoPaterno();
				String apellidoMaterno = recepcionBuscarAtencionForm.getApellidoMaterno();
				 
				Collection<Atencionmedica> atencionesMedicasBase = atencionMedicaDao.getAtencionMedicaByAfiliadoName(FormatUtil.getDate(fechaAtencionString), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), nombre, apellidoPaterno, apellidoMaterno);
				
				if(atencionesMedicasBase.size()>0 ){
					
					if(atencionesMedicasBase.size() == 1){
						
						recepcionBuscarAtencionForm.setTipoBusqueda(1);
						for(Atencionmedica atencionBase: atencionesMedicasBase){
							recepcionBuscarAtencionForm.setFolio(atencionBase.getFolio());
						}
						getAtencionesHoy(recepcionBuscarAtencionForm,userInfo);
					
					}else{					
						for(Atencionmedica atencionBase: atencionesMedicasBase){
							atencionMedicaAuxVo.setFolio(atencionBase.getFolio());
							atencionMedicaAuxVo.setNombre(atencionBase.getAfiliado().getNombre());
							atencionMedicaAuxVo.setApellidoPaterno(atencionBase.getAfiliado().getApellidoPaterno());
							atencionMedicaAuxVo.setApellidoMaterno(atencionBase.getAfiliado().getApellidoMaterno());
							atencionMedicaAuxVo.setFechaDeAtencion(fechaAtencionString);
							atencionMedicaAuxVo.setFechaDeNacimiento(FormatUtil.getDate(atencionBase.getAfiliado().getFechaDeNacimiento()));
							atencionMedicaAuxVo.setDescripcionIdentificador(atencionBase.getCattipoidentificador().getTipoIdentificador());
							
							
							Iterator<Afiliadotipoidentificador> afiliadoTipoIterator = atencionBase.getAfiliado().getAfiliadotipoidentificadors().iterator();
							while(afiliadoTipoIterator.hasNext()){
								Afiliadotipoidentificador afiliadoTipoIdentificador = afiliadoTipoIterator.next();
								atencionMedicaAuxVo.setIdIdentificador(""+afiliadoTipoIdentificador.getTipoIdValor());	
							}
							atencionesmedicas.add(atencionMedicaAuxVo);
						}
						recepcionBuscarAtencionForm.setAtenciones(atencionesmedicas);
					}
				}else{
					recepcionBuscarAtencionForm.setError("No se encontraron atenciones médicas");
				}
			}			
	}
}
