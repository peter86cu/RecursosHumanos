package com.ayalait.rh.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;


import com.ayalait.rh.modelo.*;

public interface EmpleadoCargoJpa extends JpaRepository<EmpleadoCargo, Integer>{
	
	void deleteByIdempleado(String id);

	
}
