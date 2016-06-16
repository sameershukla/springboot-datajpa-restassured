package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Sameer Shukla
 *
 *         Boostrap Spring
 */

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	/**
	 * You can initiate insertion here for demo.
	 */
	@Override
	public void run(String... arg0) throws Exception {
	}
}
