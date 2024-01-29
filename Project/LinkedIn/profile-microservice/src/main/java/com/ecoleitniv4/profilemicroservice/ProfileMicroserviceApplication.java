package com.ecoleitniv4.profilemicroservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// These following lines are to add swagger documentation
@OpenAPIDefinition(info = @Info(
        title = "Profile microservice REST API Documentation",
        description = "Including all classes, services and repository of the entities 'Profile','ProfilesConnection','Education','Experience','ProfilesSkills','Skills' and the CRUD of the entities 'Profile' and 'ProfilesConnections'",
        version = "v1.0",
        contact = @Contact(
                name = "Amram Bassime",
                email = "gouliben@gmail.com"
        )
))
public class ProfileMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileMicroserviceApplication.class, args);
    }

}
