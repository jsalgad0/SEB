package com.mx.sab.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.ICuadroPrestacionesDao;
import com.mx.sab.dao.IPrestadoresDao;
import com.mx.sab.form.ConveniosForm;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.CuadroprestacionPrestacionId;
import com.mx.sab.model.Cuadroprestaciones;
import com.mx.sab.service.ICuadroPrestacionesService;

@Service
@Log4j2
public class CuadroPrestacionesServiceImpl implements ICuadroPrestacionesService {
	
	@Autowired
	private IPrestadoresDao prestadoresDao;
	
	@Autowired
	private ICuadroPrestacionesDao cuadroPrestacionesDao;

	@Override
	public void cuadroPrestacionesPrestador(ConveniosForm conveniosForm) throws IOException {
//		Collection<CuadroprestacionPrestacion> cuadroprestacionPrestacions = new ArrayList<>();
//		HSSFWorkbook workbook = new HSSFWorkbook(conveniosForm.getFile().getInputStream());
//		HSSFSheet worksheet = workbook.getSheetAt(0);
//		Iterator<Row> iterator = worksheet.rowIterator();
//		int index = 0;
//		CuadroprestacionPrestacion cuadroprestacionPrestacion = null;
//		while (iterator.hasNext()) {
//			Row row = (Row) iterator.next();
//			if (index!=0) {
//				cuadroprestacionPrestacion = new CuadroprestacionPrestacion();
//				cuadroprestacionPrestacion.setCuadroprestaciones(conveniosForm.getCuadroprestaciones());
//				HSSFCell cell = (HSSFCell) row.getCell(0);
//				if (cell!=null) {
//					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//					Catprestacion catprestacion = cuadroPrestacionesDao.getPrestacionSabById((int)cell.getNumericCellValue());
//					cuadroprestacionPrestacion.setCatprestacion(catprestacion);
//					cuadroprestacionPrestacion.setActivo(1);
//				}			
//				cuadroprestacionPrestacions.add(cuadroprestacionPrestacion);
//			}
//			index++;
//		}
//		conveniosForm.setCuadroprestacionPrestacions(cuadroprestacionPrestacions);
	}

	@Override
	public Collection<Cuadroprestaciones> getCuadroPrestaciones() {
		Collection<Cuadroprestaciones> cuadroprestaciones = cuadroPrestacionesDao.getCuadroPrestaciones();
		return cuadroprestaciones;
	}

	@Override
	public Collection<CuadroprestacionPrestacion> generaCuadroPrestacionPrestacion(Integer idCuadroPrestaciones, Collection<Integer> prestacionesAsegurador,Collection<Integer> prestacionesPrestador) {
		Collection<CuadroprestacionPrestacion> cuadroprestacionPrestacions = new ArrayList<>();
		CuadroprestacionPrestacion cuadroprestacionPrestacion = null;
		CuadroprestacionPrestacionId cuadroprestacionPrestacionId = null;
		for (Integer integer : prestacionesPrestador) {
			if(prestacionesAsegurador.contains(integer)){
				cuadroprestacionPrestacion = new CuadroprestacionPrestacion();
				cuadroprestacionPrestacion.setActivo(1);
				cuadroprestacionPrestacionId = new CuadroprestacionPrestacionId();
				cuadroprestacionPrestacionId.setCuadroPrestacionId(idCuadroPrestaciones);
				cuadroprestacionPrestacionId.setPrestacionId(integer);
				cuadroprestacionPrestacion.setId(cuadroprestacionPrestacionId);
				cuadroprestacionPrestacions.add(cuadroprestacionPrestacion);
			}
		}
		return cuadroprestacionPrestacions;
	}

}
