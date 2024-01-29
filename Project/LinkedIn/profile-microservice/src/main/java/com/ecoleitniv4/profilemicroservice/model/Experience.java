package com.ecoleitniv4.profilemicroservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@Table(name = "experiences")
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long profile_id;
    private String job_title;
    private String company_name;
    private String location;
    private Date start_date;
    private Date end_date;
}
