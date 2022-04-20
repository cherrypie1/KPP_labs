package com.example.demo.controller;

import com.example.demo.entity.ResultValue;
import com.example.demo.repository.InputParams;
import com.example.demo.service.SimpleCalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleCalculationsController {

    @Autowired
    SimpleCalculationsService service;

    @GetMapping("/calculate")
    public ResultValue calculateByOperation(@RequestParam(value = "operation") String operation,
                                            @RequestParam(value = "value") int value) {
        return service.calculate(new InputParams(operation, value));
    }

}
