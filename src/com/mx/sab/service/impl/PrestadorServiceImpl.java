package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IPrestadoresDao;
import com.mx.sab.form.PrestadorForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Cattipoprestador;
import com.mx.sab.model.Cattipospersonas;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.Prestadores;
import com.mx.sab.model.Propietarioslector;
import com.mx.sab.service.IGenericService;
import com.mx.sab.service.IPrestadorService;
import com.mx.sab.service.IRegistroLectoresService;
import com.mx.sab.vo.CatTiposPersonasVo;
import com.mx.sab.vo.PrestadoresVo;

@Service
@Log4j2
public class PrestadorServiceImpl implements IPrestadorService {
	
	@Autowired
	private IGenericService genericService;
	
	@Autowired
	private IPrestadoresDao prestadoresDao;
	
	@Autowired
	private IRegistroLectoresService registroLectoresService;
			
	@Override
	public void save(PrestadorForm prestadorForm) {
		//log.info("save");
		try{
			if (prestadoresDao.existeDireccionPrestador(prestadorForm)==null) {
				if (prestadoresDao.getPrestadoresByNombre(prestadorForm.getPrestador())==null) {
					if (prestadoresDao.getPrestadoresByRfc(prestadorForm.getRfc())==null) {
						Prestadores prestadores = new Prestadores();
						prestadores.setNombreRazonSocial(prestadorForm.getPrestador());
						prestadores.setRfc(prestadorForm.getRfc());
						prestadores.setCatcolonias(genericService.getColoniaById(prestadorForm.getIdColonia()));
						prestadores.setCatestados(genericService.getEstadoById(prestadorForm.getIdEstado()));
						prestadores.setCatmunicipios(genericService.getMunicipioById(prestadorForm.getIdMunicipio()));
						prestadores.setCalle(prestadorForm.getCalle());
						prestadores.setNoExt(prestadorForm.getNumeroExterno());
						prestadores.setNoInt(prestadorForm.getNumeroInterno());
						prestadores.setCp(prestadorForm.getCp());
						prestadores.setNombreContacto(prestadorForm.getNombre());
						prestadores.setTelefono(prestadorForm.getTelefono1());
						prestadores.setTelefono2(prestadorForm.getTelefono2());
						prestadores.setCorreoElectronico(prestadorForm.getCorreo());
						prestadores.setActivo(1);
						
						prestadores.setUsarTablaPrestacionesSab(0);
						
//						if (prestadorForm.isUsarTablaPrestacionesSab()) {
//							prestadorForm.getPrestadores().setUsarTablaPrestacionesSab(1);
//						}else {
//							prestadorForm.getPrestadores().setUsarTablaPrestacionesSab(0);
//						}
						
						prestadoresDao.save(prestadores);
						
						if(registroLectoresService.getPropietariosLectorByRfc(prestadores.getRfc())==null){
							Propietarioslector propietarioslector = new Propietarioslector();
							propietarioslector.setPropietarioLector(prestadores.getNombreRazonSocial());
							propietarioslector.setRfc(prestadores.getRfc());
							registroLectoresService.savePropietarioLector(propietarioslector);	
						}
						
						prestadorForm.setError("");
						prestadorForm.setExito("El Prestador fue creado correctamente");
					}else {
						prestadorForm.setError("El Prestador, ya está registrado");
						prestadorForm.setExito("");
					}
				}else {
					prestadorForm.setError("El Prestador, ya está registrado");
					prestadorForm.setExito("");
				}				
			}else{
				prestadorForm.setError("No se puede repetir la direccion");
				prestadorForm.setExito("");
			}

			
		}catch(Exception ex){
			ex.printStackTrace();
			prestadorForm.setExito("");
			prestadorForm.setError("Surgio un error");
		}
		
	}

	@Override
	public Collection<Prestadores> getPrestadores(PrestadorForm prestadorForm) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<Prestadores> prestadores = null;
		
//		if (prestadorForm==null) {
//			prestadorForm = new PrestadorForm();
//		}
//		
//		if (prestadorForm.getBusqueda()==null) {
//			prestadorForm.setBusqueda("");	
//		}
//		int totalPrestadores = prestadoresDao.getPrestadoresCount(prestadorForm.getBusqueda());
//		if (totalPrestadores>0) {
//			paginasTotal = totalPrestadores / filas;
//			if (totalPrestadores % filas != 0) {
//				paginasTotal++;
//			}
//			
//			if (paginasTotal>7) {
//				prestadorForm.setDisplay(7);
//			}else {
//				prestadorForm.setDisplay(paginasTotal);
//			}
//			
//			prestadorForm.setCount(paginasTotal);
//			inicio = (pagina-1)*7;
//			fin = (pagina*7);
//			
//			prestadores = prestadoresDao.getPrestadores(prestadorForm.getBusqueda(),inicio,fin);
//		}else {
//			//log.info("No hay prestadores");
//		}
	
