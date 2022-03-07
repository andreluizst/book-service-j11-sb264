package br.com.alst.softwares.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookServiceJ11Sb264Application {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceJ11Sb264Application.class, args);
	}

}
