package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class CuadroPrestacionesVo implements Serializable{

	private static final long serialVersionUID = -2281091109861371571L;
	private Integer cuadroPrestacionId;
	private String cuadroPrestacion;
	private PrestadoresVo prestadores;
}
