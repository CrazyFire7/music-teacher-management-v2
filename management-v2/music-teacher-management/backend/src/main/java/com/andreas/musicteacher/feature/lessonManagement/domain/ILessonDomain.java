package com.andreas.musicteacher.feature.lessonManagement.domain;

import java.util.List;

public interface ILessonDomain {
    List<GetLesson> getLessonsByCustomerId(Long customerId);

    List<CustomerLessonPackage> getCustomerLessonPackagesWithFourCompletedLessons();

    List<GetLesson> getAllLessons();

    GetLesson createLesson(CreateLesson lessonDto);

    GetLesson updateLesson(UpdateLesson lessonDto);

    void deleteLesson(Long id);
}
