package de.lubowiecki.firstapi.products;

import jakarta.persistence.*;

@Entity
@Table(name = "products") // Tabellenname
public class Product {

    @Id // Primärschlüssel
    @GeneratedValue // Autoincrement
    private long id;

    @Column(length = 100)
    private String name;

    private String description;

    private String image;

    private double price;

    private int quantity;

    public Product() {
    }

    public Product(String name, String description, String image, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
