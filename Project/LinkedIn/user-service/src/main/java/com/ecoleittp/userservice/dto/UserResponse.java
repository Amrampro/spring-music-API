package com.ecoleittp.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private Date birth_date;
    private LocalDateTime registration_date;
    private Date last_login_date;
    private int age;
}
