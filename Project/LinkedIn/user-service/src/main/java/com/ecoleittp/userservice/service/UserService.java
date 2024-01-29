package com.ecoleittp.userservice.service;

import com.ecoleittp.userservice.dto.UserRequest;
import com.ecoleittp.userservice.dto.UserResponse;
import com.ecoleittp.userservice.model.User;
import com.ecoleittp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/
    // Testing if id exist
    public boolean dataExist(Long id){
        if (userRepository.existsById(id)){
            return true;
        } else {
            log.info("User " + id + " does not exist");
            return false;
        }
    }

    // Test if user is aged >= 18
    public boolean ageValid(int age){
        if (age < 18) {
            log.info("User with age " + age + " Rejected. User should be at least 18 years old");
            return false;
        } else {
            return true;
        }
    }

    // -----------------------------------------------------------------
    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }

        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateStr, formatter);
    }
    // -----------------------------------------------------------------

    // Retrieving users information
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .password(user.getPassword())
                .email(user.getEmail())
                .birth_date(user.getBirth_date())
                .registration_date(user.getRegistration_date())
                .last_login_date(user.getLast_login_date())
                .age(user.getAge())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create a new user
    public String createUser(UserRequest userRequest) {
        boolean status = true;
        // Test function for age to be at least 18 years old at registry
        status = ageValid(userRequest.getAge());

        log.info("Creation status: " + status);

        if (status == true){
            User user = User.builder()
                    .username(userRequest.getUsername())
                    .first_name(userRequest.getFirst_name())
                    .last_name(userRequest.getLast_name())
                    .password(userRequest.getPassword())
                    .email(userRequest.getEmail())
                    .birth_date(userRequest.getBirth_date())
                    .registration_date(userRequest.getRegistration_date())
                    .last_login_date(userRequest.getLast_login_date())
                    .age(userRequest.getAge())
                    .build();

            userRepository.save(user);
            log.info("User " + user.getId() + " is saved successfully");
            return "User " + user.getId() + " is saved successfully";
        } else {
            return  "User less than 18 years not created";
        }
    }

    // Read all users
    public List<UserResponse> readUsers() {
        List<User> users;
        users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    // Read single user
    public UserResponse readUser(Long userId) {
        if (dataExist(userId)){
            User user = userRepository.findById(userId).get();
            return mapToUserResponse(user);
        } else {
            return null;
        }
    }

    // Update single user
    public String updateUser(Long userId, UserRequest updatedUserRequest) {
        boolean status = true;
        if (dataExist(userId)) {
            User existingUser = userRepository.findById(userId).get();

            // Test function for age to be at least 18 years old at update
            status = ageValid(updatedUserRequest.getAge());
            log.info("Creation status: " + status);

            if (status) {
                existingUser.setUsername(updatedUserRequest.getUsername());
                existingUser.setFirst_name(updatedUserRequest.getFirst_name());
                existingUser.setLast_name(updatedUserRequest.getLast_name());
                existingUser.setPassword(updatedUserRequest.getPassword());
                existingUser.setEmail(updatedUserRequest.getEmail());
                existingUser.setBirth_date(updatedUserRequest.getBirth_date());
                existingUser.setRegistration_date(updatedUserRequest.getRegistration_date());
                existingUser.setLast_login_date(updatedUserRequest.getLast_login_date());
                existingUser.setAge(updatedUserRequest.getAge());

                userRepository.save(existingUser);
                log.info("User " + userId + " is updated successfully");
                return "User " + userId + " is updated successfully";
            }
        }
        if (ageValid(updatedUserRequest.getAge()) == false){
            return "Invalid age : " + updatedUserRequest.getAge() + ". The required age is at least 18 years";
        }
        return "An error occured";
    }

    // Delete user
    public void deleteUser(Long userId) {
        if (dataExist(userId)) {
            userRepository.deleteById(userId);
        }
    }

    /***************************************************
     *               Other functions                   *
     ***************************************************/

    // Authenticate user
    public String authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        if (userOptional.isPresent()) {
            log.info("LogIn successful for username: " + username);
            return "LogIn successful for username: " + username;
        } else {
            log.info("Authentication failed for user: " + username);
            return "Authentication failed for user: " + username;
        }
    }
}
