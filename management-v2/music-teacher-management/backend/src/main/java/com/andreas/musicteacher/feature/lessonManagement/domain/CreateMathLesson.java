package com.andreas.musicteacher.feature.lessonManagement.domain;

import com.andreas.musicteacher.shared.model.LessonStatus;

import java.time.LocalDateTime;

public class CreateMathLesson extends CreateLesson{
    private String topic;

    public CreateMathLesson(Long customerId, String title, LocalDateTime start, LocalDateTime end, LessonStatus status, String topic) {
        super(customerId, title, start, end, status);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getLessonType() {
        return "Math";
    }
}
