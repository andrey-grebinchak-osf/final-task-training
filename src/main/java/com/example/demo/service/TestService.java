package com.example.demo.service;

import com.example.demo.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TestService {

    Optional<Test> findTestById(Long id);

    Page<Test> findAllTest(Pageable pageable);

    Test saveTest(Test test);
}
