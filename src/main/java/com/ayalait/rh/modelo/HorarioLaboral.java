package com.ayalait.rh.modelo;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calendariohorariolaboral")
public class HorarioLaboral implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_horario;

	private String dia_semana;

	private String hora_inicio;

	private String hora_fin;

	private int trabaja;

	private int dia;
	private int mes;
	private int annio;

	@ManyToOne
	@JoinColumn(name = "id_calendario")
	private CalendarioEmpleado calendarioEmpleado;

	
	public String getId_horario() {
		return id_horario;
	}

	public void setId_horario(String id_horario) {
		this.id_horario = id_horario;
	}

	public String getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public String getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}

	public int getTrabaja() {
		return trabaja;
	}

	public CalendarioEmpleado getCalendarioEmpleado() {
		return calendarioEmpleado;
	}

	public void setCalendarioEmpleado(CalendarioEmpleado calendarioEmpleado) {
		this.calendarioEmpleado = calendarioEmpleado;
	}

	public int isTrabaja() {
		return trabaja;
	}

	public void setTrabaja(int trabaja) {
		this.trabaja = trabaja;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnnio() {
		return annio;
	}

	public void setAnnio(int annio) {
		this.annio = annio;
	}

}
