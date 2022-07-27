package com.knoldus.cmas.service;

import com.knoldus.cmas.entity.JobStatus;

import java.util.concurrent.ExecutionException;

public interface JobStatusMigration{
//    JobStatus getJobStatusById(Long id);
    String setJobstatus(JobStatus jobstatus) throws ExecutionException, InterruptedException;

}
