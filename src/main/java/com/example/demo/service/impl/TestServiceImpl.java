package com.example.demo.service.impl;

import com.example.demo.entity.Test;
import com.example.demo.repo.TestRepository;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    public TestRepository testRepository;

    public Optional<Test> findTestById(Long id) {
        return testRepository.findById(id);
    }

    public Page<Test> findAllTest(Pageable pageable) {
        return testRepository.findAll(pageable);
    }

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }
}
