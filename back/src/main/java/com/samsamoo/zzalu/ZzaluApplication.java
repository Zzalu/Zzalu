package com.samsamoo.zzalu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ZzaluApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzaluApplication.class, args);
	}

}