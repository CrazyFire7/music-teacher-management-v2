package com.andreas.musicteacher.feature.customerManagement.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface ICustomerJpaRepository {
    List<GetCustomer> getAll();

    GetCustomer createCustomer(CreateCustomer customerDto);

    void deleteById(long customerId);

    GetCustomer updateCustomer(UpdateCustomer updateCustomer);
}
