package com.example.randomshipgenerator;

import com.example.randomshipgeneratorcontroller.ShipController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = ShipController.class)
public class RandomShipGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomShipGeneratorApplication.class, args);
	}

}
