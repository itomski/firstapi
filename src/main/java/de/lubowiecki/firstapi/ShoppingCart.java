package de.lubowiecki.firstapi;

import de.lubowiecki.firstapi.products.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Service
//@SessionScope
public class ShoppingCart {

    private List<Product> productList = new ArrayList<>();

    public void add(Product product) {
        productList.add(product);
    }

    public void remove(Product product) {
        productList.remove(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(productList);
    }
}
