package com.jumshudivanych.simplecrm.repos;

import com.jumshudivanych.simplecrm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
