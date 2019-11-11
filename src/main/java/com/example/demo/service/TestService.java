package com.example.demo.service;

import com.example.demo.repo.TestRepository;
import com.example.demo.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {

    @Autowired
    public TestRepository testRepository;

    public Test findTestByName(String name) {
        return testRepository.findByName(name);
    }

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
