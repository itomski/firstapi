package de.lubowiecki.firstapi;

import de.lubowiecki.firstapi.products.Product;
import de.lubowiecki.firstapi.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired // Der Container sorgt dafür, dass hier immer ein gültiges Objekt verfügbar ist
    private ProductRepository repo;

//    public ProductsController(ProductRepository repo) {
//        this.repo = repo;
//    }

    @GetMapping
    public Iterable<Product> all() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        repo.save(product);
        return product;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable long id, @RequestBody Product product) {
        if(!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produkt nicht gefunden");
        }
        product.setId(id);
        return repo.save(product);
    }
}