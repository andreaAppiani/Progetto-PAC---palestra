package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		new SpringApplicationBuilder(ClientApplication.class).headless(false).run(args);
	}
	@Override
    public void run(String... args) {
		AppView view = AppView.getView();
		AppController.getController(view);
	}

}
