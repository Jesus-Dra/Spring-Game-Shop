package com.ShopGameBD.ShopGamePostgre;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
public class ShopGamePostgreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopGamePostgreApplication.class, args);
	}

}
