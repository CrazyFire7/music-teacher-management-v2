package com.andreas.musicteacher.feature.invoiceManagement.domain;
import org.springframework.core.io.Resource;

public interface IInvoiceDomain {
    Resource generateInvoicePdf(InvoiceRequest invoiceRequest) throws Exception;
}
