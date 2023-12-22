package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado_salud")
public class EmpleadoSalud implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String idempleado;
	private String nombre;
	private String contacto;
	private int vencimiento_carne;
	
	
	
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



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getContacto() {
		return contacto;
	}



	public void setContacto(String contacto) {
		this.contacto = contacto;
	}



	public int getVencimiento_carne() {
		return vencimiento_carne;
	}



	public void setVencimiento_carne(int vencimiento_carne) {
		this.vencimiento_carne = vencimiento_carne;
	}



	public EmpleadoSalud() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
