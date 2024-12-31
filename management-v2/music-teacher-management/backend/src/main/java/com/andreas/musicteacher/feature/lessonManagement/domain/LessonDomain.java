package com.andreas.musicteacher.feature.lessonManagement.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonDomain implements ILessonDomain {
    private final ILessonJpaRepository lessonRepository;
    private final ILessonManagementCustomerJpaRepository customerRepository;

    @Autowired
    public LessonDomain(ILessonJpaRepository lessonRepository, ILessonManagementCustomerJpaRepository customerRepository) {
        this.lessonRepository = lessonRepository;
        this.customerRepository = customerRepository;
    }

    public List<GetLesson> getLessonsByCustomerId(Long customerId) {
        var lessons = lessonRepository.findByCustomerId(customerId);

        return lessons;
    }

    public List<CustomerLessonPackage> getCustomerLessonPackagesWithFourCompletedLessons() {
        var lessonPackages = customerRepository.getCustomerLessonPackagesWithFourCompletedLessons();

        return lessonPackages;
    }

    public List<GetLesson> getAllLessons() {
        var lessons = lessonRepository.findAll();

        return lessons;
    }

    public GetLesson createLesson(CreateLesson createLesson) {
        var createdLesson = lessonRepository.createLesson(createLesson);

        return createdLesson;
    }

    public GetLesson updateLesson(UpdateLesson updateLesson) {
        var updatedLesson = lessonRepository.updateLesson(updateLesson);

        return updatedLesson;
    }

    public void deleteLesson(Long id) {
        lessonRepository.delete(id);
    }
}