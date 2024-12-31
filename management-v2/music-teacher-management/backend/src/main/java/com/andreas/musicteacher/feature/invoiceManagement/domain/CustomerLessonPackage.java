package com.andreas.musicteacher.feature.invoiceManagement.domain;


import java.util.List;

public class CustomerLessonPackage {
    private Customer customer;
    private List<Lesson> lessons;

    public CustomerLessonPackage(Customer customer, List<Lesson> lessons) {
        this.customer = customer;
        this.lessons = lessons;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}

