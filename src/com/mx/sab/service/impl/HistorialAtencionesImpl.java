package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IHistorialAtencionesDao;
import com.mx.sab.form.HistorialAtencionesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.service.IHistorialAtencionesService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.HistorialAtencionesVo;

@Service
@Log4j2
public class HistorialAtencionesImpl implements IHistorialAtencionesService {

	@Autowired
	private IHistorialAtencionesDao historialAtencionesDao;
	
	@Autowired
	private IAgendaDao agendaDao;

	@Override
	public Collection<HistorialAtencionesVo> getHistorial(HistorialAtencionesForm historialAtencionesForm) {
		int filas = 7;
		int paginasTotal = 0;
		int inicio = 0;
		int fin = 0;
		int pagina = 1;
		Collection<HistorialAtencionesVo> historialAtencionesVos = new ArrayList<>();
		
		int totalHistorialAtenciones = 0;
		Afiliado afiliado = agendaDao.getAtencionMedicaById(historialAtencionesForm.getIdAtencion()).getAfiliado();
		historialAtencionesForm.setIdAfiliado(afiliado.getAfiliadoId());
		totalHistorialAtenciones = historialAtencionesDao.getHistorialCount(historialAtencionesForm.getIdAfiliado());

		if (totalHistorialAtenciones>0) {
			paginasTotal = totalHistorialAtenciones / filas;
			if (totalHistorialAtenciones % filas != 0) {
				paginasTotal++;
			}
			
			if (paginasTotal>7) {
				historialAtencionesForm.setDisplay(7);
			}else {
				historialAtencionesForm.setDisplay(paginasTotal);
			}
			
			historialAtencionesForm.setCount(paginasTotal);
			inicio = (pagina-1)*7;
			fin = (pagina*7);
			
			Collection<Atencionmedica> atencionmedicas = historialAtencionesDao.getHistorial(historialAtencionesForm.getIdAfiliado(), inicio, fin);
			
			for (Atencionmedica atencionmedica : atencionmedicas) {
				HistorialAtencionesVo historialAtencionesVo = new HistorialAtencionesVo();
				historialAtencionesVo.setFecha(FormatUtil.getDate(atencionmedica.getFechaAsistio()));
				historialAtencionesVo.setIdAtencion(atencionmedica.getAtencionMedicaId());
				historialAtencionesVo.setLugarAtencion(atencionmedica.getLugaresdeatencion().getDescripcion());
				Iterator<Atencionprestacion> atencionPrestacionIterator = atencionmedica.getAtencionprestacions().iterator();
				while (atencionPrestacionIterator.hasNext()) {
					Atencionprestacion atencionprestacion = (Atencionprestacion) atencionPrestacionIterator.next();
					Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = atencionprestacion.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
					while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
						Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
						historialAtencionesVo.setPrestacion(prestacionesaseguradorequivalencias.getPrestacionasegurador().getDescripcion());
						break;
					}
					break;
				}
				historialAtencionesVos.add(historialAtencionesVo);
			}
		}else {
			//log.info("No hay atenciones");
			historialAtencionesForm.setSinResultados(true);
		}
	
		return historialAtencionesVos;
	}

	@Override
	public Collection<HistorialAtencionesVo> getHistorial(int idAfiliado, int page) {
		int inicio = 0;
		int fin = 0;
		int pagina = page;
		Collection<HistorialAtencionesVo> historialAtencionesVos = new ArrayList<>();
		inicio = (pagina-1)*7;
		fin = 7;
		Collection<Atencionmedica> atencionmedicas = new ArrayList<>();
		atencionmedicas = historialAtencionesDao.getHistorial(idAfiliado, inicio, fin);
		
		for (Atencionmedica atencionmedica : atencionmedicas) {
			HistorialAtencionesVo historialAtencionesVo = new HistorialAtencionesVo();
			historialAtencionesVo.setFecha(FormatUtil.getDate(atencionmedica.getFechaAsistio()));
			historialAtencionesVo.setIdAtencion(atencionmedica.getAtencionMedicaId());
			historialAtencionesVo.setLugarAtencion(atencionmedica.getLugaresdeatencion().getDescripcion());
			Iterator<Atencionprestacion> atencionPrestacionIterator = atencionmedica.getAtencionprestacions().iterator();
			while (atencionPrestacionIterator.hasNext()) {
				Atencionprestacion atencionprestacion = (Atencionprestacion) atencionPrestacionIterator.next();
				Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = atencionprestacion.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
				while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
					Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
					historialAtencionesVo.setPrestacion(prestacionesaseguradorequivalencias.getPrestacionasegurador().getDescripcion());
					break;
				}
				break;
			}
			historialAtencionesVos.add(historialAtencionesVo);
		}
		
		return historialAtencionesVos;
	}
	
}
