package com.knoldus.cmas.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.knoldus.cmas.entity.MigrationResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class MigrationServiceImpl implements MigrationService{
    private static final String COLLECTION_NAME="MigrationResponse";

    @Override
    public MigrationResponse getMigrationResponse() throws ExecutionException, InterruptedException{
        Firestore dbFirestore=FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document("Vl4QVv6QMx4ziAEu4EnY");
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        MigrationResponse migrationResponse = null;
        if(document.exists()){
            migrationResponse = document.toObject(MigrationResponse.class);
            return migrationResponse;
        }else{
            return null;
        }
   }

    @Override
    public List<MigrationResponse> getAllMigrationResponse() throws ExecutionException, InterruptedException{
        Firestore dbFirestore=FirestoreClient.getFirestore();
        CollectionReference collectionReference= dbFirestore.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        QuerySnapshot query = future.get();
        List<MigrationResponse> migrationResponse = query.toObjects(MigrationResponse.class);
        return migrationResponse;
    }

    @Override
    public String createMigrationResponse(MigrationResponse migrationResponse) throws ExecutionException, InterruptedException{
        Firestore dbFirestore=FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document().set(migrationResponse);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public String updateMigrationResponse(MigrationResponse migrationResponse,String id) throws ExecutionException, InterruptedException{
        Firestore dbFirestore=FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection(COLLECTION_NAME).document(id).set(migrationResponse);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    @Override
    public void deleteMigrationResponse(String id){
        Firestore dbFirestore=FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COLLECTION_NAME).document(id).delete();
    }

}
