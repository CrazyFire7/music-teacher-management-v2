package com.andreas.musicteacher.feature.lessonManagement.domain;

import java.util.List;

public interface ILessonManagementCustomerJpaRepository {
    List<GetCustomer> getAll();

    List<CustomerLessonPackage> getCustomerLessonPackagesWithFourCompletedLessons();

}
