package com.andreas.musicteacher.feature.customerManagement.repository;

import com.andreas.musicteacher.feature.customerManagement.domain.CreateCustomer;
import com.andreas.musicteacher.feature.customerManagement.domain.GetCustomer;
import com.andreas.musicteacher.feature.customerManagement.domain.ICustomerJpaRepository;
import com.andreas.musicteacher.feature.customerManagement.domain.UpdateCustomer;
import com.andreas.musicteacher.shared.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerJpaRepository implements ICustomerJpaRepository {
    private final CustomerRepository customerRepository;
    private final ICustomerJpaRepositoryMapper mapper;

    @Autowired
    public CustomerJpaRepository(CustomerRepository customerRepository, ICustomerJpaRepositoryMapper customerJpaRepositoryMapper) {
        this.customerRepository = customerRepository;
        this.mapper = customerJpaRepositoryMapper;
    }

    @Override
    public List<GetCustomer> getAll() {
        var customers = customerRepository.findAll();
        for (var customer : customers) {
            System.out.println(customer.getId());
        }

        return customers.stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public GetCustomer createCustomer(CreateCustomer customerDto) {
        var customer = mapper.mapToEntity(customerDto);

        var savedCustomer = customerRepository.save(customer);

        var getCustomer = mapper.mapToDomain(savedCustomer);

        return getCustomer;
    }

    @Override
    public void deleteById(long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public GetCustomer updateCustomer(UpdateCustomer updateCustomer) {
        Optional<Customer> customerOptional = customerRepository.findById(updateCustomer.getId());

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            customer.setFirstName(updateCustomer.getFirstName());
            customer.setLastName(updateCustomer.getLastName());
            customer.setEmail(updateCustomer.getEmail());
            customer.setStrasse(updateCustomer.getStrasse());
            customer.setPostleitzahl(updateCustomer.getPostleitzahl());
            customer.setOrt(updateCustomer.getOrt());

            customer = customerRepository.save(customer);

            var getCustomer = mapper.mapToDomain(customer);

            return getCustomer;
        } else {
            throw new RuntimeException("Customer not found with id: " + updateCustomer.getId());
        }
    }
}
