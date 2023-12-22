package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompositeIdMarcasEmpleado implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String idempleado;
    private String fecha;
    private String idmarca;
    
	public String getIdEmpleado() {
		return idempleado;
	}
	public void setIdEmpleado(String idEmpleado) {
		this.idempleado = idEmpleado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public CompositeIdMarcasEmpleado() {
		super();
	}
	public String getIdmarca() {
		return idmarca;
	}
	public void setIdmarca(String idmarca) {
		this.idmarca = idmarca;
	}
    
    
}
