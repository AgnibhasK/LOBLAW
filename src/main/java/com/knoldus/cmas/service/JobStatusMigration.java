package com.knoldus.cmas.service;

import com.knoldus.cmas.entity.JobStatus;
import com.knoldus.cmas.request.MigrationRequest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

public interface JobStatusMigration{

    JobStatus getJobDetailsById(String id) throws ExecutionException, InterruptedException;

    String saveJobDetails(MigrationRequest migrationRequest) throws ExecutionException, InterruptedException, GeneralSecurityException, IOException;

}
