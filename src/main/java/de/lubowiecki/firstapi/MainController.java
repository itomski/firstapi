package de.lubowiecki.firstapi;

import de.lubowiecki.firstapi.products.Product;
import de.lubowiecki.firstapi.products.ProductRepository;
import de.lubowiecki.firstapi.products.ProductRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;
import java.util.Optional;

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
        ui.addAttribute("id", 0);
        ui.addAttribute("product", new ProductRequest("", "", "", "0", 0));
        return "standard";
    }

//    @PostMapping("/products/save")
//    public String saveProduct(String name, String description, String image, int quantity, double price) {
//        Product p = new Product(name, description, image, price, quantity);
//        productRepo.save(p);
//        return "redirect:/products"; // Umleitung
//    }

    @PostMapping("/products/save")
    public String saveProduct(@Valid ProductRequest product, BindingResult result, long id, Model ui) {
        if(result.hasErrors()) {
            ui.addAttribute("form", true);
            ui.addAttribute("product", product);
            ui.addAttribute("id", id);
            return "standard"; // Zurück zum Formular
        }
        else {
            Product p = product.toProduct();
            if (id > 0) {
                p.setId(id);
            }
            productRepo.save(p);
            return "redirect:/products";
        }
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        // TODO: Validieren
        productRepo.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable long id, Model ui) {
        Optional<Product> opt = productRepo.findById(id);
        if(opt.isPresent()) {
            // TODO: Formular füllen
            ui.addAttribute("form", true);
            ui.addAttribute("product", ProductRequest.fromProduct(opt.get()));
            ui.addAttribute("id", id);
            return "standard";
        }
        // TODO: Meldung, wenn ein Objekt mit gewünschter ID nicht verfügbar
        return "redirect:/products"; // Umleitung
    }
}
