package com.knoldus.cmas.service;

import com.google.api.services.cloudscheduler.v1.CloudScheduler;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface CloudSchedulerService{
    CloudScheduler createJobSchedule() throws GeneralSecurityException, IOException;
}
