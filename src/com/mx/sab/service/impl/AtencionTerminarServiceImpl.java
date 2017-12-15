package com.mx.sab.service.impl;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;
import serviceimplementation.services.sass.mediaccess._2014._08.ISvcSassc_Autorizar_DefaultFaultContractFault_FaultMessage;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ArrayOfMedicamentoCto;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ArrayOfProcedimientoCto;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ArrayOfstring;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.Autorizar;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.AutorizarResponse;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.MedicamentoCto;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ObtenerAfiliadoSiEsElegible;
import serviceimplementation.services.sass.mediaccess._2014._08.SvcSassSiStub.ProcedimientoCto;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterators;
import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAtencionSolicitudReferenciaDao;
import com.mx.sab.dao.IGenericDao;
import com.mx.sab.dao.INotasNoFirmadasDao;
import com.mx.sab.enums.AseguradoresEnum;
import com.mx.sab.enums.CatEstatusFirmaEnum;
import com.mx.sab.enums.CatEstatusUsuarioEnum;
import com.mx.sab.enums.CatTipoServicioEnum;
import com.mx.sab.enums.TipoIdentificadorEnum;
import com.mx.sab.form.AtencionOtrosDiagnosticosForm;
import com.mx.sab.form.AtencionTerminarForm;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Atencionmedica;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Permisoespecial;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesportomar;
import com.mx.sab.model.Solicitudreferencia;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.service.IAtencionEstudiosMedicosService;
import com.mx.sab.service.IAtencionOtrosDiagnosticosService;
import com.mx.sab.service.IAtencionRecetaService;
import com.mx.sab.service.IAtencionTerminarService;
import com.mx.sab.service.IMedicoLicenciaMedicaService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.DiagnosticoVo;
import com.mx.sab.vo.LicenciaMedicaVo;
import com.mx.sab.vo.RecetasVo;
import com.mx.sab.vo.UserInfo;

@Service
@Log4j2
public class AtencionTerminarServiceImpl implements IAtencionTerminarService {
	
	@Autowired
	private IAtencionOtrosDiagnosticosService atencionOtrosDiagnosticosService;
	
	@Autowired
	private IAtencionRecetaService atencionRecetaService;
	
	@Autowired
	private IAtencionEstudiosMedicosService atencionEstudiosMedicosService;
	
	@Autowired
	private IMedicoLicenciaMedicaService medicoLicenciaMedicaService;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired 
	private IAtencionSolicitudReferenciaDao atencionSolicitudReferenciaDao;
	
	@Autowired
	private INotasNoFirmadasDao notasNoFirmadasDao;
	
	@Autowired
	private IGenericDao genericDao;
	
