package com.andreas.musicteacher.feature.customerManagement.repository;

import com.andreas.musicteacher.feature.customerManagement.domain.CreateCustomer;
import com.andreas.musicteacher.feature.customerManagement.domain.GetCustomer;
import com.andreas.musicteacher.shared.model.Customer;
import org.springframework.stereotype.Component;

public interface ICustomerJpaRepositoryMapper {
    GetCustomer mapToDomain(Customer customer);

    Customer mapToEntity(CreateCustomer customerDto);
}
