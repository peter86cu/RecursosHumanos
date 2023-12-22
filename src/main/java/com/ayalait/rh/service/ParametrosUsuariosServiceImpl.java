package com.ayalait.rh.service;

import com.ayalait.rh.dao.ParametrosUsuariosDao;
import com.ayalait.rh.modelo.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParametrosUsuariosServiceImpl implements ParametrosUsuariosService {

    @Autowired
    ParametrosUsuariosDao service;
   
    
	@Override
	public ResponseEntity<String> listadoTipoDocumento() {
		try {
			List<TipoDocumento> lstTipo= service.listadoTipoDoc();
			if(!lstTipo.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstTipo), HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("No existen tipos de documentos en la base de datos.",
                        HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);

		}
	}
	@Override
	public ResponseEntity<String> listadoPaises() {
		try {
			List<Paises> lstPais= service.listadoPaises();
			if(!lstPais.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstPais), HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No existen paises en la base de datos.",
                        HttpStatus.BAD_REQUEST);	
			}
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@Override
	public ResponseEntity<String> listadoDepartamentos() {
		try {
			List<Departamentos> lstDepa= service.listadoDepartamentos();
			if(!lstDepa.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstDepa), HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No existen departamentos en la base de datos.",
                        HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@Override
	public ResponseEntity<String> listadoCargos(int id) {
		try {
			List<Cargos> lstCargos= service.listadoCargosPorDepartamento(id);
			if(!lstCargos.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstCargos), HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No existen cargos en la base de datos.",
                        HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@Override
	public ResponseEntity<String> listadoTipoCambio() {
		try {
			List<TipoCambioSueldo> lstTipo= service.listadoTipoCambioSueldo();
			if(!lstTipo.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstTipo), HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No existen Tipo de Cambios en la base de datos.",
                        HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@Override
	public ResponseEntity<String> listadoHorarioTrabajo() {
		try {
			List<HorarioTrabajo> lstHorario=service.listadoHorarioTrabajo();
			if(!lstHorario.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstHorario), HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No existen Horarios de trabajo en la base de datos.",
                        HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@Override
	public ResponseEntity<String> listadoParenteco() {
		try {
			List<Parentesco> lstParentesco= service.listadoParentesco();
			if(!lstParentesco.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstParentesco), HttpStatus.OK);
	
			}else {
				return new ResponseEntity<String>("No existen parentescos en la base de datos.",
                        HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@Override
	public ResponseEntity<String> listadoMesesPago() {
		try {
			List<MesPago> lstMeses= service.listadoMesesPago(1);
			if(!lstMeses.isEmpty()) {
                return new ResponseEntity<String>(new Gson().toJson(lstMeses), HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No existen meses a pagar habilitadosen la base de datos.",
                        HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
