package com.knoldus.cmas.controller;

import com.knoldus.cmas.entity.JobStatus;

import com.knoldus.cmas.service.JobStatusMigration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class JobStatusMigrationController{
    @Autowired
    private JobStatusMigration jobStatusMigration;
//    @GetMapping("/job/status/{id}")
//    public JobStatus getJobStatusById(@PathVariable Long id){
//        return jobStatusMigration.getJobStatusById(id);
//    }
    @PostMapping("/job/status")
    public String getJobStatusById(@RequestBody JobStatus jobStatus) throws ExecutionException, InterruptedException{
        return jobStatusMigration.setJobstatus(jobStatus);
    }

}
