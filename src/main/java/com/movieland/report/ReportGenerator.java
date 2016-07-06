package com.movieland.report;

public interface ReportGenerator {
    ReportGenerationStatus generateReport(ReportRequest request) throws Exception;
}
