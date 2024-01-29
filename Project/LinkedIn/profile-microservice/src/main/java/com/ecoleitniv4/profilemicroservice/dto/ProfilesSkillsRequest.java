package com.ecoleitniv4.profilemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfilesSkillsRequest {
    private Long profile_id;
    private Long skill_id;
}
