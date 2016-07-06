package com.movieland.report;

import com.movieland.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.Callable;

public class ReportRequest implements Callable<ReportGenerationStatus> {

    String requestId;
    ReportTypeEnum reportType;
    LocalDate reportDate;
    ReportFormatEnum reportFormat;
    User user;
    ReportGenerator reportGenerator;

    public ReportGenerator getReportGenerator() {
        return reportGenerator;
    }

    public void setReportGenerator(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

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

    public ReportRequest(String requestId, ReportTypeEnum reportType, LocalDate reportDate, ReportFormatEnum reportFormat, User user, ReportGenerator reportGenerator) {
        this.requestId = requestId;
        this.reportType = reportType;
        this.reportDate = reportDate;
        this.reportFormat = reportFormat;
        this.user = user;
        this.reportGenerator = reportGenerator;
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
        return reportGenerator.generateReport(this);
    }
}
