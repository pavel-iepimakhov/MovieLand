package com.movieland.report;

import com.movieland.entity.Movie;
import com.movieland.service.MovieService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component("excelGenerator")
@Scope("prototype")
public class ExcelReportGenerator implements ReportGenerator {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelReportGenerator.class);

    @Autowired
    private MovieService movieService;

    @Override
    public ReportGenerationStatus generateReport(ReportRequest request) {
        LOGGER.info("Generating Excel report for request : " + request);

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet(request.getReportType().toString());
            XSSFRow row;

            if (request.getReportType() == ReportTypeEnum.ALL_MOVIES) {
                List<Movie> movies = movieService.getAllMovies();
                int rowId = 0;
                for (Movie movie : movies) {
                    row = spreadsheet.createRow(rowId++);
                    int cellId = 0;
                    Cell cell = row.createCell(cellId++);
                    cell.setCellValue(movie.getMovieId());
                    cell = row.createCell(cellId++);
                    cell.setCellValue(movie.getMovieNameEng() + "/" + movie.getMovieNameRus());
                    cell = row.createCell(cellId++);
                    cell.setCellValue(movie.getMovieYear());
                    cell = row.createCell(cellId++);
                    cell.setCellValue(movie.getMovieRating());
                }
            }

            String fileName = request.getReportType().toString() + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
            LOGGER.info("Report generated successfully. Filename is {}", fileName);
        } catch (Exception e) {
            LOGGER.info("Exception during report generation. " + e );
            return new ReportGenerationStatus(ReportGenerationStatusEnum.FAILURE, e.toString());
        }


        return new ReportGenerationStatus(ReportGenerationStatusEnum.OK, null);
    }
}
