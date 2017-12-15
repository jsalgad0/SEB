package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import lombok.Data;

import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Aseguradores;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Convenios;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.vo.AfiliadoIsssteVo;
import com.mx.sab.vo.AfiliadoSinAseguradorVo;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.PrestacionesPorTomarVo;
import com.mx.sab.ws.vo.AfiliadoCtoVo;

@Data
public class CitasPresencialesForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 230066230307119210L;
	private Collection<Aseguradores> aseguradores;
	private int idAsegurador;
	private int idPrestador;
	private int idIdentificador;
	private int idLugarAtencion;
	private int idAgenda;
	private String folio;
	private int idAtencion;
	private int idAfiliado;
	private int idPersona;
	private int idMedico;
	private String idTiempo;
	private int idConvenio;
	private boolean envio;
	
	//MEDIACCESS
	private Collection<AfiliadoCtoVo> afiliadoCtoVos;
	private int codEmpresa;
	private String codProducto;
	private String codAfiliado;
	private int correlativo;
	private AutocompleteVo autocompleteVo;
	private List<AutocompleteVo> autocompleteVos;
	private String rfc;
	private String fechaDeNacimiento;
	private int sexo;
	private int estado;
	
	//ISSSTE
	private Collection<AfiliadoIsssteVo> afiliadoIsssteVos;
	private int numIssste;
	private boolean personaConfianza;
	
	//SIN ASEGURADOR
	private Collection<AfiliadoSinAseguradorVo> afiliadoSinAseguradorVos;
	private int idAfiliadoSinAsegurador;
	
	private Agenda agenda;
	private Afiliadotipoidentificador afiliadotipoidentificador;
	private Collection<Convenios> convenios;
	
	private String tipoAfiliado;
	private String sexoAfiliado;
	private Catprestacion catPrestacion;
	private Prestacionprestador prestacionprestador;
	
	private String dato;
	private String tipoDato;

	private boolean menorDeEdad;
	
	private boolean vigencia;
	private boolean autentia;
	private int autorizacionEspecial;
	private int mayorEdad;
	
	private int mostrarBotonPacienteNoEnLista;
	
	private int idSexo;
	private int idEstado;
	private String medico;
	private String cantidad;
	private boolean validarMedico;
	
	private String ordenServicio;
	private Collection<PrestacionesPorTomarVo> prestacionesPorTomarVos;
	public Collection<Aseguradores> getAseguradores() {
		return aseguradores;
	}
	public void setAseguradores(Collection<Aseguradores> aseguradores) {
		this.aseguradores = aseguradores;
	}
	public int getIdAsegurador() {
		return idAsegurador;
	}
	public void setIdAsegurador(int idAsegurador) {
		this.idAsegurador = idAsegurador;
	}
	public int getIdPrestador() {
		return idPrestador;
	}
	public void setIdPrestador(int idPrestador) {
		this.idPrestador = idPrestador;
	}
	public int getIdIdentificador() {
		return idIdentificador;
	}
	public void setIdIdentificador(int idIdentificador) {
		this.idIdentificador = idIdentificador;
	}
	public int getIdLugarAtencion() {
		return idLugarAtencion;
	}
	public void setIdLugarAtencion(int idLugarAtencion) {
		this.idLugarAtencion = idLugarAtencion;
	}
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public int getIdAtencion() {
		return idAtencion;
	}
	public void setIdAtencion(int idAtencion) {
		this.idAtencion = idAtencion;
	}
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public String getIdTiempo() {
		return idTiempo;
	}
	public void setIdTiempo(String idTiempo) {
		this.idTiempo = idTiempo;
	}
	public int getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}
	public boolean isEnvio() {
		return envio;
	}
	public void setEnvio(boolean envio) {
		this.envio = envio;
	}
	public Collection<AfiliadoCtoVo> getAfiliadoCtoVos() {
		return afiliadoCtoVos;
	}
	public void setAfiliadoCtoVos(Collection<AfiliadoCtoVo> afiliadoCtoVos) {
		this.afiliadoCtoVos = afiliadoCtoVos;
	}
	public int getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(int codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getCodAfiliado() {
		return codAfiliado;
	}
	public void setCodAfiliado(String codAfiliado) {
		this.codAfiliado = codAfiliado;
	}
	public int getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}
	public AutocompleteVo getAutocompleteVo() {
		return autocompleteVo;
	}
	public void setAutocompleteVo(AutocompleteVo autocompleteVo) {
		this.autocompleteVo = autocompleteVo;
	}
	public List<AutocompleteVo> getAutocompleteVos() {
		return autocompleteVos;
	}
	public void setAutocompleteVos(List<AutocompleteVo> autocompleteVos) {
		this.autocompleteVos = autocompleteVos;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Collection<AfiliadoIsssteVo> getAfiliadoIsssteVos() {
		return afiliadoIsssteVos;
	}
	public void setAfiliadoIsssteVos(Collection<AfiliadoIsssteVo> afiliadoIsssteVos) {
		this.afiliadoIsssteVos = afiliadoIsssteVos;
	}
	public int getNumIssste() {
		return numIssste;
	}
	public void setNumIssste(int numIssste) {
		this.numIssste = numIssste;
	}
	public boolean isPersonaConfianza() {
		return personaConfianza;
	}
	public void setPersonaConfianza(boolean personaConfianza) {
		this.personaConfianza = personaConfianza;
	}
	public Collection<AfiliadoSinAseguradorVo> getAfiliadoSinAseguradorVos() {
		return afiliadoSinAseguradorVos;
	}
	public void setAfiliadoSinAseguradorVos(Collection<AfiliadoSinAseguradorVo> afiliadoSinAseguradorVos) {
		this.afiliadoSinAseguradorVos = afiliadoSinAseguradorVos;
	}
	public int getIdAfiliadoSinAsegurador() {
		return idAfiliadoSinAsegurador;
	}
	public void setIdAfiliadoSinAsegurador(int idAfiliadoSinAsegurador) {
		this.idAfiliadoSinAsegurador = idAfiliadoSinAsegurador;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	public Afiliadotipoidentificador getAfiliadotipoidentificador() {
		return afiliadotipoidentificador;
	}
	public void setAfiliadotipoidentificador(Afiliadotipoidentificador afiliadotipoidentificador) {
		this.afiliadotipoidentificador = afiliadotipoidentificador;
	}
	public Collection<Convenios> getConvenios() {
		return convenios;
	}
	public void setConvenios(Collection<Convenios> convenios) {
		this.convenios = convenios;
	}
	public String getTipoAfiliado() {
		return tipoAfiliado;
	}
	public void setTipoAfiliado(String tipoAfiliado) {
		this.tipoAfiliado = tipoAfiliado;
	}
	public String getSexoAfiliado() {
		return sexoAfiliado;
	}
	public void setSexoAfiliado(String sexoAfiliado) {
		this.sexoAfiliado = sexoAfiliado;
	}
	public Catprestacion getCatPrestacion() {
		return catPrestacion;
	}
	public void setCatPrestacion(Catprestacion catPrestacion) {
		this.catPrestacion = catPrestacion;
	}
	public Prestacionprestador getPrestacionprestador() {
		return prestacionprestador;
	}
	public void setPrestacionprestador(Prestacionprestador prestacionprestador) {
		this.prestacionprestador = prestacionprestador;
	}
	public String getDato() {
		return dato;
	}
	public void setDato(String dato) {
		this.dato = dato;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	public boolean isMenorDeEdad() {
		return menorDeEdad;
	}
	public void setMenorDeEdad(boolean menorDeEdad) {
		this.menorDeEdad = menorDeEdad;
	}
	public boolean isVigencia() {
		return vigencia;
	}
	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}
	public boolean isAutentia() {
		return autentia;
	}
	public void setAutentia(boolean autentia) {
		this.autentia = autentia;
	}
	public int getAutorizacionEspecial() {
		return autorizacionEspecial;
	}
	public void setAutorizacionEspecial(int autorizacionEspecial) {
		this.autorizacionEspecial = autorizacionEspecial;
	}
	public int getMayorEdad() {
		return mayorEdad;
	}
	public void setMayorEdad(int mayorEdad) {
		this.mayorEdad = mayorEdad;
	}
	public int getMostrarBotonPacienteNoEnLista() {
		return mostrarBotonPacienteNoEnLista;
	}
	public void setMostrarBotonPacienteNoEnLista(int mostrarBotonPacienteNoEnLista) {
		this.mostrarBotonPacienteNoEnLista = mostrarBotonPacienteNoEnLista;
	}
	public int getIdSexo() {
		return idSexo;
	}
	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public boolean isValidarMedico() {
		return validarMedico;
	}
	public void setValidarMedico(boolean validarMedico) {
		this.validarMedico = validarMedico;
	}
	public String getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(String ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	public Collection<PrestacionesPorTomarVo> getPrestacionesPorTomarVos() {
		return prestacionesPorTomarVos;
	}
	public void setPrestacionesPorTomarVos(Collection<PrestacionesPorTomarVo> prestacionesPorTomarVos) {
		this.prestacionesPorTomarVos = prestacionesPorTomarVos;
	}
	
	
	
	

}
