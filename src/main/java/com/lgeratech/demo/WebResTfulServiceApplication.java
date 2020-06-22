package com.lgeratech.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class WebResTfulServiceApplication implements CommandLineRunner {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(WebResTfulServiceApplication.class, args);
	}
	@Override
	public void run(String... strings) throws Exception {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS user(id SERIAL, name VARCHAR(255), age INT(8), gender VARCHAR(255))");
	}

}
