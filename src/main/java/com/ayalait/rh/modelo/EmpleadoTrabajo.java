package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado_trabajos")
public class EmpleadoTrabajo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String idempleado;
	private String trabajos;

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

	public String getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(String trabajos) {
		this.trabajos = trabajos;
	}

	public EmpleadoTrabajo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
