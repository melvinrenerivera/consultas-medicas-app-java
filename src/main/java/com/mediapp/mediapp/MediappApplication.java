package com.mediapp.mediapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com.mediapp.mediapp.utils")
@SpringBootApplication
public class MediappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediappApplication.class, args);
	}

}
