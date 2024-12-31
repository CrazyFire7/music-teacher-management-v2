package com.andreas.musicteacher.feature.lessonManagement.controller;

import com.andreas.musicteacher.feature.lessonManagement.dto.CustomerLessonPackageDto;
import com.andreas.musicteacher.feature.lessonManagement.domain.ILessonDomain;
import com.andreas.musicteacher.feature.lessonManagement.dto.LessonDto;
import com.andreas.musicteacher.feature.lessonManagement.mapper.ILessonServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final ILessonDomain domain;
    private final ILessonServiceMapper mapper;

    @Autowired
    public LessonController(ILessonDomain domain, ILessonServiceMapper mapper) {
        this.domain = domain;
        this.mapper = mapper;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<LessonDto>> getLessonsByCustomerId(@PathVariable Long customerId) {
        var lessons = domain.getLessonsByCustomerId(customerId);

        var lessonDtos = lessons.stream()
                .map(mapper::mapToDto)
                .toList();

        return ResponseEntity.ok(lessonDtos);
    }

    @GetMapping
    public ResponseEntity<List<LessonDto>> getAllLessons() {
        var lessons = domain.getAllLessons();

        var lessonDtos = lessons.stream()
                .map(mapper::mapToDto)
                .toList();

        return ResponseEntity.ok(lessonDtos);
    }

    @GetMapping("/completed-lessons-packages")
    public ResponseEntity<List<CustomerLessonPackageDto>> getCustomersWithFourCompletedLessonPackages() {
        var lessonPackages = domain.getCustomerLessonPackagesWithFourCompletedLessons();

        var customerLessonPackageDtos = lessonPackages.stream()
                .map(mapper::mapPackageToDto)
                .toList();

        return ResponseEntity.ok(customerLessonPackageDtos);
    }

    @PostMapping
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lessonDto) {
        if (lessonDto.getCustomerId() == null)
        {
            return ResponseEntity.badRequest().build();
        }

        var createLesson = mapper.mapToCreateMusicDomain(lessonDto);

        //hier könnte ich jetzt CreateMathLesson mitgeben, da beides von CreateLesson erbt und gleich behandelt wird. nachher im Repo kann es unterschieden werden
        // var createLesson = mapper.mapToCreateMathDomain(lessonDto);

        var createdLesson = domain.createLesson(createLesson);

        var createdLessonDto = mapper.mapToDto(createdLesson);

        return ResponseEntity.ok(createdLessonDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable Long id, @RequestBody LessonDto lessonDto) {
        var updateLesson = mapper.mapToUpdateDomain(id, lessonDto);

        var updatedLesson = domain.updateLesson(updateLesson);

        var updatedLessonDto = mapper.mapToDto(updatedLesson);

        return ResponseEntity.ok(updatedLessonDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable Long id) {
        try {
            domain.deleteLesson(id);
            return ResponseEntity.ok("Deleted Lesson successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting lesson");
        }
    }
}