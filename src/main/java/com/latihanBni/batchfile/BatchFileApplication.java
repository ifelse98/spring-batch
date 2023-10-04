package com.latihanBni.batchfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BatchFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchFileApplication.class, args);
	}

}
