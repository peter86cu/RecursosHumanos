package com.ayalait.rh.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "calendarioempleado")
public class CalendarioEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idCalendario;

    private String idempleado;
    

    @OneToMany(mappedBy = "calendarioEmpleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioLaboral> horariosLaborales;

	public String getIdCalendario() {
		return idCalendario;
	}

	public void setIdCalendario(String idCalendario) {
		this.idCalendario = idCalendario;
	}

	public String getIdempleado() {
		return idempleado;
	}

	public void setIdempleado(String idempleado) {
		this.idempleado = idempleado;
	}

	public List<HorarioLaboral> getHorariosLaborales() {
		return horariosLaborales;
	}

	public void setHorariosLaborales(List<HorarioLaboral> horariosLaborales) {
		this.horariosLaborales = horariosLaborales;
	}

	
	

	

	

}
