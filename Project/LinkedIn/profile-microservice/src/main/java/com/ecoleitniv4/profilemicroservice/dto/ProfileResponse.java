package com.ecoleitniv4.profilemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private Long id;
    private Long user_id;
    private String current_job_title;
    private String industry;
    private String summary;
    private String headline;
    private String website;
    private int open_for_work;
}
