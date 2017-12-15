package com.mx.sab.vo;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class RecuperaAtencionVo implements Serializable{


private static final long serialVersionUID = 819946201500563357L;
private int idAsegurador;
private String descripcionAsegurador;
private int idPrestador;
private String descripcionPrestador;
private int idConvenio;
private String descripcionConvenio;
private int idIdentificador;
private String descripcionIdentificador;
private String idPaciente;
private boolean tipoPaciente;
private boolean asistio;
private String apellidoPaterno;	
private String apellidoMaterno;
private String nombre;
private int idAfiliado;
private int idAtencionMedica;
private String fechaDeNac;
private String telefono;
private String mail;
private Collection<AutocompleteVo> prestacionesPorTomarVo;
private int idAgenda;
}
