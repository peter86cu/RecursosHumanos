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
}
