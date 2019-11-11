package com.example.demo.repo;

import com.example.demo.entity.EskillUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<EskillUser, Long> {

    EskillUser findByUsername(String name);
}
