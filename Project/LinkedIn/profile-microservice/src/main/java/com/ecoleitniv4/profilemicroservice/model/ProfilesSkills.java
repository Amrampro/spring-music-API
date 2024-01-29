package com.ecoleitniv4.profilemicroservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "profiles_skills")
@Entity
public class ProfilesSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long profile_id;
    private Long skill_id;
}
