package com.andreas.musicteacher.shared.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    private LessonStatus status;

    @Transient // Wird von Datenbank ignoriert
    private String lessonType;


    public Lesson() {}

    public Lesson(Customer customer, String title, LocalDateTime start, LocalDateTime end) {
        this.customer = customer;
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = LessonStatus.OFFEN;
    }

    public Lesson(String title, LocalDateTime start, LocalDateTime end) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = LessonStatus.OFFEN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LessonStatus getStatus() {
        return status;
    }

    public void setStatus(LessonStatus status) {
        this.status = status;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }
}