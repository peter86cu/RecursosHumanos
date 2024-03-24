package com.ayalait.rh.repositorio;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ayalait.rh.modelo.CalendarioMesAProcesar;

public interface CalendarioAProcesarJpa extends CrudRepository<CalendarioMesAProcesar, Integer>{
	
	
	@Query(value="SELECT * FROM calendariomesprocesar  WHERE estado=1", nativeQuery=true)
	CalendarioMesAProcesar findCalendarioAProcesar();
	
	}
