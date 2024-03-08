package com.ayalait.rh.service;


import java.sql.Date;
import org.springframework.http.ResponseEntity;

public interface AsistenciaService {
	ResponseEntity<String> asistenciaEmpleado(String datos);

	ResponseEntity<String> obtenerMarcasPorEmpleadoDia(String idEmpleado, Date fecha);

	ResponseEntity<String> procesarMarcas(int mes, int anio);
	
	ResponseEntity<String>  listadoMarcasFiltro(int mes, int anio, String estado);
	
	ResponseEntity<String> procesarMarcaEmpleado(String item);
	
	ResponseEntity<String> listadoMarcasEmpleadoPorFecha(String documento,int mes, int anio);
	
	ResponseEntity<String> generarCalendarioEmpleado();

}
