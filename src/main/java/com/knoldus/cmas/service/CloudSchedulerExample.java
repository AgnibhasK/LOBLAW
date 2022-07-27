//package com.knoldus.cmas.service;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.cloudscheduler.v1.CloudScheduler;
//import com.google.api.services.cloudscheduler.v1.model.HttpTarget;
//import com.google.api.services.cloudscheduler.v1.model.Job;
//import com.google.common.collect.Lists;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//
//public class CloudSchedulerExample{
//
//    static String jsonPath="cmas/src/main/resources/cloud_scheduler_service_key.json";
//
//    public static void main(String args[]) throws IOException, GeneralSecurityException{
//        // Required.
//        // The location name. For example:
//        // `projects/PROJECT_ID/locations/LOCATION_ID`.
//        String parent="projects/mindful-baton-356707/locations/asia-northeast1";
//        Job requestBody=new Job();
//        HttpTarget http=new HttpTarget();
//
//        requestBody.setName("projects/mindful-baton-356707/locations/asia-northeast1/jobs/demo-second");
//        requestBody.setHttpTarget(http.setUri("https://jsonplaceholder.typicode.com/posts"));
//
//        requestBody.setScheduleTime("2022-07-25T09:06:42Z");
//        requestBody.setSchedule("* * * * *");
//        CloudScheduler cloudSchedulerService=createCloudSchedulerService();
//        CloudScheduler.Projects.Locations.Jobs.Create request=
//                cloudSchedulerService.projects().locations().jobs().create(parent,requestBody);
//        Job response=request.execute();
//
//        // TODO: Change code below to process the `response` object:
//        System.out.println(response);
//    }
//
//    public static CloudScheduler createCloudSchedulerService()
//            throws IOException, GeneralSecurityException{
//        HttpTransport httpTransport=GoogleNetHttpTransport.newTrustedTransport();
//        JsonFactory jsonFactory=JacksonFactory.getDefaultInstance();
//        GoogleCredential credentials=authExplicit(jsonPath);
//
//
//        return new CloudScheduler.Builder(httpTransport,jsonFactory,credentials)
//                .setApplicationName("Google-CloudSchedulerSample/0.1")
//                .build();
//    }
//
//    static GoogleCredential authExplicit(String jsonPath) throws IOException{
//        GoogleCredential credentials=GoogleCredential.fromStream(new FileInputStream(jsonPath))
//                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
//        return credentials;
//    }
//}
