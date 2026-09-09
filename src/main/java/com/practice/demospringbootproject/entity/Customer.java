package com.practice.demospringbootproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

/**
 * Adding indexing for performance optimization.
 */
@Table(
        name = "customer",
        indexes = {
                @Index(name = "idx_cust_email",
                columnList = "customer_email")
        }
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private Boolean isDeleted=false;
}
