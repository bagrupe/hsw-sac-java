package de.hsw.iban;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class IbanApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbanApplication.class, args);
	}

	@Component
	public final class IbanRunner implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
		}

	}
}
