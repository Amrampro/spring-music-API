package com.ecoleitniv4.profilemicroservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "profiles_connections")
@NoArgsConstructor
@AllArgsConstructor
public class ProfilesConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long profile1_id;
    private Long profile2_id;
}
