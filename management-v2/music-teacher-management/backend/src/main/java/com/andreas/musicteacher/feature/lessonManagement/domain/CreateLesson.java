package com.andreas.musicteacher.feature.lessonManagement.domain;

import com.andreas.musicteacher.shared.model.LessonStatus;

import java.time.LocalDateTime;

public abstract class CreateLesson {

    private Long customerId;

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private LessonStatus status;

    public CreateLesson() {
    }

    public CreateLesson(Long customerId, String title, LocalDateTime start, LocalDateTime end, LessonStatus status) {
        this.customerId = customerId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = status;
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

    public abstract String getLessonType();
}
