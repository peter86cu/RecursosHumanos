package com.ayalait.rh.repositorio;




import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ayalait.rh.modelo.*;

public interface MarcasProcesadasJpa extends CrudRepository<MarcasProcesadas, CompositeIdMarcasEmpleado>{
	
	
	
	@Query(value="SELECT e.documento, CONCAT (e.nombre ,\" \", e.apellidos) AS nombreempleado,em.fecha, em.estado,SUM(em.horasprocesadas) AS horas, SUM(em.minutosprocesados)  as minutos FROM empleado e JOIN empleado_marcas_procesadas em ON (e.idempleado=em.idempleado)  \r\n"
			+ "WHERE  MONTH(em.fecha) = :mes AND YEAR(em.fecha) = :anio and e.documento =:documento  GROUP BY nombreempleado, e.idempleado,em.fecha,em.idmarca", nativeQuery=true)
	List<Object> findEmpleadoHorarios(String documento,int mes, int anio);
	
	
	}
