package com.ayalait.rh.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "historico_sueldo")
public class HitoricoSueldo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private int idcargo;
	private double sueldoactual;
	private double sueldopasado;
	private String fechainicio;
	private String fechafin;
	private String idusuario;
	private int tipocambio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdcargo() {
		return idcargo;
	}

	public void setIdcargo(int idcargo) {
		this.idcargo = idcargo;
	}

	public double getSueldoactual() {
		return sueldoactual;
	}

	public void setSueldoactual(double sueldoactual) {
		this.sueldoactual = sueldoactual;
	}

	public double getSueldopasado() {
		return sueldopasado;
	}

	public void setSueldopasado(double sueldopasado) {
		this.sueldopasado = sueldopasado;
	}

	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public int getTipocambio() {
		return tipocambio;
	}

	public void setTipocambio(int tipocambio) {
		this.tipocambio = tipocambio;
	}

	public HitoricoSueldo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
