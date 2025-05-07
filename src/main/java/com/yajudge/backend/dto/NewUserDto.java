package com.yajudge.backend.dto;

import com.yajudge.backend.model.NewUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto {
    private String name;
    private String email;
    private String username;
    private String gender;
    private String birthDate;
    private String provider;
    private String providerId;
    private Integer requestedLimit;
    private Integer usageLimit;
    private NewUser.Role role;
    private NewUser.Status status;

    // Convert DTO to entity
    public NewUser toEntity() {
        NewUser user = new NewUser();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setUsername(this.username);
        user.setGender(this.gender);
        user.setBirthDate(this.birthDate);
        user.setProvider(this.provider);
        user.setProviderId(this.providerId);
        user.setRequestedLimit(this.requestedLimit);
        user.setUsageLimit(this.usageLimit);
        user.setRole(this.role);
        user.setStatus(this.status);
        return user;
    }
} 