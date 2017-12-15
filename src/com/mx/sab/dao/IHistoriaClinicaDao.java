package com.mx.sab.dao;

import java.util.Collection;

import com.mx.sab.model.Catalimentacion;
import com.mx.sab.model.Cathigiene;
import com.mx.sab.model.Catmpf;
import com.mx.sab.model.Cattipofamilia;
import com.mx.sab.model.HcAntecedentesfamiliares;
import com.mx.sab.model.HcAntecedentespersonal;
import com.mx.sab.model.HcExploracionfisica;
import com.mx.sab.model.HcFasedelciclo;
import com.mx.sab.model.HcGineco;
import com.mx.sab.model.HcPersonalnopatologicos;
import com.mx.sab.model.Historiaclinica;




public interface IHistoriaClinicaDao {

	Collection<Catalimentacion> getCatAlimentacion();
	Catalimentacion getAlimentacionById(int id);
	Collection<Cathigiene> getCatHigiene();
	Cathigiene getHigieneById(int id);
	Collection<Catmpf> getMpf();
	Catmpf getMpfById(int id);
	Collection<Cattipofamilia> getFamilia();
	Cattipofamilia getFamiliaById(int id);
	Historiaclinica getHistoriaClinica(int idHistoria);
	void saveHistoria(Historiaclinica historiaclinica);
	void saveHcPersonales(HcAntecedentespersonal hcAntecedentespersonal);
	void saveHcFamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares);
	void saveHcNoPatologicos(HcPersonalnopatologicos hcPersonalnopatologicos);
	void saveHcFase(HcFasedelciclo hcFasedelciclo);
	void saveHcExploracion(HcExploracionfisica hcExploracionfisica);
	void saveGineco(HcGineco hcGineco);
	void updateHcPersonales(HcAntecedentespersonal hcAntecedentespersonal);
	void updateHcFamiliares(HcAntecedentesfamiliares hcAntecedentesfamiliares);
	void updateHcNoPatologicos(HcPersonalnopatologicos hcPersonalnopatologicos);
	void updateHcFase(HcFasedelciclo hcFasedelciclo);
	void updateHcExploracion(HcExploracionfisica hcExploracionfisica);
	void updateGineco(HcGineco hcGineco);
	
}