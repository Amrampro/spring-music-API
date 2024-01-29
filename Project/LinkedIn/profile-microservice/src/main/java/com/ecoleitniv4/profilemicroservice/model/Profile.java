package com.ecoleitniv4.profilemicroservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "profiles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long user_id;
    private String current_job_title;
    private String industry;
    private String summary;
    private String headline;
    private String website;
    private int open_for_work;
}
