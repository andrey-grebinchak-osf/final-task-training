package com.example.demo.controller;

import com.example.demo.model.Test;
import com.example.demo.exception.TestNotFoundException;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    public TestService testService;

    //get test by name
    @GetMapping(value = "/test/{name}")
    public Test getTestByName(@PathParam("name") String name) {
        return testService.findTestByName(name);
    }

    //get all
    @GetMapping(value = "/tests")
    public Page<Test> getAll(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return testService.findAllTest(pageable);
    }

    //create
    @PostMapping("/test")
    public Test createNewTest(@RequestBody Test newTest) {
        return testService.saveTest(newTest);
    }

    //get test by id
    @GetMapping("/test/{id}")
    public Test getOneTest(@PathVariable Long id) {

        return testService.findTestById(id)
                .orElseThrow(() -> new TestNotFoundException(id));
    }

    //update test
    @PutMapping("/test/{id}")
    public Test updateTest(@RequestBody Test newTest, @PathVariable Long id) {

        return testService.findTestById(id)
                .map(test -> {
                    test.setName(newTest.getName());
                    test.setActive(newTest.getActive());
                    test.setCustomerId(newTest.getCustomerId());
                    return testService.saveTest(test);
                })
                .orElseGet(() -> {
                    newTest.setId(id);
                    return testService.saveTest(newTest);
                });
    }
/*
    private TestDTO convertToDto(Test test) {
        TestDTO testDto = modelMapper.map(test, TestDTO.class);
        return testDto;
    }

    private Test convertToEntity(TestDTO testDto) throws ParseException {
        Test test = modelMapper.map(testDto, Test.class);
        return test;
    }*/
}
