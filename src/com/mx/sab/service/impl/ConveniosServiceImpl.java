package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.sab.dao.IAseguradorDao;
import com.mx.sab.dao.IConveniosDao;
import com.mx.sab.dao.ICuadroPrestacionesDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IPrestadoresDao;
import com.mx.sab.form.ConveniosForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.ConvenioCuadroprestaciones;
import com.mx.sab.model.ConvenioCuadroprestacionesId;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.CuadroprestacionPrestacionId;
import com.mx.sab.model.Cuadroprestaciones;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Prestacionasegurador;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.model.Prestadores;
import com.mx.sab.service.ICitasPresencialesService;
import com.mx.sab.service.IConveniosService;
import com.mx.sab.service.ICuadroPrestacionesService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AseguradoresVo;
import com.mx.sab.vo.ConveniosVo;
import com.mx.sab.vo.LugarAtencionVo;
import com.mx.sab.vo.PrestadoresVo;

@Service
@Log4j2
public class ConveniosServiceImpl implements IConveniosService {

	@Autowired
	private IConveniosDao conveniosDao;
	
	@Autowired
	private IAseguradorDao aseguradorDao;
	
	@Autowired
	private IPrestadoresDao prestadoresDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private ICuadroPrestacionesDao cuadroPrestacionesDao;
	
	@Autowired
	private ICuadroPrestacionesService cuadroPrestacionesService;
	
	@Override
	public Collection<ConveniosVo> getConvenios(ConveniosForm conveniosForm) {
		Collection<Convenios> convenios = conveniosDao.getConvenios(conveniosForm);
		Collection<ConveniosVo> conveniosVos = new ArrayList<>();
		ConveniosVo conveniosVo = null;
		for (Convenios convenio : convenios) {
			conveniosVo = new ConveniosVo();
			conveniosVo.setConvenio(convenio.getConvenio());
			conveniosVo.setConvenioId(convenio.getConvenioId());
			conveniosVo.setIdentificador(convenio.getIdentificador());
			conveniosVo.setVigencia(FormatUtil.getDateddmmmyyyy(convenio.getVigenciaInicial())+" - "+FormatUtil.getDateddmmmyyyy(convenio.getVigenciaFinal()));
			conveniosVo.setIdAsegurador(convenio.getAseguradores().getAseguradorId());
			conveniosVo.setIdLugarAtencion(convenio.getLugaresdeatencion().getLugarDeAtencionId());
			conveniosVo.setLugarAtencion(convenio.getLugaresdeatencion().getDescripcion());
			conveniosVo.setCodigoLugarAtencion(""+convenio.getLugaresdeatencion().getCodigoLugarAtencion());
			conveniosVo.setIdPrestador(convenio.getPrestadores().getPrestadorId());
			conveniosVo.setPrestador(convenio.getPrestadores().getNombreRazonSocial());
			conveniosVo.setVigenciaDesde(FormatUtil.getDateddmmyyyy(convenio.getVigenciaInicial()));
			conveniosVo.setVigenciaHasta(FormatUtil.getDateddmmyyyy(convenio.getVigenciaFinal()));
			conveniosVos.add(conveniosVo);
		}
		return conveniosVos;
	}

	@Override
	public Collection<ConveniosVo> getConvenios(String busquedaP, String busquedaL, String busquedaA, int page) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<ConveniosVo> conveniosVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Convenios> convenios = conveniosDao.getConvenios(busquedaP,busquedaL,busquedaA,inicio,fin);
		for (Convenios convenio : convenios) {
			ConveniosVo conveniosVo = new ConveniosVo();
			conveniosVo.setConvenio(convenio.getConvenio());
			conveniosVo.setConvenioId(convenio.getConvenioId());
			AseguradoresVo aseguradoresVo = new AseguradoresVo();
			aseguradoresVo.setNombreRazonSocial(convenio.getAseguradores().getNombreRazonSocial());
			conveniosVo.setAseguradores(aseguradoresVo);
			LugarAtencionVo lugarAtencionVo = new LugarAtencionVo();
			lugarAtencionVo.setDescripcion(convenio.getLugaresdeatencion().getDescripcion());
			conveniosVo.setLugaresdeatencion(lugarAtencionVo);
			PrestadoresVo prestadoresVo = new PrestadoresVo();
			prestadoresVo.setNombreRazonSocial(convenio.getPrestadores().getNombreRazonSocial());
			conveniosVo.setPrestadores(prestadoresVo);
			conveniosVos.add(conveniosVo);
		}
		
