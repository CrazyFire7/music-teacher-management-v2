package com.andreas.musicteacher.feature.invoiceManagement.domain;

import com.spire.doc.Document;
import com.spire.doc.PdfConformanceLevel;
import com.spire.doc.ToPdfParameterList;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Service
public class InvoiceDomain implements IInvoiceDomain {
    @Override
    public Resource generateInvoicePdf(InvoiceRequest invoiceRequest) throws Exception {
        Path tempDir = Files.createTempDirectory("invoice");
        Path pdfFilePath = tempDir.resolve("output_invoice.pdf");

        try {
            var lessons = invoiceRequest.getCustomerLessonPackageDto().getLessons();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("customer_name", invoiceRequest.getCustomerLessonPackageDto().getCustomer().getFullName());
            placeholders.put("customer_stra", invoiceRequest.getCustomerLessonPackageDto().getCustomer().getStrasse());
            placeholders.put("customer_postleitzahl", invoiceRequest.getCustomerLessonPackageDto().getCustomer().getPostleitzahl());
            placeholders.put("customer_ort", invoiceRequest.getCustomerLessonPackageDto().getCustomer().getOrt());
            placeholders.put("current_date", LocalDateTime.now().format(formatter));
            placeholders.put("date_first_lesson", lessons.get(0).getStart().format(formatter));
            placeholders.put("date_last_lesson", lessons.get(3).getStart().format(formatter));
            placeholders.put("price_per_lesson", "CHF 30");
            placeholders.put("total_price", "CHF " + (lessons.size() * 30));

            try (InputStream templateStream = getClass().getClassLoader().getResourceAsStream(getTemplatePath(invoiceRequest))) {
                Path tempDocxPath = Files.createTempFile("template", ".docx");
                Files.copy(templateStream, tempDocxPath, StandardCopyOption.REPLACE_EXISTING);

                Path extractDir = Files.createTempDirectory("docx_extracted");
                try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(tempDocxPath))) {
                    ZipEntry zipEntry;
                    byte[] buffer = new byte[1024];
                    while ((zipEntry = zis.getNextEntry()) != null) {
                        File newFile = new File(extractDir.toFile(), zipEntry.getName());
                        if (zipEntry.isDirectory()) {
                            newFile.mkdirs();
                        } else {
                            new File(newFile.getParent()).mkdirs();
                            try (FileOutputStream fos = new FileOutputStream(newFile)) {
                                int len;
                                while ((len = zis.read(buffer)) > 0) {
                                    fos.write(buffer, 0, len);
                                }
                            }
                        }
                        zis.closeEntry();
                    }
                }

                Path xmlPath = extractDir.resolve("word/document.xml");
                String content = Files.readString(xmlPath);

                for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                    content = content.replace(entry.getKey(), entry.getValue());
                }
                Files.write(xmlPath, content.getBytes());

                Path updatedDocxPath = Files.createTempFile("updated_template", ".docx");
                try (FileOutputStream fos = new FileOutputStream(updatedDocxPath.toFile());
                     ZipOutputStream zipOut = new ZipOutputStream(fos)) {

                    Files.walk(extractDir).filter(Files::isRegularFile).forEach(path -> {
                        try {
                            String zipEntryName = extractDir.relativize(path).toString();
                            zipOut.putNextEntry(new ZipEntry(zipEntryName));
                            Files.copy(path, zipOut);
                            zipOut.closeEntry();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
                }

                Document doc = new Document(updatedDocxPath.toString());
                ToPdfParameterList parameterList = new ToPdfParameterList();
                parameterList.setPdfConformanceLevel(PdfConformanceLevel.Pdf_A_1_A);
                doc.saveToFile(pdfFilePath.toString(), parameterList);

                ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
                try (FileInputStream fis = new FileInputStream(pdfFilePath.toString())) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        pdfOutputStream.write(buffer, 0, bytesRead);
                    }
                }

                return new ByteArrayResource(pdfOutputStream.toByteArray());
            }
        } finally {
            deleteDirectory(tempDir);
        }
    }

    private String getTemplatePath(InvoiceRequest invoiceRequest) {
        String templatePath;
        switch (invoiceRequest.getSelectedInstrument()) {
            case "Klavier":
                templatePath = "templates/Rechnung_Template_Klavier.docx";
                break;
            case "Ukulele":
                templatePath = "templates/Rechnung_Template_Ukulele.docx";
                break;
            case "Schlagzeug":
                templatePath = "templates/Rechnung_Template_Schlagzeug.docx";
                break;
            default:
                throw new IllegalArgumentException("Invalid instrument type: " + invoiceRequest.getSelectedInstrument());
        }

        return templatePath;
    }

    private void deleteDirectory(Path directory) throws IOException {
        Files.walk(directory)
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
