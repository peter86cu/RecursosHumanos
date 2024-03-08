package com.ayalait.rh.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.ayalait.rh.modelo.*;
import com.ayalait.rh.vo.*;
import com.ayalait.utils.ErrorState;
import com.ayalait.rh.dao.*;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	private static final String RESULTADO_OK = "Datos guardados correctamente.";

	@Autowired
	EmpleadoDao dao;
	
	ErrorState error = new ErrorState();

	@Override
	public ResponseEntity<String> agregarEmpleado(String datos) {

		try {
			RequestAddEmpleado request = new Gson().fromJson(datos, RequestAddEmpleado.class);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			request.getEmpleado().setFechaingreso(formato.format(calendar.getTime()));
			dao.agregarEmpleado(request.getEmpleado());
			return new ResponseEntity<String>(RESULTADO_OK, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@Override
	public ResponseEntity<String> recuperarEmpleados() {

		try {

			List<Empleado> lstEmpleados = dao.devolverEmpleados();
			if (!lstEmpleados.isEmpty()) {
				return new ResponseEntity<String>(new Gson().toJson(lstEmpleados), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No existen usuarios en la base para el id de empresa.",
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);

		}
	}

	@Override
	public ResponseEntity<String> buscarEmpleado(String id) {

		try {
			Empleado empleado = dao.recuperarEmpleadoPorId(id);
			if (empleado != null) {
				return new ResponseEntity<String>(new Gson().toJson(empleado), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No se encontro ningun empleado con el ID: " + id,
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@Override
	public List<Empleado> obtenerEmpleadoPorIdEmpresa(String id) {
		return dao.getEmpleadosPorIdEmpresa(id);
	}

	@Override
	public ResponseEntity<String> eliminarEmpleado(String id) {
		try {
			dao.eliminarEmpleado(id);
			return new ResponseEntity<String>("Rollback empleado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> obtenerEmpleadoNuevos(String id) {

		try {
			List<Empleado> lstEmpleados = dao.getEmpleadosNuevos(id);
			return new ResponseEntity<String>(new Gson().toJson(lstEmpleados), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@Override
	public ResponseEntity<String> agregarTituloEmpleado(String datos) {
		try {
			EmpleadoTitulos request = new Gson().fromJson(datos, EmpleadoTitulos.class);
			dao.agregarTitulosEmpleado(request);
			return new ResponseEntity<String>("Datos guardados.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> agregarSaludEmpleado(String datos) {
		try {
			EmpleadoSalud reques = new Gson().fromJson(datos, EmpleadoSalud.class);
			dao.agregarSaludEmpleado(reques);
			return new ResponseEntity<String>("Datos guardados.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> agregarCargoEmpleado(String datos) {
		try {
			EmpleadoCargo request = new Gson().fromJson(datos, EmpleadoCargo.class);
			dao.agregarDatosCargo(request);
			return new ResponseEntity<String>("Datos guardados.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> agregarTrabajosEmpleado(String datos) {
		try {
			EmpleadoTrabajo request = new Gson().fromJson(datos, EmpleadoTrabajo.class);
			dao.agregarTrabajosEmpleado(request);
			return new ResponseEntity<String>("Datos guardados.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> eliminarEmpleadoTitulo(String idEmpleado) {
		try {
			dao.eliminarEmpleadoTitulo(idEmpleado);
			return new ResponseEntity<String>("Rollback título empleado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> eliminarEmpleadoTrabajo(String idEmpleado) {
		try {
			dao.eliminarEmpleadoTrabajo(idEmpleado);
			return new ResponseEntity<String>("Rollback trabajos empleado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> eliminarEmpleadoSalud(String idEmpleado) {
		try {
			dao.eliminarEmpleadoSalud(idEmpleado);
			return new ResponseEntity<String>("Rollback salud empleado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> eliminarEmpleadoCargo(String idEmpleado) {
		try {
			dao.eliminarEmpleadoCargo(idEmpleado);
			return new ResponseEntity<String>("Rollback cargo empleado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> validarEmpleadoPorDocumento(String documento) {
		try {
			Empleado empleado=dao.validarEmpleadoPorDocumento(documento);
			if(empleado!=null) {
				return new ResponseEntity<String>("Ya existe un empleado con el número de documento "+documento, HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<String>("No existe empleado con el número de documento "+documento, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> filtrarEmpleadPorDocumento(String documento,int mes, int annio,int dia) {
		try {
			Empleado empleado=dao.validarEmpleadoPorDocumento(documento);
			if(empleado!=null) {
				List<HorarioLaboral> lstValores= new ArrayList<HorarioLaboral>();
				List<Object[]>lstCalendario=null;
				if(dia==0) {
					lstCalendario= dao.obtenerCalendarioEmpleado(empleado.getIdempleado(), mes,annio);
				}else {
					lstCalendario= dao.obtenerCalendarioEmpleadoDia(empleado.getIdempleado(),dia, mes,annio);

				}
				if(!lstCalendario.isEmpty()) {
					
					for (int i = 0; i < lstCalendario.size(); i++) {
						HorarioLaboral horario = new HorarioLaboral();
						Object[] objArray = lstCalendario.get(i);
						horario.setId_horario((String)objArray[0]);
						horario.setDia_semana((String)objArray[3]);
						horario.setDia((int)objArray[2]);
						horario.setMes((int)objArray[6]);
						horario.setAnnio((int)objArray[1]);
						java.sql.Time sqlTimeI = (Time)objArray[4];
						horario.setHora_inicio(sqlTimeI.toString());
						java.sql.Time sqlTimeF = (Time)objArray[5];
						horario.setHora_fin(sqlTimeF.toString());
						//if((int)objArray[8]==1) {
							horario.setTrabaja((int)objArray[7]);
						//}else {
							//horario.setTrabaja(false);
						//}
						
						lstValores.add(horario);
					}
					
					
					
					return new ResponseEntity<String>(new Gson().toJson(lstValores), HttpStatus.OK);

				}else {
					error.setCode(8000);
					error.setMenssage("No existe calendario generado para el empleado "+empleado.getNombre()+" "+empleado.getApellidos());
					return new ResponseEntity<String>(new Gson().toJson(error), HttpStatus.BAD_REQUEST);

				}
			}else {
				return new ResponseEntity<String>("No existe empleado con el número de documento "+documento, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> agregarBancoEmpleado(String datos) {
		try {
			EmpleadoBanco request = new Gson().fromJson(datos, EmpleadoBanco.class);
			dao.agregarBancoCargo(request);
			return new ResponseEntity<String>("Datos guardados.", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> eliminarEmpleadoBanco(String idEmpleado) {
		try {
			dao.eliminarEmpleadoBanco(idEmpleado);
			return new ResponseEntity<String>("Rollback banco empleado", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
