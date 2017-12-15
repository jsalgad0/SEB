package com.mx.sab.dao;

import java.util.Collection;
import java.util.Date;

import com.mx.sab.form.CitasPresencialesForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.AfiliadoAsegurador;
import com.mx.sab.model.Afiliadopersonaconfianza;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Afiliadotipovalorasegurador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Catagendadopor;
import com.mx.sab.model.Catcausas;
import com.mx.sab.model.Catestatuscitas;
import com.mx.sab.model.Catestatusrecepcion;
import com.mx.sab.model.Catgrupossanguineos;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.ConvenioCuadroprestaciones;
import com.mx.sab.model.CuadroprestacionPrestacion;
import com.mx.sab.model.Documentosprestador;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcExploracionfisica;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.Permisoespecial;
import com.mx.sab.model.Personasdeconfianza;
import com.mx.sab.model.HcAntecedentesfamiliares;

public interface IAgendaDao {

	int getAgendaCountSinMedico(String busquedaA, Date date,String busquedaE, int idLugarAtencion);

	int getAgendaCountConMedico(int busquedaM, String busquedaA,Date date, String busquedaE, int idLugarAtencion);

	Collection<Agenda> getAgendaSinMedico(String busquedaA, Date date,String busquedaE, int inicio, int fin, int idLugarAtencion);

	Collection<Agenda> getAgendaConMedico(int busquedaM, String busquedaA, Date date, String busquedaE, int inicio, int fin, int idLugarAtencion);

	Agenda getAgendaById(int idAgenda);

	int getAfiliadoPersonasConfianzaCount(Integer afiliadoId);

	Collection<Afiliadopersonaconfianza> getAfiliadoPersonasConfianza(Integer afiliadoId);

	void savePersonaConfianza(Personasdeconfianza personasdeconfianza);

	void saveAfiliadoPersonaConfianza(Afiliadopersonaconfianza afiliadopersonaconfianza);

	Catagendadopor getCatAgendadoPor(int i);

	Catestatuscitas getCatEstatusCitas(int i);

	Personasdeconfianza getPersonaDeConfianzaById(int idPersona);

	void saveAtencionMedica(Atencionmedica atencionmedica);

	void update(Agenda agenda);

	void saveAtencionPrestacion(Atencionprestacion atencionprestacion);

	Atencionmedica getAtencionMedicaById(Integer atencionMedicaId);

	ConvenioCuadroprestaciones getConvenioCuadroPrestaciones(int idConvenio);

	CuadroprestacionPrestacion getCuadroPrestacionPrestacion(Integer cuadroPrestacionId, int idPrestacion);

	void saveAfiliado(Afiliado afiliado);

	void saveAfiliadoTipoValorAsegurador(
			Afiliadotipovalorasegurador afiliadotipovalorasegurador);

	Collection<Afiliadotipoidentificador> getAfiliadoTipoIdentificadorMediaccess(int codEmpresa, String codAfiliado,
			int correlativo);

	Afiliado getAfiliadoById(int idAfiliado);

	void saveAgenda(Agenda agenda);

	Collection<Afiliadotipoidentificador> getAfiliadoTipoIdentificadorIssste(
			int numIssste);

	int getAgendaCountConMedico(Integer usuarioId, String busquedaA,
			String busquedaE, int tx_Marca);

	Collection<Agenda> getAgendaConMedico(Integer usuarioId, String busquedaA,
			String busquedaE, int inicio, int fin, int tx_Marca);

	Collection<Afiliadotipovalorasegurador> getAfiliadoTipoValorAseguradorById(
			Integer afiliadoId);

	Collection<Afiliadotipoidentificador> getAfiliadosByIdentificador(int idIdentificador,
			String identificador);

	Collection<Afiliado> getAfiliadosByNombre(String nombre, String apellidoPaterno,
			String apellidoMaterno);

	void saveAfiliadoAsegurador(AfiliadoAsegurador afiliadoAsegurador);

	Collection<AfiliadoAsegurador> getAfiliadoAseguradoByAfiliadoId(Integer afiliadoId);

