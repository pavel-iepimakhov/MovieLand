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
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.File;
import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ReportServiceImpl implements ReportService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
    private final static long REPORT_GENERATION_FIXED_RATE = 1 * 60 * 1000; //5 minutes

    @Autowired
    TokenGeneratorService tokenGeneratorService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ReportGeneratorFactory reportGeneratorFactory;

    private BlockingQueue<ReportRequest> requests = new LinkedBlockingQueue<>(10);

    private ConcurrentHashMap<String, ListenableFuture> results = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, File> generatedReportFiles = new ConcurrentHashMap<>();

    @Override
    public AddReportRequestResponse addReportRequest(ReportTypeEnum reportType, LocalDate reportDate, ReportFormatEnum reportFormat, User user) {
        String requestId = tokenGeneratorService.getToken();
        ReportGenerator reportGenerator = reportGeneratorFactory.getReportGenerator(reportFormat);
        ReportRequest request = new ReportRequest(requestId, reportType, reportDate, reportFormat, user, reportGenerator);
        LOGGER.info("New report request: " + request);
        try {
            requests.put(request);
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }
        return new AddReportRequestResponse(requestId);
    }

    @Override
    public boolean isReportRequestReady(String requestId) {
        ListenableFuture result = results.get(requestId);
        return result.isDone();
    }

    @Override
    public void removeReportRequest(String requestId) {
        results.remove(requestId);
    }

    @Override
    public File getReportFile(String requestId) {
        return generatedReportFiles.get(requestId);
    }

    @Scheduled(fixedRate = REPORT_GENERATION_FIXED_RATE)
    private void processQueuedReportRequests() throws InterruptedException {
        while(!requests.isEmpty()) {
            ReportRequest request = requests.take();
            ListenableFuture<ReportGenerationStatus> result = threadPoolTaskExecutor.submitListenable(request);
            result.addCallback((res) -> {
                                            generatedReportFiles.put(request.getRequestId(), new File(res.getFileName()));
                                            System.out.println("Success callback - send report via email");
                                        },
                               (res) -> System.out.println("Failure callback - send error report" + res));
            results.put(request.getRequestId(), result);
        }

    }
}
