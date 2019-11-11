package com.example.demo.repo;

import com.example.demo.model.EskillUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<EskillUser, Long> {

    EskillUser findByUsername(String name);
}
