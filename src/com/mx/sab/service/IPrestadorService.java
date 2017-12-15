package com.mx.sab.service;

import java.util.Collection;

import com.mx.sab.form.PrestadorForm;
import com.mx.sab.model.Cattipoprestador;
import com.mx.sab.model.Cattipospersonas;
import com.mx.sab.model.Prestadores;
import com.mx.sab.vo.PrestadoresVo;

public interface IPrestadorService {

	void save(PrestadorForm prestadorForm);
	
	Collection<Cattipospersonas> getCatTiposPersonas();

	Collection<Cattipoprestador> getCatTipoPrestador();

	Collection<Prestadores> getPrestadores(PrestadorForm prestadorForm);

	void delete(PrestadorForm prestadorForm);

	void getPrestador(PrestadorForm prestadorForm);

	void update(PrestadorForm prestadorForm);

	Collection<PrestadoresVo> getPrestadores(String busqueda, int page);

	Collection<Prestadores> getPrestadores();

	Collection<Prestadores> getPrestadoresSinPrestaciones();

	Collection<Prestadores> getPrestadoresConPrestaciones();

	Collection<PrestadoresVo> getPrestadoresByDescripcion(String busqueda);

	PrestadoresVo getPrestadoresById(int id);

}
