package com.andreas.musicteacher.feature.lessonManagement.domain;

import com.andreas.musicteacher.shared.model.LessonStatus;

import java.time.LocalDateTime;

public class CreateMusicLesson extends CreateLesson {
    private String instrument; // brauche ich für meine Applikation nicht, aber fürs Beispiel ist es gut

    public CreateMusicLesson(Long customerId, String title, LocalDateTime start, LocalDateTime end, LessonStatus status) {
        super(customerId, title, start, end, status);
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    @Override
    public String getLessonType() {
        return "Music";
    }
}
