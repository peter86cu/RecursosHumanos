package com.ayalait.rh.dao;


import java.sql.Date;
import java.util.List;

import com.ayalait.rh.modelo.*;


public interface AsistenciaDao {

	void asistenciaEmpleado(MarcasEmpleado marca);

	MarcasEmpleado obtenerMarcasEmpleadoDia(String idEmpleado, Date fecha);
	
	List<MarcasEmpleado> listadoMarcasPorFecha(int mes, int anio, String estado);
	
	List<MarcasEmpleado> listadoMarcasFiltro(int mes, int anio, String estado);
	
	Object findEmpleadoHorarios(String id);
	
	CalendarioMesAProcesar obtenerMesAProcesar();
	
	
	
}
