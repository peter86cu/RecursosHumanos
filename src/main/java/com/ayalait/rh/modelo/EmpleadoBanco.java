package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado_banco")
public class EmpleadoBanco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String idempleado;
	private int idformacobro;
	private int idbanco;
	private int idmoneda;
	private String cuenta;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getIdempleado() {
		return idempleado;
	}



	public void setIdempleado(String idempleado) {
		this.idempleado = idempleado;
	}



	public int getIdformacobro() {
		return idformacobro;
	}



	public void setIdformacobro(int idformacobro) {
		this.idformacobro = idformacobro;
	}



	public int getIdbanco() {
		return idbanco;
	}



	public void setIdbanco(int idbanco) {
		this.idbanco = idbanco;
	}



	public int getIdmoneda() {
		return idmoneda;
	}



	public void setIdmoneda(int idmoneda) {
		this.idmoneda = idmoneda;
	}



	public String getCuenta() {
		return cuenta;
	}



	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}



	public EmpleadoBanco() {
		super();
		// TODO Auto-generated constructor stub
	}

}
