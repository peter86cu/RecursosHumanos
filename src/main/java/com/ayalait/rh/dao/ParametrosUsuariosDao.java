package com.ayalait.rh.dao;

import com.ayalait.rh.modelo.*;

import java.util.List;

public interface ParametrosUsuariosDao {

   
    List<TipoDocumento> listadoTipoDoc();
    
    List<Paises> listadoPaises();
    
    List<Departamentos> listadoDepartamentos();
    
    List<Cargos> listadoCargosPorDepartamento(int id);
    
   List<TipoCambioSueldo> listadoTipoCambioSueldo();
   
   List<HorarioTrabajo> listadoHorarioTrabajo();
   
   List<Parentesco> listadoParentesco();
   
	List<MesPago> listadoMesesPago(int estado);

}
