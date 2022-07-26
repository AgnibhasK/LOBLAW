package com.knoldus.cmas.controller;

import com.google.api.services.cloudscheduler.v1.CloudScheduler;
import com.google.api.services.cloudscheduler.v1.model.HttpTarget;
import com.google.api.services.cloudscheduler.v1.model.Job;
import com.knoldus.cmas.service.CloudSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class JobScheduleController{
    @Autowired
    CloudSchedulerService cloudSchedulerService;
    @PostMapping("/schedule/{job_id}")
    public CloudScheduler createJobSchedule(@PathVariable Long job_id) throws GeneralSecurityException, IOException{
       // job_id = 1234L;
      CloudScheduler job = cloudSchedulerService.createJobSchedule();
      String parent="projects/mindful-baton-356707/locations/asia-northeast1";
        Job requestBody=new Job();
        HttpTarget http=new HttpTarget();
        requestBody.setName("projects/mindful-baton-356707/locations/asia-northeast1/jobs/" + job_id);
        requestBody.setHttpTarget(http.setUri("https://jsonplaceholder.typicode.com/posts"));

        requestBody.setScheduleTime("2022-07-25T09:06:42Z");
        requestBody.setSchedule("* * * * *");
        CloudScheduler.Projects.Locations.Jobs.Create request=
                job.projects().locations().jobs().create(parent,requestBody);
        Job response=request.execute();
        return job;
    }

}
