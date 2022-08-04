package com.knoldus.cmas.controller;

import com.knoldus.cmas.entity.JobStatus;

import com.knoldus.cmas.request.MigrationRequest;
import com.knoldus.cmas.service.JobStatusMigration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

@RestController
public class JobStatusMigrationController{
    @Autowired
    private JobStatusMigration jobStatusMigration;
    @GetMapping("/job/status/{id}")
    public JobStatus getJobDetailsById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return jobStatusMigration.getJobDetailsById(id);
    }
    @PostMapping("/schedule/job")
    public String saveJobDetails(@RequestBody MigrationRequest migrationRequest) throws ExecutionException, InterruptedException, GeneralSecurityException, IOException {
        return jobStatusMigration.saveJobDetails(migrationRequest);
    }
}
