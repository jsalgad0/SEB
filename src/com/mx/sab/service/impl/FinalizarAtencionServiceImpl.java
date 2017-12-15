package com.mx.sab.service.impl;

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IAuditoriaDao;
import com.mx.sab.dao.IContrareferenciaDao;
import com.mx.sab.dao.IEstudiosMedicosDao;
import com.mx.sab.dao.ILicenciaMedicaDao;
import com.mx.sab.dao.IListaPacientesDao;
import com.mx.sab.dao.IRecetaMedicaDao;
import com.mx.sab.dao.IAtencionSolicitudReferenciaDao;
import com.mx.sab.enums.CatEstadosEnum;
import com.mx.sab.enums.TipoIdentificadorEnum;
import com.mx.sab.form.FinalizarAtencionForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Contrareferencia;
import com.mx.sab.model.Documentosprestador;
import com.mx.sab.model.Licenciamedica;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.Prestacionesportomar;
import com.mx.sab.model.Recetas;
import com.mx.sab.model.Solicitudreferencia;
import com.mx.sab.model.Usuariotipoidentificador;
import com.mx.sab.service.ICertificadosService;
import com.mx.sab.service.IFinalizarAtencionService;
import com.mx.sab.util.FormatUtil;

@Service
@Log4j2
public class FinalizarAtencionServiceImpl implements IFinalizarAtencionService {
	
	@Autowired
	private IListaPacientesDao listaPacientesDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private IRecetaMedicaDao recetaMedicaDao;
	
	@Autowired
	private IEstudiosMedicosDao estudiosMedicosDao;
	
	@Autowired
	private ILicenciaMedicaDao licenciaMedicaDao;
	
	@Autowired
	private IAtencionSolicitudReferenciaDao solicitudReferenciaDao;
	
	@Autowired
	private IContrareferenciaDao contrareferenciaDao;
	
	@Autowired
	private IAuditoriaDao auditoriaDao;
	
	@Autowired
	private ICertificadosService certificadosService;
	
