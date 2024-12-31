package com.andreas.musicteacher.feature.lessonManagement.domain;

import com.andreas.musicteacher.shared.model.Lesson;

import java.util.List;

public interface ILessonJpaRepository {
    List<GetLesson> findByCustomerId(Long customerId);

    List<GetLesson> findAll();

    void delete(Long id);
    
    GetLesson createLesson(CreateLesson createLesson);

    GetLesson updateLesson(UpdateLesson updateLesson);
}
