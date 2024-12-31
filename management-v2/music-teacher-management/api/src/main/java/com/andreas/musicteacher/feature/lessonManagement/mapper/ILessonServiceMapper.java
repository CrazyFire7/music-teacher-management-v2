package com.andreas.musicteacher.feature.lessonManagement.mapper;

import com.andreas.musicteacher.feature.lessonManagement.domain.*;
import com.andreas.musicteacher.feature.lessonManagement.dto.CustomerLessonPackageDto;
import com.andreas.musicteacher.feature.lessonManagement.dto.LessonDto;

public interface ILessonServiceMapper {
    LessonDto mapToDto(GetLesson getLesson);

    CustomerLessonPackageDto mapPackageToDto(CustomerLessonPackage customerLessonPackage);

    CreateMusicLesson mapToCreateMusicDomain(LessonDto lessonDto);

    UpdateLesson mapToUpdateDomain(Long id, LessonDto lessonDto);
}
