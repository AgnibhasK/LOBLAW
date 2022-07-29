package com.knoldus.cmas.service;

import com.knoldus.cmas.response.MigrationResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MigrationService{
    MigrationResponse getMigrationResponse() throws ExecutionException,InterruptedException;
    List<MigrationResponse> getAllMigrationResponse()throws ExecutionException,InterruptedException;
    String createMigrationResponse(MigrationResponse migrationResponse) throws ExecutionException, InterruptedException;
    String updateMigrationResponse(MigrationResponse migrationResponse,String id) throws ExecutionException, InterruptedException;
    void deleteMigrationResponse(String id);

}
