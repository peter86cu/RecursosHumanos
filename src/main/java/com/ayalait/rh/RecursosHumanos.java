package com.ayalait.rh;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties
public class RecursosHumanos {

	
	 
	public static void main(String[] args) {
		SpringApplication.run(RecursosHumanos.class, args);
        
	}
	

/*@Bean
public TaskExecutor taskExecutor(TaskExecutorBuilder builder) {
  return builder.corePoolSize(2)
            .maxPoolSize(10)
            .queueCapacity(20)
            .keepAlive(Duration.ofSeconds(120))
            .build();
}*/

}
