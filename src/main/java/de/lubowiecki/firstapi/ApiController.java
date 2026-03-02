package de.lubowiecki.firstapi;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1") // Gilt für alle Anfrage-Methoden
public class ApiController {

    private List<User> users = new ArrayList<>();

    // HTTP-Methoden: GET, POST, PUT, DELETE, HEAD

    @GetMapping("/one/{nr}") // localhost:8080/one/2
    public User getOne(@PathVariable int nr) {
        return users.get(nr);
    }

    // @RequestMapping // Für alle HTTP-Methoden
    @GetMapping("/all") // localhost:8080/all
    public Iterable<User> getAll() {
        return users;
    }

//    @PostMapping("/add") // localhost:8080/add
//    public User addOne(String firstName, String lastName, String dateStr, String password) {
//        User u = new User(firstName, lastName, LocalDate.parse(dateStr), password);
//        users.add(u);
//        return u;
//    }

    @PostMapping("/add") // localhost:8080/add
    public User addOne(User user) { // Objekt wird automatisch geparst...
        users.add(user);
        return user;
    }
}
