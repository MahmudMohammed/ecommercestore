package com.example.ecommercestore;

import com.example.ecommercestore.customer.Customer;
import com.example.ecommercestore.customer.CustomerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class EcommercestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercestoreApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository){
		return args -> {
			var faker = new Faker();
			Random random = new Random();
			Customer customer = new Customer(
					faker.name().fullName(),
					faker.internet().emailAddress(),
					random.nextInt(16 , 99)
			);


			customerRepository.save(customer);
		};
	}

}
