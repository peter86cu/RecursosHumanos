package com.ayalait.rh.service;



import java.sql.Date;
import java.time.Month;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.ayalait.rh.modelo.*;
import com.ayalait.rh.dao.*;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {


	@Autowired
	AsistenciaDao dao;

	@Override
	public ResponseEntity<String> asistenciaEmpleado(String datos) {
		try {
			MarcasEmpleado request= new Gson().fromJson(datos, MarcasEmpleado.class);
			//Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
			////CompositeIdMarcasEmpleado ids= request.getCompositeId();
			//request.setCompositeId(ids);
			dao.asistenciaEmpleado(request);
			return new ResponseEntity<String>("Marca registrada correctamente.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> obtenerMarcasPorEmpleadoDia(String idEmpleado, Date fecha) {
		try {
			MarcasEmpleado marcas= dao.obtenerMarcasEmpleadoDia(idEmpleado, fecha);
			if(marcas!=null) {
				return new ResponseEntity<String>(new Gson().toJson(marcas), HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No se registraron marcas para la fecha solicitada.", HttpStatus.BAD_REQUEST);

			}

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Async
	@Override
	public ResponseEntity<String> procesarMarcas(int mes, int anio, String estado) {
		try {
			List<MarcasEmpleado> lstMarcas= dao.listadoMarcasPorFecha(mes,anio, estado);
			if(!lstMarcas.isEmpty()) {
				for(MarcasEmpleado marca : lstMarcas) {
					if(marca.getMarcaentrada().equalsIgnoreCase("") || marca.getMarcasalida()==null) {
						marca.setEstado("INCONSISTENTE");
						marca.setProceso("PROCESADA");
						dao.asistenciaEmpleado(marca);
					}
				}
				Thread.sleep(1000L);
				return new ResponseEntity<String>("Las marcas para la fecha: "+mes+"-"+anio + " se estan procesando correctamente.", HttpStatus.OK);

				
			}else {
				return new ResponseEntity<String>("No se registraron marcas para la fecha solicitada.", HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> listadoMarcasFiltro(int mes, int anio, String estado) {
		try {
			List<MarcasEmpleado> lstMarcas= dao.listadoMarcasFiltro(mes, anio, estado);
			if(!lstMarcas.isEmpty()) {
				return new ResponseEntity<String>(new Gson().toJson(lstMarcas), HttpStatus.OK);

			}else {
				return new ResponseEntity<String>("No se encontraron registros para el filtro de busqueda.", HttpStatus.BAD_REQUEST);
	
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
}
