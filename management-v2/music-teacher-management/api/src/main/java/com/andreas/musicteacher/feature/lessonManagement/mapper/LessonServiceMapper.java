package com.andreas.musicteacher.feature.lessonManagement.mapper;


import com.andreas.musicteacher.feature.lessonManagement.domain.*;
import com.andreas.musicteacher.feature.lessonManagement.dto.CustomerDto;
import com.andreas.musicteacher.feature.lessonManagement.dto.CustomerLessonPackageDto;
import com.andreas.musicteacher.feature.lessonManagement.dto.LessonDto;
import com.andreas.musicteacher.shared.dto.LessonStatusDto;
import com.andreas.musicteacher.shared.model.LessonStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LessonServiceMapper implements ILessonServiceMapper {

    @Override
    public LessonDto mapToDto(GetLesson getLesson) {
        var status = LessonStatusDto.valueOf(getLesson.getStatus().name());

        var lessonDto = new LessonDto(getLesson.getCustomerId(), getLesson.getTitle(), getLesson.getStart(), getLesson.getEnd(), status);

        lessonDto.setId(getLesson.getId());

        return lessonDto;
    }

    @Override
    public CustomerLessonPackageDto mapPackageToDto(CustomerLessonPackage customerLessonPackage) {
        var customerDto = new CustomerDto(customerLessonPackage.getCustomer().getFirstName(), customerLessonPackage.getCustomer().getLastName(), customerLessonPackage.getCustomer().getEmail(), customerLessonPackage.getCustomer().getStrasse(), customerLessonPackage.getCustomer().getPostleitzahl(), customerLessonPackage.getCustomer().getOrt());

        customerDto.setId(customerLessonPackage.getCustomer().getId());

        var lessonList = new ArrayList<LessonDto>();

        for (GetLesson lessonDto : customerLessonPackage.getLessons()) {
            var status = LessonStatusDto.valueOf(lessonDto.getStatus().name());
            var lesson = new LessonDto(lessonDto.getCustomerId(), lessonDto.getTitle(), lessonDto.getStart(), lessonDto.getEnd(), status);

            lesson.setId(lessonDto.getId());

            lessonList.add(lesson);
        }

        var customerLessonPackageDto = new CustomerLessonPackageDto(customerDto, lessonList);

        return customerLessonPackageDto;
    }

    @Override
    public CreateMusicLesson mapToCreateMusicDomain(LessonDto lessonDto) {
        var status = LessonStatus.valueOf(lessonDto.getStatus().name());

        var createLesson = new CreateMusicLesson(lessonDto.getCustomerId(), lessonDto.getTitle(), lessonDto.getStart(), lessonDto.getEnd(), status);

        return createLesson;
    }

    @Override
    public UpdateLesson mapToUpdateDomain(Long id, LessonDto lessonDto) {
        var status = LessonStatus.valueOf(lessonDto.getStatus().name());

        var updateLesson = new UpdateLesson(id, lessonDto.getCustomerId(), lessonDto.getTitle(), lessonDto.getStart(), lessonDto.getEnd(), status);

        return updateLesson;
    }
}
