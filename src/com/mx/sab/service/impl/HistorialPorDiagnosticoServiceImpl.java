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
import com.mx.sab.dao.IAtencionMedicaDao;
import com.mx.sab.form.HistorialPorDiagnosticoForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.service.IHistorialPorDiagnosticoService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.HistorialAtencionesVo;
import com.mx.sab.vo.HistorialPorDiagnosticoVo;

@Service
@Log4j2
public class HistorialPorDiagnosticoServiceImpl implements IHistorialPorDiagnosticoService{
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IAtencionMedicaDao atencionMedicaDao;
	
	@Override
	public Collection<HistorialPorDiagnosticoVo> getDiagnosticos(HistorialPorDiagnosticoForm historialPorDiagnosticoForm) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Afiliado afiliado = agendaDao.getAtencionMedicaById(historialPorDiagnosticoForm.getIdAtencion()).getAfiliado();
		historialPorDiagnosticoForm.setIdAfiliado(afiliado.getAfiliadoId());
		Collection<NotamedicaCies10> notamedicasCies10s = atencionMedicaDao.getNotamedicaCies10ByIdAfiliado(historialPorDiagnosticoForm.getIdAfiliado());
		HashMap<Integer, NotamedicaCies10> notaMedicaCies10Map = new HashMap<>();
		for (NotamedicaCies10 notamedicaCies10 : notamedicasCies10s) {
			if (notaMedicaCies10Map.get(notamedicaCies10.getCatcies10().getCie10id())==null) {
				notaMedicaCies10Map.put(notamedicaCies10.getCatcies10().getCie10id(), notamedicaCies10);
			}
		}
		
		List<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos = new ArrayList<>();
		Iterator<Entry<Integer, NotamedicaCies10>> entry = notaMedicaCies10Map.entrySet().iterator();
		while (entry.hasNext()) {
			Map.Entry<Integer, NotamedicaCies10> entry2 = (Map.Entry<Integer, NotamedicaCies10>) entry.next();
			HistorialPorDiagnosticoVo historialPorDiagnosticoVo = new HistorialPorDiagnosticoVo();
			historialPorDiagnosticoVo.setIdDiagnostico(entry2.getValue().getCatcies10().getCie10id());
			historialPorDiagnosticoVo.setFecha(FormatUtil.getDate(entry2.getValue().getNotamedica().getAtencionmedica().getFechaAsistio()));
			historialPorDiagnosticoVo.setDiagnostico(entry2.getValue().getCatcies10().getDescripcion());
			historialPorDiagnosticoVo.setLugarAtencion(entry2.getValue().getNotamedica().getAtencionmedica().getLugaresdeatencion().getDescripcion());
			historialPorDiagnosticoVos.add(historialPorDiagnosticoVo);
		}
		
		int totalDiagnosticos = historialPorDiagnosticoVos.size();
		
		if (totalDiagnosticos>0) {
			paginasTotal = totalDiagnosticos / filas;
			if (totalDiagnosticos % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				historialPorDiagnosticoForm.setDisplay(7);
			}else {
				historialPorDiagnosticoForm.setDisplay(paginasTotal);
			}
			
			historialPorDiagnosticoForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			historialPorDiagnosticoForm.setHistorialPorDiagnosticoVos(historialPorDiagnosticoVos);
			
			if (fin>totalDiagnosticos) {
				historialPorDiagnosticoVos = historialPorDiagnosticoVos.subList(inicio, totalDiagnosticos);
			}else{
				historialPorDiagnosticoVos = historialPorDiagnosticoVos.subList(inicio, fin);	
			}
		}else {
			//log.info("No hay usuarios");
			historialPorDiagnosticoForm.setSinResultados(true);
		}
		
