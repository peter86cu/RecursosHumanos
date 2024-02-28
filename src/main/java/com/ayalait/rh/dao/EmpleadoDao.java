package com.ayalait.rh.dao;

import java.util.List;

import com.ayalait.rh.modelo.*;

public interface EmpleadoDao {

	void agregarEmpleado(Empleado empleado);

	List<Empleado> devolverEmpleados();

	void eliminarEmpleado(String idEmpleado);

	void eliminarEmpleadoTitulo(String idEmpleado);

	void eliminarEmpleadoTrabajo(String idEmpleado);

	void eliminarEmpleadoSalud(String idEmpleado);

	void eliminarEmpleadoCargo(String idEmpleado);
	
	void eliminarEmpleadoBanco(String idEmpleado);

	Empleado validarEmpleadoPorDocumento(String documento);

	Empleado recuperarEmpleadoPorId(String idEmpleado);

	void actualizarEmpleado(Empleado empleado);

	List<Empleado> getEmpleadosPorIdEmpresa(String id);

	List<Empleado> getEmpleadosNuevos(String id);

	void agregarTitulosEmpleado(EmpleadoTitulos titulos);

	void agregarTrabajosEmpleado(EmpleadoTrabajo trabajo);

	void agregarSaludEmpleado(EmpleadoSalud salud);

	void agregarDatosCargo(EmpleadoCargo cargo);
	
	void agregarBancoCargo(EmpleadoBanco banco);
	
	List<Object[]> obtenerCalendarioEmpleado(String id,int mes, int anio);
	
	List<Object[]> obtenerCalendarioEmpleadoDia(String id,int dia, int mes, int anio);

}
