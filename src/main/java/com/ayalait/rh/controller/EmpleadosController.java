package com.ayalait.rh.controller;


import javax.servlet.http.HttpServletRequest;

import com.ayalait.rh.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmpleadosController {
	@Autowired
	EmpleadoService service;

	@GetMapping(value = "empleado/lista", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoDeEmpleados(HttpServletRequest request) throws Exception {
		return service.recuperarEmpleados();
	}

	@GetMapping(value = "empleado/nuevos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoDeEmpleadosNuevos(@RequestParam("id") String id, HttpServletRequest request)
			throws Exception {
		return service.obtenerEmpleadoNuevos(id);

	}

	@GetMapping(value = "empleado/buscar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> recuperarEmpleado(@RequestParam("id") String id, HttpServletRequest request)
			throws Exception {
		return service.buscarEmpleado(id);

	}
	
	@GetMapping(value = "empleado/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> buscarEmpleadoPorDocumento(@RequestParam("documento") String documento,@RequestParam("mes") int mes,
			@RequestParam("annio") int annio,@RequestParam("dia") int dia,HttpServletRequest request)
			throws Exception {
		return service.filtrarEmpleadPorDocumento(documento,mes,annio,dia);

	}

	@PostMapping(value = "empleado/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarEmpleado(@RequestBody String datos, HttpServletRequest request)
			throws Exception {
		return service.agregarEmpleado(datos);

	}

	@PostMapping(value = "empleado/add/titulos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarTitulosEmpleado(@RequestBody String datos, HttpServletRequest request)
			throws Exception {

		return service.agregarTituloEmpleado(datos);

	}
	
	@PostMapping(value = "empleado/add/salud", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarSaludEmpleado(@RequestBody String datos, HttpServletRequest request)
			throws Exception {

		return service.agregarSaludEmpleado(datos);

	}
	
	@PostMapping(value = "empleado/add/trabajos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarTrabajosEmpleado(@RequestBody String datos, HttpServletRequest request)
			throws Exception {

		return service.agregarTrabajosEmpleado(datos);

	}
	
	@PostMapping(value = "empleado/add/cargo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarCargoEmpleado(@RequestBody String datos, HttpServletRequest request)
			throws Exception {

		return service.agregarCargoEmpleado(datos);

	}
	
	@PostMapping(value = "empleado/add/banco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> guardarBancoEmpleado(@RequestBody String datos, HttpServletRequest request)
			throws Exception {

		return service.agregarBancoEmpleado(datos);

	}
	
	@DeleteMapping(value="empleado/delete/titulo",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> eliminarEmpleadoTitulo(@RequestParam("id") String id) {
		
		return service.eliminarEmpleadoTitulo(id);
	}

	@DeleteMapping(value="empleado/delete/trabajo",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> eliminarEmpleadoTrabajo(@RequestParam("id") String id) {
		
		return service.eliminarEmpleadoTrabajo(id);
	}
	
	@DeleteMapping(value="empleado/delete/salud",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> eliminarEmpleadoSalud(@RequestParam("id") String id) {
		
		return service.eliminarEmpleadoSalud(id);
	}
	
	@DeleteMapping(value="empleado/delete/banco",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> eliminarEmpleadoBanco(@RequestParam("id") String id) {
		
		return service.eliminarEmpleadoBanco(id);
	}
	
	
	@DeleteMapping(value="empleado/delete/cargo",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> eliminarEmpleadoCargo(@RequestParam("id") String id) {
		
		return service.eliminarEmpleadoCargo(id);
	}
	
	@DeleteMapping(value="empleado/delete/empleado",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> eliminarEmpleado(@RequestParam("id") String id) {
		
		return service.eliminarEmpleado(id);
	}
	
	
	@GetMapping(value = "empleado/validar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> validarEmpleadoProrDocumento(@RequestParam("documento") String documento)
			throws Exception {

		return service.validarEmpleadoPorDocumento(documento);

	}
}
