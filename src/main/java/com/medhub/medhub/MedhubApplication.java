package com.medhub.medhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MedhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedhubApplication.class, args);
	}

}
