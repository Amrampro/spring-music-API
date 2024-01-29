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
public class UserDTOE {
    private Long id;
    private String username;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private Date birth_date;
    private Date registration_date;
    private Date last_login_date;
    private int age;
}
