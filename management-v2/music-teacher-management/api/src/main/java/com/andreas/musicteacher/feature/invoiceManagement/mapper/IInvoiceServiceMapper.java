package com.andreas.musicteacher.feature.invoiceManagement.mapper;

import com.andreas.musicteacher.feature.invoiceManagement.domain.InvoiceRequest;
import com.andreas.musicteacher.feature.invoiceManagement.dto.InvoiceRequestDto;

public interface IInvoiceServiceMapper {
    InvoiceRequest MapToDomain(InvoiceRequestDto invoiceRequestDto);
}
