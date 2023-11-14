package com.library.tijoLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.library.tijoLibrary")
public class TijoLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TijoLibraryApplication.class, args);
	}

}
