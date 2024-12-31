package com.andreas.musicteacher.feature.lessonManagement.repository;


import com.andreas.musicteacher.feature.lessonManagement.domain.GetCustomer;
import com.andreas.musicteacher.shared.model.Customer;

public interface ILessonManagementCustomerJpaRepositoryMapper {
    GetCustomer mapToDomain(Customer customer);
}
