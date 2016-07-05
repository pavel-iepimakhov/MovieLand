package com.movieland.report;

import com.movieland.entity.User;

import java.time.LocalDate;
import java.util.concurrent.Callable;

public class ReportRequest implements Callable<ReportGenerationStatus> {

    String requestId;
    ReportTypeEnum reportType;
    LocalDate reportDate;
    ReportFormatEnum reportFormat;
    User user;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ReportTypeEnum getReportType() {
        return reportType;
    }

    public void setReportType(ReportTypeEnum reportType) {
        this.reportType = reportType;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public ReportFormatEnum getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(ReportFormatEnum reportFormat) {
        this.reportFormat = reportFormat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReportRequest(String requestId, ReportTypeEnum reportType, LocalDate reportDate, ReportFormatEnum reportFormat, User user) {
        this.requestId = requestId;
        this.reportType = reportType;
        this.reportDate = reportDate;
        this.reportFormat = reportFormat;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "requestId='" + requestId + '\'' +
                ", reportType=" + reportType +
                ", reportDate=" + reportDate +
                ", reportFormat=" + reportFormat +
                ", user=" + user +
                '}';
    }

    @Override
    public ReportGenerationStatus call() throws Exception {
        //generate report with Apache POI
        return new ReportGenerationStatus(ReportGenerationStatusEnum.OK, null);
    }
}
