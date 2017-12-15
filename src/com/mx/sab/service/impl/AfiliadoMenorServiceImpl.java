package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IListaMenoresDao;
import com.mx.sab.model.Afiliado;
import com.mx.sab.service.IAfiliadoMenorService;
import com.mx.sab.vo.AfiliadoMenorVo;

@Service
@Log4j2
public class AfiliadoMenorServiceImpl implements IAfiliadoMenorService {

	@Autowired
	private IListaMenoresDao listaMenoresDao;
	
	@Override	
	public Collection<AfiliadoMenorVo> getAfiliadosMenor() {
		//log.info("coleccion de menores");
		Collection<AfiliadoMenorVo> afiliadoMenorVos = new ArrayList<AfiliadoMenorVo>();
		Collection<Afiliado> afiliados = listaMenoresDao.getAfiliados();
		for(Afiliado renglon : afiliados){
			AfiliadoMenorVo afiliadoMenorVo = new AfiliadoMenorVo();
			afiliadoMenorVo.setAfiliadoId(renglon.getAfiliadoId());
			afiliadoMenorVo.setNombre(renglon.getNombre());
			afiliadoMenorVo.setApellidoPaterno(renglon.getApellidoPaterno());
			afiliadoMenorVo.setApellidoMaterno(renglon.getApellidoMaterno());
			afiliadoMenorVo.setFechaDeNacimiento(renglon.getFechaDeNacimiento());
			afiliadoMenorVos.add(afiliadoMenorVo);
		}
		return afiliadoMenorVos;
	}

}
