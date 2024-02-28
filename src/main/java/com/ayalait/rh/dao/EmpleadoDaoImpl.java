package com.ayalait.rh.dao;

import java.util.List;

import com.ayalait.rh.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayalait.rh.modelo.*;


@Repository
public class EmpleadoDaoImpl implements EmpleadoDao {
	@Autowired
	EmpleadoJpaSpring empleadoDao;
	
	@Autowired
	EmpleadoTutulosJpaSpring daoEmpTitulo;
	
	@Autowired
	EmpleadoSaludJpa daoSalud;
	
	@Autowired
	EmpleadoTrabajoJpa daoTrabajo;
	
	@Autowired
	EmpleadoCargoJpa daoCargo;
	
	@Autowired
	CalendarioEmpleadoJpa daoCalendario;
	
	@Autowired
	CalendarioHorarioEmpleadoJpa daoHorario;
	
	@Autowired
	EmpleadoBancoJpa daoEmplBanco;

	@Override
	public void agregarEmpleado(Empleado empleado) {
		empleadoDao.save(empleado);
		
	}

	@Override
	public List<Empleado> devolverEmpleados() {
		return empleadoDao.findAll();
	}

	@Override
	public void eliminarEmpleado(String idEmpleado) {
		 empleadoDao.deleteById(idEmpleado);
		
	}

	@Override
	public Empleado recuperarEmpleadoPorId(String idEmpleado) {		
		return empleadoDao.findById(idEmpleado).orElse(null);
	}

	@Override
	public void actualizarEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Empleado> getEmpleadosPorIdEmpresa(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleado> getEmpleadosNuevos(String id) {
		
		return empleadoDao.findEmpleadosSinUsuarios(id);
	}

	@Override
	public void agregarTitulosEmpleado(EmpleadoTitulos titulos) {
       daoEmpTitulo.save(titulos);
		
	}

	@Override
	public void agregarTrabajosEmpleado(EmpleadoTrabajo trabajo) {
		daoTrabajo.save(trabajo);
		
	}

	@Override
	public void agregarSaludEmpleado(EmpleadoSalud salud) {
		daoSalud.save(salud);
		
	}

	@Override
	public void agregarDatosCargo(EmpleadoCargo cargo) {
        daoCargo.save(cargo);		
	}

	@Override
	public void eliminarEmpleadoTitulo(String idEmpleado) {
		daoEmpTitulo.deleteByIdempleado(idEmpleado);		
	}

	@Override
	public void eliminarEmpleadoTrabajo(String idEmpleado) {
		daoTrabajo.deleteByIdempleado(idEmpleado);
		
	}

	@Override
	public void eliminarEmpleadoSalud(String idEmpleado) {
		daoSalud.deleteByIdempleado(idEmpleado);		
	}

	@Override
	public void eliminarEmpleadoCargo(String idEmpleado) {
        daoCargo.deleteByIdempleado(idEmpleado);		
	}

	@Override
	public Empleado validarEmpleadoPorDocumento(String documento) {
		return empleadoDao.findByDocumento(documento);
	}

	@Override
	public List<Object[]> obtenerCalendarioEmpleado(String id,int mes, int anio) {
		return daoHorario.findByCalendarioPorEmpleadoyMes(id, mes, anio);
	}

	@Override
	public List<Object[]> obtenerCalendarioEmpleadoDia(String id,int dia,int mes, int anio) {
		return daoHorario.findByCalendarioPorEmpleadoyMesyDia(id, mes, anio, dia);
	}

	@Override
	public void agregarBancoCargo(EmpleadoBanco banco) {
		daoEmplBanco.save(banco);
		
	}

	@Override
	public void eliminarEmpleadoBanco(String idEmpleado) {
		daoEmplBanco.deleteByIdempleado(idEmpleado);
	}

	

}
