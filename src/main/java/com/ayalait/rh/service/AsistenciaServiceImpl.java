package com.ayalait.rh.service;



import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.ayalait.rh.modelo.*;
import com.ayalait.rh.repositorio.HorasExtraJpa;
import com.ayalait.rh.repositorio.ProcesarMarcaJpa;
import com.ayalait.rh.utils.CompositeMarcaEmpleado;
import com.ayalait.rh.utils.Resultado;
import com.ayalait.utils.ErrorState;

import com.ayalait.rh.dao.*;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {


	@Autowired
	AsistenciaDao dao;
	
	@Autowired
	ProcesarMarcaJpa daoProcMarca;
	
	@Autowired
	private HorasExtraJpa daoHorasExt;
	
	ErrorState error = new ErrorState();

	private Object marcasIterator;

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
	
	
	@Async
	@Override
	public ResponseEntity<String> procesarMarcaEmpleado(String datos) {
		try {
			MarcasEmpleado item = new Gson().fromJson(datos, MarcasEmpleado.class);
			MarcasProcesadas procesada = new MarcasProcesadas();
			if(item.getMarcaentrada()!=null && item.getMarcasalida()!=null) {
				
					Resultado resultado = new Resultado();
					
					marcasIterator = dao.findEmpleadoHorarios(item.getCompositeId().getIdEmpleado());
						Object[] objArray = (Object[]) marcasIterator;

						resultado.setIdempleado(objArray[0].toString());
						resultado.setHoras((int) objArray[1]);
					
					
					String fechaIni = item.getCompositeId().getFecha()+" "+ item.getMarcaentrada();
					String fechaFin = item.getCompositeId().getFecha()+" "+ item.getMarcasalida();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					// Convertir Date a LocalDateTime
					LocalDateTime fechaHoraIni = LocalDateTime.parse(fechaIni, formatter);
					LocalDateTime fechaHoraFin = LocalDateTime.parse(fechaFin, formatter);

					// Calcular la diferencia de tiempo entre las fechas
					Duration duracion = Duration.between(fechaHoraIni, fechaHoraFin);

					long horas = duracion.toHours();
					long minutos = duracion.toMinutes() % 60;
					long segundos = duracion.getSeconds() % 60;

					procesada.setMinutosprocesados((int) minutos);
					CompositeMarcaEmpleado ids= new CompositeMarcaEmpleado();
					ids.setIdempleado(item.getCompositeId().getIdEmpleado());
					ids.setFecha(item.getCompositeId().getFecha());
					ids.setIdmarca(item.getCompositeId().getIdmarca());
					
					procesada.setCompositeId(ids);
					// procesada.setEstado("OK");
					procesada.setHorasprocesadas((int) horas);
					procesada.setSegundosprocesados((int) segundos);
					
					EmpleadoHorasExtra horaExtra= new EmpleadoHorasExtra();
					if(horas>resultado.getHoras()) {
						procesada.setEstado("INCONSISTENTE");
						item.setAccion("NOK");
						horaExtra.setId(item.getCompositeId().getIdmarca());
						horaExtra.setFecha(item.getCompositeId().getFecha());
						horaExtra.setHoras((int)horas);
						horaExtra.setMinutos((int)minutos);
						horaExtra.setSegundos((int)segundos);
						horaExtra.setEstado("SIN VALIDAR");
						horaExtra.setTipoausencia(1);
						horaExtra.setIdempleado(item.getCompositeId().getIdEmpleado());
						daoHorasExt.save(horaExtra);						
						dao.asistenciaEmpleado(item);

						int extra= (int) (resultado.getHoras()-horas);
						return new ResponseEntity<String>("Aprobar "+ extra +" horas extras. Validadas por: ", HttpStatus.ACCEPTED);

					}
					if(horas==resultado.getHoras() && minutos>=15) {
						procesada.setEstado("INCONSISTENTE");
						item.setAccion("NOK");
						item.setProceso("INCONSISTENTE");
					}
					if(horas==resultado.getHoras() && minutos<=15) {
						procesada.setEstado("CORRECTA");
						item.setAccion("CORRECTA");
						item.setProceso("PROCESADA");
					}
					if(horas<resultado.getHoras() ) {
						procesada.setEstado("INCONSISTENTE");
						item.setAccion("NOK");
						item.setProceso("SIN PROCESAR");
					}
					
					dao.asistenciaEmpleado(item);
				
				if(procesada.getEstado().equalsIgnoreCase("CORRECTA")) {
					daoProcMarca.save(procesada);

					return new ResponseEntity<String>("Las marcas para la fecha: "+item.getCompositeId().getFecha()+" se estan procesando correctamente.", HttpStatus.OK);

				}else {
					error.setCode(3002);
					return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);

				}

					
				
			}else {
				error.setCode(3003);
				return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);

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
				error.setCode(3001);
				return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);
	
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
}
