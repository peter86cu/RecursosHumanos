package com.ayalait.rh.controller;


import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import com.ayalait.rh.service.*;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AsistenciaController {
	@Autowired
	AsistenciaService service;

	

	@GetMapping(value = "empleado/marcas/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> recuperarEmpleado(@RequestParam("id") String id, @RequestParam("fecha") Date fecha, HttpServletRequest request)
			throws Exception {
		return service.obtenerMarcasPorEmpleadoDia(id,fecha);

	}

	@PostMapping(value = "empleado/marcas/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarEmpleado(@RequestBody String datos, HttpServletRequest request)
			throws Exception {
		return service.asistenciaEmpleado(datos);

	}
	
	@PostMapping(value = "empleado/marcas/procesar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> procesarMarcasPorMes(@RequestParam("mes") int mes,@RequestParam("anio") int anio,HttpServletRequest request)
			throws Exception {
		try {
			return service.procesarMarcas(mes, anio);
		} catch (Exception e) {
			
		}
		return null;
		

	}
	
	@PostMapping(value = "empleado/marcas/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> filtrarMarca(@RequestParam("mes") int mes,@RequestParam("anio") int anio,
			@RequestParam("estado") String estado,HttpServletRequest request)
			throws Exception {
		return service.listadoMarcasFiltro(mes, anio, estado);

	}
	
	@PostMapping(value = "empleado/marcas/procesar-empleado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> procesarMarcaManualEmpleado(@RequestBody String datos,HttpServletRequest request)
			throws Exception {
		return service.procesarMarcaEmpleado(datos);

	}

	@PostMapping(value = "empleado/marcas/obtener-marcas-empleado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> listadoMarcasEmpleadosDocumento(@RequestParam("documento") String documento,@RequestParam("mes") int mes,@RequestParam("anio") int anio,HttpServletRequest request)
			throws Exception {
		return service.listadoMarcasEmpleadoPorFecha(documento,mes, anio);

	}
	
	@Async
	@PostMapping(value = "empleado/calendario/generar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> generarCalendarioEmpleado(@RequestParam("accion") String accion,
			@RequestParam("mes") int mes,@RequestParam("anio") int anio,HttpServletRequest request)
			throws Exception {		 
				service.generarCalendarioEmpleado(accion,mes, anio);
				Thread.sleep(1000L);
				return new ResponseEntity<String>(new Gson().toJson("Se esta procesando el calendario de empleados para la fecha: "),HttpStatus.OK);

	}
	
	@GetMapping(value = "calendario/mes-a-procesar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerMesAProcesar(HttpServletRequest request)
			throws Exception {
		return service.obtenerMesAProcesar();

	}
	

	@PostMapping(value = "calendario/existe-mes-procesar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> existeMesAprocesar(@RequestParam("mes") int mes,@RequestParam("anio") int anio,HttpServletRequest request)
			throws Exception {
		return service.existeMesProcesado(mes, anio);

	}
	
	/*@DeleteMapping(value="empleado/delete/cargo",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> eliminarEmpleadoCargo(@RequestParam("id") String id) {
		
		return service.eliminarEmpleadoCargo(id);
	}*/
	
	
}
