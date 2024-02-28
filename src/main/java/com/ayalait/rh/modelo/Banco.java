package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "banco")
public class Banco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idbanco;
	private String banco;
	private int estado;

	

	public int getIdbanco() {
		return idbanco;
	}



	public void setIdbanco(int idbanco) {
		this.idbanco = idbanco;
	}



	public String getBanco() {
		return banco;
	}



	public void setBanco(String banco) {
		this.banco = banco;
	}



	public int getEstado() {
		return estado;
	}



	public void setEstado(int estado) {
		this.estado = estado;
	}



	public Banco() {
		super();
		// TODO Auto-generated constructor stub
	}

}
