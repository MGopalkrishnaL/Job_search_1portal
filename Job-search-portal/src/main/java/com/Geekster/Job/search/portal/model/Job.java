package com.Geekster.Job.search.portal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String location;
    @Min(value = 20000,message = "salary should be above 20,000")
    private Double salary;
    private String companyEmail;
    private String companyName;
    private String employerName;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private LocalDate appliedDate;
}
