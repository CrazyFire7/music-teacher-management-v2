package com.andreas.musicteacher.feature.invoiceManagement.mapper;

import com.andreas.musicteacher.feature.invoiceManagement.domain.*;
import com.andreas.musicteacher.feature.invoiceManagement.dto.InvoiceRequestDto;
import com.andreas.musicteacher.feature.invoiceManagement.dto.LessonDto;
import com.andreas.musicteacher.shared.model.LessonStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InvoiceServiceMapper implements IInvoiceServiceMapper {

    @Override
    public InvoiceRequest MapToDomain(InvoiceRequestDto invoiceRequestDto) {
        var customer = new Customer(invoiceRequestDto.getLessonPackage().getCustomer().getFirstName(), invoiceRequestDto.getLessonPackage().getCustomer().getLastName(), invoiceRequestDto.getLessonPackage().getCustomer().getEmail(), invoiceRequestDto.getLessonPackage().getCustomer().getStrasse(), invoiceRequestDto.getLessonPackage().getCustomer().getPostleitzahl(), invoiceRequestDto.getLessonPackage().getCustomer().getOrt());

        var lessonList = new ArrayList<Lesson>();

        for (LessonDto lessonDto : invoiceRequestDto.getLessonPackage().getLessons()) {
            var status = LessonStatus.valueOf(lessonDto.getStatus().name());
            var lesson = new Lesson(lessonDto.getCustomerId(), lessonDto.getTitle(), lessonDto.getStart(), lessonDto.getEnd(), status);

            lessonList.add(lesson);
        }

        var customerLessonPackageDto = new CustomerLessonPackage(customer, lessonList);

        var invoiceRequest = new InvoiceRequest(customerLessonPackageDto, invoiceRequestDto.getSelectedInstrument());

        return invoiceRequest;
    }
}
