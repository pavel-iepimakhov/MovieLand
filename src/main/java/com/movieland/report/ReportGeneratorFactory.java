package com.movieland.report;

public interface ReportGeneratorFactory {
    ReportGenerator getReportGenerator(ReportFormatEnum format);
}
