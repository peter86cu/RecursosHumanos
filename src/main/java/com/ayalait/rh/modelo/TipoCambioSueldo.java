package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_cambio_sueldo")
public class TipoCambioSueldo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String tipo;
	private int estado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public TipoCambioSueldo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
