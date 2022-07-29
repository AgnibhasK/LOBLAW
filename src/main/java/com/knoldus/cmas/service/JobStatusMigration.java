package com.knoldus.cmas.service;

import com.google.cloud.firestore.DocumentReference;
import com.knoldus.cmas.entity.JobStatus;
import com.knoldus.cmas.request.MigrationRequest;
import com.knoldus.cmas.response.MigrationResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

public interface JobStatusMigration{

    JobStatus getCustomersByJobId(String id) throws ExecutionException, InterruptedException;

    String setJobstatus(MigrationRequest migrationRequest) throws ExecutionException, InterruptedException, GeneralSecurityException, IOException;

}
