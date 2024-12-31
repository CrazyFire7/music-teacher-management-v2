package com.andreas.musicteacher.feature.lessonManagement.dto;


import com.andreas.musicteacher.shared.dto.LessonStatusDto;

import java.time.LocalDateTime;

public class LessonDto {
    private Long id;

    private Long customerId;

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private LessonStatusDto status;

    public LessonDto() {}

    public LessonDto(Long customerId, String title, LocalDateTime start, LocalDateTime end, LessonStatusDto status) {
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

    public LessonStatusDto getStatus() {
        return status;
    }

    public void setStatus(LessonStatusDto status) {
        this.status = status;
    }
}