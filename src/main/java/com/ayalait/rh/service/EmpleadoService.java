package com.ayalait.rh.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.ayalait.rh.modelo.*;

public interface EmpleadoService {
	ResponseEntity<String> agregarEmpleado(String datos);

	ResponseEntity<String> recuperarEmpleados();

	ResponseEntity<String> eliminarEmpleado(String id);

	ResponseEntity<String> buscarEmpleado(String id);

	List<Empleado> obtenerEmpleadoPorIdEmpresa(String id);

	ResponseEntity<String> obtenerEmpleadoNuevos(String id);

	ResponseEntity<String> agregarTituloEmpleado(String datos);

	ResponseEntity<String> agregarSaludEmpleado(String datos);

	ResponseEntity<String> agregarCargoEmpleado(String datos);

	ResponseEntity<String> agregarTrabajosEmpleado(String datos);

	ResponseEntity<String> eliminarEmpleadoTitulo(String idEmpleado);

	ResponseEntity<String> eliminarEmpleadoTrabajo(String idEmpleado);

	ResponseEntity<String> eliminarEmpleadoSalud(String idEmpleado);

	ResponseEntity<String> eliminarEmpleadoCargo(String idEmpleado);
	
	ResponseEntity<String> validarEmpleadoPorDocumento(String documento);
	
	ResponseEntity<String> filtrarEmpleadPorDocumento(String documento, int mes, int annio,int dia);


}
