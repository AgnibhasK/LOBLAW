package com.knoldus.cmas.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.cloudscheduler.v1.CloudScheduler;
import com.google.common.collect.Lists;
import com.knoldus.cmas.service.CloudSchedulerService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class CloudSchedulerServiceImpl implements CloudSchedulerService {

    static String jsonPath="src/main/resources/cloud_scheduler_service_key.json";

    String parent="projects/mindful-baton-356707/locations/asia-northeast1";


    static GoogleCredential authExplicit(String jsonPath) throws IOException{
        GoogleCredential credentials=GoogleCredential.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        return credentials;
    }

    @Override
    public CloudScheduler createJobSchedule() throws GeneralSecurityException, IOException{
        HttpTransport httpTransport=GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory=JacksonFactory.getDefaultInstance();
        GoogleCredential credentials=authExplicit(jsonPath);
        return new CloudScheduler.Builder(httpTransport,jsonFactory,credentials)
                .setApplicationName("Google-CloudSchedulerSample/0.1")
                .build();
    }
}
