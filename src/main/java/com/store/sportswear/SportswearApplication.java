package com.store.sportswear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SportswearApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportswearApplication.class, args);
	}

}
