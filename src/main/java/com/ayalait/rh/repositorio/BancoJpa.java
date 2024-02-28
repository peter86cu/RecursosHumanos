package com.ayalait.rh.repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ayalait.rh.modelo.*;

public interface BancoJpa extends JpaRepository<Banco, Integer>{
	

}
