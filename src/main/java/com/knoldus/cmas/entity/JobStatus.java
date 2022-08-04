package com.knoldus.cmas.entity;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobStatus{
    private String job_id;
    private String tenant_id;
    private List<Integer> customer_list;
    private String migration_direction;
    private String scheduled_start_time;
    private String status;
}
