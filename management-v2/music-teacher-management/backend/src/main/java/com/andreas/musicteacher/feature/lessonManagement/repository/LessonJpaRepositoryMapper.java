package com.andreas.musicteacher.feature.lessonManagement.repository;

import com.andreas.musicteacher.feature.lessonManagement.domain.CreateLesson;
import com.andreas.musicteacher.feature.lessonManagement.domain.GetLesson;
import com.andreas.musicteacher.shared.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonJpaRepositoryMapper implements ILessonJpaRepositoryMapper {

    @Override
    public GetLesson mapToDomain(Lesson lesson) {
        var getLesson = new GetLesson(lesson.getCustomer().getId(), lesson.getTitle(), lesson.getStart(), lesson.getEnd(), lesson.getStatus());

        getLesson.setId(lesson.getId());

        return getLesson;
    }

    @Override
    public Lesson mapToEntity(CreateLesson createLesson) {
        var lesson = new Lesson(createLesson.getTitle(), createLesson.getStart(), createLesson.getEnd());

        return lesson;
    }
}
