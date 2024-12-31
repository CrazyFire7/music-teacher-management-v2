package com.andreas.musicteacher.feature.lessonManagement.repository;

import com.andreas.musicteacher.feature.customerManagement.domain.GetCustomer;
import com.andreas.musicteacher.feature.lessonManagement.domain.CreateLesson;
import com.andreas.musicteacher.feature.lessonManagement.domain.GetLesson;
import com.andreas.musicteacher.shared.model.Lesson;

public interface ILessonJpaRepositoryMapper {
    GetLesson mapToDomain(Lesson lesson);

    Lesson mapToEntity(CreateLesson createLesson);
}
