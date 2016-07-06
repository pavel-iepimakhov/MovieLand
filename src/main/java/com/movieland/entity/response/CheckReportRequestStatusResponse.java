package com.movieland.entity.response;

public class CheckReportRequestStatusResponse {
    private boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public CheckReportRequestStatusResponse(boolean isDone) {
        this.isDone = isDone;
    }
}
