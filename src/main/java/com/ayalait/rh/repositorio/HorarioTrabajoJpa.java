package com.ayalait.rh.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayalait.rh.modelo.*;

public interface HorarioTrabajoJpa extends JpaRepository<HorarioTrabajo, Integer>{
	
	@Query(value="SELECT * FROM horario_trabajo WHERE estado=1", nativeQuery=true)
	List<HorarioTrabajo> findHorarioTrabajo();
}
