package com.ecoleitniv4.profilemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationRequest {
    private Long profile_id;
    private String school;
    private String degree;
    private String field_of_study;
    private Date start_date;
    private Date end_date;
}
