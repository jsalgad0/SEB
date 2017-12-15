package com.mx.sab.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.sab.dao.IIsssteDao;
import com.mx.sab.enums.TipoIdentificadorEnum;
import com.mx.sab.form.CitasPresencialesForm;
import com.mx.sab.util.FormatUtil;
import com.mx.sab.vo.AfiliadoIsssteVo;

@Log4j2
@Service
public class IsssteDaoImpl implements IIsssteDao {

	@Autowired
	private DataSource dataSourceIssste;
	
	public void leer() throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSourceIssste.getConnection();
			ps = con.prepareStatement("SELECT * FROM videntisa");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}	
		}finally{
			if (rs!=null) {
				rs.close();
			}
			if (ps!=null) {
				ps.close();
			}			
			if (con!=null) {
				con.close();
			}
		}
	}

	@Override
	public Collection<AfiliadoIsssteVo> getAfiliado(int idIdentificador, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoIdValor) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<AfiliadoIsssteVo> afiliadoIsssteVos = new ArrayList<>();
		AfiliadoIsssteVo afiliadoIsssteVo = null;
		try{
			con = dataSourceIssste.getConnection();
			boolean paramExtra = false;
			String query = "SELECT * FROM videntisa WHERE nom_tra like ? AND apa_tra like ? AND ama_tra like ?";
			if (idIdentificador == TipoIdentificadorEnum.RFC.getId()){
				query = query + " AND rfc_tra = ?";
				paramExtra = true;
			}else if (idIdentificador == TipoIdentificadorEnum.CURP.getId()) {
				query = query + " AND curp_tra = ?";
				paramExtra = true;
			}else if (idIdentificador == TipoIdentificadorEnum.NUMISSSTE.getId()) {
				query = query + " AND num_issste = ?";
				paramExtra = true;
			}
			ps = con.prepareStatement(query);
			ps.setString(1, "%"+nombre+"%");
			ps.setString(2, "%"+apellidoPaterno+"%");
			ps.setString(3, "%"+apellidoMaterno+"%");
			if (paramExtra) {
				ps.setString(4, tipoIdValor);	
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				afiliadoIsssteVo = new AfiliadoIsssteVo();
				System.out.println(rs.getInt(1));
				afiliadoIsssteVo.setNumIssste(rs.getInt(1));
				System.out.println(rs.getString(2));
				afiliadoIsssteVo.setRfc(rs.getString(2));
				System.out.println(rs.getString(3));
				afiliadoIsssteVo.setCurp(rs.getString(3));
				System.out.println(rs.getString(4));
				afiliadoIsssteVo.setTipo(rs.getString(4));
				System.out.println(rs.getString(5));
				afiliadoIsssteVo.setApellidoPaterno(rs.getString(5));
				System.out.println(rs.getString(6));
				afiliadoIsssteVo.setApellidoMaterno(rs.getString(6));
				System.out.println(rs.getString(7));
				afiliadoIsssteVo.setNombre(rs.getString(7));
				System.out.println(rs.getString(8));
				afiliadoIsssteVo.setVigencia(rs.getString(8));
				System.out.println(rs.getString(9));
				afiliadoIsssteVo.setSexo(rs.getString(9));
				System.out.println(rs.getDate(10));
				if (rs.getDate(10)!=null) {
					afiliadoIsssteVo.setFechaNacimiento(FormatUtil.getDate(rs.getDate(10)));	
				}
				
				afiliadoIsssteVos.add(afiliadoIsssteVo);
			}	
		}finally{
			if (rs!=null) {
				rs.close();
			}
			if (ps!=null) {
				ps.close();
			}			
			if (con!=null) {
				con.close();
			}
		}
		return afiliadoIsssteVos;
	}
	
}
