package com.andreas.musicteacher.feature.customerManagement.repository;

import com.andreas.musicteacher.feature.customerManagement.domain.CreateCustomer;
import com.andreas.musicteacher.feature.customerManagement.domain.GetCustomer;
import com.andreas.musicteacher.shared.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerJpaRepositoryMapper implements ICustomerJpaRepositoryMapper {
    @Override
    public GetCustomer mapToDomain(Customer customer) {
        var getCustomer = new GetCustomer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getStrasse(), customer.getPostleitzahl(), customer.getOrt());

        getCustomer.setId(customer.getId());

        return getCustomer;
    }

    @Override
    public Customer mapToEntity(CreateCustomer customerDto) {
        var customer = new Customer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getEmail(), customerDto.getStrasse(), customerDto.getPostleitzahl(), customerDto.getOrt());

        return customer;
    }
}
