package com.ayalait.rh.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ayalait.rh.modelo.*;

public interface EmpleadoJpaSpring extends JpaRepository<Empleado, String>{
	
	
	@Query(value="SELECT t1.* FROM empleado t1 WHERE NOT EXISTS (SELECT NULL FROM usuarios t2  WHERE t2.idempleado = t1.idempleado) AND t1.fechaegreso IS NULL AND t1.idempresa=:id", nativeQuery=true)
	List<Empleado> findEmpleadosSinUsuarios(@Param("id") String id);
	
	Empleado findByDocumento(String documento);
	
	List<Empleado>findByFechaegresoIsNull();
	
	@Query(value="SELECT e.idempleado,c.horas, ht.valor,ht.horastrabajo FROM empleado e JOIN empleado_cargo ec ON (e.idempleado=ec.idempleado) JOIN cargos c ON (ec.idcargo=c.id) JOIN horario_trabajo ht ON (ht.id=ec.idhorario) AND e.fechaegreso IS NULL", nativeQuery=true)
    List<Object> findAllEmpleadoHorarios();
	
	@Query(value="SELECT e.idempleado,c.horas FROM empleado e JOIN empleado_cargo ec ON (e.idempleado=ec.idempleado) JOIN cargos c ON (ec.idcargo=c.id) JOIN horario_trabajo ht ON (ht.id=ec.idhorario) where e.idempleado=:id", nativeQuery=true)
    List<Object> findEmpleadoHorarios(String id);
}
