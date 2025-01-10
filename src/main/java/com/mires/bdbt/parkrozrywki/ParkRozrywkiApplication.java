package com.mires.bdbt.parkrozrywki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.mires.bdbt.parkrozrywki"
		}
)
public class ParkRozrywkiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParkRozrywkiApplication.class, args);
	}
}
