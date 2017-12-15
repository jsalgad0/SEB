package com.mx.sab.dao.impl;
 
import java.util.Collection;
import java.util.Date;

import lombok.extern.log4j.Log4j2;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sab.dao.IAgendaDao;
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
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcExploracionfisica;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.Historiaclinica;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.Permisoespecial;
import com.mx.sab.model.Personasdeconfianza;
import com.mx.sab.util.FormatUtil;

@Log4j2
@Transactional
@Service
public class AgendaDaoImpl implements IAgendaDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static String GET_COUNT_AGENDA_SIN_MEDICO = "SELECT count(a.agendaId) FROM Agenda a WHERE a.afiliado.apellidoPaterno like ? AND a.fechaCita = ? AND a.asistio like ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.afiliado.activo = 1";
	private static String GET_COUNT_AGENDA_CON_MEDICO = "SELECT count(a.agendaId) FROM Agenda a WHERE a.usuarios.usuarioId = ? AND a.afiliado.apellidoPaterno like ? AND a.fechaCita = ? AND a.asistio like ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.afiliado.activo = 1";
	private static String GET_AGENDA_SIN_MEDICO = "FROM Agenda a WHERE a.afiliado.apellidoPaterno like ? AND a.fechaCita = ? AND a.asistio like ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.afiliado.activo = 1";
	private static String GET_AGENDA_CON_MEDICO = "FROM Agenda a WHERE a.usuarios.usuarioId = ? AND a.afiliado.apellidoPaterno like ? AND a.fechaCita = ? AND a.asistio like ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.afiliado.activo = 1";
	private static String GET_AGENDA_BY_ID = "FROM Agenda WHERE agendaId = ?";
	private static String GET_COUNT_AFILIADO_PERSONAS_CONFIANZA = "SELECT count(a.esAcompaniante) FROM Afiliadopersonaconfianza a WHERE a.afiliado.afiliadoId = ? AND a.personasdeconfianza.activo = 1";
	private static String GET_AFILIADO_PERSONAS_CONFIANZA = "FROM Afiliadopersonaconfianza a WHERE a.afiliado.afiliadoId = ? AND a.personasdeconfianza.activo = 1";
	private static String GET_PERSONA_CONFIANZA_BY_ID = "FROM Personasdeconfianza WHERE personaId = ?";
	private static String GET_AGENDADO_POR_BY_ID = "FROM Catagendadopor WHERE agendadoPorId = ?";
	private static String GET_ESTATUS_CITAS_BY_ID = "FROM Catestatuscitas WHERE estatusCitaId = ?";
	private static String GET_ATENCION_MEDICA_BY_ID = "FROM Atencionmedica WHERE atencionMedicaId = ?";
	private static String GET_CONVENIO_CUADRO_PRESTACIONES = "FROM ConvenioCuadroprestaciones WHERE convenios.convenioId = ?";
	private static String GET_CUADRO_PRESTACION_PRESTACION = "FROM CuadroprestacionPrestacion WHERE cuadroprestaciones.cuadroPrestacionId = ? AND catprestacion.prestacionId = ?";
	private static String GET_AFILIADO_TIPO_IDENTIFICADOR_MEDIACCESS = "FROM Afiliadotipoidentificador WHERE (cattipoidentificador.tipoIdentificadorId = 9 AND tipoIdValor = ?) OR (cattipoidentificador.tipoIdentificadorId = 8 AND tipoIdValor = ?) OR (cattipoidentificador.tipoIdentificadorId = 7 AND tipoIdValor = ?) order by afiliado.afiliadoId";
	private static String GET_AFILIADO_TIPO_IDENTIFICADOR_ISSSTE = "FROM Afiliadotipoidentificador WHERE (cattipoidentificador.tipoIdentificadorId = 4 AND tipoIdValor = ?) order by afiliado.afiliadoId";
	private static String GET_AFILIADO_BY_ID = "FROM Afiliado WHERE afiliadoId = ?";
	
	private static String GET_LISTA_PACIENTES_CON_MEDICO = "FROM Agenda a WHERE a.usuarios.usuarioId = ? AND a.afiliado.apellidoPaterno like ? AND a.asistio like ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.atencionmedica.atencionMedicaId IS NOT NULL AND a.afiliado.activo = 1";
	private static String GET_COUNT_LISTA_PACIENTE_CON_MEDICO = "SELECT count(a.agendaId) FROM Agenda a WHERE a.usuarios.usuarioId = ? AND a.afiliado.apellidoPaterno like ? AND a.asistio like ? AND a.lugaresdeatencion.codigoLugarAtencion = ? AND a.atencionmedica.atencionMedicaId IS NOT NULL AND a.afiliado.activo = 1";
	
	private static String GET_AFILIADO_TIPO_VALOR_ASEGURADOR = "FROM Afiliadotipovalorasegurador WHERE afiliadoId = ?";
	
	private static String GET_AFILIADO_BY_IDENTIFICADOR = "FROM Afiliadotipoidentificador WHERE cattipoidentificador.tipoIdentificadorId = ? AND tipoIdValor = ?";
	private static String GET_AFILIADO_BY_NOMBRE = "FROM Afiliado WHERE nombre like ? AND apellidoPaterno like ? AND apellidoMaterno like ?";
	
	private static String GET_AFILIADO_ASEGURADOR_BY_AFILIADO_ID = "FROM AfiliadoAsegurador WHERE afiliado.afiliadoId = ?";
	
	private static String GET_AGENDA_BY_CITA_PRESENCIAL = "FROM Agenda a WHERE a.afiliado.afiliadoId = ? AND a.lugaresdeatencion.lugarDeAtencionId = ? AND a.aseguradores.aseguradorId = ? AND a.prestadores.prestadorId = ? AND a.fechaCita = ? AND a.atencionmedica.atencionMedicaId IS NULL";
	private static String GET_PERMISO_ESPECIAL_AFILIADO = "FROM Permisoespecialafiliado WHERE afiliado.afiliadoId = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND aseguradores.aseguradorId = ? AND tipoAutorizacion = ? AND duracion = ?";
	private static String GET_PERMISO_ESPECIAL_AFILIADOS = "FROM Permisoespecialafiliado WHERE afiliado.afiliadoId = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND aseguradores.aseguradorId = ? AND tipoAutorizacion = ? AND fechaInicio = ?";
	private static String GET_PERMISO_ESPECIAL_AUTENTIA = "FROM Permisoespecial WHERE afiliado.afiliadoId = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND aseguradores.aseguradorId = ? AND tipoAutorizacion = 2 AND fechaInicio <= ? AND fechaFin >= ?";
	private static String GET_CAT_CAUSAS = "FROM Catcausas WHERE causaId = ?";
	
	private static String GET_DOCUMENTOS_PRESTADOR_BY_PRESTADOR = "FROM Documentosprestador WHERE prestadores.prestadorId = ?";
	private static String GET_AGENDA_DUPLICADA = "FROM Agenda WHERE afiliado.afiliadoId = ? AND aseguradores.aseguradorId = ? AND fechaCita = ? AND horaCita = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND prestadores.prestadorId = ?";
	private static String GET_AGENDA_DUPLICADA_BY_CONSULTORIO = "FROM Agenda WHERE consultorio = ? AND fechaCita = ? AND horaCita = ?";
	private static String GET_AGENDA_DUPLICADA_BY_USUARIO = "FROM Agenda WHERE usuarios.usuarioId = ? AND fechaCita = ? AND horaCita = ?";
	private static String GET_ATENCION_PRESTACION_BY_ID_ATENCION = "FROM Atencionprestacion WHERE atencionmedica.atencionMedicaId = ?";
	
	private static String GET_AGENDAS_BY_ID_AFILIADO = "FROM Agenda WHERE afiliado.afiliadoId = ? AND fechaCita = ?";
	private static String GET_CAT_ESTATUS_RECEPCION_BY_ID = "FROM Catestatusrecepcion WHERE id = ?";
	
	private static String GET_COUNT_ATENCION_PACIENTE_CON_MEDICO = "SELECT COUNT(*) FROM atencionmedica ate, afiliado afi WHERE afi.AfiliadoId = ate.AfiliadoId AND afi.ApellidoPaterno LIKE ? AND ate.FechaAsistio = ? AND ate.LugarDeAtencionId = ? AND ate.UsuarioMedicoId = ? AND ate.TipoAtencionMedicaId <> 3";
	private static String GET_COUNT_ATENCION_PACIENTE_CON_MEDICO_ATENDIDOS = "SELECT COUNT(*) FROM atencionmedica ate, afiliado afi WHERE afi.AfiliadoId = ate.AfiliadoId AND afi.ApellidoPaterno LIKE ? AND ate.FechaAsistio = ? AND ate.LugarDeAtencionId = ? AND ate.UsuarioMedicoId = ? AND ate.FechaAtendidoMedico IS NOT NULL AND ate.TipoAtencionMedicaId <> 3";
	private static String GET_COUNT_ATENCION_PACIENTE_CON_MEDICO_SIN_ATENDER = "SELECT COUNT(*) FROM atencionmedica ate, afiliado afi WHERE afi.AfiliadoId = ate.AfiliadoId AND afi.ApellidoPaterno LIKE ? AND ate.FechaAsistio = ? AND ate.LugarDeAtencionId = ? AND ate.UsuarioMedicoId = ? AND ate.FechaAtendidoMedico IS NULL AND ate.TipoAtencionMedicaId <> 3";
	private static String GET_COUNT_ATENCION_PACIENTE_SIN_MEDICO = "SELECT COUNT(*) FROM atencionmedica ate, afiliado afi WHERE afi.AfiliadoId = ate.AfiliadoId AND afi.ApellidoPaterno LIKE ? AND ate.FechaAsistio = ? AND ate.LugarDeAtencionId = ? AND ate.UsuarioMedicoId IS NULL";
	private static String GET_COUNT_ATENCION_PACIENTE_SIN_MEDICO_ATENDIDOS = "SELECT COUNT(*) FROM atencionmedica ate, afiliado afi WHERE afi.AfiliadoId = ate.AfiliadoId AND afi.ApellidoPaterno LIKE ? AND ate.FechaAsistio = ? AND ate.LugarDeAtencionId = ? AND ate.UsuarioMedicoId IS NULL AND ate.FechaAtendidoMedico IS NOT NULL";
	private static String GET_COUNT_ATENCION_PACIENTE_SIN_MEDICO_SIN_ATENDER = "SELECT COUNT(*) FROM atencionmedica ate, afiliado afi WHERE afi.AfiliadoId = ate.AfiliadoId AND afi.ApellidoPaterno LIKE ? AND ate.FechaAsistio = ? AND ate.LugarDeAtencionId = ? AND ate.UsuarioMedicoId IS NULL AND ate.FechaAtendidoMedico IS NULL";
	private static String GET_ATENCION_PACIENTE_CON_MEDICO = "FROM Atencionmedica WHERE afiliado.apellidoPaterno LIKE ? AND fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND usuariosByUsuarioMedicoId.usuarioId = ? AND tipoatencionmedica.tipoAtencionMedicaId <> 3";
	private static String GET_ATENCION_PACIENTE_CON_MEDICO_ATENDIDOS = "FROM Atencionmedica WHERE afiliado.apellidoPaterno LIKE ? AND fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND usuariosByUsuarioMedicoId.usuarioId = ? AND fechaAtendidoMedico IS NOT NULL AND tipoatencionmedica.tipoAtencionMedicaId <> 3";
	private static String GET_ATENCION_PACIENTE_CON_MEDICO_SIN_ATENDER = "FROM Atencionmedica WHERE afiliado.apellidoPaterno LIKE ? AND fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND usuariosByUsuarioMedicoId.usuarioId = ? AND fechaAtendidoMedico IS NULL AND tipoatencionmedica.tipoAtencionMedicaId <> 3";
	private static String GET_ATENCION_PACIENTE_SIN_MEDICO = "FROM Atencionmedica WHERE afiliado.apellidoPaterno LIKE ? AND fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND usuariosByUsuarioMedicoId.usuarioId IS NULL";
	private static String GET_ATENCION_PACIENTE_SIN_MEDICO_ATENDIDOS = "FROM Atencionmedica WHERE afiliado.apellidoPaterno LIKE ? AND fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND usuariosByUsuarioMedicoId.usuarioId IS NULL AND fechaAtendidoMedico IS NOT NULL";
	private static String GET_ATENCION_PACIENTE_SIN_MEDICO_SIN_ATENDER = "FROM Atencionmedica WHERE afiliado.apellidoPaterno LIKE ? AND fechaAsistio = ? AND lugaresdeatencion.lugarDeAtencionId = ? AND usuariosByUsuarioMedicoId.usuarioId IS NULL AND fechaAtendidoMedico IS NULL";
	
	private static String GET_ANTECEDENTES_PERSONALESS_BY_ID_HISTORIA_CLINICA = "FROM HcAntecedentespersonal WHERE historiaclinica.historiaClinicaId = ?";
	private static String GET_GINECO_BY_ID_HISTORIA_CLINICA = "FROM HcGineco WHERE historiaclinica.historiaClinicaId = ?";
	private static String GET_ANTECEDENTES_FAMILIARES_BY_ID_HISTORIA_CLINICA = "FROM HcAntecedentesfamiliares WHERE historiaclinica.historiaClinicaId = ?";
	private static String GET_HISTORIA_CLINICA_BY_AFILIADO_ID = "FROM Historiaclinica WHERE afiliado.afiliadoId = ?";
	
	private static String GET_CAT_MPF = "FROM Catmpf";
	private static String GET_CAT_MPF_BY_ID = "FROM Catmpf where mpfId = ?";
	
	private static String GET_EXPLORACION_FISICA_BY_ID_HISTORIA_CLINICA = "FROM HcExploracionfisica WHERE historiaclinica.historiaClinicaId = ?";
	private static String GET_NOTA_MEDICA_BY_ID_ATENCION = "FROM Notamedica WHERE atencionmedica.atencionMedicaId = ?";
	
	private static String GET_AGENDA_BY_ID_USUARIO_AND_FECHA = "FROM Agenda WHERE usuarios.usuarioId = ? AND fechaCita = ?";
	private static String GET_AGENDA_BY_ID_ATENCION = "FROM Agenda WHERE atencionmedica.atencionMedicaId = ?";

	
	public Session getSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		if (currentSession == null) {
			currentSession = sessionFactory.openSession();
			return currentSession;
		}
		return currentSession;
	}
	
	@Override
	public int getAgendaCountSinMedico(String busquedaA, Date busquedaF, String busquedaE, int idLugarAtencion) {
		//log.info("getAgendaCountSinMedico");
		Query query = getSession().createQuery(GET_COUNT_AGENDA_SIN_MEDICO);
		query.setString(0, "%"+busquedaA.trim()+"%");
		query.setDate(1, busquedaF);
		query.setString(2, "%"+busquedaE.trim()+"%");
		query.setInteger(3, idLugarAtencion);
		return ((Number) query.uniqueResult()).intValue(); 
	}

	@Override
	public int getAgendaCountConMedico(int busquedaM, String busquedaA, Date busquedaF, String busquedaE, int idLugarAtencion) {
		//log.info("getAgendaCountConMedico");
		Query query = getSession().createQuery(GET_COUNT_AGENDA_CON_MEDICO);
		query.setInteger(0, busquedaM);
		query.setString(1, "%"+busquedaA.trim()+"%");
		query.setDate(2, busquedaF);
		query.setString(3, "%"+busquedaE.trim()+"%");
		query.setInteger(4, idLugarAtencion);
		return ((Number) query.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Agenda> getAgendaSinMedico(String busquedaA, Date busquedaF, String busquedaE, int inicio, int fin, int idLugarAtencion) {
		//log.info("getAgendaSinMedico");
		Query query = getSession().createQuery(GET_AGENDA_SIN_MEDICO);
		query.setString(0, "%"+busquedaA.trim()+"%");
		query.setDate(1, busquedaF);
		query.setString(2, "%"+busquedaE.trim()+"%");
		query.setInteger(3, idLugarAtencion);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		Collection<Agenda> agendas = query.list();
		return agendas;
	}

	@Override
	public Collection<Agenda> getAgendaConMedico(int busquedaM, String busquedaA, Date busquedaF, String busquedaE, int inicio, int fin, int idLugarAtencion) {
		//log.info("getAgendaConMedico");
		Query query = getSession().createQuery(GET_AGENDA_CON_MEDICO);
		query.setInteger(0, busquedaM);
		query.setString(1, "%"+busquedaA.trim()+"%");
		query.setDate(2, busquedaF);
		query.setString(3, "%"+busquedaE.trim()+"%");
		query.setInteger(4, idLugarAtencion);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		Collection<Agenda> agendas = query.list();
		return agendas;
	}

	@Override
	public Agenda getAgendaById(int idAgenda) {
		//log.info("getAgendaConMedico");
		Query query = getSession().createQuery(GET_AGENDA_BY_ID);
		query.setInteger(0, idAgenda);
		return (Agenda) query.uniqueResult();
	}

	@Override
	public int getAfiliadoPersonasConfianzaCount(Integer afiliadoId) {
		//log.info("getAfiliadoPersonasConfianzaCount");
		Query query = getSession().createQuery(GET_COUNT_AFILIADO_PERSONAS_CONFIANZA);
		query.setInteger(0, afiliadoId);
		return ((Number) query.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Afiliadopersonaconfianza> getAfiliadoPersonasConfianza(Integer afiliadoId) {
		//log.info("getAfiliadoPersonasConfianza");
		Query query = getSession().createQuery(GET_AFILIADO_PERSONAS_CONFIANZA);
		query.setInteger(0, afiliadoId);
		Collection<Afiliadopersonaconfianza> afiliadopersonaconfianzas = query.list();
		return afiliadopersonaconfianzas;
	}

	@Override
	public void savePersonaConfianza(Personasdeconfianza personasdeconfianza) {
		//log.info("savePersonaConfianza");
		getSession().save(personasdeconfianza);
		getSession().refresh(personasdeconfianza);
	}

	@Override
	public void saveAfiliadoPersonaConfianza(Afiliadopersonaconfianza afiliadopersonaconfianza) {
		//log.info("saveAfiliadoPersonaConfianza");
		getSession().save(afiliadopersonaconfianza);		
		
	}

	@Override
	public Catagendadopor getCatAgendadoPor(int id) {
		//log.info("getCatAgendadoPor");
		Query query = getSession().createQuery(GET_AGENDADO_POR_BY_ID);
		query.setInteger(0, id);
		Catagendadopor catagendadopor = (Catagendadopor) query.uniqueResult();
		return catagendadopor;
	}

	@Override
	public Catestatuscitas getCatEstatusCitas(int id) {
		//log.info("getCatEstatusCitas");
		Query query = getSession().createQuery(GET_ESTATUS_CITAS_BY_ID);
		query.setInteger(0, id);
		Catestatuscitas catestatuscitas = (Catestatuscitas) query.uniqueResult();
		return catestatuscitas;
	}

	@Override
	public Personasdeconfianza getPersonaDeConfianzaById(int idPersona) {
		//log.info("getPersonaDeConfianzaById");
		Query query = getSession().createQuery(GET_PERSONA_CONFIANZA_BY_ID);
		query.setInteger(0, idPersona);
		Personasdeconfianza personasdeconfianza = (Personasdeconfianza) query.uniqueResult();
		return personasdeconfianza;
	}

	@Override
	public void saveAtencionMedica(Atencionmedica atencionmedica) {
		//log.info("saveAtencionMedica");
		getSession().save(atencionmedica);	
		getSession().refresh(atencionmedica);
	}

	@Override
	public void update(Agenda agenda) {
		//log.info("update");
		getSession().merge(agenda);
		
	}

	@Override
	public void saveAtencionPrestacion(Atencionprestacion atencionprestacion) {
		//log.info("saveAtencionPrestacion");
		getSession().save(atencionprestacion);
	}

	@Override
	public Atencionmedica getAtencionMedicaById(Integer atencionMedicaId) {
		//log.info("getPersonaDeConfianzaById");
		Query query = getSession().createQuery(GET_ATENCION_MEDICA_BY_ID);
		query.setInteger(0, atencionMedicaId);
		Atencionmedica atencionmedica = (Atencionmedica) query.uniqueResult();
		return atencionmedica;
	}

	@Override
	public ConvenioCuadroprestaciones getConvenioCuadroPrestaciones(int idConvenio) {
		//log.info("getPersonaDeConfianzaById");
		Query query = getSession().createQuery(GET_CONVENIO_CUADRO_PRESTACIONES);
		query.setInteger(0, idConvenio);
		ConvenioCuadroprestaciones convenioCuadroprestaciones = (ConvenioCuadroprestaciones) query.uniqueResult();
		return convenioCuadroprestaciones;		
	}

	@Override
	public CuadroprestacionPrestacion getCuadroPrestacionPrestacion(Integer cuadroPrestacionId, int idPrestacion) {
		//log.info("getCuadroPrestacionPrestacion");
		Query query = getSession().createQuery(GET_CUADRO_PRESTACION_PRESTACION);
		query.setInteger(0, cuadroPrestacionId);
		query.setInteger(1, idPrestacion);
		CuadroprestacionPrestacion cuadroprestacionPrestacion = (CuadroprestacionPrestacion) query.uniqueResult();
		return cuadroprestacionPrestacion;	
	}

	@Override
	public void saveAfiliado(Afiliado afiliado) {
		//log.info("saveAfiliado");
		getSession().save(afiliado);
		getSession().refresh(afiliado);
	}

	@Override
	public void saveAfiliadoTipoValorAsegurador(Afiliadotipovalorasegurador afiliadotipovalorasegurador) {
		//log.info("saveAfiliadoTipoValorAsegurador");
		getSession().save(afiliadotipovalorasegurador);
		
	}

	@Override
	public Collection<Afiliadotipoidentificador> getAfiliadoTipoIdentificadorMediaccess(int codEmpresa, String codAfiliado, int correlativo) {
		//log.info("getAfiliadoMediaccess");
		Query query = getSession().createQuery(GET_AFILIADO_TIPO_IDENTIFICADOR_MEDIACCESS);
		query.setInteger(0, correlativo);
		query.setString(1, codAfiliado);
		query.setInteger(2, codEmpresa);
		Collection<Afiliadotipoidentificador> afiliadotipoidentificador = query.list();
		return afiliadotipoidentificador;
	}

	@Override
	public Afiliado getAfiliadoById(int idAfiliado) {
		//log.info("getAfiliadoById");
		Query query = getSession().createQuery(GET_AFILIADO_BY_ID);
		query.setInteger(0, idAfiliado);
		Afiliado afiliado = (Afiliado) query.uniqueResult();
		return afiliado;
	}

	@Override
	public void saveAgenda(Agenda agenda) {
		//log.info("saveAgenda");
		getSession().save(agenda);
		getSession().refresh(agenda);
	}

	@Override
	public Collection<Afiliadotipoidentificador> getAfiliadoTipoIdentificadorIssste(int numIssste) {
		//log.info("getAfiliadoTipoIdentificadorIssste");
		Query query = getSession().createQuery(GET_AFILIADO_TIPO_IDENTIFICADOR_ISSSTE);
		query.setInteger(0, numIssste);
		Collection<Afiliadotipoidentificador> afiliadotipoidentificador = query.list();
		return afiliadotipoidentificador;
	}

	@Override
	public int getAgendaCountConMedico(Integer usuarioId, String busquedaA, String busquedaE, int idLugarAtencion) {
		//log.info("getAgendaCountConMedico");
		Query query = getSession().createQuery(GET_COUNT_LISTA_PACIENTE_CON_MEDICO);
		query.setInteger(0, usuarioId);
		query.setString(1, "%"+busquedaA.trim()+"%");
		query.setString(2, "%"+busquedaE.trim()+"%");
		query.setInteger(3, idLugarAtencion);
		return ((Number) query.uniqueResult()).intValue(); 
	}

	@Override
	public Collection<Agenda> getAgendaConMedico(Integer usuarioId, String busquedaA, String busquedaE, int inicio, int fin, int idLugarAtencion) {
		//log.info("getAgendaConMedico");
		Query query = getSession().createQuery(GET_LISTA_PACIENTES_CON_MEDICO);
		query.setInteger(0, usuarioId);
		query.setString(1, "%"+busquedaA.trim()+"%");
		query.setString(2, "%"+busquedaE.trim()+"%");
		query.setInteger(3, idLugarAtencion);
		query.setFirstResult(inicio);
		query.setMaxResults(fin);
		Collection<Agenda> agendas = query.list();
		return agendas;
	}

	@Override
	public Collection<Afiliadotipovalorasegurador> getAfiliadoTipoValorAseguradorById(Integer afiliadoId) {
		//log.info("getAfiliadoTipoValorAseguradorById");
		Query query = getSession().createQuery(GET_AFILIADO_TIPO_VALOR_ASEGURADOR);
		query.setInteger(0, afiliadoId);
		return query.list(); 
	}

	@Override
	public Collection<Afiliadotipoidentificador> getAfiliadosByIdentificador(int idIdentificador, String identificador) {
		//log.info("getAfiliadosByIdentificador");
		Query query = getSession().createQuery(GET_AFILIADO_BY_IDENTIFICADOR);
		query.setInteger(0, idIdentificador);
		query.setString(1, identificador);
		return query.list(); 
	}

	@Override
	public Collection<Afiliado> getAfiliadosByNombre(String nombre, String apellidoPaterno, String apellidoMaterno) {
		//log.info("getAfiliadosByIdentificador");
		Query query = getSession().createQuery(GET_AFILIADO_BY_NOMBRE);
		query.setString(0, "%"+nombre+"%");
		query.setString(1, "%"+apellidoPaterno+"%");
		query.setString(2, "%"+apellidoMaterno+"%");
		return query.list(); 
	}

	@Override
	public void saveAfiliadoAsegurador(AfiliadoAsegurador afiliadoAsegurador) {
		//log.info("saveAfiliadoAsegurador");
		getSession().save(afiliadoAsegurador);
	}

	@Override
	public Collection<AfiliadoAsegurador> getAfiliadoAseguradoByAfiliadoId(Integer afiliadoId) {
		//log.info("getAfiliadosByIdentificador");
		Query query = getSession().createQuery(GET_AFILIADO_ASEGURADOR_BY_AFILIADO_ID);
		query.setInteger(0, afiliadoId);
		return query.list(); 
	}

	@Override
	public void updateAtencionMedica(Atencionmedica atencionmedica) {
		//log.info("updateAtencionMedica");
		getSession().update(atencionmedica);
	}
	
	@Override
	public void updateAtencionPrestacion(Atencionprestacion atencionprestacion) {
		//log.info("updateAtencionPrestacion");
		getSession().update(atencionprestacion);
	}

	@Override
	public Collection<Agenda> getAgendaByCitaPresencial(CitasPresencialesForm citasPresencialesForm) {
		//log.info("getAgendaByCitaPresencial");
		Query query = getSession().createQuery(GET_AGENDA_BY_CITA_PRESENCIAL);
		query.setInteger(0, citasPresencialesForm.getIdAfiliado());
		query.setInteger(1, citasPresencialesForm.getIdLugarAtencion());
		query.setInteger(2, citasPresencialesForm.getIdAsegurador());
		query.setInteger(3, citasPresencialesForm.getIdPrestador());
		query.setDate(4, new Date());
		return query.list(); 
	}

//	@Override
//	public void savePermisoEspecialAfiliado(Permisoespecialafiliado permisoespecialafiliado) {
//		//log.info("savePermisoEspecialAfiliado");
//		getSession().save(permisoespecialafiliado);
//	}
//
//	@Override
//	public Permisoespecialafiliado getPermisoEspecialAfiliado(int idAfiliado, int idLugarAtencion, int idAsegurador, int vigencia, int duracion) {
//		//log.info("getPermisoEspecialAfiliado");
//		Query query = getSession().createQuery(GET_PERMISO_ESPECIAL_AFILIADO);
//		query.setInteger(0, idAfiliado);
//		query.setInteger(1, idLugarAtencion);
//		query.setInteger(2, idAsegurador);
//		query.setInteger(3, vigencia);
//		query.setInteger(4, duracion);
//		return (Permisoespecialafiliado) query.uniqueResult(); 
//	}
//
//	@Override
//	public Collection<Permisoespecialafiliado> getPermisoEspecialAfiliados(int idAfiliado, int idLugarAtencion, int idAsegurador, int vigencia) {
//		//log.info("getPermisoEspecialAfiliados");
//		Query query = getSession().createQuery(GET_PERMISO_ESPECIAL_AFILIADOS);
//		query.setInteger(0, idAfiliado);
//		query.setInteger(1, idLugarAtencion);
//		query.setInteger(2, idAsegurador);
//		query.setInteger(3, vigencia);
//		query.setDate(4, new Date());
//		return query.list();
//	}
//
	@Override
	public Permisoespecial getPermisoEspecialAutentia(int idAfiliado, int idLugarAtencion, int idAsegurador) {
		//log.info("getPermisoEspecialAutentia");
		Query query = getSession().createQuery(GET_PERMISO_ESPECIAL_AUTENTIA);
		query.setInteger(0, idAfiliado);
		query.setInteger(1, idLugarAtencion);
		query.setInteger(2, idAsegurador);
		query.setDate(3, new Date());
		query.setDate(4, new Date());
		return (Permisoespecial) query.uniqueResult();
	}

	@Override
	public Catcausas getCatCausas(int id) {
		//log.info("getCatCausas");
		Query query = getSession().createQuery(GET_CAT_CAUSAS);
		query.setInteger(0, id);
		return (Catcausas) query.uniqueResult();
	}

	@Override
	public Collection<Documentosprestador> getDocumentosPrestadorByPrestador(Integer prestadorId) {
		//log.info("getDocumentosPrestadorByPrestador");
		Query query = getSession().createQuery(GET_DOCUMENTOS_PRESTADOR_BY_PRESTADOR);
		query.setInteger(0, prestadorId);
		return query.list();
	}

	@Override
	public Agenda existeAgenda(Agenda agenda) {	
		//log.info("existeAgenda");
		Query query = getSession().createQuery(GET_AGENDA_DUPLICADA);
		query.setInteger(0, agenda.getAfiliado().getAfiliadoId());
		query.setInteger(1, agenda.getAseguradores().getAseguradorId());
		query.setDate(2, agenda.getFechaCita());
		query.setTime(3, agenda.getHoraCita());
		query.setInteger(4, agenda.getLugaresdeatencion().getLugarDeAtencionId());
		query.setInteger(5, agenda.getPrestadores().getPrestadorId());
		return (Agenda) query.uniqueResult();
	}

	@Override
	public Agenda existeAgendaByConsultorio(Agenda agenda) {
		//log.info("existeAgendaByConsultorio");
		Query query = getSession().createQuery(GET_AGENDA_DUPLICADA_BY_CONSULTORIO);
		query.setString(0, agenda.getConsultorio());
		query.setDate(1, agenda.getFechaCita());
		query.setTime(2, agenda.getHoraCita());
		return (Agenda) query.uniqueResult();
	}

	@Override
	public Agenda existeAgendaByUsuario(Agenda agenda) {
		//log.info("existeAgendaByUsuario");
		Query query = getSession().createQuery(GET_AGENDA_DUPLICADA_BY_USUARIO);
		query.setInteger(0, agenda.getUsuarios().getUsuarioId());
		query.setDate(1, agenda.getFechaCita());
		query.setTime(2, agenda.getHoraCita());
		return (Agenda) query.uniqueResult();
	}

	@Override
	public Collection<Atencionprestacion> getAtencionPrestacionesByIdAtencion(Integer atencionMedicaId) {
		//log.info("getAtencionPrestacionesByIdAtencion");
		Query query = getSession().createQuery(GET_ATENCION_PRESTACION_BY_ID_ATENCION);
		query.setInteger(0, atencionMedicaId);
		return query.list();
	}

	@Override
	public void deleteAtencionPrestacion(Atencionprestacion atencionprestacionAux) {
		//log.info("deleteAtencionPrestacion");
		getSession().delete(atencionprestacionAux);
	}

	@Override
	public void updateAfiliado(Afiliado afiliado) {
		//log.info("updateAfiliado");
		getSession().update(afiliado);
	}

	@Override
	public Collection<Agenda> getAgendaByIdAfiliado(int idAfiliado) {
		//log.info("getAgendaByIdAfiliado");
		Query query = getSession().createQuery(GET_AGENDAS_BY_ID_AFILIADO);
		query.setInteger(0, idAfiliado);
		query.setDate(1, new Date());
		return query.list();
	}

	@Override
	public Catestatusrecepcion getCatEstatusRecepcionById(int id) {
		//log.info("getCatEstatusRecepcionById");
		Query query = getSession().createQuery(GET_CAT_ESTATUS_RECEPCION_BY_ID);
		query.setInteger(0, id);
		return (Catestatusrecepcion) query.uniqueResult();
	}


	@Override
	public void deleteAgenda(Agenda agenda) {
		//log.info("delete Agenda");
		getSession().delete(agenda);
	}

	@Override
	public Collection<Atencionmedica> getAtencionPacientesConDoctor(String busquedaApellidoPaterno, String busquedaFecha, int inicio, int fin, Integer lugarDeAtencionId, Integer usuarioId, int idEstatus) {
		//log.info("getAtencionPacientesConDoctor");
		Query query = null;
		if (idEstatus==-1) {
			query = getSession().createQuery(GET_ATENCION_PACIENTE_CON_MEDICO);
		}else if (idEstatus==1) {
			query = getSession().createQuery(GET_ATENCION_PACIENTE_CON_MEDICO_ATENDIDOS);
		}else {
			query = getSession().createQuery(GET_ATENCION_PACIENTE_CON_MEDICO_SIN_ATENDER);
		}
		query.setString(0, "%"+busquedaApellidoPaterno.trim()+"%");
		query.setDate(1, FormatUtil.getDate(busquedaFecha));
		query.setInteger(2, lugarDeAtencionId);
		query.setInteger(3, usuarioId);
		query.setFirstResult(inicio);
		query.setMaxResults(fin); 
		return query.list();
	}

	@Override
	public Collection<Atencionmedica> getAtencionPacientesSinDoctor(String busquedaApellidoPaterno, String busquedaFecha, int inicio, int fin, Integer lugarDeAtencionId, int idEstatus) {
		//log.info("getAtencionPacientesConDoctor");
		Query query = null;
		if (idEstatus==-1) {
			query = getSession().createQuery(GET_ATENCION_PACIENTE_SIN_MEDICO);
		}else if (idEstatus==1) {
			query = getSession().createQuery(GET_ATENCION_PACIENTE_SIN_MEDICO_ATENDIDOS);
		}else {
			query = getSession().createQuery(GET_ATENCION_PACIENTE_SIN_MEDICO_SIN_ATENDER);
		}		
		query.setString(0, "%"+busquedaApellidoPaterno.trim()+"%");
		query.setDate(1, FormatUtil.getDate(busquedaFecha));
		query.setInteger(2, lugarDeAtencionId);
		query.setFirstResult(inicio);
		query.setMaxResults(fin); 
		return query.list();
	}

	@Override
	public int getAtencionPacientesConDoctorCount(String busquedaApellidoPaterno, String busquedaFecha,Integer lugarDeAtencionId, Integer usuarioId, int busquedaidEstatus) {
		//log.info("getAtencionPacientesConDoctorCount");
		SQLQuery query = null;
		if (busquedaidEstatus==-1) {
			query = getSession().createSQLQuery(GET_COUNT_ATENCION_PACIENTE_CON_MEDICO);	
		}else if (busquedaidEstatus==1) {
			query = getSession().createSQLQuery(GET_COUNT_ATENCION_PACIENTE_CON_MEDICO_ATENDIDOS);
		}else {
			query = getSession().createSQLQuery(GET_COUNT_ATENCION_PACIENTE_CON_MEDICO_SIN_ATENDER);
		}
		
		query.setString(0, "%"+busquedaApellidoPaterno.trim()+"%");
		query.setDate(1, FormatUtil.getDate(busquedaFecha));
		query.setInteger(2, lugarDeAtencionId);
		query.setInteger(3, usuarioId);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public int getAtencionPacientesSinDoctorCount(String busquedaApellidoPaterno, String busquedaFecha,Integer lugarDeAtencionId, int busquedaidEstatus) {
		//log.info("getAtencionPacientesConDoctorCount");
		SQLQuery query = null;
		if (busquedaidEstatus==-1) {
			query = getSession().createSQLQuery(GET_COUNT_ATENCION_PACIENTE_SIN_MEDICO);	
		}else if (busquedaidEstatus==1) {
			query = getSession().createSQLQuery(GET_COUNT_ATENCION_PACIENTE_SIN_MEDICO_ATENDIDOS);
		}else {
			query = getSession().createSQLQuery(GET_COUNT_ATENCION_PACIENTE_SIN_MEDICO_SIN_ATENDER);
		}
		query.setString(0, "%"+busquedaApellidoPaterno.trim()+"%");
		query.setDate(1, FormatUtil.getDate(busquedaFecha));
		query.setInteger(2, lugarDeAtencionId);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public HcAntecedentesfamiliares getAntecedentesFamiliares(Integer historiaMedicaId) {
		//log.info("getHcAntecedentesFamilares");
		Query query = getSession().createQuery(GET_ANTECEDENTES_FAMILIARES_BY_ID_HISTORIA_CLINICA);
		query.setInteger(0, historiaMedicaId);
		return (HcAntecedentesfamiliares) query.uniqueResult();
		
	}
	
	@Override
	public HcExploracionfisica getExploracionFisica(Integer historiaMedicaId) {
		//log.info("getHcExploracionFisica");
		Query query = getSession().createQuery(GET_EXPLORACION_FISICA_BY_ID_HISTORIA_CLINICA);
		query.setInteger(0, historiaMedicaId);
		return (HcExploracionfisica) query.uniqueResult();
		
	}
	
	@Override
	public void saveExploracionFisica(HcExploracionfisica hcExploracionfisica) {
		//log.info("saveHcExploracionfisica");
		getSession().save(hcExploracionfisica);
		getSession().refresh(hcExploracionfisica);
	}

	@Override
	public void updateHcExploracionfisica(HcExploracionfisica hcExploracionfisica) {
		//log.info("updateHcExploracionfisica");
		getSession().merge(hcExploracionfisica);
	}
	
	@Override
	public Historiaclinica getHistoriaClinicaByAfiliadoId(Integer idAfiliado) {
		//log.info("getHistoriaClinica");
		Query query = getSession().createQuery(GET_HISTORIA_CLINICA_BY_AFILIADO_ID);
		query.setInteger(0, idAfiliado);
		return (Historiaclinica) query.uniqueResult();
		
	}

	@Override
	public void saveHistoriaClinica(Historiaclinica historiaclinica) {
		//log.info("saveHistoriaClinica");
		getSession().save(historiaclinica);
		getSession().refresh(historiaclinica);
	}

	@Override
	public void saveAntecedentesFamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares) {
		//log.info("saveAntecedentesFamiliares");
		getSession().save(hcAntecedentesfamiliares);
		getSession().refresh(hcAntecedentesfamiliares);
	}

	@Override
	public void updateAntecedentesFamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares) {
		//log.info("updateAntecedentesFamiliares");
		getSession().merge(hcAntecedentesfamiliares);
	}
	
	@Override
	public HcAntecedentespersonal getAntecedentesPersonales(Integer historiaMedicaId) {
		//log.info("getHcAntecedentesPersonales");
		Query query = getSession().createQuery(GET_ANTECEDENTES_PERSONALESS_BY_ID_HISTORIA_CLINICA);
		query.setInteger(0, historiaMedicaId);
		return (HcAntecedentespersonal) query.uniqueResult();
		
	}

	@Override
	public void saveAntecedentesPersonales(HcAntecedentespersonal hcAntecedentespersonal) {
		//log.info("saveAntecedentesPersonales");
		getSession().save(hcAntecedentespersonal);
		getSession().refresh(hcAntecedentespersonal);
	}

	@Override
	public void updateAntecedentesPersonales(HcAntecedentespersonal hcAntecedentespersonal) {
		//log.info("updateAntecedentesPersonales");
		getSession().merge(hcAntecedentespersonal);
	}
	
	@Override
	public HcGineco getGinecoObstentrico(Integer historiaMedicaId) {
		//log.info("getHcGineco");
		Query query = getSession().createQuery(GET_GINECO_BY_ID_HISTORIA_CLINICA);
		query.setInteger(0, historiaMedicaId);
		return (HcGineco) query.uniqueResult();
		
	}

	@Override
	public void saveGinecoObstentrico(HcGineco hcGineco) {
		//log.info("saveGineco");
		getSession().save(hcGineco);
		getSession().refresh(hcGineco);
	}

	@Override
	public void updateGinecoObstentrico(HcGineco hcGineco) {
		//log.info("updateGineco");
		getSession().merge(hcGineco);
	}

	@Override
	public Collection<Catmpf>getMpf() {
		//log.info("getMpf");
		Query query = getSession().createQuery(GET_CAT_MPF);
		return query.list();
	}

	@Override
	public Catmpf getCatMpfById(int idMpf) {
		//log.info("getCatMpfById");
		Query query = getSession().createQuery(GET_CAT_MPF_BY_ID);
		query.setInteger(0, idMpf);
		return (Catmpf) query.uniqueResult();
	}

	@Override
	public Notamedica getNotaMedicaByIdAtencion(int idAtencion) {
		//log.info("getNotaMedicaByIdAtencion");
		Query query = getSession().createQuery(GET_NOTA_MEDICA_BY_ID_ATENCION);
		query.setInteger(0, idAtencion);
		return (Notamedica) query.uniqueResult();
	}

	@Override
	public Collection<Agenda> getAgendaByUsuarioAndFecha(Integer usuarioId) {
		//log.info("getAgendaByUsuarioAndFecha");
		Query query = getSession().createQuery(GET_AGENDA_BY_ID_USUARIO_AND_FECHA);
		query.setInteger(0, usuarioId);
		query.setDate(1, new Date());
		return query.list();
	}

	@Override
	public Agenda getagendaByIdAtencion(int idAgenda) {
		//log.info("getagendaByIdAtencion");
		Query query = getSession().createQuery(GET_AGENDA_BY_ID_ATENCION);
		query.setInteger(0, idAgenda);
		return (Agenda) query.uniqueResult();
	}


}
