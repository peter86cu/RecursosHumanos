package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado_cargo")
public class EmpleadoCargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private int iddepartamento;
	private String idempleado;
	private int idcargo;
	private int idhorario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIddepartamento() {
		return iddepartamento;
	}

	public void setIddepartamento(int iddepartamento) {
		this.iddepartamento = iddepartamento;
	}

	

	public String getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(String idempleado) {
		this.idempleado = idempleado;
	}

	public int getIdcargo() {
		return idcargo;
	}

	public void setIdcargo(int idcargo) {
		this.idcargo = idcargo;
	}

	public int getIdhorario() {
		return idhorario;
	}

	public void setIdhorario(int idhorario) {
		this.idhorario = idhorario;
	}

	public EmpleadoCargo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
