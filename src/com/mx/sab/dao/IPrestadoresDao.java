package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.form.PrestadorForm;
import com.mx.sab.model.Cattipoprestador;
import com.mx.sab.model.Cattipospersonas;
import com.mx.sab.model.Prestadores;

public interface IPrestadoresDao {

	void save(Prestadores prestadores);
	public Collection<Cattipospersonas> getCatTiposPersonas();
	public Collection<Cattipoprestador> getCatTipoPrestador();
	public Cattipoprestador getTipoPrestadorById(int i);
	public Cattipospersonas getTipoPersonaById(int i);
	Collection<Prestadores> getPrestadores(String string, int inicio, int fin);
	void delete(Prestadores prestador);
	Prestadores getPrestadorById(int parseInt);
	void update(Prestadores prestadores);
	int getPrestadoresCount(String string);
	Collection<Prestadores> getPrestadores();
	Collection<Prestadores> getPrestadoresSinPrestaciones();
	Prestadores getPrestadoresByNombre(String nombreRazonSocial);
	Prestadores getPrestadoresByRfc(String rfc);
	Prestadores existeDireccionPrestador(PrestadorForm prestadorForm);
	Collection<Prestadores> getPrestadoresByNombreBusqueda(String busqueda);
}
