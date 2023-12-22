package com.ayalait.rh.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayalait.rh.modelo.*;

public interface DepartamentoJpa extends JpaRepository<Departamentos, Integer>{
	
	
	@Query(value="SELECT * FROM departamentos WHERE estado=1", nativeQuery=true)
	List<Departamentos> findDepartamentosActivos();
	
}
