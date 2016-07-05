package com.movieland.report;

public class ReportGenerationStatus {
    ReportGenerationStatusEnum status;
    String errorMessage;

    public ReportGenerationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ReportGenerationStatusEnum status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ReportGenerationStatus{" +
                "status=" + status +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public ReportGenerationStatus(ReportGenerationStatusEnum status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