	@Override
	public void inicializarForm(FinalizarAtencionForm finalizarAtencionForm) {
		try{
			Agenda agenda = agendaDao.getAgendaById(finalizarAtencionForm.getIdAgenda());
			Notamedica notamedica = listaPacientesDao.getNotaMedicaByAtencionMedicaId(agenda.getAtencionmedica().getAtencionMedicaId());
			Collection<NotamedicaCies10> notamedicaCies10s = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedica(notamedica.getNotaMedicaId());
			String diagnosticos = "";
			String[] diagnostico = new String[notamedicaCies10s.size()];
			int i = 0;
			for (NotamedicaCies10 notamedicaCies10 : notamedicaCies10s) {
				diagnostico[i] = notamedicaCies10.getCatcies10().getCodigo();
				diagnosticos = diagnosticos + notamedicaCies10.getCatcies10().getDescripcion().trim() + ",";
				i++;
			}
			finalizarAtencionForm.setDiagnosticos(diagnosticos);
			
			Collection<Atencionprestacion> atencionprestacions = listaPacientesDao.getAtencionPrestaciones(agenda.getAtencionmedica().getAtencionMedicaId());
			String prestaciones = "";
			String[] prestacionesArray = new String[atencionprestacions.size()];
			i=0;
			for (Atencionprestacion atencionprestacion : atencionprestacions) {
				prestaciones = prestaciones + atencionprestacion.getCatprestacion().getDescripcion().trim() + ",";
				prestacionesArray[i] = atencionprestacion.getCatprestacion().getCodigo();
				i++;
			}
			finalizarAtencionForm.setPrestaciones(prestaciones);
			
			String medicamentos = "";
			Collection<Recetas> recetas = recetaMedicaDao.getRecetasByAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
		
			i = 0;
			Integer[] unidades = new Integer[recetas.size()];
			String[] claveMedicamentos = new String[recetas.size()];
			for (Recetas receta : recetas) {
				Iterator<Medicamentosreceta> medicamentosRecetaIterator = receta.getMedicamentosrecetas().iterator();
				while (medicamentosRecetaIterator.hasNext()) {
					Medicamentosreceta medicamentosreceta = (Medicamentosreceta) medicamentosRecetaIterator.next();
					medicamentos = medicamentos + medicamentosreceta.getCatmedicamentos().getMedicamento().trim() + ",";
					unidades[i] = medicamentosreceta.getCantidadUnidades();
					claveMedicamentos[i] = medicamentosreceta.getCatmedicamentos().getClave();
					i++;
				}
			}
			finalizarAtencionForm.setMedicamentos(medicamentos);
			
			String estudiosLaboratorio = "";
			Collection<Prestacionesportomar> prestacionesportomars = estudiosMedicosDao.getPrestacionPorTomarByEstudios(agenda.getAtencionmedica().getAtencionMedicaId(),1);
			for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
				estudiosLaboratorio = estudiosLaboratorio + prestacionesportomar.getCatprestacion().getDescripcion().trim() + ",";
			}
			finalizarAtencionForm.setEstudiosLaboratorio(estudiosLaboratorio);
			
			String estudiosGabinete = "";
			Collection<Prestacionesportomar> prestacionesportomarsGabinete = estudiosMedicosDao.getPrestacionPorTomarByEstudios(agenda.getAtencionmedica().getAtencionMedicaId(),2);
			for (Prestacionesportomar prestacionesportomar : prestacionesportomarsGabinete) {
				estudiosGabinete = estudiosGabinete + prestacionesportomar.getCatprestacion().getDescripcion().trim() + ",";
			}
			finalizarAtencionForm.setEstudiosGabinete(estudiosGabinete);
			
			String otrosEstudios = "";
			Collection<Prestacionesportomar> prestacionesportomarsOtros = estudiosMedicosDao.getPrestacionPorTomarByEstudios(agenda.getAtencionmedica().getAtencionMedicaId(),3);
			for (Prestacionesportomar prestacionesportomar : prestacionesportomarsOtros) {
				otrosEstudios = otrosEstudios + prestacionesportomar.getCatprestacion().getDescripcion().trim() + ",";
			}
			finalizarAtencionForm.setOtrosEstudios(otrosEstudios);
			
			String licenciaMedica = "";
			Licenciamedica licenciamedica = licenciaMedicaDao.getLicenciaMedicaByIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
			licenciaMedica = licenciamedica.getCatlicmedicamotivos().getLicMedicaMotivo() + ",Inicial Desde " + licenciamedica.getFechaInicio() + ",Hasta " + licenciamedica.getFechaTermino();
			finalizarAtencionForm.setLicenciaMedica(licenciaMedica);
			
			String referenciaEspecialista = "";
			Solicitudreferencia solicitudreferencia = solicitudReferenciaDao.getSolicitudReferenciaByIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
			referenciaEspecialista = solicitudreferencia.getCatespecialidadesmedicas().getEspecialidadMedica();
			finalizarAtencionForm.setReferenciaEspecialista(referenciaEspecialista);
			
			String contrareferenciaS = "";
			Contrareferencia contrareferencia = contrareferenciaDao.getContrareferenciaByIdSolicitud(solicitudreferencia.getSolicitudReferenciaId());
			contrareferenciaS = contrareferencia.getMotivoContrareferencia();
			finalizarAtencionForm.setContrareferenciaS(contrareferenciaS);
			
			finalizarAtencionForm.setNombre(agenda.getUsuarios().getNombre());
			finalizarAtencionForm.setApellidoMaterno(agenda.getUsuarios().getApellidoMaterno());
			finalizarAtencionForm.setApellidoPaterno(agenda.getUsuarios().getApellidoPaterno());
			finalizarAtencionForm.setSexo(agenda.getUsuarios().getCatsexos().getSexoId());
			finalizarAtencionForm.setFechaDeNacimiento(FormatUtil.getDate(agenda.getUsuarios().getFechaDeNacimiento()));
			finalizarAtencionForm.setEstado(agenda.getUsuarios().getCatestados().getEstadoId());
			finalizarAtencionForm.setIdUsuario(agenda.getUsuarios().getUsuarioId());
			Iterator<Usuariotipoidentificador> usuarioTipoIdentificadorIterator = agenda.getUsuarios().getUsuariotipoidentificadors().iterator();
			String cedula = "";
			while (usuarioTipoIdentificadorIterator.hasNext()) {
				Usuariotipoidentificador usuariotipoidentificador = (Usuariotipoidentificador) usuarioTipoIdentificadorIterator.next();
				if(usuariotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.RFC.getId()){
					finalizarAtencionForm.setRfc(usuariotipoidentificador.getValor());
				}else if (usuariotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CVEMEDICO.getId()){
					cedula = usuariotipoidentificador.getValor();
				}
			}
			
			finalizarAtencionForm.setNombreAfiliado(agenda.getAfiliado().getNombre());
			finalizarAtencionForm.setApellidoMaternoAfiliado(agenda.getAfiliado().getApellidoMaterno());
			finalizarAtencionForm.setApellidoPaternoAfiliado(agenda.getAfiliado().getApellidoPaterno());
			finalizarAtencionForm.setSexoAfiliado(agenda.getAfiliado().getCatsexos().getSexoId());
			finalizarAtencionForm.setFechaDeNacimientoAfiliado(FormatUtil.getDate(agenda.getAfiliado().getFechaDeNacimiento()));
			finalizarAtencionForm.setEstadoAfiliado(CatEstadosEnum.DISTRITO_FEDERAL.getId());
			finalizarAtencionForm.setIdAfiliado(agenda.getAfiliado().getAfiliadoId());
			
			Collection<Documentosprestador> documentosprestadors = agendaDao.getDocumentosPrestadorByPrestador(agenda.getPrestadores().getPrestadorId());
			for (Documentosprestador documentosprestador : documentosprestadors) {
				if (documentosprestador.getAutentiaMedico()) {
					finalizarAtencionForm.setHuellaMedico(true);
					break;
				}
			}
			
			for (Documentosprestador documentosprestador : documentosprestadors) {
				if (documentosprestador.getAutentiaPaciente()) {
					finalizarAtencionForm.setHuellaAfiliado(true);
					break;
				}
			}

//			SvcSassSiStub svcSassSiStub = new SvcSassSiStub();
//			Autorizar autorizar = new Autorizar();
//			Iterator<Afiliadotipoidentificador> afiliadoTipoIdentificadoIterator = agenda.getAfiliado().getAfiliadotipoidentificadors().iterator();
//			while (afiliadoTipoIdentificadoIterator.hasNext()) {
//				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) afiliadoTipoIdentificadoIterator.next();
//				if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CODEMPRESA.getId()) {
//					autorizar.setCodEmpresa(Integer.parseInt(afiliadotipoidentificador.getTipoIdValor()));		
//				}else if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CODAFILIADO.getId()) {
//					autorizar.setCodAfiliado(afiliadotipoidentificador.getTipoIdValor());
//				}else if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CORRELATIVO.getId()) {
//					autorizar.setCorrelativo(Integer.parseInt(afiliadotipoidentificador.getTipoIdValor()));
//				}
//			}
//
//			autorizar.setCodCuenta(Integer.parseInt(agenda.getLugaresdeatencion().getClaveDeLaUnidad()));
//			ArrayOfstring arrayOfstring = new ArrayOfstring();
//			arrayOfstring.setString(diagnostico);
//			autorizar.setCodDiagnosticoArray(arrayOfstring);
//			
//			ArrayOfProcedimientoCto arrayOfProcedimientoCto = new ArrayOfProcedimientoCto();
//			ProcedimientoCto[] procedimientoCtos = new ProcedimientoCto[prestacionesArray.length];
//			
//			for (int j = 0; j < prestacionesArray.length; j++) {
//				ProcedimientoCto procedimientoCto = new ProcedimientoCto();
//				TiposCuenta tiposCuenta = new TiposCuenta("2",true);
//				procedimientoCto.setTipoCuenta(tiposCuenta);
//				procedimientoCto.setCodEspecialidad(cedula);
//				procedimientoCto.setCodProcedimiento(prestacionesArray[j]);
//				TiposCopago tiposCopago = new TiposCopago("1", true);
//				procedimientoCto.setTipoCopago(tiposCopago);
//				procedimientoCto.setCostoBase(new BigDecimal(0.0));
//				procedimientoCto.setCopago(new BigDecimal(0.0));
//				procedimientoCtos[j] = procedimientoCto;	
//			}
//			arrayOfProcedimientoCto.setProcedimientoCto(procedimientoCtos);
//			autorizar.setProcedimientos(arrayOfProcedimientoCto);
//			
//			ArrayOfMedicamentoCto arrayOfMedicamentoCto = new ArrayOfMedicamentoCto();
//			MedicamentoCto[] medicamentoCtos = new MedicamentoCto[claveMedicamentos.length];
//			for (int j = 0; j < claveMedicamentos.length; j++) {
//				MedicamentoCto medicamentoCto = new MedicamentoCto();
//				medicamentoCto.setCodMedicamento(claveMedicamentos[j]);
//				medicamentoCto.setCantidad(unidades[j]);
////				medicamentoCto.setSitAutorizacion(param);
//				TiposCopago tiposCopago2 = new TiposCopago("1", true);
//				medicamentoCto.setTipoCopago(tiposCopago2);
//				medicamentoCto.setCostoBase(new BigDecimal(0.0));
//				medicamentoCto.setCopago(new BigDecimal(0.0));
//				medicamentoCtos[j] = medicamentoCto;	
//			}
//			arrayOfMedicamentoCto.setMedicamentoCto(medicamentoCtos);
//			autorizar.setMedicamentos(arrayOfMedicamentoCto);
//			
//			AutorizacionCto autorizacionCto = svcSassSiStub.autorizar(autorizar).getAutorizarResult();
//
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		} catch (ISvcSassc_Autorizar_DefaultFaultContractFault_FaultMessage e) {
//			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void finalizar(FinalizarAtencionForm finalizarAtencionForm) {
		Agenda agenda = agendaDao.getAgendaById(finalizarAtencionForm.getIdAgenda());
		agenda.getAtencionmedica().setFechaAtendidoMedico(new Date());
		agenda.getAtencionmedica().setHoraAtendidoMedico(new Time(new Date().getTime()));
		agenda.getAtencionmedica().setAuditoriaautentiaByAuditoriaAutentiaMedicoId(auditoriaDao.getAuditoriAutentiaById(finalizarAtencionForm.getIdAuditoria()));
		agendaDao.updateAtencionMedica(agenda.getAtencionmedica());
		finalizarAtencionForm.setAtencionFinalizada(true);
	}

