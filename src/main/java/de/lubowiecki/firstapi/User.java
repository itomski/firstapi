package de.lubowiecki.firstapi;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class User {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @JsonIgnore // Wird bei der Ausgabe als JSON nicht verwendet
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, LocalDate birthDate, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
