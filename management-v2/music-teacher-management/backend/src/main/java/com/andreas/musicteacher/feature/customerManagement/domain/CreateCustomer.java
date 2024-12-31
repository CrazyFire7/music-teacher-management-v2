package com.andreas.musicteacher.feature.customerManagement.domain;

public class CreateCustomer {
    private String firstName;
    private String lastName;
    private String email;
    private String strasse;
    private String postleitzahl;
    private String ort;

    public CreateCustomer() {}


    public CreateCustomer(String lastName, String firstName, String email, String strasse, String postleitzahl, String ort) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.strasse = strasse;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
