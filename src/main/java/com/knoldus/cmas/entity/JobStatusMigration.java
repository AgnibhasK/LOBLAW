package com.knoldus.cmas.entity;

import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobStatusMigration{
    private String tenant_id;
    private Long customer_id;
    private Long job_id;
    private String migration_direction;
    private Timestamp scheduled_start_time;
    private String status;
}
