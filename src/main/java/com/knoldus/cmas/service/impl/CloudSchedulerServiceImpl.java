package com.knoldus.cmas.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.cloudscheduler.v1.CloudScheduler;
import com.google.api.services.cloudscheduler.v1.model.HttpTarget;
import com.google.api.services.cloudscheduler.v1.model.Job;
import com.google.common.collect.Lists;
import com.knoldus.cmas.service.CloudSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import static com.knoldus.cmas.util.Constants.*;

@Service
public class CloudSchedulerServiceImpl implements CloudSchedulerService {

    static GoogleCredential authExplicit(String jsonPath) throws IOException {
        GoogleCredential credentials = GoogleCredential.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        return credentials;
    }

    @Value(value = "cloud.scheduler.service.key.json.path")
    String G;

    @Autowired
    Environment environment;

    @Override
    public CloudScheduler createCloudConnection() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleCredential credentials = authExplicit(environment.getProperty(CLOUD_SCHEDULER_JSON_PATH));
        return new CloudScheduler.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Override
    public CloudScheduler scheduleJob(String job_id) throws GeneralSecurityException, IOException, ExecutionException, InterruptedException {
        CloudScheduler job = createCloudConnection();
        Job requestBody = new Job();
        HttpTarget http = new HttpTarget();
        requestBody.setName(PROJECT_PATH_NAME + job_id);
        requestBody.setHttpTarget(http.setUri(environment.getProperty(HTTPS_TARGET) + job_id));
        JobStatusMigrationImpl jobStatusMigration = new JobStatusMigrationImpl();
        requestBody.setScheduleTime(jobStatusMigration.getJobDetailsById(job_id).getScheduled_start_time());
        requestBody.setSchedule("* * * * *");
        CloudScheduler.Projects.Locations.Jobs.Create request =
                job.projects().locations().jobs().create(CLOUD_SCHEDULER_PARENT_LOCATION, requestBody);
        request.execute();
        return job;
    }
}
