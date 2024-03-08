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
    private String id_calendario;

    private String id_empleado;
    

    @OneToMany(mappedBy = "calendarioEmpleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioLaboral> horariosLaborales;

	


	public String getId_calendario() {
		return id_calendario;
	}

	public void setId_calendario(String id_calendario) {
		this.id_calendario = id_calendario;
	}

	public String getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}

	public List<HorarioLaboral> getHorariosLaborales() {
		return horariosLaborales;
	}

	public void setHorariosLaborales(List<HorarioLaboral> horariosLaborales) {
		this.horariosLaborales = horariosLaborales;
	}

	
	

	

	

}
