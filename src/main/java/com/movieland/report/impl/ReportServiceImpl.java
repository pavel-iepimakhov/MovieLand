package com.movieland.report.impl;

import com.movieland.entity.User;
import com.movieland.entity.response.AddReportRequestResponse;
import com.movieland.report.*;
import com.movieland.util.TokenGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ReportServiceImpl implements ReportService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
    private final static long REPORT_GENERATION_FIXED_RATE = 5 * 60 * 1000; //5 minutes

    @Autowired
    TokenGeneratorService tokenGeneratorService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private BlockingQueue<ReportRequest> requests = new LinkedBlockingQueue<>(10);

    private ConcurrentHashMap<ReportRequest, ListenableFuture> results = new ConcurrentHashMap<>();

    @Override
    public AddReportRequestResponse addReportRequest(ReportTypeEnum reportType, LocalDate reportDate, ReportFormatEnum reportFormat, User user) {
        String requestId = tokenGeneratorService.getToken();
        ReportRequest request = new ReportRequest(requestId, reportType, reportDate, reportFormat, user);
        LOGGER.info("New report request: " + request);
        try {
            requests.put(request);
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }
        return new AddReportRequestResponse(requestId);
    }

    @Scheduled(fixedRate = REPORT_GENERATION_FIXED_RATE)
    private void processQueuedReportRequests() throws InterruptedException {
        while(!requests.isEmpty()) {
            ReportRequest request = requests.take();
            ListenableFuture<ReportGenerationStatus> result = threadPoolTaskExecutor.submitListenable(request);
            result.addCallback((res) -> System.out.println("Success callback - send report via email"),
                               (res) -> System.out.println("Failure callback - send error report" + res));
            results.put(request, result);
        }

    }
}
