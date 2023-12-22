package com.ayalait.rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CacheController {

	 @Autowired
	    private CacheManager cacheManager;      // autowire cache manager
	    // clear all cache using cache manager
	    @RequestMapping(value = "clearCache")
	    public ResponseEntity<String> clearCache(){
	        for(String name:cacheManager.getCacheNames()){
	            cacheManager.getCache(name).clear();            
	        }
			return new ResponseEntity<String>("Cache limpia", HttpStatus.OK);

	    }
}
