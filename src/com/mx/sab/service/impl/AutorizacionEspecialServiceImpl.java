package com.mx.sab.service.impl;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAutorizacionEspecialDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.form.AutorizacionEspecialForm;
import com.mx.sab.service.IAutorizacionEspecialService;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class AutorizacionEspecialServiceImpl implements IAutorizacionEspecialService {
	
	@Autowired
	private IAutorizacionEspecialDao autorizacionEspecialDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Override
	public void inicializaAutorizacionEspecialForm(AutorizacionEspecialForm autorizacionEspecialForm, UserInfo userInfo) {
		//log.info("autorizacion especial");
//		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByCodigo(userInfo.getTx_Marca());
//		Collection<Permisoespecialafiliado> permisoespecialafiliados = autorizacionEspecialDao.getPermisosEspeciales(lugaresdeatencion.getLugarDeAtencionId());
//		Collection<Catcausas> catcausas = autorizacionEspecialDao.getCatCausas();
//		autorizacionEspecialForm.setPermisoespecialafiliados(permisoespecialafiliados);
//		autorizacionEspecialForm.setCatcausas(catcausas);
	}

	@Override
	public void autorizarPermisoEspecialVigencia(AutorizacionEspecialForm autorizacionEspecialForm, UserInfo userInfo) {
//		Permisoespecialafiliado permisoespecialafiliado = autorizacionEspecialDao.getPermisoEspecial(autorizacionEspecialForm.getIdPermisoEspecial());
//		permisoespecialafiliado.setUsuarioAutorizaId(userInfo.getUsuarios().getUsuarioId());
//		permisoespecialafiliado.setFechaFin(new Date());
//		permisoespecialafiliado.setCatcausas(autorizacionEspecialDao.getCatCausa(CatCausasEnum.VIGENCIA.getId()));
//		permisoespecialafiliado.setDuracion(CatTipoDuracionEnum.TEMPORAL.getId());
//		autorizacionEspecialDao.update(permisoespecialafiliado);
	}

	@Override
	public void autorizarPermisoEspecialHuella(AutorizacionEspecialForm autorizacionEspecialForm, UserInfo userInfo) {
//		Permisoespecialafiliado permisoespecialafiliado = autorizacionEspecialDao.getPermisoEspecial(autorizacionEspecialForm.getIdPermisoEspecial());
//		permisoespecialafiliado.setUsuarioAutorizaId(userInfo.getUsuarios().getUsuarioId());
//		if (autorizacionEspecialForm.getIdTipoVigencia() == CatTipoDuracionEnum.TEMPORAL.getId()) {
//			permisoespecialafiliado.setFechaFin(FormatUtil.getDate(autorizacionEspecialForm.getFechaFin()));
//			permisoespecialafiliado.setDuracion(CatTipoDuracionEnum.TEMPORAL.getId());
//		}else{
//			permisoespecialafiliado.setFechaFin(new Date());
//			permisoespecialafiliado.setDuracion(CatTipoDuracionEnum.PERMANENTE.getId());
//		}
//		permisoespecialafiliado.setCatcausas(autorizacionEspecialDao.getCatCausa(autorizacionEspecialForm.getIdCatCausa()));
//		autorizacionEspecialDao.update(permisoespecialafiliado);
	}

}
