package com.ayalait.rh.repositorio;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ayalait.rh.modelo.HorarioLaboral;






public interface CalendarioHorarioEmpleadoJpa extends JpaRepository<HorarioLaboral, String>{
	
	@Query(value="SELECT cl.* FROM  calendariohorariolaboral cl JOIN calendarioempleado ce ON (ce.id_calendario=cl.id_calendario) WHERE ce.id_empleado=:idempleado AND cl.mes =:mes AND cl.annio=:anio", nativeQuery=true)
	List<Object[]> findByCalendarioPorEmpleadoyMes(String idempleado, int mes, int anio);
	
	@Query(value="SELECT cl.* FROM  calendariohorariolaboral cl JOIN calendarioempleado ce ON (ce.id_calendario=cl.id_calendario) WHERE ce.id_empleado=:idempleado AND cl.dia =:dia AND cl.mes =:mes AND cl.annio=:anio", nativeQuery=true)
	List<Object[]> findByCalendarioPorEmpleadoyMesyDia(String idempleado, int mes, int anio, int dia);
	
	@Query(value="SELECT * FROM calendariohorariolaboral WHERE mes=:mesB AND annio=:anioB", nativeQuery=true)
	List<HorarioLaboral> existeCalendarioMes( int mesB, int anioB);
	
	}
