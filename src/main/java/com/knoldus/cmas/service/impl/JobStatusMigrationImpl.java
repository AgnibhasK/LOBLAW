package com.knoldus.cmas.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.knoldus.cmas.controller.JobScheduleController;
import com.knoldus.cmas.entity.JobStatus;
import com.knoldus.cmas.request.MigrationRequest;
import com.knoldus.cmas.response.MigrationResponse;
import com.knoldus.cmas.service.JobStatusMigration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

@Service
public class JobStatusMigrationImpl implements JobStatusMigration {
    private static final String COLLECTION_NAME="jobStatus";

    @Override
    public JobStatus getCustomersByJobId(String id) throws ExecutionException, InterruptedException{
        Firestore dbFirestore=FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        JobStatus jobStatus = null;
        if(document.exists()){
            jobStatus = document.toObject(JobStatus.class);
            return jobStatus;
        }else{
            return null;
        }
    }

    @Override
    public String setJobstatus(MigrationRequest migrationRequest) throws ExecutionException, InterruptedException, GeneralSecurityException, IOException {
        Firestore dbFirestore=FirestoreClient.getFirestore();
//        ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document().set(migrationRequest);
        ApiFuture<DocumentReference> documentReferenceApiFuture=dbFirestore.collection(COLLECTION_NAME).add(migrationRequest);
        documentReferenceApiFuture.get().update("job_id", documentReferenceApiFuture.get().getId());
        JobScheduleController jobScheduleController =new JobScheduleController();
        jobScheduleController.createJobSchedule(documentReferenceApiFuture.get().getId());
//        return collectionApiFuture.get().getUpdateTime().toString();
        return documentReferenceApiFuture.get().getId();
    }
}

