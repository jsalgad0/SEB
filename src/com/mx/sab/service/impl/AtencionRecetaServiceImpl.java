package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAtencionRecetaDao;
import com.mx.sab.dao.IFoliosDao;
import com.mx.sab.enums.CatEstatusRecetasEnum;
import com.mx.sab.enums.FoliosEnum;
import com.mx.sab.form.AtencionRecetaForm;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Catmedicamentos;
import com.mx.sab.model.Catunidadesdetiempo;
import com.mx.sab.model.Catviasdeadminmedicamento;
import com.mx.sab.model.Folio;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.model.MedicamentosrecetaId;
import com.mx.sab.model.Recetas;
import com.mx.sab.service.IAtencionRecetaService;
import com.mx.sab.vo.CatMedicamentosVo;
import com.mx.sab.vo.RecetasVo;

@Service
@Log4j2
public class AtencionRecetaServiceImpl implements IAtencionRecetaService {
	
	@Autowired
	private IAtencionRecetaDao atencionRecetaDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IFoliosDao foliosDao;	
	
	@Override
	public void inicializaAtencionRecetaForm(AtencionRecetaForm atencionRecetaForm) {
		atencionRecetaForm.setIdCada(-1);
		atencionRecetaForm.setIdDurante(-1);
		atencionRecetaForm.setIdUnidad(-1);
		List<RecetasVo> recetasVos = new ArrayList<>();
		RecetasVo recetasVo = new RecetasVo();
		Collection<Recetas> recetas = atencionRecetaDao.getRecetasByIdAtencion(atencionRecetaForm.getIdAtencion());
		for (Recetas receta : recetas) {
			if (receta.getResurtible()==1) {
				atencionRecetaForm.setResurtible(true);
				atencionRecetaForm.setRecetaCada(""+receta.getCada());
				atencionRecetaForm.setRecetaDurante(""+receta.getDurante());
			}else{
				atencionRecetaForm.setResurtible(false);
			}
			
			Iterator<Medicamentosreceta> medicamentoRecetaIterator = receta.getMedicamentosrecetas().iterator();
			while (medicamentoRecetaIterator.hasNext()) {
				Medicamentosreceta medicamentosreceta = (Medicamentosreceta) medicamentoRecetaIterator.next();
				recetasVo = new RecetasVo();
				recetasVo.setIdReceta(medicamentosreceta.getId().getRecetaId());
				recetasVo.setIdMedicamento(medicamentosreceta.getId().getMedicamentoId());
				recetasVo.setMedicamento(medicamentosreceta.getCatmedicamentos().getMedicamento().substring(0, 25));
				recetasVo.setUnidad(medicamentosreceta.getCantidadUnidades());
				recetasVo.setIdUnidad(medicamentosreceta.getCatviasdeadminmedicamento().getViaDeAdmonMedicamentoId());
				recetasVo.setUnidadDescripcion(medicamentosreceta.getCatviasdeadminmedicamento().getViaDeAdmonMedicamento());
				recetasVo.setCada(medicamentosreceta.getFrecuenciaDeToma());
				recetasVo.setCadaDescripcion(medicamentosreceta.getCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId().getUnidadTiempo());
				recetasVo.setIdCada(medicamentosreceta.getCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId().getUnidadTiempoId());
				recetasVo.setDurante(medicamentosreceta.getDuracionDeToma());
				recetasVo.setDuranteDescripcion(medicamentosreceta.getCatunidadesdetiempoByDuracionUnidadDeTiempoId().getUnidadTiempo());
				recetasVo.setIdDurante(medicamentosreceta.getCatunidadesdetiempoByDuracionUnidadDeTiempoId().getUnidadTiempoId());
				recetasVo.setDosis(medicamentosreceta.getDosis());
				recetasVo.setDosisNo(medicamentosreceta.getDosisNo());
				recetasVo.setObservaciones(medicamentosreceta.getObservaciones());
				recetasVos.add(recetasVo);
			}
		}
		atencionRecetaForm.setRecetasVos(recetasVos);
	}
	
