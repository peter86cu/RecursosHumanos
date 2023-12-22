package com.ayalait.rh.controller;

import com.ayalait.rh.service.ParametrosUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParametrosUsuariosController {

	@Autowired
	ParametrosUsuariosService service;

	@GetMapping(value = "parametros/tipo-documento", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoTipoDoc() throws Exception {
		return service.listadoTipoDocumento();

	}

	@GetMapping(value = "parametros/paises", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoPaises() throws Exception {
		return service.listadoPaises();

	}
	
	
	@GetMapping(value = "parametros/Tipo-cambio", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoTipoCambio() throws Exception {
		return service.listadoTipoCambio();

	}
	
	@GetMapping(value = "parametros/horario-trabajo", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoHorarioTrabajo() throws Exception {
		return service.listadoHorarioTrabajo();

	}
	
	@GetMapping(value = "parametros/departamentos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoDepartamento() throws Exception {
		return service.listadoDepartamentos();

	}
	
	@GetMapping(value = "parametros/cargos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoCargos(@RequestParam("id") int id) throws Exception {
		return service.listadoCargos(id);

	}
	
	@GetMapping(value = "parametros/parentescos", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> listadoParentesco() throws Exception {
		return service.listadoParenteco();

	}
	
	@GetMapping(value = "parametros/mes-pago", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> mesesPago() throws Exception {
		return service.listadoMesesPago();

	}

}
