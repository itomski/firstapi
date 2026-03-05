package de.lubowiecki.firstapi.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.text.NumberFormat;
import java.text.ParseException;

// Seit Java 14
public record ProductRequest(
        @NotEmpty @Size(min = 2, max = 100) String name,
        @NotEmpty @Size(min = 2, max = 1000) String description,
        @NotEmpty String img,
        @NotEmpty String price,
        @Min(value = 0) int quantity) {

    public double priceAsDouble() {
        NumberFormat fmt = NumberFormat.getNumberInstance(); // Automatisch lokalisiert
        try {
            return fmt.parse(price).doubleValue();
        } catch (ParseException e) {
            return 0.0;
        }
    }

    public Product toProduct() {
        return new Product(name, description, img, priceAsDouble(), quantity);
    }

    public static ProductRequest fromProduct(Product product) {
        return new ProductRequest(product.getName(), product.getDescription(), product.getImage(), product.getPrice() + "", product.getQuantity());
    }
}
