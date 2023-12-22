package com.ayalait.rh.utils;

import java.util.GregorianCalendar;

public class Utils {

	public static void main(String[] args) {
		System.out.println(numeroDeDiasMes("3", 2023));

	}
	
	
	public static int numeroDeDiasMes(String mes, int anio) {
		 
	    int numeroDias = -1;
	    switch (mes.toLowerCase().trim()) {
	        case "1":
	        case "3":
	        case "5":
	        case "7":
	        case "8":
	        case "10":
	        case "12":
	            numeroDias = 31;
	            break;
	        case "4":
	        case "6":
	        case "9":
	        case "11":
	            numeroDias = 30;
	            break;
	        case "2":
	 
	            if (esBisiesto(anio)) {
	                numeroDias = 29;
	            } else {
	                numeroDias = 28;
	            }
	            break;
	 
	    }
	 
	    return numeroDias;
	}
	 
	/**
	 * Indica si un año es bisiesto o no
	 *
	 * @param anio Año
	 * @return True = es bisiesto
	 */
	public static boolean esBisiesto(int anio) {
	 
	    GregorianCalendar calendar = new GregorianCalendar();
	    boolean esBisiesto = false;
	    if (calendar.isLeapYear(anio)) {
	        esBisiesto = true;
	    }
	    return esBisiesto;
	 
	}

}
