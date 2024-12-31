package com.andreas.musicteacher.feature.lessonManagement.repository;


import com.andreas.musicteacher.feature.lessonManagement.domain.GetCustomer;
import com.andreas.musicteacher.shared.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class LessonManagementCustomerJpaRepositoryMapper implements ILessonManagementCustomerJpaRepositoryMapper {
    @Override
    public GetCustomer mapToDomain(Customer customer) {
        var getCustomer = new GetCustomer(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getStrasse(), customer.getPostleitzahl(), customer.getOrt());

        getCustomer.setId(customer.getId());

        return getCustomer;
    }
}
