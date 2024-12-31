package com.andreas.musicteacher.feature.lessonManagement.repository;

import com.andreas.musicteacher.feature.lessonManagement.domain.*;
import com.andreas.musicteacher.shared.model.Customer;
import com.andreas.musicteacher.shared.model.Lesson;
import com.andreas.musicteacher.shared.model.LessonStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LessonJpaRepository implements ILessonJpaRepository {
    private final LessonRepository lessonRepository;
    private final LessonManagementCustomerRepository lessonManagementCustomerRepository;
    private final ILessonJpaRepositoryMapper mapper;

    public LessonJpaRepository(LessonRepository lessonRepository, LessonManagementCustomerRepository lessonManagementCustomerRepository, ILessonJpaRepositoryMapper mapper) {
        this.lessonRepository = lessonRepository;
        this.lessonManagementCustomerRepository = lessonManagementCustomerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetLesson> findByCustomerId(Long customerId) {
        List<Lesson> lessons = lessonRepository.findByCustomerId(customerId);
        return lessons.stream().map(mapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public List<GetLesson> findAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public GetLesson createLesson(CreateLesson createLesson) {
        // Hier könnte ich dann z.B. unterscheiden ob es sich um ein MathLesson oder ein MusicLesson handelt und das dan in der DB je anders speichern
        // var topic = ((CreateMathLesson) createLesson).getTopic(); So mappe ich eine superklasse zu einer subklasse

        Lesson lesson = mapper.mapToEntity(createLesson);

        var status = LessonStatus.valueOf(createLesson.getStatus().name());

        lesson.setStatus(status);

        if (createLesson.getCustomerId() != null) {
            Optional<Customer> customerOpt = lessonManagementCustomerRepository.findById(createLesson.getCustomerId());
            if (customerOpt.isEmpty()) {
                throw new RuntimeException("Customer not found");
            }
            lesson.setCustomer(customerOpt.get());
        }

        Lesson savedLesson = lessonRepository.save(lesson);

        var createdGetLesson = mapper.mapToDomain(savedLesson);

        return createdGetLesson;
    }

    @Override
    public GetLesson updateLesson(UpdateLesson updateLesson) {
        Lesson lesson = lessonRepository.findById(updateLesson.getId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        if (updateLesson.getEnd().isBefore(updateLesson.getStart())) {
            throw new RuntimeException("Enddatum darf nicht vor dem Startdatum liegen.");
        }

        lesson.setTitle(updateLesson.getTitle());
        lesson.setStart(updateLesson.getStart());
        lesson.setEnd(updateLesson.getEnd());
        lesson.setStatus(updateLesson.getStatus());

        if (updateLesson.getCustomerId() != null) {
            Optional<Customer> customerOpt = lessonManagementCustomerRepository.findById(updateLesson.getCustomerId());
            if (customerOpt.isEmpty()) {
                throw new RuntimeException("Customer not found");
            }
            lesson.setCustomer(customerOpt.get());
        } else {
            lesson.setCustomer(null);
        }

        Lesson updatedLesson = lessonRepository.save(lesson);

        var updatedGetLesson = mapper.mapToDomain(updatedLesson);

        return updatedGetLesson;
    }

    @Override
    public void delete(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        lessonRepository.delete(lesson);
    }
}
