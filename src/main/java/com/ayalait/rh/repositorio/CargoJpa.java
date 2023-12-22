package com.ayalait.rh.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ayalait.rh.modelo.*;

public interface CargoJpa extends JpaRepository<Cargos, Integer>{
	
	@Query(value="SELECT cargos.* FROM cargos INNER JOIN departamentos ON (departamentos.id=cargos.iddepartamento) WHERE departamentos.id=:id and cargos.estado=1", nativeQuery=true)
	List<Cargos> findCargosPorDepartamento(@Param("id") int id);
	
	List<Cargos> findByIddepartamento(int id);
}
