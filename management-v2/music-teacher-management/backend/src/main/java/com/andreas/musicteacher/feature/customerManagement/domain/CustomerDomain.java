package com.andreas.musicteacher.feature.customerManagement.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDomain implements ICustomerDomain {
    private final ICustomerJpaRepository customerRepository;

    @Autowired
    public CustomerDomain(ICustomerJpaRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<GetCustomer> getAllCustomers() {
        List<GetCustomer> customers = customerRepository.getAll();

        return customers;
    }

    public GetCustomer createCustomer(CreateCustomer customerDto) {
        var createdCustomer = customerRepository.createCustomer(customerDto);

        return createdCustomer;
    }

    public void deleteCustomerById(long customerId) {
        customerRepository.deleteById(customerId);
    }

    public GetCustomer updateCustomer(UpdateCustomer updateCustomer) {
        var updatedCustomer = customerRepository.updateCustomer(updateCustomer);

        return updatedCustomer;
    }
}
