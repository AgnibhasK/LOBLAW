package com.knoldus.cmas.service;

import com.google.api.services.cloudscheduler.v1.CloudScheduler;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

public interface CloudSchedulerService{
    CloudScheduler createCloudConnection() throws GeneralSecurityException, IOException;

    CloudScheduler scheduleJob(String job_id) throws GeneralSecurityException, IOException, ExecutionException, InterruptedException;
}
