package com.andreas.musicteacher.feature.lessonManagement.repository;

import com.andreas.musicteacher.feature.lessonManagement.domain.*;
import com.andreas.musicteacher.shared.model.Lesson;
import com.andreas.musicteacher.shared.model.LessonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LessonManagementLessonManagementCustomerJpaRepository implements ILessonManagementCustomerJpaRepository {
    private final LessonManagementCustomerRepository lessonManagementCustomerRepository;
    private final LessonRepository lessonRepository;
    private final ILessonManagementCustomerJpaRepositoryMapper mapper;
    private final ILessonJpaRepositoryMapper lessonMapper;

    @Autowired
    public LessonManagementLessonManagementCustomerJpaRepository(LessonManagementCustomerRepository lessonManagementCustomerRepository, LessonRepository lessonRepository, ILessonManagementCustomerJpaRepositoryMapper customerJpaRepositoryMapper, ILessonJpaRepositoryMapper lessonMapper) {
        this.lessonManagementCustomerRepository = lessonManagementCustomerRepository;
        this.lessonRepository = lessonRepository;
        this.mapper = customerJpaRepositoryMapper;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public List<GetCustomer> getAll() {
        var customers = lessonManagementCustomerRepository.findAll();

        return customers.stream()
                .map(mapper::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerLessonPackage> getCustomerLessonPackagesWithFourCompletedLessons() {
        List<GetCustomer> allCustomers = getAll();

        List<CustomerLessonPackage> lessonPackages = new ArrayList<>();

        allCustomers.forEach(customer -> {
            List<Lesson> lessons = lessonRepository.findByCustomerIdAndStatus(customer.getId(), LessonStatus.STATTGEFUNDEN);

            lessons.sort(Comparator.comparing(Lesson::getStart));

            if (lessons.size() >= 4) {
                int fullPackages = lessons.size() / 4;

                for (int i = 0; i < fullPackages * 4; i += 4) {
                    List<Lesson> lessonGroup = lessons.subList(i, i + 4);

                    var domainLessonGroups = lessonGroup.stream()
                            .map(lessonMapper::mapToDomain)
                            .collect(Collectors.toList());

                    CustomerLessonPackage lessonPackage = new CustomerLessonPackage(customer, domainLessonGroups);
                    lessonPackages.add(lessonPackage);
                }
            }
        });

        return lessonPackages;
    }
}
