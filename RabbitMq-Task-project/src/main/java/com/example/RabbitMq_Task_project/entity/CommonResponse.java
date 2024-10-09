package com.example.RabbitMq_Task_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CommonResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String contact;
    private String college;
    private String branch;
    private int year;
    private String section;

    // New field to track processing status
    private boolean processed; // Indicates if the response has been processed
}