	@Override
	public void imprimirTodo(FinalizarAtencionForm finalizarAtencionForm) {
		try{
			Collection<ByteArrayOutputStream> byteArrayOutputStreams = new ArrayList<>();
			byteArrayOutputStreams.add(certificadosService.recetaMedica(finalizarAtencionForm.getIdAgenda()));
			byteArrayOutputStreams.add(certificadosService.licenciaMedica(finalizarAtencionForm.getIdAgenda()));
			byteArrayOutputStreams.add(certificadosService.estudiosLaboratorio(finalizarAtencionForm.getIdAgenda()));
			byteArrayOutputStreams.add(certificadosService.estudiosGabinete(finalizarAtencionForm.getIdAgenda()));
			byteArrayOutputStreams.add(certificadosService.estudiosOtros(finalizarAtencionForm.getIdAgenda()));
			byteArrayOutputStreams.add(certificadosService.solicitudReferencia(finalizarAtencionForm.getIdAgenda()));
			byteArrayOutputStreams.add(certificadosService.solicitudContrareferencia(finalizarAtencionForm.getIdAgenda()));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);

			PdfCopy copy = new PdfCopy(document, baos);
			document.open();
			
	        PdfReader reader;
	        int n = 0;
	        
	        for (ByteArrayOutputStream byteArrayOutputStream : byteArrayOutputStreams) {
	        	reader = new PdfReader(byteArrayOutputStream.toByteArray());
	            n = reader.getNumberOfPages();
	            for (int page = 0; page < n; ) {
	                copy.addPage(copy.getImportedPage(reader, ++page));
	            }
	            copy.freeReader(reader);
	            reader.close();	
			}
            
            document.close();
            finalizarAtencionForm.setFile(baos);
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

}

