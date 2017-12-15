package com.mx.sab.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lombok.extern.log4j.Log4j2;
import mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub;
import mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub.ConsultaMedicamentos;
import mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub.ConsultaMedicamentosRequest;
import mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub.ConsultaMedicamentosResponse;
import mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub.ConsultaMedicamentosResponseE;
import mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub.Medicamento;
import mx.gob.issste.consultamedicamentos.scm.services.ConsultaMedicamentosServiceStub.Medicamentos_type0;

import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.types.xsd.Integer;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.IRecetaMedicaDao;
import com.mx.sab.enums.AseguradoresEnum;
import com.mx.sab.enums.CatEstatusRecetasEnum;
import com.mx.sab.enums.CatTipoValorAseguradorEnum;
import com.mx.sab.form.RecetaMedicaForm;
import com.mx.sab.model.Afiliadotipovalorasegurador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Catmedicamentos;
import com.mx.sab.model.Catunidadesdetiempo;
import com.mx.sab.model.Catviasdeadminmedicamento;
import com.mx.sab.model.Medicamentosreceta;
import com.mx.sab.model.MedicamentosrecetaId;
import com.mx.sab.model.Recetas;
import com.mx.sab.service.IRecetaMedicaService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AutocompleteVo;
import com.mx.sab.vo.SeleccionMedicamentoVo;
import com.mx.sab.ws.vo.MedicamentoVo;

@Service
@Log4j2
public class RecetaMedicaServiceImpl implements IRecetaMedicaService {

	@Autowired
	private IRecetaMedicaDao recetaMedicaDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Override
	public Collection<Catviasdeadminmedicamento> getCatViasDeAdiminMedicamento() {
		return recetaMedicaDao.getCatViasDeAdiminMedicamento();
	}

	@Override
	public Collection<Catunidadesdetiempo> getCatUnidadesDeTiempoMenorDia() {
		return recetaMedicaDao.getCatUnidadesDeTiempoMenorDia();
	}

	@Override
	public Collection<Catunidadesdetiempo> getCatUnidadesDeTiempoMayorDia() {
		return recetaMedicaDao.getCatUnidadesDeTiempoMayorDia();
	}

