package com.mx.sab.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.IMantenimientoUsuarioDeclaracionPendienteService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

@Service
@Log4j2
public class MantenimientoUsuarioDeclaracionPendienteServiceImpl implements IMantenimientoUsuarioDeclaracionPendienteService {
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public Collection<UsuariosVo> getUsuarios(UserInfo userInfo) {
		Lugaresdeatencion lugaresdeatencion = lugarAtencionDao.getLugarAtencionByClave(""+userInfo.getTx_Marca());
		Collection<Usuariolugaratencion> usuariolugaratencions = usuarioDao.getUsuariosDeclaracionPendiente(lugaresdeatencion.getLugarDeAtencionId());
		Collection<UsuariosVo> usuariosVos = new ArrayList<>();
		for (Usuariolugaratencion usuariolugaratencion : usuariolugaratencions) {
			UsuariosVo usuariosVo = new UsuariosVo();
			usuariosVo.setUsuarioId(usuariolugaratencion.getUsuarios().getUsuarioId());
			usuariosVo.setApellidoMaterno(usuariolugaratencion.getUsuarios().getApellidoMaterno());
			usuariosVo.setApellidoPaterno(usuariolugaratencion.getUsuarios().getApellidoPaterno());
			usuariosVo.setFechaDeNacimiento(FormatUtil.getDate(usuariolugaratencion.getUsuarios().getFechaDeNacimiento()));
			usuariosVo.setNombre(usuariolugaratencion.getUsuarios().getNombre());
			usuariosVo.setRfc(usuariolugaratencion.getUsuarios().getRfc());
			usuariosVo.setSexo(usuariolugaratencion.getUsuarios().getCatsexos().getSexoId());
			usuariosVo.setIdEstado(usuariolugaratencion.getUsuarios().getCatestados().getEstadoId());
			usuariosVos.add(usuariosVo);
		}
		return usuariosVos;
	}

	@Override
	public Usuarios getUsuario(int idUsuario) {
		Usuarios usuarios = new Usuarios();
		if (idUsuario!=0) {
			usuarios = usuarioDao.getUsuarioById(idUsuario);	
		}
		return usuarios;
	}

	
}
