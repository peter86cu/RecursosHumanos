package com.ayalait.rh.dao;

import com.ayalait.rh.modelo.*;

import com.ayalait.rh.repositorio.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ParametrosUsuariosDaoImpl implements ParametrosUsuariosDao{

    

    @Autowired
    TipoDocJpaSpring daoTipoDoc;
    
    @Autowired
    PaisesJpaSpring daoPais;
    
    @Autowired
    DepartamentoJpa daoDepa;
    
    @Autowired
    CargoJpa daoCargo;
    
    @Autowired
    TipoCambioJpa daoTipoCambio;
    
    @Autowired
    HorarioTrabajoJpa daoHorario;
    
    
    @Autowired
    ParentescoJpa daoParentesco;
    
    @Autowired
	MesesPagoJpa daoMesesPago;
    
    @Autowired
    BancoJpa daoBanco;
    

	@Override
	public List<TipoDocumento> listadoTipoDoc() {		
		return daoTipoDoc.findAll();
	}

	@Override
	public List<Paises> listadoPaises() {
		return daoPais.findAll();
	}

	@Override
	public List<Departamentos> listadoDepartamentos() {
		return daoDepa.findDepartamentosActivos();
	}

	@Override
	public List<Cargos> listadoCargosPorDepartamento(int id) {
		return daoCargo.findByIddepartamento(id);
	}

	@Override
	public List<TipoCambioSueldo> listadoTipoCambioSueldo() {
		return daoTipoCambio.findAll();
	}

	@Override
	public List<HorarioTrabajo> listadoHorarioTrabajo() {
		return daoHorario.findHorarioTrabajo();
	}

	@Override
	public List<Parentesco> listadoParentesco() {
		return daoParentesco.findAll();
	}
	
	@Override
	public List<MesPago> listadoMesesPago(int estado) {
		return daoMesesPago.findByEstadoOrderByIdDesc(estado);
	}

	@Override
	public List<Banco> listadoBanco() {
		return daoBanco.findAll();
	}
}
