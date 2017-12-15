package com.mx.sab.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import lombok.Data;

import com.mx.sab.vo.AtencionMedicaAuxVo;
import com.mx.sab.vo.AutocompleteVo;

@Data
public class RecepcionBuscarAtencionForm extends ParentForm implements Serializable{
	
	private static final long serialVersionUID = 230066230307119210L;
	
	private int idAtencionMedica;
	private String telefono;
	private String mail;	
	private int idMedico;
	private String medico;
	private String cantidad;
	private boolean validarMedico;
	private String ordenServicio;
	private AutocompleteVo autocompleteVo;
	private List<AutocompleteVo> autocompleteVos;
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
	private String fechaDeNac;
	private Collection<AutocompleteVo> prestacionesPorTomarVo;
	private List<AtencionMedicaAuxVo> atenciones;
	private int idAgenda;
	private String idTiempo;
	private String folio;
	private int tipoBusqueda;
	private boolean personaConfianza;
	private String nombreMedico;
	private String fechaAtencion;
	public int getIdAtencionMedica() {
		return idAtencionMedica;
	}
	public void setIdAtencionMedica(int idAtencionMedica) {
		this.idAtencionMedica = idAtencionMedica;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
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
	public int getIdAsegurador() {
		return idAsegurador;
	}
	public void setIdAsegurador(int idAsegurador) {
		this.idAsegurador = idAsegurador;
	}
	public String getDescripcionAsegurador() {
		return descripcionAsegurador;
	}
	public void setDescripcionAsegurador(String descripcionAsegurador) {
		this.descripcionAsegurador = descripcionAsegurador;
	}
	public int getIdPrestador() {
		return idPrestador;
	}
	public void setIdPrestador(int idPrestador) {
		this.idPrestador = idPrestador;
	}
	public String getDescripcionPrestador() {
		return descripcionPrestador;
	}
	public void setDescripcionPrestador(String descripcionPrestador) {
		this.descripcionPrestador = descripcionPrestador;
	}
	public int getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}
	public String getDescripcionConvenio() {
		return descripcionConvenio;
	}
	public void setDescripcionConvenio(String descripcionConvenio) {
		this.descripcionConvenio = descripcionConvenio;
	}
	public int getIdIdentificador() {
		return idIdentificador;
	}
	public void setIdIdentificador(int idIdentificador) {
		this.idIdentificador = idIdentificador;
	}
	public String getDescripcionIdentificador() {
		return descripcionIdentificador;
	}
	public void setDescripcionIdentificador(String descripcionIdentificador) {
		this.descripcionIdentificador = descripcionIdentificador;
	}
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public boolean isTipoPaciente() {
		return tipoPaciente;
	}
	public void setTipoPaciente(boolean tipoPaciente) {
		this.tipoPaciente = tipoPaciente;
	}
	public boolean isAsistio() {
		return asistio;
	}
	public void setAsistio(boolean asistio) {
		this.asistio = asistio;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdAfiliado() {
		return idAfiliado;
	}
	public void setIdAfiliado(int idAfiliado) {
		this.idAfiliado = idAfiliado;
	}
	public String getFechaDeNac() {
		return fechaDeNac;
	}
	public void setFechaDeNac(String fechaDeNac) {
		this.fechaDeNac = fechaDeNac;
	}
	public Collection<AutocompleteVo> getPrestacionesPorTomarVo() {
		return prestacionesPorTomarVo;
	}
	public void setPrestacionesPorTomarVo(Collection<AutocompleteVo> prestacionesPorTomarVo) {
		this.prestacionesPorTomarVo = prestacionesPorTomarVo;
	}
	public List<AtencionMedicaAuxVo> getAtenciones() {
		return atenciones;
	}
	public void setAtenciones(List<AtencionMedicaAuxVo> atenciones) {
		this.atenciones = atenciones;
	}
	public int getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}
	public String getIdTiempo() {
		return idTiempo;
	}
	public void setIdTiempo(String idTiempo) {
		this.idTiempo = idTiempo;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public int getTipoBusqueda() {
		return tipoBusqueda;
	}
	public void setTipoBusqueda(int tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	public boolean isPersonaConfianza() {
		return personaConfianza;
	}
	public void setPersonaConfianza(boolean personaConfianza) {
		this.personaConfianza = personaConfianza;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}
	public String getFechaAtencion() {
		return fechaAtencion;
	}
	public void setFechaAtencion(String fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	
	
	
}
