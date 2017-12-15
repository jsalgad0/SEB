package com.mx.sab.service.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IAgendaDao;
import com.mx.sab.dao.ILugarAtencionDao;
import com.mx.sab.dao.IUsuarioDao;
import com.mx.sab.enums.RolesEnum;
import com.mx.sab.form.SeleccionarMedicoForm;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Medicohorario;
import com.mx.sab.model.Usuarios;
import com.mx.sab.service.ISeleccionarMedicoService;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.HorariosVo;
import com.mx.sab.vo.MedicosHorariosVo;
import com.mx.sab.vo.UserInfo;
import com.mx.sab.vo.UsuariosVo;

@Service
@Log4j2
public class SeleccionarMedicoServiceImpl implements ISeleccionarMedicoService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private ILugarAtencionDao lugarAtencionDao;
	
	@Autowired
	private IAgendaDao agendaDao;
	
	@Override
	public void inicializarSeleccionarMedicoForm(SeleccionarMedicoForm seleccionarMedicoForm, UserInfo userInfo) {
		//log.info("seleccionar medico");
		Collection<Usuarios> usuarios = usuarioDao.getUsuariosMedicos(RolesEnum.MEDICO.getId(), userInfo.getLugaresdeatencion().getLugarDeAtencionId());
		//log.info("usuarios.size() ="+usuarios.size());
		int id = 1;
//		Time horaEntrada = null;
//		Time horaSalida = null;
//		int index = 0;
//		for (Usuarios usuario : usuarios) {
//			Iterator<Medicohorario> medicoHorarioIterator = usuario.getMedicohorarios().iterator();
//			while (medicoHorarioIterator.hasNext()) {
//				Medicohorario medicohorario = (Medicohorario) medicoHorarioIterator.next();
//				if (index!=0) {
//					if (horaEntrada.after(medicohorario.getHoraEntrada())) {
//						horaEntrada = medicohorario.getHoraEntrada();
//					}
//					if (horaSalida.before(medicohorario.getHoraSalida())) {
//						horaSalida = medicohorario.getHoraSalida();
//					}	
//				}else {
//					horaEntrada = medicohorario.getHoraEntrada();
//					horaSalida = medicohorario.getHoraSalida();
//				}
//				index++;
//			}
//		}
		Calendar calendarEntrada = Calendar.getInstance();
		Calendar calendarSalida = Calendar.getInstance();
		Collection<MedicosHorariosVo> medicosHorariosVos = new ArrayList<>();
		MedicosHorariosVo medicosHorariosVo = new MedicosHorariosVo();
		HorariosVo horariosVo = new HorariosVo();
		Collection<HorariosVo> horariosVos = new ArrayList<>();
		for (Usuarios usuario : usuarios) {
			//log.info("usuario.getUsuarioId() ="+usuario.getUsuarioId());
			Iterator<Medicohorario> medicoHorarioIterator = usuario.getMedicohorarios().iterator();
			while (medicoHorarioIterator.hasNext()) {
				Medicohorario medicohorario = (Medicohorario) medicoHorarioIterator.next();
				//log.info("medicohorario.getUsuarioId() ="+medicohorario.getUsuarioId());
				calendarEntrada.setTimeInMillis(medicohorario.getHoraEntrada().getTime());
				calendarSalida.setTimeInMillis(medicohorario.getHoraSalida().getTime());
				horariosVos = new ArrayList<>();
				String salida = FormatUtil.getTime(calendarSalida.getTime());
				String entrada = "";
				
				Collection<Agenda> agendas = agendaDao.getAgendaByUsuarioAndFecha(usuario.getUsuarioId());
				Collection<String> horariosDescartar = new ArrayList<>();
				//log.info("Verificando que horarios a descartar");
				for (Agenda agenda : agendas) {
					String hora = FormatUtil.getTime(agenda.getHoraCita());
					horariosDescartar.add(hora);
				}
					
				
				//log.info("Generando horarios");
				while(!salida.equals(entrada)){
					horariosVo = new HorariosVo();
					entrada = FormatUtil.getTime(calendarEntrada.getTime());
					if (!horariosDescartar.contains(entrada)) {
						horariosVo.setHorario(entrada);
						horariosVo.setId(""+id);
						horariosVos.add(horariosVo);
						calendarEntrada.add(Calendar.MINUTE, 15);
						id++;						
					}else{
						calendarEntrada.add(Calendar.MINUTE, 15);
					}
				}	
				
				//log.info("Llenando VO");
				medicosHorariosVo = new MedicosHorariosVo();
				medicosHorariosVo.setHorariosVos(horariosVos);
				medicosHorariosVo.setIdUsuario(usuario.getUsuarioId());
				medicosHorariosVo.setNombre(usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
				medicosHorariosVos.add(medicosHorariosVo);
			}
		}
		seleccionarMedicoForm.setMedicosHorariosVos(medicosHorariosVos);
	}
	
	public static void main (String a[]){
		Time horaEntrada = new Time(new Date().getTime());
		System.out.println(horaEntrada);
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(horaEntrada.getTime());
		
		System.out.println(FormatUtil.getTime(calendar.getTime()));
		
		calendar.add(Calendar.MINUTE, 15);
		
		System.out.println(FormatUtil.getTime(calendar.getTime()));
	}

	@Override
	public Collection<UsuariosVo> getMedicos(String idTiempo,Collection<Usuarios> usuarios) {
		Collection<UsuariosVo> usuariosVos = new ArrayList<>();
		UsuariosVo usuariosVo = null;
		boolean usuarioDescartado = false;
		String hora = "";
		for (Usuarios usuario : usuarios) {
			Iterator<Agenda> agendaIterator = usuario.getAgendas().iterator();
			while (agendaIterator.hasNext()) {
				Agenda agenda = (Agenda) agendaIterator.next();
				hora = FormatUtil.getTime(agenda.getHoraCita());
				if(hora.equals(idTiempo)){
					usuarioDescartado = true;
					break;
				}
			}
			
			if (!usuarioDescartado) {
				usuariosVo = new UsuariosVo();
				usuariosVo.setUsuarioId(usuario.getUsuarioId());
				usuariosVo.setNombre(usuario.getNombre());
				usuariosVo.setApellidoMaterno(usuario.getApellidoMaterno());
				usuariosVo.setApellidoPaterno(usuario.getApellidoPaterno());
				usuariosVos.add(usuariosVo);				
			}
			
			usuarioDescartado = false;
		}
		
		return usuariosVos;
	}

	@Override
	public UsuariosVo getMedicoInfo(int idMedico,Collection<Usuarios> usuarios) {
		UsuariosVo usuariosVo = new UsuariosVo();
		Usuarios usuario = usuarioDao.getUsuarioById(idMedico);
		usuariosVo.setNombre(usuario.getNombre());
		usuariosVo.setApellidoMaterno(usuario.getApellidoMaterno());
		usuariosVo.setApellidoPaterno(usuario.getApellidoPaterno());
		usuariosVo.setUsuarioId(usuario.getUsuarioId());		
		return usuariosVo;
	}

}
