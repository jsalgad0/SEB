package com.mx.sab.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.mx.sab.form.AtencionTerminarForm;
import com.mx.sab.form.CertificadosForm;
import com.mx.sab.form.ConstanciaAsistenciaForm;
import com.mx.sab.form.ConstanciaCuidadosMaternalesForm;
import com.mx.sab.form.ConstanciaSaludForm;
import com.mx.sab.form.validator.ConstanciaAsistenciaValidator;
import com.mx.sab.form.validator.ConstanciaCuidadosMaternalesFormValidator;
import com.mx.sab.form.validator.ConstanciaSaludValidator;
import com.mx.sab.service.ICertificadosService;

@Controller
@Log4j2
@RequestMapping("/certificados")
public class CertificadosController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ICertificadosService certificadosService;
	
	@Autowired
	private HttpServletResponse response;
	
	@InitBinder("constanciaAsistenciaForm")
	protected void constanciaAsistenciaFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new ConstanciaAsistenciaValidator());
	}		
	
	@InitBinder("constanciaSaludForm")
	protected void constanciaSaludFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new ConstanciaSaludValidator());
	}		
	
	@InitBinder("constanciaCuidadosMaternalesForm")
	protected void constanciaCuidadosMaternalesFormBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new ConstanciaCuidadosMaternalesFormValidator());
	}		
	
	@RequestMapping(value = "/certificados")
	public ModelAndView certificados(@RequestParam(value = "idAgenda") int idAgenda) {
		//log.info("certificados");
		ModelAndView model = new ModelAndView();
		CertificadosForm certificadosForm = new CertificadosForm();
		certificadosForm.setIdAgenda(idAgenda);
		certificadosService.inicializaFrom(certificadosForm);
		model.addObject("certificadosForm", certificadosForm);
		model.setViewName("certificados");
		return model; 
	}
	
	@RequestMapping(value = "/constanciaDeAsistencia")
	public ModelAndView constanciaDeAsistencia(ConstanciaAsistenciaForm constanciaAsistenciaForm) {
		//log.info("constanciaDeAsistencia");
		ModelAndView model = new ModelAndView();
		certificadosService.inicializaConstanciaDeAsistencia(constanciaAsistenciaForm);
		model.addObject("constanciaAsistenciaForm", constanciaAsistenciaForm);
		model.setViewName("constanciaDeAsistencia");		
		return model;
	}
	
	@RequestMapping(value = "/constanciaDeSalud")
	public ModelAndView constanciaDeSalud(ConstanciaSaludForm constanciaSaludForm) {
		//log.info("constanciaDeSalud");
		ModelAndView model = new ModelAndView();
		certificadosService.inicializaConstanciaDeSalud(constanciaSaludForm);
		model.addObject("constanciaSaludForm", constanciaSaludForm);
		model.setViewName("constanciaDeSalud");		
		return model;
	}	
	
	@RequestMapping(value = "/constanciaCuidadosMaternales")
	public ModelAndView constanciaCuidadosMaternales(ConstanciaCuidadosMaternalesForm constanciaCuidadosMaternalesForm) {
		//log.info("constanciaCuidadosMaternales");
		ModelAndView model = new ModelAndView();
		model.addObject("constanciaCuidadosMaternalesForm", constanciaCuidadosMaternalesForm);
		model.setViewName("constanciaCuidadosMaternales");		
		return model;
	}
	
	@RequestMapping(value = "/imprimirConstanciaDeAsistencia")
	public ModelAndView imprimirConstanciaDeAsistencia(@Valid ConstanciaAsistenciaForm constanciaAsistenciaForm, BindingResult result) {
		//log.info("imprimirConstanciaDeAsistencias");
		ModelAndView model = null;
		try{
			if (!result.hasErrors()) {
				certificadosService.constanciaDeAsistencia(constanciaAsistenciaForm);
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=ConstanciaDeAsistencia.pdf");
				response.setContentLength(constanciaAsistenciaForm.getFile().size());
				OutputStream responseOutputStream = response.getOutputStream();
				responseOutputStream.write(constanciaAsistenciaForm.getFile().toByteArray());	
			}else {
				model = new ModelAndView("constanciaDeAsistencia");
				model.addObject("constanciaAsistenciaForm", constanciaAsistenciaForm);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirConstanciaDeSalud")
	public ModelAndView imprimirConstanciaDeSalud(@Valid ConstanciaSaludForm constanciaSaludForm, BindingResult result) {
		//log.info("imprimirConstanciaDeSalud");
		ModelAndView model = null;
		try{
			if (!result.hasErrors()) {
				certificadosService.constanciaDeSalud(constanciaSaludForm);
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=ConstanciaDeSalud.pdf");
				response.setContentLength(constanciaSaludForm.getFile().size());
				OutputStream responseOutputStream = response.getOutputStream();
				responseOutputStream.write(constanciaSaludForm.getFile().toByteArray());	
			}else {
				model = new ModelAndView("constanciaDeSalud");
				model.addObject("constanciaSaludForm", constanciaSaludForm);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirConstanciaCuidadosMaternales")
	public ModelAndView imprimirConstanciaCuidadosMaternales(@Valid ConstanciaCuidadosMaternalesForm constanciaCuidadosMaternalesForm, BindingResult result) {
		//log.info("imprimirConstanciaDeSalud");
		ModelAndView model = null;
		try{
			if (!result.hasErrors()) {
				certificadosService.constanciaCuidadosMaternales(constanciaCuidadosMaternalesForm);
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=constanciaCuidadosMaternales.pdf");
				response.setContentLength(constanciaCuidadosMaternalesForm.getFile().size());
				OutputStream responseOutputStream = response.getOutputStream();
				responseOutputStream.write(constanciaCuidadosMaternalesForm.getFile().toByteArray());	
			}else {
				model = new ModelAndView("constanciaCuidadosMaternales");
				model.addObject("constanciaCuidadosMaternalesForm", constanciaCuidadosMaternalesForm);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirRecetaMedica")
	public ModelAndView imprimirRecetaMedica(CertificadosForm certificadosForm) {
		//log.info("imprimirRecetaMedica");
		ModelAndView model = null;
		try{
			certificadosService.recetaMedica(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=recetaMedica.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirLicenciaMedica")
	public ModelAndView imprimirLicenciaMedica(CertificadosForm certificadosForm) {
		//log.info("imprimirLicenciaMedica");
		ModelAndView model = null;
		try{
			certificadosService.licenciaMedica(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=licenciaMedica.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirEstudiosLaboratorio")
	public ModelAndView imprimirEstudiosLaboratorio(CertificadosForm certificadosForm) {
		//log.info("imprimirEstudiosLaboratorio");
		ModelAndView model = null;
		try{
			certificadosService.estudiosLaboratorio(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=estudiosLaboratorio.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirEstudiosGabinete")
	public ModelAndView imprimirEstudiosGabinete(CertificadosForm certificadosForm) {
		//log.info("imprimirEstudiosGabinete");
		ModelAndView model = null;
		try{
			certificadosService.estudiosGabinete(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=estudiosGabinete.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}	
	
	@RequestMapping(value = "/imprimirEstudiosOtros")
	public ModelAndView imprimirEstudiosOtros(CertificadosForm certificadosForm) {
		//log.info("imprimirEstudiosOtros");
		ModelAndView model = null;
		try{
			certificadosService.estudiosOtros(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=estudiosOtros.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}		
	
	@RequestMapping(value = "/imprimirSolicitudReferencia")
	public ModelAndView imprimirSolicitudReferencia(CertificadosForm certificadosForm) {
		//log.info("imprimirSolicitudReferencia");
		ModelAndView model = null;
		try{
			certificadosService.solicitudReferencia(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=solicitudReferencia.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}	
	
	@RequestMapping(value = "/imprimirSolicitudContrareferencia")
	public ModelAndView imprimirSolicitudContrareferencia(CertificadosForm certificadosForm) {
		//log.info("imprimirSolicitudContrareferencia");
		ModelAndView model = null;
		try{
			certificadosService.solicitudContrareferencia(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=solicitudContrareferencia.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}	
	
	@RequestMapping(value = "/imprimirMediaccessOrdenGabinete")
	public ModelAndView imprimirMediaccessOrdenGabinete(CertificadosForm certificadosForm) {
		//log.info("imprimirMediaccessOrdenGabinete");
		ModelAndView model = null;
		try{
			certificadosService.mediaccessOrdenGabinete(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=mediaccessOrdenGabinete.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirMediaccessOrdenLaboratorio")
	public ModelAndView imprimirMediaccessOrdenLaboratorio(CertificadosForm certificadosForm) {
		//log.info("imprimirMediaccessOrdenLaboratorio");
		ModelAndView model = null;
		try{
			//log.info("certificadosForm.getIdAtencion() ="+certificadosForm.getIdAtencion());
			certificadosService.mediaccessOrdenLaboratorio(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=mediaccessOrdenLaboratorio.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}	
	
	@RequestMapping(value = "/imprimirMediaccessAtencionConsultorio")
	public ModelAndView imprimirMediaccessAtencionConsultorio(CertificadosForm certificadosForm) {
		//log.info("imprimirMediaccessAtencionConsultorio");
		ModelAndView model = null;
		try{
			certificadosService.mediaccessAtencionConsultorio(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=mediaccessAtencionConsultorio.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirMediaccessRecetaMedica")
	public ModelAndView imprimirMediaccessRecetaMedica(CertificadosForm certificadosForm) {
		//log.info("imprimirMediaccessRecetaMedica");
		ModelAndView model = null;
		try{
			certificadosService.mediaccessRecetaMedica(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=mediaccessRecetaMedica.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/imprimirMediaccessOtrosApoyos")
	public ModelAndView imprimirMediaccessOtrosApoyos(CertificadosForm certificadosForm) {
		//log.info("imprimirMediaccessOtrosApoyos");
		ModelAndView model = null;
		try{
			certificadosService.mediaccessOtrosApoyos(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=mediaccessOtrosApoyos.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}		
	
	@RequestMapping(value = "/imprimirMediaccessSolicitudReferencia")
	public ModelAndView imprimirMediaccessSolicitudReferencia(CertificadosForm certificadosForm) {
		//log.info("imprimirMediaccessSolicitudReferencia");
		ModelAndView model = null;
		try{
			certificadosService.mediaccessSolicitudReferencia(certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=mediaccessSolicitudReferencia.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}	
	
	@RequestMapping(value = "/imprimirMediaccessTodo")
	public ModelAndView imprimirMediaccessTodo(CertificadosForm certificadosForm) {
		//log.info("imprimirMediaccessTodo");
		ModelAndView model = null;
		try{
			List<ByteArrayOutputStream> byteArrayOutputStreams = new ArrayList<>();
			AtencionTerminarForm atencionTerminarFormAux = (AtencionTerminarForm) session.getAttribute("atencionTerminarFormSession");
			if (atencionTerminarFormAux.isLlenadoReferencia()) {
				certificadosService.mediaccessSolicitudReferencia(certificadosForm);
				byteArrayOutputStreams.add(certificadosForm.getFile());
			}
			
			if (atencionTerminarFormAux.getOtrosVos()!=null) {
				certificadosService.mediaccessOtrosApoyos(certificadosForm);
				byteArrayOutputStreams.add(certificadosForm.getFile());				
			}
			
			if (atencionTerminarFormAux.getRecetasVos()!=null) {
				certificadosService.mediaccessRecetaMedica(certificadosForm);
				byteArrayOutputStreams.add(certificadosForm.getFile());				
			}

			if (atencionTerminarFormAux.getDiagnosticoVos()!=null || atencionTerminarFormAux.getConsultorioVos()!=null) {
				certificadosService.mediaccessAtencionConsultorio(certificadosForm);
				byteArrayOutputStreams.add(certificadosForm.getFile());	
			}
			
			if (atencionTerminarFormAux.getLaboratorioVos()!=null) {
				certificadosService.mediaccessOrdenLaboratorio(certificadosForm);
				byteArrayOutputStreams.add(certificadosForm.getFile());				
			}
			
			if (atencionTerminarFormAux.getGabineteVos()!=null) {
				certificadosService.mediaccessOrdenGabinete(certificadosForm);
				byteArrayOutputStreams.add(certificadosForm.getFile());				
			}

			certificadosService.doMerge(byteArrayOutputStreams, certificadosForm);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=mediaccessSolicitudReferencia.pdf");
			response.setContentLength(certificadosForm.getFile().size());
			OutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(certificadosForm.getFile().toByteArray());	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}		

	public static void main(String[] args) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Rectangle a4 = PageSize.A4;
			Rectangle a4LandScape = a4.rotate();
			Document document = new Document();
			document.setPageSize(a4LandScape);
			PdfWriter.getInstance(document, baos);
			document.open();
			Image image = Image.getInstance("src/com/mx/sab/resources/Mediaccess_Solicitud_de_referencia.jpg");
			
			float scaler = (float) (.667 * 100);
			
			image.scalePercent(scaler);
			image.setAbsolutePosition(0,40);
			document.add(image);
			document.close();
//			InputStream img = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/mx/sab/resources/constanciaAsistencia.pdf");
			
			
			OutputStream file = new FileOutputStream(new File("C:/Users/LAPTOPRAUL/Desktop/a22sd.pdf"));
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
			PdfReader reader = new PdfReader(byteArrayInputStream);
			PdfStamper stamper = new PdfStamper(reader, file);
			PdfContentByte canvas = stamper.getOverContent(1);
			
//			Font helvetica = new Font(FontFamily.HELVETICA, 9);
	        
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("No de autorizacion"), 328, 525, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Doctor"), 130, 486, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Especialidad"), 395, 486, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Cedula Profesional"), 182, 469, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Telefono"), 370, 469, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Domicilio"), 130, 450, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Ciudad"), 510, 450, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Paciente"), 128, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Sexo"), 490, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Edad"), 589, 419, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Empresa"), 128, 401, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Cod Afiliado"), 438, 401, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Parentesco"), 588, 401, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Poliza"), 110, 382, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Plan"), 223, 382, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Producto"), 450, 382, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("PYME"), 110, 364, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Copago"), 490, 364, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Fecha"), 720, 380, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Folio"), 720, 361, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Diagnostico"), 126, 321, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Motivo de Referencia"), 179, 247, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Especialidad"), 188, 174, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Se envia"), 126, 100, 0);
			
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Unidad Medica"), 545, 321, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Presentacion del caso"), 385, 235, 0);
			ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Se envia"), 385, 122, 0);
			
			stamper.close();
			file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}


