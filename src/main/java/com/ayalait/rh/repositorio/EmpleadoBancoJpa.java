package com.ayalait.rh.repositorio;




import org.springframework.data.jpa.repository.JpaRepository;


import com.ayalait.rh.modelo.*;

public interface EmpleadoBancoJpa extends JpaRepository<EmpleadoBanco, Integer>{
	
	void deleteByIdempleado(String id);


}