	@Override
	public Collection<AutocompleteVo> getCatMedicamentos(String busqueda, RecetaMedicaForm recetaMedicaForm) {
		Agenda agenda = agendaDao.getAgendaById(recetaMedicaForm.getIdAgenda());
		Collection<AutocompleteVo> autocompleteVos = new ArrayList<>();
		AutocompleteVo autocompleteVo = null;
		if(agenda.getAseguradores().getAseguradorId() == AseguradoresEnum.MEDIACCESS.getId()){
			try {
				String plan = "0";
				Collection<Afiliadotipovalorasegurador> afiliadotipovaloraseguradors = agendaDao.getAfiliadoTipoValorAseguradorById(agenda.getAfiliado().getAfiliadoId());
				for (Afiliadotipovalorasegurador afiliadotipovalorasegurador : afiliadotipovaloraseguradors) {
					if(afiliadotipovalorasegurador.getId().getTipoValorAseguradorId()==CatTipoValorAseguradorEnum.COD_PLAN.getId()){
						plan = afiliadotipovalorasegurador.getId().getTipoIdValor();
						break;
					}
				}
				URL url = new URL("http://201.215.44.233/wsFarmacia/Mediaccess/RecetaElectronica.asmx/BusquedaMedicamentos?term="+busqueda+"&plan="+plan+"&callback=");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output = br.readLine();
				conn.disconnect();
				
				Gson gson = new GsonBuilder().create();
				output = output.replaceAll("\"", "'");
				List<MedicamentoVo> medicamentoVos = gson.fromJson(output, new TypeToken<List<MedicamentoVo>>(){}.getType());
				for (MedicamentoVo medicamentoVo : medicamentoVos) {
					autocompleteVo = new AutocompleteVo();
					autocompleteVo.setValue(medicamentoVo.getId());
					autocompleteVo.setLabel(medicamentoVo.getLabel());
					autocompleteVos.add(autocompleteVo);
				}
			}catch (MalformedURLException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else if (agenda.getAseguradores().getAseguradorId() == AseguradoresEnum.ISSSTE.getId()) {
			try {
				ConsultaMedicamentosServiceStub consultaMedicamentosServiceStub = new ConsultaMedicamentosServiceStub();
				consultaMedicamentosServiceStub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT, 2000);
				ConsultaMedicamentos consultaMedicamentos = new ConsultaMedicamentos();
				ConsultaMedicamentosRequest consultaMedicamentosRequest = new ConsultaMedicamentosRequest();
				consultaMedicamentosRequest.setClaveInsumo(busqueda);
				consultaMedicamentosRequest.setTipoBusqueda(2);
				consultaMedicamentosRequest.setClaveUM(FormatUtil.claveIssste(agenda.getLugaresdeatencion().getClaveDeLaUnidad()));
				consultaMedicamentosRequest.setTipoPresentacion(0);
				consultaMedicamentosRequest.setTipoTipificador(2);
				consultaMedicamentos.setConsultaMedicamentosRequest(consultaMedicamentosRequest);
				ConsultaMedicamentosResponseE consultaMedicamentosResponseE = consultaMedicamentosServiceStub.consultaMedicamentos(consultaMedicamentos);
				ConsultaMedicamentosResponse consultaMedicamentosResponse = consultaMedicamentosResponseE.getConsultaMedicamentosResponse();
				Medicamentos_type0 medicamentos_type0 = consultaMedicamentosResponse.getMedicamentos();
				Medicamento[] medicamentos = medicamentos_type0.getMedicamento();
				Collection<String> claves = new ArrayList<>();
				for (Medicamento medicamento : medicamentos) {
					claves.add(medicamento.getClave());
				}
				Collection<Catmedicamentos> catmedicamentos = recetaMedicaDao.getCatMedicamentos(claves);
				for (Catmedicamentos catmedicamento : catmedicamentos) {
					autocompleteVo = new AutocompleteVo();
					autocompleteVo.setValue(""+catmedicamento.getMedicamentoId());
					autocompleteVo.setLabel(catmedicamento.getMedicamento());
					autocompleteVos.add(autocompleteVo);
				}
			} catch (AxisFault e) {
				e.printStackTrace();
				Collection<Catmedicamentos> catmedicamentos = recetaMedicaDao.getCatMedicamentos(busqueda);
				for (Catmedicamentos catmedicamento : catmedicamentos) {
					autocompleteVo = new AutocompleteVo();
					autocompleteVo.setValue(""+catmedicamento.getMedicamentoId());
					autocompleteVo.setLabel(catmedicamento.getMedicamento());
					autocompleteVos.add(autocompleteVo);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			} 
		}else{
			Collection<Catmedicamentos> catmedicamentos = recetaMedicaDao.getCatMedicamentos(busqueda);
			for (Catmedicamentos catmedicamento : catmedicamentos) {
				autocompleteVo = new AutocompleteVo();
				autocompleteVo.setValue(""+catmedicamento.getMedicamentoId());
				autocompleteVo.setLabel(catmedicamento.getMedicamento());
				autocompleteVos.add(autocompleteVo);
			}
		}
		return autocompleteVos;	
	}

	@Override
	public void save(RecetaMedicaForm recetaMedicaForm) {
		Catmedicamentos catmedicamentos = recetaMedicaDao.getCatMedicamentosById(recetaMedicaForm.getIdMedicamento());
		Catunidadesdetiempo catunidadesdetiempoDuracion = recetaMedicaDao.getCatUnidadesDeTiempoMayorDiaById(recetaMedicaForm.getIdUnidadTiempoDuracion());
		Catunidadesdetiempo catunidadesdetiempoFrecuencia = recetaMedicaDao.getCatUnidadesDeTiempoMenorDiaById(recetaMedicaForm.getIdUnidadTiempoFrecuencia());
		Catviasdeadminmedicamento catviasdeadminmedicamento = recetaMedicaDao.getCatViasDeAdiminMedicamentoById(recetaMedicaForm.getIdViaDeAdmonMedicamento());
		recetaMedicaForm.getMedicamentosreceta().setCatmedicamentos(catmedicamentos);
		recetaMedicaForm.getMedicamentosreceta().setCatunidadesdetiempoByDuracionUnidadDeTiempoId(catunidadesdetiempoDuracion);
		recetaMedicaForm.getMedicamentosreceta().setCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId(catunidadesdetiempoFrecuencia);
		recetaMedicaForm.getMedicamentosreceta().setCatviasdeadminmedicamento(catviasdeadminmedicamento);
		
		Agenda agenda = agendaDao.getAgendaById(recetaMedicaForm.getIdAgenda());
		Recetas recetas = new Recetas();
		recetas.setFolio(1);
		recetas.setAtencionmedica(agenda.getAtencionmedica());
		recetas.setCatestatusrecetas(recetaMedicaDao.getCatEstatusRecetasById(CatEstatusRecetasEnum.PENDIENTE.getId()));
		recetaMedicaDao.saveReceta(recetas);
		
		MedicamentosrecetaId medicamentosrecetaId = new MedicamentosrecetaId();
		medicamentosrecetaId.setMedicamentoId(recetaMedicaForm.getIdMedicamento());
		medicamentosrecetaId.setRecetaId(recetas.getRecetaId());
		recetaMedicaForm.getMedicamentosreceta().setId(medicamentosrecetaId);
		recetaMedicaDao.save(recetaMedicaForm.getMedicamentosreceta());
	}

	@Override
	public void inicializaForm(RecetaMedicaForm recetaMedicaForm) {
		recetaMedicaForm.setEditar(false);
		Agenda agenda = agendaDao.getAgendaById(recetaMedicaForm.getIdAgenda());
		if (agenda.getAtencionmedica().getFechaAtendidoMedico()!=null) {
			recetaMedicaForm.setFinalizoAtencionMedica(true);
		}
		if (agenda.getAseguradores().getAseguradorId() == AseguradoresEnum.MEDIACCESS.getId() || agenda.getAseguradores().getAseguradorId() == AseguradoresEnum.ISSSTE.getId()) {
			recetaMedicaForm.setVentanaMedicamentos(true);	
		}
	}

	@Override
	public SeleccionMedicamentoVo getSeleccionMedicamentos(RecetaMedicaForm recetaMedicaForm) {
		Agenda agenda = agendaDao.getAgendaById(recetaMedicaForm.getIdAgenda());
		Catmedicamentos catmedicamentos = recetaMedicaDao.getCatMedicamentosById(recetaMedicaForm.getIdMedicamento());
		SeleccionMedicamentoVo seleccionMedicamentoVo = new SeleccionMedicamentoVo();
		if (agenda.getAseguradores().getAseguradorId() == AseguradoresEnum.ISSSTE.getId()) {
			try{
				ConsultaMedicamentosServiceStub consultaMedicamentosServiceStub = new ConsultaMedicamentosServiceStub();
				consultaMedicamentosServiceStub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT, 2000);
				ConsultaMedicamentos consultaMedicamentos = new ConsultaMedicamentos();
				ConsultaMedicamentosRequest consultaMedicamentosRequest = new ConsultaMedicamentosRequest();
				consultaMedicamentosRequest.setClaveInsumo(catmedicamentos.getClave());
				consultaMedicamentosRequest.setTipoBusqueda(1);
				consultaMedicamentosRequest.setClaveUM(FormatUtil.claveIssste(agenda.getLugaresdeatencion().getClaveDeLaUnidad()));
				consultaMedicamentosRequest.setTipoPresentacion(0);
				consultaMedicamentosRequest.setTipoTipificador(2);
				consultaMedicamentos.setConsultaMedicamentosRequest(consultaMedicamentosRequest);
				ConsultaMedicamentosResponseE consultaMedicamentosResponseE = consultaMedicamentosServiceStub.consultaMedicamentos(consultaMedicamentos);
				ConsultaMedicamentosResponse consultaMedicamentosResponse = consultaMedicamentosResponseE.getConsultaMedicamentosResponse();
				Medicamentos_type0 medicamentos_type0 = consultaMedicamentosResponse.getMedicamentos();
				Medicamento[] medicamentos = medicamentos_type0.getMedicamento();
				Medicamento medicamento = medicamentos[0];
				recetaMedicaForm.setMedicamentoWs(medicamento);
				seleccionMedicamentoVo.setErrorWs(false);
			} catch (AxisFault e) {
				e.printStackTrace();
				seleccionMedicamentoVo.setErrorWs(true);
			} catch (RemoteException e) {
				e.printStackTrace();
				seleccionMedicamentoVo.setErrorWs(true);
			}
		}
		return seleccionMedicamentoVo;
	}

	@Override
	public Collection<Medicamentosreceta> getMedicamentoRecetas(RecetaMedicaForm recetaMedicaForm) {
		Agenda agenda = agendaDao.getAgendaById(recetaMedicaForm.getIdAgenda());
		Collection<Recetas> recetas = recetaMedicaDao.getRecetasByAtencionMedica(agenda.getAtencionmedica().getAtencionMedicaId());
		Collection<Medicamentosreceta> medicamentosrecetas = new ArrayList<>();
		for (Recetas receta : recetas) {
			Iterator<Medicamentosreceta> medicamentoRecetaIterator = receta.getMedicamentosrecetas().iterator();
			while (medicamentoRecetaIterator.hasNext()) {
				Medicamentosreceta medicamentosreceta = (Medicamentosreceta) medicamentoRecetaIterator.next();
				medicamentosrecetas.add(medicamentosreceta);
			}
		}
		return medicamentosrecetas;
	}

	@Override
	public void deleteMedicamentoRecetas(RecetaMedicaForm recetaMedicaForm) {
		Recetas recetas = recetaMedicaDao.getRecetaMedicaById(recetaMedicaForm.getIdReceta());
		recetaMedicaDao.delete(recetas);
	}

	@Override
	public void inicializaMedicamentoRecetas(RecetaMedicaForm recetaMedicaForm) {
		Recetas recetas = recetaMedicaDao.getRecetaMedicaById(recetaMedicaForm.getIdReceta());
		Iterator<Medicamentosreceta> medicamentosRecetaIterator = recetas.getMedicamentosrecetas().iterator();
		while (medicamentosRecetaIterator.hasNext()) {
			Medicamentosreceta medicamentosreceta = (Medicamentosreceta) medicamentosRecetaIterator.next();
			recetaMedicaForm.setMedicamentosreceta(medicamentosreceta);
			recetaMedicaForm.setMedicamento(medicamentosreceta.getCatmedicamentos().getMedicamento());
			recetaMedicaForm.setIdMedicamento(medicamentosreceta.getCatmedicamentos().getMedicamentoId());
		}
		recetaMedicaForm.setEditar(true);
	}

	@Override
	public void update(RecetaMedicaForm recetaMedicaForm) {
		Catmedicamentos catmedicamentos = recetaMedicaDao.getCatMedicamentosById(recetaMedicaForm.getIdMedicamento());
		Catunidadesdetiempo catunidadesdetiempoDuracion = recetaMedicaDao.getCatUnidadesDeTiempoMayorDiaById(recetaMedicaForm.getIdUnidadTiempoDuracion());
		Catunidadesdetiempo catunidadesdetiempoFrecuencia = recetaMedicaDao.getCatUnidadesDeTiempoMenorDiaById(recetaMedicaForm.getIdUnidadTiempoFrecuencia());
		Catviasdeadminmedicamento catviasdeadminmedicamento = recetaMedicaDao.getCatViasDeAdiminMedicamentoById(recetaMedicaForm.getIdViaDeAdmonMedicamento());
		recetaMedicaForm.getMedicamentosreceta().setCatmedicamentos(catmedicamentos);
		recetaMedicaForm.getMedicamentosreceta().setCatunidadesdetiempoByDuracionUnidadDeTiempoId(catunidadesdetiempoDuracion);
		recetaMedicaForm.getMedicamentosreceta().setCatunidadesdetiempoByFrecuenciaUnidadDeTiempoId(catunidadesdetiempoFrecuencia);
		recetaMedicaForm.getMedicamentosreceta().setCatviasdeadminmedicamento(catviasdeadminmedicamento);
		
		MedicamentosrecetaId medicamentosrecetaId = new MedicamentosrecetaId();
		medicamentosrecetaId.setMedicamentoId(recetaMedicaForm.getIdMedicamento());
		medicamentosrecetaId.setRecetaId(recetaMedicaForm.getIdReceta());
		recetaMedicaForm.getMedicamentosreceta().setId(medicamentosrecetaId);
		recetaMedicaDao.update(recetaMedicaForm.getMedicamentosreceta());
		recetaMedicaForm.setEditar(false);
	}

}
