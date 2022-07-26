package com.knoldus.cmas.controller;

import com.google.api.services.cloudscheduler.v1.CloudScheduler;
import com.google.api.services.cloudscheduler.v1.model.HttpTarget;
import com.google.api.services.cloudscheduler.v1.model.Job;
import com.knoldus.cmas.service.CloudSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
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
      CloudScheduler job = cloudSchedulerService.createJobSchedule();
      String parent="projects/mindful-baton-356707/locations/asia-northeast1";
        Job requestBody=new Job();
        HttpTarget http=new HttpTarget();
        requestBody.setName("projects/mindful-baton-356707/locations/asia-northeast1/jobs/demo-second");
        requestBody.setHttpTarget(http.setUri("http://localhost:8086/schedule/"+job_id));

        requestBody.setScheduleTime("2022-07-25T09:06:42Z");
        requestBody.setSchedule("* * * * *");
        CloudScheduler.Projects.Locations.Jobs.Create request=
                job.projects().locations().jobs().create(parent,requestBody);
        Job response=request.execute();
        return job;
    }

}
