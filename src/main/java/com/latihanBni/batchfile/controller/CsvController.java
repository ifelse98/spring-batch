package com.latihanBni.batchfile.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CsvController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    private boolean isJobExecuted = false;

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @Scheduled(fixedRate = 5000)
    public void importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        if (!isJobExecuted) {
            try {
                jobLauncher.run(job, jobParameters);
            } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                     | JobParametersInvalidException e) {
                e.printStackTrace();
            }
        }
        isJobExecuted = true;
    }
}
