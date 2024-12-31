package com.andreas.musicteacher.feature.invoiceManagement.dto;

public class InvoiceRequestDto {
    private CustomerLessonPackageDto lessonPackage;
    private String selectedInstrument;

    public CustomerLessonPackageDto getLessonPackage() {
        return lessonPackage;
    }

    public void setLessonPackage(CustomerLessonPackageDto lessonPackage) {
        this.lessonPackage = lessonPackage;
    }

    public String getSelectedInstrument() {
        return selectedInstrument;
    }

    public void setSelectedInstrument(String selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }
}
