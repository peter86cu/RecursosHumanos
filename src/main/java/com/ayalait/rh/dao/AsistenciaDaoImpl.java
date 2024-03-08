package com.ayalait.rh.dao;

import com.ayalait.rh.repositorio.*;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayalait.rh.modelo.*;

@Repository
public class AsistenciaDaoImpl implements AsistenciaDao {

	@Autowired
	AsistenciaJpa daoAsistencia;
	
	

	@Override
	public void asistenciaEmpleado(MarcasEmpleado marca) {
		daoAsistencia.save(marca);
	}

	@Override
	public MarcasEmpleado obtenerMarcasEmpleadoDia(String idEmpleado, Date fecha) {
		return daoAsistencia.findByCompositeId(idEmpleado, fecha);
	}

	@Override
	public List<MarcasEmpleado> listadoMarcasPorFecha(int mes, int anio, String estado) {
		return daoAsistencia.findMarcasAProcesar(mes, anio, estado);
	}

	@Override
	public List<MarcasEmpleado> listadoMarcasFiltro(int mes, int anio, String estado) {
		if(!estado.equalsIgnoreCase("TODAS"))
		return daoAsistencia.findMarcasPorFiltro(mes, anio, estado);
		return daoAsistencia.findMarcasPorFiltroAll(mes, anio);
	}

	@Override
	public Object findEmpleadoHorarios(String id) {
		
		return daoAsistencia.findEmpleadoHorarios(id);
	}

	
	
	
}
