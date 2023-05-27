package com.currantino.rfidserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class RfidServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RfidServerApplication.class, args);
	}
}
