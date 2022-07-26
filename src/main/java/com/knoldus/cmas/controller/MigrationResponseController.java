package com.knoldus.cmas.controller;

import com.knoldus.cmas.entity.MigrationResponse;
import com.knoldus.cmas.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class MigrationResponseController{
    @Autowired
    private MigrationService migrationService;

    @PostMapping("/migration/status")
    public String createMigrationResponse(@RequestBody MigrationResponse migrationResponse) throws ExecutionException, InterruptedException{
        return migrationService.createMigrationResponse(migrationResponse);
    }
    @GetMapping("/migration/status")
    public MigrationResponse getMigrationResponse() throws ExecutionException, InterruptedException{
        return migrationService.getMigrationResponse();
    }
    @GetMapping("/all/migration/status")
    public List<MigrationResponse> getAllMigrationResponse() throws ExecutionException, InterruptedException{
        return migrationService.getAllMigrationResponse();
    }
    @PutMapping("/migration/status")
    public String updateMigrationResponse(@RequestBody MigrationResponse migrationResponse,String id) throws ExecutionException, InterruptedException{
        return migrationService.updateMigrationResponse(migrationResponse,"iPNNwMAbHI06XV8tkKPu");
    }
    @DeleteMapping("/migration")
    public void deleteMigrationResponse(String id){
        migrationService.deleteMigrationResponse("tVZAjl5eyyyNUvb243sG");
    }
}
