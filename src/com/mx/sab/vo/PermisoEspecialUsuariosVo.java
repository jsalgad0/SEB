package com.mx.sab.vo;

import java.util.Date;

import lombok.Data;

import com.mx.sab.model.Catcausas;
import com.mx.sab.model.Cattipoautorizacion;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Usuarios;

@Data
public class PermisoEspecialUsuariosVo {

	private Catcausas catcausas;
	private Cattipoautorizacion cattipoautorizacion;
	private Lugaresdeatencion lugaresdeatencion;
	private Usuarios usuariosByUsuarioSolicitaId;
	private Usuarios usuariosByUsuarioAutorizaId;
	private Usuarios usuariosByUsuarioId;
	private Integer duracion;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaAutorizacion;
}
