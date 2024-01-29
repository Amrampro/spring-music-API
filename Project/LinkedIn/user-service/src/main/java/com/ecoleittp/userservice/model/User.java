package com.ecoleittp.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private Date birth_date;
//    private Date registration_date;
    private LocalDateTime registration_date;
    private Date last_login_date;
    private int age;
}