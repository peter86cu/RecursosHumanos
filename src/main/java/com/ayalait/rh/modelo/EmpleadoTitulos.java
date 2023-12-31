package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado_profesion")
public class EmpleadoTitulos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String idempleado;
	private int idtitulo;
	private String nombre_archivo;
	private String archivo;
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
	public int getIdtitulo() {
		return idtitulo;
	}
	public void setIdtitulo(int idtitulo) {
		this.idtitulo = idtitulo;
	}
	public String getNombre_archivo() {
		return nombre_archivo;
	}
	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public EmpleadoTitulos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
