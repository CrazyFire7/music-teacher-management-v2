package com.andreas.musicteacher.feature.invoiceManagement.domain;

import com.andreas.musicteacher.shared.model.LessonStatus;

import java.time.LocalDateTime;

public class Lesson {
    private Long id;

    private Long customerId;

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private LessonStatus status;

    public Lesson() {}

    public Lesson(Long customerId, String title, LocalDateTime start, LocalDateTime end, LessonStatus status) {
        this.customerId = customerId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}