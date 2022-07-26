package com.knoldus.cmas.entity;


import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMigrationStatus{
    private String cart_migration_status;
    private Long customer_id;
    private String customer_profile_migration_status;
    private Timestamp migration_end_time;
    private Timestamp migration_start_time;
    private String order_migration_status;
    private String payment_method_migration_status;
    private String traffic_destination;
    private String tenant_id;
}
