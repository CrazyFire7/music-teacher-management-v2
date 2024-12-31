package com.andreas.musicteacher.feature.lessonManagement.domain;


import com.andreas.musicteacher.shared.model.Lesson;

import java.util.List;

public class CustomerLessonPackage {
    private GetCustomer customer;
    private List<GetLesson> lessons;

    public CustomerLessonPackage(GetCustomer customer, List<GetLesson> lessons) {
        this.customer = customer;
        this.lessons = lessons;
    }

    public GetCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(GetCustomer customer) {
        this.customer = customer;
    }

    public List<GetLesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<GetLesson> lessons) {
        this.lessons = lessons;
    }
}

