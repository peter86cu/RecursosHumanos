package com.ayalait.rh.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;


import com.ayalait.rh.modelo.*;

public interface EmpleadoTrabajoJpa extends JpaRepository<EmpleadoTrabajo, Integer>{
	
	void deleteByIdempleado(String id);

	
}
