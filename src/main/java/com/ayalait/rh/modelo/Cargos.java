package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cargos")
public class Cargos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private int iddepartamento;
	private String cargo;
	private int estado;

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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Cargos() {
		super();
		// TODO Auto-generated constructor stub
	}

}
