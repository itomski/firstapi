package de.lubowiecki.firstapi;

import de.lubowiecki.firstapi.products.Product;
import de.lubowiecki.firstapi.products.ProductRepository;
import de.lubowiecki.firstapi.products.ProductRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
public class FirstapiApplication {

	@Value("${db.reset}") // Sucht nach einem Wert mit dieser Bezeichnung in der application.properties
	private boolean resetDb;

	public static void main(String[] args) {
		SpringApplication.run(FirstapiApplication.class, args);
	}

	@Bean // Wird durch den Spring-Container verwaltet
	// @Scope("singleton") // Es existiert IMMER nur ein Objekt davon
	// @RequestScope // Jeder Request erzeugt ein neues Objekt
	@SessionScope // Pro Besuch und User wird ein neues Objekt erzeugt
	// @ApplicationScope // Pro Server-Start und für alle User gemeinsam wird ein neues Objekt erzeugt
	public ShoppingCart shoppingCart() {
		return new ShoppingCart();
	}

	// Führt beim Start bestimmte Aufgaben über die Console aus
	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {

		return str -> {
			if(resetDb) {
				productRepository.deleteAll();
				productRepository.save(new Product("Tasse, gelb", "...", "xyz1.jpg", 7.99, 10));
				productRepository.save(new Product("Tasse, blau", "...", "xyz2.jpg", 6.99, 10));
				productRepository.save(new Product("Tasse, schwarz", "...", "xyz3.jpg", 5.99, 10));
			}
		};
	}
}