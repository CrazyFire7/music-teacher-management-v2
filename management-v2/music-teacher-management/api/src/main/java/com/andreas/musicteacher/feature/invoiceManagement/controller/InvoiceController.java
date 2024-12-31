package com.andreas.musicteacher.feature.invoiceManagement.controller;

import com.andreas.musicteacher.feature.invoiceManagement.domain.IInvoiceDomain;
import com.andreas.musicteacher.feature.invoiceManagement.dto.InvoiceRequestDto;
import com.andreas.musicteacher.feature.invoiceManagement.mapper.IInvoiceServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/invoices")
public class InvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class); // Logger

    private final IInvoiceDomain domain;
    private final IInvoiceServiceMapper mapper;

    public InvoiceController(IInvoiceDomain invoiceDomain, IInvoiceServiceMapper invoiceServiceMapper) {
        this.domain = invoiceDomain;
        this.mapper = invoiceServiceMapper;
    }

    @PostMapping
    public ResponseEntity<Resource> createInvoicePdf(@RequestBody InvoiceRequestDto invoiceRequestDto) {
        try {
            logger.info("Received InvoiceRequest: {}", invoiceRequestDto);

            var invoiceRequest = mapper.MapToDomain(invoiceRequestDto);

            Resource pdfResource = domain.generateInvoicePdf(invoiceRequest);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rechnung.pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            return new ResponseEntity<>(pdfResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while generating invoice PDF", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}