package com.ayalait.rh.repositorio;





import org.springframework.data.jpa.repository.JpaRepository;

import com.ayalait.rh.modelo.CalendarioEmpleado;






public interface CalendarioEmpleadoJpa extends JpaRepository<CalendarioEmpleado, String>{
	
	CalendarioEmpleado findByIdempleado(String idempleado);
	
	}
