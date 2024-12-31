package com.andreas.musicteacher.feature.customerManagement.domain;

import java.util.List;

public interface ICustomerDomain {
    List<GetCustomer> getAllCustomers();

    GetCustomer createCustomer(CreateCustomer createCustomer);

    void deleteCustomerById(long id);

    GetCustomer updateCustomer(UpdateCustomer updateCustomer);
}