	@Override
	public void inicializarForm(AtencionTerminarForm atencionTerminarForm, UserInfo userInfo) {
		AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm = new AtencionOtrosDiagnosticosForm();
		atencionOtrosDiagnosticosForm.setIdAtencion(atencionTerminarForm.getIdAtencion());
		Collection<DiagnosticoVo> diagnosticoVos = atencionOtrosDiagnosticosService.getTodosDiagnosticos(atencionOtrosDiagnosticosForm);
		atencionTerminarForm.setDiagnosticoVos(diagnosticoVos);
		Collection<RecetasVo> recetasVos = atencionRecetaService.getRecetas(atencionTerminarForm.getIdAtencion());
		atencionTerminarForm.setRecetasVos(recetasVos);
		Collection<AutocompleteVo> autocompleteVos = atencionEstudiosMedicosService.getEstudios(atencionTerminarForm.getIdAtencion(), CatTipoServicioEnum.GABINETE.getId());
		atencionTerminarForm.setGabineteVos(autocompleteVos);
		autocompleteVos = atencionEstudiosMedicosService.getEstudios(atencionTerminarForm.getIdAtencion(), CatTipoServicioEnum.LABORATORIO.getId());
		atencionTerminarForm.setLaboratorioVos(autocompleteVos);
		autocompleteVos = atencionEstudiosMedicosService.getEstudios(atencionTerminarForm.getIdAtencion(), CatTipoServicioEnum.OTROS.getId());
		atencionTerminarForm.setOtrosVos(autocompleteVos);
		autocompleteVos = atencionEstudiosMedicosService.getEstudios(atencionTerminarForm.getIdAtencion(), CatTipoServicioEnum.CONSULTORIO.getId());
		atencionTerminarForm.setConsultorioVos(autocompleteVos);	
		LicenciaMedicaVo licenciaMedicaVo = medicoLicenciaMedicaService.getLicenciaMedica(atencionTerminarForm.getIdAtencion());
		if (licenciaMedicaVo!=null) {
			atencionTerminarForm.setLlenadoLicenciaMedica(true);
		}
		atencionTerminarForm.setLicenciaMedicaVo(licenciaMedicaVo);
		Solicitudreferencia solicitudreferencia = atencionSolicitudReferenciaDao.getSolicitudReferenciaByIdAtencionMedica(atencionTerminarForm.getIdAtencion());
		
		atencionTerminarForm.setIdUsuario(userInfo.getUsuarios().getUsuarioId());
		atencionTerminarForm.setRfcUsuario(userInfo.getUsuarios().getRfc());
		atencionTerminarForm.setNombreUsuario(userInfo.getUsuarios().getNombre());
		atencionTerminarForm.setApellidoPaternoUsuario(userInfo.getUsuarios().getApellidoPaterno());
		atencionTerminarForm.setApellidoMaternoUsuario(userInfo.getUsuarios().getApellidoMaterno());
		atencionTerminarForm.setSexoUsuario(userInfo.getUsuarios().getCatsexos().getSexoId());
		atencionTerminarForm.setFechaNacimientoUsuario(FormatUtil.getDate(userInfo.getUsuarios().getFechaDeNacimiento()));
		atencionTerminarForm.setEstadoUsuario(userInfo.getUsuarios().getCatestados().getEstadoId());
		if(userInfo.getUsuarios().getCatestatususuario().getId() == CatEstatusUsuarioEnum.AUTORIZADO_CON_CLAVE.getId()){
			atencionTerminarForm.setUsarClave(1);	
		}
		
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(atencionTerminarForm.getIdAtencion());
		Afiliado afiliado = atencionmedica.getAfiliado();
		atencionTerminarForm.setIdAfiliadoTerminar(afiliado.getAfiliadoId());
		atencionTerminarForm.setNombreAfiliado(afiliado.getNombre());
		atencionTerminarForm.setApellidoPaternoAfiliado(afiliado.getApellidoPaterno());
		atencionTerminarForm.setApellidoMaternoAfiliado(afiliado.getApellidoMaterno());
		atencionTerminarForm.setSexoAfiliado(afiliado.getCatsexos().getSexoId());
		atencionTerminarForm.setFechaNacimientoAfiliado(FormatUtil.getDate(afiliado.getFechaDeNacimiento()));
		atencionTerminarForm.setEstadoAfiliado(afiliado.getCatestadosByEstadoId().getEstadoId());
		
		if (solicitudreferencia!=null) {
			atencionTerminarForm.setLlenadoReferencia(true);
			atencionTerminarForm.setEspecialidad(solicitudreferencia.getCatespecialidadesmedicas().getEspecialidadMedica());
		}
		
		Permisoespecial permisoespecial = agendaDao.getPermisoEspecialAutentia(afiliado.getAfiliadoId(), userInfo.getLugaresdeatencion().getLugarDeAtencionId(), atencionmedica.getAseguradores().getAseguradorId());
		if (permisoespecial!=null) {
			atencionTerminarForm.setHuellaAfiliado(0);
		}else{
			atencionTerminarForm.setHuellaAfiliado(1);
		}
		
		if(userInfo.getUsuarios().getCatestatususuario().getId() != CatEstatusUsuarioEnum.AUTORIZADO_CON_HUELLA.getId()){
			atencionTerminarForm.setHuellaMedico(0);
		}else{
			atencionTerminarForm.setHuellaMedico(1);
		}
		
		if (licenciaMedicaVo!=null) {
			atencionTerminarForm.setEsNecesarioHuellaAfiliado(1);
		}
		
		if (atencionmedica.getAseguradores().getAseguradorId() == AseguradoresEnum.MEDIACCESS.getId()) {
			try {
				SvcSassSiStub svcSassSiStub = new SvcSassSiStub();
				Autorizar autorizar = new Autorizar();
				
				autorizar.setIdSolicitud(0);
				Afiliadotipoidentificador afiliadotipoidentificador = genericDao.getAfiliadoTipoIdentificador(afiliado.getAfiliadoId(), TipoIdentificadorEnum.CODEMPRESA.getId());
				autorizar.setCodEmpresa(Integer.parseInt(afiliadotipoidentificador.getTipoIdValor()));
				afiliadotipoidentificador = genericDao.getAfiliadoTipoIdentificador(afiliado.getAfiliadoId(), TipoIdentificadorEnum.CODAFILIADO.getId());
				autorizar.setCodAfiliado(afiliadotipoidentificador.getTipoIdValor());
				afiliadotipoidentificador = genericDao.getAfiliadoTipoIdentificador(afiliado.getAfiliadoId(), TipoIdentificadorEnum.CORRELATIVO.getId());
				autorizar.setCorrelativo(Integer.parseInt(afiliadotipoidentificador.getTipoIdValor()));
				autorizar.setCodCuenta(63472);
				
				Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = userInfo.getUsuarios().getUsuarioespecialidadeses().iterator();
				Usuarioespecialidades usuarioespecialidades = null;
				while (usuarioEspecialidadesIterator.hasNext()) {
					usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
					break;
				}
				autorizar.setCedProfMedico(usuarioespecialidades.getCedulaEspecialidad());
				
				if (diagnosticoVos!=null) {
					ArrayOfstring arrayOfstring = new ArrayOfstring();
					int i = 0;
					String[] codDiagnosticoArray = new String[diagnosticoVos.size()];
					for (DiagnosticoVo diagnosticoVo : diagnosticoVos) {
						codDiagnosticoArray[i] = diagnosticoVo.getCodigo();
						i++;
					}
					arrayOfstring.setString(codDiagnosticoArray);
					autorizar.setCodDiagnosticoArray(arrayOfstring);
				}
				
				ArrayOfProcedimientoCto arrayOfProcedimientoCto = new ArrayOfProcedimientoCto();
				
				
				Iterator<Atencionprestacion> atencionPrestacion = atencionmedica.getAtencionprestacions().iterator();
				int i = 0;
				int size = 0;
				if (atencionPrestacion!=null) {
					size = Iterators.size(atencionPrestacion);	
				}
				if (atencionTerminarForm.getGabineteVos()!=null) {
					size = size + atencionTerminarForm.getGabineteVos().size();
				}
				if (atencionTerminarForm.getLaboratorioVos()!=null) {
					size = size + atencionTerminarForm.getLaboratorioVos().size();
				}
				if (atencionTerminarForm.getOtrosVos()!=null) {
					size = size + atencionTerminarForm.getOtrosVos().size();
				}
				if (atencionTerminarForm.getConsultorioVos()!=null) {
					size = size + atencionTerminarForm.getConsultorioVos().size();
				}				
				
				if (size!=0) {
					ProcedimientoCto[] procedimientoCtos = new ProcedimientoCto[size];
					atencionPrestacion = atencionmedica.getAtencionprestacions().iterator();
					while (atencionPrestacion.hasNext()) {
						procedimientoCtos[i] = new ProcedimientoCto();
						procedimientoCtos[i].setCodProcedimiento("");
						Atencionprestacion atencionprestacion = atencionPrestacion.next();
						Iterator<Prestacionesaseguradorequivalencias> prestacionAseguradorEquivalenciasIterator = atencionprestacion.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
						while (prestacionAseguradorEquivalenciasIterator.hasNext()) {
							Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionAseguradorEquivalenciasIterator.next();
							if (prestacionesaseguradorequivalencias.getPrestacionasegurador().getAseguradores().getAseguradorId() == atencionmedica.getAseguradores().getAseguradorId()) {
								procedimientoCtos[i].setCodProcedimiento(prestacionesaseguradorequivalencias.getPrestacionasegurador().getCodigo());		
							}
						}
						i++;					
					}
					
					autocompleteVos = atencionEstudiosMedicosService.getEstudiosAsegurador(atencionTerminarForm.getIdAtencion(), CatTipoServicioEnum.GABINETE.getId());
					for (AutocompleteVo autocompleteVo : autocompleteVos) {
						procedimientoCtos[i] = new ProcedimientoCto();
						procedimientoCtos[i].setCodProcedimiento(autocompleteVo.getCodigo());
						i++;
					}
					
					autocompleteVos = atencionEstudiosMedicosService.getEstudiosAsegurador(atencionTerminarForm.getIdAtencion(), CatTipoServicioEnum.LABORATORIO.getId());
					for (AutocompleteVo autocompleteVo : autocompleteVos) {
						procedimientoCtos[i] = new ProcedimientoCto();
						procedimientoCtos[i].setCodProcedimiento(autocompleteVo.getCodigo());
						i++;
					}
					
					autocompleteVos = atencionEstudiosMedicosService.getEstudiosAsegurador(atencionTerminarForm.getIdAtencion(), CatTipoServicioEnum.OTROS.getId());
					for (AutocompleteVo autocompleteVo : autocompleteVos) {
						procedimientoCtos[i] = new ProcedimientoCto();
						procedimientoCtos[i].setCodProcedimiento(autocompleteVo.getCodigo());
						i++;
					}	
					
					autocompleteVos = atencionEstudiosMedicosService.getEstudiosAsegurador(atencionTerminarForm.getIdAtencion(), CatTipoServicioEnum.CONSULTORIO.getId());
					for (AutocompleteVo autocompleteVo : autocompleteVos) {
						procedimientoCtos[i] = new ProcedimientoCto();
						procedimientoCtos[i].setCodProcedimiento(autocompleteVo.getCodigo());
						i++;
					}	

					arrayOfProcedimientoCto.setProcedimientoCto(procedimientoCtos);
					autorizar.setProcedimientos(arrayOfProcedimientoCto);
				}
				
				if (recetasVos!=null) {
					ArrayOfMedicamentoCto arrayOfMedicamentoCto = new ArrayOfMedicamentoCto();
					MedicamentoCto[] medicamentoCtos = new MedicamentoCto[recetasVos.size()];
					i = 0;
					for (RecetasVo recetasVo : recetasVos) {
						medicamentoCtos[i] = new MedicamentoCto();
						medicamentoCtos[i].setCostoBase(new BigDecimal(0));
						medicamentoCtos[i].setCopago(new BigDecimal(0));
						medicamentoCtos[i].setCodMedicamento(""+recetasVo.getIdMedicamento());
						i++;
					}
					arrayOfMedicamentoCto.setMedicamentoCto(medicamentoCtos);
					autorizar.setMedicamentos(arrayOfMedicamentoCto);
				}
				autorizar.setAutorizacionEnFirme(0);

				AutorizarResponse autorizarResponse = svcSassSiStub.autorizar(autorizar);	
				autorizarResponse.getAutorizarResult();
			} catch (AxisFault e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ISvcSassc_Autorizar_DefaultFaultContractFault_FaultMessage e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void verificarClave(AtencionTerminarForm atencionTerminarForm, UserInfo userInfo) {
		atencionTerminarForm.setIntentos(atencionTerminarForm.getIntentos()+1);
		String clave = FormatUtil.getMd5(atencionTerminarForm.getClave());
		if (clave.equals(userInfo.getUsuarios().getPsswd())) {
			atencionTerminarForm.setError("");
			atencionTerminarForm.setExito("Clave correcta");
		}else{
			atencionTerminarForm.setError("Clave incorrecta");
			atencionTerminarForm.setExito("");			
		}
	}

	@Override
	public void inicializarForm(AtencionTerminarForm atencionTerminarForm, UserInfo userInfo, AtencionTerminarForm atencionTerminarFormAux) {
		atencionTerminarForm.setDiagnosticoVos(atencionTerminarFormAux.getDiagnosticoVos());
		atencionTerminarForm.setRecetasVos(atencionTerminarFormAux.getRecetasVos());
		atencionTerminarForm.setGabineteVos(atencionTerminarFormAux.getGabineteVos());
		atencionTerminarForm.setLaboratorioVos(atencionTerminarFormAux.getLaboratorioVos());
		atencionTerminarForm.setOtrosVos(atencionTerminarFormAux.getOtrosVos());
		atencionTerminarForm.setConsultorioVos(atencionTerminarFormAux.getConsultorioVos());
		atencionTerminarForm.setLicenciaMedicaVo(atencionTerminarFormAux.getLicenciaMedicaVo());
		
		atencionTerminarForm.setIdUsuario(atencionTerminarFormAux.getIdUsuario());
		atencionTerminarForm.setRfcUsuario(atencionTerminarFormAux.getRfcUsuario());
		atencionTerminarForm.setNombreUsuario(atencionTerminarFormAux.getNombreUsuario());
		atencionTerminarForm.setApellidoPaternoUsuario(atencionTerminarFormAux.getApellidoPaternoUsuario());
		atencionTerminarForm.setApellidoMaternoUsuario(atencionTerminarFormAux.getApellidoMaternoUsuario());
		atencionTerminarForm.setSexoUsuario(atencionTerminarFormAux.getSexoUsuario());
		atencionTerminarForm.setFechaNacimientoUsuario(atencionTerminarFormAux.getFechaNacimientoUsuario());
		atencionTerminarForm.setEstadoUsuario(atencionTerminarFormAux.getEstadoUsuario());
		atencionTerminarForm.setUsarClave(atencionTerminarFormAux.getUsarClave());	
		
		atencionTerminarForm.setIdAfiliadoTerminar(atencionTerminarFormAux.getIdAfiliadoTerminar());
		atencionTerminarForm.setNombreAfiliado(atencionTerminarFormAux.getNombreAfiliado());
		atencionTerminarForm.setApellidoPaternoAfiliado(atencionTerminarFormAux.getApellidoPaternoAfiliado());
		atencionTerminarForm.setApellidoMaternoAfiliado(atencionTerminarFormAux.getApellidoMaternoAfiliado());
		atencionTerminarForm.setSexoAfiliado(atencionTerminarFormAux.getSexoAfiliado());
		atencionTerminarForm.setFechaNacimientoAfiliado(atencionTerminarFormAux.getFechaNacimientoAfiliado());
		atencionTerminarForm.setEstadoAfiliado(atencionTerminarFormAux.getEstadoAfiliado());

		atencionTerminarForm.setEspecialidad(atencionTerminarFormAux.getEspecialidad());
		
		atencionTerminarForm.setHuellaAfiliado(atencionTerminarFormAux.getHuellaAfiliado());
		atencionTerminarForm.setHuellaMedico(atencionTerminarFormAux.getHuellaMedico());	
		atencionTerminarForm.setEsNecesarioHuellaAfiliado(atencionTerminarFormAux.getEsNecesarioHuellaAfiliado());
		
		Atencionmedica atencionmedica = agendaDao.getAtencionMedicaById(atencionTerminarForm.getIdAtencion());
		if(atencionTerminarForm.getAutorizacionMedico() == 0){
			atencionmedica.setCatestatusfirmaByEstatusFirmaMedico(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_AUTORIZADA.getId()));	
		}else if (atencionTerminarForm.getAutorizacionMedico() == 1) {
			atencionmedica.setCatestatusfirmaByEstatusFirmaMedico(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_PENDIENTE.getId()));
		}else if (atencionTerminarForm.getAutorizacionMedico() == 2) {
			atencionmedica.setCatestatusfirmaByEstatusFirmaMedico(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_AUTORIZADA.getId()));
		}else if (atencionTerminarForm.getAutorizacionMedico() == 3) {
			atencionmedica.setCatestatusfirmaByEstatusFirmaMedico(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_PENDIENTE.getId()));
		}
		
		if(atencionTerminarForm.getAutorizacionAfiliado() == 0){
			atencionmedica.setCatestatusfirmaByEstatusFirmaPaciente(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_AUTORIZADA.getId()));	
		}else if (atencionTerminarForm.getAutorizacionAfiliado() == 1) {
			atencionmedica.setCatestatusfirmaByEstatusFirmaPaciente(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_AUTORIZADA.getId()));
		}else if (atencionTerminarForm.getAutorizacionAfiliado() == 2) {
			atencionmedica.setCatestatusfirmaByEstatusFirmaPaciente(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_PENDIENTE.getId()));
		}else if (atencionTerminarForm.getAutorizacionAfiliado() == 3) {
			atencionmedica.setCatestatusfirmaByEstatusFirmaPaciente(notasNoFirmadasDao.getEstatusFirma(CatEstatusFirmaEnum.IDENTIDAD_CON_AUTORIZACION_ESPECIAL.getId()));
		}		
		
		agendaDao.updateAtencionMedica(atencionmedica);
		
		Autorizar autorizar = new Autorizar();
	}

}
