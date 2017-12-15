package com.mx.sab.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IEstudiosMedicosDao;
import com.mx.sab.dao.ILicenciaMedicaDao;
import com.mx.sab.dao.IListaPacientesDao;
import com.mx.sab.dao.IPrestacionesDao;
import com.mx.sab.dao.ISignosDao;
import com.mx.sab.enums.CatSexoEnum;
import com.mx.sab.enums.CatTipoServicioEnum;
import com.mx.sab.enums.CatTipoValorAseguradorEnum;
import com.mx.sab.enums.TipoIdentificadorEnum;
import com.mx.sab.form.CertificadosForm;
import com.mx.sab.form.ConstanciaAsistenciaForm;
import com.mx.sab.form.ConstanciaCuidadosMaternalesForm;
import com.mx.sab.form.ConstanciaSaludForm;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Afiliadotipovalorasegurador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Atencionprestacion;
import com.mx.sab.model.Catprestacion;
import com.mx.sab.model.Contrareferencia;
import com.mx.sab.model.Licenciamedica;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.model.Notamedica;
import com.mx.sab.model.NotamedicaCies10;
import com.mx.sab.model.Prestacionesaseguradorequivalencias;
import com.mx.sab.model.Prestacionesportomar;
import com.mx.sab.model.Prestacionesprestadorequivalencias;
import com.mx.sab.model.Prestacionprestador;
import com.mx.sab.model.Recetas;
import com.mx.sab.model.Signosvitales;
import com.mx.sab.model.Solicitudreferencia;
import com.mx.sab.model.Usuarioespecialidades;
import com.mx.sab.model.Usuariolugaratencion;
import com.mx.sab.service.ICertificadosService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AutocompleteVo;

@Service
@Log4j2
public class CertificadosServiceImpl implements ICertificadosService {
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Autowired
	private ILicenciaMedicaDao licenciaMedicaDao;
	
	@Autowired
	private IListaPacientesDao listaPacientesDao;	
	
	@Autowired
	private IPrestacionesDao prestacionesDao;
	
	@Autowired
	private IEstudiosMedicosDao estudiosMedicosDao;
	
	@Autowired
	private ISignosDao signosDao;
	
	
	
	@Override
	public void inicializaFrom(CertificadosForm certificadosForm) {
		Collection<Integer> certificados = new ArrayList<>();
		certificados.add(1);
		certificadosForm.setCertificados(certificados);
	}

