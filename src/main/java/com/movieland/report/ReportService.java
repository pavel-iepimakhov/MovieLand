package com.movieland.report;

import com.movieland.entity.User;
import com.movieland.entity.response.AddReportRequestResponse;

import java.time.LocalDate;

public interface ReportService {
    AddReportRequestResponse addReportRequest(ReportTypeEnum reportType, LocalDate reportDate, ReportFormatEnum reportFormat, User user);
}
