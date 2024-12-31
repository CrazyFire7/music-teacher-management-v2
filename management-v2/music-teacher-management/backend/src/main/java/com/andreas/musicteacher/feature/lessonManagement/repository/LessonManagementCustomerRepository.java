package com.andreas.musicteacher.feature.lessonManagement.repository;

import com.andreas.musicteacher.shared.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonManagementCustomerRepository extends JpaRepository<Customer, Long> {
}