		return conveniosVos;
	}

	@Override
	public void delete(ConveniosForm conveniosForm) {
		//log.info("delete");
		conveniosForm.setExito("");
		conveniosForm.setError("");
		if (conveniosForm.getIdConvenio()!=0) {
			Convenios convenios = conveniosDao.getConveniosById(conveniosForm.getIdConvenio());	
			Iterator<Atencionmedica> iteratorAtencionMedica = convenios.getAtencionmedicas().iterator();
			if (iteratorAtencionMedica.hasNext()) {
				conveniosForm.setError("Hay Atenciones médicas registradas para este Convenio, no se puede Eliminar");
				conveniosForm.setExito("");						
			}else{			
				conveniosDao.delete(convenios);
				conveniosForm.setError("");
				conveniosForm.setExito("Se elimino correctamente el Convenio");	
				conveniosForm.setCodigoLugarAtencion("");
				conveniosForm.setConvenio("");
				conveniosForm.setIdAsegurador(-1);
				conveniosForm.setIdConvenio(0);
				conveniosForm.setIdentificadorConvenio("");
				conveniosForm.setIdLugarDeAtencion(0);
				conveniosForm.setIdPrestador(-1);
				conveniosForm.setLugarAtencion("");
				conveniosForm.setPrestador("");
				conveniosForm.setVigenteDesde("");
				conveniosForm.setVigenteHasta("");
			}
		}else{
			conveniosForm.setError("Debe de seleccionar un Convenio");
			conveniosForm.setExito("");
		}
		
	}

	@Override
	public void save(ConveniosForm conveniosForm) {
		//log.info("save");
		conveniosForm.setExito("");
		conveniosForm.setError("");
		if (conveniosForm.getIdLugarDeAtencion()!=0) {
			if(conveniosDao.existeConvenio(conveniosForm)==null){
				Convenios convenios = new Convenios();
				convenios.setActivo(1);
				convenios.setAseguradores(aseguradorDao.getAseguradorById(conveniosForm.getIdAsegurador()));
				convenios.setConvenio(conveniosForm.getConvenio());
				convenios.setIdentificador(conveniosForm.getIdentificadorConvenio());
				convenios.setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(conveniosForm.getIdLugarDeAtencion()));
				convenios.setPrestadores(prestadoresDao.getPrestadorById(conveniosForm.getIdPrestador()));
				convenios.setVigenciaInicial(FormatUtil.getDate(conveniosForm.getVigenteDesde()));
				convenios.setVigenciaFinal(FormatUtil.getDate(conveniosForm.getVigenteHasta()));
				convenios = conveniosDao.save(convenios);
				conveniosForm.setError("");
				conveniosForm.setExito("Convenio registrado con éxito, se le asignó el correlativo "+ convenios.getConvenioId());
			}else{
				conveniosForm.setError("Ya existe un convenio de las mismas características");
				conveniosForm.setExito("");
			}	
		}else{
			conveniosForm.setError("Debe de seleccionar un Lugar de Atencion");
			conveniosForm.setExito("");
		}
		
