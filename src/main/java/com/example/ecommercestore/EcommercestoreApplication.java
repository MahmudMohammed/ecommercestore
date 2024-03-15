package com.example.ecommercestore;

import com.example.ecommercestore.customer.Customer;
import com.example.ecommercestore.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EcommercestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercestoreApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository){
		return args -> {
			Customer alex = new Customer(1, "Alex", "alex@gmail.com", 21);
			Customer john = new Customer(2, "John", "john@gmail.com", 22);

			List<Customer> customers = List.of(alex,john);
			//customerRepository.saveAll(customers);
		};
	}

}
