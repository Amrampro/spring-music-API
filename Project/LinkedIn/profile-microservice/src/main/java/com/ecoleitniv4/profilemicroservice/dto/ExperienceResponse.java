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
public class ExperienceResponse {
    private Long id;
    private Long profile_id;
    private String job_title;
    private String company_name;
    private String location;
    private Date start_date;
    private Date end_date;
}