//		//log.info("save");
//		try{
//			conveniosForm.getConvenios().setAseguradores(aseguradorDao.getAseguradorById(conveniosForm.getIdAseguradores()));
//			conveniosForm.getConvenios().setPrestadores(prestadoresDao.getPrestadorById(conveniosForm.getIdPrestadores()));
//			conveniosForm.getConvenios().setLugaresdeatencion(lugarAtencionDao.getLugarAtencionById(conveniosForm.getIdLugarDeAtencion()));
//			conveniosForm.getConvenios().setActivo(1);
//			Convenios convenios = conveniosDao.getLastConvenio();
//			String correlativo = null;
//			if (convenios!=null) {
//				correlativo = ""+(Integer.parseInt(convenios.getCorrelativo())+1);	
//			}else {
//				correlativo = "1";
//			}
//			conveniosForm.getConvenios().setCorrelativo(FormatUtil.agregaCeros(correlativo, 3));
//			conveniosForm.setConvenios(conveniosDao.save(conveniosForm.getConvenios()));
//			
//			ConvenioCuadroprestaciones convenioCuadroprestaciones = new ConvenioCuadroprestaciones();
//			ConvenioCuadroprestacionesId convenioCuadroprestacionesId = new ConvenioCuadroprestacionesId();
//			
//			if(conveniosForm.getOpcionCuadroPrestaciones().equals("C")){
//				if (cuadroPrestacionesDao.getCuadroPrestacionesByNombre(conveniosForm.getCuadroprestaciones().getCuadroPrestacion()) == null) {
//					conveniosForm.getCuadroprestaciones().setActivo(1);
//					conveniosForm.setCuadroprestaciones(cuadroPrestacionesDao.saveCuadroPrestaciones(conveniosForm.getCuadroprestaciones()));
//					
//					cuadroPrestacionesService.cuadroPrestacionesPrestador(conveniosForm);
//					CuadroprestacionPrestacionId cuadroprestacionPrestacionId = null;
//					for (CuadroprestacionPrestacion cuadroprestacionPrestacion : conveniosForm.getCuadroprestacionPrestacions()) {
//						cuadroprestacionPrestacionId = new CuadroprestacionPrestacionId();
//						cuadroprestacionPrestacionId.setCuadroPrestacionId(cuadroprestacionPrestacion.getCuadroprestaciones().getCuadroPrestacionId());
//						cuadroprestacionPrestacionId.setPrestacionId(cuadroprestacionPrestacion.getCatprestacion().getPrestacionId());
//						cuadroprestacionPrestacion.setId(cuadroprestacionPrestacionId);
//						cuadroPrestacionesDao.saveCuadroPrestacionPrestacion(cuadroprestacionPrestacion);
//					}
//					convenioCuadroprestacionesId.setCuadroPrestacionId(conveniosForm.getCuadroprestaciones().getCuadroPrestacionId());
//				}else {
//					conveniosForm.setBanderaError(true);
//					conveniosForm.setError("No se puede repetir el nombre");
//				}
//			}else if (conveniosForm.getOpcionCuadroPrestaciones().equals("E")){
//				boolean existenPrestacionesAsegurador = false;
//				boolean existenPrestacionesPrestador = false;
//				Collection<Integer> prestacionesCuadro = new ArrayList<>();
//				Collection<CuadroprestacionPrestacion> cuadroprestacionPrestacions = cuadroPrestacionesDao.getCuadroPrestacionesPrestacionById(conveniosForm.getIdCuadroPrestacion());
//				for (CuadroprestacionPrestacion cuadroprestacionPrestacion : cuadroprestacionPrestacions) {
//					prestacionesCuadro.add(cuadroprestacionPrestacion.getCatprestacion().getPrestacionId());
//				}
//				
//				Collection<Prestacionasegurador> prestacionaseguradors = cuadroPrestacionesDao.getPrestacionesAseguradorById(conveniosForm.getIdAseguradores());
//				Collection<Integer> prestacionesAsegurador = new ArrayList<>();
//				for (Prestacionasegurador prestacionasegurador : prestacionaseguradors) {
//					Iterator<Prestacionesaseguradorequivalencias> pseIterator = prestacionasegurador.getPrestacionesaseguradorequivalenciases().iterator();
//					while (pseIterator.hasNext()) {
//						Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) pseIterator.next();
//						prestacionesAsegurador.add(prestacionesaseguradorequivalencias.getCatprestacion().getPrestacionId());
//					}
//				}
//				
//				existenPrestacionesAsegurador = existenPrestaciones(prestacionesAsegurador,prestacionesCuadro);
//				
//				Collection<Integer> prestacionesPrestador = new ArrayList<>();
//				Prestadores prestadores = prestadoresDao.getPrestadorById(conveniosForm.getIdPrestadores());
//				if (prestadores.getUsarTablaPrestacionesSab() == 1) {
//					existenPrestacionesPrestador = true;
//				}else{
//					Collection<Prestacionprestador> prestacionprestadors = cuadroPrestacionesDao.getPrestacionesPrestadorById(conveniosForm.getIdPrestadores());
//					for (Prestacionprestador prestacionprestador : prestacionprestadors) {
//						Iterator<Prestacionesprestadorequivalencias> ppeIterator = prestacionprestador.getPrestacionesprestadorequivalenciases().iterator();
//						while (ppeIterator.hasNext()) {
//							Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = (Prestacionesprestadorequivalencias) ppeIterator.next();
//							prestacionesPrestador.add(prestacionesprestadorequivalencias.getCatprestacion().getPrestacionId());
//						}
//					}	
//					existenPrestacionesPrestador = existenPrestaciones(prestacionesPrestador,prestacionesCuadro);
//				}
//				
//				if (existenPrestacionesPrestador && existenPrestacionesAsegurador) {
//					convenioCuadroprestacionesId.setCuadroPrestacionId(conveniosForm.getIdCuadroPrestacion());
//				}else {
//					conveniosForm.setBanderaError(true);
//					conveniosForm.setError("Las prestaciones no coinciden");
//				}
//			}else if (conveniosForm.getOpcionCuadroPrestaciones().equals("G")) {
//				Collection<Prestacionasegurador> prestacionaseguradors = cuadroPrestacionesDao.getPrestacionesAseguradorById(conveniosForm.getIdAseguradores());
//				Collection<Integer> prestacionesAsegurador = new ArrayList<>();
//				for (Prestacionasegurador prestacionasegurador : prestacionaseguradors) {
//					Iterator<Prestacionesaseguradorequivalencias> pseIterator = prestacionasegurador.getPrestacionesaseguradorequivalenciases().iterator();
//					while (pseIterator.hasNext()) {
//						Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) pseIterator.next();
//						prestacionesAsegurador.add(prestacionesaseguradorequivalencias.getCatprestacion().getPrestacionId());
//					}
//				}
//				
//				Collection<Integer> prestacionesPrestador = new ArrayList<>();
//				Prestadores prestadores = prestadoresDao.getPrestadorById(conveniosForm.getIdPrestadores());
//				if (prestadores.getUsarTablaPrestacionesSab() == 0) {
//					Collection<Prestacionprestador> prestacionprestadors = cuadroPrestacionesDao.getPrestacionesPrestadorById(conveniosForm.getIdPrestadores());
//					for (Prestacionprestador prestacionprestador : prestacionprestadors) {
//						Iterator<Prestacionesprestadorequivalencias> ppeIterator = prestacionprestador.getPrestacionesprestadorequivalenciases().iterator();
//						while (ppeIterator.hasNext()) {
//							Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = (Prestacionesprestadorequivalencias) ppeIterator.next();
//							prestacionesPrestador.add(prestacionesprestadorequivalencias.getCatprestacion().getPrestacionId());
//						}
//					}
//				}
//				
//				Cuadroprestaciones cuadroprestaciones = new Cuadroprestaciones();
//				cuadroprestaciones.setActivo(1);
//				cuadroprestaciones.setCuadroPrestacion(conveniosForm.getNombrecuadroPrestacion());
//				conveniosForm.setCuadroprestaciones(cuadroprestaciones);
//				conveniosForm.setCuadroprestaciones(cuadroPrestacionesDao.saveCuadroPrestaciones(conveniosForm.getCuadroprestaciones()));
//				
//				if (prestadores.getUsarTablaPrestacionesSab() == 0) {
//					conveniosForm.setCuadroprestacionPrestacions(cuadroPrestacionesService.generaCuadroPrestacionPrestacion(conveniosForm.getCuadroprestaciones().getCuadroPrestacionId(), prestacionesAsegurador,prestacionesPrestador));
//					CuadroprestacionPrestacionId cuadroprestacionPrestacionId = null;
//					for (CuadroprestacionPrestacion cuadroprestacionPrestacion : conveniosForm.getCuadroprestacionPrestacions()) {
//						cuadroprestacionPrestacionId = new CuadroprestacionPrestacionId();
//						cuadroprestacionPrestacionId.setCuadroPrestacionId(cuadroprestacionPrestacion.getCuadroprestaciones().getCuadroPrestacionId());
//						cuadroprestacionPrestacionId.setPrestacionId(cuadroprestacionPrestacion.getCatprestacion().getPrestacionId());
//						cuadroprestacionPrestacion.setId(cuadroprestacionPrestacionId);
//						cuadroPrestacionesDao.saveCuadroPrestacionPrestacion(cuadroprestacionPrestacion);
//					}
//					convenioCuadroprestacionesId.setCuadroPrestacionId(conveniosForm.getCuadroprestaciones().getCuadroPrestacionId());	
//				}else{
//					CuadroprestacionPrestacionId cuadroprestacionPrestacionId = null;
//					CuadroprestacionPrestacion cuadroprestacionPrestacion = null;
//					for (Integer idPrestacion : prestacionesAsegurador) {
//						cuadroprestacionPrestacionId = new CuadroprestacionPrestacionId();
//						cuadroprestacionPrestacionId.setCuadroPrestacionId(conveniosForm.getCuadroprestaciones().getCuadroPrestacionId());
//						cuadroprestacionPrestacionId.setPrestacionId(idPrestacion);
//						cuadroprestacionPrestacion = new CuadroprestacionPrestacion();
//						cuadroprestacionPrestacion.setId(cuadroprestacionPrestacionId);
//						cuadroprestacionPrestacion.setActivo(1);
//						cuadroPrestacionesDao.saveCuadroPrestacionPrestacion(cuadroprestacionPrestacion);
//					}
//				}
//				
//				convenioCuadroprestacionesId.setCuadroPrestacionId(conveniosForm.getCuadroprestaciones().getCuadroPrestacionId());
//			}
//			
//			if (conveniosForm.isBanderaError()) {
//				conveniosDao.deleteConvenioById(conveniosForm.getConvenios());
//			}else{
//				convenioCuadroprestacionesId.setConvenioId(conveniosForm.getConvenios().getConvenioId());
//				convenioCuadroprestacionesId.setVigenteDesde(FormatUtil.getDate(conveniosForm.getVigenteDesde()));
//				convenioCuadroprestacionesId.setVigenteHasta(FormatUtil.getDate(conveniosForm.getVigenteHasta()));
//				convenioCuadroprestaciones.setId(convenioCuadroprestacionesId);
//				conveniosDao.saveConvenioCuadroPrestaciones(convenioCuadroprestaciones);
//			}
//			
//			
//		}catch(Exception ex){
//			ex.printStackTrace();
//			conveniosForm.setBanderaError(true);
//			conveniosForm.setError("Surgio un error");
//		}
	}

	private boolean existenPrestaciones(Collection<Integer> prestaciones,Collection<Integer> prestacionesCuadro) {
		boolean existenPrestaciones = true;
		for (Integer idPrestacion : prestacionesCuadro) {
			if(!prestaciones.contains(idPrestacion)){
				existenPrestaciones = false;
				break;
			}
		}
		return existenPrestaciones;
	}

	@Override
	public String getNombre(Integer idAseguradores, Integer idLugarDeAtencion, Integer idPrestadores) {
		Aseguradores aseguradores = aseguradorDao.getAseguradorById(idAseguradores);
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionById(idLugarDeAtencion);
		Prestadores prestadores = prestadoresDao.getPrestadorById(idPrestadores);
		String acronimo = FormatUtil.generarAcronimo(aseguradores.getNombreRazonSocial())+"-"+FormatUtil.generarAcronimo(lugaresdeatencion.getDescripcion())+"-"+FormatUtil.generarAcronimo(prestadores.getNombreRazonSocial());
		int total = conveniosDao.getConveniosCount(acronimo);
		if (total>0) {
			acronimo = acronimo+"_"+(total);
		}
		Gson gson = new GsonBuilder().create();
		acronimo = gson.toJson(acronimo);
		return acronimo;
	}

	@Override
	public void update(ConveniosForm conveniosForm) {
		//log.info("update");
		conveniosForm.setExito("");
		conveniosForm.setError("");
		boolean actualizar = false;
		if (conveniosForm.getIdConvenio()!=0) {
			Convenios convenios = conveniosDao.getConveniosById(conveniosForm.getIdConvenio());
			Convenios conveniosAux = conveniosDao.existeConvenio(conveniosForm);
			if (conveniosAux == null) {
				actualizar = true;
			}else {
				if (conveniosAux.getConvenioId() == convenios.getConvenioId()) {
					actualizar = true;
				}else{
					conveniosForm.setError("Ya existe un convenio de las mismas características");
					conveniosForm.setExito("");	
				}
			}	
			
			Iterator<Atencionmedica> iteratorAtencionMedica = convenios.getAtencionmedicas().iterator();
			if (iteratorAtencionMedica.hasNext()) {
				if (!FormatUtil.getDate(conveniosAux.getVigenciaInicial()).equals(convenios.getVigenciaInicial())) {
					actualizar = false;
					conveniosForm.setError("Existen Atenciones registradas para este Convenio, no se puede modificar su fecha de inicio de vigencia");
					conveniosForm.setExito("");						
				}
			}			
			if(actualizar){
				convenios.setConvenio(conveniosForm.getConvenio());
				convenios.setIdentificador(conveniosForm.getIdentificadorConvenio());
				convenios.setVigenciaInicial(FormatUtil.getDate(conveniosForm.getVigenteDesde()));
				convenios.setVigenciaFinal(FormatUtil.getDate(conveniosForm.getVigenteHasta()));
				conveniosDao.update(convenios);
				conveniosForm.setError("");
				conveniosForm.setExito("Se actualizo correctamente el Convenio");					
			}
		}else{
			conveniosForm.setError("Debe de seleccionar un Convenio");
			conveniosForm.setExito("");
		}
	}

}
