package com.ayalait.rh.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ayalait.rh.modelo.*;

public interface ParentescoJpa extends JpaRepository<Parentesco, Integer>{
	
	
	List<Cargos> findByEstado(int id);
}
