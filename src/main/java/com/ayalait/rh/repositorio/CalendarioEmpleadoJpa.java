package com.ayalait.rh.repositorio;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayalait.rh.modelo.CalendarioEmpleado;






public interface CalendarioEmpleadoJpa extends JpaRepository<CalendarioEmpleado, String>{
	
	@Query(value="SELECT * FROM calendarioempleado  WHERE id_empleado=:idempleado", nativeQuery=true)
	CalendarioEmpleado findByIdempleado(String idempleado);
	
	}
