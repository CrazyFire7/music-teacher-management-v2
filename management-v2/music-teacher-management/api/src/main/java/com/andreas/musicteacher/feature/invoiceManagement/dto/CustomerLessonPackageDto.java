package com.andreas.musicteacher.feature.invoiceManagement.dto;

import java.util.List;

public class CustomerLessonPackageDto {
    private CustomerDto customer;
    private List<LessonDto> lessons;

    public CustomerLessonPackageDto(CustomerDto customer, List<LessonDto> lessons) {
        this.customer = customer;
        this.lessons = lessons;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public List<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDto> lessons) {
        this.lessons = lessons;
    }
}

