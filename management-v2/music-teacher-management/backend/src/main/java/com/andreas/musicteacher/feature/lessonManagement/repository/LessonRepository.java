package com.andreas.musicteacher.feature.lessonManagement.repository;

import com.andreas.musicteacher.shared.model.Lesson;
import com.andreas.musicteacher.shared.model.LessonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCustomerIdAndStatus(Long customerId, LessonStatus status);
    List<Lesson> findByCustomerId(Long customerId);
}
