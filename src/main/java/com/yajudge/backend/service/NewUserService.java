package com.yajudge.backend.service;

import com.yajudge.backend.model.NewUser;
import com.yajudge.backend.repository.NewUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewUserService {

    private final NewUserRepository newUserRepository;

    @Autowired
    public NewUserService(NewUserRepository newUserRepository) {
        this.newUserRepository = newUserRepository;
    }

    public List<NewUser> getAllUsers() {
        return newUserRepository.findAll();
    }

    public Optional<NewUser> getUserById(Long id) {
        return newUserRepository.findById(id);
    }

    public NewUser createUser(NewUser newUser) {
        // Set default values if not provided
        LocalDateTime now = LocalDateTime.now();
        
        if (newUser.getCreatedAt() == null) {
            newUser.setCreatedAt(now);
        }
        
        if (newUser.getUpdatedAt() == null) {
            newUser.setUpdatedAt(now);
        }
        
        if (newUser.getUuid() == null || newUser.getUuid().isEmpty()) {
            newUser.setUuid(UUID.randomUUID().toString());
        }
        
        if (newUser.getStatus() == null) {
            newUser.setStatus(NewUser.Status.ACTIVE);
        }
        
        if (newUser.getRole() == null) {
            newUser.setRole(NewUser.Role.USER);
        }
        
        return newUserRepository.save(newUser);
    }

    public NewUser updateUser(Long id, NewUser userDetails) {
        return newUserRepository.findById(id)
                .map(user -> {
                    if (userDetails.getBirthDate() != null) {
                        user.setBirthDate(userDetails.getBirthDate());
                    }
                    if (userDetails.getEmail() != null) {
                        user.setEmail(userDetails.getEmail());
                    }
                    if (userDetails.getGender() != null) {
                        user.setGender(userDetails.getGender());
                    }
                    if (userDetails.getName() != null) {
                        user.setName(userDetails.getName());
                    }
                    if (userDetails.getProvider() != null) {
                        user.setProvider(userDetails.getProvider());
                    }
                    if (userDetails.getProviderId() != null) {
                        user.setProviderId(userDetails.getProviderId());
                    }
                    if (userDetails.getRequestedLimit() != null) {
                        user.setRequestedLimit(userDetails.getRequestedLimit());
                    }
                    if (userDetails.getRole() != null) {
                        user.setRole(userDetails.getRole());
                    }
                    if (userDetails.getStatus() != null) {
                        user.setStatus(userDetails.getStatus());
                    }
                    if (userDetails.getUsageLimit() != null) {
                        user.setUsageLimit(userDetails.getUsageLimit());
                    }
                    if (userDetails.getUsername() != null) {
                        user.setUsername(userDetails.getUsername());
                    }
                    
                    user.setUpdatedAt(LocalDateTime.now());
                    return newUserRepository.save(user);
                })
                .orElse(null);
    }

    public void deleteUser(Long id) {
        newUserRepository.deleteById(id);
    }
} 