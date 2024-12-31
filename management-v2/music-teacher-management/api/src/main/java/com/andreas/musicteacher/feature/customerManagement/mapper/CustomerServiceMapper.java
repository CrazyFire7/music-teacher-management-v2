package com.andreas.musicteacher.feature.customerManagement.mapper;

import com.andreas.musicteacher.feature.customerManagement.domain.GetCustomer;
import com.andreas.musicteacher.feature.customerManagement.domain.UpdateCustomer;
import com.andreas.musicteacher.feature.customerManagement.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceMapper implements ICustomerServiceMapper {
    @Override
    public CustomerDto mapToDto(GetCustomer customer) {
        var customerDto = new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getStrasse(), customer.getPostleitzahl(), customer.getOrt());

        customerDto.setId(customer.getId());

        return customerDto;
    }

    @Override
    public UpdateCustomer mapToUpdateDomain(long id, CustomerDto customerDto) {
        var updateCustomer = new UpdateCustomer(id, customerDto.getFirstName(), customerDto.getLastName(), customerDto.getEmail(), customerDto.getStrasse(), customerDto.getPostleitzahl(), customerDto.getOrt());

        return updateCustomer;
    }
}
