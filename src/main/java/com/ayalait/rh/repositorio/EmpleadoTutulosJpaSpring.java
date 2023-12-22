package com.ayalait.rh.repositorio;



import org.springframework.data.jpa.repository.JpaRepository;


import com.ayalait.rh.modelo.*;

public interface EmpleadoTutulosJpaSpring extends JpaRepository<EmpleadoTitulos, Integer>{
	
	void deleteByIdempleado(String id);

	
}
