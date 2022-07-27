package com.knoldus.cmas.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.cloud.FirestoreClient;
import com.knoldus.cmas.entity.JobStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class JobStatusMigrationImpl implements JobStatusMigration{
    private static final String COLLECTION_NAME="jobStatus";

//    @Override
//    public JobStatus getJobStatusById(Long id){
//        return null;
//    }

    @Override
    public String setJobstatus(JobStatus jobstatus) throws ExecutionException, InterruptedException{
        Firestore dbFirestore=FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document().set(jobstatus);
        return collectionApiFuture.get().getUpdateTime().toString();

    }
}