	@Override
	public List<RecetasVo> getRecetas(int idAtencion) {
		List<RecetasVo> recetasVos = new ArrayList<>();
		RecetasVo recetasVo = new RecetasVo();
		Collection<Recetas> recetas = atencionRecetaDao.getRecetasByIdAtencion(idAtencion);
		for (Recetas receta : recetas) {
			Iterator<Medicamentosreceta> medicamentoRecetaIterator = receta.getMedicamentosrecetas().iterator();
			while (medicamentoRecetaIterator.hasNext()) {
				Medicamentosreceta medicamentosreceta = (Medicamentosreceta) medicamentoRecetaIterator.next();
				recetasVo = new RecetasVo();
				recetasVo.setIdReceta(medicamentosreceta.getId().getRecetaId());
				recetasVo.setIdMedicamento(medicamentosreceta.getId().getMedicamentoId());
				recetasVo.setMedicamento(medicamentosreceta.getCatmedicamentos().getMedicamento());
				recetasVo.setUnidad(medicamentosreceta.getCantidadUnidades());
				recetasVo.setIdUnidad(medicamentosreceta.getCatviasdeadminmedicamento().getViaDeAdmonMedicamentoId());
				recetasVo.setUnidadDescripcion(medicamentosreceta.getCatviasdeadminmedicamento().getViaDeAdmonMedicamento());
				recetasVo.setCada(medicamentosreceta.getFrecuenciaDeToma());
				recetasVo.setCadaDescripcion(medicamentosreceta.getCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId().getUnidadTiempo());
				recetasVo.setIdCada(medicamentosreceta.getCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId().getUnidadTiempoId());
				recetasVo.setDurante(medicamentosreceta.getDuracionDeToma());
				recetasVo.setDuranteDescripcion(medicamentosreceta.getCatunidadesdetiempoByDuracionUnidadDeTiempoId().getUnidadTiempo());
				recetasVo.setIdDurante(medicamentosreceta.getCatunidadesdetiempoByDuracionUnidadDeTiempoId().getUnidadTiempoId());
				recetasVo.setDosis(medicamentosreceta.getDosis());
				recetasVo.setDosisNo(medicamentosreceta.getDosisNo());				
				recetasVo.setObservaciones(medicamentosreceta.getObservaciones());
				recetasVos.add(recetasVo);
			}
		}
		return recetasVos;
	}	

	@Override
	public Collection<Catviasdeadminmedicamento> getCatViaAdminMedicamento() {
		Collection<Catviasdeadminmedicamento> catviasdeadminmedicamentos = atencionRecetaDao.getCatViaAdminMedicamento();
		return catviasdeadminmedicamentos;
	}

	@Override
	public Collection<Catunidadesdetiempo> getCatUnidadesTiempoMenorDia() {
		Collection<Catunidadesdetiempo> catunidadesdetiempos = atencionRecetaDao.getCatUnidadesTiempoMenorDia();
		return catunidadesdetiempos;
	}

	@Override
	public Collection<Catunidadesdetiempo> getCatUnidadesTiempoMayorDia() {
		Collection<Catunidadesdetiempo> catunidadesdetiempos = atencionRecetaDao.getCatUnidadesTiempoMayorDia();
		return catunidadesdetiempos;
	}

	@Override
	public Collection<CatMedicamentosVo> medicamentos(String busqueda, int idAtencion) {
		Collection<CatMedicamentosVo> catMedicamentosVos = new ArrayList<>();
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(idAtencion);
		Collection<Catmedicamentos> catmedicamentos = atencionRecetaDao.getCatMedicamentos(busqueda, atencionmedica.getAseguradores().getAseguradorId());
		CatMedicamentosVo catMedicamentosVo = new CatMedicamentosVo();
		for (Catmedicamentos catmedicamento : catmedicamentos) {
			catMedicamentosVo = new CatMedicamentosVo();
			catMedicamentosVo.setIdMedicamento(catmedicamento.getMedicamentoId());
			catMedicamentosVo.setMedicamento(catmedicamento.getMedicamento());
			catMedicamentosVos.add(catMedicamentosVo);
		}
		
		if (catMedicamentosVos.size()==0) {
			catMedicamentosVo = new CatMedicamentosVo();
			catMedicamentosVo.setMedicamento("No hay medicamentos coincidentes");
			catMedicamentosVo.setIdMedicamento(-1);
			catMedicamentosVos.add(catMedicamentosVo);
		}
		return catMedicamentosVos;
	}

