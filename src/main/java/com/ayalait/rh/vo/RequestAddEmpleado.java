package com.ayalait.rh.vo;

import java.io.Serializable;

import com.ayalait.rh.modelo.*;



public class RequestAddEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Empleado empleado;

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	

	
	

}
