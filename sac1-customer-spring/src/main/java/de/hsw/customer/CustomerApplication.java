package de.hsw.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import de.hsw.customer.beans.Customer;
import de.hsw.customer.services.CustomerService;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Component
	public final class IbanRunner implements CommandLineRunner {
		private CustomerService service;

		@Autowired
		public IbanRunner(CustomerService service) {
			this.service = service;
		}

		@Override
		public void run(String... args) throws Exception {
			service.save(new Customer(1, "Max Mustermann", "Musterstr. 1, 12345 Musterstadt", "DE75888888880000012345"));
			service.save(new Customer(2, "Max Mustermann", "Musterstr. 1, 12345 Musterstadt", "DE75888888880000012345"));
			service.save(new Customer(3, "Max Mustermann", "Musterstr. 1, 12345 Musterstadt", "DE75888888880000012345"));
			service.save(new Customer(4, "Max Mustermann", "Musterstr. 1, 12345 Musterstadt", "DE75888888880000012345"));
			service.save(new Customer(5, "Max Mustermann", "Musterstr. 1, 12345 Musterstadt", "DE75888888880000012345"));
		}

	}
}
