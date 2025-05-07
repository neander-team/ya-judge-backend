package com.yajudge.backend.repository;

import com.yajudge.backend.model.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewUserRepository extends JpaRepository<NewUser, Long> {
    // Add custom query methods if needed
} 