	@Override
	public void constanciaDeAsistencia(ConstanciaAsistenciaForm constanciaAsistenciaForm) {
		try{
			Agenda agenda = agendaDao.getAgendaById(constanciaAsistenciaForm.getIdAgenda());
			InputStream pdf = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/mx/sab/resources/constanciaAsistencia.pdf");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfReader reader = new PdfReader(pdf);
			PdfStamper stamper = new PdfStamper(reader, baos);
			PdfContentByte canvas = stamper.getOverContent(1);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getDescripcion()), 360, 680, 0);
			Calendar calendar = Calendar.getInstance();
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(calendar.get(Calendar.DATE)+" de "+ new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)]  +" de "+ calendar.get(Calendar.YEAR)), 360, 648, 0);
			String nombre = constanciaAsistenciaForm.getNombre();
			if (nombre.length()>31) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(nombre.substring(0, 31)), 360, 602, 0);	
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(nombre.substring(31, nombre.length())), 20, 575, 0);	
			}else{
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(nombre), 360, 602, 0);	
			}
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaAsistenciaForm.getDescripcion()), 150, 547, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaAsistenciaForm.getHoraInicio()), 100, 520, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaAsistenciaForm.getHoraFin()), 250, 520, 0);
			stamper.close();
			baos.close();
			constanciaAsistenciaForm.setFile(baos);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inicializaConstanciaDeAsistencia(ConstanciaAsistenciaForm constanciaAsistenciaForm) {
		Agenda agenda = agendaDao.getAgendaById(constanciaAsistenciaForm.getIdAgenda());
		constanciaAsistenciaForm.setNombre(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno());
		constanciaAsistenciaForm.setHoraInicio(FormatUtil.getTime(agenda.getHoraCita()));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(agenda.getHoraCita());
		calendar.add(Calendar.HOUR, 1);
		constanciaAsistenciaForm.setHoraFin(FormatUtil.getTime(calendar.getTime()));
	}

	@Override
	public void inicializaConstanciaDeSalud(ConstanciaSaludForm constanciaSaludForm) {
		Agenda agenda = agendaDao.getAgendaById(constanciaSaludForm.getIdAgenda());
		constanciaSaludForm.setMedico(agenda.getUsuarios().getNombre()+" "+agenda.getUsuarios().getApellidoPaterno()+" "+agenda.getUsuarios().getApellidoMaterno());
		constanciaSaludForm.setNombre(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno());
	}

	@Override
	public void constanciaDeSalud(ConstanciaSaludForm constanciaSaludForm) {
		try{
			Agenda agenda = agendaDao.getAgendaById(constanciaSaludForm.getIdAgenda());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/constancia_salud.jpg").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (0.45 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0, 100);
			document.add(image);
			document.close();
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);
			
			Font helvetica = new Font(FontFamily.HELVETICA, 9);
			
			Calendar calendar = Calendar.getInstance();
	        
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaSaludForm.getNombre()), 50, 485, 0);
			
			long diffyears = FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime());
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+diffyears), 124, 460, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaSaludForm.getDescripcion()), 50, 433, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DATE),helvetica), 60, 393, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)],helvetica), 160, 393, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR),helvetica), 244, 393, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaSaludForm.getMedico()), 50, 233, 0);
			
			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = agenda.getUsuarios().getUsuarioespecialidadeses().iterator();
			String cedula = "";
			while (usuarioEspecialidadesIterator.hasNext()) {
				Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				cedula = usuarioespecialidades.getCedulaEspecialidad();
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(cedula), 105, 170, 0);
			stamper.close();
			constanciaSaludForm.setFile(baos2);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void constanciaCuidadosMaternales(ConstanciaCuidadosMaternalesForm constanciaCuidadosMaternalesForm) {
		try{
			Agenda agenda = agendaDao.getAgendaById(constanciaCuidadosMaternalesForm.getIdAgenda());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/cuidados_maternos.jpg").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (0.45 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0, 100);
			document.add(image);
			document.close();
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);
			
			
			Calendar calendar = Calendar.getInstance();
			
			long diffyears = FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime());
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getDescripcion()), 25, 705, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getNombre()), 25, 653, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getCategoria()), 25, 620, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getClave()), 382, 618, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getAdscripcion()), 25, 588, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getHorario()), 382, 587, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getNombreClave()), 25, 510, 0);
			calendar.setTime(agenda.getAfiliado().getFechaDeNacimiento());
			
		    GregorianCalendar d1 = new GregorianCalendar();
		    d1.setTime(agenda.getAfiliado().getFechaDeNacimiento());
		    GregorianCalendar d2 = new GregorianCalendar();

		    d2.add(Calendar.MONTH, -d1.get(Calendar.MONTH));
		    d2.add(Calendar.DAY_OF_MONTH, -d1.get(Calendar.DAY_OF_MONTH) + 1);

		    int m = d2.get(Calendar.MONTH);
		    int d = d2.get(Calendar.DAY_OF_MONTH) - 1;
		    
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DAY_OF_MONTH)), 65, 445, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.MONTH)), 150, 445, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR)), 235, 445, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+diffyears), 325, 445, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+m), 405, 445, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+d), 495, 445, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getDiagnostico()), 25, 378, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("DIAGNOSTICO"), 25, 360, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("DIAGNOSTICO"), 25, 342, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("DIAGNOSTICO"), 25, 323, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("NOMBRE"), 282, 300, 0);
			
			GregorianCalendar calendarInicio = new GregorianCalendar(Integer.parseInt(constanciaCuidadosMaternalesForm.getAnioInicio()), Integer.parseInt(constanciaCuidadosMaternalesForm.getMesInicio()), Integer.parseInt(constanciaCuidadosMaternalesForm.getDiaInicio()));
			GregorianCalendar calendarFin = new GregorianCalendar(Integer.parseInt(constanciaCuidadosMaternalesForm.getAnioFin()), Integer.parseInt(constanciaCuidadosMaternalesForm.getMesFin()), Integer.parseInt(constanciaCuidadosMaternalesForm.getDiaFin()));
			
			long diff = calendarFin.getTimeInMillis() - calendarInicio.getTimeInMillis();
			long days = (24 * 60 * 60 * 1000);
			long diffdays = diff / days;
			int dias = 0;
			String letra = "";
			diffdays++;
			dias = (int) diffdays;
			switch (""+diffdays) {
			case "1":
				letra = "UNO";
				break;
			case "2":
				letra = "DOS";
				break;
			case "3":
				letra = "TRES";
				break;
			case "4":
				letra = "CUATRO";
				break;
			case "5":
				letra = "CINCO";
				break;
			case "6":
				letra = "SEIS";
				break;
			case "7":
				letra = "SIETE";
				break;
			default:
				break;
			}
		
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+dias), 43, 195, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(letra), 175, 195, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getDiaInicio()), 370, 210, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getMesInicio()), 435, 210, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getAnioInicio()), 500, 210, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getDiaFin()), 370, 180, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getMesFin()), 435, 180, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(constanciaCuidadosMaternalesForm.getAnioFin()), 500, 180, 0);	
			stamper.close();
			constanciaCuidadosMaternalesForm.setFile(baos2);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void recetaMedica(CertificadosForm certificadosForm) {
		certificadosForm.setFile(recetaMedica(certificadosForm.getIdAgenda()));
	}
	
	@Override
	public ByteArrayOutputStream recetaMedica(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/receta.jpg").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (0.80 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,40);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getPrestadores().getCatestados().getEstado()), 25, 463, 0);
			
			Calendar calendar = Calendar.getInstance();
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DAY_OF_MONTH)), 318, 463, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.MONTH)), 350, 463, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR)), 388, 463, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getClaveDeLaUnidad()), 422, 463, 0);
			
			Iterator<Licenciamedica> licenciaMedicaIterator = agenda.getAtencionmedica().getLicenciamedicas().iterator();
			NotamedicaCies10 notamedicaCies10Principal = null;
			String tipoServicio = "";
			while (licenciaMedicaIterator.hasNext()) {
				Licenciamedica licenciamedica = licenciaMedicaIterator.next();
				tipoServicio = licenciamedica.getCatlicmedicatiposservicio().getLicMedicaTipoServicio();
			}
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(tipoServicio), 667, 463, 0);
			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno() ), 25, 425, 0);
			
			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = agenda.getUsuarios().getUsuarioespecialidadeses().iterator();
			String cedula = "";
			while (usuarioEspecialidadesIterator.hasNext()) {
				Usuarioespecialidades usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				usuarioespecialidades.getCedulaEspecialidad();
				break;
			}
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(cedula), 693, 425, 0);
			
			Iterator<Notamedica> notaMedicaIterator = agenda.getAtencionmedica().getNotamedicas().iterator();
			String diagnostico = "";
			while (notaMedicaIterator.hasNext()) {
				Notamedica notamedica = (Notamedica) notaMedicaIterator.next();
				Iterator<NotamedicaCies10> notaMedicaCies10Iterator = notamedica.getNotamedicaCies10s().iterator();
				while (notaMedicaCies10Iterator.hasNext()) {
					NotamedicaCies10 notamedicaCies10 = (NotamedicaCies10) notaMedicaCies10Iterator.next();
					if (notamedicaCies10.getPrincipal() == 1) {
						diagnostico = notamedicaCies10.getCatcies10().getDescripcion();	
					}
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diagnostico), 25, 400, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("DIAGNOSTICO DIAGNOSTICO DIAGNOSTICO"), 25, 380, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("DIAGNOSTICO DIAGNOSTICO DIAGNOSTICO"), 25, 360, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(cedula), 30, 175, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("CLAVE"), 123, 212, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre()+" "+agenda.getUsuarios().getApellidoPaterno()+" "+agenda.getUsuarios().getApellidoMaterno()), 219, 212, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getDescripcion()), 123, 175, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 30, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("1"), 50, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("2"), 85, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("3"), 107, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("4"), 127, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("5"), 148, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("6"), 184, 122, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("7"), 223, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("8"), 245, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("9"), 281, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 298, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("1"), 316, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("2"), 335, 122, 0);	
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("TD"), 375, 122, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("NUMERO NUMERO"), 426, 122, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("SURTIO SURTIO"), 624, 122, 0);

			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void licenciaMedica(CertificadosForm certificadosForm) {
		certificadosForm.setFile(licenciaMedica(certificadosForm.getIdAgenda()));
	}

	public ByteArrayOutputStream licenciaMedica(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/licencia_medica.jpg").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (0.80 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,40);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getCatestados().getEstado()), 65, 464, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getDescripcion()), 216, 464, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getClaveDeLaUnidad()), 461, 464, 0);
			
			Calendar calendar = Calendar.getInstance();
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DAY_OF_MONTH)), 616, 464, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.MONTH)), 672, 464, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR)), 730, 464, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno()), 65, 415, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 665, 415, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 681, 415, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 697, 415, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 713, 415, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 730, 415, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 745, 415, 0);
			
			Licenciamedica licenciamedica = licenciaMedicaDao.getLicenciaMedicaByIdAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
			Notamedica notamedica = listaPacientesDao.getNotaMedicaByAtencionMedicaId(agenda.getAtencionmedica().getAtencionMedicaId());
			NotamedicaCies10 notamedicaCies10 = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedicaPrincipal(notamedica.getNotaMedicaId());
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(notamedicaCies10.getCatcies10().getDescripcion()), 65, 336, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(licenciamedica.getLetraDias()), 195, 258, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+licenciamedica.getNumDias()), 384, 258, 0);
			
			calendar.setTime(licenciamedica.getFechaInicio());
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DAY_OF_MONTH)), 455, 258, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.MONTH)), 510, 258, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR)), 560, 258, 0);
			
			calendar.setTime(licenciamedica.getFechaTermino());
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DAY_OF_MONTH)), 625, 258, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.MONTH)), 680, 258, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR)), 730, 258, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 225, 226, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 176, 209, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 243, 209, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 386, 224, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 386, 203, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 386, 183, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 386, 162, 0);
			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 528, 221, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 528, 193, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 528, 165, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre()+" "+agenda.getUsuarios().getApellidoPaterno()+" "+agenda.getUsuarios().getApellidoMaterno()), 65, 115, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno()), 554, 115, 0);

			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void estudiosLaboratorio(CertificadosForm certificadosForm) {
		certificadosForm.setFile(estudiosLaboratorio(certificadosForm.getIdAgenda()));
	}

	@Override
	public ByteArrayOutputStream estudiosLaboratorio(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/laboratorio.png").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (0.24 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,10);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			Calendar calendar = Calendar.getInstance();
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DAY_OF_MONTH)), 445, 741, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.MONTH)), 475, 741, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR)), 510, 741, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre()+" "+agenda.getUsuarios().getApellidoPaterno()+" "+agenda.getUsuarios().getApellidoMaterno()), 80, 720, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getTelefono1()), 200, 653, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno()), 96, 632, 0);
			
			Notamedica notamedica = listaPacientesDao.getNotaMedicaByAtencionMedicaId(agenda.getAtencionmedica().getAtencionMedicaId());
			NotamedicaCies10 notamedicaCies10 = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedicaPrincipal(notamedica.getNotaMedicaId());
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(notamedicaCies10.getCatcies10().getDescripcion()), 110, 527, 0);
			
			
			Collection<Prestacionesportomar> prestacionesportomars = estudiosMedicosDao.getPrestacionPorTomarByEstudios(agenda.getAtencionmedica().getAtencionMedicaId(),1);
			Collection<Catprestacion> catprestacions = new ArrayList<>();
			for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
				catprestacions.add(prestacionesportomar.getCatprestacion());
			}
			
			AutocompleteVo autocompleteVo = null;
			List<AutocompleteVo> autocompleteVos = new ArrayList<>();
			if(agenda.getPrestadores().getUsarTablaPrestacionesSab()==1){
				for (Catprestacion catprestacion : catprestacions) {
					autocompleteVo = new AutocompleteVo();
//					autocompleteVo.setData(catprestacion.getPrestacionId());
//					autocompleteVo.setValue(catprestacion.getDescripcion());
					autocompleteVos.add(autocompleteVo);
				}
			}else{
				
				Collection<Prestacionprestador> prestacionprestadors = prestacionesDao.getPrestacionesByPrestador(agenda.getPrestadores().getPrestadorId());
				
				for (Prestacionprestador prestacionprestador : prestacionprestadors) {
					Iterator<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciasIterator = prestacionprestador.getPrestacionesprestadorequivalenciases().iterator();
					while (prestacionesprestadorequivalenciasIterator.hasNext()) {
						Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = (Prestacionesprestadorequivalencias) prestacionesprestadorequivalenciasIterator.next();
						for (Catprestacion catprestacion : catprestacions) {
							if(catprestacion.getPrestacionId() == prestacionesprestadorequivalencias.getCatprestacion().getPrestacionId()){
								autocompleteVo = new AutocompleteVo();
//								autocompleteVo.setData(prestacionprestador.getPrestacionPrestadorId());
//								autocompleteVo.setValue(prestacionprestador.getDescripcion());
								autocompleteVos.add(autocompleteVo);			
							}
						}
					}				
				}
			}
			
			int x = 70;
			int y = 505;
			for (AutocompleteVo autocompleteVo2 : autocompleteVos) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(autocompleteVo2.getValue()), x, y, 0);
				y=y-20;
				x=50;
			}
			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void estudiosGabinete(CertificadosForm certificadosForm) {
		certificadosForm.setFile(estudiosGabinete(certificadosForm.getIdAgenda()));
	}

	@Override
	public ByteArrayOutputStream estudiosGabinete(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/gabinete.png").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (0.24 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,10);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			Calendar calendar = Calendar.getInstance();
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DAY_OF_MONTH)), 445, 741, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.MONTH)), 475, 741, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR)), 510, 741, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre()+" "+agenda.getUsuarios().getApellidoPaterno()+" "+agenda.getUsuarios().getApellidoMaterno()), 80, 720, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getTelefono1()), 200, 653, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno()), 96, 632, 0);
			
			Notamedica notamedica = listaPacientesDao.getNotaMedicaByAtencionMedicaId(agenda.getAtencionmedica().getAtencionMedicaId());
			NotamedicaCies10 notamedicaCies10 = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedicaPrincipal(notamedica.getNotaMedicaId());
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(notamedicaCies10.getCatcies10().getDescripcion()), 110, 527, 0);
			
			
			Collection<Prestacionesportomar> prestacionesportomars = estudiosMedicosDao.getPrestacionPorTomarByEstudios(agenda.getAtencionmedica().getAtencionMedicaId(),2);
			Collection<Catprestacion> catprestacions = new ArrayList<>();
			for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
				catprestacions.add(prestacionesportomar.getCatprestacion());
			}
			
			AutocompleteVo autocompleteVo = null;
			List<AutocompleteVo> autocompleteVos = new ArrayList<>();
			if(agenda.getPrestadores().getUsarTablaPrestacionesSab()==1){
				for (Catprestacion catprestacion : catprestacions) {
					autocompleteVo = new AutocompleteVo();
//					autocompleteVo.setData(catprestacion.getPrestacionId());
//					autocompleteVo.setValue(catprestacion.getDescripcion());
					autocompleteVos.add(autocompleteVo);
				}
			}else{
				
				Collection<Prestacionprestador> prestacionprestadors = prestacionesDao.getPrestacionesByPrestador(agenda.getPrestadores().getPrestadorId());
				
				for (Prestacionprestador prestacionprestador : prestacionprestadors) {
					Iterator<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciasIterator = prestacionprestador.getPrestacionesprestadorequivalenciases().iterator();
					while (prestacionesprestadorequivalenciasIterator.hasNext()) {
						Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = (Prestacionesprestadorequivalencias) prestacionesprestadorequivalenciasIterator.next();
						for (Catprestacion catprestacion : catprestacions) {
							if(catprestacion.getPrestacionId() == prestacionesprestadorequivalencias.getCatprestacion().getPrestacionId()){
								autocompleteVo = new AutocompleteVo();
//								autocompleteVo.setData(prestacionprestador.getPrestacionPrestadorId());
//								autocompleteVo.setValue(prestacionprestador.getDescripcion());
								autocompleteVos.add(autocompleteVo);			
							}
						}
					}				
				}
			}
			
			int x = 70;
			int y = 505;
			for (AutocompleteVo autocompleteVo2 : autocompleteVos) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(autocompleteVo2.getValue()), x, y, 0);
				y=y-20;
				x=50;
			}
			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void estudiosOtros(CertificadosForm certificadosForm) {
		certificadosForm.setFile(estudiosOtros(certificadosForm.getIdAgenda()));
		
	}

	@Override
	public ByteArrayOutputStream estudiosOtros(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/otros.png").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (0.24 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,10);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			Calendar calendar = Calendar.getInstance();
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.DAY_OF_MONTH)), 445, 741, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.MONTH)), 475, 741, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+calendar.get(Calendar.YEAR)), 510, 741, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre()+" "+agenda.getUsuarios().getApellidoPaterno()+" "+agenda.getUsuarios().getApellidoMaterno()), 80, 720, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getTelefono1()), 200, 653, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno()), 96, 632, 0);
			
			Notamedica notamedica = listaPacientesDao.getNotaMedicaByAtencionMedicaId(agenda.getAtencionmedica().getAtencionMedicaId());
			NotamedicaCies10 notamedicaCies10 = listaPacientesDao.getNotaMedicaCies10ByIdNotaMedicaPrincipal(notamedica.getNotaMedicaId());
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(notamedicaCies10.getCatcies10().getDescripcion()), 110, 527, 0);
			
			
			Collection<Prestacionesportomar> prestacionesportomars = estudiosMedicosDao.getPrestacionPorTomarByEstudios(agenda.getAtencionmedica().getAtencionMedicaId(),3);
			Collection<Catprestacion> catprestacions = new ArrayList<>();
			for (Prestacionesportomar prestacionesportomar : prestacionesportomars) {
				catprestacions.add(prestacionesportomar.getCatprestacion());
			}
			
			AutocompleteVo autocompleteVo = null;
			List<AutocompleteVo> autocompleteVos = new ArrayList<>();
			if(agenda.getPrestadores().getUsarTablaPrestacionesSab()==1){
				for (Catprestacion catprestacion : catprestacions) {
					autocompleteVo = new AutocompleteVo();
//					autocompleteVo.setData(catprestacion.getPrestacionId());
//					autocompleteVo.setValue(catprestacion.getDescripcion());
					autocompleteVos.add(autocompleteVo);
				}
			}else{
				
				Collection<Prestacionprestador> prestacionprestadors = prestacionesDao.getPrestacionesByPrestador(agenda.getPrestadores().getPrestadorId());
				
				for (Prestacionprestador prestacionprestador : prestacionprestadors) {
					Iterator<Prestacionesprestadorequivalencias> prestacionesprestadorequivalenciasIterator = prestacionprestador.getPrestacionesprestadorequivalenciases().iterator();
					while (prestacionesprestadorequivalenciasIterator.hasNext()) {
						Prestacionesprestadorequivalencias prestacionesprestadorequivalencias = (Prestacionesprestadorequivalencias) prestacionesprestadorequivalenciasIterator.next();
						for (Catprestacion catprestacion : catprestacions) {
							if(catprestacion.getPrestacionId() == prestacionesprestadorequivalencias.getCatprestacion().getPrestacionId()){
								autocompleteVo = new AutocompleteVo();
//								autocompleteVo.setData(prestacionprestador.getPrestacionPrestadorId());
//								autocompleteVo.setValue(prestacionprestador.getDescripcion());
								autocompleteVos.add(autocompleteVo);			
							}
						}
					}				
				}
			}
			
			int x = 70;
			int y = 505;
			for (AutocompleteVo autocompleteVo2 : autocompleteVos) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(autocompleteVo2.getValue()), x, y, 0);
				y=y-20;
				x=50;
			}
			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void solicitudReferencia(CertificadosForm certificadosForm) {
		certificadosForm.setFile(solicitudReferencia(certificadosForm.getIdAgenda()));
	}

	@Override
	public ByteArrayOutputStream solicitudReferencia(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/solicitud_referencia.png").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (.874 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,25);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			Calendar calendar = Calendar.getInstance();
			int dia = calendar.get(Calendar.DAY_OF_MONTH);
			if (dia<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 433, 758, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+dia), 448, 758, 0);
			}else {
				String diaS = ""+dia;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(0, 1)), 433, 758, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(1, 2)), 448, 758, 0);				
			}
			
			int mes = calendar.get(Calendar.MONTH);
			if (mes<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 463, 758, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+mes), 478, 758, 0);	
			}else{
				String mesS = ""+mes;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(0, 1)), 463, 758, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(1, 2)), 478, 758, 0);
			}
			
			int anio = calendar.get(Calendar.YEAR);
			String anioS = ""+anio;
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(2, 3)), 494, 758, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(3, 4)), 510, 758, 0);

			int hora = calendar.get(Calendar.HOUR_OF_DAY);
			if (hora<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 526, 758, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+hora), 541, 758, 0);	
			}else {
				String horaS = ""+hora;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(horaS.substring(0, 1)), 526, 758, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(horaS.substring(1, 2)), 541, 758, 0);
			}
			
			int minutos = calendar.get(Calendar.MINUTE);
			if (minutos<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 557, 758, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutos), 572, 758, 0);				
			}else{
				String minutosS = ""+minutos;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutosS.substring(0, 1)), 557, 758, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutosS.substring(1, 2)), 572, 758, 0);				
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getDescripcion()), 150, 726, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getClaveDeLaUnidad()), 507, 726, 0);
			
			Iterator<Solicitudreferencia> solicitudReferenciaIterator = agenda.getAtencionmedica().getSolicitudreferencias().iterator();
			Solicitudreferencia solicitudreferencia = null;
			while (solicitudReferenciaIterator.hasNext()) {
				Solicitudreferencia solicitudreferenciaAux = (Solicitudreferencia) solicitudReferenciaIterator.next();
				solicitudreferencia = solicitudreferenciaAux;
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getMotivoReferencia()), 150, 695, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno()), 135, 671, 0);
			
			if(agenda.getAfiliado().getCatsexos().getSexoId() == CatSexoEnum.MASCULINO.getId()){
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 70, 640, 0);	
			}else{
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 95, 640, 0);	
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime())), 175, 640, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getTelefono1()), 500, 640, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getLugaresdeatencion().getDescripcion()), 155, 617, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getLugaresdeatencion().getClaveDeLaUnidad()), 510, 617, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getCatespecialidadesmedicas().getEspecialidadMedica()), 120, 602, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 145, 585, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 275, 585, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 545, 585, 0);
			
			calendar.setTime(solicitudreferencia.getFecha());
			dia = calendar.get(Calendar.DAY_OF_MONTH);
			if (dia<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 433, 528, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+dia), 448, 528, 0);
			}else {
				String diaS = ""+dia;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(0, 1)), 433, 528, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(1, 2)), 448, 528, 0);		
			}
			
			mes = calendar.get(Calendar.MONTH);
			if (mes<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 463, 528, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+mes), 478, 528, 0);	
			}else{
				String mesS = ""+mes;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(0, 1)), 463, 528, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(1, 2)), 478, 528, 0);
			}
			
			anio = calendar.get(Calendar.YEAR);
			anioS = ""+anio;
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(2, 3)), 494, 528, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(3, 4)), 510, 528, 0);
			
			calendar.setTimeInMillis(solicitudreferencia.getHora().getTime());
			hora = calendar.get(Calendar.HOUR_OF_DAY);
			if (hora<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 526, 528, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+hora), 541, 528, 0);	
			}else {
				String horaS = ""+hora;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(horaS.substring(0, 1)), 526, 528, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(horaS.substring(1, 2)), 541, 528, 0);
			}
			
			minutos = calendar.get(Calendar.MINUTE);
			if (minutos<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 557, 528, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutos), 572, 528, 0);			
			}else{
				String minutosS = ""+minutos;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutosS.substring(0, 1)), 557, 528, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutosS.substring(1, 2)), 572, 528, 0);				
			}
			
			String[] palabras = solicitudreferencia.getMotivoDelEnvio().split(" ");
			String[] renglones = new String[10];
			String aux = "";
			int j = 0;
			for (int i = 0; i < palabras.length; i++) {
				aux = aux + palabras[i] + " ";
				if (aux.length()>65) {
					renglones[j] = aux;
					aux = "";
					j++;
				}
			}
			if (aux.length()!=0) {
				renglones[j] = aux;
			}
			 
			int i = 480;
			for (String string : renglones) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(string), 12, i, 0);
				i=i-15;
			}			
			
			
			palabras = solicitudreferencia.getResultadosLaboratorio().split(" ");
			renglones = new String[10];
			aux = "";
			j = 0;
			for (i = 0; i < palabras.length; i++) {
				aux = aux + palabras[i] + " ";
				if (aux.length()>65) {
					renglones[j] = aux;
					aux = "";
					j++;
				}
			}
			if (aux.length()!=0) {
				renglones[j] = aux;
			}
			 
			i = 300;
			for (String string : renglones) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(string), 12, i, 0);
				i=i-12;
			}	
			
			Iterator<Licenciamedica> licenciaMedicaIterator = agenda.getAtencionmedica().getLicenciamedicas().iterator();
			Licenciamedica licenciamedica = null;
			while (licenciaMedicaIterator.hasNext()) {
				Licenciamedica licenciamedicaAux = (Licenciamedica) licenciaMedicaIterator.next();
				licenciamedica = licenciamedicaAux;
			}
			calendar.setTime(licenciamedica.getFechaInicio());
			
			dia = calendar.get(Calendar.DAY_OF_MONTH);
			if (dia<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 170, 225, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+dia), 185, 225, 0);
			}else {
				String diaS = ""+dia;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(0, 1)), 170, 225, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(1, 2)), 185, 225, 0);		
			}
			
			mes = calendar.get(Calendar.MONTH);
			if (mes<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 200, 225, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+mes), 215, 225, 0);	
			}else{
				String mesS = ""+mes;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(0, 1)), 200, 225, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(1, 2)), 215, 225, 0);
			}
			
			anio = calendar.get(Calendar.YEAR);
			anioS = ""+anio;
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(2, 3)), 230, 225, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(3, 4)), 245, 225, 0); 
			
			calendar.setTime(licenciamedica.getFechaTermino());
			
			dia = calendar.get(Calendar.DAY_OF_MONTH);
			if (dia<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 308, 225, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+dia), 323, 225, 0);
			}else {
				String diaS = ""+dia;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(0, 1)), 308, 225, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(1, 2)), 323, 225, 0);		
			}
			
			mes = calendar.get(Calendar.MONTH);
			if (mes<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 338, 225, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+mes), 353, 225, 0);	
			}else{
				String mesS = ""+mes;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(0, 1)), 338, 225, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(1, 2)), 353, 225, 0);
			}
			
			anio = calendar.get(Calendar.YEAR);
			anioS = ""+anio;
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(2, 3)), 368, 225, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(3, 4)), 383, 225, 0); 			
			
			if (solicitudreferencia.getCatsolirefrerenciapor().getReferenciaPorId() == 1) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 478, 225, 0);	
			}else{
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 572, 225, 0);	
			}
		
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre()+" "+agenda.getUsuarios().getApellidoPaterno()+" "+agenda.getUsuarios().getApellidoMaterno()), 13, 163, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno()), 397, 57, 0);
			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void solicitudContrareferencia(CertificadosForm certificadosForm) {
		certificadosForm.setFile(solicitudContrareferencia(certificadosForm.getIdAgenda()));
	}

	@Override
	public ByteArrayOutputStream solicitudContrareferencia(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = classLoader.getResource("/com/mx/sab/resources/solicitud_contrareferencia.png").getPath();
			Image image = Image.getInstance(path);
			
			float scaler = (float) (.914 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,20);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			Calendar calendar = Calendar.getInstance();
			int dia = calendar.get(Calendar.DAY_OF_MONTH);
			if (dia<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 433, 753, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+dia), 448, 753, 0);
			}else {
				String diaS = ""+dia;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(0, 1)), 433, 753, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diaS.substring(1, 2)), 448, 753, 0);				
			}
			
			int mes = calendar.get(Calendar.MONTH);
			if (mes<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 463, 753, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+mes), 478, 753, 0);	
			}else{
				String mesS = ""+mes;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(0, 1)), 463, 753, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(mesS.substring(1, 2)), 478, 753, 0);
			}
			
			int anio = calendar.get(Calendar.YEAR);
			String anioS = ""+anio;
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(2, 3)), 494, 753, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(anioS.substring(3, 4)), 510, 753, 0);

			int hora = calendar.get(Calendar.HOUR_OF_DAY);
			if (hora<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 526, 753, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+hora), 541, 753, 0);	
			}else {
				String horaS = ""+hora;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(horaS.substring(0, 1)), 526, 753, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(horaS.substring(1, 2)), 541, 753, 0);
			}
			
			int minutos = calendar.get(Calendar.MINUTE);
			if (minutos<10) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("0"), 557, 753, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutos), 572, 753, 0);				
			}else{
				String minutosS = ""+minutos;
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutosS.substring(0, 1)), 557, 753, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(minutosS.substring(1, 2)), 572, 753, 0);				
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getDescripcion()), 150, 720, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getLugaresdeatencion().getClaveDeLaUnidad()), 507, 720, 0);
			
			Iterator<Solicitudreferencia> solicitudReferenciaIterator = agenda.getAtencionmedica().getSolicitudreferencias().iterator();
			Solicitudreferencia solicitudreferencia = null;
			Contrareferencia contrareferencia = null;
			while (solicitudReferenciaIterator.hasNext()) {
				Solicitudreferencia solicitudreferenciaAux = (Solicitudreferencia) solicitudReferenciaIterator.next();
				solicitudreferencia = solicitudreferenciaAux;
				Iterator<Contrareferencia> contrareferenciaIterator = solicitudreferenciaAux.getContrareferencias().iterator();
				while (contrareferenciaIterator.hasNext()) {
					Contrareferencia contrareferenciaAux = (Contrareferencia) contrareferenciaIterator.next();
					contrareferencia = contrareferenciaAux;
				}
			}
					
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(contrareferencia.getMotivoContrareferencia()), 150, 696, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+contrareferencia.getNumInterConsultas()), 120, 673, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+contrareferencia.getNumConsultas()), 310, 673, 0);
			
			Iterator<Notamedica> notaMedicaIterator = agenda.getAtencionmedica().getNotamedicas().iterator();
			String diagnostico = "";
		    int idDiagnostico = 0;
			while (notaMedicaIterator.hasNext()) {
				Notamedica notamedica = (Notamedica) notaMedicaIterator.next();
				Iterator<NotamedicaCies10> notaMedicaCies10Iterator = notamedica.getNotamedicaCies10s().iterator();
				while (notaMedicaCies10Iterator.hasNext()) {
					NotamedicaCies10 notamedicaCies10 = (NotamedicaCies10) notaMedicaCies10Iterator.next();
					if (notamedicaCies10.getPrincipal() == 1) {
						diagnostico = notamedicaCies10.getCatcies10().getCodigo();
						idDiagnostico = notamedicaCies10.getCatcies10().getCie10id();
					}
				}
			}
			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(diagnostico), 495, 674, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(contrareferencia.getCatcies10().getCodigo()), 155, 650, 0);
			
			if (contrareferencia.getCatcies10().getCie10id() == idDiagnostico) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 540, 654, 0);	
			}else{
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 572, 654, 0);	
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getLugaresdeatencion().getDescripcion()), 190, 613, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getLugaresdeatencion().getClaveDeLaUnidad()), 510, 613, 0);
			
			String[] palabras = contrareferencia.getResultadosValoracion().split(" ");
			String[] renglones = new String[10];
			String aux = "";
			int j = 0;
			for (int i = 0; i < palabras.length; i++) {
				aux = aux + palabras[i] + " ";
				if (aux.length()>65) {
					renglones[j] = aux;
					aux = "";
					j++;
				}
			}
			if (aux.length()!=0) {
				renglones[j] = aux;
			}
			 
			int i = 565;
			for (String string : renglones) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(string), 12, i, 0);
				i=i-15;
			}
			
			palabras = contrareferencia.getIndicaciones().split(" ");
			renglones = new String[10];
			aux = "";
			j = 0;
			for (i = 0; i < palabras.length; i++) {
				aux = aux + palabras[i] + " ";
				if (aux.length()>65) {
					renglones[j] = aux;
					aux = "";
					j++;
				}
			}
			if (aux.length()!=0) {
				renglones[j] = aux;
			}
			 
			i = 242;
			for (String string : renglones) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(string), 12, i, 0);
				i=i-15;
			}			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre()+" "+agenda.getUsuarios().getApellidoPaterno()+" "+agenda.getUsuarios().getApellidoMaterno()), 13, 57, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre()+" "+agenda.getAfiliado().getApellidoPaterno()+" "+agenda.getAfiliado().getApellidoMaterno()), 413, 57, 0);
			
			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void mediaccessOrdenGabinete(CertificadosForm certificadosForm) {
		Agenda agenda = agendaDao.getagendaByIdAtencion(certificadosForm.getIdAtencion());
		certificadosForm.setFile(mediaccessOrdenGabinete(agenda.getAgendaId()));
	}

	private ByteArrayOutputStream mediaccessOrdenGabinete(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);
			PdfWriter.getInstance(document, baos);
			document.open();
			String path = null;
			try{
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				//log.info("3");
				path = classLoader.getResource("/com/mx/sab/resources/Mediaccess_Orden_de_gabinete.jpg").getPath();
				//log.info("path ="+path);				
			}catch(Exception ex){
				ex.printStackTrace();
				path = "/usr/local/share/apache-tomcat-8.0.33/webapps/sab/resources/img/Mediaccess_Orden_de_gabinete.jpg";
			}
			
			Image image = Image.getInstance(path);
			
			float scaler = (float) (.667 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,40);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 328, 523, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre() + " " + agenda.getUsuarios().getApellidoPaterno() + " " + agenda.getUsuarios().getApellidoMaterno()), 130, 485, 0);
			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = agenda.getUsuarios().getUsuarioespecialidadeses().iterator();
			Usuarioespecialidades usuarioespecialidades = null;
			while (usuarioEspecialidadesIterator.hasNext()) {
				usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				break;
			}
			if (usuarioespecialidades!=null) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedica()), 395, 486, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCedulaEspecialidad()), 182, 468, 0);
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getTelefono()), 370, 469, 0);
			
			Iterator<Usuariolugaratencion> usuarioLugarAtencionIterator = agenda.getUsuarios().getUsuariolugaratencions().iterator();
			while (usuarioLugarAtencionIterator.hasNext()) {
				Usuariolugaratencion usuariolugaratencion = (Usuariolugaratencion) usuarioLugarAtencionIterator.next();
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCalleyNo() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumInterno() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumExterno()  + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatcolonias().getColonia() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatestados().getEstado() + " cp:" + 
																				  usuariolugaratencion.getLugaresdeatencion().getCp()), 130, 451, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCatmunicipios().getMunicipio()), 510, 449, 0);
				break;
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre() + " " + agenda.getAfiliado().getApellidoPaterno() + " " + agenda.getAfiliado().getApellidoMaterno()), 128, 418, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCatsexos().getSexo()), 490, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime())), 588, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Empresa"), 128, 401, 0);
			Iterator<Afiliadotipoidentificador> afiliadoTipoIdentificadorIterator = agenda.getAfiliado().getAfiliadotipoidentificadors().iterator();
			while (afiliadoTipoIdentificadorIterator.hasNext()) {
				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) afiliadoTipoIdentificadorIterator.next();
				if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CODAFILIADO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipoidentificador.getTipoIdValor()), 432, 401, 0);	
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCattipoafiliado().getTipoAfiliado()), 588, 401, 0);
			
			Collection<Afiliadotipovalorasegurador> afiliadotipovaloraseguradors = agendaDao.getAfiliadoTipoValorAseguradorById(agenda.getAfiliado().getAfiliadoId());
			for (Afiliadotipovalorasegurador afiliadotipovalorasegurador : afiliadotipovaloraseguradors) {
				if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.POLIZA.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 382, 0);	
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PLAN.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 222, 382, 0);	
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PRODUCTO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 450, 382, 0);	
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PYME.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 364, 0);	
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 490, 364, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(FormatUtil.getDate(agenda.getFechaCita())), 720, 381, 0);
			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Id Sucursal"), 50, 280, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Nombre o Razon social"), 178, 280, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Fecha"), 428, 280, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 560, 280, 0);
			
			int y = 220;
			Iterator<Prestacionesportomar> prestacionPorTomarIterator = agenda.getAtencionmedica().getPrestacionesportomarsForAtencionMedicaId().iterator();
			while (prestacionPorTomarIterator.hasNext()) {
				Prestacionesportomar prestacionesportomar = (Prestacionesportomar) prestacionPorTomarIterator.next();
				if (prestacionesportomar.getCatpaginasestudios().getIdPagina() == CatTipoServicioEnum.GABINETE.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesportomar.getOrden()), 720, 361, 0);
					Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = prestacionesportomar.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
					while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
						Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
						if(prestacionesaseguradorequivalencias.getPrestacionasegurador().getAseguradores().getAseguradorId() == agenda.getAseguradores().getAseguradorId()){
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesaseguradorequivalencias.getPrestacionasegurador().getCodigo()), 45, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesaseguradorequivalencias.getPrestacionasegurador().getDescripcion()), 100, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("CPT"), 300, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Precio"), 344, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("IVA"), 418, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Total"), 480, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 534, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 600, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Resta"), 670, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Observaciones"), 720, y, 0);	
							y = y-36;
						} 
					}
				}
			
			}

			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void mediaccessOrdenLaboratorio(CertificadosForm certificadosForm) {
		//log.info("certificadosForm.getIdAtencion()" + certificadosForm.getIdAtencion());
		Agenda agenda = agendaDao.getagendaByIdAtencion(certificadosForm.getIdAtencion());
		//log.info("agenda.getAgendaId()" + agenda.getAgendaId());
		certificadosForm.setFile(mediaccessOrdenLaboratorio(agenda.getAgendaId()));
	}

	private ByteArrayOutputStream mediaccessOrdenLaboratorio(int idAgenda) {
		try{
			//log.info("1");
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			//log.info("2");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);
			PdfWriter.getInstance(document, baos);
			document.open();
			String path = null;
			try{
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				//log.info("3");
				path = classLoader.getResource("/com/mx/sab/resources/Mediaccess_Orden_de_laboratorio.jpg").getPath();
				//log.info("path ="+path);				
			}catch(Exception ex){
				ex.printStackTrace();
				path = "/usr/local/share/apache-tomcat-8.0.33/webapps/sab/resources/img/Mediaccess_Orden_de_laboratorio.jpg";
			}

			Image image = Image.getInstance(path);
			//log.info("image ="+image);
			//log.info("4");
			float scaler = (float) (.667 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,40);
			document.add(image);
			document.close();
			//log.info("5");
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 328, 523, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre() + " " + agenda.getUsuarios().getApellidoPaterno() + " " + agenda.getUsuarios().getApellidoMaterno()), 130, 485, 0);
			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = agenda.getUsuarios().getUsuarioespecialidadeses().iterator();
			Usuarioespecialidades usuarioespecialidades = null;
			while (usuarioEspecialidadesIterator.hasNext()) {
				usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				break;
			}
			if (usuarioespecialidades!=null) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedica()), 395, 486, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCedulaEspecialidad()), 182, 468, 0);
			}
			//log.info("6");
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getTelefono()), 370, 469, 0);
			
			Iterator<Usuariolugaratencion> usuarioLugarAtencionIterator = agenda.getUsuarios().getUsuariolugaratencions().iterator();
			while (usuarioLugarAtencionIterator.hasNext()) {
				Usuariolugaratencion usuariolugaratencion = (Usuariolugaratencion) usuarioLugarAtencionIterator.next();
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCalleyNo() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumInterno() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumExterno()  + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatcolonias().getColonia() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatestados().getEstado() + " cp:" + 
																				  usuariolugaratencion.getLugaresdeatencion().getCp()), 130, 451, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCatmunicipios().getMunicipio()), 510, 449, 0);
				break;
			}
			//log.info("7");
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre() + " " + agenda.getAfiliado().getApellidoPaterno() + " " + agenda.getAfiliado().getApellidoMaterno()), 128, 418, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCatsexos().getSexo()), 490, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime())), 588, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Empresa"), 128, 401, 0);
			Iterator<Afiliadotipoidentificador> afiliadoTipoIdentificadorIterator = agenda.getAfiliado().getAfiliadotipoidentificadors().iterator();
			while (afiliadoTipoIdentificadorIterator.hasNext()) {
				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) afiliadoTipoIdentificadorIterator.next();
				if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CODAFILIADO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipoidentificador.getTipoIdValor()), 432, 401, 0);	
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCattipoafiliado().getTipoAfiliado()), 588, 401, 0);
			
			Collection<Afiliadotipovalorasegurador> afiliadotipovaloraseguradors = agendaDao.getAfiliadoTipoValorAseguradorById(agenda.getAfiliado().getAfiliadoId());
			for (Afiliadotipovalorasegurador afiliadotipovalorasegurador : afiliadotipovaloraseguradors) {
				if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.POLIZA.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 382, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PLAN.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 222, 382, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PRODUCTO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 450, 382, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PYME.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 364, 0);
				}
			}			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 490, 364, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(FormatUtil.getDate(agenda.getFechaCita())), 720, 381, 0);
			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Id Sucursal"), 50, 280, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Nombre o Razon social"), 178, 280, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Fecha"), 428, 280, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 560, 280, 0);
			//log.info("8");
			int y = 220;
			Iterator<Prestacionesportomar> prestacionPorTomarIterator = agenda.getAtencionmedica().getPrestacionesportomarsForAtencionMedicaId().iterator();
			while (prestacionPorTomarIterator.hasNext()) {
				Prestacionesportomar prestacionesportomar = (Prestacionesportomar) prestacionPorTomarIterator.next();
				if (prestacionesportomar.getCatpaginasestudios().getIdPagina() == CatTipoServicioEnum.LABORATORIO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesportomar.getOrden()), 720, 361, 0);
					Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = prestacionesportomar.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
					while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
						Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
						if(prestacionesaseguradorequivalencias.getPrestacionasegurador().getAseguradores().getAseguradorId() == agenda.getAseguradores().getAseguradorId()){
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesaseguradorequivalencias.getPrestacionasegurador().getCodigo()), 45, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesaseguradorequivalencias.getPrestacionasegurador().getDescripcion()), 100, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("CPT"), 300, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Precio"), 344, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("IVA"), 418, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Total"), 480, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 534, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 600, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Resta"), 670, y, 0);
							ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Observaciones"), 720, y, 0);
							y = y-36;	
						} 
					}
				}
			}
			//log.info("9");
			stamper.close();
			//log.info("10");
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void mediaccessAtencionConsultorio(CertificadosForm certificadosForm) {
		Agenda agenda = agendaDao.getagendaByIdAtencion(certificadosForm.getIdAtencion());
		certificadosForm.setFile(mediaccessAtencionConsultorio(agenda.getAgendaId()));		
	}

	private ByteArrayOutputStream mediaccessAtencionConsultorio(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			String path = null;
			try{
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				//log.info("3");
				path = classLoader.getResource("/com/mx/sab/resources/Mediaccess_Atención_consultorio.jpg").getPath();
				//log.info("path ="+path);				
			}catch(Exception ex){
				ex.printStackTrace();
				path = "/usr/local/share/apache-tomcat-8.0.33/webapps/sab/resources/img/Mediaccess_Atención_consultorio.jpg";
			}			
			Image image = Image.getInstance(path);
			
			float scaler = (float) (.450 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,70);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 342, 686, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre() + " " + agenda.getUsuarios().getApellidoPaterno() + " " + agenda.getUsuarios().getApellidoMaterno()), 90, 653, 0);

			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = agenda.getUsuarios().getUsuarioespecialidadeses().iterator();
			Usuarioespecialidades usuarioespecialidades = null;
			while (usuarioEspecialidadesIterator.hasNext()) {
				usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				break;
			}
			if (usuarioespecialidades!=null) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedica()), 105, 638, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCedulaEspecialidad()), 340, 638, 0);
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getTelefono()), 283, 606, 0);
			
			Iterator<Usuariolugaratencion> usuarioLugarAtencionIterator = agenda.getUsuarios().getUsuariolugaratencions().iterator();
			while (usuarioLugarAtencionIterator.hasNext()) {
				Usuariolugaratencion usuariolugaratencion = (Usuariolugaratencion) usuarioLugarAtencionIterator.next();
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCalleyNo() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumInterno() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumExterno()  + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatcolonias().getColonia() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatestados().getEstado() + " cp:" + 
																				  usuariolugaratencion.getLugaresdeatencion().getCp()), 90, 622, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCatmunicipios().getMunicipio()), 80, 606, 0);
				break;
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre() + " " + agenda.getAfiliado().getApellidoPaterno() + " " + agenda.getAfiliado().getApellidoMaterno()), 88, 570, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCatsexos().getSexo()), 392, 570, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime())), 480, 570, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Empresa"), 88, 554, 0);
			Iterator<Afiliadotipoidentificador> afiliadoTipoIdentificadorIterator = agenda.getAfiliado().getAfiliadotipoidentificadors().iterator();
			while (afiliadoTipoIdentificadorIterator.hasNext()) {
				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) afiliadoTipoIdentificadorIterator.next();
				if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CODAFILIADO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipoidentificador.getTipoIdValor()), 348, 554, 0);	
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCattipoafiliado().getTipoAfiliado()), 480, 554, 0);			
			
			Collection<Afiliadotipovalorasegurador> afiliadotipovaloraseguradors = agendaDao.getAfiliadoTipoValorAseguradorById(agenda.getAfiliado().getAfiliadoId());
			for (Afiliadotipovalorasegurador afiliadotipovalorasegurador : afiliadotipovaloraseguradors) {
				if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.POLIZA.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 72, 538, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PLAN.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 170, 538, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PRODUCTO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 361, 538, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PYME.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 72, 523, 0);
				}
			}
			
			Iterator<Signosvitales> signosVitalesIterator = agenda.getAtencionmedica().getSignosvitaleses().iterator();
			while (signosVitalesIterator.hasNext()) {
				Signosvitales signosvitales = (Signosvitales) signosVitalesIterator.next();
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+signosvitales.getPeso()), 93, 494, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+signosvitales.getCintura()), 179, 494, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+signosvitales.getTensionArterial()), 272, 494, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+signosvitales.getTemperatura()), 439, 494, 0);
				
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+signosvitales.getSaturacionOxigeno()), 87, 476, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+signosvitales.getImc()), 178, 476, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+signosvitales.getPulso()), 310, 476, 0);				
			}

			
			int y = 430;
			Iterator<Notamedica> notaMedicaIterator = agenda.getAtencionmedica().getNotamedicas().iterator();
			while (notaMedicaIterator.hasNext()) {
				Notamedica notamedica = (Notamedica) notaMedicaIterator.next();
				Iterator<NotamedicaCies10> notaMedicaCies10Iterator = notamedica.getNotamedicaCies10s().iterator();
				while (notaMedicaCies10Iterator.hasNext()) {
					NotamedicaCies10 notamedicaCies10 = (NotamedicaCies10) notaMedicaCies10Iterator.next();
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(notamedicaCies10.getCatcies10().getCodigo()), 70, y, 0);
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(notamedicaCies10.getCatcies10().getDescripcion()), 121, y, 0);
					y = y - 17;					
				}
				
				
			}
			
			y = 348;			
			Iterator<Prestacionesportomar> prestacionesportomarsIterator = agenda.getAtencionmedica().getPrestacionesportomarsForAtencionMedicaId().iterator();
			while (prestacionesportomarsIterator.hasNext()) {
				Prestacionesportomar prestacionesportomar = (Prestacionesportomar) prestacionesportomarsIterator.next();
				if (prestacionesportomar.getCatpaginasestudios().getIdPagina() == CatTipoServicioEnum.CONSULTORIO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesportomar.getCatprestacion().getDescripcion()), 70, y, 0);
					y = y - 17;	
				}
			}
			
			Iterator<Atencionprestacion> atencionPrestacionIterator = agenda.getAtencionmedica().getAtencionprestacions().iterator();
			y = 250;
			while (atencionPrestacionIterator.hasNext()) {
				Atencionprestacion atencionPrestacion = (Atencionprestacion) atencionPrestacionIterator.next();
				Iterator<Prestacionesaseguradorequivalencias> prestacionesAseguradorEquivalenciasIterator = atencionPrestacion.getCatprestacion().getPrestacionesaseguradorequivalenciases().iterator();
				while (prestacionesAseguradorEquivalenciasIterator.hasNext()) {
					Prestacionesaseguradorequivalencias prestacionesaseguradorequivalencias = (Prestacionesaseguradorequivalencias) prestacionesAseguradorEquivalenciasIterator.next();
					if(prestacionesaseguradorequivalencias.getPrestacionasegurador().getAseguradores().getAseguradorId() == agenda.getAseguradores().getAseguradorId()){
						ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesaseguradorequivalencias.getPrestacionasegurador().getCodigo()), 75, y, 0);
						ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(prestacionesaseguradorequivalencias.getPrestacionasegurador().getDescripcion()), 120, y, 0);
						ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+atencionPrestacion.getCantidad()), 330, y, 0);
						ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+atencionPrestacion.getValorPrestacionConvenio()), 370, y, 0);
						ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+atencionPrestacion.getAporteAsegurador()), 415, y, 0);
						ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+atencionPrestacion.getCopagoAfiliado()), 461, y, 0);
						y = y - 12;	
					} 
				}
			}			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(FormatUtil.getDate(agenda.getFechaCita())), 176, 115, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAtencionmedica().getFolio()), 340, 115, 0);

			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void mediaccessRecetaMedica(CertificadosForm certificadosForm) {
		Agenda agenda = agendaDao.getagendaByIdAtencion(certificadosForm.getIdAtencion());
		certificadosForm.setFile(mediaccessRecetaMedica(agenda.getAgendaId()));	
		
	}

	private ByteArrayOutputStream mediaccessRecetaMedica(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);
			PdfWriter.getInstance(document, baos);
			document.open();
			String path = null;
			try{
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				//log.info("3");
				path = classLoader.getResource("/com/mx/sab/resources/Mediaccess_Receta_medica.jpg").getPath();
				//log.info("path ="+path);				
			}catch(Exception ex){
				ex.printStackTrace();
				path = "/usr/local/share/apache-tomcat-8.0.33/webapps/sab/resources/img/Mediaccess_Receta_medica.jpg";
			}						
			Image image = Image.getInstance(path);
			
			float scaler = (float) (.667 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,40);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 328, 523, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre() + " " + agenda.getUsuarios().getApellidoPaterno() + " " + agenda.getUsuarios().getApellidoMaterno()), 130, 485, 0);

			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = agenda.getUsuarios().getUsuarioespecialidadeses().iterator();
			Usuarioespecialidades usuarioespecialidades = null;
			while (usuarioEspecialidadesIterator.hasNext()) {
				usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				break;
			}
			if (usuarioespecialidades!=null) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedica()), 155, 468, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCedulaEspecialidad()), 430, 468, 0);
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getTelefono()), 370, 430, 0);
			
			Iterator<Usuariolugaratencion> usuarioLugarAtencionIterator = agenda.getUsuarios().getUsuariolugaratencions().iterator();
			while (usuarioLugarAtencionIterator.hasNext()) {
				Usuariolugaratencion usuariolugaratencion = (Usuariolugaratencion) usuarioLugarAtencionIterator.next();
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCalleyNo() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumInterno() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumExterno()  + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatcolonias().getColonia() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatestados().getEstado() + " cp:" + 
																				  usuariolugaratencion.getLugaresdeatencion().getCp()), 130, 450, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCatmunicipios().getMunicipio()), 130, 430, 0);
				break;
			}			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre() + " " + agenda.getAfiliado().getApellidoPaterno() + " " + agenda.getAfiliado().getApellidoMaterno()), 128, 398, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCatsexos().getSexo()), 490, 398, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime())), 588, 398, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Empresa"), 128, 378, 0);
			Iterator<Afiliadotipoidentificador> afiliadoTipoIdentificadorIterator = agenda.getAfiliado().getAfiliadotipoidentificadors().iterator();
			while (afiliadoTipoIdentificadorIterator.hasNext()) {
				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) afiliadoTipoIdentificadorIterator.next();
				if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CODAFILIADO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipoidentificador.getTipoIdValor()), 440, 378, 0);	
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCattipoafiliado().getTipoAfiliado()), 588, 378, 0);
			
			Collection<Afiliadotipovalorasegurador> afiliadotipovaloraseguradors = agendaDao.getAfiliadoTipoValorAseguradorById(agenda.getAfiliado().getAfiliadoId());
			for (Afiliadotipovalorasegurador afiliadotipovalorasegurador : afiliadotipovaloraseguradors) {
				if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.POLIZA.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 360, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PLAN.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 223, 360, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PRODUCTO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 450, 360, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PYME.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 341, 0);
				}
			}			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 490, 341, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(FormatUtil.getDate(agenda.getFechaCita())), 720, 298, 0);
			
			
			Iterator<Recetas> recetasIterator = agenda.getAtencionmedica().getRecetases().iterator();
			int y = 288;
			while (recetasIterator.hasNext()) {
				Recetas recetas = (Recetas) recetasIterator.next();
				Iterator<Medicamentosreceta> medicamentoRecetaIterator = recetas.getMedicamentosrecetas().iterator();
				while (medicamentoRecetaIterator.hasNext()) {
					Medicamentosreceta medicamentosreceta = (Medicamentosreceta) medicamentoRecetaIterator.next();
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(medicamentosreceta.getFolio()), 720, 265, 0);
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(medicamentosreceta.getCatmedicamentos().getMedicamento()), 70, y, 0);
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(medicamentosreceta.getCantidadUnidades() + " " + 
																					  medicamentosreceta.getCatviasdeadminmedicamento().getDescripcion() + " " + 
																					  medicamentosreceta.getDosisNo() + " " + 
																					  medicamentosreceta.getDosis() + " " + 
																					  medicamentosreceta.getFrecuenciaDeToma() + " " + 
																					  medicamentosreceta.getCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId().getUnidadTiempo() + " " + 
																					  medicamentosreceta.getDuracionDeToma() + " " + 
																					  medicamentosreceta.getCatunidadesdetiempoByDuracionUnidadDeTiempoId().getUnidadTiempo()), 415, y, 0);
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(medicamentosreceta.getObservaciones()), 530, y, 0);
					y = y - 15;
				}
			}
			
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("IdSucursal"), 110, 205, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Precio"), 280, 205, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Unitario"), 372, 205, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Descuento"), 480, 205, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Monto"), 650, 205, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("I.V.A."), 730, 205, 0);
//			
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Precio Total"), 110, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 310, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 490, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Resta"), 690, 160, 0);
//			
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("NoUnidades"), 95, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("NoUnidades"), 164, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 265, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 300, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 335, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 367, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 399, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 430, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 465, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 498, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 530, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 565, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 598, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 630, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 665, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 698, 117, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("X"), 730, 117, 0);
//			
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Fecha de surtido"), 110, 73, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de Autorizacion"), 360, 73, 0);

			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public void mediaccessOtrosApoyos(CertificadosForm certificadosForm) {
		Agenda agenda = agendaDao.getagendaByIdAtencion(certificadosForm.getIdAtencion());
		certificadosForm.setFile(mediaccessOtrosApoyos(agenda.getAgendaId()));	
	}

	private ByteArrayOutputStream mediaccessOtrosApoyos(int idAgenda) {
		try{
			Agenda agenda = agendaDao.getAgendaById(idAgenda);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);
			PdfWriter.getInstance(document, baos);
			document.open();
			String path = null;
			try{
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				//log.info("3");
				path = classLoader.getResource("/com/mx/sab/resources/Mediaccess_Otros_apoyos.jpg").getPath();
				//log.info("path ="+path);				
			}catch(Exception ex){
				ex.printStackTrace();
				path = "/usr/local/share/apache-tomcat-8.0.33/webapps/sab/resources/img/Mediaccess_Otros_apoyos.jpg";
			}						
			Image image = Image.getInstance(path);
			
			float scaler = (float) (.667 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,40);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);

			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 328, 525, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre() + " " + agenda.getUsuarios().getApellidoPaterno() + " " + agenda.getUsuarios().getApellidoMaterno()), 130, 488, 0);

			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = agenda.getUsuarios().getUsuarioespecialidadeses().iterator();
			Usuarioespecialidades usuarioespecialidades = null;
			while (usuarioEspecialidadesIterator.hasNext()) {
				usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				break;
			}
			if (usuarioespecialidades!=null) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedica()), 395, 488, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCedulaEspecialidad()), 182, 470, 0);
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getTelefono()), 370, 470, 0);
			
			Iterator<Usuariolugaratencion> usuarioLugarAtencionIterator = agenda.getUsuarios().getUsuariolugaratencions().iterator();
			while (usuarioLugarAtencionIterator.hasNext()) {
				Usuariolugaratencion usuariolugaratencion = (Usuariolugaratencion) usuarioLugarAtencionIterator.next();
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCalleyNo() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumInterno() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumExterno()  + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatcolonias().getColonia() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatestados().getEstado() + " cp:" + 
																				  usuariolugaratencion.getLugaresdeatencion().getCp()), 130, 452, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCatmunicipios().getMunicipio()), 510, 452, 0);
				break;
			}				
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre() + " " + agenda.getAfiliado().getApellidoPaterno() + " " + agenda.getAfiliado().getApellidoMaterno()), 128, 421, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCatsexos().getSexo()), 490, 421, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime())), 589, 421, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Empresa"), 128, 403, 0);
			Iterator<Afiliadotipoidentificador> afiliadoTipoIdentificadorIterator = agenda.getAfiliado().getAfiliadotipoidentificadors().iterator();
			while (afiliadoTipoIdentificadorIterator.hasNext()) {
				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) afiliadoTipoIdentificadorIterator.next();
				if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CODAFILIADO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipoidentificador.getTipoIdValor()), 438, 403, 0);	
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCattipoafiliado().getTipoAfiliado()), 588, 403, 0);			
			
			Collection<Afiliadotipovalorasegurador> afiliadotipovaloraseguradors = agendaDao.getAfiliadoTipoValorAseguradorById(agenda.getAfiliado().getAfiliadoId());
			for (Afiliadotipovalorasegurador afiliadotipovalorasegurador : afiliadotipovaloraseguradors) {
				if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.POLIZA.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 384, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PLAN.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 223, 384, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PRODUCTO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 450, 384, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PYME.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 365, 0);
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 490, 365, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(FormatUtil.getDate(agenda.getFechaCita())), 720, 384, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAtencionmedica().getFolio()), 720, 364, 0);			
			
			Iterator<Notamedica> notaMedicaIterator = agenda.getAtencionmedica().getNotamedicas().iterator();
			while (notaMedicaIterator.hasNext()) {
				Notamedica notamedica = (Notamedica) notaMedicaIterator.next();
				Iterator<NotamedicaCies10> notaMedicaCies10Iterator = notamedica.getNotamedicaCies10s().iterator();
				while (notaMedicaCies10Iterator.hasNext()) {
					NotamedicaCies10 notamedicaCies10 = (NotamedicaCies10) notaMedicaCies10Iterator.next();
					if (notamedicaCies10.getPrincipal()==1) {
						ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(notamedicaCies10.getCatcies10().getDescripcion()), 126, 324, 0);	
					}
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Prestacion"), 382, 300, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Observaciones"), 608, 300, 0);
			
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("IdSucursal"), 125, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Precio"), 255, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("I.V.A."), 350, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Total"), 450, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 535, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 635, 160, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Resta"), 735, 160, 0);
//			
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Nombre"), 110, 100, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Fecha"), 420, 100, 0);
//			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 590, 100, 0);

			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void mediaccessSolicitudReferencia(CertificadosForm certificadosForm) {
		Agenda agenda = agendaDao.getagendaByIdAtencion(certificadosForm.getIdAtencion());
		certificadosForm.setFile(mediaccessSolicitudReferencia(agenda.getAgendaId()));	
	}

	private ByteArrayOutputStream mediaccessSolicitudReferencia(Integer agendaId) {
		try{
			Agenda agenda = agendaDao.getAgendaById(agendaId);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);
			PdfWriter.getInstance(document, baos);
			document.open();
			String path = null;
			try{
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				//log.info("3");
				path = classLoader.getResource("/com/mx/sab/resources/Mediaccess_Solicitud_de_referencia.jpg").getPath();
				//log.info("path ="+path);				
			}catch(Exception ex){
				ex.printStackTrace();
				path = "/usr/local/share/apache-tomcat-8.0.33/webapps/sab/resources/img/Mediaccess_Solicitud_de_referencia.jpg";
			}						
			Image image = Image.getInstance(path);
			
			float scaler = (float) (.667 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,40);
			document.add(image);
			document.close();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, baos2);
			PdfContentByte canvas = stamper.getOverContent(1);
					
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 328, 525, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getNombre() + " " + agenda.getUsuarios().getApellidoPaterno() + " " + agenda.getUsuarios().getApellidoMaterno()), 130, 486, 0);
			Iterator<Usuarioespecialidades> usuarioEspecialidadesIterator = agenda.getUsuarios().getUsuarioespecialidadeses().iterator();
			Usuarioespecialidades usuarioespecialidades = null;
			while (usuarioEspecialidadesIterator.hasNext()) {
				usuarioespecialidades = (Usuarioespecialidades) usuarioEspecialidadesIterator.next();
				break;
			}
			if (usuarioespecialidades!=null) {
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCatespecialidadesmedicas().getEspecialidadMedica()), 395, 486, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuarioespecialidades.getCedulaEspecialidad()), 182, 469, 0);
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getUsuarios().getTelefono()), 370, 469, 0);
			
			Iterator<Usuariolugaratencion> usuarioLugarAtencionIterator = agenda.getUsuarios().getUsuariolugaratencions().iterator();
			while (usuarioLugarAtencionIterator.hasNext()) {
				Usuariolugaratencion usuariolugaratencion = (Usuariolugaratencion) usuarioLugarAtencionIterator.next();
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCalleyNo() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumInterno() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getNumExterno()  + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatcolonias().getColonia() + " " + 
																				  usuariolugaratencion.getLugaresdeatencion().getCatestados().getEstado() + " cp:" + 
																				  usuariolugaratencion.getLugaresdeatencion().getCp()), 130, 450, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(usuariolugaratencion.getLugaresdeatencion().getCatmunicipios().getMunicipio()), 510, 450, 0);
				break;
			}			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getNombre() + " " + agenda.getAfiliado().getApellidoPaterno() + " " + agenda.getAfiliado().getApellidoMaterno()), 128, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCatsexos().getSexo()), 490, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""+FormatUtil.getEdad(agenda.getAfiliado().getFechaDeNacimiento().getTime())), 589, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Empresa"), 128, 401, 0);
			Iterator<Afiliadotipoidentificador> afiliadoTipoIdentificadorIterator = agenda.getAfiliado().getAfiliadotipoidentificadors().iterator();
			while (afiliadoTipoIdentificadorIterator.hasNext()) {
				Afiliadotipoidentificador afiliadotipoidentificador = (Afiliadotipoidentificador) afiliadoTipoIdentificadorIterator.next();
				if (afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId() == TipoIdentificadorEnum.CODAFILIADO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipoidentificador.getTipoIdValor()), 438, 401, 0);	
				}
			}
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(agenda.getAfiliado().getCattipoafiliado().getTipoAfiliado()), 588, 401, 0);
			
			Collection<Afiliadotipovalorasegurador> afiliadotipovaloraseguradors = agendaDao.getAfiliadoTipoValorAseguradorById(agenda.getAfiliado().getAfiliadoId());
			for (Afiliadotipovalorasegurador afiliadotipovalorasegurador : afiliadotipovaloraseguradors) {
				if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.POLIZA.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 382, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PLAN.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 223, 382, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PRODUCTO.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 450, 382, 0);
				}else if (afiliadotipovalorasegurador.getId().getTipoValorAseguradorId() == CatTipoValorAseguradorEnum.PYME.getId()) {
					ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(afiliadotipovalorasegurador.getId().getTipoIdValor()), 110, 364, 0);
				}
			}			
			
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 490, 364, 0);
						
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(FormatUtil.getDate(agenda.getFechaCita())), 720, 380, 0);
			
			
			Iterator<Notamedica> notaMedicaIterator = agenda.getAtencionmedica().getNotamedicas().iterator();
			while (notaMedicaIterator.hasNext()) {
				Notamedica notamedica = (Notamedica) notaMedicaIterator.next();
				Iterator<NotamedicaCies10> notaMedicaCies10Iterator = notamedica.getNotamedicaCies10s().iterator();
				while (notaMedicaCies10Iterator.hasNext()) {
					NotamedicaCies10 notamedicaCies10 = (NotamedicaCies10) notaMedicaCies10Iterator.next();
					if (notamedicaCies10.getPrincipal()==1) {
						ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(notamedicaCies10.getCatcies10().getDescripcion()), 126, 321, 0);	
					}
				}
			}
			
			Iterator<Solicitudreferencia> solicitudReferenciaIterator = agenda.getAtencionmedica().getSolicitudreferencias().iterator();
			while (solicitudReferenciaIterator.hasNext()) {
				Solicitudreferencia solicitudreferencia = (Solicitudreferencia) solicitudReferenciaIterator.next();
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getFolio()), 720, 361, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getMotivoReferencia()), 179, 247, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getCatespecialidadesmedicas().getEspecialidadMedica()), 188, 174, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(""), 126, 100, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getLugaresdeatencion().getDescripcion()), 545, 321, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getMotivoDelEnvio()), 385, 235, 0);
				ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(solicitudreferencia.getResultadosLaboratorio()), 385, 122, 0);
			}

			stamper.close();
			return baos2;
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public void doMerge(List<ByteArrayOutputStream> list, CertificadosForm certificadosForm){
    	try {
    		Document document = new Document();
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos2);
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            
            for (ByteArrayOutputStream baos : list) {
                PdfReader reader = new PdfReader(baos.toByteArray());
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    document.newPage();
                    PdfImportedPage page = writer.getImportedPage(reader, i);
                    cb.addTemplate(page, 0, 0);
                }
            }
            baos2.flush();
            document.close();
            baos2.close();
            certificadosForm.setFile(baos2);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	
}
