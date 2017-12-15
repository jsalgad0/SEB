package com.mx.sab.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import com.mx.sab.dao.ICargaAgendaDao;
import com.mx.sab.model.Afiliado;
import com.mx.sab.model.AfiliadoAsegurador;
import com.mx.sab.model.Afiliadotipoidentificador;
import com.mx.sab.model.Agenda;
import com.mx.sab.model.Cattipoafiliado;
import com.mx.sab.model.Cattipoidentificador;
import com.mx.sab.model.Lugaresdeatencion;
import com.mx.sab.model.Prestadores;
import com.mx.sab.model.Usuarios;
import com.mx.sab.model.jndi.AfiliadoTipoIdentificador;

@Log4j2
@Service
public class CargaAgendaDaoImpl implements ICargaAgendaDao {
	
	@Override
	public Collection<AfiliadoTipoIdentificador> getAfiliadoTipoIdentificadorIsssteJNDI(int numIssste) {
		//log.info("getAfiliadoTipoIdentificadorIsssteJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Collection<AfiliadoTipoIdentificador> afiliadotipoidentificadors = new ArrayList<>();
        AfiliadoTipoIdentificador afiliadotipoidentificador = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM afiliadotipoidentificador WHERE TipoIdentificadorId = 4 AND tipoIdValor = ? order by AfiliadoId");
            pstm.setInt(1, numIssste);
             
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	afiliadotipoidentificador = new AfiliadoTipoIdentificador();
            	afiliadotipoidentificador.setAfiliadoId(rs.getInt(1));
            	afiliadotipoidentificadors.add(afiliadotipoidentificador);
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return afiliadotipoidentificadors;
	}

	@Override
	public Collection<AfiliadoTipoIdentificador> getAfiliadoTipoIdentificadorIdJNDI(int afiliadoId) {
		//log.info("getAfiliadoTipoIdentificadorIdJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Collection<AfiliadoTipoIdentificador> afiliadotipoidentificadors = new ArrayList<>();
        AfiliadoTipoIdentificador afiliadotipoidentificador = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM afiliadotipoidentificador WHERE AfiliadoId = ? order by AfiliadoId");
            pstm.setInt(1, afiliadoId);
             
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	afiliadotipoidentificador = new AfiliadoTipoIdentificador();
            	afiliadotipoidentificador.setAfiliadoId(rs.getInt(1));
            	afiliadotipoidentificador.setTipoIdentificadorId(rs.getInt(2));
            	afiliadotipoidentificador.setTipoIdValor(rs.getString(3));
            	afiliadotipoidentificadors.add(afiliadotipoidentificador);
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return afiliadotipoidentificadors;
	}

	@Override
	public Cattipoafiliado getCatTipoAfiliadoByClaveJNDI(int derechohabiente,int idAsegurador) {
		//log.info("getCatTipoAfiliadoByClaveJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Cattipoafiliado cattipoafiliado = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM cattipoafiliado WHERE tipoAfilClave = ? AND AseguradorId = ?");
            pstm.setInt(1, derechohabiente);
            pstm.setInt(2, idAsegurador);
             
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	cattipoafiliado = new Cattipoafiliado();
            	cattipoafiliado.setTipoAfiliadoId(rs.getInt(1));
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return cattipoafiliado;
	}

	@Override
	public Afiliado saveAfiliadoJNDI(Afiliado afiliado) {
		//log.info("saveAfiliadoJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("INSERT INTO afiliado (Nombre, ApellidoPaterno, ApellidoMaterno, FechaDeNacimiento, EstadoDeNacimientoId, SexoId, EstadoId, FechaAlta, Activo, TipoAfiliadoId)"
            							+ "VALUES (?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, afiliado.getNombre());
            pstm.setString(2, afiliado.getApellidoPaterno());
            pstm.setString(3, afiliado.getApellidoMaterno());
            pstm.setDate(4, new java.sql.Date(afiliado.getFechaDeNacimiento().getTime()));
            pstm.setInt(5, afiliado.getCatestadosByEstadoDeNacimientoId().getEstadoId());
            pstm.setInt(6, afiliado.getCatsexos().getSexoId());
            pstm.setInt(7, afiliado.getCatestadosByEstadoId().getEstadoId());
            pstm.setDate(8, new java.sql.Date(afiliado.getFechaAlta().getTime()));
            pstm.setInt(9, afiliado.getActivo());
            pstm.setInt(10, afiliado.getCattipoafiliado().getTipoAfiliadoId());
            
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            rs.next();
            afiliado.setAfiliadoId(rs.getInt(1));
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return afiliado;
	}

