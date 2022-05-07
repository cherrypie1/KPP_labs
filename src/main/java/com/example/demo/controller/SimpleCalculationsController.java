package com.example.demo.controller;

import com.example.demo.counter.CounterService;
import com.example.demo.entity.ResultValue;
import com.example.demo.repository.InputParams;
import com.example.demo.service.SimpleCalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
public class SimpleCalculationsController {

    @Autowired
    SimpleCalculationsService service;

    @GetMapping("/calculate")
    public ResultValue calculateByOperation(@RequestParam(value = "operation") String operation,
                                            @RequestParam(value = "value") int value) {
        return service.calculate(new InputParams(operation, value));
    }

    @GetMapping("/cache")
    public HashMap<InputParams, ResultValue> getCache() {
        return service.getNumberInMemoryCache();
    }

    @GetMapping("/counter")
    public ResponseEntity<Integer> getCount(){
        return new ResponseEntity<>(CounterService.getCounter(), HttpStatus.OK);
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateBulkOperation(@Valid @RequestBody List<InputParams> bodyList) {
        List<Integer> resultList = new LinkedList<>();
        bodyList.forEach((currentElem) -> {
            resultList.add(service.calculate(currentElem).getValue());
        });

        double averageResult = 0;
        if (!resultList.isEmpty()) {
            averageResult = resultList.stream().mapToInt(Integer::intValue).average().getAsDouble();
        }
        int maxResult = 0;
        if (!resultList.isEmpty()) {
            maxResult = resultList.stream().mapToInt(Integer::intValue).max().getAsInt();
        }
        int minResult = 0;
        if (!resultList.isEmpty()) {
            minResult = resultList.stream().mapToInt(Integer::intValue).min().getAsInt();
        }

        return new ResponseEntity<>(resultList + "\nAverage: " + averageResult + "\nMax result: " +
                maxResult + "\nMin result: " + minResult, HttpStatus.OK);
    }
}
