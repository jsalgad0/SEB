package com.mx.sab.controller;

import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mx.sab.form.AtencionEstudiosMedicosForm;
import com.mx.sab.form.AtencionRecetaForm;
import com.mx.sab.form.AtencionTerminarForm;
import com.mx.sab.form.CicloVitalForm;
import com.mx.sab.form.GinecoObstentricosForm;
import com.mx.sab.form.AtencionOtrosDiagnosticosForm;
import com.mx.sab.form.HistoriaClinicaPersonalesNoPatologicosForm;
import com.mx.sab.form.HistorialAtencionesForm;
import com.mx.sab.form.HistorialPorDiagnosticoForm;
import com.mx.sab.form.MedicoAtencionPacientesForm;
import com.mx.sab.form.SignosVitalesForm;
import com.mx.sab.form.SolicitudReferenciaForm;
import com.mx.sab.service.ISignosService;
import com.mx.sab.vo.TomaSignosVo;

@Controller
@Log4j2
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private MedicoAtencionPacientesController medicoAtencionPacientesController;
	
	@Autowired
	private MedicoInformacionPacienteController medicoInformacionPacienteController;
	
	@Autowired
	private HistoriaClinicaPersonalesNoPatologicosController historiaClinicaPersonalesNoPatologicosController;

	@Autowired
	private MedicoAntecedentesFamiliaresController medicoAntecedentesFamiliaresController;
	
	@Autowired
	private MedicoAntecedentesPersonalesController medicoAntecedentesPersonalesController;
	
	@Autowired
	private MedicoGinecoObstentricosController medicoGinecoObstentricosController;
	
	@Autowired
	private MedicoExploracionFisicaController medicoExploracionFisicaController;
	
	@Autowired
	private MedicoCicloVitalController medicoCicloVitalController;
	
	@Autowired
	private HistorialAtencionesController historialAtencionesController;
	
	@Autowired
	private HistorialDiagnosticoController historialDiagnosticoController;
	
	@Autowired
	private AtencionOtrosDiagnosticosController atencionOtrosDiagnosticosController;
	
	@Autowired
	private AtencionSignosController atencionSignosController;
	
	@Autowired
	private ISignosService signosService;
	
	@Autowired
	private MedicoNotaMedicaController medicoNotaMedicaController;
	
	@Autowired
	private MedicoLicenciaMedicaController medicoLicenciaMedicaController;

	@Autowired
	private AtencionEstudiosMedicosController atencionEstudiosMedicosController;
	
	@Autowired
	private AtencionRecetaController atencionRecetaController;
	
	@Autowired
	private MedicoConstanciaAsistenciaController medicoConstanciaAsistenciaController;
	
	@Autowired
	private MedicoCuidadosMaternalesController medicoCuidadosMaternalesController;
	
	@Autowired
	private MedicoConstanciaSaludController medicoConstanciaSaludController;
	
	@Autowired
	private AtencionSolicitudReferenciaController atencionSolicitudReferenciaController;
	
	@Autowired
	private AtencionTerminarController atencionTerminarController;
	
	@RequestMapping(value = "/atencionPacientes")
	public ModelAndView atencionPacientes(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		return medicoAtencionPacientesController.inicioAtencionPacientes(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/menuMedico")
	public ModelAndView menuMedico(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		//log.info("menuMedico");	
		ModelAndView model = new ModelAndView();
		model.setViewName("menuMedico");
		verificaIdAtencion(medicoAtencionPacientesForm);
		model.addObject("medicoAtencionPacientesForm", medicoAtencionPacientesForm);
		
		return model; 
	}	
	
	@RequestMapping(value = "/inicioAtencion")
	public ModelAndView inicioAtencion(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		if (medicoAtencionPacientesForm.getIdAtencion()!=0) {
			medicoAtencionPacientesForm.setIdAtencion(((MedicoAtencionPacientesForm)session.getAttribute("medicoAtencionPacientesForm")).getIdAtencion());
		}
		return medicoInformacionPacienteController.inicioInformacionPaciente(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/antecedentesFamiliares")
	public ModelAndView inicioHistoriaMedica(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		return medicoAntecedentesFamiliaresController.inicioAntecedentesFamiliares(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/antecedentesPersonales")
	public ModelAndView inicioAntecedentesPersonales(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		return medicoAntecedentesPersonalesController.inicioAntecedentesPersonales(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/exploracionFisica")
	public ModelAndView inicioExploracionFisica(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		return medicoExploracionFisicaController.inicioExploracionFisica(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/personalesNoPatologicos")
	public ModelAndView personalesNoPatologicos(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		HistoriaClinicaPersonalesNoPatologicosForm historiaClinicaPersonalesNoPatologicosForm = new HistoriaClinicaPersonalesNoPatologicosForm();
		historiaClinicaPersonalesNoPatologicosForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		return historiaClinicaPersonalesNoPatologicosController.inicioPersonalesNoPatologicos(historiaClinicaPersonalesNoPatologicosForm);
	}
	
	@RequestMapping(value = "/ginecoObstentricos")
	public ModelAndView ginecoObstentricos(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		GinecoObstentricosForm ginecoObstentricosForm = new GinecoObstentricosForm();
		ginecoObstentricosForm.setAfiliadoId(medicoAtencionPacientesForm.getIdAtencion());
		return medicoGinecoObstentricosController.inicioGinecoObstentricos(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/cicloVital")
	public ModelAndView cicloVital(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		CicloVitalForm cicloVitalForm = new CicloVitalForm();
		cicloVitalForm.setAfiliadoId(medicoAtencionPacientesForm.getIdAtencion());
		return medicoCicloVitalController.inicioCicloVital(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/historialAtenciones")
	public ModelAndView historialAtenciones(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		HistorialAtencionesForm historialAtencionesForm = new HistorialAtencionesForm();
		historialAtencionesForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		return historialAtencionesController.inicioHistorialAtenciones(historialAtencionesForm);
	}	
	
	@RequestMapping(value = "/historialDiagnostico")
	public ModelAndView historialDiagnostico(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		HistorialPorDiagnosticoForm historialPorDiagnosticoForm = new HistorialPorDiagnosticoForm();
		historialPorDiagnosticoForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		return historialDiagnosticoController.inicioHistorialDiagnostico(historialPorDiagnosticoForm);
	}	
	
	@RequestMapping(value = "/otrosDiagnosticos")
	public ModelAndView otrosDiagnosticos(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		AtencionOtrosDiagnosticosForm atencionOtrosDiagnosticosForm = new AtencionOtrosDiagnosticosForm();
		atencionOtrosDiagnosticosForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		return atencionOtrosDiagnosticosController.inicioAtencionOtrosDiagnosticos(atencionOtrosDiagnosticosForm);
	}	
	
	@RequestMapping(value = "/signosVitales")
	public ModelAndView signosVitales(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		SignosVitalesForm signosVitalesForm = new SignosVitalesForm();
		signosVitalesForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		return atencionSignosController.inicioTomaSignos(signosVitalesForm);
	}
	
	@RequestMapping(value = "/notaMedica")
	public ModelAndView notaMedica(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		session.removeAttribute("medicoAtencionPacientesFormAux");
		session.setAttribute("medicoAtencionPacientesFormAux", medicoAtencionPacientesForm);
		TomaSignosVo tomaSignosVo = signosService.getTomaSignos(medicoAtencionPacientesForm.getIdAtencion());
		session.setAttribute("infoSignos", tomaSignosVo);
		return medicoNotaMedicaController.inicioNotaMedica(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/licenciaMedica")
	public ModelAndView licenciaMedica(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		return medicoLicenciaMedicaController.inicioLicenciaMedica(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/informacionPaciente")
	public ModelAndView inicioInformacionPaciente(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		return medicoInformacionPacienteController.inicioInformacionPaciente(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/estudiosLaboratorio")
	public ModelAndView estudiosLaboratorio(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		AtencionEstudiosMedicosForm atencionEstudiosMedicosForm = new AtencionEstudiosMedicosForm();
		atencionEstudiosMedicosForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		atencionEstudiosMedicosForm.setIdEstudios(2);
		return atencionEstudiosMedicosController.estudiosMedicos(atencionEstudiosMedicosForm);
	}	
	
	@RequestMapping(value = "/estudiosGabinete")
	public ModelAndView estudiosGabinete(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		AtencionEstudiosMedicosForm atencionEstudiosMedicosForm = new AtencionEstudiosMedicosForm();
		atencionEstudiosMedicosForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		atencionEstudiosMedicosForm.setIdEstudios(1);
		return atencionEstudiosMedicosController.estudiosMedicos(atencionEstudiosMedicosForm);
	}	
	
	@RequestMapping(value = "/estudiosOtros")
	public ModelAndView estudiosOtros(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		AtencionEstudiosMedicosForm atencionEstudiosMedicosForm = new AtencionEstudiosMedicosForm();
		atencionEstudiosMedicosForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		atencionEstudiosMedicosForm.setIdEstudios(3);
		return atencionEstudiosMedicosController.estudiosMedicos(atencionEstudiosMedicosForm);
	}	
	
	@RequestMapping(value = "/estudiosConsultorio")
	public ModelAndView estudiosConsultorio(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		AtencionEstudiosMedicosForm atencionEstudiosMedicosForm = new AtencionEstudiosMedicosForm();
		atencionEstudiosMedicosForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		atencionEstudiosMedicosForm.setIdEstudios(4);
		return atencionEstudiosMedicosController.estudiosMedicos(atencionEstudiosMedicosForm);
	}	
	
	@RequestMapping(value = "/receta")
	public ModelAndView receta(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		AtencionRecetaForm atencionRecetaForm = new AtencionRecetaForm();
		atencionRecetaForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		return atencionRecetaController.receta(atencionRecetaForm);
	}	
	
	@RequestMapping(value = "/constanciaAsistencia")
	public ModelAndView constanciaAsistencia(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		return medicoConstanciaAsistenciaController.inicioConstanciaAsistencia(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/cuidadosMaternales")
	public ModelAndView cuidadosMaternales(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		return medicoCuidadosMaternalesController.inicioCuidadosMaternales(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/constanciaSalud")
	public ModelAndView constanciaSalud(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		return medicoConstanciaSaludController.inicioConstanciaSalud(medicoAtencionPacientesForm);
	}
	
	@RequestMapping(value = "/solicitudReferencia")
	public ModelAndView solicitudReferencia(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		SolicitudReferenciaForm solicitudReferenciaForm = new SolicitudReferenciaForm();
		solicitudReferenciaForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		return atencionSolicitudReferenciaController.inicioSolicitudReferencia(solicitudReferenciaForm);
	}
	
	@RequestMapping(value = "/terminar")
	public ModelAndView terminar(MedicoAtencionPacientesForm medicoAtencionPacientesForm) {
		verificaIdAtencion(medicoAtencionPacientesForm);
		AtencionTerminarForm atencionTerminarForm = new AtencionTerminarForm();
		atencionTerminarForm.setIdAtencion(medicoAtencionPacientesForm.getIdAtencion());
		return atencionTerminarController.inicioTerminar(atencionTerminarForm);
	}	
	
	private void verificaIdAtencion(MedicoAtencionPacientesForm medicoAtencionPacientesForm){
		if (medicoAtencionPacientesForm.getIdAtencion()==0) {
			medicoAtencionPacientesForm.setIdAtencion(((MedicoAtencionPacientesForm)session.getAttribute("medicoAtencionPacientesFormAux")).getIdAtencion());
		}
	}

}
