package com.ayalait.rh;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CachingConfig {
 
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("tipoProducto","categorias","um","impuesto",
        		"monedas","formasPagos","plazos","defecto","depositos","turnos","tipoCuadre","cajas","tipoArqueo","billetes",
        		"configuraciones","formaCobro","cliente","estadosUsuarios");
    }
    
    
}