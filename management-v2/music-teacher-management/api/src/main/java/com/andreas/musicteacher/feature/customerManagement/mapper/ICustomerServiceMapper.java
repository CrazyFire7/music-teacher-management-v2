package com.andreas.musicteacher.feature.customerManagement.mapper;

import com.andreas.musicteacher.feature.customerManagement.domain.GetCustomer;
import com.andreas.musicteacher.feature.customerManagement.domain.UpdateCustomer;
import com.andreas.musicteacher.feature.customerManagement.dto.CustomerDto;

public interface ICustomerServiceMapper {
    CustomerDto mapToDto(GetCustomer customer);

    UpdateCustomer mapToUpdateDomain(long id, CustomerDto customerDto);
}
