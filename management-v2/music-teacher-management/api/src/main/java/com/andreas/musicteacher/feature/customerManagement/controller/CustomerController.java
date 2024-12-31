package com.andreas.musicteacher.feature.customerManagement.controller;

import com.andreas.musicteacher.feature.customerManagement.domain.CreateCustomer;
import com.andreas.musicteacher.feature.customerManagement.domain.GetCustomer;
import com.andreas.musicteacher.feature.customerManagement.domain.ICustomerDomain;
import com.andreas.musicteacher.feature.customerManagement.dto.CustomerDto;
import com.andreas.musicteacher.feature.customerManagement.mapper.ICustomerServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final ICustomerDomain domain;
    private final ICustomerServiceMapper mapper;

    @Autowired
    public CustomerController(ICustomerDomain domain, ICustomerServiceMapper mapper) {
        this.domain = domain;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<GetCustomer> customers = domain.getAllCustomers();

        List<CustomerDto> customerDtos = customers.stream()
                .map(mapper::mapToDto)
                .toList();

        return ResponseEntity.ok(customerDtos);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        var createCustomer = new CreateCustomer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getEmail(), customerDto.getStrasse(), customerDto.getPostleitzahl(), customerDto.getOrt());
        var createdCustomer = domain.createCustomer(createCustomer);

        var createdCustomerDto = mapper.mapToDto(createdCustomer);

        return ResponseEntity.ok(createdCustomerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable long id) {
        try {
            domain.deleteCustomerById(id);
            return ResponseEntity.ok("Customer deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        try {
            var updateCustomer = mapper.mapToUpdateDomain(id, customerDto);
            var updatedCustomer = domain.updateCustomer(updateCustomer);

            var updatedCustomerDto = mapper.mapToDto(updatedCustomer);

            return ResponseEntity.ok(updatedCustomerDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
