package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IListaPacientesDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.form.ListaPacientesForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.AtencionprestacionId;
import com.mx.sab.model.Catcies10;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.NotamedicaCies10Id;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.service.IListaPacientesService;
import com.mx.sab.service.ISignosService;
import com.mx.sab.vo.AfiliadoVo;
import com.mx.sab.vo.AgendaVo;
import com.mx.sab.vo.AtencionMedicaVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class ListaPacientesServiceImpl implements IListaPacientesService {

	@Autowired
	private IListaPacientesDao listaPacientesDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IPrestacionesDao prestacionesDao;
	
	@Autowired
	private ISignosDao signosDao;

	@Override
	public Collection<Agenda> getListaPacientes(ListaPacientesForm listaPacientesForm, UserInfo userInfo) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<Agenda> agendas = null;
		
		if (listaPacientesForm==null) {
			listaPacientesForm = new ListaPacientesForm();
		}
		
		if (listaPacientesForm.getBusquedaA()==null && listaPacientesForm.getBusquedaE()==null) {
			listaPacientesForm.setBusquedaA("");
			listaPacientesForm.setBusquedaE("");
		}
		
		int totalLugarAtencion = 0;

		totalLugarAtencion = agendaDao.getAgendaCountConMedico(userInfo.getUsuarios().getUsuarioId(), listaPacientesForm.getBusquedaA(), listaPacientesForm.getBusquedaE(), userInfo.getTx_Marca());

		if (totalLugarAtencion>0) {
			paginasTotal = totalLugarAtencion / filas;
			if (totalLugarAtencion % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				listaPacientesForm.setDisplay(7);
			}else {
				listaPacientesForm.setDisplay(paginasTotal);
			}
			
			listaPacientesForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			agendas = agendaDao.getAgendaConMedico(userInfo.getUsuarios().getUsuarioId(), listaPacientesForm.getBusquedaA(), listaPacientesForm.getBusquedaE(), inicio, fin, userInfo.getTx_Marca());
			
		}else {
			//log.info("No hay agenda");
		}
		return agendas;
	}

	@Override
	public Collection<AgendaVo> getListaPacientes(String busquedaA, String busquedaE, int page, UserInfo userInfo) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<AgendaVo> agendaVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Agenda> agendas = null; 

		agendas = agendaDao.getAgendaConMedico(userInfo.getUsuarios().getUsuarioId(), busquedaA, busquedaE, inicio, fin, userInfo.getTx_Marca());
		
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
			AtencionMedicaVo atencionMedicaVo = new AtencionMedicaVo();
			atencionMedicaVo.setHoraAsistio(agenda.getAtencionmedica().getHoraAsistio());
			agendaVos.add(agendaVo);
		}
		
		
		return agendaVos;
	}

	@Override
	public Signosvitales getSignosVitales(ListaPacientesForm listaPacientesForm) {
		//log.info("getSignosVitales");
		Agenda agenda = agendaDao.getAgendaById(listaPacientesForm.getIdAgenda());
		Signosvitales signosvitales = null;
		try{
			signosvitales = listaPacientesDao.getSignosVitalesByIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return signosvitales;
	}

	@Override
	public Collection<AutocompleteVo> getCatCies10(String busqueda) {
		//log.info("getCatCies10");
		Collection<Catcies10> catcies10s = listaPacientesDao.getCatCies10(busqueda);
		Collection<AutocompleteVo> autocompleteVos = new ArrayList<>();
		for (Catcies10 catcies10 : catcies10s) {
			AutocompleteVo autocompleteVo = new AutocompleteVo();
			autocompleteVo.setValue(""+catcies10.getCie10id());
			autocompleteVo.setLabel(catcies10.getDescripcion());
			autocompleteVos.add(autocompleteVo);
		}
		return autocompleteVos;
	}

	@Override
	public void save(ListaPacientesForm listaPacientesForm, UserInfo userInfo) {
		//log.info("save");
		Agenda agenda = agendaDao.getAgendaById(listaPacientesForm.getIdAgenda());
		
		if (listaPacientesForm.isEditar()) {
			Notamedica notamedica = listaPacientesDao.getNotaMedicaById(listaPacientesForm.getNotamedica().getNotaMedicaId());
			notamedica.setAntecedentes(listaPacientesForm.getNotamedica().getAntecedentes());
			notamedica.setSintomas(listaPacientesForm.getNotamedica().getSintomas());
			notamedica.setObservaciones(listaPacientesForm.getNotamedica().getObservaciones());
			notamedica.setPlanAseguir(listaPacientesForm.getNotamedica().getPlanAseguir());
			listaPacientesDao.update(notamedica);
			NotamedicaCies10 notamedicaCies10 = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedicaPrincipal(notamedica.getNotaMedicaId());
			notamedicaCies10.setCatcies10(listaPacientesDao.getCatCies10ById(listaPacientesForm.getIdDiagnostico()));
			notamedicaCies10.setPrincipal(1);
			listaPacientesDao.updateCies10(notamedicaCies10);
			NotamedicaCies10Id notamedicaCies10Id = new NotamedicaCies10Id();
			Collection<NotamedicaCies10> notamedicaCies10s = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedica(notamedica.getNotaMedicaId());
			HashMap<Integer, NotamedicaCies10> mapaNotaMedicaCies10 = new HashMap<>();
			for (NotamedicaCies10 notamedicaCie10 : notamedicaCies10s) {
				mapaNotaMedicaCies10.put(notamedicaCie10.getCatcies10().getCie10id(), notamedicaCie10);
			}
			
			if (listaPacientesForm.getAutocompleteVos()!=null) {
				for (AutocompleteVo autocompleteVo : listaPacientesForm.getAutocompleteVos()) {
					if (autocompleteVo.getValue()!=null) {
						if (mapaNotaMedicaCies10.get(Integer.parseInt((String)autocompleteVo.getValue()))!=null) {
							mapaNotaMedicaCies10.remove(Integer.parseInt((String)autocompleteVo.getValue()));
						}else {
							notamedicaCies10 = new NotamedicaCies10();
							notamedicaCies10Id = new NotamedicaCies10Id();
							notamedicaCies10Id.setCie10id(Integer.parseInt((String) autocompleteVo.getValue()));
							notamedicaCies10Id.setNotaMedicaId(listaPacientesForm.getNotamedica().getNotaMedicaId());
							notamedicaCies10.setId(notamedicaCies10Id);
							notamedicaCies10.setPrincipal(0);
							listaPacientesDao.saveCies10(notamedicaCies10);						
						}	
					}
				}
			}
			
			if (listaPacientesForm.isEditarDiagnostico()) {
				Iterator<Entry<Integer, NotamedicaCies10>> it = mapaNotaMedicaCies10.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry<Integer, NotamedicaCies10> pair = (Map.Entry<Integer, NotamedicaCies10>)it.next();
			        listaPacientesDao.deleteCies10(pair.getValue());	
			    }	
			}
		    
			
		}else{
			listaPacientesForm.getNotamedica().setAtencionmedica(agenda.getAtencionmedica());
			listaPacientesForm.getNotamedica().setUsuarios(userInfo.getUsuarios());
			listaPacientesDao.save(listaPacientesForm.getNotamedica());
			NotamedicaCies10 notamedicaCies10 = new NotamedicaCies10();
			NotamedicaCies10Id notamedicaCies10Id = new NotamedicaCies10Id();
			notamedicaCies10Id.setCie10id(listaPacientesForm.getIdDiagnostico());
			notamedicaCies10Id.setNotaMedicaId(listaPacientesForm.getNotamedica().getNotaMedicaId());
			notamedicaCies10.setId(notamedicaCies10Id);
			notamedicaCies10.setPrincipal(1);
			listaPacientesDao.saveCies10(notamedicaCies10);
			
			if (listaPacientesForm.getAutocompleteVos()!=null) {
				for (AutocompleteVo autocompleteVo : listaPacientesForm.getAutocompleteVos()) {
					notamedicaCies10 = new NotamedicaCies10();
					notamedicaCies10Id = new NotamedicaCies10Id();
					notamedicaCies10Id.setCie10id(Integer.parseInt((String) autocompleteVo.getValue()));
					notamedicaCies10Id.setNotaMedicaId(listaPacientesForm.getNotamedica().getNotaMedicaId());
					notamedicaCies10.setId(notamedicaCies10Id);
					notamedicaCies10.setPrincipal(0);
					listaPacientesDao.saveCies10(notamedicaCies10);
				}
			}	
		}
		
		
	}

	@Override
	public void agregaDiagnosticos(ListaPacientesForm listaPacientesFormAux,ListaPacientesForm listaPacientesForm) {
		//log.info("agregaDiagnosticos");
		if (listaPacientesForm!=null) {
			listaPacientesFormAux.setAutocompleteVos(listaPacientesForm.getAutocompleteVos());	
		}
		
		if (listaPacientesForm.isEditar()) {
			Notamedica notamedica = listaPacientesDao.getNotaMedicaById(listaPacientesForm.getNotamedica().getNotaMedicaId());
			Collection<NotamedicaCies10> notamedicaCies10s = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedica(notamedica.getNotaMedicaId());
			HashMap<Integer, NotamedicaCies10> mapaNotaMedicaCies10 = new HashMap<>();
			for (NotamedicaCies10 notamedicaCie10 : notamedicaCies10s) {
				mapaNotaMedicaCies10.put(notamedicaCie10.getCatcies10().getCie10id(), notamedicaCie10);
			}
			
			NotamedicaCies10 notamedicaCies10 = null;
			NotamedicaCies10Id notamedicaCies10Id = null;
			if (listaPacientesForm.getAutocompleteVos()!=null) {
				for (AutocompleteVo autocompleteVo : listaPacientesForm.getAutocompleteVos()) {
					if (autocompleteVo.getValue() instanceof String) {
						if (mapaNotaMedicaCies10.get(autocompleteVo.getValue())!=null) {
							mapaNotaMedicaCies10.remove(autocompleteVo.getValue());
						}else {
							notamedicaCies10 = new NotamedicaCies10();
							notamedicaCies10Id = new NotamedicaCies10Id();
							notamedicaCies10Id.setCie10id(Integer.parseInt((String) autocompleteVo.getValue()));
							notamedicaCies10Id.setNotaMedicaId(listaPacientesForm.getNotamedica().getNotaMedicaId());
							notamedicaCies10.setId(notamedicaCies10Id);
							notamedicaCies10.setPrincipal(0);
							listaPacientesDao.saveCies10(notamedicaCies10);						
						}
					}else if (autocompleteVo.getValue() instanceof String) {
						if (mapaNotaMedicaCies10.get(Integer.parseInt((String)autocompleteVo.getValue()))!=null) {
							mapaNotaMedicaCies10.remove(Integer.parseInt((String)autocompleteVo.getValue()));
						}else {
							notamedicaCies10 = new NotamedicaCies10();
							notamedicaCies10Id = new NotamedicaCies10Id();
							notamedicaCies10Id.setCie10id(Integer.parseInt((String) autocompleteVo.getValue()));
							notamedicaCies10Id.setNotaMedicaId(listaPacientesForm.getNotamedica().getNotaMedicaId());
							notamedicaCies10.setId(notamedicaCies10Id);
							notamedicaCies10.setPrincipal(0);
							listaPacientesDao.saveCies10(notamedicaCies10);						
						}
					}
				}
			}
			
			Iterator<Entry<Integer, NotamedicaCies10>> it = mapaNotaMedicaCies10.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<Integer, NotamedicaCies10> pair = (Map.Entry<Integer, NotamedicaCies10>)it.next();
		        if (pair.getValue()!=null) {
		        	listaPacientesDao.deleteCies10(pair.getValue());	
				}
		        	
		    }		
		}
		
		
	}

	@Override
	public void getPrestaciones(ListaPacientesForm listaPacientesForm) {
		Agenda agenda = agendaDao.getAgendaById(listaPacientesForm.getIdAgenda());
		if (agenda.getAtencionmedica().getFechaAtendidoMedico()!=null) {
			listaPacientesForm.setFinalizoAtencionMedica(true);
		}
		Iterator<Atencionprestacion> atencionPrestacionIterator = agenda.getAtencionmedica().getAtencionprestacions().iterator();
		Collection<Catprestacion> catprestacions = new ArrayList<>();
		while(atencionPrestacionIterator.hasNext()){
			Atencionprestacion atencionprestacion = atencionPrestacionIterator.next();
			catprestacions.add(atencionprestacion.getCatprestacion());
		}
		
		AutocompleteVo autocompleteVo = null;
		List<AutocompleteVo> autocompleteVos = new ArrayList<>();
		if(agenda.getPrestadores().getUsarTablaPrestacionesSab()==1){
			for (Catprestacion catprestacion : catprestacions) {
				autocompleteVo = new AutocompleteVo();
				autocompleteVo.setValue(""+catprestacion.getPrestacionId());
				autocompleteVo.setLabel(catprestacion.getDescripcion());
				autocompleteVos.add(autocompleteVo);
			}
		}else{
			
			Collection<Prestacionprestador> prestacionprestadors = prestacionesDao.getPrestacionesByPrestador(agenda.getPrestadores().getPrestadorId());
			
			for (Prestacionprestador prestacionprestador : prestacionprestadors) {
				Iterator<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciasIterator = prestacionprestador.getPrestacionesprestadorequivalenciases().iterator();
				while (prestacionesprestadorequivalenciasIterator.hasNext()) {
					Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = (Prestacionesprestadorequivalencias) prestacionesprestadorequivalenciasIterator.next();
					for (Catprestacion catprestacion : catprestacions) {
						if(catprestacion.getPrestacionId() == prestacionesprestadorequivalencias.getCatprestacion().getPrestacionId()){
							autocompleteVo = new AutocompleteVo();
							autocompleteVo.setValue(""+prestacionprestador.getPrestacionPrestadorId());
							autocompleteVo.setLabel(prestacionprestador.getDescripcion());
							autocompleteVos.add(autocompleteVo);			
						}
					}
				}				
			}
		}
		
		listaPacientesForm.setPrestacionesAutocompleteVos(autocompleteVos);
		
	}

	@Override
	public Collection<AutocompleteVo> getCatPrestaciones(String busqueda, int idAgenda) {
		Agenda agenda = agendaDao.getAgendaById(idAgenda);

		AutocompleteVo autocompleteVo = null;
		List<AutocompleteVo> autocompleteVos = new ArrayList<>();
		if(agenda.getPrestadores().getUsarTablaPrestacionesSab()==1){
			Collection<Catprestacion> catprestacions = prestacionesDao.getCatPrestacionByDescripcion(busqueda);
			for (Catprestacion catprestacion : catprestacions) {
				autocompleteVo = new AutocompleteVo();
				autocompleteVo.setValue(""+catprestacion.getPrestacionId());
				autocompleteVo.setLabel(catprestacion.getDescripcion());
				autocompleteVos.add(autocompleteVo);				
			}
		}else{
			Collection<Prestacionprestador> prestacionprestadors = prestacionesDao.getPrestacionesByDescripcion(agenda.getPrestadores().getPrestadorId(), busqueda);
			for (Prestacionprestador prestacionprestador : prestacionprestadors) {
				autocompleteVo = new AutocompleteVo();
				autocompleteVo.setValue(""+prestacionprestador.getPrestacionPrestadorId());
				autocompleteVo.setLabel(prestacionprestador.getDescripcion());
				autocompleteVos.add(autocompleteVo);				
			}
		}
		return autocompleteVos;
	}

	@Override
	public void agregaPrestaciones(ListaPacientesForm listaPacientesForm) {
		Agenda agenda = agendaDao.getAgendaById(listaPacientesForm.getIdAgenda());
		
		Iterator<Atencionprestacion> atencionPrestacionIterator = agenda.getAtencionmedica().getAtencionprestacions().iterator();
		Collection<Catprestacion> catprestacions = new ArrayList<>();
		while(atencionPrestacionIterator.hasNext()){
			Atencionprestacion atencionprestacion = atencionPrestacionIterator.next();
			catprestacions.add(atencionprestacion.getCatprestacion());
		}

		if(agenda.getPrestadores().getUsarTablaPrestacionesSab()==1){
			HashMap<Integer, AutocompleteVo> mapaAutocompleteVo = new HashMap<>();
			HashMap<Integer, Catprestacion> mapaCatPrestaciones = new HashMap<>();
			if (listaPacientesForm.getPrestacionesAutocompleteVos()!=null) {
				for (AutocompleteVo autocompleteVo : listaPacientesForm.getPrestacionesAutocompleteVos()) {
					if (autocompleteVo.getValue()!=null) {
						mapaAutocompleteVo.put(Integer.parseInt((String) autocompleteVo.getValue()), autocompleteVo);
					}
				}
				for (Catprestacion catprestacion : catprestacions) {
					mapaCatPrestaciones.put(catprestacion.getPrestacionId(), catprestacion);
				}
				
			    Iterator<Entry<Integer, Catprestacion>> it = mapaCatPrestaciones.entrySet().iterator();
			    while (it.hasNext()) {
			        Entry<Integer, Catprestacion> pair = it.next();
			        if(mapaAutocompleteVo.get(pair.getKey())==null){
			        	Atencionprestacion atencionprestacion = listaPacientesDao.getAtencionPrestacion(agenda.getAtencionmedica().getAtencionMedicaId(), pair.getKey());
			        	if(atencionprestacion.getPrincipal()==0){
			        		listaPacientesDao.delete(atencionprestacion);
			        	}
			        }else {
						mapaAutocompleteVo.remove(pair.getKey());
					}
			    }
			    
			    Iterator<Entry<Integer, AutocompleteVo>> itAutocompleteVo = mapaAutocompleteVo.entrySet().iterator();
			    while (itAutocompleteVo.hasNext()) {
			    	Entry<Integer, AutocompleteVo> pair = itAutocompleteVo.next();
					Atencionprestacion atencionprestacion = new Atencionprestacion();
					AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
					atencionprestacionId.setAtencionMedicaId(agenda.getAtencionmedica().getAtencionMedicaId());
					atencionprestacionId.setPrestacionId(pair.getKey());
					atencionprestacion.setPrincipal(0);
					atencionprestacion.setId(atencionprestacionId);
					listaPacientesDao.saveAtencionPrestacion(atencionprestacion);
			    }
			}
		}else{
			HashMap<Integer, AutocompleteVo> mapaAutocompleteVo = new HashMap<>();
			HashMap<Integer, Catprestacion> mapaCatPrestaciones = new HashMap<>();
			if (listaPacientesForm.getPrestacionesAutocompleteVos()!=null) {
				for (AutocompleteVo autocompleteVo : listaPacientesForm.getPrestacionesAutocompleteVos()) {
					mapaAutocompleteVo.put(Integer.parseInt((String) autocompleteVo.getValue()), autocompleteVo);
				}
				
				Collection<Prestacionprestador> prestacionprestadors = prestacionesDao.getPrestacionesByPrestador(agenda.getPrestadores().getPrestadorId());
				for (Prestacionprestador prestacionprestador : prestacionprestadors) {
					Iterator<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciasIterator = prestacionprestador.getPrestacionesprestadorequivalenciases().iterator();
					while (prestacionesprestadorequivalenciasIterator.hasNext()) {
						Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = (Prestacionesprestadorequivalencias) prestacionesprestadorequivalenciasIterator.next();
						for (Catprestacion catprestacion : catprestacions) {
							if(catprestacion.getPrestacionId() == prestacionesprestadorequivalencias.getCatprestacion().getPrestacionId()){
								mapaCatPrestaciones.put(catprestacion.getPrestacionId(), catprestacion);
							}
						}
					}
				}
				
			    Iterator<Entry<Integer, Catprestacion>> it = mapaCatPrestaciones.entrySet().iterator();
			    while (it.hasNext()) {
			        Entry<Integer, Catprestacion> pair = it.next();
			        if(mapaAutocompleteVo.get(pair.getKey())==null){
			        	Atencionprestacion atencionprestacion = listaPacientesDao.getAtencionPrestacion(agenda.getAtencionmedica().getAtencionMedicaId(), pair.getKey());
			        	if(atencionprestacion.getPrincipal()==0){
			        		listaPacientesDao.delete(atencionprestacion);
			        	}
			        }else {
						mapaAutocompleteVo.remove(pair.getKey());
					}
			    }
			    
			    Iterator<Entry<Integer, AutocompleteVo>> itAutocompleteVo = mapaAutocompleteVo.entrySet().iterator();
			    while (itAutocompleteVo.hasNext()) {
			    	Entry<Integer, AutocompleteVo> pair = itAutocompleteVo.next();
					Atencionprestacion atencionprestacion = new Atencionprestacion();
					AtencionprestacionId atencionprestacionId = new AtencionprestacionId();
					atencionprestacionId.setAtencionMedicaId(agenda.getAtencionmedica().getAtencionMedicaId());
					Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = prestacionesDao.getPrestacionesPrestadorEquivalencias(pair.getKey());				
					atencionprestacionId.setPrestacionId(prestacionesprestadorequivalencias.getCatprestacion().getPrestacionId());
					atencionprestacion.setPrincipal(0);
					atencionprestacion.setId(atencionprestacionId);
					listaPacientesDao.saveAtencionPrestacion(atencionprestacion);
				}
			}
		}
	}

	@Override
	public void inicializaForm(ListaPacientesForm listaPacientesForm) {
		Agenda agenda = agendaDao.getAgendaById(listaPacientesForm.getIdAgenda());
		if (agenda.getAtencionmedica().getFechaAtendidoMedico()!=null) {
			listaPacientesForm.setFinalizoAtencionMedica(true);
		}
		Notamedica notamedica = listaPacientesDao.getNotaMedicaByAtencionMedicaId(agenda.getAtencionmedica().getAtencionMedicaId());
		if (notamedica!=null) {
			Iterator<NotamedicaCies10> notaMedicaCies10Iterator = notamedica.getNotamedicaCies10s().iterator();
			while (notaMedicaCies10Iterator.hasNext()) {
				NotamedicaCies10 notamedicaCies10 = (NotamedicaCies10) notaMedicaCies10Iterator.next();
				if (notamedicaCies10.getPrincipal()==1) {
					listaPacientesForm.setIdDiagnostico(notamedicaCies10.getCatcies10().getCie10id());
					listaPacientesForm.setDiagnostico(notamedicaCies10.getCatcies10().getDescripcion());
					break;
				}
			}
			listaPacientesForm.setNotamedica(notamedica);
			
			Collection<NotamedicaCies10> notamedicaCies10s = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedica(notamedica.getNotaMedicaId());
			Collection<AutocompleteVo> autocompleteVos = new ArrayList<>();
			for (NotamedicaCies10 notamedicaCies10 : notamedicaCies10s) {
				AutocompleteVo autocompleteVo = new AutocompleteVo();
				autocompleteVo.setLabel(notamedicaCies10.getCatcies10().getDescripcion());
				autocompleteVo.setValue(""+notamedicaCies10.getCatcies10().getCie10id());
				autocompleteVos.add(autocompleteVo);
			}
			listaPacientesForm.setAutocompleteVos((List<AutocompleteVo>) autocompleteVos);
			listaPacientesForm.setEditar(true);
			
			Signosvitales signosvitales = signosDao.getSignosByAtencion(agenda.getAtencionmedica().getAtencionMedicaId());
			if (signosvitales!=null) {
				listaPacientesForm.setFinalizarAtencion(true);
			}
		}
		
		
		
	}
}
