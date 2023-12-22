package com.ayalait.rh.repositorio;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ayalait.rh.modelo.*;

public interface MesesPagoJpa extends JpaRepository<MesPago, Integer>{
	
	
	List<MesPago> findByEstado(int estado);
	
	}
