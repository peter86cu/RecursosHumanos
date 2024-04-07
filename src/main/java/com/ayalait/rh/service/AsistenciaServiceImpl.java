package com.ayalait.rh.service;



import java.math.BigDecimal;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.ayalait.rh.modelo.*;
import com.ayalait.rh.repositorio.AsistenciaJpa;
import com.ayalait.rh.repositorio.CalendarioEmpleadoJpa;
import com.ayalait.rh.repositorio.CalendarioHorarioEmpleadoJpa;
import com.ayalait.rh.repositorio.EmpleadoJpaSpring;
import com.ayalait.rh.repositorio.FeriadosJPA;
import com.ayalait.rh.repositorio.HorasExtraJpa;
import com.ayalait.rh.repositorio.MarcasProcesadasJpa;
import com.ayalait.rh.repositorio.ProcesarMarcaJpa;
import com.ayalait.rh.utils.CompositeMarcaEmpleado;
import com.ayalait.rh.utils.Resultado;
import com.ayalait.rh.utils.ResultadoCalendario;
import com.ayalait.utils.ErrorState;
import com.ayalait.utils.MarcaEmpleadoProcess;
import com.ayalait.modelo.Feriados;
import com.ayalait.rh.dao.*;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {


	@Autowired
	AsistenciaDao dao;
	
	@Autowired
	ProcesarMarcaJpa daoProcMarca;
	
	@Autowired
	HorasExtraJpa daoHorasExt;
	
	ErrorState error = new ErrorState();

	private Object marcasIterator;
	
	private Iterator<Object> marcasProcess;
	
	@Autowired
    AsistenciaJpa respository;
	
	@Autowired
	MarcasProcesadasJpa daoMarcasProc;
	
	private Iterator<MarcasEmpleado> marcasIter;
	
	@Autowired
	EmpleadoJpaSpring respositoryCaled;

	private Iterator<Object> empleadoIterator;
	
	@Autowired
	FeriadosJPA repositoriFeriados;
	
	@Autowired
    CalendarioEmpleadoJpa respoCaldEmpl;
	
	@Autowired
	CalendarioHorarioEmpleadoJpa horarioLaboral;

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
	public ResponseEntity<String> procesarMarcas(int mes, int anio) {
		try {
			List<MarcasEmpleado> lstMarcas= dao.listadoMarcasPorFecha(mes,anio, "SIN PROCESAR");
			if(!lstMarcas.isEmpty()) {
				for(MarcasEmpleado marca : lstMarcas) {
					procesarMarcaEmpleado( new Gson().toJson(marca));
					
					
					/*if(marca.getMarcaentrada().equalsIgnoreCase("") || marca.getMarcasalida()==null) {
						marca.setEstado("INCONSISTENTE");
						marca.setProceso("PROCESADA");
						dao.asistenciaEmpleado(marca);
					}*/
				}
				Thread.sleep(1000L);
				return new ResponseEntity<String>("Las marcas para la fecha: "+mes+"-"+anio + " se enviaron a procesar.", HttpStatus.OK);

				
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
					long minutos = duracion.minusHours(horas).toMinutes() % 60;
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
						return new ResponseEntity<String>("Se ha generado "+ extra +" horas extras. Por favor debe gestionarlas. ", HttpStatus.ACCEPTED);

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
						item.setEstado("CORRECTA");
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

	@Override
	public ResponseEntity<String> listadoMarcasEmpleadoPorFecha(String documento, int mes, int anio) {
		try {
			marcasProcess = daoMarcasProc.findEmpleadoHorarios(documento,mes,anio).iterator();
			List<MarcaEmpleadoProcess> lstMarcas= new ArrayList<MarcaEmpleadoProcess>();
			if(marcasProcess != null && marcasProcess.hasNext()) {
				while( marcasProcess.hasNext()) {
				Object[] objArray = (Object[]) marcasProcess.next();
				MarcaEmpleadoProcess marcas = new MarcaEmpleadoProcess();
				marcas.setDocumento(objArray[0].toString());
				marcas.setNombreEmpleado(objArray[1].toString());
				marcas.setFecha(objArray[2].toString());
				marcas.setEstado(objArray[3].toString());
				marcas.setHoras(Integer.parseInt(objArray[4].toString()) );
				marcas.setMinutos(Integer.parseInt(objArray[5].toString()));
				lstMarcas.add(marcas);
				}
				return new ResponseEntity<String>(new Gson().toJson(lstMarcas), HttpStatus.OK);
			}else {
				error.setCode(80003);
				error.setMenssage("No existen marcas procesadas para el documento: "+documento);
				return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.BAD_REQUEST);
			}
			
			
		
		} catch (Exception e) {
			error.setCode(406);
			error.setMenssage(e.getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	
	@Async
	@Override
	public void generarCalendarioEmpleado(String accion,int mes, int anio) {
		try {
			if(accion.equalsIgnoreCase("reprocesar")) {
				List<HorarioLaboral> lstHorarios= horarioLaboral.existeCalendarioMes(mes, anio);
				for(HorarioLaboral horario: lstHorarios) {
					respoCaldEmpl.delete(horario.getCalendarioEmpleado());
					horarioLaboral.delete(horario);
				}
			}
			empleadoIterator = respositoryCaled.findAllEmpleadoHorarios().iterator();
			while (empleadoIterator != null && empleadoIterator.hasNext()) {
				Object[] objArray = (Object[]) empleadoIterator.next();
				ResultadoCalendario resultado = new ResultadoCalendario();

				resultado.setIdempleado(objArray[0].toString());
				resultado.setHoras((int) objArray[1]);
				resultado.setValor(objArray[2].toString());
				resultado.setHorastrabajo(objArray[3].toString());
				CalendarioEmpleado response= process(resultado,mes, anio);
				if(!response.getId_calendario().equalsIgnoreCase("") || !response.getHorariosLaborales().isEmpty()) {
					respoCaldEmpl.save(response);
				}
			} 
			

			
		} catch (Exception e) {
			error.setCode(406);
			error.setMenssage(e.getMessage());
			//return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@Async
	public CalendarioEmpleado process(ResultadoCalendario item, int mesP, int anioP) throws Exception {
		CalendarioEmpleado procesada = new CalendarioEmpleado();
		List<HorarioLaboral> horariosLaborales  = new ArrayList<HorarioLaboral>();
		LocalTime horaInicio= null;
		LocalTime horaFin=null;
		
		List<com.ayalait.rh.modelo.Feriados> lstFeriados=repositoriFeriados.findAll();
		
		if(item!=null) {
			String[] h= item.getHorastrabajo().split("-");
			String[] hInicio=h[0].split(":");
			String[] hFin=h[1].split(":");
			
			if(hInicio!=null) {
				 horaInicio = LocalTime.of(Integer.parseInt( hInicio[0]), Integer.parseInt( hInicio[1]));
			}
			if(hFin!=null) {
				 horaFin = LocalTime.of(Integer.parseInt( hFin[0]), Integer.parseInt( hFin[1]));

			}

			 // Obtener la fecha actual
	        LocalDate fechaActual = LocalDate.now();
	       
	        fechaActual = fechaActual.of(anioP, mesP, 1);
	        // Obtener el mes siguiente
	        //LocalDate fechaSiguiente = fechaActual.plusMonths(1);

	        // Obtener el mes y año del mes siguiente
	        /*Month mesSiguiente = fechaSiguiente.getMonth();
	        int anoSiguiente = fechaSiguiente.getYear();*/
	        
	        Month mesSiguiente = fechaActual.getMonth();
	        int anoSiguiente = fechaActual.getYear();

	        LocalDate startDate = LocalDate.of(anoSiguiente, mesSiguiente.getValue(), 1); // Primer día del mes
	        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth()); // Último día del mes

	        LocalDate currentDate = startDate;
	        procesada.setId_calendario(UUID.randomUUID().toString());;      
			procesada.setId_empleado(item.getIdempleado());
			//procesada.setIdempleado(item.getIdempleado());

			
			
	        while (!currentDate.isAfter(endDate)) {
	            System.out.println("Día: " + currentDate.getDayOfMonth());
	            //Obtiene dia de la semana
	            DayOfWeek dayOfWeek = currentDate.getDayOfWeek(); // Obtener el día de la semana
		        Locale spanishLocale = new Locale("es", "ES");
		        String displayName = dayOfWeek.getDisplayName(TextStyle.FULL, spanishLocale); // Obtener el nombre del día en español
		        int dia= currentDate.getDayOfMonth();
		        int mes= currentDate.getMonthValue();
		        int anio= currentDate.getDayOfMonth();
		        
		        
	            String[] diaTrabajo= item.getValor().split(",");
	            boolean encontrado = Arrays.stream(diaTrabajo)
	                    .anyMatch(valor -> valor.equals(displayName));

	            
	            
	            Optional<com.ayalait.rh.modelo.Feriados> resultado = lstFeriados.stream()
	                    .filter(numero -> numero.getDia() == dia &&  numero.getMes()== mes && numero.getAnio()==anoSiguiente)
	                    .findFirst();
	            
	           
	            HorarioLaboral laboral= new HorarioLaboral();
	            if(encontrado) {
	            	 // Realizar las operaciones necesarias para cada día
		           
		            laboral.setHora_fin(horaFin.toString());
		            laboral.setHora_inicio(horaInicio.toString());
		            laboral.setId_horario(UUID.randomUUID().toString());
		            laboral.setDia_semana(displayName);
		            laboral.setTrabaja(1);
	            	
	            }else {
	            	 // Realizar las operaciones necesarias para cada día
	            	laboral.setHora_fin(horaFin.toString());
		            laboral.setHora_inicio(horaInicio.toString());
		            laboral.setId_horario(UUID.randomUUID().toString());
		            laboral.setDia_semana(displayName);
		            laboral.setTrabaja(2);
	            }
	            if( resultado.isPresent()) {
		        	   laboral.setTrabaja(resultado.get().getEstado());
		           }
	            
	            
	            laboral.setAnnio(anoSiguiente);
	            laboral.setDia(currentDate.getDayOfMonth());
	            laboral.setMes( currentDate.getMonthValue());
	            laboral.setCalendarioEmpleado(procesada);

	            horariosLaborales.add(laboral);
	            
	           

	            currentDate = currentDate.plusDays(1); // Avanzar al siguiente día
	        }
			
	       
	        procesada.setHorariosLaborales(horariosLaborales);

		  
		  	
		}
		
		
		

		return procesada;
	}

	@Override
	public ResponseEntity<String> obtenerMesAProcesar() {
		try {
			CalendarioMesAProcesar c= dao.obtenerMesAProcesar();
			if(c!=null) {
				return new ResponseEntity<String>(new Gson().toJson(c),HttpStatus.OK);

			}else {
				error.setCode(90020);
				error.setMenssage("No se ha cargado mes a procesar. Consular a un administrador. ");
				return new ResponseEntity<String>(new Gson().toJson(error),HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {
			error.setCode(406);
			error.setMenssage(e.getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> existeMesProcesado(int mes, int anio) {
		try {
			List<HorarioLaboral> horario= horarioLaboral.existeCalendarioMes(mes, anio);
			if(horario.isEmpty()) {
				return new ResponseEntity<String>(new Gson().toJson(true),HttpStatus.OK);

			}else {
				return new ResponseEntity<String>(new Gson().toJson(false),HttpStatus.OK);

			}
		} catch (Exception e) {
			error.setCode(406);
			error.setMenssage(e.getMessage());
			return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	
}
