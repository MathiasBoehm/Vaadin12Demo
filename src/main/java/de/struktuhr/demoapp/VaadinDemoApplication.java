package de.struktuhr.demoapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.struktuhr.demoapp.entity.Customer;
import de.struktuhr.demoapp.repo.CustomerRepository;
import org.springframework.scheduling.annotation.EnableAsync;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@EnableAsync
@SpringBootApplication
public class VaadinDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(VaadinDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VaadinDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer", false,
					LocalDate.of(1980, Month.APRIL, 3), "Mr.", BigDecimal.valueOf(5_100.00),
					"https://randomuser.me/api/portraits/men/10.jpg"));

			repository.save(new Customer("Chloe", "O'Brian", true,
					LocalDate.of(1970, Month.JUNE, 10), "Ms.", BigDecimal.valueOf(3_250.80),
					"https://randomuser.me/api/portraits/women/24.jpg"));

			repository.save(new Customer("Kim", "Bauer", false,
					LocalDate.of(1995, Month.APRIL, 13), "Mrs.", BigDecimal.valueOf(6_100.00),
					"https://randomuser.me/api/portraits/women/88.jpg"));

			repository.save(new Customer("David", "Palmer", true,
					LocalDate.of(1960, Month.APRIL, 24), "Mr.", BigDecimal.valueOf(17_100.00),
					"https://randomuser.me/api/portraits/men/88.jpg"));

			repository.save(new Customer("Michelle", "Dessler", false,
					LocalDate.of(1974, Month.APRIL, 6), "Ms.", BigDecimal.valueOf(1_100.43),
					"https://randomuser.me/api/portraits/women/69.jpg"));

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
