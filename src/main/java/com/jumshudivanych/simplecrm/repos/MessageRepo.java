package com.jumshudivanych.simplecrm.repos;

import com.jumshudivanych.simplecrm.entity.Message;
import com.jumshudivanych.simplecrm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> findByTag(String tag);
}