	void updateAtencionMedica(Atencionmedica atencionmedica);

	Collection<Agenda> getAgendaByCitaPresencial(
			CitasPresencialesForm citasPresencialesForm);

//	void savePermisoEspecialAfiliado(
//			Permisoespecialafiliado permisoespecialafiliado);
//
//	Permisoespecialafiliado getPermisoEspecialAfiliado(int idAfiliado, int idLugarAtencion, int idAsegurador, int vigencia, int duracion);
//
//	Collection<Permisoespecialafiliado> getPermisoEspecialAfiliados(int idAfiliado, int idLugarAtencion, int idAsegurador, int vigencia);

	Permisoespecial getPermisoEspecialAutentia(int idAfiliado, int idLugarAtencion, int idAsegurador);

	Catcausas getCatCausas(int id);

	Collection<Documentosprestador> getDocumentosPrestadorByPrestador(
			Integer prestadorId);

	Agenda existeAgenda(Agenda agenda);

	Agenda existeAgendaByConsultorio(Agenda agenda);

	Agenda existeAgendaByUsuario(Agenda agenda);

	Collection<Atencionprestacion> getAtencionPrestacionesByIdAtencion(
			Integer atencionMedicaId);

	void deleteAtencionPrestacion(Atencionprestacion atencionprestacionAux);

	void updateAfiliado(Afiliado afiliado);


	void updateAtencionPrestacion(Atencionprestacion atencionprestacion);

	void deleteAgenda(Agenda agenda);

	Collection<Agenda> getAgendaByIdAfiliado(int idAfiliado);

	Catestatusrecepcion getCatEstatusRecepcionById(int id);

	Collection<Atencionmedica> getAtencionPacientesConDoctor(
			String busquedaApellidoPaterno, String busquedaFecha, int inicio,
			int fin, Integer lugarDeAtencionId, Integer usuarioId, int busquedaidEstatus);

	Collection<Atencionmedica> getAtencionPacientesSinDoctor(
			String busquedaApellidoPaterno, String busquedaFecha, int inicio,
			int fin, Integer lugarDeAtencionId, int busquedaidEstatus);

	int getAtencionPacientesConDoctorCount(String busquedaApellidoPaterno,
			String busquedaFecha, Integer lugarDeAtencionId, Integer usuarioId,
			int busquedaidEstatus);

	int getAtencionPacientesSinDoctorCount(String busquedaApellidoPaterno,
			String busquedaFecha, Integer lugarDeAtencionId,
			int busquedaidEstatus);
	
	HcAntecedentesfamiliares getAntecedentesFamiliares(Integer historiaMedicaId);
	Historiaclinica getHistoriaClinicaByAfiliadoId(Integer idAfiliado);
	
	void saveHistoriaClinica(Historiaclinica historiaclinica);
	void saveAntecedentesFamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares);
	void updateAntecedentesFamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares);
	
	HcAntecedentespersonal getAntecedentesPersonales(Integer historiaMedicaId);
	void saveAntecedentesPersonales(HcAntecedentespersonal hcAntecedentespersonal);
	void updateAntecedentesPersonales(HcAntecedentespersonal hcAntecedentespersonal);
	
	HcGineco getGinecoObstentrico(Integer historiaMedicaId);
	void saveGinecoObstentrico(HcGineco hcGinecoObstentrico);
	void updateGinecoObstentrico(HcGineco hcGinecoObstentrico);

	HcExploracionfisica getExploracionFisica(Integer historiaMedicaId);

	void saveExploracionFisica(HcExploracionfisica hcExploracionfisica);

	void updateHcExploracionfisica(HcExploracionfisica hcExploracionfisica);

	Collection<Catmpf> getMpf();

	Catmpf getCatMpfById(int idMpf);	
	
	Notamedica getNotaMedicaByIdAtencion(int idAtencion);

	Collection<Agenda> getAgendaByUsuarioAndFecha(Integer usuarioId);

	Agenda getagendaByIdAtencion(int idAgenda);

}