	@Override
	public void saveReceta(AtencionRecetaForm atencionRecetaForm,Collection<RecetasVo> recetasVos) {
		boolean recetaSeleccionado = false;
		for (RecetasVo recetasVo : atencionRecetaForm.getRecetasVos()) {
			if (recetasVo!=null) {
				if (recetasVo.getIdMedicamento()!=0) {
					recetaSeleccionado = false;
					Iterator<RecetasVo> recetaVoIterator = recetasVos.iterator();
					while (recetaVoIterator.hasNext()) {
						RecetasVo recetasVoAux = (RecetasVo) recetaVoIterator.next();
						if (recetasVo.getIdMedicamento() == recetasVoAux.getIdMedicamento()) {
							recetaSeleccionado = true;
							recetaVoIterator.remove();
							if (recetasVo.getIdMedicamento()==recetasVoAux.getIdMedicamento() &&
								recetasVo.getIdCada()==recetasVoAux.getIdCada() &&
								recetasVo.getIdDurante()==recetasVoAux.getIdDurante() &&
								recetasVo.getIdUnidad()==recetasVoAux.getIdUnidad() &&
								recetasVo.getCada()==recetasVoAux.getCada() &&
								recetasVo.getDurante()==recetasVoAux.getDurante() &&
								recetasVo.getUnidad()==recetasVoAux.getUnidad() &&
								recetasVo.getObservaciones().equals(recetasVoAux.getObservaciones()) &&
								recetasVo.getDosis().equals(recetasVoAux.getDosis()) &&
								recetasVo.getDosisNo().equals(recetasVoAux.getDosisNo())) {
								break;
							}else{
								Recetas recetas = atencionRecetaDao.getRecetasById(recetasVo.getIdReceta());
								Medicamentosreceta medicamentosreceta = atencionRecetaDao.getMedicamentoRecetaById(recetasVoAux.getIdReceta());
								String folio = medicamentosreceta.getFolio();
								atencionRecetaDao.deleteMedicamentoReceta(medicamentosreceta);
								medicamentosreceta = new Medicamentosreceta();
								MedicamentosrecetaId medicamentosrecetaId = new MedicamentosrecetaId();
								medicamentosrecetaId.setMedicamentoId(recetasVo.getIdMedicamento());
								medicamentosrecetaId.setRecetaId(recetasVoAux.getIdReceta());
								medicamentosreceta.setId(medicamentosrecetaId);
								medicamentosreceta.setCantidadUnidades(recetasVo.getUnidad());
								medicamentosreceta.setCatmedicamentos(atencionRecetaDao.getCatMedicamentoById(recetasVo.getIdMedicamento()));
								medicamentosreceta.setCatunidadesdetiempoByDuracionUnidadDeTiempoId(atencionRecetaDao.getCatUnidadesDeTiempoById(recetasVo.getIdDurante()));
								medicamentosreceta.setCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId(atencionRecetaDao.getCatUnidadesDeTiempoById(recetasVo.getIdCada()));
								medicamentosreceta.setCatviasdeadminmedicamento(atencionRecetaDao.getCatViaAdminMedicamentoById(recetasVo.getIdUnidad()));
								medicamentosreceta.setDuracionDeToma(recetasVo.getDurante());
								medicamentosreceta.setFrecuenciaDeToma(recetasVo.getCada());
								medicamentosreceta.setObservaciones(recetasVo.getObservaciones());
								medicamentosreceta.setRecetas(recetas);
								medicamentosreceta.setUnidad(""+recetasVo.getIdUnidad());
								medicamentosreceta.setDosis(recetasVo.getDosis());
								medicamentosreceta.setDosisNo(recetasVo.getDosisNo());
								medicamentosreceta.setFolio(folio);
								atencionRecetaDao.saveMedicamentoReceta(medicamentosreceta);							
							}
						}
					}
					if (!recetaSeleccionado) {
						Recetas recetas = new Recetas();
						Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(atencionRecetaForm.getIdAtencion());
						recetas.setAtencionmedica(atencionmedica);
						recetas.setCatestatusrecetas(atencionRecetaDao.getCatEstatusReceta(CatEstatusRecetasEnum.PENDIENTE.getId()));
						recetas.setResurtible(1);
						recetas.setFolio(1);
						atencionRecetaDao.saveReceta(recetas);
						recetas = atencionRecetaDao.getRecetasById(recetas.getRecetaId());
						Medicamentosreceta medicamentosreceta = new Medicamentosreceta();
						MedicamentosrecetaId medicamentosrecetaId = new MedicamentosrecetaId();
						medicamentosrecetaId.setMedicamentoId(recetasVo.getIdMedicamento());
						medicamentosrecetaId.setRecetaId(recetas.getRecetaId());
						medicamentosreceta.setId(medicamentosrecetaId);
						medicamentosreceta.setCantidadUnidades(recetasVo.getUnidad());
						medicamentosreceta.setCatmedicamentos(atencionRecetaDao.getCatMedicamentoById(recetasVo.getIdMedicamento()));
						medicamentosreceta.setCatunidadesdetiempoByDuracionUnidadDeTiempoId(atencionRecetaDao.getCatUnidadesDeTiempoById(recetasVo.getIdDurante()));
						medicamentosreceta.setCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId(atencionRecetaDao.getCatUnidadesDeTiempoById(recetasVo.getIdCada()));
						medicamentosreceta.setCatviasdeadminmedicamento(atencionRecetaDao.getCatViaAdminMedicamentoById(recetasVo.getIdUnidad()));
						medicamentosreceta.setDuracionDeToma(recetasVo.getDurante());
						medicamentosreceta.setFrecuenciaDeToma(recetasVo.getCada());
						medicamentosreceta.setObservaciones(recetasVo.getObservaciones());
						medicamentosreceta.setRecetas(recetas);
						medicamentosreceta.setUnidad(""+recetasVo.getIdUnidad());	
						medicamentosreceta.setDosis(recetasVo.getDosis());
						medicamentosreceta.setDosisNo(recetasVo.getDosisNo());
						
						Folio folio = foliosDao.getFolioById(FoliosEnum.RECETA_MEDICA.getId(), atencionmedica.getLugaresdeatencion().getLugarDeAtencionId());
						folio.setConsecutivo(folio.getConsecutivo()+1);
						medicamentosreceta.setFolio(""+folio.getConsecutivo());
						foliosDao.updateFolio(folio);						
						atencionRecetaDao.saveMedicamentoReceta(medicamentosreceta);
					}
				}
			}
		}
		
		for (RecetasVo recetasVo : recetasVos) {
			Recetas recetas = atencionRecetaDao.getRecetasById(recetasVo.getIdReceta());
			atencionRecetaDao.deleteReceta(recetas);
		}
		
		int resurtible = 0;
		int recetaCada = 0;
		int recetaDurante = 0;
		if (atencionRecetaForm.isResurtible()) {
			resurtible = 1;
			recetaCada = Integer.parseInt(atencionRecetaForm.getRecetaCada());
			recetaDurante = Integer.parseInt(atencionRecetaForm.getRecetaDurante());				
		}
		
		
		inicializaAtencionRecetaForm(atencionRecetaForm);
		
		for (RecetasVo recetasVo : atencionRecetaForm.getRecetasVos()) {
			Recetas recetas = atencionRecetaDao.getRecetasById(recetasVo.getIdReceta());
			if (resurtible == 1) {
				recetas.setResurtible(1);
				recetas.setCada(recetaCada);
				recetas.setDurante(recetaDurante);				
			}else{
				recetas.setResurtible(0);
				recetas.setCada(null);
				recetas.setDurante(null);
			}
			atencionRecetaDao.updateReceta(recetas);
		}
	}

}
