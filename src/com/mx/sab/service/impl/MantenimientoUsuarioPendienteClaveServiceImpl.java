package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMantenimientoUsuarioPendienteClaveService;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class MantenimientoUsuarioPendienteClaveServiceImpl implements IMantenimientoUsuarioPendienteClaveService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Override
	public Collection<Usuarios> getUsuarios(UserInfo userInfo) {
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		Collection<Usuariolugaratencion> usuariolugaratencions = usuarioDao.getUsuariosPendienteClave(lugaresdeatencion.getLugarDeAtencionId());
		Collection<Usuarios> usuarios = new ArrayList<>();
		for (Usuariolugaratencion usuariolugaratencion : usuariolugaratencions) {
			usuarios.add(usuariolugaratencion.getUsuarios());
		}
		return usuarios;
	}

}
