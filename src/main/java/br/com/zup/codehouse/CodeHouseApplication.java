package br.com.zup.codehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class CodeHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeHouseApplication.class, args);
	}

}
