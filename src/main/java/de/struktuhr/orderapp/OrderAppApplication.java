package de.struktuhr.orderapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.struktuhr.orderapp.entity.Customer;
import de.struktuhr.orderapp.repo.CustomerRepository;
import org.springframework.scheduling.annotation.EnableAsync;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@EnableAsync
@SpringBootApplication
public class OrderAppApplication {

	private static final Logger log = LoggerFactory.getLogger(OrderAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OrderAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer", false, LocalDate.of(1980, Month.APRIL, 3), "Mr.", BigDecimal.valueOf(5_100.00)));
			repository.save(new Customer("Chloe", "O'Brian", true, LocalDate.of(1970, Month.JUNE, 10), "Ms.", BigDecimal.valueOf(3_250.80)));
			repository.save(new Customer("Kim", "Bauer", false, LocalDate.of(1995, Month.APRIL, 13), "Mrs.", BigDecimal.valueOf(6_100.00)));
			repository.save(new Customer("David", "Palmer", true, LocalDate.of(1960, Month.APRIL, 24), "Mr.", BigDecimal.valueOf(7_100.00)));
			repository.save(new Customer("Michelle", "Dessler", false, LocalDate.of(1974, Month.APRIL, 6), "Ms.", BigDecimal.valueOf(1_100.43)));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L).get();
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastNameStartsWithIgnoreCase("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}
}
