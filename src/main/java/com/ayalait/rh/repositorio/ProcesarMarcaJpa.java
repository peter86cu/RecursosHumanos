package com.ayalait.rh.repositorio;





import org.springframework.data.jpa.repository.JpaRepository;

import com.ayalait.rh.modelo.MarcasProcesadas;




public interface ProcesarMarcaJpa extends JpaRepository<MarcasProcesadas, String>{
	
	
	}
