package com.ayalait.rh.service;

import org.springframework.http.ResponseEntity;

public interface ParametrosUsuariosService {

    
    ResponseEntity<String> listadoTipoDocumento();
    
    ResponseEntity<String> listadoPaises();
    
    ResponseEntity<String> listadoDepartamentos();
    
    ResponseEntity<String> listadoCargos(int id);
    
    ResponseEntity<String> listadoTipoCambio();
    
    ResponseEntity<String> listadoHorarioTrabajo();
    
    ResponseEntity<String> listadoParenteco();
    
    ResponseEntity<String> listadoMesesPago();


}