		return historialPorDiagnosticoVos;
	}

	@Override
	public Collection<HistorialPorDiagnosticoVo> getDiagnosticos(List<HistorialPorDiagnosticoVo> historialPorDiagnosticoVosAux, int page) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		List<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		int totalDiagnosticos = historialPorDiagnosticoVosAux.size();
		
		if (fin>totalDiagnosticos) {
			historialPorDiagnosticoVos = historialPorDiagnosticoVosAux.subList(inicio, totalDiagnosticos);
		}else{
			historialPorDiagnosticoVos = historialPorDiagnosticoVosAux.subList(inicio, fin);	
		}
		
		return historialPorDiagnosticoVos;
	}

	@Override
	public Collection<HistorialPorDiagnosticoVo> getDiagnosticosDetallados(HistorialPorDiagnosticoForm historialPorDiagnosticoForm) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos = new ArrayList<>();
		int totalDiagnosticos = atencionMedicaDao.getCountDiagnosticosByIdDiagnostico(historialPorDiagnosticoForm.getIdAfiliado(), historialPorDiagnosticoForm.getIdDiagnostico());
		
		if (totalDiagnosticos>0) {
			
			paginasTotal = totalDiagnosticos / filas;
			if (totalDiagnosticos % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				historialPorDiagnosticoForm.setDisplay(7);
			}else {
				historialPorDiagnosticoForm.setDisplay(paginasTotal);
			}
			
			historialPorDiagnosticoForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			Collection<NotamedicaCies10> notamedicaCies10s = atencionMedicaDao.getDiagnosticosByIdDiagnostico(historialPorDiagnosticoForm.getIdAfiliado(), historialPorDiagnosticoForm.getIdDiagnostico(), inicio, fin);
			for (NotamedicaCies10 notamedicaCies10 : notamedicaCies10s) {
				HistorialPorDiagnosticoVo historialPorDiagnosticoVo = new HistorialPorDiagnosticoVo();
				historialPorDiagnosticoVo.setIdAtencion(notamedicaCies10.getNotamedica().getAtencionmedica().getAtencionMedicaId());
				historialPorDiagnosticoVo.setFecha(FormatUtil.getDate(notamedicaCies10.getNotamedica().getAtencionmedica().getFechaAsistio()));
				historialPorDiagnosticoVo.setLugarAtencion(notamedicaCies10.getNotamedica().getAtencionmedica().getLugaresdeatencion().getDescripcion());
				Iterator<Atencionprestacion> atencionPrestacionIterator = notamedicaCies10.getNotamedica().getAtencionmedica().getAtencionprestacions().iterator();
				while (atencionPrestacionIterator.hasNext()) {
					Atencionprestacion atencionprestacion = (Atencionprestacion) atencionPrestacionIterator.next();
					Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = atencionprestacion.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
					while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
						Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
						historialPorDiagnosticoVo.setPrestacion(prestacionesaseguradorequivalencias.getPrestacionasegurador().getDescripcion());
						break;
					}
					break;
				}
				historialPorDiagnosticoVos.add(historialPorDiagnosticoVo);
			}
		}else {
			//log.info("No hay usuarios");
			historialPorDiagnosticoForm.setSinResultados(true);
		}
		
		return historialPorDiagnosticoVos;
	}

	@Override
	public Collection<HistorialPorDiagnosticoVo> getDiagnosticos(int idAfiliado, int idDiagnostico, int page) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		List<HistorialPorDiagnosticoVo> historialPorDiagnosticoVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		
		Collection<NotamedicaCies10> notamedicaCies10s = atencionMedicaDao.getDiagnosticosByIdDiagnostico(idAfiliado, idDiagnostico, inicio, fin);
		for (NotamedicaCies10 notamedicaCies10 : notamedicaCies10s) {
			HistorialPorDiagnosticoVo historialPorDiagnosticoVo = new HistorialPorDiagnosticoVo();
			historialPorDiagnosticoVo.setIdAtencion(notamedicaCies10.getNotamedica().getAtencionmedica().getAtencionMedicaId());
			historialPorDiagnosticoVo.setFecha(FormatUtil.getDate(notamedicaCies10.getNotamedica().getAtencionmedica().getFechaAsistio()));
			historialPorDiagnosticoVo.setLugarAtencion(notamedicaCies10.getNotamedica().getAtencionmedica().getLugaresdeatencion().getDescripcion());
			Iterator<Atencionprestacion> atencionPrestacionIterator = notamedicaCies10.getNotamedica().getAtencionmedica().getAtencionprestacions().iterator();
			while (atencionPrestacionIterator.hasNext()) {
				Atencionprestacion atencionprestacion = (Atencionprestacion) atencionPrestacionIterator.next();
				Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = atencionprestacion.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
				while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
					Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
					historialPorDiagnosticoVo.setPrestacion(prestacionesaseguradorequivalencias.getPrestacionasegurador().getDescripcion());
					break;
				}
				break;
			}
			historialPorDiagnosticoVos.add(historialPorDiagnosticoVo);
		}
		return historialPorDiagnosticoVos;
	}
	
}
