package de.lubowiecki.firstapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controller für HTML-Ausgabe
public class MainController {

    @GetMapping // http://localhost:8080
    public String startseite() {
        return "seite1"; // Name der HTML-Vorlage aus dem resources/templates-Ordner
    }

    @GetMapping("/kontakt") // http://localhost:8080/kontakt
    public String andereSeite() {
        return "seite2";
    }
}
