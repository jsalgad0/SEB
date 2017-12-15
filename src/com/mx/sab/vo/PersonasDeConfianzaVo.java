package com.mx.sab.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonasDeConfianzaVo implements Serializable{

	private static final long serialVersionUID = -3937192093399692670L;
	private Integer personaId;
	private String nombre;
	private CatTipoIdentificadorVo cattipoidentificador;
	private String valorTipoIdentificador;
}
