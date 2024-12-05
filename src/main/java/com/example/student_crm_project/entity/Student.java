package com.example.student_crm_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Used for Getters,Setters, toString, equals and hashcode
@NoArgsConstructor // Generates a non-arguments constructor
@AllArgsConstructor // Generates an all argument constructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private int phone;
}
