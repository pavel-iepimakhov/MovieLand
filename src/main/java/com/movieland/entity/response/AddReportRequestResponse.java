package com.movieland.entity.response;

public class AddReportRequestResponse {
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public AddReportRequestResponse(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "AddReportRequestResponse{" +
                "requestId='" + requestId + '\'' +
                '}';
    }
}
