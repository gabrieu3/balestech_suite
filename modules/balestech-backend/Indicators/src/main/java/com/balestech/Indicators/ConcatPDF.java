package com.balestech.Indicators;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.io.IOException;

public class ConcatPDF {
    public static void main(String[] args) {
        String file1 = "C:\\Users\\gabrieu3\\Downloads\\multa\\Recurso.pdf";
        String file2 = "C:\\Users\\gabrieu3\\Downloads\\multa\\CRLV.pdf";
        String file3 = "C:\\Users\\gabrieu3\\Downloads\\multa\\CNH.pdf";
        String outputFilePath = "C:\\Users\\gabrieu3\\Downloads\\multa\\Recurso-CRLV-CNH.pdf";
        concatPDFFiles(file1, file2, file3, outputFilePath);
        System.out.println("Os arquivos foram concatenados com sucesso.");
    }

    public static void concatPDFFiles(String file1, String file2, String file3, String outputFilePath) {
        Document document = new Document();
        try {
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(outputFilePath));
            document.open();
            appendPDFFile(copy, file1);
            appendPDFFile(copy, file2);
            appendPDFFile(copy, file3);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendPDFFile(PdfCopy copy, String filePath) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(filePath);
        int numPages = reader.getNumberOfPages();
        for (int i = 0; i < numPages; i++) {
            copy.addPage(copy.getImportedPage(reader, i + 1));
        }
        reader.close();
    }
}
