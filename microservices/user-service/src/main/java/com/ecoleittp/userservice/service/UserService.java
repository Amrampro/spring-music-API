package com.ecoleittp.userservice.service;

import com.ecoleittp.userservice.dto.UserRequest;
import com.ecoleittp.userservice.dto.UserResponse;
import com.ecoleittp.userservice.model.User;
import com.ecoleittp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // Create a new User
    public void createUser(UserRequest userRequest) {
        // username and email are required
        boolean create = true;

        if (userRequest.getUsername() == null || userRequest.getEmail() == null) {
            create = false;
            log.info("No username or email");
        }

        log.info("The Creation authorization: " + create + " -");

        if (create == true){
            User user = User.builder()
                    .username(userRequest.getUsername())
                    .email(userRequest.getEmail())
                    .build();

            userRepository.save(user);
            log.info("User "+ user.getId() +" is saved successfully");
        }
    }

    // List of users
    public List<UserResponse> getAllUsers() {
        List<User> users;
        users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    // Get single user
    public UserResponse getSingleUser(Long userId) {
        if (userRepository.existsById(userId)){
            User user = userRepository.findById(userId).get();

            return mapToUserResponse(user);
        } else {
            log.info("User with id " + userId + " Not found");
            return null;
        }
    }

    // Delete user
    public void deleteUser(Long userId){
        if (userRepository.existsById(userId)){
            userRepository.deleteById(userId);
        } else {
            log.info("User with id " + userId + " Not found");
        }
    }

    // Update user
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        // Test user_id
        boolean create = true;
        if (userRepository.existsById(userId)) {
            User user = userRepository.findById(userId).get();

            // Update user properties based on the request
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());

            // Save the updated user
            userRepository.save(user);

            return mapToUserResponse(user);
        } else {
            create = false;
            log.info("User with id " + userId + " not found");
            return null; // Or throw an exception, depending on your requirements
        }
    }

}