		return prestadores;
	}
	
	@Override
	public Collection<Cattipospersonas> getCatTiposPersonas() {
		return prestadoresDao.getCatTiposPersonas();
	}

	@Override
	public Collection<Cattipoprestador> getCatTipoPrestador() {
		return prestadoresDao.getCatTipoPrestador();
	}

	@Override
	public void delete(PrestadorForm prestadorForm) {
		prestadorForm.setExito("");
		prestadorForm.setError("");
		Prestadores prestadores = prestadoresDao.getPrestadorById(prestadorForm.getIdPrestador());
		if (prestadores!=null) {
			Iterator<Convenios> iteratorConvenios = prestadores.getConvenioses().iterator();
			if (iteratorConvenios.hasNext()) {
				prestadorForm.setExito("");
				prestadorForm.setError("Hay Convenios vigentes para este Prestador, no se puede Eliminar");
			}else{
				Iterator<Atencionmedica> iteratorAtencionMedica = prestadores.getAtencionmedicas().iterator();
				if (iteratorAtencionMedica.hasNext()) {
					prestadorForm.setExito("");
					prestadorForm.setError("Hay Atenciones médicas registradas para este Prestador, no se puede Eliminar");			
				}else{
					prestadoresDao.delete(prestadores);
					prestadorForm.setExito("Se ha eliminado correctamente el Prestador");
					prestadorForm.setError("");
					prestadorForm.setEditar(false);
					prestadorForm.setBusqueda(0);
					prestadorForm.setIdPrestador(0);
					prestadorForm.setRfc("");
					prestadorForm.setPrestador("");
					prestadorForm.setCalle("");
					prestadorForm.setNumeroExterno("");
					prestadorForm.setNumeroInterno("");
					prestadorForm.setIdEstado(-1);
					prestadorForm.setIdColonia(-1);
					prestadorForm.setIdMunicipio(-1);
					prestadorForm.setCp("");
					prestadorForm.setNombre("");
					prestadorForm.setTelefono1("");
					prestadorForm.setTelefono2("");
					prestadorForm.setCorreo("");	
				}	
			}	
		}else{
			prestadorForm.setError("Debe de buscar un Prestador");
			prestadorForm.setExito("");
		}
	}

	@Override
	public void getPrestador(PrestadorForm prestadorForm) {
		prestadorForm.setExito("");
		prestadorForm.setError("");
		if (prestadorForm.getRfc().trim().length()!=0 || prestadorForm.getIdPrestador()!=0) {
			Prestadores prestadores = null;
			if (prestadorForm.getBusqueda()==0){
				prestadores = prestadoresDao.getPrestadoresByRfc(prestadorForm.getRfc());
			}else{
				prestadores = prestadoresDao.getPrestadorById(prestadorForm.getIdPrestador());	
			}
			prestadorForm.setBusqueda(0);
			if (prestadores!=null) {
				prestadorForm.setPrestador(prestadores.getNombreRazonSocial());
				prestadorForm.setIdPrestador(prestadores.getPrestadorId());
				prestadorForm.setCalle(prestadores.getCalle());
				prestadorForm.setNumeroExterno(prestadores.getNoExt());
				prestadorForm.setNumeroInterno(prestadores.getNoInt());
				prestadorForm.setIdColonia(prestadores.getCatcolonias().getColoniaId());
				prestadorForm.setIdEstado(prestadores.getCatestados().getEstadoId());
				prestadorForm.setIdMunicipio(prestadores.getCatmunicipios().getMunicipioId());
				prestadorForm.setCp(prestadores.getCp());
				prestadorForm.setNombre(prestadores.getNombreContacto());
				prestadorForm.setTelefono1(prestadores.getTelefono());
				prestadorForm.setTelefono2(prestadores.getTelefono2());
				prestadorForm.setCorreo(prestadores.getCorreoElectronico());
				prestadorForm.setRfc(prestadores.getRfc());
				prestadorForm.setEditar(true);
				prestadorForm.setError("");	
			}else{
				prestadorForm.setError("Prestador no registrado en el sistema");
			}
		}else{
			prestadorForm.setError("Ingrese el RFC o el Nombre");
		}
	}

	@Override
	public void update(PrestadorForm prestadorForm) {
		//log.info("update");
		boolean actualizar = false;
		try{
			if (prestadorForm.isEditar()) {
				Prestadores prestadores = prestadoresDao.getPrestadorById(prestadorForm.getIdPrestador());
				Prestadores prestadoresAux = prestadoresDao.existeDireccionPrestador(prestadorForm);
				if (prestadoresAux == null) {
					actualizar = true;
				}else {
					if (prestadoresAux.getPrestadorId() == prestadores.getPrestadorId()) {
						actualizar = true;
					}else{
						prestadorForm.setError("No se puede repetir la direccion");
						prestadorForm.setExito("");	
					}
				}
				if (actualizar) {
					prestadores.setNombreRazonSocial(prestadorForm.getPrestador());
					prestadores.setRfc(prestadorForm.getRfc());
					prestadores.setCatcolonias(genericService.getColoniaById(prestadorForm.getIdColonia()));
					prestadores.setCatestados(genericService.getEstadoById(prestadorForm.getIdEstado()));
					prestadores.setCatmunicipios(genericService.getMunicipioById(prestadorForm.getIdMunicipio()));
					prestadores.setCalle(prestadorForm.getCalle());
					prestadores.setNoExt(prestadorForm.getNumeroExterno());
					prestadores.setNoInt(prestadorForm.getNumeroInterno());
					prestadores.setCp(prestadorForm.getCp());
					prestadores.setNombreContacto(prestadorForm.getNombre());
					prestadores.setTelefono(prestadorForm.getTelefono1());
					prestadores.setTelefono2(prestadorForm.getTelefono2());
					prestadores.setCorreoElectronico(prestadorForm.getCorreo());
					prestadores.setActivo(1);
					prestadores.setUsarTablaPrestacionesSab(0);
					
					prestadoresDao.update(prestadores);
					prestadorForm.setExito("Se actualizo correctamente el Prestador");					
				}
			}else{
				prestadorForm.setError("Debe de buscar un Prestador");
				prestadorForm.setExito("");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			prestadorForm.setError("Surgio un error");
			prestadorForm.setExito("");
		}
	}

	@Override
	public Collection<PrestadoresVo> getPrestadores(String busqueda, int page) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<PrestadoresVo> prestadoresVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Prestadores> prestadores = prestadoresDao.getPrestadores(busqueda,inicio,fin);
		for (Prestadores prestador : prestadores) {
			PrestadoresVo prestadoresVo = new PrestadoresVo();
			prestadoresVo.setNombreRazonSocial(prestador.getNombreRazonSocial());
			prestadoresVo.setPrestadorId(prestador.getPrestadorId());
			prestadoresVo.setRfc(prestador.getRfc());
			CatTiposPersonasVo catTiposPersonasVo = new CatTiposPersonasVo();
			catTiposPersonasVo.setTipoPersona(prestador.getCattipospersonas().getTipoPersona());
			prestadoresVo.setCattipospersonas(catTiposPersonasVo);
			prestadoresVos.add(prestadoresVo);
		}
		
		return prestadoresVos;
	}

	@Override
	public Collection<Prestadores> getPrestadores() {
		Collection<Prestadores> prestadores = prestadoresDao.getPrestadores();
		return prestadores;
	}

	@Override
	public Collection<Prestadores> getPrestadoresSinPrestaciones() {
		Collection<Prestadores> prestadores = prestadoresDao.getPrestadoresSinPrestaciones();
		Collection<Prestadores> prestadoresSinPrestaciones = new ArrayList<>();
		for (Prestadores prestador : prestadores) {
			if(prestador.getPrestacionprestadors().size()==0){
				prestadoresSinPrestaciones.add(prestador);
			}
		}
		return prestadoresSinPrestaciones;
	}

	@Override
	public Collection<Prestadores> getPrestadoresConPrestaciones() {
		Collection<Prestadores> prestadores = prestadoresDao.getPrestadores();
		Collection<Prestadores> prestadoresConPrestaciones = new ArrayList<>();
		for (Prestadores prestador : prestadores) {
			if (prestador.getPrestacionprestadors().size()!=0) {
				prestadoresConPrestaciones.add(prestador);
			}else if (prestador.getUsarTablaPrestacionesSab() == 1) {
				prestadoresConPrestaciones.add(prestador);
			}
		}
		
		return prestadoresConPrestaciones;
	}

	@Override
	public Collection<PrestadoresVo> getPrestadoresByDescripcion(String busqueda) {
		Collection<Prestadores> prestadores = prestadoresDao.getPrestadoresByNombreBusqueda(busqueda);
		Collection<PrestadoresVo> prestadoresVos = new ArrayList<>();
		PrestadoresVo prestadoresVo = null;
		for (Prestadores prestador : prestadores) {
			prestadoresVo = new PrestadoresVo();
			prestadoresVo.setNombreRazonSocial(prestador.getNombreRazonSocial());
			prestadoresVo.setPrestadorId(prestador.getPrestadorId());
			prestadoresVo.setRfc(prestador.getRfc());
			prestadoresVos.add(prestadoresVo);
		}
		if (prestadoresVos.size()==0) {
			prestadoresVo = new PrestadoresVo();
			prestadoresVo.setNombreRazonSocial("No hay prestadores coincidentes");
			prestadoresVo.setPrestadorId(-1);
			prestadoresVos.add(prestadoresVo);
		}
		return prestadoresVos;
	}

	@Override
	public PrestadoresVo getPrestadoresById(int id) {
		Prestadores prestador = prestadoresDao.getPrestadorById(id);
		PrestadoresVo prestadoresVo = new PrestadoresVo();
		prestadoresVo.setNombreRazonSocial(prestador.getNombreRazonSocial());
		prestadoresVo.setPrestadorId(prestador.getPrestadorId());
		prestadoresVo.setRfc(prestador.getRfc());
		return prestadoresVo;
	}
	
}
