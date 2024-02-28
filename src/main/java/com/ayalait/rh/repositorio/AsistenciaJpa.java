package com.ayalait.rh.repositorio;




import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ayalait.rh.modelo.*;

public interface AsistenciaJpa extends CrudRepository<MarcasEmpleado, CompositeIdMarcasEmpleado>{
	
	@Query(value="SELECT * FROM empleado_asistencia  WHERE idempleado =:idEmpleado AND fecha=:fecha", nativeQuery=true)
	MarcasEmpleado findByCompositeId(String idEmpleado, Date fecha);
	
	//List<MarcasEmpleado> findByFecha(Date fecha);
	
	@Query(value="SELECT * FROM empleado_asistencia WHERE MONTH(fecha) = :mes AND YEAR(fecha) = :anio AND proceso=:estado", nativeQuery=true)
	List<MarcasEmpleado> findMarcasAProcesar(@Param("mes") int mes,@Param("anio") int anio, @Param("estado") String estado );
	
	@Query(value="SELECT * FROM empleado_asistencia WHERE estado=:estado AND MONTH(fecha) =:mes AND YEAR(fecha) =:anio", nativeQuery=true)
	List<MarcasEmpleado> findMarcasPorFiltro(@Param("mes") int mes,@Param("anio") int anio, @Param("estado") String estado);
	
	@Query(value="SELECT * FROM empleado_asistencia WHERE MONTH(fecha) =:mes AND YEAR(fecha) =:anio", nativeQuery=true)
	List<MarcasEmpleado> findMarcasPorFiltroAll(@Param("mes") int mes,@Param("anio") int anio);
	
	
	@Query(value="SELECT e.idempleado,c.horas FROM empleado e JOIN empleado_cargo ec ON (e.idempleado=ec.idempleado) JOIN cargos c ON (ec.idcargo=c.id) JOIN horario_trabajo ht ON (ht.id=ec.idhorario) where e.idempleado=:id", nativeQuery=true)
    Object findEmpleadoHorarios(String id);
	
	
	}
