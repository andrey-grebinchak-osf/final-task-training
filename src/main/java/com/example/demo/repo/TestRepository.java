package com.example.demo.repo;

import com.example.demo.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

    Test findByName(String name);
}