	@Override
	public void saveAfiliadoAseguradorJNDI(AfiliadoAsegurador afiliadoAsegurador) {
		//log.info("saveAfiliadoAseguradorJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("INSERT INTO afiliado_asegurador (AfiliadoId, AseguradorId)"
            							+ "VALUES (?,?)");
            pstm.setInt(1, afiliadoAsegurador.getAfiliado().getAfiliadoId());
            pstm.setInt(2, afiliadoAsegurador.getAseguradores().getAseguradorId());
            
            pstm.executeUpdate();
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
        }
	}

	@Override
	public void saveAfiliadotipoIdentificarJNDI(Afiliadotipoidentificador afiliadotipoidentificador) {
		//log.info("saveAfiliadotipoIdentificarJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("INSERT INTO afiliadotipoidentificador (AfiliadoId, TipoIdentificadorId, TipoIdValor)"
            							+ "VALUES (?,?,?)");
            pstm.setInt(1, afiliadotipoidentificador.getAfiliado().getAfiliadoId());
            pstm.setInt(2, afiliadotipoidentificador.getCattipoidentificador().getTipoIdentificadorId());
            pstm.setString(3, afiliadotipoidentificador.getTipoIdValor());
            
            pstm.executeUpdate();
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
        }
	}

	@Override
	public Cattipoidentificador getCatTipoIdentificadorByIdJNDI(int tipoIdentificadorId) {
		//log.info("getCatTipoIdentificadorByIdJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Cattipoidentificador cattipoidentificador = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM cattipoidentificador WHERE TipoIdentificadorId = ?");
            pstm.setInt(1, tipoIdentificadorId);
             
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	cattipoidentificador = new Cattipoidentificador();
            	cattipoidentificador.setTipoIdentificadorId(rs.getInt(1));
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return cattipoidentificador;
	}

	@Override
	public Lugaresdeatencion getLugarAtencionByClaveJNDI(String claveInst) {
		//log.info("getLugarAtencionByClaveJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Lugaresdeatencion lugaresdeatencion = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM lugaresdeatencion WHERE ClaveDeLaUnidad = ? AND Activo = 1");
            pstm.setString(1, claveInst);
             
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	lugaresdeatencion = new Lugaresdeatencion();
            	lugaresdeatencion.setLugarDeAtencionId(rs.getInt(1));
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return lugaresdeatencion;
	}

	@Override
	public Prestadores getPrestadoresByRfcJNDI(String prestador) {
		//log.info("getPrestadoresByRfcJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Prestadores prestadores = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM prestadores WHERE RFC = ?");
            pstm.setString(1, prestador);
             
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	prestadores = new Prestadores();
            	prestadores.setPrestadorId(rs.getInt(1));
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return prestadores;
	}

	@Override
	public Usuarios getUsuarioByIdentificadorJNDI(int id, String cveMedico) {
		//log.info("getUsuarioByIdentificadorJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Usuarios usuarios = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM usuariotipoidentificador WHERE TipoIdentificadorId = ? AND Valor = ?");
            pstm.setInt(1, id);
            pstm.setString(2, cveMedico);
             
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	usuarios = new Usuarios();
            	usuarios.setUsuarioId(rs.getInt(2));
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return usuarios;
	}

	@Override
	public Agenda existeAgendaJNDI(Agenda agenda) {
		//log.info("existeAgendaJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Agenda agendaRes = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM agenda WHERE AfiliadoId = ? AND AseguradorId = ? AND FechaCita = ? AND HoraCita = ? AND LugarDeAtencionId = ? AND PrestadorId = ?");
            pstm.setInt(1, agenda.getAfiliado().getAfiliadoId());
            pstm.setInt(2, agenda.getAseguradores().getAseguradorId());
            pstm.setDate(3, new Date(agenda.getFechaCita().getTime()));
            pstm.setTime(4, agenda.getHoraCita());
            pstm.setInt(5, agenda.getLugaresdeatencion().getLugarDeAtencionId());
            pstm.setInt(6, agenda.getPrestadores().getPrestadorId());
             
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	agendaRes = new Agenda();
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return agendaRes;
	}

	@Override
	public Object existeAgendaByConsultorioJNDI(Agenda agenda) {
		//log.info("existeAgendaByConsultorioJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Agenda agendaRes = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM agenda WHERE Consultorio = ? AND FechaCita = ? AND HoraCita = ?");
            pstm.setString(1, agenda.getConsultorio());
            pstm.setDate(2, new Date(agenda.getFechaCita().getTime()));
            pstm.setTime(3, agenda.getHoraCita());
            
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	agendaRes = new Agenda();
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return agendaRes;
	}

	@Override
	public Object existeAgendaByUsuarioJNDI(Agenda agenda) {
		//log.info("existeAgendaByUsuarioJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Agenda agendaRes = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("SELECT * FROM agenda WHERE UsuarioMedicoId = ? AND FechaCita = ? AND HoraCita = ?");
            pstm.setInt(1, agenda.getUsuarios().getUsuarioId());
            pstm.setDate(2, new Date(agenda.getFechaCita().getTime()));
            pstm.setTime(3, agenda.getHoraCita());
            
            rs = pstm.executeQuery();
             
            while(rs.next()){
            	agendaRes = new Agenda();
            } 
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
        return agendaRes;
	}

	@Override
	public void saveAgendaJNDI(Agenda agenda) {
		//log.info("saveAgendaJNDI");
        Context ctx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/identisa");
             
            con = ds.getConnection();
            pstm = con.prepareStatement("INSERT INTO agenda (UsuarioMedicoId, TipoIdentificadorId, AfiliadoId, Consultorio, LugarDeAtencionId, AseguradorId, PrestadorId, AgendadoPorId, FechaCita, HoraCita, ClaveCita, ConsultorioOriginal, Prestacion)"
            							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstm.setInt(1, agenda.getUsuarios().getUsuarioId());
            pstm.setInt(2, agenda.getCattipoidentificador().getTipoIdentificadorId());
            pstm.setInt(3, agenda.getAfiliado().getAfiliadoId());
            pstm.setString(4, agenda.getConsultorio());
            pstm.setInt(5, agenda.getLugaresdeatencion().getLugarDeAtencionId());
            pstm.setInt(6, agenda.getAseguradores().getAseguradorId());
            pstm.setInt(7, agenda.getPrestadores().getPrestadorId());
            pstm.setInt(8, agenda.getCatagendadopor().getAgendadoPorId());
            pstm.setDate(9, new java.sql.Date(agenda.getFechaCita().getTime()));
            pstm.setTime(10, agenda.getHoraCita());
            pstm.setString(11, agenda.getClaveCita());
            pstm.setString(12, agenda.getConsultorioOriginal());
            pstm.setString(13, agenda.getPrestacion());
            
            pstm.executeUpdate();
            
        }catch(NamingException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
            	if(rs!=null){
            		rs.close();
            	}
            	if (pstm!=null) {
					pstm.close();
				}
            	if (con!=null) {
					con.close();
				}
            	if (ctx!=null){
            		ctx.close();	
            	}
                
            } catch (SQLException e) {
                //log.info("Exception in closing DB resources");
            } catch (NamingException e) {
                //log.info("Exception in closing Context");
            }
             
        }
	}

}
