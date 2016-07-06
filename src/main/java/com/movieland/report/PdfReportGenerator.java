package com.movieland.report;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.movieland.entity.Movie;
import com.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component("pdfGenerator")
@Scope("prototype")
public class PdfReportGenerator implements ReportGenerator {

    private final static Logger LOGGER = LoggerFactory.getLogger(PdfReportGenerator.class);

    @Autowired
    private MovieService movieService;

    @Override
    public ReportGenerationStatus generateReport(ReportRequest request) {
        LOGGER.info("Generating PDF report for request : " + request);

        try {
            Document iTextDoc = new Document();
            String fileName = request.getReportType().toString() + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + request.getRequestId() + ".pdf";
            PdfWriter.getInstance(iTextDoc, new FileOutputStream(fileName));
            iTextDoc.open();
            PdfPTable pdfPTable = new PdfPTable(4);
            PdfPCell cell;

            if (request.getReportType() == ReportTypeEnum.ALL_MOVIES) {
                List<Movie> movies = movieService.getAllMovies();
                for (Movie movie : movies) {
                    cell = new PdfPCell(new Phrase(movie.getMovieId()));
                    pdfPTable.addCell(cell);
                    cell = new PdfPCell(new Phrase(movie.getMovieNameEng() + "/" + movie.getMovieNameRus()));
                    pdfPTable.addCell(cell);
                    cell = new PdfPCell(new Phrase(movie.getMovieYear()));
                    pdfPTable.addCell(cell);
                    cell = new PdfPCell(new Phrase(movie.getMovieDescription()));
                    pdfPTable.addCell(cell);
                }
            }
            iTextDoc.add(pdfPTable);
            iTextDoc.close();
            LOGGER.info("Report generated successfully. Filename is {}", fileName);
        } catch (Exception e) {
            LOGGER.info("Exception during report generation. " + e );
            return new ReportGenerationStatus(ReportGenerationStatusEnum.FAILURE, e.toString());
        }
        return new ReportGenerationStatus(ReportGenerationStatusEnum.OK, null);
    }
}
