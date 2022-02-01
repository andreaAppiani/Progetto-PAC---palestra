package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DashboardApplication implements CommandLineRunner{

	public static void main(String[] args) {
		new SpringApplicationBuilder(DashboardApplication.class).headless(false).run(args);
	}
	@Override
    public void run(String... args) {
		DashboardView view = DashboardView.getView();
		DashboardController.getController(view);
	}

}
