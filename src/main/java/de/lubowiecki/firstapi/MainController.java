package de.lubowiecki.firstapi;

import de.lubowiecki.firstapi.products.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller // Controller für HTML-Ausgabe
public class MainController {

    private ProductRepository productRepo;

    // Wird automatisch vom Spring-Container aufgerufen
    public MainController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping // http://localhost:8080
    public String startseite(Model ui) { // Model transportiert Werte der Methode an die HTML-Vorlage
        ui.addAttribute("title", "Startseite");
        return "standard"; // Name der HTML-Vorlage aus dem resources/templates-Ordner
    }

    @GetMapping("/products") // http://localhost:8080/products
    public String productList(Model ui) {
        ui.addAttribute("title", "Produkte");
        ui.addAttribute("productList", productRepo.findAll());
        return "standard";
    }

    @GetMapping("/products/new") // http://localhost:8080/products/new
    public String productForm(Model ui) {
        ui.addAttribute("title", "Neues Produkt");
        ui.addAttribute("form", true);
        return "standard";
    }
}
