package com.mx.sab.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.mx.sab.vo.AfiliadoIsssteVo;

public interface IIsssteDao {

	void leer() throws SQLException;

	Collection<AfiliadoIsssteVo> getAfiliado(int idIdentificador, String nombre, String apellidoPaterno, String apellidoMaterno, String tipoIdValor) throws SQLException;
}
