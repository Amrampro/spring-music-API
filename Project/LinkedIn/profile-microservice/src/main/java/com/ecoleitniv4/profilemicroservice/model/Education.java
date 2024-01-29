package com.ecoleitniv4.profilemicroservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "education")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long profile_id;
    private String school;
    private String degree;
    private String field_of_study;
    private Date start_date;
    private Date end_date;
}
