package com.anjaneya.jasper.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @SequenceGenerator(name = "emp_id",
    allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_id")
    Integer id;

    String firstName;
    String lastName;
    String email;
    String mobile;
}