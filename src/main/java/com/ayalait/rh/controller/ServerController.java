package com.ayalait.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayalait.rh.RecursosHumanos;

@RestController
public class ServerController {

	@Autowired
	private ApplicationContext context;

	@GetMapping(value = "/server", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> validadServidorConeccion() {
		try {
			return new ResponseEntity<String>("Acceso disponible", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/restart")
	public ResponseEntity<String> restart() {

		try {
			Thread thread = new Thread(() -> {
				// Espera breve para permitir que la respuesta se envíe al cliente antes del
				// reinicio

				try {
					Thread.sleep(2000);

				} catch (InterruptedException e) {
					e.printStackTrace();

				}
				// Reinicio de la aplicación
				SpringApplication.exit(context, () -> 0);
				SpringApplication.run(RecursosHumanos.class);

			});
			thread.setDaemon(false);
			thread.start();

			return new ResponseEntity<String>("Aplicación reiniciada exitosamente", HttpStatus.OK);
		} catch (Exception e2) {
			return new ResponseEntity<String>(e2.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	
	
	 @PostMapping("/stop")
	    public void stop() {
	        Thread thread = new Thread(() -> {
	            // Espera breve para permitir que la respuesta se envíe al cliente antes de detener la aplicación
	            try {
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            // Detención de la aplicación
	            SpringApplication.exit(context, () -> 0);
	        });
	        thread.setDaemon(false);
	        thread.start();
	    }
	

}
