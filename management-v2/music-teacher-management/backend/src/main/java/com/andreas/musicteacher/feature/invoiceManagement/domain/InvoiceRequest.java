package com.andreas.musicteacher.feature.invoiceManagement.domain;

public class InvoiceRequest {
    private CustomerLessonPackage customerLessonPackage;
    private String selectedInstrument;

    public InvoiceRequest(CustomerLessonPackage customerLessonPackage, String selectedInstrument) {
        this.customerLessonPackage = customerLessonPackage;
        this.selectedInstrument = selectedInstrument;
    }

    public CustomerLessonPackage getCustomerLessonPackageDto() {
        return customerLessonPackage;
    }

    public void setCustomerLessonPackageDto(CustomerLessonPackage customerLessonPackage) {
        this.customerLessonPackage = customerLessonPackage;
    }

    public String getSelectedInstrument() {
        return selectedInstrument;
    }

    public void setSelectedInstrument(String selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }
}
